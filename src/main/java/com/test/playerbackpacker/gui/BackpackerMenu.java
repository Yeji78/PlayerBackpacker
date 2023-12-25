package com.test.playerbackpacker.gui;

import com.test.playerbackpacker.LoadMenu;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import static com.test.playerbackpacker.Listener.OnInventoryClose.playerItemStack;
import static com.test.playerbackpacker.LoadMenu.materialSlot;
import static com.test.playerbackpacker.gui.UnlockBackpacker.unlockMap;


public class BackpackerMenu {
    public static HashMap<UUID, Inventory[]> menuViewer = new HashMap<>();


    public static void open(Player player, int i) {

        //第一次打开时，设置初始背包
        Inventory[] inventories = new Inventory[9];
        if (!menuViewer.containsKey(player.getUniqueId())) {
            for (int a = 0; a < 9; a++) {
                BackpackHolder backpackHolder = new BackpackHolder();
                backpackHolder.setPage(1+a);
                Inventory inventory = Bukkit.createInventory(backpackHolder, LoadMenu.size, LoadMenu.title + "§6第" + (a + 1) + "页");
                Integer[] invSlot = new Integer[54];
                for (int n = 0; n < 54; n++) {
                    invSlot[n] = n;
                }
                for (int c : invSlot) {
                    inventory.setItem(c, LoadMenu.boarder);
                }
                inventories[a] = inventory;
            }
            menuViewer.put(player.getUniqueId(), inventories);
        }
        //如果玩家不存在解锁物品栏数量，存入长度为0的int数组
        if (!unlockMap.containsKey(player.getUniqueId())) {
            Integer[] integers = new Integer[0];
            unlockMap.put(player.getUniqueId(), integers);
        }
        //如果玩家解锁物品数量大于原始数量，则解锁玩家拥有数量的格子，反之解锁原始数量
        if (unlockMap.get(player.getUniqueId()).length > materialSlot) {
            UnlockBackpacker.addUnlock(player, i);
        } else {UnlockBackpacker.unlockBse(player);}



        //如果玩家临时容器数组存在，则更新玩家容器
        if (playerItemStack.containsKey(player.getUniqueId())) {
            //获取玩家现在的容器数组
            Inventory[] inventories1 = menuViewer.get(player.getUniqueId());
            BackpackHolder backpackHolder = (BackpackHolder) playerItemStack.get(player.getUniqueId()).getHolder();
            int page = backpackHolder.getPage();
            //问题所在
            inventories1[page-1] = playerItemStack.get(player.getUniqueId());
            Arrays.fill(inventories1,page-1,page-1,playerItemStack.get(player.getUniqueId()));
            player.sendMessage("§b替换的页数为" + (page));
            menuViewer.put(player.getUniqueId(), inventories1);
            playerItemStack.remove(player.getUniqueId());
        }

        //打开玩家指定容器
        player.openInventory(menuViewer.get(player.getUniqueId())[i - 1]);

        player.sendMessage("playerItemStack§5" + playerItemStack.containsKey(player.getUniqueId()));
        player.sendMessage("§b打开的背包为" + (i - 1));

    }
}
