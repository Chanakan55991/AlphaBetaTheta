package live.chanakancloud.alphabetatheta.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import live.chanakancloud.alphabetatheta.AlphaBetaTheta;
import live.chanakancloud.alphabetatheta.Screens.PlayScreen;

import static live.chanakancloud.alphabetatheta.AlphaBetaTheta.PPM;

public class Ash extends Sprite {
    public enum State {FALLING, JUMPING, STANDING, RUNNING}

    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private final Animation<TextureRegion> ashStand;
    // private Animation ashJump;
    private final Animation<TextureRegion> ashRun;
    private float stateTimer;
    private boolean runningRight;

    public Ash(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("ashidle"));
        this.world = world;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<>();
        /*for(int i = 67; i < 80; i++) {
            frames.add(new TextureRegion(getTexture(), i * 32, 0, 32, 32));
        }*/
        for (int i = 0; i < 14; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("ashwalking"), i * 32, 0, 32, 32));
        }
        ashRun = new Animation<>(0.1f, frames);
        frames.clear();
        defineAsh();
        for(int i = 0; i < 6; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("ashidle"), i * 32, 0, 32, 32));
        }
        ashStand = new Animation<>(0.1f, frames);
        frames.clear();
        setBounds(0, 0, 32 / PPM, 32 / PPM);
        setRegion(ashStand.getKeyFrame(stateTimer, true));

    }

    public void update(float delta) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(delta));
    }

    public TextureRegion getFrame(float delta) {
        currentState = getState();

        TextureRegion region;
        switch(currentState) {
            case JUMPING: {
                region = ashRun.getKeyFrame(stateTimer);
                break;
            }
            case RUNNING: {
                region = ashRun.getKeyFrame(stateTimer,  true);
                break;
            }
            case FALLING:
            case STANDING:
            default:
                region = ashStand.getKeyFrame(stateTimer, true);
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    public void defineAsh() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(200 / PPM, 90 / PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef2 = new FixtureDef();
        EdgeShape feet = new EdgeShape();
        feet.set(new Vector2(-2 / PPM, -6 / PPM), new Vector2(2 / PPM, -6 / PPM));
        fdef2.shape = feet;

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / PPM);
        fdef.filter.categoryBits = AlphaBetaTheta.ASH_BIT;
        fdef.filter.maskBits = AlphaBetaTheta.DEFAULT_BIT | AlphaBetaTheta.GEMS_BIT | AlphaBetaTheta.FP_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("body");
        b2body.createFixture(fdef2).setUserData("feet");
    }
}
