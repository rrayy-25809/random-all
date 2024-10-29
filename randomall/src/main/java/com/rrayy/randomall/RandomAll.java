package com.rrayy.randomall;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.joml.Random;

public class RandomAll extends JavaPlugin implements Listener {
    public Random random = new Random();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("RandomAll plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RandomAll plugin has been disabled.");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        event.setCancelled(random.nextInt(5)==0);
    }
}