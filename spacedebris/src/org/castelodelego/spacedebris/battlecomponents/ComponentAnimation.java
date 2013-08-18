package org.castelodelego.spacedebris.battlecomponents;

import com.badlogic.gdx.graphics.Color;

/**
 * 
 * This component indicates that the entity has an associated animation, and should be rendered as an animation.
 * 
 * @author caranha
 *
 */
public class ComponentAnimation implements ComponentInterface {

	public Color tint = null; // TODO: tint should probably be a separate component : Low Priority
	public String spritename;
	public float time = 0;
	
	public ComponentAnimation(String sprite, Color c)
	{
		if (c != null)
			tint = c.cpy();
		spritename = sprite.substring(0);
	}

	@Override
	public ComponentType getType() {
		return ComponentType.COMP_ANIM;
	}

	@Override
	public ComponentInterface copy() {
		ComponentAnimation clone = (new ComponentAnimation(spritename,tint));
		clone.time = this.time;
		return clone;
	}

	@Override
	public void dispose() {
		// intentionally left blank (GC should take care of this)
	}
	
}
