package com.test.playerbackpacker.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class BackpackHolder implements InventoryHolder {
    int page;
    @Override
    public Inventory getInventory() {
        return null;
    }
    public int getPage(){
        return page;
    }
    public void setPage(int page){
        this.page = page;
    }
}
