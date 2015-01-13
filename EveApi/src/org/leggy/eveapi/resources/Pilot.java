package org.leggy.eveapi.resources;

import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.beimin.eveapi.character.sheet.ApiAttributeEnhancer;
import com.beimin.eveapi.character.sheet.ApiSkill;
import com.beimin.eveapi.character.sheet.CharacterSheetResponse;

public class Pilot {

	private long id;
	private String name;
	private double balance;
	private Date dob;
	private String race;

	private int perception;
	private int memory;
	private int willpower;
	private int intelligence;
	private int charisma;

	private String corpName;
	private long corpID;
	private String allianceName;
	private long allianceID;

	private List<String> implants;

	private Map<Integer, Integer> skills;
	private int skillPoints;

	public Pilot(CharacterSheetResponse sheet) {
		this.id = sheet.getCharacterID();
		this.name = sheet.getName();
		this.balance = sheet.getBalance();
		this.dob = sheet.getDateOfBirth();
		this.race = toCamelCase(sheet.getRace().name());

		this.perception = sheet.getPerception();
		this.memory = sheet.getMemory();
		this.willpower = sheet.getWillpower();
		this.intelligence = sheet.getIntelligence();
		this.charisma = sheet.getCharisma();

		this.corpName = sheet.getCorporationName();
		this.corpID = sheet.getCorporationID();
		this.allianceName = sheet.getAllianceName();
		//this.allianceID = sheet.getAllianceID();

		this.implants = new LinkedList<String>();
		for (ApiAttributeEnhancer enhancer : sheet.getAttributeEnhancers()) {
			implants.add(enhancer.getAugmentatorName());

		}
		skills = new HashMap<Integer, Integer>();
		for (ApiSkill skill : sheet.getSkills()) {
			this.skillPoints += skill.getSkillpoints();
			skills.put(skill.getTypeID(), skill.getLevel());
		}
	}

	private static String toCamelCase(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	private static String urlInfo(String name) {
		String res = "[url=https://gate.eveonline.com/Profile/"
				+ name
				+ "]Eve Gate[/url] | [url=http://evewho.com/pilot/"
				+ name
				+ "]Eve Who[/url] | [url=http://www.eve-search.com/search/author/"
				+ name
				+ "]Eve Search[/url] | [url=http://eve.battleclinic.com/killboard/combat_record.php?type=player&name="
				+ name + "]Battle Clinic Killboard[/url]";

		return res;
	}

	public List<String> generateReport() {

		List<String> report = new LinkedList<String>();

		String gifs[] = {
				"[img]http://myeve.eve-online.com/bitmaps/character/level0.gif[/img]",
				"[img]http://myeve.eve-online.com/bitmaps/character/level1.gif[/img]",
				"[img]http://myeve.eve-online.com/bitmaps/character/level2.gif[/img]",
				"[img]http://myeve.eve-online.com/bitmaps/character/level3.gif[/img]",
				"[img]http://myeve.eve-online.com/bitmaps/character/level4.gif[/img]",
				"[img]http://myeve.eve-online.com/bitmaps/character/level5.gif[/img]" };

		report.add("Name (ID): " + this.name + " (" + this.id + ")");
		report.add("Race: " + this.race);
		report.add("Corporation: " + this.corpName);
		report.add("Wallet Balance: "
				+ NumberFormat.getCurrencyInstance().format(this.balance));
		report.add("Total Skill Points " + NumberFormat.getNumberInstance().format(this.skillPoints));
		report.add(urlInfo(this.name));
		report.add("");
		report.add("[b]Attributes[/b]");
		report.add("Intelligence: " + this.intelligence);
		report.add("Memory: " + this.memory);
		report.add("Charisma: " + this.charisma);
		report.add("Perception: " + this.perception);
		report.add("Willpower: " + this.willpower);
		report.add("");

		report.add("[b]Implants[/b]:");
		if (this.implants.size() == 0) {
			report.add("[i]No implants found[/i]");
		} else {
			for (String implant : implants) {
				report.add(implant);
			}
		}
		report.add("");

		report.add("[b]Skills[/b]");
		for (SkillGroup group : SkillTree.getSkillTree()) {
			report.add("[i]" + group.getName() + "[/i]");
			boolean hasSkill = false;
			for (Skill skill : group.getSkills()) {
				if (skills.containsKey(skill.getTypeID())) {
					hasSkill = true;
					report.add(gifs[skills.get(skill.getTypeID())] + " "
							+ skill.getName());
				}
			}
			if (hasSkill == false) {
				report.add("No skills found");
			}
			report.add("");
		}

		return report;
	}

}
