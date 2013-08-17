package org.castelodelego.spacedebris.battlesystems;


import java.util.Iterator;

import org.castelodelego.spacedebris.battlecomponents.ComponentType;
import org.castelodelego.spacedebris.battleentities.Entity;
import org.castelodelego.spacedebris.battleentities.EntityManager;

/**
 * This system removes entities from the entity manager, 
 * It also does anything that needs to be done to an entity before removing it (it usually does NOT postpone death, though)
 * 
 * @author caranha
 *
 */
public class KillSystem {
	
	public KillSystem()
	{
		
	}
	
	public void update(float dt, EntityManager m)
	{
		Iterator<Entity> it = m.iterator();
		
		while (it.hasNext())
		{
			Entity e = it.next();
			

			
			if (e.getComponentByType(ComponentType.COMP_KILL) != null)
			{
				// TODO: Do anything else that needs to be done before removing the entity;
				it.remove();
			}
		}
				
	}
}
