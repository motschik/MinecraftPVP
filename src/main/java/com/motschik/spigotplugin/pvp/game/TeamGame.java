package com.motschik.spigotplugin.pvp.game;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

public class TeamGame extends Game {

  List<PvpTeam> teams = new ArrayList<>();

  public TeamGame(JavaPlugin plugin) {
    super(plugin);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void gameSet() {
    super.gameSet();
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

    // ゲームルールの設定
    World world = plugin.getServer().getWorld("world");
    world.setGameRule(GameRule.NATURAL_REGENERATION, false);
    world.setGameRule(GameRule.KEEP_INVENTORY, true);

    resetScore();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void endGame() {
    super.endGame();
    removeTeamPlayerAll();
  }

  /**
   * チーム追加.
   *
   * @param name チーム名
   * @param colorCode チームカラー(DefineName or ColorCode)
   */
  public boolean addTeam(String name, String colorCode) {

    ChatColor color = null;
    try {
      color = ChatColor.valueOf(colorCode);
    } catch (IllegalArgumentException e) {
      color = ChatColor.getByChar(colorCode);
    }

    if (color == null) {
      return false;
    }

    PvpTeam pvpTeam = new PvpTeam(board.registerNewTeam(name), color);
    Team team = pvpTeam.getTeam();

    team.setDisplayName(name);
    team.setColor(color);
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
   * @param playerName プレイヤー
   * @return result
   */
  public boolean joinTeam(String teamName, String playerName) {
    board.getTeam(teamName).addEntry(playerName);
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
      gameSet();
    }
    score.setScore(nextScore);
  }


}
