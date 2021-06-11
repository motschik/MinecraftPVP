package com.motschik.spigotplugin.pvp.rule;

public class GachihokoThread extends Thread {

  private final Gachihoko hoko;

  private boolean enable = false;

  public GachihokoThread(Gachihoko hoko) {
    this.hoko = hoko;
  }

  @Override
  public void run() {
    enable = true;
    while (enable) {
      hoko.tickCount();
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        break;
      }
      if (isInterrupted()) {
        break;
      }
    }
  }

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
  }

  public boolean getEnable() {
    return enable;
  }
}
