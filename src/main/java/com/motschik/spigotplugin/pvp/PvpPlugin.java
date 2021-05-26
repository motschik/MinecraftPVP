package com.motschik.spigotplugin.pvp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import com.motschik.spigotplugin.pvp.executor.AreaExecutor;
import com.motschik.spigotplugin.pvp.executor.GameExecutor;
import com.motschik.spigotplugin.pvp.game.TeamGame;
import com.motschik.spigotplugin.pvp.rule.AreaButtle;

public class PvpPlugin extends JavaPlugin {

  private TeamGame game;
  private AreaButtle area;

  /**
   * 起動時に実行される処理.
   */
  @Override
  public void onEnable() {

    game = new TeamGame(this);
    area = new AreaButtle(game, this);
    game.addRule(area);


    FileConfiguration config = getConfig();

    GameExecutor gameExecutor = new GameExecutor(this, game);
    AreaExecutor areaExecutor = new AreaExecutor(this, area);
  }
}
