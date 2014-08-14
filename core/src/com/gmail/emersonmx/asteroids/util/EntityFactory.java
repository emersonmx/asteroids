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

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gmail.emersonmx.asteroids.ecs.component.MotionComponent;
import com.gmail.emersonmx.asteroids.ecs.component.PhysicBodyComponent;
import com.gmail.emersonmx.asteroids.ecs.component.PlayerInputComponent;
import com.gmail.emersonmx.asteroids.ecs.component.SpriteRenderComponent;
import com.gmail.emersonmx.asteroids.ecs.component.TransformComponent;

public class EntityFactory {

    private TextureAtlas atlas;
    private PhysicBodyFactory physicBodyFactory;

    public EntityFactory(TextureAtlas atlas,
            PhysicBodyFactory physicBodyFactory) {

        this.atlas = atlas;
        this.physicBodyFactory = physicBodyFactory;
    }

    public Entity createEntity() {
        return new Entity();
    }

    public Entity createSpaceship() {
        Entity spaceship = createEntity();

        spaceship.add(createSpaceshipTransform());
        spaceship.add(createSpaceshipMotion());
        spaceship.add(new PlayerInputComponent());
        spaceship.add(
            new PhysicBodyComponent(physicBodyFactory.createSpaceship()));
        spaceship.add(new SpriteRenderComponent(createSpaceshipSprite()));

        return spaceship;
    }

    private TransformComponent createSpaceshipTransform() {
        return new TransformComponent();
    }

    private MotionComponent createSpaceshipMotion() {
        MotionComponent motion = new MotionComponent();
        motion.direction.set(0, 1);
        motion.acceleration = 1;
        motion.maxSpeed = 5;
        motion.angularVelocity = 90;

        return motion;
    }

    private Sprite createSpaceshipSprite() {
        return atlas.createSprites("game/spaceship").get(0);
    }

    public Entity createBullet() {
        return null;
    }

    public Entity createAsteroid() {
        return null;
    }

    public Entity createOVNI() {
        return null;
    }

}
