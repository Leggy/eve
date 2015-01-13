package org.leggy.eveapi.resources;

import java.util.Set;

import com.beimin.eveapi.account.apikeyinfo.ApiKeyInfoParser;
import com.beimin.eveapi.account.apikeyinfo.ApiKeyInfoResponse;
import com.beimin.eveapi.character.killlog.KillLogParser;
import com.beimin.eveapi.character.sheet.CharacterSheetParser;
import com.beimin.eveapi.character.sheet.CharacterSheetResponse;
import com.beimin.eveapi.core.ApiAuthorization;
import com.beimin.eveapi.exception.ApiException;
import com.beimin.eveapi.shared.killlog.AbstractKillLogParser;
import com.beimin.eveapi.shared.killlog.ApiKill;
import com.beimin.eveapi.shared.killlog.KillLogHandler;
import com.beimin.eveapi.shared.killlog.KillLogResponse;

public class KillLogReport {

	public static void main(String[] args) throws ApiException{
		System.out.println(getPilot(3192643, "uLm6k3jJerCg0MYEXdCS1UC4MskJaIJaaFc6lCAZh0KeF4p9WtzOJgw06UmCxjnr", 91930985));
		//System.out.println(validateApi(3192643, "uLm6k3jJerCg0MYEXdCS1UC4MskJaIJaaFc6lCAZh0KeF4p9WtzOJgw06UmCxjnr"));
	}

	private static int getPilot(int keyID, String code, long characterID) throws ApiException {
		ApiAuthorization auth = new ApiAuthorization(keyID, characterID, code);
		System.out.println(auth);

		AbstractKillLogParser parser = KillLogParser.getInstance();

		KillLogResponse response = parser.getResponse(auth);
		System.out.println(response.hasError());
		System.out.println(response.getError());
		
		
		ApiKeyInfoParser parser2 = ApiKeyInfoParser.getInstance();
		ApiKeyInfoResponse response2 = parser2.getResponse(auth);
		System.out.println(response2.hasError());
		
		Set<ApiKill> kills = response.getAll();
		
		System.out.println(kills.size());
		
		for(ApiKill kill : kills){
			System.out.println(kill.getKillTime());
		}

		return 1;
	}
	
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
		} else if (!checkAccessMask((int)response.getAccessMask())) {
			return 2;
		} else {
			return 0;
		}
	}
	
	private static boolean checkAccessMask(int mask){
		System.out.println(mask);
		/*
		 * Kill log = 256
		 * Charactersheet = 8
		 */
		System.out.println((mask & 256));
		 System.out.println((mask & 8));
		return ((mask & 256) > 0 && (mask & 8) > 0);
	}

}
