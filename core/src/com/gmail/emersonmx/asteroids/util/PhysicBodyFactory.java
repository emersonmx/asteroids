/*
  Copyright (C) 2014 Emerson Max de Medeiros Silva

  This file is part of asteroids.

  asteroids is free software: you can redistribute it and/or modify
  it under the terms of the GNU General Public License as published by
  the Free Software Foundation, either version 3 of the License, or
  (at your option) any later version.

  asteroids is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with asteroids.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.gmail.emersonmx.asteroids.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.gmail.emersonmx.asteroids.GameApplication;

public class PhysicBodyFactory {

    private World world;

    public PhysicBodyFactory(World world) {
        this.world = world;
    }

    private FixtureDef createDefaultFixture() {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 1;
        fixtureDef.restitution = 0;
        fixtureDef.density = 1;
        return fixtureDef;
    }

    public Body createSpaceship() {
        BodyDef bodyDef = createSpaceshipBodyDef();
        Body body = world.createBody(bodyDef);
        body.setAngularDamping(100);
        PolygonShape shape = createSpaceshipShape();
        FixtureDef fixtureDef = createDefaultFixture();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();

        return body;
    }

    private BodyDef createSpaceshipBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        float centerX =
            ConverterUtil.pixelToUnit(GameApplication.WINDOW_CENTER_X);
        float centerY =
            ConverterUtil.pixelToUnit(GameApplication.WINDOW_CENTER_Y);

        bodyDef.position.set(centerX, centerY);

        return bodyDef;
    }

    private PolygonShape createSpaceshipShape() {
        Vector2[] vertices = createSpaceshipShapeVertices();
        PolygonShape shape = new PolygonShape();
        shape.set(vertices);

        return shape;
    }

    private Vector2[] createSpaceshipShapeVertices() {
        return new Vector2[] {
            ConverterUtil.pixelToUnit(new Vector2(0, 0)),
            ConverterUtil.pixelToUnit(new Vector2(32, 0)),
            ConverterUtil.pixelToUnit(new Vector2(16, 32))
        };
    }

}
