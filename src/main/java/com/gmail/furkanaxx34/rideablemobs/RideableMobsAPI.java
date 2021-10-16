package com.gmail.furkanaxx34.rideablemobs;

import com.gmail.furkanaxx34.rideablemobs.task.RideableTeleportTask;
import com.gmail.furkanaxx34.rideablemobs.util.ReflectionUtils;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class RideableMobsAPI {

    RideableMobsAPI() {
    }

    public void rideMob(Player player, Entity entity) {
        if (entity instanceof Mob) {
            String name = entity.getType().name().toLowerCase(Locale.ROOT).replace('_', ' ');
            String permission = "allow.mob." + name;
            Mob mob = (Mob) entity;

            Horse horse = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
            prepareHorse(horse, player);
            changeMountedEntity(player, entity);
            new RideableTeleportTask(horse, mob).startTask();
        }
    }

    private void prepareHorse(Horse horse, Player player) {
        horse.setTamed(true);
        horse.setOwner(player);
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        horse.setPassenger(player);
        horse.setSilent(true);
        horse.setInvisible(true);
    }

    public void changeMountedEntity(Player player, Entity mob) {
        try {
            Class<?> packetPlayOutMount = ReflectionUtils.getNMSClass("PacketPlayOutMount");
            Class<?> entity = ReflectionUtils.getNMSClass("Entity");
            Object packet = packetPlayOutMount.getConstructor(entity).newInstance(mob.getClass().getMethod("getHandle").invoke(mob));
            ReflectionUtils.sendPacket(player, packet);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | ClassCastException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
