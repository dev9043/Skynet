package com.skynet.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class LoginProcessor implements Runnable {

	private boolean isStartLoginProcess;

	private JProgressBar progressBar;

	private JComboBox<String> uTypeComboBox;

	private JTextField userIdTxtField;

	private JPasswordField passwordTxtField;

	private Connection dbConnectin;

	private Statement statement;

	private ResultSet resultSet;

	public void setStartLoginProcess(boolean isStartLoginProcess) {
		this.isStartLoginProcess = isStartLoginProcess;
	}

	public LoginProcessor(JProgressBar progressBar, JComboBox<String> uTypeComboBox, JTextField userIdTxtField,
			JPasswordField passwordTxtField) {
		
		this.progressBar = progressBar;

		this.uTypeComboBox = uTypeComboBox;

		this.userIdTxtField = userIdTxtField;

		this.passwordTxtField = passwordTxtField;
		
		checkDbConnecton();
	}

	@Override
	public void run() {
		try {
			while (isStartLoginProcess) {
				if (progressBar.getValue() < 100) {
					progressBar.setValue(progressBar.getValue() + 5);
					Thread.sleep(200);
				} else {
					statement = dbConnectin.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					resultSet = statement.executeQuery("select * from user_detail");
					boolean flag1 = false;
					String usertype = "";
					String userid = "";
					String upass = "";
					while (resultSet.next()) {
						usertype = resultSet.getString(1);
						userid = resultSet.getString(2);
						upass = resultSet.getString(3);
						if (usertype.equalsIgnoreCase("" + uTypeComboBox.getSelectedItem())
								&& userid.equals(userIdTxtField.getText())
								&& upass.equals(new String(passwordTxtField.getPassword()))) {
							flag1 = true;
							break;
						}
					}
					if (flag1) {
						JOptionPane.showMessageDialog(null, "Welcome", "LoginWindow", 1);
						// Parent parent = new Parent();
						isStartLoginProcess = false;
						// dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid User Type/Password", "LoginWindow", 0);
						progressBar.setValue(0);
						isStartLoginProcess = false;
					}
				}
			}
		} catch (InterruptedException ie) {
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Unable To Create Connection Object:" + e.getMessage(), "LoginWindow",
					0);
		}
	}

	/*
	 * private void checkDbConnecton() { // dbConnectin =
	 * ConnectionFactory.getConnection(); if (dbConnectin == null) { //
	 * JOptionPane.showMessageDialog(this, "Unable to Load Driver", "", // 0);
	 * // System.exit(0); } }
	 */
	private void checkDbConnecton() {

		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConnectin = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "skynet", "skynet");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
