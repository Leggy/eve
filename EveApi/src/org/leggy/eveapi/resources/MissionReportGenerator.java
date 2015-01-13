package org.leggy.eveapi.resources;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.beimin.eveapi.core.ApiAuthorization;
import com.beimin.eveapi.corporation.wallet.journal.WalletJournalParser;
import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.shared.wallet.RefType;
import com.beimin.eveapi.shared.wallet.journal.AbstractWalletJournalParser;
import com.beimin.eveapi.shared.wallet.journal.ApiJournalEntry;
import com.beimin.eveapi.shared.wallet.journal.WalletJournalResponse;

public class MissionReportGenerator {

	public static List<String> generateReport(int keyID, String vCode) throws MissionReportException{
		List<String> report = new LinkedList<String>();

		ApiAuthorization auth = new ApiAuthorization(keyID, vCode);

		AbstractWalletJournalParser parser = WalletJournalParser.getInstance();
		WalletJournalResponse response = null;
		
		try {
			response = parser.getResponse(auth, 1000,
					Long.MAX_VALUE, Integer.MAX_VALUE);
		} catch (ApiException e) {
			//Placeholder exception.
			throw new MissionReportException();
		}

		Collection<ApiJournalEntry> entries = response.getAll();
		Map<String, Double> taxIncome = new HashMap<String, Double>();
		Map<String, Integer> missions = new HashMap<String, Integer>();

		for (ApiJournalEntry entry : entries) {

			if (entry.getRefType().equals(RefType.AGENT_MISSION_REWARD)
					|| entry.getRefType().equals(
							RefType.AGENT_MISSION_TIME_BONUS_REWARD)
					|| entry.getRefType().equals(RefType.BOUNTY_PRIZE)
					|| entry.getRefType().equals(RefType.BOUNTY_PRIZES)) {

				String name = entry.getOwnerName2();
				double isk = entry.getAmount();
				boolean missionsRun = false;

				if (entry.getRefType().equals(RefType.AGENT_MISSION_REWARD)) {
					missionsRun = true;
				}

				if (taxIncome.containsKey(name)) {
					taxIncome.put(name, taxIncome.get(name) + isk);
				} else {
					taxIncome.put(name, isk);
				}
				if (missionsRun) {
					if (missions.containsKey(name)) {
						missions.put(name, missions.get(name) + 1);
					} else {
						missions.put(name, 1);
					}
				}

			}
		}

		addTaxReport(report, taxIncome);
		report.add("");
		report.add("");
		addMissionReport(report, missions);
		return report;
	}

	
	
	private static void addTaxReport(List<String> report, Map<String, Double> taxIncome) {
		List<JournalSummationEntry> pilots = new LinkedList<JournalSummationEntry>();
		for (String name : taxIncome.keySet()) {
			pilots.add(new JournalSummationEntry(name, taxIncome.get(name)));
		}
		Collections.sort(pilots);
		int position = 0;
		report.add("[center][table]");
		report.add("[tr][td][b]Position[/b][/td][td][b]   Pilot   [/b][/td][td][b]   Tax   [/b][/td][/tr]");
		for (JournalSummationEntry pilot : pilots) {
			report.add("[tr][td]" + ++position + "[/td]" + pilot);
		}
		report.add("[/table][/center]");
	}

	
	
	private static void addMissionReport(List<String> report, Map<String, Integer> missions) {
		List<MissionSummationEntry> pilotMissions = new LinkedList<MissionSummationEntry>();
		for (String name : missions.keySet()) {
			pilotMissions.add(new MissionSummationEntry(name, missions
					.get(name)));
		}
		Collections.sort(pilotMissions);
		int position = 0;
		report.add("[center][table]");
		report.add("[tr][td][b]Position[/b][/td][td][b]   Pilot   [/b][/td][td][b]   Missions Run   [/b][/td][/tr]");
		for (MissionSummationEntry pilot : pilotMissions) {
			report.add("[tr][td]" + ++position + "[/td]" + pilot);
		}
		report.add("[/table][/center]");
	}

	
	
	public static void main(String[] args) throws ApiException {
		ApiAuthorization auth = new ApiAuthorization(3871908,
				"7BSfhCKVH3N2nZVVNkyi2fKaRotjbNrm8LsxdAfVuj5S3DxhR1E4CNJV7fMKmpGq");

		AbstractWalletJournalParser parser = WalletJournalParser.getInstance();
		WalletJournalResponse response = parser.getResponse(auth, 1000,
				Long.MAX_VALUE, Integer.MAX_VALUE);

		Collection<ApiJournalEntry> entries = response.getAll();
		Map<String, Double> taxIncome = new HashMap<String, Double>();
		Map<String, Integer> missions = new HashMap<String, Integer>();

		for (ApiJournalEntry entry : entries) {
			// System.out.println(entry.getDate());

			if (entry.getRefType().equals(RefType.AGENT_MISSION_REWARD)
					|| entry.getRefType().equals(
							RefType.AGENT_MISSION_TIME_BONUS_REWARD)
					|| entry.getRefType().equals(RefType.BOUNTY_PRIZE)
					|| entry.getRefType().equals(RefType.BOUNTY_PRIZES)) {

				String name = entry.getOwnerName2();
				double isk = entry.getAmount();
				boolean missionsRun = false;

				if (entry.getRefType().equals(RefType.AGENT_MISSION_REWARD)) {
					missionsRun = true;
				}

				if (taxIncome.containsKey(name)) {
					taxIncome.put(name, taxIncome.get(name) + isk);
				} else {
					taxIncome.put(name, isk);
				}
				if (missionsRun) {
					if (missions.containsKey(name)) {
						missions.put(name, missions.get(name) + 1);
					} else {
						missions.put(name, 1);
					}
				}

			}
		}

		List<JournalSummationEntry> pilots = new LinkedList<JournalSummationEntry>();
		for (String name : taxIncome.keySet()) {
			pilots.add(new JournalSummationEntry(name, taxIncome.get(name)));
		}
		Collections.sort(pilots);
		int position = 0;
		System.out.println("[center][table]");
		System.out
				.println("[tr][td][b]Position[/b][/td][td][b]   Pilot   [/b][/td][td][b]   Tax   [/b][/td][/tr]");
		for (JournalSummationEntry pilot : pilots) {
			System.out.print("[tr][td]" + ++position + "[/td]");
			System.out.println(pilot);
		}
		System.out.println("[/table][/center]");

		List<MissionSummationEntry> pilotMissions = new LinkedList<MissionSummationEntry>();
		for (String name : missions.keySet()) {
			pilotMissions.add(new MissionSummationEntry(name, missions
					.get(name)));
		}
		Collections.sort(pilotMissions);
		position = 0;
		System.out.println("[center][table]");
		System.out
				.println("[tr][td][b]Position[/b][/td][td][b]   Pilot   [/b][/td][td][b]   Missions Run   [/b][/td][/tr]");
		for (MissionSummationEntry pilot : pilotMissions) {
			System.out.print("[tr][td]" + ++position + "[/td]");
			System.out.println(pilot);
		}
		System.out.println("[/table][/center]");

	}
}
