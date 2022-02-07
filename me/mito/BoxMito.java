package me.mito;

import me.mito.APIs.MultipleFile;
import me.mito.Commands.MythCommands;
import me.mito.Listeners.Events;
import me.mito.Listeners.nChat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BoxMito extends JavaPlugin {

    public static BoxMito Instance;
    public static ConfigManager Config;
    public MultipleFile messages;

    public void onEnable() {

        Instance = this;
        registerYaml();
        registerCommands();
        registerEvents();
        sendMessage();
    }

    private void registerCommands() {

        getCommand("mito").setExecutor(new MythCommands());

    }

    private void registerEvents() {

        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new nChat(), this);

    }
    private void registerYaml() {

        Config = new ConfigManager();
        saveDefaultConfig();
        messages = new MultipleFile(this, null, "mensagens.yml", false);
        messages.reloadConfig();
        Config.loadConfig();

    }

    private void sendMessage() {

        Bukkit.getConsoleSender().sendMessage("§b[BoxMito] §fCriado por §b[Stompado]");
        Bukkit.getConsoleSender().sendMessage("§b[BoxMito] §aO plugin §bBoxMito §afoi iniciado com sucesso.");

    }

}
