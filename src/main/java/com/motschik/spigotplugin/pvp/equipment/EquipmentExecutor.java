package com.motschik.spigotplugin.pvp.equipment;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class EquipmentExecutor implements CommandExecutor {

  private final JavaPlugin plugin;
  private final Equipment equipment;



  public EquipmentExecutor(JavaPlugin plugin, Equipment equipment) {
    this.plugin = plugin;
    this.equipment = equipment;

    plugin.getCommand("equip").setExecutor(this);
  }



  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

    if ("equip".equals(str)) {
      if ("teamhelmet".equals(args[0])) {
        equipment.equipTeamHelmet((Player) sender);
      } else {
        equipment.equipGunner((Player) sender, args[0]);
      }
    }

    return false;
  }



}
