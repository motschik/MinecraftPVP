package com.motschik.spigotplugin.pvp.color;

import org.bukkit.ChatColor;
import org.bukkit.Color;

public enum TeamColor {

  AQUA(Color.AQUA, ChatColor.AQUA), //
  BLUE(Color.BLUE, ChatColor.BLUE), //
  GREEN(Color.GREEN, ChatColor.GREEN), //
  YELLOW(Color.YELLOW, ChatColor.YELLOW), //
  RED(Color.RED, ChatColor.RED), //
  PURPLE(Color.PURPLE, ChatColor.LIGHT_PURPLE);

  private Color color;
  private ChatColor chatColor;

  TeamColor(Color color, ChatColor chatColor) {
    this.color = color;
    this.chatColor = chatColor;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public ChatColor getChatColor() {
    return chatColor;
  }

  public void setChatColor(ChatColor chatColor) {
    this.chatColor = chatColor;
  }

  public static Color toColor(ChatColor chatColor) {
    switch (chatColor) {
      case AQUA:
        return Color.AQUA;
      case BLUE:
        return Color.BLUE;
      case GREEN:
        return Color.GREEN;
      case YELLOW:
        return Color.YELLOW;
      case RED:
        return Color.RED;
      case LIGHT_PURPLE:
        return Color.PURPLE;
      default:
        return null;
    }
  }
}
