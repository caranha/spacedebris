package org.castelodelego.spacedebris.battlecomponents;

/**
 * empty "Flag" component. The existence of this component indicates that the 
 * entity holding it should be removed from the entity manager
 * 
 * @author caranha
 *
 */
public class ComponentKill implements ComponentInterface {

	// This is an empty "flag" component
	
	@Override
	public ComponentType getType() {
		return ComponentType.COMP_KILL;
	}

	@Override
	public ComponentInterface copy() {
		return new ComponentKill();
	}

	@Override
	public void dispose() {
		// intentionally left blank
	}

}
