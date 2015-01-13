package org.leggy.btc.recruitment;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.leggy.btc.recruitment.exceptions.*;
import org.leggy.eveapi.resources.CharacterReport;
import org.leggy.eveapi.resources.Pilot;

public class Model {

	private static String newline = System.getProperty("line.separator");
	
	private String filename = null;;



	public void generateReport(int keyID, String code) throws AccountKeyException, AccessMaskException, ConnectionException {

		switch (CharacterReport.validateApi(keyID, code)) {
		case 0:
			this.filename = outputToFile(CharacterReport.getPilots(keyID, code));
			break;
		case 1:
			throw new AccountKeyException();
		case 2:
			throw new AccessMaskException();
		case 3:
			throw new ConnectionException();
		}
				
	}

	private String outputToFile(List<Pilot> pilots) {
		String filename = "reports/" + getCurrentTimeStamp() + ".txt";
		System.out.println("output");
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filename), "utf-8"))) {
			for (Pilot pilot : pilots) {
				for (String line : pilot.generateReport()) {
					writer.write(line + newline);
				}
				writer.write(newline);
			}
		} catch (IOException ex) {
			return null;
		}
		System.out.println("Done");
		return filename;
	}

	public static String getCurrentTimeStamp() {
		/*
		 * Source :
		 * http://stackoverflow.com/questions/1459656/how-to-get-the-current
		 * -time-in-yyyy-mm-dd-hhmisec-millisecond-format-in-java
		 */
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}
	
	public String getFilename(){
		return filename;
	}
}
