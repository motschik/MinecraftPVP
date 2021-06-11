package com.motschik.spigotplugin.pvp.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.motschik.spigotplugin.pvp.rule.Gachihoko;

public class HokoExecutor implements CommandExecutor {

  private final JavaPlugin plugin;
  private final Gachihoko rule;



  public HokoExecutor(JavaPlugin plugin, Gachihoko rule) {
    this.plugin = plugin;
    this.rule = rule;

    plugin.getCommand("disc").setExecutor(this);
  }



  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
    if ("disc".equals(str)) {

      if ("setspawn".equals(args[0])) {

        sender.sendMessage("set disc spawn");
        rule.setHokoSpawn(((Player) sender).getLocation());

      } else if ("uppertick".equals(args[0])) {

        sender.sendMessage("set uppertick");
        try {
          rule.setUpperTick(Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
          sender.sendMessage("number format error");
        }

      }
    }
    return false;
  }

}
