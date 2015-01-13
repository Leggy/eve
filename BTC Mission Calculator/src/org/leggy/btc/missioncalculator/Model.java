package org.leggy.btc.missioncalculator;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.leggy.btc.missioncalculator.exceptions.*;
import org.leggy.eveapi.resources.MissionReportException;
import org.leggy.eveapi.resources.MissionReportGenerator;

public class Model {

	private static String newline = System.getProperty("line.separator");

	private String filename = null;;

	public void generateReport(int keyID, String code)
			throws MissionReportException {

		List<String> report = MissionReportGenerator
				.generateReport(keyID, code);

		this.filename = outputToFile(report);
	}

	private String outputToFile(List<String> report) {
		String filename = "reports/" + getCurrentTimeStamp() + ".txt";
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filename), "utf-8"))) {
			for (String line : report) {
				writer.write(line + newline);
			}
		} catch (IOException ex) {
			return null;
		}
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

	public String getFilename() {
		return filename;
	}
}
