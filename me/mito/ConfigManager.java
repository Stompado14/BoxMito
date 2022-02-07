package me.mito;

import me.mito.APIs.MultipleFile;

import java.util.List;
import java.util.stream.Collectors;

public class ConfigManager {

    public String prefix;

    public String Mito;
    public String PlayerOff;
    public String semPerm;

    public String GuiName;
    public String GuiItemNome;
    public int GuiSlot;
    public List<String> GuiLore;

    public boolean JoinMsg;
    public boolean LeaveMsg;
    public boolean NewMitoMsg;
    public boolean Lighting;

    public List<String> MsgJoin;
    public List<String> MsgLeave;
    public List<String> MsgNewMito;

    public boolean isCommand;
    public List<String> CommandsList;

    public void loadConfig() {

        MultipleFile ConfigMyth = BoxMito.Instance.messages;

        prefix = ConfigMyth.getString("Prefixo").replace("&", "§");

        Mito = ConfigMyth.getString("Tag").replace("&", "§");
        PlayerOff = ConfigMyth.getString("JogadorNaoEncontrado").replace("&", "§");
        semPerm = ConfigMyth.getString("SemPermissao").replace("&", "§");

        GuiName = ConfigMyth.getString("Menu.Nome").replace("&", "§");
        GuiItemNome = ConfigMyth.getString("Menu.Itens.Nome").replace("&", "§");
        GuiSlot = ConfigMyth.getInt("Menu.Itens.Slot");
        GuiLore = ConfigMyth.getStringList("Menu.Itens.Lore");
        GuiLore = GuiLore.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());

        JoinMsg = ConfigMyth.getBoolean("AnunciarEntrada");
        LeaveMsg = ConfigMyth.getBoolean("AnunciarSaida");
        NewMitoMsg = ConfigMyth.getBoolean("AnunciarNovoMito");
        Lighting = ConfigMyth.getBoolean("Raio");

        MsgJoin = ConfigMyth.getStringList("Mensagens.MensagemDeEntrada");
        MsgJoin = MsgJoin.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());

        MsgLeave = ConfigMyth.getStringList("Mensagens.MensagemDeSaida");
        MsgLeave = MsgLeave.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());

        MsgNewMito = ConfigMyth.getStringList("Mensagens.AnunciarNovoMito");
        MsgNewMito = MsgNewMito.stream().map(l -> l.replace("&", "§")).collect(Collectors.toList());

        isCommand = ConfigMyth.getBoolean("Comandos.Ativar");
        CommandsList = ConfigMyth.getStringList("Comandos.Comandos");

    }

}
