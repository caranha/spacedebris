package org.castelodelego.spacedebris.battlecomponents;

import com.badlogic.gdx.graphics.Color;

/**
 * This components contains logic for rendering the entity's body
 * Either by drawing to the GraphicsRenderer, or by Rendering a sprite
 * 
 * @author caranha
 *
 */
public class ComponentRender implements ComponentInterface {

	public static final int TYPE_WIREBOX = 0; // draws the collisionbox with lines
	public static final int TYPE_FILLBOX = 1; // draws the collisionbox with a filled rectangle
	public static final int TYPE_SPRITE = 2; // draws the sprite with center of mass at the entity's position
	
	public int drawtype = 0;
	public Color color = Color.WHITE;
	public String spritename;
	
	public ComponentRender(int type, Color c, String sprite)
	{
		drawtype = type;
		color = c.cpy();
		spritename = sprite.substring(0);
	}

	@Override
	public ComponentType getType() {
		return ComponentType.COMP_RENDER;
	}

	@Override
	public ComponentInterface copy() {
		return (new ComponentRender(drawtype, color, spritename));
	}

	@Override
	public void dispose() {
		// intentionally left blank (GC should take care of this)
	}
	
}
