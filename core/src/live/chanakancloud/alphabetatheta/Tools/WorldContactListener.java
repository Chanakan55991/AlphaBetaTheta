package live.chanakancloud.alphabetatheta.Tools;

import com.badlogic.gdx.physics.box2d.*;
import live.chanakancloud.alphabetatheta.Sprites.InteractiveTileObject;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "feet" || fixB.getUserData() == "feet") {
            Fixture feet = fixA.getUserData() == "feet" ? fixA : fixB;
            Fixture object = feet == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onFeetHit();
            }
            //CHECK FALLING PLATFORM
        }
        // PUT GEM AND FALLING PLATFORM DETECTION HERE
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
