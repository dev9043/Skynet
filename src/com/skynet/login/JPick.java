package com.skynet.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.skynet.utility.Constants;

public class JPick extends JPanel {
	
	private static final long serialVersionUID = -2389342749272285824L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		MediaTracker track = new MediaTracker(this);
		Image img = Toolkit.getDefaultToolkit().getImage(Constants.RESOURCE_IMAGES_SHOP_JPG);
		track.addImage(img, 0);
		try {
			track.waitForID(0);
		} catch (InterruptedException e) {
		}
		g.drawImage(img, 0, 0, 795, 490, this);
		g.setColor(Color.yellow);
		g.setFont(new Font("Showcard Gothic", Font.BOLD, 60));
		g.drawString("SKYNET COMPUTER SHOP", 0, 50);
		g.drawString("AUTOMATION SYSTEM", 40, 120);
	}

}
