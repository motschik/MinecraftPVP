package com.motschik.spigotplugin.pvp.rule;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;
import com.motschik.spigotplugin.pvp.game.Game;

public class Gachihoko extends PvpRule {

  private String discName = "伝説のDVD";
  private Location hokoSpawn;
  private String havingPlayerName = null;

  private Map<String, Integer> tickMap = new HashMap<>();

  private int upperTick = 60;

  private int hokoDropedTime = 0;

  private int hokoRespawnTime = 300;

  private ItemStack hoko;

  GachihokoThread thread = new GachihokoThread(this);

  public Gachihoko(Game game, JavaPlugin plugin) {
    super(game, plugin);
  }

  @Override
  public void startTickCycle() {
    if (thread.isAlive()) {
      thread.setEnable(false);
    }
    thread = new GachihokoThread(this);
    thread.start();

    ItemStack disc = new ItemStack(Material.MUSIC_DISC_CAT);
    ItemMeta discMeta = plugin.getServer().getItemFactory().getItemMeta(Material.MUSIC_DISC_CAT);
    discMeta.setDisplayName(discName);
    disc.setItemMeta(discMeta);
    plugin.getServer().getWorld("world").dropItem(hokoSpawn, disc).setGlowing(true);
  }

  @Override
  public void stopTickCycle() {
    thread.setEnable(false);
    plugin.getServer().getOnlinePlayers().stream().filter(player -> {
      boolean haveDisc = false;
      for (ItemStack item : player.getInventory().all(Material.MUSIC_DISC_CAT).values()) {
        haveDisc |= item.getItemMeta().getDisplayName().equals(discName);
      }
      return haveDisc;
    }).forEach(player -> {
      player.getInventory().remove(Material.MUSIC_DISC_CAT);
    });
  }

  public void setHokoSpawn(Location loc) {
    hokoSpawn = loc;
  }

  public void tickCount() {
    Optional<? extends Player> discPlayer =
        plugin.getServer().getOnlinePlayers().stream().filter(player -> {
          boolean haveDisc = false;
          for (ItemStack item : player.getInventory().all(Material.MUSIC_DISC_CAT).values()) {
            haveDisc |= item.getItemMeta().getDisplayName().equals(discName);
          }
          return haveDisc;
        }).findFirst();
    if (discPlayer.isPresent()) {
      Team team = game.getBoard().getEntryTeam(discPlayer.get().getName());
      tickMap.computeIfAbsent(team.getName(), t -> 0);
      int nextTick = tickMap.get(team.getName()) + 1;
      if (nextTick > upperTick) {
        nextTick = 0;
        game.addScore(team.getName(), 1);
      }
      tickMap.replace(team.getName(), nextTick);

      discPlayer.get().setGlowing(true);
    }
    plugin.getServer().getOnlinePlayers().stream().filter(player -> {
      if (discPlayer.isPresent()) {
        return !player.equals(discPlayer.get());
      }
      return true;
    }).forEach(player -> {
      player.setGlowing(false);
    });
  }

  private boolean isDisc(ItemStack item) {
    return item.getType().equals(Material.MUSIC_DISC_CAT)
        && item.getItemMeta().getDisplayName().equals(discName);
  }

  public String getHavingPlayerName() {
    return havingPlayerName;
  }

  public void setHavingPlayerName(String havingPlayerName) {
    this.havingPlayerName = havingPlayerName;
  }

  public int getUpperTick() {
    return upperTick;
  }

  public void setUpperTick(int upperTick) {
    this.upperTick = upperTick;
  }

  public int getHokoDropedTime() {
    return hokoDropedTime;
  }

  public void setHokoDropedTime(int hokoDropedTime) {
    this.hokoDropedTime = hokoDropedTime;
  }

  public int getHokoRespawnTime() {
    return hokoRespawnTime;
  }

  public void setHokoRespawnTime(int hokoRespawnTime) {
    this.hokoRespawnTime = hokoRespawnTime;
  }

  public ItemStack getHoko() {
    return hoko;
  }

  public void setHoko(ItemStack hoko) {
    this.hoko = hoko;
  }

  public Location getHokoSpawn() {
    return hokoSpawn;
  }

  public String getDiscName() {
    return discName;
  }
}
