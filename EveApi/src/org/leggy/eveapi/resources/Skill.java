package org.leggy.eveapi.resources;

import com.beimin.eveapi.eve.skilltree.ApiSkill;

public class Skill implements Comparable<Skill> {

	private int typeID;
	private String name;
	private String description;
	private int rank;
	private SkillGroup group;

	// todo required skills

	public Skill(ApiSkill skill, SkillGroup group) {
		this.typeID = skill.getTypeID();
		this.name = skill.getTypeName();
		this.description = skill.getDescription();
		this.rank = skill.getRank();
		this.group = group;
	}

	/**
	 * 
	 * @return
	 */
	public int getTypeID() {
		return typeID;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @return
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * 
	 * @return
	 */
	public SkillGroup getGroup() {
		return group;
	}


	@Override
	public int compareTo(Skill skill) {
		if (skill == null) {
			throw new NullPointerException();
		}
		return this.name.toLowerCase().compareTo(skill.name.toLowerCase());
	}

}
