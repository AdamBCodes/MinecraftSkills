package skills.skills;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import skills.skills.skillfolder.PlayerSkill;
import skills.skills.skillfolder.SkillDataType;

public class GetSkill implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return true;

        if(args[0] != null)
        {
            Player player = (Player) sender;
            PersistentDataContainer container = player.getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class), args[0]);
            if(container.has(key)){
                PlayerSkill skill = container.get(key, new SkillDataType());
                player.getInventory().addItem(skill.getSkillInfo());
                return true;
            }
            else{
                player.sendMessage("Skill Does not exist");
                return true;
            }
        }
        return true;
    }
}
