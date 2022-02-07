package me.mito.Methods;

import me.mito.APIs.ItemBuilder;;
import me.mito.BoxMito;
import me.mito.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.stream.Collectors;

public class MythMethods {

    ConfigManager Config = BoxMito.Config;

    FileConfiguration config = BoxMito.Instance.getConfig();

    public void setMito(Player p) {
        if (p != null) {
            config.set("Mito", p.getName());
            BoxMito.Instance.saveConfig();
        }

        if (Config.isCommand) {
            for (String commands : Config.CommandsList) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("@mito", config.getString("Mito")));
            }
        }
    }

    public void MitoGUI(Player p) {

        Inventory inv = Bukkit.createInventory(null, 9*3, Config.GuiName);

        ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

        String Mito = config.getString("Mito");

        inv.setItem(13, new ItemBuilder(playerHead).setSkullOwner(Mito).setName(Config.GuiItemNome).setLore(Config.GuiLore.stream().map(l -> l.replace("{mito}", Mito)).collect(Collectors.toList())).build());

        p.openInventory(inv);

    }

}
