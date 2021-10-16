package com.gmail.furkanaxx34.rideablemobs;

import com.gmail.furkanaxx34.rideablemobs.listener.HorseInventoryListener;
import com.gmail.furkanaxx34.rideablemobs.listener.MobClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class RideableMobs extends JavaPlugin {

    @Nullable
    private static RideableMobs instance;

    @Nullable
    private RideableMobsAPI api;

    @NotNull
    public static RideableMobs getInstance() {
        Objects.requireNonNull(RideableMobs.instance, "You cant use API before enable plugin.");
        return RideableMobs.instance;
    }

    @Override
    public void onEnable() {
        RideableMobs.instance = this;
        this.api = new RideableMobsAPI();
        Bukkit.getPluginManager().registerEvents(new MobClickListener(), this);
        Bukkit.getPluginManager().registerEvents(new HorseInventoryListener(), this);
        getLogger().info("Plugin successfully enabled!");
    }

    @Override
    public void onDisable() {
        RideableMobs.instance = null;
        this.api = null;
        getLogger().info("Plugin successfully disabled!");
    }

    @NotNull
    public RideableMobsAPI getAPI() {
        Objects.requireNonNull(this.api, "You cant use API before enable plugin.");
        return this.api;
    }
}
