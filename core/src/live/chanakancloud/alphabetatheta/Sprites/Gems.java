package live.chanakancloud.alphabetatheta.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Gems extends InteractiveTileObject {
// DO THIS FOR SOMETHING LIKE BRICKS TOO
    public Gems(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }

    @Override
    public void onFeetHit() {
        Gdx.app.log("DEBUG", "Collided with gem");
    }
}