package com.test.playerbackpacker.Listener;

import com.test.playerbackpacker.PlayerBackpacker;
import com.test.playerbackpacker.util.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.io.IOException;

import static com.test.playerbackpacker.LoadMenu.title;
import static com.test.playerbackpacker.util.LoadItem.*;

public class OnInventoryClick implements Listener {

    @EventHandler
    public void InventoryClick(InventoryClickEvent event){
        if (event.getCurrentItem() != null) {
            ItemStack itemStack = event.getCurrentItem();
            ItemStack itemStack1 = getRet();
            if (event.getInventory().getTitle().contains(title)) {
                if (itemStack.equals(itemStack1)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}



