package org.castelodelego.spacedebris.battlescene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * The battle screen is the game screen that is active during a battle. It includes handlers for the interface, for the input, and for the battle manager
 * @author caranha
 *
 */
public class BattleScreen implements Screen {

	BattleManager bm;
	
	
	public BattleScreen()
	{
		bm = new BattleManager();
		bm.debugInit(50);
	}
	
	@Override
	public void render(float delta) {
		
		// dealing with input and interface
		
		// sending imput to the battle manager
		
		/*-- UPDATE BLOCK --*/
		bm.update(delta);
		
		/*-- RENDERING BLOCK --*/
		// clearing the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// rendering the Battle Screen
		bm.render(delta);
		
		
		// DEBUG - playing with sprites
		

		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
