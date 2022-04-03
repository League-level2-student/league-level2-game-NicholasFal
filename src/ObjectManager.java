import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
Rocketship rocket;
UFO ufo;
ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
//ArrayList<Alien> aliens = new ArrayList<Alien>();
Random random = new Random();
int score = 0;
ObjectManager(Rocketship rocket, UFO ufo) {
	this.rocket = rocket;
	this.ufo = ufo;
}

void addProjectile(Projectile projectile) {
	projectiles.add(projectile);
}
void addAlien() {
	//aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
}

	void update() {
	//for(Alien alien : aliens) {
		//alien.update();
		//if(alien.y >= LeagueInvaders.HEIGHT) {
		//	alien.isActive = false;
		//}
	//}
	for(Projectile projectile : projectiles) {
		projectile.update();
		if(projectile.y <= 0) {
			projectile.isActive = false;
		}
	}
	rocket.update();
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
	}
	void purgeObjects() {
		//for(int i = aliens.size() - 1; i >= 0; i--) {
			//if(!aliens.get(i).isActive) {
				//aliens.remove(i);
			//}
		//}
		for(int i = projectiles.size() - 1; i >= 0; i--) {
			if (!projectiles.get(i).isActive) {
				projectiles.remove(i);
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
			//}
		}
	}
	public int getScore() {
		return score;
		}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addAlien();
	}
}
