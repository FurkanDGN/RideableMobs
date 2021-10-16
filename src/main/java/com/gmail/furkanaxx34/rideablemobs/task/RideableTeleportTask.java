package com.gmail.furkanaxx34.rideablemobs.task;

import com.gmail.furkanaxx34.rideablemobs.RideableMobs;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Mob;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RideableTeleportTask {

    @NotNull
    private final Horse horse;
    @NotNull
    private final Mob mob;

    @Nullable
    private BukkitTask task;

    public RideableTeleportTask(@NotNull Horse horse, @NotNull Mob mob) {
        this.horse = horse;
        this.mob = mob;
    }

    public void startTask() {
        mob.setAware(false);
        mob.setAI(false);
        mob.setTarget(null);

        Optional.ofNullable(task).ifPresent(BukkitTask::cancel);

        task = Bukkit.getScheduler().runTaskTimer(RideableMobs.getInstance(), () -> {
            if (!mob.isValid() || horse.getPassenger() == null) {
                stopTask();
                return;
            }

            mob.teleport(horse);
        }, 2, 1);
    }

    public void stopTask() {
        Optional.ofNullable(task).ifPresent(BukkitTask::cancel);
        mob.setAware(true);
        mob.setAI(true);
        horse.getInventory().setSaddle(new ItemStack(Material.AIR));
        horse.remove();
    }
}
