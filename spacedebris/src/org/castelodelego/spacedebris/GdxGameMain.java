package org.castelodelego.spacedebris;

import org.castelodelego.spacedebris.battlescene.BattleScreen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxGameMain extends Game {

	static int SCREEN_NUMBER = 3;
	static int SCREEN_SPLASH = 0;
	static int SCREEN_MAIN = 1;
	static int SCREEN_BATTLE = 2;
	
	static Screen[] screenlist;
	static AssetManager manager;
	
	static int nextscreen;
	static boolean changescreen;
	
	// Debug text display
	BitmapFont debugtext;
	SpriteBatch batch;
	
	@Override
	public void create() {		
		screenlist = new Screen[SCREEN_NUMBER];
		screenlist[SCREEN_SPLASH] = new SplashScreen(SCREEN_SPLASH);
		screenlist[SCREEN_BATTLE] = new BattleScreen();
		
		queueAssets();
		setScreen(screenlist[SCREEN_SPLASH]);
		
		nextscreen = SCREEN_SPLASH;
		changescreen = false;
		
		debugtext = new BitmapFont();
		batch = new SpriteBatch();
		
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	@Override
	public void dispose() {
		debugtext.dispose();
		super.dispose();
	}

	@Override
	/**
	 * Global Render call for "game". Things rendering here should be rendered in any screen.
	 * TODO: Test if this is true;
	 */
	public void render() {		
		if (changescreen)
		{
			changescreen = false;
			setScreen(screenlist[nextscreen]);
		}
		
		super.render();
		
		
		// Rendering here renders above everything else
		// Good for rendering debug info
		batch.begin();
		debugtext.setColor(Color.YELLOW);
		debugtext.draw(batch, "FPS: "+Gdx.graphics.getFramesPerSecond(), 5, 795);
		batch.end();
		
	}

	
	/**
	 * Creates the assed manager and queue all assets for loading
	 */
	private void queueAssets()
	{
		manager = new AssetManager();
		
		// Do manage.load everything here

	}
	
	/**
	 * static method for changing screens
	 * @param index - internal number of the screen to switch to.
	 */
	static public void setScreen(int index)
	{
		changescreen = true;
		nextscreen = index;
	}
	
	/**
	 * Returns a screen for direct manipulation
	 * (still needs to be cast on the other side, and the other side needs to know what kind of screen it is)
	 * TODO: make a new screen interface with a "reset" function
	 * @param index
	 * @return
	 */
	static public Screen getScreen(int index)
	{
		return screenlist[index];
	}
	
	
	/*
	 * The methods below are super-methods for Game. I can override 
	 * this methods as needed to create behaviors that are done in all screens.
	 */
//	@Override
//	public void resize(int width, int height) {
//	}
//
//	@Override
//	public void pause() {
//	}
//
//	@Override
//	public void resume() {
//	}
}
