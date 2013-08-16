package org.castelodelego.spacedebris.battlecomponents;

import com.badlogic.gdx.math.Vector2;

/**
 * This component contains the world position of the entity. Indicates that the entity exists in the world
 * @author caranha
 *
 */
public class ComponentPosition implements ComponentInterface {

	public Vector2 pos;
	
	public ComponentPosition(float x, float y)
	{
		pos = new Vector2(x,y);
	}
	
	
	@Override
	public ComponentType getType() {
		return ComponentType.COMP_POS;
	}


	@Override
	public ComponentInterface copy() {
		return (new ComponentPosition(pos.x, pos.y));
	}

	@Override
	public void dispose() {
		// This function is empty
	}

}
