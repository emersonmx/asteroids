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
import com.gmail.emersonmx.asteroids.component.MotionComponent;
import com.gmail.emersonmx.asteroids.component.TransformComponent;

public class MotionSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> transformMapper;
    private ComponentMapper<MotionComponent> motionMapper;

    @SuppressWarnings("unchecked")
    public MotionSystem() {
        super(Family.getFor(TransformComponent.class,
                            MotionComponent.class));

        setupMappers();
    }

    private void setupMappers() {
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        motionMapper = ComponentMapper.getFor(MotionComponent.class);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = transformMapper.get(entity);
        MotionComponent motion = motionMapper.get(entity);

        transform.position.x += motion.velocity.x * deltaTime;
        transform.position.y += motion.velocity.y * deltaTime;
        transform.rotation += motion.velocity.angleRad() * deltaTime;

        motion.velocity.setZero();
    }

}
