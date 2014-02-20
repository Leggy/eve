package org.leggy.eveapi.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.beimin.eveapi.eve.skilltree.ApiSkill;
import com.beimin.eveapi.eve.skilltree.ApiSkillGroup;
import com.beimin.eveapi.eve.skilltree.SkillTreeParser;
import com.beimin.eveapi.eve.skilltree.SkillTreeResponse;
import com.beimin.eveapi.exception.ApiException;

public class SkillTree {
	
	public static void main(String args[]){
		skillTree();
	}
	

	public List<SkillGroup> getSkillTree() {
		SkillTreeParser parser = SkillTreeParser.getInstance();
		SkillTreeResponse response = null;
		try {
			response = parser.getResponse();
		} catch (ApiException e) {
			System.out.println("exception");
		}

		return null;

	}
	


	private static void skillTree() {
		List<SkillGroup> skillGroups = new LinkedList<SkillGroup>();

		SkillTreeParser parser = SkillTreeParser.getInstance();
		SkillTreeResponse response = null;
		try {
			response = parser.getResponse();
		} catch (ApiException e) {
			System.out.println("exception");
		}

		Set<ApiSkillGroup> groups = response.getAll();

		Map<ApiSkillGroup, List<ApiSkill>> grps = new HashMap<ApiSkillGroup, List<ApiSkill>>();
		List<ApiSkill> temp = null;
		
		ApiSkillGroup g1 = null;
		ApiSkillGroup g2 = null;

		for (ApiSkillGroup group : groups) {
			if(group.getGroupID() == 1213){
				if(g1 == null){
					g1 = group;
				} else if(g2 == null){
					g2 = group;
				}
			}
			if(g1 != null && g2 != null){
				System.out.println("Group 1: " + g1.getGroupID());
				System.out.println("Group 2: " + g2.getGroupID());
				System.out.println("Equals: " + g1.equals(g2));
			}
			/*
			 * If the ApiSkillGroup exists, get it, else initialise a new one
			 */
			if (grps.containsKey(group.getGroupID())) {
				temp = grps.get(group.getGroupID());
			} else {
				temp = new LinkedList<ApiSkill>();
				//grps.put(group.getGroupID(), temp);
			}

			for (ApiSkill skill : group.getSkills()) {
				/*
				 * For each skill in the group, if it it published, then add it
				 * to the list.
				 */
				if (skill.isPublished()) {
					temp.add(skill);
				}
			}
		}

		for (ApiSkillGroup key : grps.keySet()) {
			//System.out.println(key.getGroupName());
			Collections.sort(grps.get(key));
			for (ApiSkill skill : grps.get(key)) {
				//System.out.println("    " + skill.getTypeName());
			}
		}

	}

}
