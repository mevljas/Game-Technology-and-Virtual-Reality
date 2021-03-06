package tk.sebastjanmevlja.doodlejumpspace.Gameplay;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import tk.sebastjanmevlja.doodlejumpspace.Helpers.Assets;
import tk.sebastjanmevlja.doodlejumpspace.Helpers.Constants;

import static tk.sebastjanmevlja.doodlejumpspace.Helpers.Constants.PPM;


public class Bullet extends Actor {

    private static final float bulletMovingScale = tk.sebastjanmevlja.doodlejumpspace.Helpers.Constants.HEIGHT * 0.008f;
    public static float BULLET_HEIGHT = tk.sebastjanmevlja.doodlejumpspace.Helpers.Constants.HEIGHT * 0.015f;
    @SuppressWarnings("SuspiciousNameCombination")
    public static float BULLET_WIDTH = BULLET_HEIGHT;
    public boolean alive;
    Sprite sprite;
    World world;
    Body body;


    public Bullet(float xs, float ys, World world, float xt, float yt) {
        sprite = new Sprite(Assets.atlas.findRegion("bullet"));
        sprite.setSize(BULLET_WIDTH, BULLET_HEIGHT);
        sprite.setPosition(xs, ys);
        sprite.setCenterX(xs);

        this.world = world;

        // Now create a BodyDefinition.  This defines the physics objects type and position in the simulation
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
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
        shape.setAsBox(sprite.getWidth() * 0.5f / PPM, sprite.getHeight() * 0.5f / PPM);
        // FixtureDef is a confusing expression for physical properties
        // Basically this is where you, in addition to defining the shape of the body
        // you also define it's properties like density, restitution and others
        // Density and area are used to calculate over all mass
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = tk.sebastjanmevlja.doodlejumpspace.Helpers.Constants.BULLET_BIT;
        fixtureDef.filter.maskBits = tk.sebastjanmevlja.doodlejumpspace.Helpers.Constants.MONSTER_BIT;
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.isSensor = true;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        // Shape is the only disposable of the lot, so get rid of it
        shape.dispose();

        Vector2 vectDirection = new Vector2(xt / PPM, yt / PPM).sub(new Vector2(xs / PPM, ys / PPM)).nor().scl(bulletMovingScale);
        body.setLinearVelocity(vectDirection);
        alive = true;

    }

    public void updatePos() {
        if (alive) {
            // Set the sprite's position from the updated physics body location
            sprite.setPosition((body.getPosition().x * PPM) - sprite.
                            getWidth() / 2,
                    (body.getPosition().y * PPM) - sprite.getHeight() / 2);

            if (sprite.getX() + sprite.getWidth() > tk.sebastjanmevlja.doodlejumpspace.Helpers.Constants.WIDTH || sprite.getX() < 0 ||
                    sprite.getY() + sprite.getHeight() > Constants.HEIGHT || sprite.getY() < 0) {
                this.deactivate();
            }
        }


    }


    @Override
    public void act(float delta) {
        super.act(delta);
        updatePos();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (alive) {
            sprite.draw(batch);
        }


    }

    public Sprite getSprite() {
        return sprite;
    }

    public Body getBody() {
        return body;
    }


    public void deactivate() {
        this.alive = false;
    }


    @Override
    public float getY() {
        return sprite.getY();
    }

    @Override
    public float getX() {
        return sprite.getX();
    }

    @Override
    public float getWidth() {
        return sprite.getWidth();
    }

    @Override
    public float getHeight() {
        return sprite.getHeight();
    }


}
