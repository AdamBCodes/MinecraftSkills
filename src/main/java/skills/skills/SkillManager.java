package skills.skills;

import skills.skills.skillfolder.Skill;

import java.util.ArrayList;
import java.util.List;

//IDEA: Expand Later so it can be read from config so skills can be added or removed
public class SkillManager {

    private static List<Skill> skills = new ArrayList<>();
    public static SkillManager instance = null;

    public static SkillManager getInstance(){
        if(instance == null){
            instance = new SkillManager();
            //Skills
            skills.add(new Skill("Acrobatics", 10, 5, skillType.NORMAL));
            skills.add(new Skill("Hardiness", 5, 5, skillType.ATTRIBUTE));
            skills.add(new Skill("Toughness", 3, 5, skillType.ATTRIBUTE));
        }
        return instance;
    }

    public List<Skill> getSkills(){
        return skills;
    }
}
