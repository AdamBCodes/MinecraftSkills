package skills.skills.commands;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import skills.skills.Skills;
import skills.skills.skillfolder.PlayerSkill;
import skills.skills.skillfolder.SkillDataType;

public class LevelUp implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return true;
        if(args[0] == null) return true;
        Player p = (Player) sender;
        PersistentDataContainer container = p.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class), args[0]);
        if(container.has(key)){
            PlayerSkill skill = container.get(key, new SkillDataType());
            skill.commandLevelUp();
            p.sendMessage(skill.getSkillName() + " has increased to level " + skill.getCurrent_level());
            container.set(key, new SkillDataType(), skill);
            return true;
        }else{
            p.sendMessage("Skill does not exist");
            return true;
        }
    }
}
