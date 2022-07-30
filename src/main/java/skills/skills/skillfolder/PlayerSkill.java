package skills.skills.skillfolder;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skills.skills.Skills;

import java.io.Serializable;

public class PlayerSkill implements Serializable {
    private String skillName;
    private int max_level;
    private int current_level;
    private float exp_to_next_level;

    private float current_exp;

    public PlayerSkill(String name, int maxLevel, float exp, int curLevel){
        skillName = name;
        max_level = maxLevel;
        exp_to_next_level = exp;
        current_level = curLevel;
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

    public void setCurrent_level(int newLevel){
        current_level = newLevel;
    }
    public void setCurrent_exp(float newExp){
        current_exp = newExp;
    }

    public ItemStack getSkillInfo(){
        ItemStack info = new ItemStack(Material.PAPER);
        ItemMeta im = info.getItemMeta();
        NamespacedKey key = new NamespacedKey(Skills.getPlugin(Skills.class), this.getSkillName());
        im.getPersistentDataContainer().set(key, new SkillDataType(), this);
        im.setDisplayName("SkillPaper: " + this.getSkillName());
        info.setItemMeta(im);

        return info;
    }
}
