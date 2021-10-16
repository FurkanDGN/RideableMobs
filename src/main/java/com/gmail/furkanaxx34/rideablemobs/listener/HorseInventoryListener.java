package com.gmail.furkanaxx34.rideablemobs.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.HorseInventory;

public class HorseInventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() instanceof HorseInventory) {
            Entity vehicle = event.getWhoClicked().getVehicle();
            if (vehicle instanceof Horse && ((Horse) vehicle).isInvisible())
                event.setCancelled(true);
        }
    }
}
