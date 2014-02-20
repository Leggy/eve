package org.leggy.eveapi.resources;

import java.util.List;

import com.beimin.eveapi.eve.skilltree.ApiSkill;
import com.beimin.eveapi.eve.skilltree.ApiSkillGroup;

public class SkillGroup {
	
	private int groupID;
	private String name;
	private List<Skill> skills;
	
	public SkillGroup(ApiSkillGroup group){
		this.groupID = group.getGroupID();
		this.name = group.getGroupName();
		
	}
	
	public int getGroupID(){
		return groupID;
	}
	
	public String getName(){
		return name;
	}
	
	public List<Skill> getSkills(){
		return skills;
	}
	
	public void addSkill(ApiSkill skill){
		Skill newSkill = new Skill(skill, this);
		if(!skills.contains(newSkill)){
			skills.add(newSkill);
		}
		
	}
}
