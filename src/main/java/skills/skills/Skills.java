package skills.skills;

import org.bukkit.plugin.java.JavaPlugin;
import skills.skills.commands.GetSkill;
import skills.skills.commands.LevelUp;
import skills.skills.commands.ListSkills;
import skills.skills.listeners.ExpListener;
import skills.skills.listeners.JoinListener;
import skills.skills.listeners.SkillPaperListener;

public final class Skills extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        //Event Listeners
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new SkillPaperListener(), this);
        getServer().getPluginManager().registerEvents(new ExpListener(), this);

        //Commands
        getCommand("getSkill").setExecutor(new GetSkill());
        getCommand("levelUp").setExecutor(new LevelUp());
        getCommand("listSkills").setExecutor(new ListSkills());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
