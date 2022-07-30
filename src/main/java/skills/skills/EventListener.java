package skills.skills;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import skills.skills.skillfolder.PlayerSkill;
import skills.skills.skillfolder.Skill;
import skills.skills.skillfolder.SkillDataType;

import java.util.ArrayList;
import java.util.List;

public class EventListener implements Listener {
    List<Skill> skills = SkillManager.getInstance().getSkills();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        List<PlayerSkill> playerSkills = new ArrayList<>();
        System.out.println("Player Joined(Get or Create Initial Skills");
        Player p = e.getPlayer();
        PersistentDataContainer pdata = p.getPersistentDataContainer();
        for(int s = 0; s < skills.size(); s++){
            NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class), skills.get(s).getSkillName());
            PersistentDataContainer container = p.getPersistentDataContainer();
            if(!pdata.has(key)) {
                PlayerSkill skill = new PlayerSkill(skills.get(s).getSkillName(), skills.get(s).getMax_level(), skills.get(s).getExp_to_next_level(), 0);
                playerSkills.add(skill);
                container.set(key, new SkillDataType(), skill);
                p.sendMessage("Added " + skill.getSkillName() + " to Your Skills");
                p.getInventory().addItem(skill.getSkillInfo());
            }
            else{
                p.sendMessage("Already Have Skill: " + SkillManager.getInstance().getSkills().get(s).getSkillName());
            }
        }
    }

    @EventHandler
    public void onUse(PlayerInteractEvent e){
        if(!e.hasItem()) return;

        if(e.getItem().getType() != Material.PAPER) return;

        if(!e.getItem().hasItemMeta()) return;

        if(!e.getItem().getItemMeta().getDisplayName().contains("SkillPaper")) return;

        Player player = e.getPlayer();
        PersistentDataContainer container = e.getItem().getItemMeta().getPersistentDataContainer();
        String name = e.getItem().getItemMeta().getDisplayName();
        String[] nameArr = name.split(" ", 2);
        NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class), nameArr[1]);
        PlayerSkill playerInfo = player.getPersistentDataContainer().get(key, new SkillDataType());

        PlayerSkill info = container.get(key, new SkillDataType());
        if(container.has(key, new SkillDataType())){
            ItemStack item = e.getItem();
            ItemMeta im = item.getItemMeta();
            info.setCurrent_exp(playerInfo.getCurrent_exp());
            info.setCurrent_level(playerInfo.getCurrent_level());
            info.setExp_to_next_level(playerInfo.getExp_to_next_level());
            im.getPersistentDataContainer().set(key, new SkillDataType(), info);
            item.setItemMeta(im);
            player.sendMessage(ChatColor.GREEN + info.getSkillName());
            player.sendMessage(ChatColor.GREEN + "Level: " + info.getCurrent_level() + "/" + info.getMax_level());
            player.sendMessage(ChatColor.YELLOW + "Exp: " + info.getCurrent_exp() + "/" + info.getExp_to_next_level());
        }
    }


    //Add Exp After Falling
    @EventHandler
    public void onFallDamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();

        //Acrobatics
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
            NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class), "Acrobatics");
            PersistentDataContainer container = p.getPersistentDataContainer();
            PlayerSkill acrobatics = container.get(key, new SkillDataType());
            acrobatics.setCurrent_exp(acrobatics.getCurrent_exp()+1f);
            if(acrobatics.getExp_to_next_level() <= acrobatics.getCurrent_exp()){
                if(acrobatics.getCurrent_level() != acrobatics.getMax_level()) {
                    acrobatics.LevelUp();
                    acrobatics.expScale();
                    p.sendMessage("Acrobatics Level has Increased to " + acrobatics.getCurrent_level());
                }
            }
            container.set(key, new SkillDataType(), acrobatics);
        }
        else if(e.getCause() == EntityDamageEvent.DamageCause.STARVATION){
            return;
        }
    }
}
