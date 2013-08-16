package org.castelodelego.spacedebris.battlesystems;

import java.util.ArrayList;

import org.castelodelego.spacedebris.battlecomponents.ComponentCollBox;
import org.castelodelego.spacedebris.battlecomponents.ComponentRender;
import org.castelodelego.spacedebris.battlecomponents.ComponentType;
import org.castelodelego.spacedebris.battleentities.Entity;
import org.castelodelego.spacedebris.battleentities.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

	ShapeRenderer linedrawer;
	OrthographicCamera c;

	
	public RenderSystem(float width, float height)
	{
		linedrawer = new ShapeRenderer();
		c = new OrthographicCamera(); // TODO: make sure that this determines the size of the CAMERA, not the size of the area covered by the camera
		c.setToOrtho(false, width, height);
	}
	

	/**
	 * Renders all entities with a Renderer component, using two passes, one for Renderers of spritebatch type, another for 
	 * rectangle renderers;
	 * TODO: Consider separating the components into RendererSprite and Renderer Shape, or somehow ordering the list by 
	 * Renderer Type.
	 * @param m
	 */
	public void RenderEntities(EntityManager m)
	{

		// ShapeRendererPass: // FIXME: for now I ignore the type of shapeRenderer, and rend all of them as a Shapetype.Line
		
		ComponentType[] filter = {ComponentType.COMP_RENDER, ComponentType.COMP_COLLBOX}; // I need a collisionBox to render - TODO: probably not true, decouple these.
		ArrayList<Entity> list = m.queryEntities(filter);

		Gdx.app.debug("Number of Entities to render",list.size()+"");
		
		linedrawer.setProjectionMatrix(c.combined);
		linedrawer.begin(ShapeType.Line);
		
		ComponentRender renderer = null;
		ComponentCollBox rectangle = null;		
		for (int i = 0; i < list.size(); i++)
		{
			Gdx.app.debug("", "Entity no: "+i);
			// getting the necessary components from the entity
			renderer = (ComponentRender) list.get(i).getComponentByType(ComponentType.COMP_RENDER);
			rectangle = (ComponentCollBox) list.get(i).getComponentByType(ComponentType.COMP_COLLBOX);

			if (renderer == null || rectangle == null)
			{
				Gdx.app.error("Rendering Line Pass", "Failed to find necessary components after querying");
				break;
			}
			
			linedrawer.setColor(renderer.color);
			linedrawer.rect(rectangle.box.x, rectangle.box.y, rectangle.box.width, rectangle.box.height);			
		}
		linedrawer.end();
		
	}
	
	
	
	
	
}
