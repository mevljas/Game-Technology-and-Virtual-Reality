package tk.sebastjanmevlja.doodlejumpspace.Gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import tk.sebastjanmevlja.doodlejumpspace.Gameplay.Platforms.Platform;
import tk.sebastjanmevlja.doodlejumpspace.Helpers.Assets;
import tk.sebastjanmevlja.doodlejumpspace.Helpers.Constants;

import static tk.sebastjanmevlja.doodlejumpspace.Helpers.Constants.PPM;


public class Slime extends Actor {

    public static float TRAMPOLINE_WIDTH = Platform.PLATFORM_WIDTH * 0.7f;
    public static float TRAMPOLINE_HEIGHT = Platform.PLATFORM_HEIGHT * 1.3f;
    private final float bodyHeight;
    public Animation<TextureRegion> runningAnimation;
    public boolean playerJumping = false;
    Sprite sprite;
    World world;
    Body body;
    // A variable for tracking elapsed time for the animation
    float stateTime;


    public Slime(float x, float y, World world) {
        sprite = new Sprite(Assets.atlas.findRegions("slime").get(0));
        sprite.setSize(TRAMPOLINE_WIDTH, TRAMPOLINE_HEIGHT);
        sprite.setPosition(x, y);
        sprite.setCenterX(x);

        this.world = world;

        // Now create a BodyDefinition.  This defines the physics objects type and position in the simulation
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.gravityScale = 0.0f;
        // We are going to use 1 to 1 dimensions.  Meaning 1 in physics engine  is 1 pixel
        // Set our body to the same position as our sprite
        bodyDef.position.set((sprite.getX() + sprite.getWidth() / 2) /
                        PPM,
                (sprite.getY() + sprite.getHeight() / 2) / PPM);

        // Create a body in the world using our definition
        body = world.createBody(bodyDef);


        // Now define the dimensions of the physics shape
        PolygonShape shape = new PolygonShape();
        // Basically set the physics polygon to a box with the same dimensions as our sprite
        float bodyWidth = sprite.getWidth() * 0.5f / PPM;
        bodyHeight = sprite.getHeight() * 0.3f / PPM;
        shape.setAsBox(bodyWidth, bodyHeight);
        // FixtureDef is a confusing expression for physical properties
        // Basically this is where you, in addition to defining the shape of the body
        // you also define it's properties like density, restitution and others
        // Density and area are used to calculate over all mass
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = tk.sebastjanmevlja.doodlejumpspace.Helpers.Constants.TRAMPOLINE_BIT;
        fixtureDef.filter.maskBits = Constants.PLAYER_BIT;
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.isSensor = true;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        // Shape is the only disposable of the lot, so get rid of it
        shape.dispose();


        runningAnimation = new Animation<TextureRegion>(0.1f, Assets.atlas.findRegions("slime"), Animation.PlayMode.NORMAL);
        this.stateTime = 0f;
    }

    public void updatePos(float x, float y) {
        // Set the sprite's position from the updated physics body location
        sprite.setPosition(x, y);
        this.body.setTransform((sprite.getX() + sprite.getWidth() / 2) / PPM,
                (sprite.getY() + sprite.getHeight() / 2) / PPM, 0);

        updateAnimations();
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }


    private void updateAnimations() {
        if (this.playerJumping) {
            this.stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
            if (this.runningAnimation.isAnimationFinished(this.stateTime)) {
                this.playerJumping = false;
            }
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!playerJumping) {
            sprite.draw(batch);
        } else {
            // Get current frame of animation for the current stateTime
            TextureRegion currentFrame = runningAnimation.getKeyFrame(stateTime, false);
            batch.draw(currentFrame, sprite.getX(), sprite.getY(), TRAMPOLINE_WIDTH, TRAMPOLINE_HEIGHT);

        }

    }

    public Vector2 getBodyPosition() {
        return body.getPosition();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Body getBody() {
        return body;
    }

    public void playerJump() {
        this.playerJumping = true;
        this.stateTime = 0f;

    }


    public float getBodyHeight() {
        return bodyHeight;
    }
}
