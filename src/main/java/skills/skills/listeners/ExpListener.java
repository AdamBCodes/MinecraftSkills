package skills.skills.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataContainer;
import skills.skills.Skills;
import skills.skills.skillfolder.PlayerSkill;
import skills.skills.skillfolder.SkillDataType;

public class ExpListener implements Listener {
    //Add Exp After Falling
    @EventHandler
    public void onDamageEvent(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();
        PersistentDataContainer container = p.getPersistentDataContainer();
        //Acrobatics
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL){
            NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class), "Acrobatics");

            PlayerSkill skill = container.get(key, new SkillDataType());
            skill.setCurrent_exp(skill.getCurrent_exp()+1f);
            skill.LevelUp(p);
            container.set(key, new SkillDataType(), skill);
        }
        //Hardiness
        else if(e.getCause() == EntityDamageEvent.DamageCause.STARVATION){
            NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class), "Hardiness");

            PlayerSkill skill = container.get(key, new SkillDataType());
            skill.setCurrent_exp(skill.getCurrent_exp()+1f);
            skill.LevelUp(p);
            container.set(key, new SkillDataType(), skill);
        }
        //Toughness
        else{
            NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class), "Toughness");

            PlayerSkill skill = container.get(key, new SkillDataType());
            skill.setCurrent_exp(skill.getCurrent_exp()+1f);
            skill.LevelUp(p);
            container.set(key, new SkillDataType(), skill);
        }
    }
}
