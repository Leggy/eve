package org.leggy.btc.missioncalculator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Launcher {

	/*
	 * TODO: Filename field; Invalid API Response; Format output numbers
	 * (http://docs.oracle.com/javase/tutorial/i18n/format/decimalFormat.html);
	 */

	private static final double version = 0.1;

	private static final String title = "BTC Mission Calculator Report | Version "
			+ version;
	private static ImageIcon icon;

	
	public static void main(String args[]) {
		Frame frame = new Frame(title, version);

	}

	private static class Frame {
		public Frame(String title, double version) {
			View view = new View();
			Model model = new Model();
			view.setGenerateListener(new GenerateReportListener(view, model));
			view.setOpenListener(new OpenReportListener(view, model));

			JFrame frame = new JFrame();
			frame.add(view);
			frame.pack();
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle(title);
			frame.setPreferredSize(View.size);

			icon = new ImageIcon(getClass().getResource("/logo.png"));

			if (icon != null) {
				frame.setIconImage(icon.getImage());
				// Sets the icon image of the window.
			}

		}
	}

}
