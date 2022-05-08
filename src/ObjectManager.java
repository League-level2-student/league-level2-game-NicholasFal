import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
Rocketship rocket;
UFO ufo;
ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
ArrayList<Meteor> meteors = new ArrayList<Meteor>();
ArrayList<Laser> lasers = new ArrayList<Laser>();
ArrayList<Alien> aliens = new ArrayList<Alien>();
Random ran = new Random();
Random random = new Random();
int score = 0;
ObjectManager(Rocketship rocket, UFO ufo) {
	this.rocket = rocket;
	this.ufo = ufo;
}

void addProjectile(Projectile projectile) {
	projectiles.add(projectile);
}
void addMeteor() {
	meteors.add(new Meteor(random.nextInt(Runner.WIDTH),0,75,75));
}
void addLaser(Laser laser) {
	lasers.add(laser);
}
void addAlien(Alien alien) {
	aliens.add(alien);
}

	void update() {
	for(Projectile projectile : projectiles) {
		projectile.update();
		if(projectile.y <= 0) {
			projectile.isActive = false;
		}
	}
	for(Meteor meteor : meteors) {
		meteor.update();
		if(meteor.y <= 0) {
			meteor.isActive = false;
		}
	}
	for(Laser laser : lasers) {
		laser.update();
		if(laser.y <= 0) {
			laser.isActive = false;
		}
	}
	for(Alien alien : aliens) {
		alien.update();
		alien.rocketX = rocket.x;
		if(alien.y <= 0) {
			alien.isActive = false;
		}
	}
	rocket.update();
	if(rocket.shooting && !rocket.shootingCooldown) {
		addProjectile(rocket.getProjectile());
	}
	if(rocket.isActive) {
	checkCollision();
	purgeObjects();
	}
}
	void draw(Graphics g) {
		rocket.draw(g);
		ufo.draw(g);
		for(Projectile projectile : projectiles) {
			projectile.draw(g);
		}
		for(Meteor meteor : meteors) {
			meteor.draw(g);
		}
		for(Laser laser : lasers) {
			laser.draw(g);
		}
		for(Alien alien : aliens) {
			alien.draw(g);
		}
	}
	void purgeObjects() {
		for(int i = projectiles.size() - 1; i >= 0; i--) {
			if (!projectiles.get(i).isActive) {
				projectiles.remove(i);
			}
		}
		for(int i = meteors.size() - 1; i >= 0; i--) {
			if(!meteors.get(i).isActive) {
				meteors.remove(i);
			}
		}
		for(int i = lasers.size() - 1; i >= 0; i--) {
			if(!lasers.get(i).isActive) {
				lasers.remove(i);
			}
		}
		for(int i = aliens.size() - 1; i >= 0; i--) {
			if(!aliens.get(i).isActive) {
				aliens.remove(i);
			}
		}
		
	}
	void checkCollision() {
		//for(Alien alien: aliens) {
			//if(rocket.collisionBox.intersects(alien.collisionBox)) {
				//alien.isActive = false;
				//rocket.isActive = false;
		//	}
			for(Projectile projectile: projectiles) {
				if(ufo.collisionBox.intersects(projectile.collisionBox)) {
					projectile.isActive = false;
					System.out.println("ufo has been hit");
					if(ufo.ufoHP == 1) {
						ufo.isActive = false;
					} else {
					ufo.ufoHP--;
					score++;
					}
				}
		}
			for(Meteor meteor : meteors) {
				if(rocket.collisionBox.intersects(meteor.collisionBox)) {
					meteor.isActive = false;
					System.out.println("you have been hit with a meteor");
					if(rocket.rocketHP < 6) {
						rocket.isActive = false;
					} else {
						rocket.rocketHP-= 5;
					}
				}
			}
			for(Laser laser : lasers) {
				if(rocket.collisionBox.intersects(laser.collisionBox)) {
					laser.isActive = false;
					System.out.println("you have been hit with a laser");
					if(rocket.rocketHP < 2) {
						rocket.isActive = false;
					} else {
						rocket.rocketHP -= 1;
					}
				}
			}
			for(Alien alien : aliens) {
				if(rocket.collisionBox.intersects(alien.collisionBox)) {
					System.out.println("you have been attacked by an alien");
					if(rocket.rocketHP < 2) {
						rocket.isActive = false;
					} else if(!rocket.invincible){
						rocket.rocketHP -= aliens.size();
						rocket.startInvincibility();
					}
				}
				for(Projectile projectile :projectiles) {
					if(alien.collisionBox.intersects(projectile.collisionBox)) {
						projectile.isActive = false;
						if(alien.alienHP < 2) {
							alien.isActive = false;
						} else {
							alien.alienHP--;
						}
					}
				}
			}
	}
	public int getScore() {
		return score;
		}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == GamePanel.meteorSpawn) {
		addMeteor();
		} 
		if(arg0.getSource() == GamePanel.laserSpawn){
			addLaser(ufo.getLaser());
		}
		if(arg0.getSource() == GamePanel.alienSpawn) {
			addAlien(ufo.getAlien());
		}
	}
}
