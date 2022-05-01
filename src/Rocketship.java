import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Rocketship extends GameObject implements ActionListener{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Timer invincibility = new Timer(1000, this);
	Boolean invincible = false;
	int rocketHP;
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
		if (needImage) {
		    loadImage ("rocketship.png");
		}
	}
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
        if (gotImage) {
        	g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
        }
	}
	public void up() {
		y -= speed;
	}
	public void right() {
		x += speed;
	}
	public void down() {
		y += speed;
	}
	public void left() {
		x -= speed;
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	public Projectile getProjectile() {
        return new Projectile(x+width/2, y, 10, 10);
}
	public void startInvincibility() {
		invincibility.start();
		invincible = true;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		invincibility.stop();
		invincible = false;
	} 
	
}
