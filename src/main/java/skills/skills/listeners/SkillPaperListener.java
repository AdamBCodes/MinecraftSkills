package skills.skills.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import skills.skills.Skills;
import skills.skills.skillfolder.PlayerSkill;
import skills.skills.skillfolder.SkillDataType;

public class SkillPaperListener implements Listener {
    @EventHandler
    public void onUse(PlayerInteractEvent e){
        //Checks if paper has All requirements to use
        if(!e.hasItem()) return;

        if(e.getItem().getType() != Material.PAPER) return;

        if(!e.getItem().hasItemMeta()) return;

        if(!e.getItem().getItemMeta().getDisplayName().contains("SkillPaper")) return;

        //Gets information for to get ready to display
        Player player = e.getPlayer();
        PersistentDataContainer container = e.getItem().getItemMeta().getPersistentDataContainer();
        String name = e.getItem().getItemMeta().getDisplayName();
        String[] nameArr = name.split(" ", 2);
        NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class), nameArr[1]);
        PlayerSkill playerInfo = player.getPersistentDataContainer().get(key, new SkillDataType());

        //Displays Information and sets new values
        PlayerSkill info = container.get(key, new SkillDataType());
        if(container.has(key, new SkillDataType())){
            ItemStack item = e.getItem();
            ItemMeta im = item.getItemMeta();
            info.setCurrent_exp(playerInfo.getCurrent_exp());
            info.setCurrent_level(playerInfo.getCurrent_level());
            info.setExp_to_next_level(playerInfo.getExp_to_next_level());
            im.getPersistentDataContainer().set(key, new SkillDataType(), info);
            item.setItemMeta(im);
            player.sendMessage(ChatColor.AQUA + info.getSkillName());
            player.sendMessage(ChatColor.GREEN + "Level: " + info.getCurrent_level() + "/" + info.getMax_level());
            player.sendMessage(ChatColor.YELLOW + "Exp: " + info.getCurrent_exp() + "/" + info.getExp_to_next_level());
        }
    }
}
