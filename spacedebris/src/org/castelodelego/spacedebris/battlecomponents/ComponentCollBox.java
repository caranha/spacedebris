package org.castelodelego.spacedebris.battlecomponents;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ComponentCollBox implements ComponentInterface {

	public Rectangle box;
	
	public ComponentCollBox(float width, float height, Vector2 center)
	{
		box = new Rectangle(0,0,width,height);
		box.setCenter(center);
	}
	
	@Override
	public ComponentType getType() {
		return ComponentType.COMP_COLLBOX;
	}

	@Override
	public ComponentInterface copy() {
		return (new ComponentCollBox(box.width, box.height, box.getCenter(new Vector2())));
	}

	@Override
	public void dispose() {
		// this function intentionally left blank;
	}

}
