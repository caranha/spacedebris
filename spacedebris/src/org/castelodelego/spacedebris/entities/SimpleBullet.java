package org.castelodelego.spacedebris.entities;

import org.castelodelego.spacedebris.battlescene.BattleContext;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * This is a simple bullet, it has no fancy information such as damage ammount.
 * It has a position, a speed and direction, and fly straight ahead.
 * It also has a lifetime.
 * @author caranha
 *
 */
public class SimpleBullet implements FlyingObject {

	Vector2 pos;
	Vector2 dir;
	
	// TODO: move the bullet constants to a "constants" object.
	float speed = 10f;	
	float lifetime = 5f; // default lifetime of 5 seconds.
	int team = -1;
	float size = 1f; // size for the collision box.
	
	/**
	 * Creates a simple bullet that will go nowhere;
	 */
	public SimpleBullet(Vector2 p)
	{
		pos = p.cpy();
		dir = new Vector2();
	}
	
	
	@Override
	public void update(float delta, BattleContext b) {
		float move = speed*delta;
		pos.x += dir.x*move;
		pos.y += dir.y*move;
		lifetime -= delta;
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

	public float getSize()
	{
		return size;
	}
	
	public float getLifeTime()
	{
		return lifetime;
	}
	
	public float getSpeed()
	{
		return speed;
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
	
	public void setSize(float s)
	{
		size = s;
	}

	public void setLifeTime(float t)
	{
		lifetime = t;
	}
	
	public void setSpeed(float s)
	{
		speed = s;
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
						kill();
						return true; // intersects with at least one subrectangle
					}
				return false; // intersects with no subrectangle
			}
			else	
			{
				kill();
				return true; // object with a single rectangle, collided;
			}
		}
		else
			return false; // did not collide;
		
	}

	@Override
	public boolean isDead() {
		return lifetime > 0;
	}

	@Override
	public void kill() {
		lifetime = 0;		
	}

}
