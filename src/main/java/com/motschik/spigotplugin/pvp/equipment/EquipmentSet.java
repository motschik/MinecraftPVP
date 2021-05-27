package com.motschik.spigotplugin.pvp.equipment;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EquipmentSet {

  private final ItemStack chestplate;
  private final ItemStack leggings;
  private final ItemStack boots;
  private final ItemStack offHand;
  private final List<ItemStack> items;

  public EquipmentSet(ItemStack chestplate, ItemStack leggings, ItemStack boots, ItemStack offHand,
      List<ItemStack> items) {
    this.chestplate = chestplate;
    this.leggings = leggings;
    this.boots = boots;
    this.offHand = offHand;
    this.items = items;
  }

  public void equip(Player player) {
    PlayerInventory inv = player.getInventory();
    ItemStack helmet = inv.getHelmet();
    inv.clear();
    inv.setHelmet(helmet);
    inv.setChestplate(chestplate);
    inv.setLeggings(leggings);
    inv.setBoots(boots);
    inv.setItemInOffHand(offHand);
    inv.addItem(items.toArray(new ItemStack[items.size()]));
  }
}
