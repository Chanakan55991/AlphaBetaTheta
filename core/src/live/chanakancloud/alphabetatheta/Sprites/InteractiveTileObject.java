package live.chanakancloud.alphabetatheta.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Timer;
import live.chanakancloud.alphabetatheta.AlphaBetaTheta;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    private boolean playerJustDead = false;
    public static boolean isChangingWorld = false;

    protected Fixture fixture;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds)
    {
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / AlphaBetaTheta.PPM, (bounds.getY() + bounds.getHeight() / 2) / AlphaBetaTheta.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / AlphaBetaTheta.PPM, bounds.getHeight() / 2 / AlphaBetaTheta.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onFeetHit();
    public abstract void onBodyHit();

    public void setCategoryFilter(short filterBit)
    {
        if(isChangingWorld) return;
        if(playerJustDead) return;
        if(Ash.currentState == Ash.State.DEAD) {
            playerJustDead = true;
            Timer.schedule(new Timer.Task(){
                @Override
                public void run() {
                    playerJustDead = false;
                }
            }, 3);
            return;
        }
        //Filter filter = new Filter();
        //filter.categoryBits = filterBit;
        //fixture.setFilterData(filter);

        if(filterBit == AlphaBetaTheta.DESTROYED_BIT)
        {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run () {
                    world.destroyBody(fixture.getBody());
                }
            });
        }
    }

    public TiledMapTileLayer.Cell getCell()
    {
        if(body == null) return null;
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x * AlphaBetaTheta.PPM / 16), (int)(body.getPosition().y * AlphaBetaTheta.PPM / 16));
    }
}
