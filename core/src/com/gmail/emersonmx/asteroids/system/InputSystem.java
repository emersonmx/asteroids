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

package com.gmail.emersonmx.asteroids.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.gmail.emersonmx.asteroids.component.MovementComponent;
import com.gmail.emersonmx.asteroids.component.PlayerInputComponent;

public class InputSystem extends IteratingSystem {

    private ComponentMapper<MovementComponent> motionMapper;

    @SuppressWarnings("unchecked")
    public InputSystem() {
        super(Family.getFor(PlayerInputComponent.class,
                            MovementComponent.class));

        setupMappers();
    }

    private void setupMappers() {
        motionMapper = ComponentMapper.getFor(MovementComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        MovementComponent motion = motionMapper.get(entity);

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            motion.direction.rotate(motion.angularVelocity * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            motion.direction.rotate(-motion.angularVelocity * deltaTime);
        }

        if (Gdx.input.isKeyPressed(Keys.UP)) {
            motion.velocity
                .add(motion.direction)
                .scl(motion.acceleration);
        }
    }

}
