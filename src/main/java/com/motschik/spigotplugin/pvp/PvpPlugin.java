package com.motschik.spigotplugin.pvp;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import com.motschik.spigotplugin.pvp.equipment.Equipment;
import com.motschik.spigotplugin.pvp.equipment.EquipmentExecutor;
import com.motschik.spigotplugin.pvp.executor.AreaExecutor;
import com.motschik.spigotplugin.pvp.executor.GameExecutor;
import com.motschik.spigotplugin.pvp.game.TeamGame;
import com.motschik.spigotplugin.pvp.rule.AreaButtle;
import com.motschik.spigotplugin.pvp.rule.GachihokoListener;
import com.motschik.spigotplugin.pvp.sign.SignClickListener;

public class PvpPlugin extends JavaPlugin {

  private TeamGame game;
  private AreaButtle area;
  private Equipment equipment;

  public TeamGame getGame() {
    return game;
  }

  public void setGame(TeamGame game) {
    this.game = game;
  }

  public AreaButtle getArea() {
    return area;
  }

  public void setArea(AreaButtle area) {
    this.area = area;
  }

  public Equipment getEquipment() {
    return equipment;
  }

  public void setEquipment(Equipment equipment) {
    this.equipment = equipment;
  }

  /**
   * 起動時に実行される処理.
   */
  @Override
  public void onEnable() {

    equipment = new Equipment(this);
    game = new TeamGame(this);
    area = new AreaButtle(game, this);
    game.addRule(area);


    FileConfiguration config = getConfig();

    GameExecutor gameExecutor = new GameExecutor(this, game);
    AreaExecutor areaExecutor = new AreaExecutor(this, area);
    EquipmentExecutor equipExecutor = new EquipmentExecutor(this, equipment);

    getServer().getPluginManager().registerEvents(new SignClickListener(this), this);
    getServer().getPluginManager().registerEvents(new GachihokoListener(this), this);
  }
}
