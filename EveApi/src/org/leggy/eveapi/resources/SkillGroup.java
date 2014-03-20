package org.leggy.eveapi.resources;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.beimin.eveapi.eve.skilltree.ApiSkill;
import com.beimin.eveapi.eve.skilltree.ApiSkillGroup;

public class SkillGroup implements Comparable<SkillGroup>{
	
	private int groupID;
	private String name;
	private List<Skill> skills;
	
	public SkillGroup(ApiSkillGroup group, List<ApiSkill> skillList){
		this.groupID = group.getGroupID();
		this.name = group.getGroupName();
		
		this.skills = new LinkedList<Skill>();
		for(ApiSkill skill :skillList){
			skills.add(new Skill(skill, this));
		}
		Collections.sort(skills);
		
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
	

	@Override
	public int compareTo(SkillGroup skillGroup) {
		if(skillGroup == null){
			throw new NullPointerException();
		}
		return this.name.toLowerCase().compareTo(skillGroup.name.toLowerCase());
	}
}
