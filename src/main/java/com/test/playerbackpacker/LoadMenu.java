package com.test.playerbackpacker;

import com.test.playerbackpacker.util.Color;
import com.test.playerbackpacker.util.LoadItem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoadMenu {
    public static String title;
    public static int size;
    public static ItemStack boarder;

    public static Integer materialSlot;

    public static void loadMenu() {
        if (!PlayerBackpacker.instance.getDataFolder().exists()) {
            PlayerBackpacker.instance.getDataFolder().mkdir();
        }
        File file = new File(PlayerBackpacker.instance.getDataFolder(), "menu.yml");
        if (!file.exists()) {
            PlayerBackpacker.instance.saveResource("menu.yml", true);
        }
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(reader);
            title = "";
            if (config.get("Title") != null) {
                title = Color.stringColorTranslate(config.getString("Title"));
            }
            size = config.getInt("Size");
            boarder = LoadItem.readItemStack(config, "Boarder");
            materialSlot = config.getInt("Material" + ".TranslateSlot");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
        }
    }
}