package com.test.playerbackpacker.util;

import org.bukkit.entity.Player;

public class Judge {
    public static boolean isFull(Player player){
        if(player.getInventory().firstEmpty() == -1){
            return true;
        }
        return false;




    }
}
