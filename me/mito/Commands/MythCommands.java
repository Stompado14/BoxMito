package me.mito.Commands;

import me.mito.BoxMito;
import me.mito.ConfigManager;
import me.mito.Methods.MythMethods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MythCommands implements CommandExecutor {

    ConfigManager Config = BoxMito.Config;

    String Prefix = Config.prefix;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {

        if (args.length > 0) {

            if (args[0].equalsIgnoreCase("setmito") || args[0].equalsIgnoreCase("setmyth")) {

                if (!sender.hasPermission("boxmito.admin")) {
                    sender.sendMessage(Config.semPerm.replace("@prefix", Prefix));
                    return true;
                }

                if (args.length < 2) {
                    sender.sendMessage("§b[BoxMito] §8» §cUtilize /mito setmito (jogador).");
                    return true;
                }

                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage(Config.PlayerOff.replace("@prefix", Prefix));
                    return true;
                }

                String myth = BoxMito.Instance.getConfig().getString("Mito");

                if (!myth.equals(target.getName())) {

                    new MythMethods().setMito(target);
                    sender.sendMessage("§b[BoxMito] §8» §aVoce setou o §bMito §ano jogador §b" + target.getName());
                    BoxMito.Instance.saveConfig();

                    if (Config.Lighting) {
                        target.getWorld().strikeLightning(target.getLocation());
                    }

                } else {

                    sender.sendMessage("§b[BoxMito] §8» §cEsse jogador já possui mito.");

                }

            } else if (args[0].equalsIgnoreCase("ajuda")) {

                if (!sender.hasPermission("boxmito.admin")) {
                    sender.sendMessage("");
                    sender.sendMessage("§b§lMITO §8» §bComandos");
                    sender.sendMessage("");
                    sender.sendMessage(" §b/mito §8» §7Veja o Mito atual");
                    sender.sendMessage(" §b/mito ajuda §8» §7Ver está mensagem");
                    sender.sendMessage("");

                }

                sender.sendMessage("");
                sender.sendMessage("§b§lMITO §8» §bComandos");
                sender.sendMessage("");
                sender.sendMessage(" §b/mito §8» §7Veja o Mito atual");
                sender.sendMessage(" §b/mito setmito (jogador) §8» §7Setar o mito em um jogador");
                sender.sendMessage(" §b/mito ajuda §8» §7Ver está mensagem");
                sender.sendMessage("");

            } else {
                sender.sendMessage("§5[BoxMito] §cUtilize /mito ajuda para mais comandos.");
            }

        } else {

            if (!(sender instanceof Player)) {
                sender.sendMessage("§cO console não executa esse comando.");
                return true;
            }

            Player p = (Player)sender;
            new MythMethods().MitoGUI(p);

        }


        return false;
    }
}
