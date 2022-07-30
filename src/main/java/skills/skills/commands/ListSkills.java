package skills.skills.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import skills.skills.SkillManager;

public class ListSkills implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        for(int s = 0; s < SkillManager.getInstance().getSkills().size(); s++){
            p.sendMessage(ChatColor.AQUA+ "Skill: " + SkillManager.getInstance().getSkills().get(s).getSkillName());
        }
        return true;
    }
}
