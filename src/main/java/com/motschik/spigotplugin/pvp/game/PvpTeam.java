package com.motschik.spigotplugin.pvp.game;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

public class PvpTeam {

  private Team team;
  private int score;
  private ChatColor color;


  /**
   * コンストラクタ.
   *
   * @param team Teamインスタンス
   * @param color チームカラー
   */
  public PvpTeam(Team team, ChatColor color) {
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

  public ChatColor getColor() {
    return color;
  }

  public void setColor(ChatColor color) {
    this.color = color;
  }



}
