package live.chanakancloud.alphabetatheta.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import live.chanakancloud.alphabetatheta.AlphaBetaTheta;
import live.chanakancloud.alphabetatheta.Scenes.Hud;
import live.chanakancloud.alphabetatheta.Sprites.Ash;
import live.chanakancloud.alphabetatheta.Tools.B2WorldCreator;
import live.chanakancloud.alphabetatheta.Tools.WorldContactListener;

public class PlayScreen implements Screen {
    private final AlphaBetaTheta game;
    private final TextureAtlas atlas;
    private final OrthographicCamera camera;
    private final Viewport gamePort;
    private final Hud hud;

    private final TiledMap map;
    private final OrthogonalTiledMapRenderer renderer;

    private final World world;
    private final Box2DDebugRenderer b2dr;
    private final Ash player;

    public PlayScreen(AlphaBetaTheta game) {
        atlas = new TextureAtlas("ABTSprites.pack");
        this.game = game;
        camera = new OrthographicCamera();
        gamePort = new FitViewport(AlphaBetaTheta.V_WIDTH / AlphaBetaTheta.PPM, AlphaBetaTheta.V_HEIGHT / AlphaBetaTheta.PPM, camera);
        hud = new Hud(game.batch);
        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / AlphaBetaTheta.PPM);
        camera.position.set(gamePort.getWorldWidth() / 2f, gamePort.getWorldHeight() / 2f, 0);
        b2dr = new Box2DDebugRenderer();

        world = new World(new Vector2(0, -10), true);
        new B2WorldCreator(world, map);

        player = new Ash(world, this);

        world.setContactListener(new WorldContactListener());
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && player.b2body.getLinearVelocity().x <= 2) {
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && player.b2body.getLinearVelocity().x >= -2) {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }
    }

    public void update(float delta) {
        handleInput();
        world.step(1 / 60f, 6, 2);
        player.update(delta);
        camera.position.x = player.b2body.getPosition().x;
        camera.update();
        renderer.setView(camera);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        b2dr.render(world, camera.combined);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
