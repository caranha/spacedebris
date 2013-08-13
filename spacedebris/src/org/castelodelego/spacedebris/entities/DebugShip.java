package org.castelodelego.spacedebris.entities;

import java.util.Random;

import org.castelodelego.spacedebris.battlescene.BattleContext;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class DebugShip implements FlyingObject {

	Vector2 pos;
	Vector2 dir;
	
	float speed = 3f;	
	float headingDEG = 0; // heading in degrees
	float headingDelta = 15; // maximum heading speed
	int team = -1;
	float size = 8f; // size for the collision box.
	
	boolean alive = true;
	
	public DebugShip(Vector2 startpos)
	{
		pos = startpos.cpy();
		Random dice = new Random();
		
		
		// random initial team and random initial direction;
		team = dice.nextInt(4);
		float randhed = dice.nextFloat()*360;
		dir.x = (float) Math.cos(Math.toRadians(randhed));
		dir.y = (float) Math.sin(Math.toRadians(randhed));
	}
	
	@Override
	public void update(float delta, BattleContext b) {
		Random dice = new Random();
		
		// First = update position:
		float move = speed*delta;
		pos.x = dir.x*move;
		pos.y = dir.y*move;
		
		// Second = Choose new random direction
		float dirdelta = (dice.nextFloat() - 0.5f)*headingDelta*2;
		headingDEG += dirdelta;
		dir.x = (float) Math.cos(Math.toRadians(headingDEG));
		dir.y = (float) Math.sin(Math.toRadians(headingDEG));
		
		// Third = Shoot some bullets
		if (dice.nextFloat() < 0.05)
		{
			SimpleBullet bullet = new SimpleBullet(pos);

			// random bullet direction
			Vector2 bdir = new Vector2();
			float randhed = dice.nextFloat()*360;
			bdir.x = (float) Math.cos(Math.toRadians(randhed));
			bdir.y = (float) Math.sin(Math.toRadians(randhed));

			bullet.setTeam(team);
			bullet.setDir(bdir);
			
			b.addBullet(bullet);			
		}
	}


	@Override
	public Vector2 getPos() {
		return pos;
	}

	@Override
	public Vector2 getDir() {
		return dir;
	}

	@Override
	public int getTeam() {
		return team;
	}

	@Override
	public Rectangle[] getCollisionRectangles() {
		Rectangle ret[] = new Rectangle[1];
		ret[0] = new Rectangle(pos.x - (size/2),pos.y - (size/2),size,size); // centering the box around "pos"
		return ret;
	}

	@Override
	public void setPos(Vector2 pos) {
		this.pos = pos.cpy();
	}

	@Override
	public void setDir(Vector2 dir) {
		this.dir = dir.cpy();
	}

	@Override
	public void setTeam(int t) {
		team = t;
	}

	@Override
	public boolean testCollision(FlyingObject f) {
		Rectangle obj[] = f.getCollisionRectangles();
		Rectangle me = this.getCollisionRectangles()[0];
		
		if (Intersector.overlaps(me, obj[0])) // intersects with coarse rectangle;
		{
			if (obj.length > 1)
			{
				for (int i = 1; i < obj.length; i++)
					if (Intersector.overlaps(me, obj[i]))
					{
						team = f.getTeam();
						return true; // intersects with at least one subrectangle						
					}
				return false; // intersects with no subrectangle
			}
			else
			{
				team = f.getTeam();
				return true; // object with a single rectangle, collided;
			}
		}
		else
			return false; // did not collide;
	}

	@Override
	public boolean isDead() {
		return alive;
	}


	@Override
	public void kill() {
		alive = false;
	}

}
