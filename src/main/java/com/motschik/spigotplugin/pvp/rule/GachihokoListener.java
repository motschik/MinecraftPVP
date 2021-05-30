package com.motschik.spigotplugin.pvp.rule;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GachihokoListener implements Listener {

  protected final JavaPlugin plugin;

  public GachihokoListener(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onDropItem(PlayerDropItemEvent event) {
    event.getItemDrop().setGlowing(true);
  }

}
