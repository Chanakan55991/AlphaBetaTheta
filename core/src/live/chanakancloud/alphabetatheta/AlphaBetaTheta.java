package live.chanakancloud.alphabetatheta;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import live.chanakancloud.alphabetatheta.Screens.PlayScreen;

public class AlphaBetaTheta extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100f;
	public static final short ASH_BIT = 2;
	public static final short FP_BIT = 4;
	public static final short GEMS_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short FRIEND_BIT = 32;
	public static final short GROUND_BIT = 64;
	public static final short DEFAULT_BIT = 1;
	public static AssetManager manager;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("music/music.wav", Music.class);
		manager.finishLoading();

		setScreen(new PlayScreen(this));
	}
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
