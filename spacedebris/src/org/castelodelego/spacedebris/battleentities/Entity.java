package org.castelodelego.spacedebris.battleentities;

import java.util.ArrayList;

import org.castelodelego.spacedebris.battlecomponents.ComponentInterface;
import org.castelodelego.spacedebris.battlecomponents.ComponentType;

public class Entity {

	public ArrayList<ComponentInterface> componentList;
	public ArrayList<ComponentInterface> cacheList; // The results of the last "queryComponents" is put here, in order to avoid doing expensive searches many times.
	
	public Entity()
	{
		componentList = new ArrayList<ComponentInterface>();
		cacheList = new ArrayList<ComponentInterface>();
	}
	
	/**
	 * Adds a component to the Entity
	 * @param c
	 */
	public void addComponent(ComponentInterface c)
	{
		componentList.add(c);
	}
	
	/**
	 * Removes the first component of this type
	 * @return false if no component of this type;
	 */
	public boolean removeComponent(ComponentType type)
	{
		for (int i = 0; i < componentList.size(); i++)
			if (componentList.get(i).getType() == type)
			{
				componentList.remove(i);
				return true;
			}
		return false;
	}
	
	/**
	 * Removes the exact component passed as a parameter from this entity
	 * @return false if the component was not found;
	 */
	public boolean removeComponent(ComponentInterface comp)
	{
		return componentList.remove(comp);
	}
	
	/**
	 * Creates an exact, independent copy of this entity. The Cachelist is NOT copied.
	 * WARNING: make sure this works sanely when dealing with components shared across entities
	 * @return
	 */
	public Entity copy()
	{
		Entity ret = new Entity();
		for (int i = 0; i < componentList.size(); i++)
		{
			ret.addComponent(componentList.get(i).copy());
		}
		
		return ret;
	}
	
	/**
	 * Test if this entity has all the components specified in the request list. 
	 * If at least one of each component type is found, return true. 
	 * All components that match the request list are stored in the cacheList. 
	 * 
	 * @param idlist
	 * @return
	 */
	public boolean queryComponents(ComponentType requestlist[])
	{
		cacheList.clear();
		boolean[] check = new boolean[requestlist.length];

		// java SHOULd have initialized this to false. But initializing explicitily is good practice.
		for (int i = 0; i < check.length; i++)
			check[i] = false; 
		
		for(int i = 0; i < componentList.size(); i++)
			for (int j = 0; j < requestlist.length; j++)
				if (componentList.get(i).getType() == requestlist[j])
				{
					cacheList.add(componentList.get(i));
					check[j] = true;
					break;
				}
		
		// Checking if all required types were fulfilled
		for (int i = 0; i < check.length; i++)
			if (check[i] == false) // at least one unfulfilled type;
			{
				cacheList.clear();
				return false;
			}
		return true;
	}
	
	/**
	 * Gets the first component of this type. Searches the cache first.
	 * @param type
	 * @return null if the component is not found;
	 */
	public ComponentInterface getComponentByType(ComponentType type)
	{
		for (int i = 0; i < cacheList.size(); i++)
			if (cacheList.get(i).getType() == type)
				return cacheList.get(i);
		
		for (int i = 0; i < componentList.size(); i++)
			if (componentList.get(i).getType() == type)
				return componentList.get(i);
		return null;
	}
	
	
	/**
	 * Free all the memory of this entity.
	 * BE CAREFUL that this do not destroy components used by more than one entity! 
	 */
	public void dispose()
	{
		cacheList.clear();
		for (int i = 0; i < componentList.size(); i++)
			componentList.get(i).dispose();
	}
	
}
