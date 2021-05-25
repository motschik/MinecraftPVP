package com.motschik.spigotplugin.pvp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class TestServerExecuter {


  /**
   * サーバーテスト.
   *
   * @param args String[]
   *
   */
  public static void main(String[] args) {

    // ファイルの移動
    try {
      Files.copy(Paths.get("../target/MinecraftPVP-0.1.0-SNAPSHOT.jar"),
          Paths.get("plugins/MinecraftPVP.jar"), StandardCopyOption.REPLACE_EXISTING);

      ProcessBuilder p = new ProcessBuilder("java", "-jar", "spigot-1.16.5.jar");
      p.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
