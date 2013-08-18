package org.castelodelego.spacedebris.battleentities;

import org.castelodelego.spacedebris.battlecomponents.ComponentAnimation;
import org.castelodelego.spacedebris.battlecomponents.ComponentCollBox;
import org.castelodelego.spacedebris.battlecomponents.ComponentDirection;
import org.castelodelego.spacedebris.battlecomponents.ComponentInterface;
import org.castelodelego.spacedebris.battlecomponents.ComponentKill;
import org.castelodelego.spacedebris.battlecomponents.ComponentPosition;
import org.castelodelego.spacedebris.battlecomponents.ComponentTrigger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


/**
 * This static class creates a variety of standard entities;
 * @author caranha
 *
 */
public class EntityFactory {

	
	/**
	 * Creates a simple bullet entity
	 */
	static public Entity createBullet(Vector2 pos, Vector2 dir, float timetodie, int size, Color tint)
	{
		Entity ret = new Entity();

		ret.addComponent(new ComponentPosition(pos.x, pos.y));
		ret.addComponent(new ComponentDirection(dir.x, dir.y));
		
		ret.addComponent(new ComponentCollBox(size, size, pos));

		// TODO: Replace color with team appropriate values
		ret.addComponent(new ComponentAnimation("animations/battlespace/bullet", tint));
		
		ComponentInterface[] add = {new ComponentKill()};
		ret.addComponent(new ComponentTrigger(timetodie,add,null));
		
		// TODO: Add hit effect
		
		return ret;
	}
	
	
}
