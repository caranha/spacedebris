package org.castelodelego.spacedebris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class SplashScreen implements Screen {
	
	public int ID;

	// Variables for drawing the splash screen
	OrthographicCamera camera;
	ShapeRenderer lineDrawer;
	SpriteBatch batch;
	Texture splashimg;	
	float time;
	float fade;
	
	boolean loaddone;
	
	public SplashScreen(int myid)
	{
		ID = myid;
		
		loaddone = false;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.SCREEN_W, Constants.SCREEN_H);
		
		lineDrawer = new ShapeRenderer();
		batch = new SpriteBatch();
		
		splashimg = new Texture(Gdx.files.internal("imgs/splash_vert.png"));
		fade = 0;
	}
	
	
	@Override
	public void render(float delta) {
		
		// Clear the screen
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Set loading progress variables
		loaddone = GdxGameMain.manager.update(); // true if all loading is finished	
		//	loadprogress = GdxGameMain.manager.getProgress(); // 0-1 loading progress, if I need a loading bar
		
		// splash screen fade crontrol
		time = time+delta;		
		if (time < 0.5)
		{
			fade = time*2;
		}
		if (time > 1.5 && loaddone)
		{
			fade = fade - delta*3;
		}
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(splashimg, 0,0);
		batch.end();
		
		
		// Drawing Fade
		if (fade < 1.0f)
		{
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			lineDrawer.setProjectionMatrix(camera.combined);
			lineDrawer.begin(ShapeType.Filled);
			lineDrawer.setColor(1f, 1f, 1f, 1-fade);
			lineDrawer.rect(0, 0, Constants.SCREEN_W, Constants.SCREEN_H);		
			lineDrawer.end();
		}
		// End Drawing Fade
		

		if ((fade <= 0) && (loaddone))
			GdxGameMain.setScreen(GdxGameMain.SCREEN_BATTLE);
		
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
		splashimg.dispose();
	}

}
