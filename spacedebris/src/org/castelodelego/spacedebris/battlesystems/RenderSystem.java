package org.castelodelego.spacedebris.battlesystems;

import java.util.ArrayList;

import org.castelodelego.spacedebris.GdxGameMain;
import org.castelodelego.spacedebris.battlecomponents.ComponentCollBox;
import org.castelodelego.spacedebris.battlecomponents.ComponentAnimation;
import org.castelodelego.spacedebris.battlecomponents.ComponentPosition;
import org.castelodelego.spacedebris.battlecomponents.ComponentType;
import org.castelodelego.spacedebris.battleentities.Entity;
import org.castelodelego.spacedebris.battleentities.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * System that renders the battlefield.
 * 
 * It deals with setting the camera up (initializing, player following, etc) 
 * and drawing each instance that has the "ComponentRender".
 * 
 * @author caranha
 *
 */
public class RenderSystem {

	// FIXME: Should the camera be really here?
	ShapeRenderer linedrawer; // TODO: should this be global for the entire game?
	OrthographicCamera c;

	
	public RenderSystem(float width, float height)
	{
		linedrawer = new ShapeRenderer();
		c = new OrthographicCamera(); // TODO: make sure that this determines the size of the CAMERA, not the size of the area covered by the camera
		c.setToOrtho(false, width, height);
	}
	

	
	/**
	 * This method, when called, renders a collision box for every entity that has one. 
	 * This is a bit slow, and usually useful for debugging.
	 * @param m The entity Manager
	 */
	public void RenderCollBoxes(EntityManager m)
	{

		ComponentType[] filter = { ComponentType.COMP_COLLBOX};
		ArrayList<Entity> list = m.queryEntities(filter);

		linedrawer.setProjectionMatrix(c.combined);
		linedrawer.begin(ShapeType.Line);
		linedrawer.setColor(Color.RED);
		
		ComponentCollBox rectangle = null;		
		for (int i = 0; i < list.size(); i++)
		{
			// getting the necessary components from the entity
			rectangle = (ComponentCollBox) list.get(i).getComponentByType(ComponentType.COMP_COLLBOX);

			if (rectangle == null)
			{
				Gdx.app.error("Rendering Line Pass", "Failed to find necessary components after querying");
				break;
			}			
			linedrawer.rect(rectangle.box.x, rectangle.box.y, rectangle.box.width, rectangle.box.height);			
		}
		linedrawer.end();
		
	}


	public void RenderAnimations(EntityManager m, float dt) {
		ComponentType[] filter = { ComponentType.COMP_POS, ComponentType.COMP_ANIM };
		ArrayList<Entity> list = m.queryEntities(filter);
		
		GdxGameMain.batch.begin();

		ComponentAnimation anim = null;
		ComponentPosition pos = null;
		Animation drawable = null;
		TextureRegion texture = null;
		
		for (int i = 0; i < list.size(); i++)
		{
			// getting the necessary components from the entity
			pos = (ComponentPosition) list.get(i).getComponentByType(ComponentType.COMP_POS);
			anim = (ComponentAnimation) list.get(i).getComponentByType(ComponentType.COMP_ANIM);
			drawable = GdxGameMain.animman.get(anim.spritename);
			
			if (drawable != null)
			{
				anim.time += dt;
				texture = drawable.getKeyFrame(anim.time);
				
				if (anim.tint != null)
					GdxGameMain.batch.setColor(anim.tint); // setting tint
				
				GdxGameMain.batch.draw(texture, pos.pos.x - texture.getRegionWidth()/2, pos.pos.y - texture.getRegionHeight()/2);
				
				if (anim.tint != null)
					GdxGameMain.batch.setColor(Color.WHITE); // unsetting tint

			}
			else
				Gdx.app.error("Resource Missing","Could not find the animation called "+anim.spritename);
			
		}
		GdxGameMain.batch.end();
		
	}

	
	
	
	
	
}
