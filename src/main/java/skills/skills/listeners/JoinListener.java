package skills.skills.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import skills.skills.SkillManager;
import skills.skills.Skills;
import skills.skills.skillfolder.PlayerSkill;
import skills.skills.skillfolder.Skill;
import skills.skills.skillfolder.SkillDataType;

import java.util.List;

//Used To Listen for when Players Join
public class JoinListener implements Listener {
    //List of All the skills
    List<Skill> skills = SkillManager.getInstance().getSkills();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        PersistentDataContainer pdata = p.getPersistentDataContainer();
        //Creates Skills If not already and gives Player paper on each skill
        for(int s = 0; s < skills.size(); s++){
            NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class), skills.get(s).getSkillName());
            PersistentDataContainer container = p.getPersistentDataContainer();
            //Adds Skills To Player if they dont exist and gives info sheet on skills
            if(!pdata.has(key)) {
                PlayerSkill skill = new PlayerSkill(skills.get(s).getSkillName(), skills.get(s).getMax_level(), skills.get(s).getExp_to_next_level(), 1, skills.get(s).getSkill_type());
                container.set(key, new SkillDataType(), skill);
                p.sendMessage("Added " + skill.getSkillName() + " to Your Skills");
                p.getInventory().addItem(skill.getSkillInfo());
            }
            else{

                p.sendMessage("Already Have Skill: " + SkillManager.getInstance().getSkills().get(s).getSkillName());
            }
        }
    }
}
