package skills.skills;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

public class GetSkill implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.getInventory().firstEmpty() == -1){
                player.sendMessage(ChatColor.RED + "Inventory full");
                return true;
            }
            PersistentDataContainer pdata = player.getPersistentDataContainer();
            NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class),  args[0]);
            if(pdata.has(key)){
                for(int s = 0; s < SkillManager.getInstance().getSkills().size(); s++)
                {
                    if(SkillManager.getInstance().getSkills().get(s).getSkillName().toLowerCase() == args[0].toLowerCase()){
                        return true;
                    }
                }
                return true;
            }else{
                player.sendMessage("Skill Does Not Exist");
                return true;
            }
        }
        return true;
    }
}
