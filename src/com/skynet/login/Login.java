package com.skynet.login;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class Login extends JFrame implements Runnable, ActionListener {

	private static final long serialVersionUID = -7680761455541209253L;

	private JLabel loginWindowLabel, userIdLabel, passwordLabel, userTypeLabel;
	private JTextField userIdTxtField;
	private JPasswordField passwordTxtField;
	private JPick mediaTypeContentLoader;
	private JComboBox<String> uTypeComboBox;
	private JButton loginButton, cencelButton;

	private JProgressBar progressBar;

	public Login() {
		
		Container container = this.getContentPane();

		initilizeCommonValues();

		initilizeLoginWindowLabel();

		initilizeUserIdFields();

		initilizePasswordField();

		initilizeUserTypeField();

		initilizeButtons();

		initilizeProgressBar();

		setResizable(false);

		addContentOnContainer(container);

		Thread loginBlinkingThread = new Thread(this);
		loginBlinkingThread.start();
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			try {
				Thread.sleep(500);
				if (i % 2 == 0)
					loginWindowLabel.setVisible(false);
				else
					loginWindowLabel.setVisible(true);
			} catch (InterruptedException ie) {
				JOptionPane.showMessageDialog(null, "interrupted");
			}
		}
	}
	
	private void addContentOnContainer(Container container) {
		container.add(progressBar);
		container.add(loginWindowLabel);
		container.add(userIdLabel);
		container.add(userIdTxtField);
		container.add(passwordLabel);
		container.add(passwordTxtField);
		container.add(userTypeLabel);
		container.add(uTypeComboBox);
		container.add(loginButton);
		container.add(cencelButton);
		container.add(mediaTypeContentLoader);
	}

	private void initilizeCommonValues() {

		setVisible(true);

		setTitle("LOGIN WINDOW");

		setBounds(110, 70, 800, 500);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mediaTypeContentLoader = new JPick();

		//checkDbConnecton();
	}

	private void initilizeLoginWindowLabel() {
		loginWindowLabel = new JLabel("LOGIN WINDOW");
		loginWindowLabel.setBounds(205, 150, 450, 40);
		loginWindowLabel.setForeground(Color.yellow);
		loginWindowLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 40));
	}

	private void initilizeProgressBar() {
		progressBar = new JProgressBar();

		progressBar.setStringPainted(true);
		progressBar.setOpaque(false);
		progressBar.setForeground(Color.green);
		progressBar.setBorderPainted(true);
		// pbr.paintBorder(new Color(155,252,58));
		progressBar.setOrientation(0);
		progressBar.setBounds(185, 370, 370, 20);
	}

	private void initilizeButtons() {
		loginButton = new JButton("Login");
		loginButton.setBounds(250, 405, 100, 25);
		loginButton.setForeground(Color.red);
		loginButton.addActionListener(this);
		loginButton.setMnemonic('u');
		loginButton.setToolTipText("click to continue");

		cencelButton = new JButton("Cancel");
		cencelButton.setForeground(Color.red);
		cencelButton.setBounds(390, 405, 100, 25);
		cencelButton.addActionListener(this);
		cencelButton.setMnemonic('c');
		cencelButton.setToolTipText("click to exit");
	}

	private void initilizeUserTypeField() {
		userTypeLabel = new JLabel("User Type");
		userTypeLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
		userTypeLabel.setBounds(230, 200, 150, 50);
		userTypeLabel.setVisible(true);
		userTypeLabel.setForeground(Color.yellow);

		uTypeComboBox = new JComboBox<String>();
		uTypeComboBox.setBounds(375, 208, 160, 29);
		uTypeComboBox.setToolTipText("USER Type");
		uTypeComboBox.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		uTypeComboBox.addItem("Administrator");
		uTypeComboBox.addItem("Staff");
		uTypeComboBox.addItem("General");
	}

	private void initilizePasswordField() {
		passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
		passwordLabel.setBounds(230, 320, 150, 50);
		passwordLabel.setVisible(true);
		passwordLabel.setForeground(Color.yellow);

		passwordTxtField = new JPasswordField("singh");
		passwordTxtField.setBounds(375, 327, 160, 29);
		passwordTxtField.setToolTipText("PASSWORD");
		passwordTxtField.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		// t2.addKeyListener(this);
	}

	private void initilizeUserIdFields() {

		userIdLabel = new JLabel("USER ID");
		userIdLabel.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
		userIdLabel.setBounds(230, 260, 150, 50);
		userIdLabel.setVisible(true);
		userIdLabel.setForeground(Color.yellow);

		userIdTxtField = new JTextField("Devendra");
		userIdTxtField.setVisible(true);
		userIdTxtField.setBounds(375, 268, 160, 29);
		userIdTxtField.setToolTipText("USER ID");
		userIdTxtField.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
	}

	/*
	 * public void keyPressed(KeyEvent ke) { if(ke.getSource()==t2) {
	 * if(ke.getKeyChar()=='\n') { parent h=new parent(); dispose(); } } }
	 */
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == loginButton) {
			LoginProcessor loginProcessor = new LoginProcessor(progressBar, uTypeComboBox,userIdTxtField,passwordTxtField);
			Thread loginProcessorThread = new Thread(loginProcessor);
			loginProcessor.setStartLoginProcess(true);
			loginProcessorThread.start();
		}
		if (event.getSource() == cencelButton) {
			System.exit(0);
		}
		
		/*
		 * if(ae.getSource()==change) { changepass obj=new changepass(); }
		 */
	   }
	public static void main(String args[]) {
		new Login();
	}
	
}
