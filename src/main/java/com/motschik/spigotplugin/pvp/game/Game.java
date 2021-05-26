package com.motschik.spigotplugin.pvp.game;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Game {

  private GameConfig gameConfig = new GameConfig();
  protected JavaPlugin plugin;

  protected Scoreboard board;

  public GameConfig getGameConfig() {
    return gameConfig;
  }

  public void setGameConfig(GameConfig gameConfig) {
    this.gameConfig = gameConfig;
  }

  /**
   * コンストラクタ.
   *
   * @param plugin プラグインインスタンス
   */
  public Game(JavaPlugin plugin) {
    this.plugin = plugin;
    ScoreboardManager manager = Bukkit.getScoreboardManager();
    board = manager.getMainScoreboard();
  }

  /**
   * ゲームセット処理.
   */
  public void gameSet() {

  }

  /**
   * スコアリセット.
   */
  public void resetScore() {}

  /**
   * ゲーム開始.
   */
  public void startGame() {

  }

  /**
   * ゲーム終了.
   */
  public void endGame() {
    board.clearSlot(DisplaySlot.PLAYER_LIST);
    board.clearSlot(DisplaySlot.SIDEBAR);
    board.clearSlot(DisplaySlot.BELOW_NAME);
    board.getObjectives().forEach(obj -> {
      obj.unregister();
    });
  }

}
