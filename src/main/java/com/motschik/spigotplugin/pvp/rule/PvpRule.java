package com.motschik.spigotplugin.pvp.rule;

import org.bukkit.plugin.java.JavaPlugin;
import com.motschik.spigotplugin.pvp.game.Game;

public abstract class PvpRule {

  protected final Game game;
  protected final JavaPlugin plugin;

  /**
   * コンストラクタ.
   *
   * @param game Game
   * @param plugin Plugin
   */
  public PvpRule(Game game, JavaPlugin plugin) {
    this.game = game;
    this.plugin = plugin;
  }

  public abstract void startTickCycle();

  public abstract void stopTickCycle();

}
