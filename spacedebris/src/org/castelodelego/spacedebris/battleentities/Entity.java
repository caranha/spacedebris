package org.castelodelego.spacedebris.battleentities;

import java.util.EnumMap;
import java.util.Iterator;

import org.castelodelego.spacedebris.battlecomponents.ComponentInterface;
import org.castelodelego.spacedebris.battlecomponents.ComponentType;

import com.badlogic.gdx.utils.Array;

/**
 * An entity is a collection of components.
 * @author caranha
 *
 */
public class Entity {

	
	private EnumMap<ComponentType, Array<ComponentInterface>> componentList;
	
	
	public Entity()
	{
		componentList = new EnumMap<ComponentType,Array<ComponentInterface>>(ComponentType.class);
	}
	
	/**
	 * Adds a component to the Entity
	 * @param c
	 */
	public void addComponent(ComponentInterface c)
	{
		Array<ComponentInterface> t = componentList.get(c.getType());
		
		if (t == null) // this entity still does not have this component, creates the list;
		{
			t = new Array<ComponentInterface>();
			componentList.put(c.getType(), t);
		}		
		t.add(c);
	}
	
	/**
	 * Adds an entire array of components to this entity. The array can be pure or mixed, the components 
	 * are added one at a time. If the array is pure, the order of the components is preserved.
	 * @param t
	 */
	public void addComponents(Array<ComponentInterface> t)
	{
		if (t != null)
			for (int i = 0; i < t.size; i++)
			{
				this.addComponent(t.get(i));
			}
	}
	
	
	/**
	 * Removes the last component of this type.
	 * @return The removed component, or null if no components of this type exist
	 */
	public ComponentInterface removeLastComponent(ComponentType type)
	{
		Array<ComponentInterface> t = componentList.get(type);
		if (t == null || t.size == 0)
			return null;
		else
			return t.pop();		
	}
	
	/**
	 * Removes the exact component passed as a parameter from this entity
	 * @return false if the componbent was not found.
	 */
	public boolean removeComponent(ComponentInterface comp)
	{
		Array<ComponentInterface> t = componentList.get(comp.getType());
		if (t == null)
			return false;

		for (int i = 0; i < t.size; i++)
			if (t.get(i) == comp)
			{
				t.removeIndex(i);
				return true;
			}
		
		return false;
	}
	
	/**
	 * Creates an exact, independent copy of this entity.
	 * WARNING: make sure this works sanely when dealing with components shared across entities
	 * @return
	 * 
	 */
	public Entity copy()
	{
		Entity ret = new Entity();
		
		Iterator<Array<ComponentInterface>> it = componentList.values().iterator();
		
		while (it.hasNext())
		{
			Array<ComponentInterface> t = it.next();
			this.addComponents(t);
		}
		
		componentList.clear();

		return ret;
	}
	
	/**
	 * Test if this entity has all the components specified in the request list. 
	 * If at least one of each component type is found, return true. 
	 * 
	 * @param idlist
	 * @return
	 */
	public boolean queryComponents(ComponentType requestlist[])
	{
		for (int i = 0; i < requestlist.length; i++)
		{
			Array<ComponentInterface> t = componentList.get(requestlist[i]);
			if (t == null || t.size == 0)
				return false;
		}
		return true;
	}
	
	/**
	 * Gets all components of this type
	 * @param type
	 * @return null if the component is not found;
	 */
	public Array<ComponentInterface> getComponentByType(ComponentType type)
	{
		Array<ComponentInterface> t = componentList.get(type);
		if (t == null || t.size == 0)
			return null;
		else
			return t;
	}
	
	
	/**
	 * Free all the memory of this entity.
	 * BE CAREFUL that this do not destroy components used by more than one entity! 
	 */
	public void dispose()
	{
		Iterator<Array<ComponentInterface>> it = componentList.values().iterator();
		
		while (it.hasNext())
		{
			Array<ComponentInterface> t = it.next();
			if (t != null)
				for (int i = 0; i < t.size; i++)
					t.get(i).dispose();
		}
		
		componentList.clear();
	}
	
}
