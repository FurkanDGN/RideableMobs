package com.gmail.furkanaxx34.rideablemobs;

import com.gmail.furkanaxx34.rideablemobs.task.RideTeleportTask;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class RideableMobsAPI {

    RideableMobsAPI() {
    }

    public void rideMob(Player player, Entity entity) {
        if (!(entity instanceof Mob)) {
            return;
        }
        String name = entity.getType().name().toLowerCase(Locale.ROOT);
        String permission = "allow.mob." + name;

        if (!player.hasPermission(permission))
            return;

        Mob mob = (Mob) entity;

        Horse horse = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
        prepareHorse(horse, player);
        new RideTeleportTask(horse, mob).startTask();
    }

    private void prepareHorse(Horse horse, Player player) {
        horse.setTamed(true);
        horse.setOwner(player);
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        horse.setPassenger(player);
        horse.setSilent(true);
        horse.setInvisible(true);
    }
}
