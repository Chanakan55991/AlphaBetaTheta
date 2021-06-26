package live.chanakancloud.alphabetatheta.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import live.chanakancloud.alphabetatheta.AlphaBetaTheta;
import live.chanakancloud.alphabetatheta.Scenes.Hud;
import live.chanakancloud.alphabetatheta.Screens.PlayScreen;

public class EndHole extends InteractiveTileObject {
    public EndHole(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);
        setCategoryFilter(AlphaBetaTheta.END_BIT);
    }

    @Override
    public void onFeetHit() {
        holeStuff();
    }

    @Override
    public void onBodyHit() {
        holeStuff();
    }

    private void holeStuff() {
        Integer score = Hud.getScore();
        if(Hud.getScore() == 2) {
            Gdx.app.log("WORLD", "MOVE TO 2");
            ChangeMap();

        } else {
            Gdx.app.log("WORLD", "NOT ENOUGH GEMS");
        }
    }

    private void ChangeMap() {
        map.dispose();
        PlayScreen.changeMap();
    }
}
