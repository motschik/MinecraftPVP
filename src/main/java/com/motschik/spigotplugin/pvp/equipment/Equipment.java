package com.motschik.spigotplugin.pvp.equipment;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Team;
import com.motschik.spigotplugin.pvp.PvpPlugin;
import com.motschik.spigotplugin.pvp.color.TeamColor;

public class Equipment {

  private final PvpPlugin plugin;

  public final EquipmentSet GUNNER;

  public Equipment(PvpPlugin plugin) {
    this.plugin = plugin;

    GUNNER = gunner();
  }

  public void equipTeamHelmet(Player player) {

    ItemStack item = new ItemStack(Material.LEATHER_HELMET);

    LeatherArmorMeta meta =
        (LeatherArmorMeta) plugin.getServer().getItemFactory().getItemMeta(Material.LEATHER_HELMET);
    Team team = plugin.getGame().getBoard().getEntryTeam(player.getName());
    meta.setColor(TeamColor.toColor(team.getColor()));
    meta.setUnbreakable(true);

    item.setItemMeta(meta);

    PlayerInventory inventory = player.getInventory();
    inventory.setHelmet(item);

  }

  public void equipGunner(Player player, String equipType) {
    PlayerInventory inventory = player.getInventory();
    if (!inventory.getHelmet().getItemMeta().isUnbreakable()) {
      return;
    }

    PlayerInventory inv = player.getInventory();
    ItemStack helmet = inv.getHelmet();
    inv.clear();
    inv.setHelmet(helmet);


    if (equipType.equals("gunner")) {

      // クロスボウ:高速装填レベル5
      Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(), "give " + player.getName()//
          + " minecraft:crossbow{"//
          + "Enchantments:[{id:quick_charge,lvl:5}]}");
      // 攻撃ポーション
      Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(), "give " + player.getName()//
          + " minecraft:splash_potion{display:{"//
          + "Name:\"\\\"Splash Potion\\\"\"},"//
          + "CustomPotionColor:16711680,"//
          + "CustomPotionEffects:[{Id:7,Amplifier:2,Duration:200}]} 1");
      // 回復ポーション
      Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(), "give " + player.getName()//
          + " minecraft:potion{display:{" + "Name:\"\\\"Heal Potion\\\"\"},"//
          + "CustomPotionColor:65322,"//
          + "CustomPotionEffects:[{Id:6,Amplifier:2,Duration:200}]} 3");
      // 矢
      Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
          "give " + player.getName() + " minecraft:arrow 128");

      player.setFoodLevel(20);

    } else if (equipType.equals("knight")) {

      // 盾
      ItemStack shield = new ItemStack(Material.SHIELD);
      ItemMeta shieldMeta = plugin.getServer().getItemFactory().getItemMeta(Material.SHIELD);
      shieldMeta.setUnbreakable(true);

      inv.setItemInOffHand(shield);

      // 鉄チェストプレート
      ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
      inv.setChestplate(chest);
      // 鉄レギンス
      ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
      inv.setLeggings(leggings);
      // 鉄ブーツ
      ItemStack boots = new ItemStack(Material.IRON_BOOTS);
      inv.setBoots(boots);

      // ソード：ダメージ増加レベル3、ノックバックレベル1
      Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(), "give " + player.getName()//
          + " minecraft:iron_sword{display:{"//
          + "Name:\"\\\"ソード\\\"\"}," + "Unbreakable:1,Enchantments:[{id:sharpness,lvl:3},"//
          + "{id:knockback,lvl:1}]}");
      // 毒ポーション
      Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(), "give " + player.getName()//
          + " minecraft:lingering_potion{display:{"//
          + "Name:\"\\\"Poison Potion\\\"\"},"//
          + "CustomPotionEffects:[{Id:19,Amplifier:0,Duration:200}]} 1");
      // 回復ポーション
      Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(), "give " + player.getName()//
          + " minecraft:potion{display:{"//
          + "Name:\"\\\"Heal Potion\\\"\"},"//
          + "CustomPotionColor:65322,"//
          + "CustomPotionEffects:[{Id:6,Amplifier:2,Duration:200}]} 3");

      player.setFoodLevel(6);

    } else if (equipType.equals("sasano")) {

      ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
      inv.setChestplate(chest);

      // 弓：射撃ダメージ増加5、耐久無限
      Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(), "give " + player.getName()//
          + " minecraft:bow{"//
          + "Unbreakable:1,"//
          + "Enchantments:[{id:power,lvl:7}]}");
      // 衰退ポーション
      Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(), "give " + player.getName()//
          + " minecraft:lingering_potion{display:{"//
          + "Name:\"\\\"Super Poison Potion\\\"\"},"//
          + "CustomPotionEffects:[{Id:20,Amplifier:0,Duration:200}]} 1");
      // 回復ポーション
      Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(), "give " + player.getName()//
          + " minecraft:potion{display:{Name:\"\\\"Heal Potion\\\"\"},"//
          + "CustomPotionColor:65322,"//
          + "CustomPotionEffects:[{Id:6,Amplifier:2,Duration:200}]} 3");
      // 矢
      Bukkit.dispatchCommand(plugin.getServer().getConsoleSender(),
          "give " + player.getName() + " minecraft:arrow 128");

      player.setFoodLevel(20);

    }
  }

  public void equip(Player player, EquipmentSet set) {
    PlayerInventory inventory = player.getInventory();
    if (inventory.getHelmet().getItemMeta().isUnbreakable()) {
      set.equip(player);
    }
  }

  private EquipmentSet gunner() {
    List<ItemStack> items = new ArrayList<ItemStack>();

    ItemFactory factory = plugin.getServer().getItemFactory();

    ItemStack crossbow = new ItemStack(Material.CROSSBOW);
    crossbow.addEnchantment(Enchantment.QUICK_CHARGE, 3);
    items.add(crossbow);

    ItemStack splashPotion = new ItemStack(Material.SPLASH_POTION);
    PotionMeta splashPotionMeta = (PotionMeta) factory.getItemMeta(Material.SPLASH_POTION);
    splashPotionMeta.setColor(Color.RED);
    splashPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HARM, 2, 200), true);
    splashPotion.setItemMeta(splashPotionMeta);
    items.add(splashPotion);

    ItemStack splashHealPotion = new ItemStack(Material.SPLASH_POTION);
    PotionMeta splashHealPotionMeta = (PotionMeta) factory.getItemMeta(Material.SPLASH_POTION);
    splashHealPotionMeta.setColor(Color.BLUE);
    splashHealPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 2, 200), true);
    splashHealPotion.setItemMeta(splashHealPotionMeta);
    items.add(splashHealPotion);

    for (int i = 0; i < 3; i++) {
      ItemStack healPotion = new ItemStack(Material.LINGERING_POTION);
      PotionMeta healPotionMeta = (PotionMeta) factory.getItemMeta(Material.LINGERING_POTION);
      healPotionMeta.setColor(Color.LIME);
      healPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 2, 200), true);
      healPotion.setItemMeta(healPotionMeta);
      items.add(healPotion);
    }

    for (int i = 0; i < 2; i++) {
      ItemStack bow = new ItemStack(Material.BOW, 64);
      items.add(bow);
    }

    return new EquipmentSet(null, null, null, null, items);
  }
}
