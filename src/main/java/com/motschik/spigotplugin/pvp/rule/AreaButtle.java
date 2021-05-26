package com.motschik.spigotplugin.pvp.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;
import com.motschik.spigotplugin.pvp.game.Game;

public class AreaButtle extends PvpRule {

  private Location location;
  private Location location2;

  private Location tmpLocation;

  private Map<String, Integer> tickMap = new HashMap<>();

  private int upperTick = 60;

  AreaButtleThread thread = new AreaButtleThread(this);

  public AreaButtle(Game game, JavaPlugin plugin) {
    super(game, plugin);
  }

  @Override
  public synchronized void startTickCycle() {
    if (thread.isAlive()) {
      thread.setEnable(false);
    }
    thread = new AreaButtleThread(this);
    thread.start();
  }

  @Override
  public void stopTickCycle() {
    thread.setEnable(false);
  }

  public void setAreaFrom(Location loc) {
    tmpLocation = loc;
  }

  public void setAreaTo(Location loc) {
    defineArea(tmpLocation, loc);
  }

  private void defineArea(Location loc1, Location loc2) {
    location = loc1;
    location2 = loc2;
  }

  public synchronized void checkAndCount() {

    plugin.getLogger().info("checktick");

    if (location == null || location2 == null) {
      return;
    }

    Set<Team> teamSet = new HashSet<>();

    plugin.getServer().getOnlinePlayers().stream()
        .filter(player -> player.getGameMode().equals(GameMode.SURVIVAL))//
        .forEach(player -> {
          if (playerInArea(player)) {
            Team team = game.getBoard().getEntryTeam(player.getName());
            if (team != null) {
              teamSet.add(team);
            }
          }
        });
    if (teamSet.size() == 1) {
      Team team = teamSet.stream().findFirst().get();

      tickMap.computeIfAbsent(team.getName(), t -> 0);
      int nextTick = tickMap.get(team.getName()) + 1;
      if (nextTick > upperTick) {
        nextTick = 0;
        game.addScore(team.getName(), 1);
      }
      tickMap.replace(team.getName(), nextTick);

    }
  }

  private boolean playerInArea(Player player) {

    Location ploc = player.getLocation();
    double px = ploc.getX();
    double py = ploc.getY();
    double pz = ploc.getZ();

    double x1 = location.getX();
    double y1 = location.getY();
    double z1 = location.getZ();

    double x2 = location2.getX();
    double y2 = location2.getY();
    double z2 = location2.getZ();


    if (!(x1 <= px && px <= x2) && !(x2 <= px && px <= x1)) {
      return false;
    }
    if (!(y1 <= py && py <= y2) && !(y2 <= py && py <= y1)) {
      return false;
    }
    if (!(z1 <= pz && pz <= z2) && !(z2 <= pz && pz <= z1)) {
      return false;
    }

    return true;
  }


  public int getUpperTick() {
    return upperTick;
  }

  public void setUpperTick(int upperTick) {
    this.upperTick = upperTick;
  }
}
