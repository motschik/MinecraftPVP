package com.motschik.spigotplugin.pvp.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.motschik.spigotplugin.pvp.rule.AreaButtle;

public class AreaExecutor implements CommandExecutor {

  private final JavaPlugin plugin;
  private final AreaButtle rule;

  /**
   * コンストラクタ.
   *
   * @param plugin JavaPlugin
   * @param rule AreaButtle
   */
  public AreaExecutor(JavaPlugin plugin, AreaButtle rule) {
    this.plugin = plugin;
    this.rule = rule;

    plugin.getCommand("area").setExecutor(this);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

    if ("area".equals(str)) {

      if ("setfrom".equals(args[0])) {

        sender.sendMessage("set area from-point");
        rule.setAreaFrom(((Player) sender).getLocation());

      } else if ("setto".equals(args[0])) {

        sender.sendMessage("set area to-point");
        rule.setAreaTo(((Player) sender).getLocation());

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
