package com.test.playerbackpacker;

import com.test.playerbackpacker.Listener.OnInventoryClick;
import com.test.playerbackpacker.Listener.OnInventoryClose;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static com.test.playerbackpacker.LoadMenu.loadMenu;

public final class PlayerBackpacker extends JavaPlugin {
    public static PlayerBackpacker instance;
    public static boolean eco;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        this.saveDefaultConfig();
        loadMenu();
        Bukkit.getPluginCommand("PlayerBackpacker").setExecutor(new Command());
        Bukkit.getPluginManager().registerEvents(new OnInventoryClose(),this);
        Bukkit.getPluginManager().registerEvents(new OnInventoryClick(),this);
        if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
            Bukkit.getLogger().info("§7[§e" + "CSGOLottery" + "§7]: §e检测到经济插件");
            eco = true;
        } else {
            Bukkit.getLogger().info("§7[§e" + "CSGOLottery" + "§7]: §4未检测到经济插件");
            Bukkit.getLogger().info("§7[§e" + "CSGOLottery" + "§7]: §4已禁用经济功能");
            eco = false;
        }
        getLogger().info("插件已安装");
    }
    @Override
    public void onDisable() {
        getLogger().info("插件已卸载");
        // Plugin shutdown logic
    }
    public static PlayerBackpacker getPlugin(){
        return instance;


    }
}
