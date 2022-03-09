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
	final int INSTRUCTIONS = -1;
	final int MENU = 0;
	final int EASY = 1;
	final int MEDIUM = 2;
	final int HARD = 3;
	final int INSANE = 4;
	final int GAMEEASY = 5;
	final int GAMEMEDIUM = 6;
	final int GAMEHARD = 7;
	final int GAMEINSANE = 8;
	final int END = 9;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	//Rocketship rocketship = new Rocketship(250, 700, 50, 50);
	//ObjectManager objectmanager = new ObjectManager(rocketship);
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font miniText = new Font("Arial", Font.PLAIN, 15);
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
		if(currentState == INSTRUCTIONS) {
			drawInstructionsState(g);
		} else if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAMEEASY) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		} else if(currentState == EASY) {
			drawEasyState(g);
		} else if(currentState == MEDIUM) {
			drawMediumState(g);
		} else if(currentState == HARD) {
			drawHardState(g);
		} else if(currentState == INSANE) {
			drawInsaneState(g);
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

	void startGameEasy() {
		
	}
	
	void startGameMedium() {
		
	}
	
	void startGameHard() {
		
	}
	
	void startGameInsane() {
		
	}
	void drawInstructionsState(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Instructions", 105, 100);
		g.setFont(miniText);
		g.drawString("Objective: You are a rocket flying through space while trying to kill ", 10, 200);
		g.drawString("a UFO before it kills you. Dodge meteors, lasers, etc.", 10, 250);
		g.drawString("Controls: Press a to move left, d to move right, and space to", 10, 350);
		g.drawString("shoot a bullet.", 10, 400);
	}
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Space Boss", 110, 100);
		g.setFont(miniText);
		g.setColor(Color.WHITE);
		g.drawString("Press LEFT ARROW to view the instructions", 90, 200);
		g.drawString("Press RIGHT ARROW to view Easy Mode", 100, 300);
	}
	void drawEasyState(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Easy Mode", 120, 100);
		g.setFont(miniText);
		g.setColor(Color.WHITE);
		g.drawString("Press LEFT ARROW to view the Menu", 105, 200);
		g.drawString("Press ENTER to play Space Boss in Easy Mode", 77, 300);
		g.drawString("Press RIGHT ARROW to view Medium Mode", 90, 400);
	}
	void drawMediumState(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("Medium Mode", 75, 100);
		g.setFont(miniText);
		g.drawString("Press LEFT ARROW to view Easy Mode", 100, 200);
		g.drawString("Press ENTER to play Space Boss in Medium Mode", 60, 300);
		g.drawString("Press RIGHT ARROW to view Hard Mode", 100, 400);
	}
	void drawHardState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Hard Mode", 115, 100);
		g.setFont(miniText);
		g.drawString("Press LEFT ARROW to view Medium Mode", 90, 200);
		g.drawString("Press ENTER to play Space Boss in Hard Mode", 70, 300);
		g.drawString("Press RIGHT ARROW to view Insane Mode", 90, 400);
	}
	void drawInsaneState(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Insane Mode", 100, 100);
		g.setFont(miniText);
		g.drawString("Press LEFT ARROW to view Hard Mode", 100, 200);
		g.drawString("Press ENTER to play Space Boss in Insane Mode", 70, 300);
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
		} else if (currentState == GAMEEASY) {
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
				if (currentState == EASY) {
					currentState = GAMEEASY;
					startGameEasy();
				} else if(currentState == MEDIUM) {
					currentState = GAMEMEDIUM;
					startGameMedium();
				} else if(currentState == HARD) {
					currentState = GAMEHARD;
					startGameHard();
				} else if(currentState == INSANE) {
					currentState = GAMEINSANE;
					startGameInsane();
				}
				{
				if(currentState == GAMEEASY) {
					alienSpawn.stop();
					}
					currentState++;
				}
			}
			
		}
		
			if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
				if(currentState == INSTRUCTIONS || currentState == MENU || currentState == EASY || currentState == MEDIUM || currentState == HARD) {
					currentState++;
				}
			}
			if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
				if(currentState == MENU || currentState == EASY || currentState == MEDIUM || currentState == HARD || currentState == INSANE) {
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
