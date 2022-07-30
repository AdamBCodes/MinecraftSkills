package skills.skills;

import skills.skills.skillfolder.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillManager {

    private static List<Skill> skills = new ArrayList<>();
    public static SkillManager instance = null;

    public static SkillManager getInstance(){
        if(instance == null){
            instance = new SkillManager();
            skills.add(new Skill("Acrobatics", 10, 20));
            skills.add(new Skill("Hardiness", 5, 20));
        }
        return instance;
    }

    public List<Skill> getSkills(){
        return skills;
    }
}
