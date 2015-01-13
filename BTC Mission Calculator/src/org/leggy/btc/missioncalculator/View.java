package org.leggy.btc.missioncalculator;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class View extends JPanel {

	private static final long serialVersionUID = 8104448083243729041L;

	public static final Dimension size = new Dimension(960, 640);

	private JLabel key;
	private JLabel code;
	private JTextField keyField;
	private JTextField codeField;
	private JButton generate;
	private JButton open;
	private JTextArea console;

	public View() {
		this.setMinimumSize(size);
		this.setSize(size);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setLayout(new GridLayout(0, 1));
		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		JPanel keyPanel = new JPanel();
		key = new JLabel("Key ID:");
		// key.set
		keyField = new JTextField(20);
		keyPanel.setLayout(new GridLayout(0, 1) /*FlowLayout()*/);
		keyPanel.add(key);
		keyPanel.add(keyField);

		JPanel codePanel = new JPanel();
		code = new JLabel("Verification Code:");
		codeField = new JTextField(42);
		codePanel.setLayout(new GridLayout(0, 1) /*FlowLayout()*/);
		codePanel.add(code);
		codePanel.add(codeField);

		JPanel generatePanel = new JPanel();
		generate = new JButton("Generate Report");
		generatePanel.add(generate);
		
		JPanel openPanel = new JPanel();
		open = new JButton("Open in Notepad");
		generatePanel.add(open);
		
		this.add(keyPanel);
		this.add(codePanel);

		
		console = new JTextArea(10, 40);
		JScrollPane consolePane = new JScrollPane(console);
		console.setFont(new Font("Courier New", Font.PLAIN, 13));
		console.setEditable(false);
		//consolePane.setVerticalScrollBarPolicy(JScrollPane.BOTTOM_ALIGNMENT);
		//DefaultCaret caret = (DefaultCaret)console.getCaret();
		//caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		//this.add(new JSeparator(SwingConstants.HORIZONTAL));
		//this.add(console);
		this.add(consolePane);

		this.add(generatePanel);
		this.add(openPanel);
	}

	public String getKey() {
		return keyField.getText();
	}

	public String getCode() {
		return codeField.getText();
	}

	public void setGenerateListener(GenerateReportListener listener) {
		generate.addActionListener(listener);
	}
	
	public void setOpenListener(OpenReportListener listener) {
		open.addActionListener(listener);
	}
	
	public void console(String line){
		console.append(line);
	}

}
