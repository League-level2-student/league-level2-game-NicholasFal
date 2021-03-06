import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Alien extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	int rocketX;
	int alienHP;
	Alien(int x, int y, int width, int height, int alienHP) {
		super(x, y, width, height);
		speed = 3;
		this.alienHP = alienHP;
		if (needImage) {
		    loadImage ("alien.png");
		}
	}
	void update() {
		if(y<630) {
		y+=speed;
		}
		if(x<rocketX) {
			x+=speed;
		} else {
			x-=speed;
		}
		super.update();
	}
	void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
        if (gotImage) {
        g.drawImage(image, x, y, width, height, null);
        } else {
        	g.setColor(Color.BLUE);
        	g.fillRect(x, y, width, height);
        }	

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

}
