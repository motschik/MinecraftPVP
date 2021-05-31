package com.motschik.spigotplugin.pvp.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;
import com.motschik.spigotplugin.pvp.PvpPlugin;
import com.motschik.spigotplugin.pvp.color.TeamColor;

public class TeamGame extends Game {

  List<PvpTeam> teams = new ArrayList<>();

  public TeamGame(PvpPlugin plugin) {
    super(plugin);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void gameSet(String entry) {
    super.gameSet(entry);
    String title = entry + " team win!!";
    plugin.getServer().getOnlinePlayers().forEach(player -> {
      player.sendTitle(title, "", 5, 70, 10);
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void resetScore() {
    super.resetScore();
    if (!enable) {
      return;
    }
    Objective point = board.getObjective("point");
    teams.forEach(team -> {
      point.getScore(team.getTeam().getName()).setScore(0);
      team.getTeam().addEntry(team.getTeam().getName());
    });
    Objective killCount = board.getObjective("kill");
    plugin.getServer().getOnlinePlayers().forEach(player -> {
      board.resetScores(player.getName());
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void startGame() {
    if (enable) {
      return;
    }
    super.startGame();


    // キルカウント追加
    Objective killCount =
        board.registerNewObjective("kill", "playerKillCount", "kill", RenderType.INTEGER);
    killCount.setDisplaySlot(DisplaySlot.PLAYER_LIST);

    // ポイントリスト追加
    Objective point = board.registerNewObjective("point", "dummy", "point", RenderType.INTEGER);
    point.setDisplaySlot(DisplaySlot.SIDEBAR);

    resetScore();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void endGame() {
    super.endGame();
    plugin.getServer().getOnlinePlayers().forEach(player -> {
      PlayerInventory inventory = player.getInventory();
      ItemStack helmet = inventory.getHelmet();
      if (helmet != null && helmet.getItemMeta().isUnbreakable()) {
        inventory.clear();
      }

    });
    removeTeamPlayerAll();
  }

  /**
   * チーム追加.
   *
   * @param name チーム名
   * @param colorCode チームカラー(DefineName or ColorCode)
   */
  public boolean addTeam(String name, String colorCode) {

    TeamColor color = null;
    try {
      color = TeamColor.valueOf(colorCode);
    } catch (IllegalArgumentException e) {
      return false;
    }

    PvpTeam pvpTeam = new PvpTeam(board.registerNewTeam(name), color);
    Team team = pvpTeam.getTeam();

    team.setDisplayName(name);
    team.setColor(color.getChatColor());
    team.setCanSeeFriendlyInvisibles(true);
    team.setAllowFriendlyFire(false);
    team.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.FOR_OTHER_TEAMS);
    teams.add(pvpTeam);
    team.addEntry(name);

    resetScore();

    return true;
  }

  /**
   * チーム削除.
   *
   * @param name チーム名
   * @return result
   */
  public boolean removeTeam(String name) {
    teams.removeIf(team -> team.getTeam().getName().equals(name));
    board.resetScores(name);
    board.getTeam(name).unregister();
    resetScore();
    return true;
  }

  /**
   * チーム全削除.
   *
   * @return result
   */
  public boolean removeAllTeam() {
    board.getTeams().forEach(team -> {
      board.resetScores(team.getName());
      team.unregister();
    });
    teams.clear();
    resetScore();
    return true;
  }

  /**
   * チームへ追加.
   *
   * @param teamName チーム名
   * @param player プレイヤー
   * @return result
   */
  public boolean joinTeam(String teamName, Player player) {
    if (!ebleJoinGame(player)) {
      return false;
    }
    board.getTeam(teamName).addEntry(player.getName());
    plugin.getEquipment().equipTeamHelmet(player);
    return true;
  }

  private boolean ebleJoinGame(Player player) {
    PlayerInventory inventory = player.getInventory();
    ItemStack helmet = inventory.getHelmet();
    if (helmet != null && helmet.getItemMeta().isUnbreakable()) {
      return true;
    }
    return inventory.isEmpty();
  }

  /**
   * チームから抜ける.
   *
   * @param player プレイヤー
   * @return result
   */
  public boolean removeTeamPlayer(Player player) {
    Optional<Team> team =
        board.getTeams().stream().filter(t -> t.hasEntry(player.getName())).findFirst();
    if (team.isPresent()) {
      team.get().removeEntry(player.getName());
      PlayerInventory inventory = player.getInventory();
      ItemStack helmet = inventory.getHelmet();
      if (helmet != null && helmet.getItemMeta().isUnbreakable()) {
        inventory.clear();
      }
    }
    return true;
  }

  // チーム解散
  private void removeTeamPlayerAll() {
    teams.forEach(team -> {
      team.getTeam().getEntries().forEach(entry -> {
        team.getTeam().removeEntry(entry);
      });
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addScore(String entry, int scr) {
    String teamName = board.getEntryTeam(entry).getName();

    Score score = board.getObjective("point").getScore(teamName);
    int nextScore = score.getScore() + scr;
    if (nextScore > getGameConfig().getScoreLimit()) {
      nextScore = getGameConfig().getScoreLimit();
      gameSet(teamName);
    }
    score.setScore(nextScore);
  }



}
