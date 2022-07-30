package skills.skills.skillfolder;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import skills.skills.Skills;
import skills.skills.skillType;

import java.io.Serializable;

public class PlayerSkill implements Serializable {
    //Variables
    private String skillName;
    protected int max_level;
    protected int current_level;
    protected float exp_to_next_level;
    private float base_exp;
    protected float current_exp;

    //Constructor
    public PlayerSkill(String name, int maxLevel, float exp, int curLevel, skillType type){
        skillName = name;
        max_level = maxLevel;
        exp_to_next_level = exp;
        current_level = curLevel;
        base_exp = exp;
    }

    //User Uses
    protected float expScale(){
        return Math.round((float) Math.pow(base_exp * current_level, 1.1f));
    }

    protected double AttrScale() {
        return Math.round(Math.pow(current_level, 1.2f));
    }

    public boolean LevelUp(Player p){
        if(current_level == max_level) return false;
        if(current_exp < exp_to_next_level) return false;
        current_level += 1;
        current_exp = 0;
        exp_to_next_level = expScale();
        p.sendMessage(skillName + " Level has Increased to " + current_level);
        return true;
    }

    public void commandLevelUp(){
        if(current_level+1 == max_level) return;
        current_level += 1;
        current_exp = 0;
    }
    //Getters and Setters
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
        if(current_level == max_level){ current_exp = exp_to_next_level; }
        current_exp = newExp;
    }

    public void setExp_to_next_level(float newExp){
        exp_to_next_level = newExp;
    }

    //Gets Info On Skill
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
