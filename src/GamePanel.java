import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int EASY = 1;
	final int MEDIUM = 2;
	final int HARD = 3;
	final int INSANE = 4;
	final int GAME = 5;
	final int END = 6;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	//Rocketship rocketship = new Rocketship(250, 700, 50, 50);
	//ObjectManager objectmanager = new ObjectManager(rocketship);
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font miniText = new Font("Arial", Font.PLAIN, 10);
	int currentState = MENU;
	Timer frameDraw;
	Timer alienSpawn;

	GamePanel() {
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
			loadImage("space.png");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		} else if(currentState == EASY) {
				drawEasyState(g);
		
			}
		}
	

	void updateMenuState() {

	}

	void updateGameState() {
		//objectmanager.update();
		//if(!rocketship.isActive) {
		//	currentState = END;
		//}
	}

	void updateEndState() {

	}

	void startGame() {
		
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Platform Dodger", 50, 100);
		g.setFont(miniText);
		g.setColor(Color.YELLOW);
		g.drawString("Press RIGHT ARROW to View Difficulties", 150, 300);
		g.drawString("Press SPACE for instructions", 180, 400);
	}
	void drawEasyState(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Easy Mode", 50, 100);
		g.setFont(miniText);
		g.setColor(Color.YELLOW);
		g.drawString("Press LEFT ARROW to View Menu", 150, 300);
		g.drawString("Press RIGHT ARROW to View Medium Mode", 180, 400);
	}
	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, Runner.WIDTH, Runner.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		}
		//objectmanager.draw(g);
		g.setFont(miniText);
		g.setColor(Color.WHITE);
		//g.drawString(String.valueOf(objectmanager.getScore()), 100, 100);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Game Over", 100, 100);
		g.setFont(miniText);
		g.setColor(Color.YELLOW);
		//g.drawString("You killed "  + objectmanager.getScore() + " enemies", 200, 300);
		g.drawString("Press ENTER to restart", 180, 400);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		// System.out.println("Action");
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if(currentState == END) {
				//rocketship = new Rocketship(250, 700, 50, 50);
			}
			if (currentState == END) {
				currentState = MENU;
			} else {
				if (currentState == MENU) {
					currentState = GAME;
					startGame();
				} else {
				if(currentState == GAME) {
					alienSpawn.stop();
					}
					currentState++;
				}
			}
			
		}
			if (currentState == GAME) {
				
			}
			if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
				if(currentState == MENU || currentState == EASY || currentState == MEDIUM || currentState == HARD) {
					currentState++;
				}
			}
			if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
				if(currentState == EASY || currentState == MEDIUM || currentState == HARD || currentState == INSANE) {
					currentState--;
				}
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

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
