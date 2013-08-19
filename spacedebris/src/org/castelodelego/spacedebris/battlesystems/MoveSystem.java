package org.castelodelego.spacedebris.battlesystems;

import java.util.ArrayList;

import org.castelodelego.spacedebris.battlecomponents.ComponentCollBox;
import org.castelodelego.spacedebris.battlecomponents.ComponentDirection;
import org.castelodelego.spacedebris.battlecomponents.ComponentInterface;
import org.castelodelego.spacedebris.battlecomponents.ComponentPosition;
import org.castelodelego.spacedebris.battlecomponents.ComponentType;
import org.castelodelego.spacedebris.battleentities.Entity;
import org.castelodelego.spacedebris.battleentities.EntityManager;

import com.badlogic.gdx.utils.Array;


/**
 * Simple system that updates the position of all entities that have a position and a direction components.
 * 
 * @author caranha
 *
 */
public class MoveSystem {

	static ComponentType[] filter = {ComponentType.COMP_POS, ComponentType.COMP_DIR};
	
	public MoveSystem()
	{
		
	}
	
	public void update(float dt, EntityManager m)
	{

		ArrayList<Entity> list = m.queryEntities(filter);

		
		Array<ComponentInterface> clist;
		
		ComponentPosition pos;
		ComponentDirection dir;
		ComponentCollBox rect;
		
		for (int i = 0; i < list.size(); i++)
		{
			pos = (ComponentPosition) list.get(i).getComponentByType(ComponentType.COMP_POS).get(0); // one position
			
			clist = list.get(i).getComponentByType(ComponentType.COMP_DIR); // possibly multiple direction vectors -- add them all!
			
			for (int j = 0; j < clist.size; j++)
			{
				dir = (ComponentDirection) clist.get(j);
				pos.pos.x += dir.dir.x*dt;
				pos.pos.y += dir.dir.y*dt;
			}
			
			// special cases for collision box entities
			clist = list.get(i).getComponentByType(ComponentType.COMP_COLLBOX);
			if (clist != null && clist.size > 0)
			{
				rect = (ComponentCollBox) clist.get(0); // assumes only one collision box exists
				rect.box.setCenter(pos.pos);
			}
						
			// TODO: special case for composite entities
		}
		
	}
	
}
