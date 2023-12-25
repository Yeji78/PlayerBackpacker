package com.test.playerbackpacker;

import com.test.playerbackpacker.gui.BackpackerMenu;
import com.test.playerbackpacker.gui.UnlockBackpacker;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.test.playerbackpacker.Listener.OnInventoryClose.playerItemStack;
import static com.test.playerbackpacker.LoadMenu.materialSlot;

public class Command implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                //发送消息
                player.sendMessage("PlayerBackpacker插件帮助");
                player.sendMessage("/pbp sp open [页数] [playername] - 打开仓库界面");
                return true;
            }
            if (args.length == 2 && args[0].equalsIgnoreCase("pp")) {
                if (args[1].equalsIgnoreCase("open")) {
                    if (player.hasPermission("PlayerBackpacker.sp.open")) {
                        player.openInventory(playerItemStack.get(player.getUniqueId()));
                    } else {
                        player.sendMessage("[PlayerBackpacker]你没有权限执行该指令");
                    }
                    return true;
                }
            }
            if (args.length == 3 && args[0].equalsIgnoreCase("sp")) {
                switch (args[1].toLowerCase()) {
                    case "open": {
                        if (player.hasPermission("PlayerBackpacker.sp.open")) {
                            BackpackerMenu.open(player, Integer.parseInt(args[2]));
                        } else {
                            player.sendMessage("[PlayerBackpacker]你没有权限执行该指令");
                        }
                        return true;
                    }
                    case "unlock": {
                        if (player.isOp()) {
                            UnlockBackpacker.addUnlock(player,Integer.parseInt(args[2]));
                        } else {
                            player.sendMessage("[PlayerBackpacker]你没有权限执行该指令");
                        }
                        return true;
                    }
                }
            }
            //测试


        } else if (sender instanceof ConsoleCommandSender) {
            if (args.length == 1 && args[0].equalsIgnoreCase("help")) {
                PlayerBackpacker.getPlugin().getLogger().info("PlayerBackpacker插件帮助");
                PlayerBackpacker.getPlugin().getLogger().info("/pbp reload - 重载插件");
                return true;
            }
            if (args.length == 4 && args[0].equalsIgnoreCase("open")) {
                PlayerBackpacker.getPlugin().getLogger().info("该指令无法在后台执行");
            }

        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {

        List<String> result = null;
        if (sender instanceof Player) {
            Player player = (Player) sender;
            result = new ArrayList<>();
            //当参数长度是1时
            switch (args.length) {
                case 1: {
                    result.add("sp");
                    result.add("pp");
                    break;
                }
                case 2: {
                    if (args[0].equalsIgnoreCase("sp")) {
                        result.add("open");
                        result.add("unlock");
                    }
                    if (args[0].equalsIgnoreCase("pp")) {
                        result.add("open");
                    }
                }
            }

        }
        return result;
    }
}