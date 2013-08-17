package org.castelodelego.spacedebris.battleentities;

import org.castelodelego.spacedebris.battlecomponents.ComponentCollBox;
import org.castelodelego.spacedebris.battlecomponents.ComponentDirection;
import org.castelodelego.spacedebris.battlecomponents.ComponentInterface;
import org.castelodelego.spacedebris.battlecomponents.ComponentKill;
import org.castelodelego.spacedebris.battlecomponents.ComponentPosition;
import org.castelodelego.spacedebris.battlecomponents.ComponentRender;
import org.castelodelego.spacedebris.battlecomponents.ComponentTrigger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


/**
 * This static class creates a variety of standard entities;
 * @author caranha
 *
 */
public class EntityFactory {

	
	static public Entity createBullet(Vector2 pos, Vector2 dir, float size, float timetodie)
	{
		Entity ret = new Entity();

		ret.addComponent(new ComponentPosition(pos.x, pos.y));
		ret.addComponent(new ComponentDirection(dir.x, dir.y));
		
		ret.addComponent(new ComponentCollBox(size, size, pos));

		// TODO: Replace color with team appropriate values
		ret.addComponent(new ComponentRender(ComponentRender.TYPE_WIREBOX, Color.RED, ""));
		
		ComponentInterface[] add = {new ComponentKill()};
		ret.addComponent(new ComponentTrigger(timetodie,add,null));
		
		// TODO: Add hit effect
		
		return ret;
	}
	
	
}
