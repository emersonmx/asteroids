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
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.gmail.emersonmx.asteroids.component.MovementComponent;
import com.gmail.emersonmx.asteroids.component.PhysicBodyComponent;
import com.gmail.emersonmx.asteroids.component.TransformComponent;
import com.gmail.emersonmx.asteroids.util.ConverterUtil;

public class PhysicSystem extends EntitySystem {

    public static final float TIME_STEP = 1 / 60.f;
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;

    private ComponentMapper<TransformComponent> transformMapper;
    private ComponentMapper<MovementComponent> motionMapper;
    private ComponentMapper<PhysicBodyComponent> physicBodyMapper;

    private ImmutableArray<Entity> entities;

    private World world;
    private float accumulator;

    public PhysicSystem(World world) {
        this.world = world;
        accumulator = 0;
        setupMappers();
    }

    private void setupMappers() {
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        motionMapper = ComponentMapper.getFor(MovementComponent.class);
        physicBodyMapper = ComponentMapper.getFor(PhysicBodyComponent.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addedToEngine(Engine engine) {
        Family family = Family.getFor(TransformComponent.class,
            MovementComponent.class, PhysicBodyComponent.class);
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        entities = null;
    }

    @Override
    public void update(float deltaTime) {
        processInput(deltaTime);
        step(deltaTime);
        processOutput();
    }

    private void processInput(float deltaTime) {
        for (int i = 0; i < entities.size(); i++) {
            processInputEntity(entities.get(i), deltaTime);
        }
    }

    private void processInputEntity(Entity entity, float deltaTime) {
        MovementComponent motion = motionMapper.get(entity);
        PhysicBodyComponent bodyComponent = physicBodyMapper.get(entity);

        Body body = bodyComponent.body;
        body.applyForce(motion.velocity, body.getWorldCenter(), true);
        motion.velocity.setZero();

        body.setTransform(body.getPosition(), motion.direction.angleRad());
    }

    private void step(float deltaTime) {
        float frameTime = Math.min(deltaTime, 0.25f);
        accumulator += frameTime;
        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
            accumulator -= TIME_STEP;
        }
    }

    private void processOutput() {
        for (int i = 0; i < entities.size(); i++) {
            processOutputEntity(entities.get(i));
        }
    }

    private void processOutputEntity(Entity entity) {
        TransformComponent transform = transformMapper.get(entity);
        PhysicBodyComponent bodyComponent = physicBodyMapper.get(entity);
        Body body = bodyComponent.body;

        Vector2 position = body.getPosition();
        transform.position = ConverterUtil.unitToPixel(position);
        transform.rotation = body.getAngle() * MathUtils.radiansToDegrees;
        System.out.println("velocity: " + body.getLinearVelocity());
    }

}
