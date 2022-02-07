package me.mito.Listeners;

import me.mito.BoxMito;
import me.mito.ConfigManager;
import me.mito.Methods.MythMethods;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    ConfigManager Config = BoxMito.Config;
    String Prefix = Config.prefix;
    FileConfiguration configMyth = BoxMito.Instance.getConfig();

    @EventHandler
    public void JoinMsg(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        String myth = configMyth.getString("Mito");

        if (myth.equals(p.getName())) {
            if (Config.JoinMsg) {
                for (String anuncio : Config.MsgJoin)
                    Bukkit.broadcastMessage(anuncio.replace("{mito}", myth).replace("@prefix", Prefix));
            }
        }
    }

    @EventHandler
    public void QuitMsg(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        String myth = configMyth.getString("Mito");

        if (myth.equals(p.getName())) {
            if (Config.LeaveMsg) {
                for (String anuncio : Config.MsgLeave)
                    Bukkit.broadcastMessage(anuncio.replace("{mito}", myth).replace("@prefix", Prefix));
            }
        }
    }

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent e) {

        Player p = e.getEntity();

        if (!(e.getEntity().getKiller() instanceof Player)) return;

        String myth = configMyth.getString("Mito");

        if (myth.equals(p.getName())) {
            new MythMethods().setMito(p.getKiller());
            if (Config.NewMitoMsg) {
                for (String anuncio : Config.MsgNewMito)
                    Bukkit.broadcastMessage(anuncio.replace("{mito}", myth).replace("{novomito}", p.getKiller().getName()).replace("@prefix", Prefix));
            }
            if (Config.Lighting) {
                p.getKiller().getWorld().strikeLightning(p.getKiller().getLocation());
            }
        }
    }

    @EventHandler
    public void clickInv(InventoryClickEvent e) {

        if (!e.getInventory().getName().equals(Config.GuiName)) return;
        e.setCancelled(true);
        Player p = (Player) e.getWhoClicked();

    }
}