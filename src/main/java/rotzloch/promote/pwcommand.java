package rotzloch.promote;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import rotzloch.promote.Helper;

public class pwcommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command,String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pw")) {
            Player player = Helper.GetPlayer(sender);
            if(player == null){
                return true;
            }
            if(!Helper.HasPermission(player, "promote.use")){
                return true;
            }
            if(args.length == 1){
                if(!args[0].equalsIgnoreCase(Helper.Config().getString("promote.password") )){
                    Helper.SendMessage(player, Helper.Config().getString("promote.error"));
                    return true;
                }
                Helper.SetMember(player);
            }
        }
        return true;
    }
}