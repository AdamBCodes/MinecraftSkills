package skills.skills;

import org.bukkit.plugin.java.JavaPlugin;

public final class Skills extends JavaPlugin {

    EventListener el;
    @Override
    public void onEnable() {
        // Plugin startup logic
        el = new EventListener();
        getServer().getPluginManager().registerEvents(el, this);
        getCommand("getSkill").setExecutor(new GetSkill());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
