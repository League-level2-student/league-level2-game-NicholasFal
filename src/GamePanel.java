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
	final int VICTORY = 10;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Rocketship rocketship = new Rocketship(250, 700, 50, 50);
	UFO ufo = new UFO(10, 400, 100, 100);
	ObjectManager objectmanager = new ObjectManager(rocketship, ufo);
	Font titleFont = new Font("Arial", Font.PLAIN, 48);
	Font miniText = new Font("Arial", Font.PLAIN, 15);
	Font healthFont = new Font("Impact", Font.PLAIN, 20);
	int currentState = MENU;
	int rocketshipHP;
	Timer frameDraw;
	// Timer alienSpawn;
	static Timer meteorSpawn;
	static Timer laserSpawn;
	static Timer alienSpawn;

	GamePanel() {
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
			loadImage("space.png");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == INSTRUCTIONS) {
			drawInstructionsState(g);
		} else if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAMEEASY) {
			drawGameState(g);
		} else if (currentState == GAMEMEDIUM) {
			drawGameState(g);
		} else if (currentState == GAMEHARD) {
			drawGameState(g);
		} else if (currentState == GAMEINSANE) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		} else if (currentState == EASY) {
			drawEasyState(g);
		} else if (currentState == MEDIUM) {
			drawMediumState(g);
		} else if (currentState == HARD) {
			drawHardState(g);
		} else if (currentState == INSANE) {
			drawInsaneState(g);
		} else if (currentState == VICTORY) {
			drawVictoryState(g);
		}

	}

	void updateMenuState() {

	}

	void updateGameState() {
		objectmanager.update();
		ufo.movement();
		if (!rocketship.isActive) {
			currentState = END;
			meteorSpawn.stop();
			laserSpawn.stop();
		} else if (!ufo.isActive) {
			if(currentState != GAMEEASY) {
				alienSpawn.stop();
			}
			currentState = VICTORY;
			meteorSpawn.stop();
			laserSpawn.stop();
		}
	}

	void updateEndState() {

	}

	void startGameEasy() {
		objectmanager.update();
		rocketship.rocketHP = 50;
		ufo.ufoHP = 50;
		meteorSpawn = new Timer(2500, objectmanager);
		laserSpawn = new Timer(750, objectmanager);
		meteorSpawn.start();
		laserSpawn.start();
	}

	void startGameMedium() {
		objectmanager.update();
		rocketship.rocketHP = 50;
		ufo.ufoHP = 100;
		meteorSpawn = new Timer(2250, objectmanager);
		laserSpawn = new Timer(500, objectmanager);
		alienSpawn = new Timer(25000, objectmanager);
		meteorSpawn.start();
		laserSpawn.start();
		alienSpawn.start();
	}

	void startGameHard() {
		objectmanager.update();
		rocketship.rocketHP = 50;
		ufo.ufoHP = 150;
		meteorSpawn = new Timer(2000, objectmanager);
		laserSpawn = new Timer(350, objectmanager);
		alienSpawn = new Timer(20000, objectmanager);
		meteorSpawn.start();
		laserSpawn.start();
		alienSpawn.start();
	}

	void startGameInsane() {
		objectmanager.update();
		rocketship.rocketHP = 50;
		ufo.ufoHP = 200;
		meteorSpawn = new Timer(1750, objectmanager);
		laserSpawn = new Timer(225, objectmanager);
		alienSpawn = new Timer(17500, objectmanager);
		meteorSpawn.start();
		laserSpawn.start();
		alienSpawn.start();
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
		g.drawString("Controls: Use the arrow keys to move left and right, and space to", 10, 350);
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
		g.setColor(Color.WHITE);
		g.setFont(healthFont);
		g.drawString("Your Health: " + rocketship.rocketHP, 10, 33);
		g.drawString("UFO Health: " + ufo.ufoHP, 330, 33);
		objectmanager.draw(g);
		g.setFont(miniText);
		g.setColor(Color.WHITE);
		// g.drawString(String.valueOf(objectmanager.getScore()), 100, 100);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Game Over", 100, 100);
		g.setFont(miniText);
		g.setColor(Color.YELLOW);
		// g.drawString("You killed " + objectmanager.getScore() + " enemies", 200,
		// 300);
		g.drawString("The UFO had " + ufo.ufoHP + " HP remaining.", 130, 200);
		g.drawString("Press ENTER to restart", 150, 300);
	}

	void drawVictoryState(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.GRAY);
		g.drawString("You Won!", 120, 100);
		g.setFont(miniText);
		g.setColor(Color.GRAY);
		g.drawString("You had " + rocketship.rocketHP + " HP remaining.", 133, 200);
		g.drawString("Press ENTER to restart", 150, 300);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAMEEASY || currentState == GAMEMEDIUM || currentState == GAMEHARD || currentState == GAMEINSANE) {
			//System.out.println("game state has been updated");
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
			if (currentState == END || currentState == VICTORY) {
				rocketship = new Rocketship(250, 700, 50, 50);
				ufo = new UFO(10, 400, 100, 100);
				objectmanager = new ObjectManager(rocketship, ufo);
			}
			if (currentState == END || currentState == VICTORY) {
				currentState = MENU;
			} else {
				if (currentState == EASY) {
					currentState = GAMEEASY;
					startGameEasy();
				} else if (currentState == MEDIUM) {
					currentState = GAMEMEDIUM;
					startGameMedium();
				} else if (currentState == HARD) {
					currentState = GAMEHARD;
					startGameHard();
				} else if (currentState == INSANE) {
					currentState = GAMEINSANE;
					startGameInsane();
				}
				else {
				// if(currentState == GAMEEASY) {
				// alienSpawn.stop();
				// }
				currentState++;
				}
			}
			System.out.println(currentState);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
				rocketship.shooting = true;
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (currentState == INSTRUCTIONS || currentState == MENU || currentState == EASY || currentState == MEDIUM
					|| currentState == HARD) {
				currentState++;
			} else if (currentState == GAMEEASY || currentState == GAMEMEDIUM || currentState == GAMEHARD
					|| currentState == GAMEINSANE) {
				rocketship.right = true;
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			if (currentState == MENU || currentState == EASY || currentState == MEDIUM || currentState == HARD
					|| currentState == INSANE) {
				currentState--;
			} else if (currentState == GAMEEASY || currentState == GAMEMEDIUM || currentState == GAMEHARD
					|| currentState == GAMEINSANE) {
				rocketship.left = true;
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
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			rocketship.left = false;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			rocketship.right = false;
		}
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			rocketship.shooting = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
