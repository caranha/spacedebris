package org.castelodelego.spacedebris.battlecomponents;



/**
 * Interface for a component in the Entity-System Architecture
 * @author caranha
 *
 */
public interface ComponentInterface {
	
	
	
	/**
	 * Gets the identifier for this type of Component;
	 */
	public ComponentType getType();
	
	/**
	 * Returns a copy of this Component;
	 * @return
	 */
	public ComponentInterface copy();
		
	/**
	 * Release all the memory held by this component
	 */
	public void dispose();
	
}
