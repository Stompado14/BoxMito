package me.mito.Listeners;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.mito.BoxMito;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class nChat implements Listener {

    @EventHandler
    public void nChatEvent(ChatMessageEvent e) {
        Player p = e.getSender();

        String myth = BoxMito.Instance.getConfig().getString("Mito");

        if (myth.equals(p.getName())) {
            e.setTagValue("mito", BoxMito.Config.Mito);

        }
    }
}