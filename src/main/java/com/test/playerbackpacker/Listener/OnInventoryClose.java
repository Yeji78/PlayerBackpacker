package com.test.playerbackpacker.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import static com.test.playerbackpacker.LoadMenu.title;
import static com.test.playerbackpacker.util.LoadItem.*;

public class OnInventoryClose implements Listener {
    public static HashMap<UUID, Inventory> playerItemStack = new HashMap<>();

    @EventHandler
    public static void InventoryClose(InventoryCloseEvent event) {
        if (event.getInventory().getTitle().contains(title)) {
            Inventory inventory = event.getInventory();
            HashMap<Integer, ? extends ItemStack> all = inventory.all(getRet());
            Set<Integer> set = all.keySet();

            for (Integer i : set) {
                ItemStack itemStack = all.get(i);
                if (itemStack != null && itemStack.getType() != Material.AIR) {
                    inventory.setItem(i, itemStack);
                }
            }
            Player player = (Player) event.getPlayer();
            playerItemStack.put(player.getUniqueId(), inventory);
        }
    }
}

