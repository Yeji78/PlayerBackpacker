package com.test.playerbackpacker.gui;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

import static com.test.playerbackpacker.LoadMenu.materialSlot;
import static com.test.playerbackpacker.gui.BackpackerMenu.menuViewer;

public class UnlockBackpacker {
    public static HashMap<UUID, Integer[]> unlockMap = new HashMap<>();
    public static void unlockBse(Player player) {
        Integer[] material = new Integer[materialSlot];
        for (int n = 0; n < materialSlot; n++) {
            material[n] = n;
        }
        for (int c : material) {
            menuViewer.get(player.getUniqueId())[0].clear(c);
        }
        player.sendMessage(Arrays.toString(material));

    }


    public static void addUnlock(Player player, int i) {

        Integer[] unlockMaterial = new Integer[materialSlot+i];
        for (int n = 0; n < unlockMap.get(player.getUniqueId()).length+i; n++) {
            unlockMaterial[n] = n;
        }
        unlockMap.put(player.getUniqueId(), unlockMaterial);
        for (int c : unlockMaterial) {
            menuViewer.get(player.getUniqueId())[0].clear(c);
        }


    }
}
