package org.leggy.btc.recruitment;




import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.leggy.btc.recruitment.controller.GenerateReportListener;
import org.leggy.btc.recruitment.controller.OpenReportListener;
import org.leggy.btc.recruitment.model.Model;
import org.leggy.btc.recruitment.view.View;

public class Launcher {

	private static final double version = 0.2;

	private static final String title = "BTC Recruitment Character Report | Version "
			+ version;
	private static ImageIcon icon;

	@SuppressWarnings("unused")
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
			
			if(icon != null){
				frame.setIconImage(icon.getImage());
				// Sets the icon image of the window.
			}

			
		}
	}

}
