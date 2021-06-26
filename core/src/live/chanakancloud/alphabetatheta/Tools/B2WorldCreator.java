package live.chanakancloud.alphabetatheta.Tools;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import live.chanakancloud.alphabetatheta.AlphaBetaTheta;
import live.chanakancloud.alphabetatheta.Sprites.EndHole;
import live.chanakancloud.alphabetatheta.Sprites.Gems;
import live.chanakancloud.alphabetatheta.Sprites.Spike;

public class B2WorldCreator {
    private static TiledMap mapp;
    private static World worldd;

    public B2WorldCreator(World world, TiledMap map) {
        mapp = map;
        worldd = world;

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        for(RectangleMapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = object.getRectangle();

            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2) / AlphaBetaTheta.PPM, (rect.getY() + rect.height / 2) / AlphaBetaTheta.PPM);

            body = world.createBody(bodyDef);
            shape.setAsBox(rect.getWidth() / 2 / AlphaBetaTheta.PPM, rect.getHeight() / 2 / AlphaBetaTheta.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef).setUserData("ground");
        }

        for(RectangleMapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = object.getRectangle();
            new Gems(world, map, rect);
        }

        /*for(RectangleMapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = object.getRectangle();
            new FallingPlatform(world, map, rect);
        }*/
        for(RectangleMapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = object.getRectangle();
            new EndHole(world, map, rect);
        }

        for(RectangleMapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = object.getRectangle();
            new Spike(world, map, rect);
        }
    /* TEMPLATE
        for(RectangleMapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = object.getRectangle();
            new SPRITECLASS(world, map, rect);
        }
    */
    }
}
