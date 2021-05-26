package com.motschik.spigotplugin.pvp.game;

public class GameConfig {
  /** 時間制限の有無. */
  private boolean timeUp;
  /** スコア勝利の有無. */
  private boolean scoreUp;
  /** 時間制限. */
  private int timeLimit;
  /** スコア勝利条件値. */
  private int scoreLimit;


  public GameConfig() {}

  public boolean isTimeUp() {
    return timeUp;
  }

  public void setTimeUp(boolean timeUp) {
    this.timeUp = timeUp;
  }

  public boolean isScoreUp() {
    return scoreUp;
  }

  public void setScoreUp(boolean scoreUp) {
    this.scoreUp = scoreUp;
  }

  public int getTimeLimit() {
    return timeLimit;
  }

  public void setTimeLimit(int timeLimit) {
    this.timeLimit = timeLimit;
  }

  public int getScoreLimit() {
    return scoreLimit;
  }

  public void setScoreLimit(int scoreLimit) {
    this.scoreLimit = scoreLimit;
  }
}
