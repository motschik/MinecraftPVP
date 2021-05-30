package com.motschik.spigotplugin.pvp.rule;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import com.motschik.spigotplugin.pvp.game.Game;

public class Gachihoko extends PvpRule {

  private Location hokoSpawn;
  private String havingPlayerName = null;

  public Gachihoko(Game game, JavaPlugin plugin) {
    super(game, plugin);
    // TODO 自動生成されたコンストラクター・スタブ
  }

  @Override
  public void startTickCycle() {
    // TODO 自動生成されたメソッド・スタブ

  }

  @Override
  public void stopTickCycle() {
    // TODO 自動生成されたメソッド・スタブ

  }

  public void setHokoSpawn(Location loc) {
    hokoSpawn = loc;
  }



}
