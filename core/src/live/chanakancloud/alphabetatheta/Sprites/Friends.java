package live.chanakancloud.alphabetatheta.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import live.chanakancloud.alphabetatheta.Screens.PlayScreen;

public abstract class Friends extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;

    public Friends(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineFriends();
        velocity = new Vector2(-1, -2);
        b2body.setActive(false);
    }

    protected abstract void defineFriends();
    public abstract void update(float dt);
   // LATER public abstract void hitOnHead(Ash ash);
    //public abstract void hitByEnemy(Enemy enemy);

    public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }
}