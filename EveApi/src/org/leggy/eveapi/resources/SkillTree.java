package org.leggy.eveapi.resources;


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

	public static void main(String args[]) {
		List<SkillGroup> skillGroups = getSkillTree();
		for(SkillGroup group : skillGroups){
			System.out.println(group.getName());
			for(Skill skill : group.getSkills()){
				System.out.println("    " + skill.getName());
			}
		}
	}


	private static Set<ApiSkillGroup> getSkillGroupSet() {
		SkillTreeParser parser = SkillTreeParser.getInstance();
		SkillTreeResponse response = null;
		try {
			response = parser.getResponse();
		} catch (ApiException e) {
			System.out.println("exception");
		}

		return response.getAll();

	}

	public static List<SkillGroup> getSkillTree() {

		Map<Integer, List<ApiSkill>> groupSkills = new HashMap<Integer, List<ApiSkill>>();
		Map<Integer, ApiSkillGroup> groupIDs = new HashMap<Integer, ApiSkillGroup>();
		List<ApiSkill> temp = null;

		for (ApiSkillGroup group : getSkillGroupSet()) {
			if (group.getGroupID() != 505) {
				/*
				 * If the ApiSkillGroup exists, get it, else initialise a new
				 * one
				 */
				if (groupSkills.containsKey(group.getGroupID())) {
					temp = groupSkills.get(group.getGroupID());
				} else {
					temp = new LinkedList<ApiSkill>();
					groupSkills.put(group.getGroupID(), temp);
					if (!groupIDs.containsKey(group.getGroupID())) {
						groupIDs.put(group.getGroupID(), group);
					}
				}

				for (ApiSkill skill : group.getSkills()) {
					/*
					 * For each skill in the group, if it it published, then add
					 * it to the list.
					 */
					if (skill.isPublished()) {
						temp.add(skill);
					}
				}
			}
		}
		
		return convertGroups(groupSkills, groupIDs);
	}
	
	
	private static List<SkillGroup> convertGroups(Map<Integer, List<ApiSkill>> groupSkills, Map<Integer, ApiSkillGroup> groupIDs){
		List<SkillGroup> skillList = new LinkedList<SkillGroup>();
		
		for(Integer id : groupSkills.keySet()) {
			skillList.add(new SkillGroup(groupIDs.get(id), groupSkills.get(id)));
		}
		Collections.sort(skillList);
		return skillList;
	}

}
