package com.test.playerbackpacker.util;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NonNullList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NBTutil {
    public static ItemStack setNBTString(ItemStack im, String tag, String value){
        String[] versions = Bukkit.getVersion().split("\\.");

        String major = versions[0];
        String minor = versions[1];
        String revision = "-1";
        String NMSBaseHead = "net.minecraft.server.v" + major + "_" + minor + "_R";
        for (int i = 1; i <= 9; i++) {
            String versionTest = NMSBaseHead + i;
            try {
                Class.forName(versionTest + ".ItemStack");
                revision = i + "";
                break;
            } catch (ClassNotFoundException ignored) {
            }
        }
        if (!revision.equals("-1")) {
            String NMSPackage = NMSBaseHead + revision;
            String CraftBukkitPackage = "org.bukkit.craftbukkit.v" + major + "_" + minor + "_R" + revision;
            // 以上都是准备工作，下面正式开始
            try {
                Class<?> craftItemStack = Class.forName(CraftBukkitPackage + ".inventory.CraftItemStack");
                im = new ItemStack(Material.BARRIER);
                Method asNMSCopy = craftItemStack.getMethod("asNMSCopy", ItemStack.class);
                Object NMSItem = asNMSCopy.invoke(null, im);

                // 相当于 CraftItemStack.asNMSCopy(im);

                Class<?> itemStack = Class.forName(NMSPackage + ".ItemStack");
                Class<?> nbtTagCompound = Class.forName(NMSPackage + ".NBTTagCompound");

                Method getTag = itemStack.getMethod("getTag");
                Object nbt = getTag.invoke(NMSItem);
                // 相当于 NBTTagCompound nbt = imNMS.getTag();

                if (nbt == null) {
                    Constructor<?> createNewTag = nbtTagCompound.getConstructor();
                    nbt = createNewTag.newInstance();
                    // 相当于 nbt = new NBTTagCompound();
                }

                Class<?> nbtTagString = Class.forName(NMSPackage + ".NBTTagString");
                Class<?> nbtBase = Class.forName(NMSPackage + ".NBTBase");
                Method setNBTString = nbtTagCompound.getMethod("set", String.class, nbtBase);
                Method createNBTTagString = nbtTagString.getMethod("create", String.class);
                Object stringValue = createNBTTagString.invoke(null, "This is a string.");
                setNBTString.invoke(nbt, "someValue", stringValue);
                // 相当于 nbt.set("someValue", NBTTagString.create("This is a string."));
                Method setTag = itemStack.getMethod("setTag", nbtTagCompound);
                setTag.invoke(NMSItem, nbt);

                // 相当于 imNMS.setTag(nbt);

                Method asBukkitCopy = craftItemStack.getMethod("asBukkitCopy", itemStack);
                im = (ItemStack) asBukkitCopy.invoke(null, NMSItem);

                // 相当于 im = CraftItemStack.asBukkitCopy(imNMS);
                // 主要处理到此结束，以下为异常捕获
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
                // 出错我们也没办法，只能输出了
            }
        }
        return im;
    }
}
