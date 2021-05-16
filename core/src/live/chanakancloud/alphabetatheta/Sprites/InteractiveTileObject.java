package live.chanakancloud.alphabetatheta.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import live.chanakancloud.alphabetatheta.AlphaBetaTheta;

public abstract class InteractiveTileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;

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
}
