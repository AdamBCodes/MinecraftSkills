package skills.skills.skillfolder;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skills.skills.Skills;

public class Skill {
    private String skillName;
    private int max_level;
    private int current_level;
    private float exp_to_next_level;

    private float current_exp;

    public Skill(String name, int maxLevel, float exp){
        skillName = name;
        max_level = maxLevel;
        exp_to_next_level = exp;
    }

    public String getSkillName() {
        return skillName;
    }

    public int getMax_level() {
        return max_level;
    }

    public float getCurrent_exp() {
        return current_exp;
    }

    public float getExp_to_next_level() {
        return exp_to_next_level;
    }

    public int getCurrent_level(){
        return current_level;
    }
}
