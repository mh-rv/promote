package rotzloch.promote;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        Helper.Init(this);
        this.getCommand("pw").setExecutor(new pwcommand());
    }

    @Override
    public void onDisable() {
        //Helper.SaveConfig();
    }
}