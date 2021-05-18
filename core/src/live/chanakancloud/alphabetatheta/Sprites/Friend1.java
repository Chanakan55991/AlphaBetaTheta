package live.chanakancloud.alphabetatheta.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import live.chanakancloud.alphabetatheta.AlphaBetaTheta;
import live.chanakancloud.alphabetatheta.Screens.PlayScreen;

public class Friend1 extends Friends {

    private Array<TextureRegion> frames;
    private Animation<TextureRegion> walkAnimation;
    private float stateTime;

    public Friend1(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for (int i = 0; i < 2; i++)
        {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("friendidle"), i * 32,0,32,32));
            walkAnimation = new Animation<>(0.4f, frames);
            stateTime = 0;
            setBounds(getX(), getY(), 32 / AlphaBetaTheta.PPM, 32 / AlphaBetaTheta.PPM);
        }
    }

    @Override
    protected void defineFriends() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / AlphaBetaTheta.PPM);
        fdef.filter.categoryBits = AlphaBetaTheta.FRIEND_BIT;
        fdef.filter.maskBits = AlphaBetaTheta.GROUND_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

    }


    @Override
    public void update(float delta) {
        stateTime += delta;

        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(walkAnimation.getKeyFrame(stateTime, true));

    }
}
