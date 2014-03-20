package org.leggy.btc.recruitment.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.leggy.btc.recruitment.model.Model;
import org.leggy.btc.recruitment.view.View;

public class OpenReportListener implements ActionListener {

	private View view;
	private Model model;
	
	private String newline = System.getProperty("line.separator");

	public OpenReportListener(View view, Model model) {
		this.view = view;
		this.model = model;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String filename = model.getFilename();
		if(filename != null){
			ProcessBuilder pb = new ProcessBuilder("Notepad.exe", filename);
			try {
				pb.start();
			} catch (IOException e) {
				printConsole("[Error] Cannot open file in Notepad.");

			}
		}
	}
	
	
	private void printConsole(String message){
		view.console(message + newline);
		
	}
	
	


}
