package live.chanakancloud.alphabetatheta.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Timer;
import live.chanakancloud.alphabetatheta.AlphaBetaTheta;
import live.chanakancloud.alphabetatheta.Tools.B2WorldCreator;

public class FallingPlatform extends InteractiveTileObject {
    private TiledMap map;
    private TiledMapTileLayer.Cell previousCell;

    public FallingPlatform(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        this.map = map;
        fixture.setUserData(this);
        setCategoryFilter(AlphaBetaTheta.FP_BIT);
    }

    @Override
    public void onFeetHit() {
        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                setCategoryFilter(AlphaBetaTheta.DESTROYED_BIT);
                previousCell = getCell();
                getCell().setTile(null);
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        setCategoryFilter(AlphaBetaTheta.FP_BIT);
                        B2WorldCreator.createNewFallingPlatform();
                        previousCell.setTile(map.getTileSets().getTile(171));
                    }
                }, 3);
            }
        }, 1);
    }

    @Override
    public void onBodyHit() {

    }
}
