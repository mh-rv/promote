package rotzloch.promote;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.logging.Level;

public class Helper {
    private static main plugin;
    public static void Init(main plugin) {
        Helper.plugin = plugin;
        Helper.InitConfig();
    }

    private static void InitConfig(){
      Helper.Config().addDefault("promote.password", "1234");
      Helper.Config().addDefault("promote.command", "pex group member user add %s");
      Helper.Config().addDefault("promote.error", "&FPassword is not correct!");
      Helper.Config().addDefault("promote.welcomeToUser", "&6You are now Member!");
      Helper.Config().addDefault("promote.NoPermission", "&FYou have no permission to this Command!");
      Helper.Config().addDefault("promote.welcomeBroadcast", "&6%s is now a Member!");
      Helper.Config().options().copyDefaults(true);
      Helper.Config().options().header("Copyright 2013 by Rotzloch (roland.vonrotz@gmail.com)");
      Helper.Config().options().copyHeader(true);
      Helper.SaveConfig();
    }

    //Config
    public static void SaveConfig(){
        Helper.plugin.saveConfig();
    }

    public static FileConfiguration Config(){
        return Helper.plugin.getConfig();
    }

    public static void ReloadConfig() {
        Helper.plugin.reloadConfig();
    }


    public static Player GetPlayer(CommandSender sender){
        if(sender instanceof Player){
            return (Player)sender;
        }
        return null;
    }
    public static boolean HasPermission(Player player, String permission){
        boolean value = player == null || player.hasPermission(permission);
        if(!value){
            Helper.SendMessage(player, String.format(Helper.Config().getString("promote.NoPermission"),player.getName()));
        }
        return value;
    }
    public static void SendMessage(Player player, String message){
        if(player == null){
            Helper.LogInfo(Helper.ReplaceColorCodes(message));
        } else {
            player.sendMessage(Helper.ReplaceColorCodes(message));
        }
    }
    
    public static void CallCommandFromConsole(String command) {
        Helper.plugin.getServer().dispatchCommand(Helper.plugin.getServer().getConsoleSender(), command);
    }
    
    public static void LogInfo(String message){
        Helper.plugin.getLogger().log(Level.INFO, Helper.ReplaceColorCodes(message));
    }
    public static String ReplaceColorCodes(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static void SetMember(Player player) {
        String command = String.format(Helper.Config().getString("promote.command"), player.getName());
        Helper.CallCommandFromConsole(command);
        Helper.SendMessage(player, String.format(Helper.Config().getString("promote.welcomeToUser"),player.getName()));
        Helper.plugin.getServer().broadcastMessage(Helper.ReplaceColorCodes(String.format(Helper.Config().getString("promote.welcomeBroadcast"),player.getName())));
    }
}
 