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
import com.gmail.emersonmx.asteroids.component.MovementComponent;
import com.gmail.emersonmx.asteroids.component.PhysicBodyComponent;
import com.gmail.emersonmx.asteroids.component.PlayerInputComponent;
import com.gmail.emersonmx.asteroids.component.SpriteRenderComponent;
import com.gmail.emersonmx.asteroids.component.TransformComponent;
import com.gmail.emersonmx.asteroids.game.Assets;

public class EntityFactory {

    private Assets assets;
    private PhysicBodyFactory physicBodyFactory;

    public EntityFactory(Assets assets, PhysicBodyFactory physicBodyFactory) {
        this.assets = assets;
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
        spaceship.add(new SpriteRenderComponent(assets.spaceship));

        return spaceship;
    }

    private TransformComponent createSpaceshipTransform() {
        return new TransformComponent();
    }

    private MovementComponent createSpaceshipMotion() {
        MovementComponent motion = new MovementComponent();
        motion.direction.set(0, 1);
        motion.acceleration = 1;
        motion.angularVelocity = 90;

        return motion;
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
