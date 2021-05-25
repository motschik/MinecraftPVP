package com.motschik.spigotplugin.pvp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PvpPlugin extends JavaPlugin {

  /**
   * 起動時に実行される処理.
   */
  @Override
  public void onEnable() {

    FileConfiguration config = getConfig();
  }
}
