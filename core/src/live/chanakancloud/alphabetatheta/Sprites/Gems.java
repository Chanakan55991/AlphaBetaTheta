package live.chanakancloud.alphabetatheta.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import live.chanakancloud.alphabetatheta.AlphaBetaTheta;
import live.chanakancloud.alphabetatheta.Scenes.Hud;

public class Gems extends InteractiveTileObject {
// DO THIS FOR SOMETHING LIKE BRICKS TOO
    public Gems(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(AlphaBetaTheta.GEMS_BIT);
    }

    @Override
    public void onFeetHit() {
        setCategoryFilter(AlphaBetaTheta.DESTROYED_BIT);
        gemsStuff();
    }

    @Override
    public void onBodyHit() {
        setCategoryFilter(AlphaBetaTheta.DESTROYED_BIT);
        gemsStuff();
    }

    private void gemsStuff() {
        getCell().setTile(null);
        Hud.addScore(100);
    }
}
