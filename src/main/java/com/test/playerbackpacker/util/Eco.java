package com.test.playerbackpacker.util;

import com.test.playerbackpacker.PlayerBackpacker;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Eco {
    public static RegisteredServiceProvider economyProvider =   PlayerBackpacker.instance.getServer().getServicesManager().getRegistration((Class) Economy.class);
    public static Economy economy = (Economy) economyProvider.getProvider();
    public static double lookMoney(OfflinePlayer player){

        return economy.getBalance(player);

    }
    public static boolean hasMoney(OfflinePlayer player,double amount){

        if(lookMoney(player)>= amount){
            return true;
        }
        return false;

    }
    public static void takeMoney(OfflinePlayer player,double amount){
        economy.withdrawPlayer(player,amount);

    }
}
