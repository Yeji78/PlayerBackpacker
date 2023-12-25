package com.test.playerbackpacker.util;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class LoadItem {
    public static  String name;
    public static  List<String> lore;
    public static ItemStack ret = null;
    public static ItemStack readItemStack(final YamlConfiguration config, final String path) {
        if (config.isConfigurationSection(path)) {
            final String type = config.getString(path + ".Type");
            final int data = config.getInt(path + ".Data");

            ret = new ItemStack(Material.getMaterial(type.toUpperCase()), 1, (short)data);

            final ItemMeta meta = ret.getItemMeta();

            if(config.get(path+".Name")!=null){
                name = Color.stringColorTranslate(config.getString(path + ".Name"));
                meta.setDisplayName(name);
            }
            if(config.get(path+".Lore")!=null){
                lore = Color.listColorTranslate(config.getStringList(path + ".Lore"));
                meta.setLore(lore);
            }
            if(config.get(path+".Enchant")!=null){
                String enchants = config.getString(path+".Enchant").toUpperCase();
                String[] enchant = enchants.split(":");
                meta.addEnchant(Enchantment.getByName(enchant[0]),Integer.parseInt(enchant[1]),true);

            }
            if(config.get(path+".HideEnchant")!=null){
                boolean hide = config.getBoolean(path+".HideEnchant");

                if(hide){
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
            }
            ret.setItemMeta(meta);

        }
        return ret;
    }
    public static ItemStack getRet(){
        return ret;
    }
}
