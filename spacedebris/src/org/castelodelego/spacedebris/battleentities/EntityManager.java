package org.castelodelego.spacedebris.battleentities;

import java.util.ArrayList;

import org.castelodelego.spacedebris.battlecomponents.ComponentType;

import com.badlogic.gdx.Gdx;


public class EntityManager {

	ArrayList<Entity> entitylist;
	
	
	public EntityManager()
	{
		entitylist = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity e)
	{
		entitylist.add(e);
	}
	
	public void removeEntity(Entity e)
	{
		if (!entitylist.remove(e))
		{
			Gdx.app.log("EntityManager", "Failed to remove an entity "+e);
		}
	}
	
	/**
	 * Filter existing entities for those with at least one of each componenttype specified in the filter. 
	 * 
	 * @param filter: an array with the required Component Types. There is no meaning in having repeated types in the array.
	 * @return an Array list with all entities that have at least one of each specified type. The list can be empty. The entities in the list will have their cachelist filleds, and 
	 * the cachelist can be used for accessing the components more quickly.
	 */
	public ArrayList<Entity> queryEntities(ComponentType filter[])
	{
		ArrayList<Entity> ret = new ArrayList<Entity>();
		for (int i = 0; i < entitylist.size(); i++)
			if (entitylist.get(i).queryComponents(filter))
			{
				ret.add(entitylist.get(i));
			}
		
		return ret;
	}
}
