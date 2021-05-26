package com.motschik.spigotplugin.pvp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import com.motschik.spigotplugin.pvp.executor.GameExecutor;

public class PvpPlugin extends JavaPlugin {

  /**
   * 起動時に実行される処理.
   */
  @Override
  public void onEnable() {

    FileConfiguration config = getConfig();
    GameExecutor gameExecutor = new GameExecutor(this);
    getCommand("pvpstart").setExecutor(gameExecutor);
    getCommand("pvpreset").setExecutor(gameExecutor);
    getCommand("pvpend").setExecutor(gameExecutor);
    getCommand("addteam").setExecutor(gameExecutor);
    getCommand("removeteam").setExecutor(gameExecutor);
    getCommand("removeallteam").setExecutor(gameExecutor);
    getCommand("jointeam").setExecutor(gameExecutor);
  }
}
