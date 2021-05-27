package com.motschik.spigotplugin.pvp.game;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import com.motschik.spigotplugin.pvp.PvpPlugin;
import com.motschik.spigotplugin.pvp.rule.PvpRule;

public abstract class Game {

  private GameConfig gameConfig = new GameConfig();
  protected final PvpPlugin plugin;

  protected Scoreboard board;

  protected List<PvpRule> ruleList = new ArrayList<>();
  protected int selectedRule = 0;

  protected boolean enable = false;

  /**
   * コンストラクタ.
   *
   * @param plugin プラグインインスタンス
   */
  public Game(PvpPlugin plugin) {
    this.plugin = plugin;
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    board = manager.getMainScoreboard();
  }

  /**
   * ゲームセット処理.
   */
  public void gameSet(String entry) {
    ruleList.get(selectedRule).stopTickCycle();
  }

  /**
   * スコアリセット.
   */
  public void resetScore() {
    if (enable) {
      ruleList.get(selectedRule).startTickCycle();
    }
  }

  /**
   * ゲーム開始.
   */
  public void startGame() {
    enable = true;
    // ゲームルールの設定
    World world = plugin.getServer().getWorld("world");
    world.setGameRule(GameRule.NATURAL_REGENERATION, false);
    world.setGameRule(GameRule.KEEP_INVENTORY, true);
  }

  /**
   * ゲーム終了.
   */
  public void endGame() {
    ruleList.get(selectedRule).stopTickCycle();
    board.clearSlot(DisplaySlot.PLAYER_LIST);
    board.clearSlot(DisplaySlot.SIDEBAR);
    board.clearSlot(DisplaySlot.BELOW_NAME);
    board.getObjectives().forEach(obj -> {
      obj.unregister();
    });
    // ゲームルールの設定
    World world = plugin.getServer().getWorld("world");
    world.setGameRule(GameRule.NATURAL_REGENERATION, true);
    world.setGameRule(GameRule.KEEP_INVENTORY, false);
    enable = false;
  }

  /**
   * スコア追加.
   *
   * @param entry Entry
   * @param scr 追加スコア
   */
  public abstract void addScore(String entry, int scr);

  public GameConfig getGameConfig() {
    return gameConfig;
  }

  public void setGameConfig(GameConfig gameConfig) {
    this.gameConfig = gameConfig;
  }

  public void addRule(PvpRule area) {
    ruleList.add(area);
  }

  public Scoreboard getBoard() {
    return board;
  }

  public void setBoard(Scoreboard board) {
    this.board = board;
  }
}
