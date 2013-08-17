package org.castelodelego.spacedebris.battlesystems;

import java.util.ArrayList;

import org.castelodelego.spacedebris.battlecomponents.ComponentTrigger;
import org.castelodelego.spacedebris.battlecomponents.ComponentType;
import org.castelodelego.spacedebris.battleentities.Entity;
import org.castelodelego.spacedebris.battleentities.EntityManager;

import com.badlogic.gdx.Gdx;

/**
 * This system process timed triggers components
 * @author caranha
 *
 */
public class TriggerSystem {
	
	public TriggerSystem()
	{
		
	}
	
	/**
	 * Reduces the times of all triggers, and execute the triggers that have been effected.
	 * 
	 * @param dt
	 * @param m
	 */
	public void update(float dt, EntityManager m)
	{
		ComponentType[] filter = { ComponentType.COMP_TIMER };
		ArrayList<Entity> list = m.queryEntities(filter);
		
		// list has a list with all the entities that have at least one timer, and all these timers have been cached
		for (int i = 0; i < list.size(); i++)
		{
			Entity e = list.get(i);
			for (int j = 0; j < e.cacheList.size(); j++) // processing each trigger
			{
				ComponentTrigger t = (ComponentTrigger) e.cacheList.get(j);
				t.countdown -= dt;
				if (t.countdown < 0) // execute trigger!
				{
					// first remove all components that need to be removed
					for (int k = 0; k < t.removelist.length; k++)
					{
						if (t.removelist[k].getType() == ComponentType.COMP_TIMER)
							Gdx.app.error("Bad Component", "You shouldn't use a timer to remove a timer!");
						
						if (!e.removeComponent(t.removelist[k].getType()))
							Gdx.app.debug("Trigger System", "Tried to remove a component that does not exist:"+t.removelist[k].getType());
					}
					
					// then add/process all components that need to be added
					for (int k = 0; k < t.addlist.length; k++)
					{
						switch(t.addlist[k].getType())
						{
							default:	
								e.addComponent(t.addlist[k]);
						}
					}
					
					// then remove this trigger from the entity
					t.dispose();
					e.removeComponent(t);
				}
			}
		}
		
		
	}

}
