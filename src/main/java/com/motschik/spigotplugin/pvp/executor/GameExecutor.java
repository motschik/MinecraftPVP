package com.motschik.spigotplugin.pvp.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.motschik.spigotplugin.pvp.game.TeamGame;

public class GameExecutor implements CommandExecutor {

  private final JavaPlugin plugin;
  private final TeamGame game;

  public GameExecutor(JavaPlugin plugin, TeamGame game) {
    this.plugin = plugin;
    this.game = game;

    plugin.getCommand("pvpstart").setExecutor(this);
    plugin.getCommand("pvpreset").setExecutor(this);
    plugin.getCommand("pvpend").setExecutor(this);
    plugin.getCommand("addteam").setExecutor(this);
    plugin.getCommand("removeteam").setExecutor(this);
    plugin.getCommand("removeallteam").setExecutor(this);
    plugin.getCommand("jointeam").setExecutor(this);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {

    if ("pvpstart".equals(str)) {

      sender.sendMessage(str);
      game.startGame();

    } else if ("pvpreset".equals(str)) {

      sender.sendMessage(str);
      game.resetScore();

    } else if ("pvpend".equals(str)) {

      sender.sendMessage(str);
      game.endGame();

    } else if ("addteam".equals(str)) {

      sender.sendMessage(str);
      game.addTeam(args[0], args[1]);

    } else if ("removeteam".equals(str)) {

      sender.sendMessage(str);
      game.removeTeam(args[0]);

    } else if ("removeallteam".equals(str)) {

      sender.sendMessage(str);
      game.removeAllTeam();

    } else if ("jointeam".equals(str)) {

      sender.sendMessage(str);
      if (args.length == 1) {
        game.joinTeam(args[0], (Player) sender);
      } else if (args.length == 2) {

        Player playerName = plugin.getServer().getOnlinePlayers().stream()
            .filter(player -> player.getName().equals(args[1])).findFirst().get();

        game.joinTeam(args[0], playerName);
      }
    }



    return false;
  }

}
