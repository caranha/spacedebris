package org.castelodelego.spacedebris.entities;

import org.castelodelego.spacedebris.battlescene.BattleContext;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * This interface controls the interaction of ships, bullets, power ups and 
 * other flying/collidable objects with the game.
 * 
 * @author caranha
 *
 */

public interface FlyingObject {

	/**
	 * Update the state of this flying object
	 * @param delta
	 * @param b
	 */
	public void update(float delta, BattleContext b);
	
	/*---------*/
	/* GETTERS */
	/*---------*/
	/**
	 * Where the object is right now;
	 */
	public Vector2 getPos();
	
	/**
	 * Where the object is planning to at the next update
	 */
	public Vector2 getDir();

	/**
	 * Gets the team value of the object. -1 equals to no team (does not interact with anyone)
	 * @return
	 */
	public int getTeam();
	
	/**
	 * Get a list of rectangles from the Flying object.
	 * @return Collision rectangle list. If the size is 1, this is the collision rectangle. If the size is bigger than 1, 
	 * [0] is the coarse collision rectangle, followed by more detailed collision rectangles.
	 */
	public Rectangle[] getCollisionRectangles();
	
	
	/*---------*/
	/* SETTERS */
	/*---------*/
	
	/**
	 * Clamps the object's position to some value.
	 * @param pos
	 */
	public void setPos(Vector2 pos);
	/**
	 * Clamps the object direction to some value.
	 * @param dir
	 */
	public void setDir(Vector2 dir);
	
	/**
	 * Objects won't collide with other objects of the same team (-1 will not interact with anyone);
	 * @param t
	 */
	public void setTeam(int t);
	
	
	/*--------*/
	/* OTHERS */
	/*--------*/
	
	/**
	 * Collision test. Tests if this objects collides with f, and if it does, act as appropriate.
	 * TODO: Might neet to work on this to make ship-ship collisions (has to return point of collision to colliding ship)
	 * @return True if a collision took place
	 */
	public boolean testCollision(FlyingObject f);
	
	/**
	 * Test if this flying object is dead and ready to be removed from the game
	 * @return
	 */
	public boolean isDead();
	
	/**
	 * Set this object to start its death sequence.
	 */
	public void kill();
}
