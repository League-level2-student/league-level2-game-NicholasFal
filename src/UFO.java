import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class UFO extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	int ufoHP;
	UFO(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
		if (needImage) {
		    loadImage ("ufo.png");
		}
	}
	void update() {
		super.update();
	}
	void movement() {
		if(y == 400 && x < 390) {
		x+=1;
		} else if(x > 200) {
			x-=1;
			y-=1;
		} else if(x<=200) {
			x-=1;
			y+=1;
		}
		update();
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
	public Laser getLaser() {
		return new Laser(x+width/2, y, 20, 50);
	}
	public Alien getAlien() {
		return new Alien(x+width/2, y, 80, 80, 12);
	}
}
