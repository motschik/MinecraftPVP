package com.motschik.spigotplugin.pvp.rule;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GachihokoListener implements Listener {

  protected final JavaPlugin plugin;

  private String discName = "伝説のDVD";

  public GachihokoListener(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onDropItem(PlayerDropItemEvent event) {
    ItemStack dropItem = event.getItemDrop().getItemStack();
    if (dropItem.getType().equals(Material.MUSIC_DISC_CAT)
        && dropItem.getItemMeta().getDisplayName().equals(discName)) {
      event.getItemDrop().setGlowing(true);
    }
  }

  @EventHandler
  public void onDeath(PlayerDeathEvent event) {
    Player player = event.getEntity();

    boolean haveDisc = false;
    ItemStack disc = null;
    for (ItemStack item : player.getInventory().all(Material.MUSIC_DISC_CAT).values()) {
      haveDisc |= item.getItemMeta().getDisplayName().equals(discName);
      disc = item;
    }
    if (haveDisc) {
      plugin.getServer().getWorld("world").dropItem(player.getLocation(), disc).setGlowing(true);
      player.getInventory().remove(disc);
    }
  }

}
