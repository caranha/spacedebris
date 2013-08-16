package org.castelodelego.spacedebris.battlecomponents;

import com.badlogic.gdx.math.Vector2;

public class ComponentDirection implements ComponentInterface {

	public Vector2 dir;
	
	public ComponentDirection(float x, float y)
	{
		dir.x = x;
		dir.y = y;
	}
	
	
	@Override
	public ComponentType getType() {
		return ComponentType.COMP_DIR;
	}

	@Override
	public ComponentInterface copy() {
		return (new ComponentDirection(dir.x,dir.y));
	}

	@Override
	public void dispose() {
		// Function intentionally left blank
	}

}
