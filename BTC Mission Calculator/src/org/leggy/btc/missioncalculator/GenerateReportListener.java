package org.leggy.btc.missioncalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.leggy.btc.missioncalculator.exceptions.AccessMaskException;
import org.leggy.btc.missioncalculator.exceptions.AccountKeyException;
import org.leggy.btc.missioncalculator.exceptions.ConnectionException;
import org.leggy.eveapi.resources.MissionReportException;

public class GenerateReportListener implements ActionListener {

	private View view;
	private Model model;

	private String newline = System.getProperty("line.separator");

	public GenerateReportListener(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String key = view.getKey();
		String code = view.getCode();

		/*
		 * Validation
		 */
		if (key == null || key.equals("")) {
			printConsole("[Error] Invalid Key.");
			return;
		}

		if (code == null || code.equals("")) {
			printConsole("[Error] Invalid Verification Code.");
			return;
		}

		int keyID = 0;
		try {
			keyID = Integer.parseInt(key);
		} catch (NumberFormatException e) {
			printConsole("[Error] Key non-numeric.");
			return;
		}

		/*
		 * Processing
		 */

		try {
			model.generateReport(keyID, code);
			printConsole("[Info] Report generated: " + model.getFilename());
		} catch (MissionReportException e) {
			printConsole("[Error] API invalid or could not connect to server.");
		}
	}

	private void printConsole(String message) {
		view.console(message + newline);

	}

}
