package org.castelodelego.spacedebris.battlescene;

import java.util.Random;

import org.castelodelego.spacedebris.Constants;
import org.castelodelego.spacedebris.battlecomponents.ComponentCollBox;
import org.castelodelego.spacedebris.battlecomponents.ComponentDirection;
import org.castelodelego.spacedebris.battlecomponents.ComponentPosition;
import org.castelodelego.spacedebris.battlecomponents.ComponentRender;
import org.castelodelego.spacedebris.battleentities.Entity;
import org.castelodelego.spacedebris.battleentities.EntityFactory;
import org.castelodelego.spacedebris.battleentities.EntityManager;
import org.castelodelego.spacedebris.battlesystems.KillSystem;
import org.castelodelego.spacedebris.battlesystems.MoveSystem;
import org.castelodelego.spacedebris.battlesystems.RenderSystem;
import org.castelodelego.spacedebris.battlesystems.TriggerSystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;




/**
 * 
 * The battle manager contains all of the information needed to create and run a battle.
 * It handles updates to the battle state, rendering calls from the scene, and input information;
 * 
 * It will contain calls to all the necessary systems and the entity manager.
 * 
 * @author caranha
 *
 */
public class BattleManager {

	// systems
	RenderSystem renderer;
	MoveSystem mover;
	KillSystem killer;
	TriggerSystem timer;
	
	// entities
	EntityManager entityman;
	
	
	public BattleManager()
	{
		entityman = new EntityManager();
		
		
		mover = new MoveSystem();
		killer = new KillSystem();
		timer = new TriggerSystem();
		
		
		renderer = new RenderSystem(Constants.SCREEN_W,Constants.SCREEN_H); // TODO: Decide appropriate values for these Width/Height constants;
	}
	
	/**
	 * Create a random number of bullets
	 */
	public void debugInit(int n)
	{
		Vector2 pos = new Vector2();
		Vector2 dir = new Vector2();
		Random dice = new Random();
		for (int i = 0; i < n; i++)
		{
			pos.x = dice.nextInt(200)+100;
			pos.y = dice.nextInt(300)+100;
			float orient = (float) (dice.nextFloat()*Math.PI*2);
			float speed = (dice.nextFloat()*30)+1;
			dir.x = (float) (Math.cos(orient)*speed);
			dir.y = (float) (Math.sin(orient)*speed);
			entityman.addEntity(EntityFactory.createBullet(pos, dir, dice.nextInt(10)+5, dice.nextFloat()*5+0.5f));
		}
		
	}
	
	public void update(float dt)
	{
		// Update the game logic
		mover.update(dt, entityman);
		timer.update(dt, entityman);
		killer.update(dt, entityman);
		
	}
	
	/**
	 * Renders everything that needs rendering in the battlefield. Assumes that screen clearing, 
	 * camera positioning and interface drawing happens outside of this call.
	 * @param dt
	 */
	public void render(float dt)
	{
		renderer.RenderEntities(entityman);
	}
	
}
