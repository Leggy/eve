package org.leggy.eveapi.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.beimin.eveapi.account.apikeyinfo.ApiKeyInfoParser;
import com.beimin.eveapi.account.apikeyinfo.ApiKeyInfoResponse;
import com.beimin.eveapi.account.characters.EveCharacter;
import com.beimin.eveapi.character.sheet.CharacterSheetParser;
import com.beimin.eveapi.character.sheet.CharacterSheetResponse;
import com.beimin.eveapi.core.ApiAuthorization;
import com.beimin.eveapi.exception.ApiException;

public class CharacterReport {

	/**
	 * 
	 * @param keyID
	 * @param code
	 * @return Returns 0 if it is successful, 1 if it is not an account key, 2
	 *         if the access mask is incorrect and 3 if an error is encountered.
	 */
	public static int validateApi(int keyID, String code) {
		ApiAuthorization auth = new ApiAuthorization(keyID, code);

		ApiKeyInfoParser parser = ApiKeyInfoParser.getInstance();
		ApiKeyInfoResponse response = null;

		try {
			response = parser.getResponse(auth);
		} catch (ApiException e) {
			return 3;
		}

		if (!response.isAccountKey()) {
			return 1;
		} else if (response.getAccessMask() != 264) {
			return 2;
		} else {
			return 0;
		}
	}

	public static List<Pilot> getPilots(int keyID, String code) {
		List<Long> characterIDs = getCharacterIDs(keyID, code);

		List<Pilot> pilots = new ArrayList<Pilot>();

		for (long characterID : characterIDs) {
			Pilot temp = getPilot(keyID, code, characterID);
			if (temp != null) {
				pilots.add(temp);
			}
		}

		return pilots;

	}

	private static Pilot getPilot(int keyID, String code, long characterID) {
		ApiAuthorization auth = new ApiAuthorization(keyID, characterID, code);

		CharacterSheetParser parser = CharacterSheetParser.getInstance();
		CharacterSheetResponse response = null;
		try {
			response = parser.getResponse(auth);
		} catch (ApiException e) {
			return null;
		}

		return new Pilot(response);
	}

	private static List<Long> getCharacterIDs(int keyID, String code) {
		List<Long> characterIDs = new ArrayList<Long>();

		ApiAuthorization auth = new ApiAuthorization(keyID, code);

		ApiKeyInfoParser parser = ApiKeyInfoParser.getInstance();
		ApiKeyInfoResponse response = null;

		try {
			response = parser.getResponse(auth);
		} catch (ApiException e) {
			return null;
		}

		Collection<EveCharacter> chars = response.getEveCharacters();

		for (EveCharacter character : chars) {
			characterIDs.add(character.getCharacterID());
		}

		return characterIDs;
	}

	public static void main(String args[]) {
		int keyId = 3060581;
		String vCode = "WdILcl61q7EAAonQoz8ETt5YqbJurspK5HtixNLcBeaiNA4kAuqXBU4emsnfGZ4d";
		int lauraID = 91930985;

		ApiAuthorization auth = new ApiAuthorization(keyId, lauraID, vCode);


		CharacterSheetParser parser = CharacterSheetParser.getInstance();
		CharacterSheetResponse response = null;
		try {
			response = parser.getResponse(auth);
		} catch (ApiException e) {
			System.out.println("exception");
		}

		ApiKeyInfoParser parser2 = ApiKeyInfoParser.getInstance();
		ApiKeyInfoResponse response2 = null;

		try {
			response2 = parser2.getResponse(auth);
		} catch (ApiException e) {
			System.out.println("exception2");
		}

		System.out.println(response2.getAccessMask());
		System.out.println("Account: " + response2.isAccountKey());
		System.out.println("Character: " + response2.isCharacterKey());
		System.out.println("Corporation: " + response2.isCorporationKey());
		System.out.println("Account: " + response2.getEveCharacters());

		Pilot laura = new Pilot(response);

		laura.generateReport();
	}

}
