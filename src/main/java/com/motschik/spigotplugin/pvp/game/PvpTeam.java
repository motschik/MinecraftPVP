package com.motschik.spigotplugin.pvp.game;

import org.bukkit.scoreboard.Team;
import com.motschik.spigotplugin.pvp.color.TeamColor;

public class PvpTeam {

  private Team team;
  private int score;
  private TeamColor color;


  /**
   * コンストラクタ.
   *
   * @param team Teamインスタンス
   * @param color チームカラー
   */
  public PvpTeam(Team team, TeamColor color) {
    this.team = team;
    this.color = color;
    this.score = 0;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public TeamColor getColor() {
    return color;
  }

  public void setColor(TeamColor color) {
    this.color = color;
  }



}
