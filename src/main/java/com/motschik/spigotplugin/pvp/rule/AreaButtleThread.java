package com.motschik.spigotplugin.pvp.rule;

public class AreaButtleThread extends Thread {

  private final AreaButtle area;

  private boolean enable;

  /**
   * @param area
   */
  public AreaButtleThread(AreaButtle area) {
    this.area = area;
  }

  @Override
  public void run() {
    enable = true;
    while (enable) {
      area.checkAndCount();
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
}
