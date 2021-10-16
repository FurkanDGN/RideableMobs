package com.gmail.furkanaxx34.rideablemobs.listener;

import com.gmail.furkanaxx34.rideablemobs.RideableMobs;
import com.gmail.furkanaxx34.rideablemobs.util.Versions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class MobClickListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (Versions.MINOR >= 10) {
            if (event.getHand() == EquipmentSlot.OFF_HAND) {
                return; // Don't fire twice.
            }
        }
        event.setCancelled(true);

        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        RideableMobs.getInstance().getAPI().rideMob(player, entity);
    }
}
