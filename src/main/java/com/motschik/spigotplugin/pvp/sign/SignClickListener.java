package com.motschik.spigotplugin.pvp.sign;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import com.motschik.spigotplugin.pvp.PvpPlugin;

public class SignClickListener implements Listener {

  private final PvpPlugin plugin;

  public SignClickListener(PvpPlugin plugin) {
    this.plugin = plugin;
  }


  @EventHandler
  public void onSignClick(PlayerInteractEvent event) {
    if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
      return;
    }
    Block clickedBlock = event.getClickedBlock();
    Material material = clickedBlock.getType();
    if (material == Material.OAK_SIGN || material == Material.SPRUCE_SIGN
        || material == Material.BIRCH_SIGN || material == Material.JUNGLE_SIGN
        || material == Material.ACACIA_SIGN || material == Material.DARK_OAK_SIGN
        || material == Material.CRIMSON_SIGN || material == Material.WARPED_SIGN
        || material == Material.OAK_WALL_SIGN || material == Material.SPRUCE_WALL_SIGN
        || material == Material.BIRCH_WALL_SIGN || material == Material.JUNGLE_WALL_SIGN
        || material == Material.ACACIA_WALL_SIGN || material == Material.DARK_OAK_WALL_SIGN
        || material == Material.CRIMSON_WALL_SIGN || material == Material.WARPED_WALL_SIGN) {
      Sign sign = (Sign) clickedBlock.getState();

      String[] lines = sign.getLines();// 全行取得
      if ("[pvp]".equals(lines[0])) {
        if ("gunner".equalsIgnoreCase(lines[1])) {
          plugin.getEquipment().equipGunner(event.getPlayer());
        }
      }
    }
  }

}
