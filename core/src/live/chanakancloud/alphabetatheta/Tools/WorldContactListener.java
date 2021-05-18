package live.chanakancloud.alphabetatheta.Tools;

import com.badlogic.gdx.physics.box2d.*;
import live.chanakancloud.alphabetatheta.Screens.PlayScreen;
import live.chanakancloud.alphabetatheta.Sprites.InteractiveTileObject;

public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "feet" || fixB.getUserData() == "feet") {
            if (fixA.getUserData() == "ground" || fixB.getUserData() == "ground" || InteractiveTileObject.class.isAssignableFrom(fixA.getUserData().getClass()) || InteractiveTileObject.class.isAssignableFrom(fixB.getUserData().getClass())) {
                PlayScreen.isJumping = false;
            }
        }

        if(fixA.getUserData() == "feet" || fixB.getUserData() == "feet") {
            Fixture feet = fixA.getUserData() == "feet" ? fixA : fixB;
            Fixture object = feet == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onFeetHit();
            }
            //CHECK FALLING PLATFORM
        }

        if(fixA.getUserData() == "body" || fixB.getUserData() == "body") {
            Fixture body = fixA.getUserData() == "body" ? fixA : fixB;
            Fixture object = body == fixA ? fixB : fixA;

            if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onBodyHit();
            }
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
