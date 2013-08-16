package org.castelodelego.spacedebris.battlescene;

import org.castelodelego.spacedebris.Constants;
import org.castelodelego.spacedebris.battlecomponents.ComponentCollBox;
import org.castelodelego.spacedebris.battlecomponents.ComponentRender;
import org.castelodelego.spacedebris.battleentities.Entity;
import org.castelodelego.spacedebris.battleentities.EntityManager;
import org.castelodelego.spacedebris.battlesystems.RenderSystem;

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

	RenderSystem renderer;
	EntityManager entityman;
	
	public BattleManager()
	{
		entityman = new EntityManager();
		
		// TODO: These values are actually how much of the battle space I am seeing. They don't need to be the same as the size of my screen (but should be the same proportion)
		renderer = new RenderSystem(Constants.SCREEN_W,Constants.SCREEN_H);
	}
	
	/**
	 * Dummy initialization for testing purposes
	 */
	public void debugInit()
	{
		Entity a;
		a = new Entity();
		a.addComponent(new ComponentCollBox(10, 10, new Vector2(200,100)));
		a.addComponent(new ComponentRender(ComponentRender.TYPE_WIREBOX, Color.WHITE, ""));
		entityman.addEntity(a);
		
		a = new Entity();
		a.addComponent(new ComponentCollBox(10, 10, new Vector2(100,100)));
		entityman.addEntity(a);
		
		a = new Entity();
		a.addComponent(new ComponentCollBox(10, 10, new Vector2(100,200)));
		a.addComponent(new ComponentRender(ComponentRender.TYPE_WIREBOX, Color.GREEN, ""));
		entityman.addEntity(a);
		
		a = new Entity();
		a.addComponent(new ComponentCollBox(10, 10, new Vector2(200,200)));
		entityman.addEntity(a);
		
		a = new Entity();
		a.addComponent(new ComponentCollBox(10, 10, new Vector2(150,150)));
		a.addComponent(new ComponentRender(ComponentRender.TYPE_WIREBOX, Color.RED, ""));
		entityman.addEntity(a);
	}
	
	public void update(float dt)
	{
		// Update the game logic
		
		
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
