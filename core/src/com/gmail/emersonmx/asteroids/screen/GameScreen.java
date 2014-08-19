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

package com.gmail.emersonmx.asteroids.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.gmail.emersonmx.asteroids.GameApplication;
import com.gmail.emersonmx.asteroids.ecs.system.DebugPhysicSystem;
import com.gmail.emersonmx.asteroids.ecs.system.InputSystem;
import com.gmail.emersonmx.asteroids.ecs.system.PhysicSystem;
import com.gmail.emersonmx.asteroids.ecs.system.SpriteRenderSystem;
import com.gmail.emersonmx.asteroids.util.EntityFactory;
import com.gmail.emersonmx.asteroids.util.PhysicBodyFactory;

public class GameScreen extends BaseScreen {

    private World world;
    private PhysicBodyFactory physicBodyFactory;
    private Engine engine;
    private EntityFactory entityFactory;

    public GameScreen(GameApplication app) {
        super(app);

        initializePhysicWorld();
        initializeEngine();
    }

    private void initializePhysicWorld() {
        createWorld();
        setupBodyFactory();
    }

    private void createWorld() {
        world = new World(new Vector2(0, 0), true);
    }

    private void setupBodyFactory() {
        physicBodyFactory = new PhysicBodyFactory(world);
    }

    private void initializeEngine() {
        createEntityFactory();
        createEngine();
        createEntities();
        createSystems();
    }

    private void createEntityFactory() {
        entityFactory = new EntityFactory(app.atlas, physicBodyFactory);
    }

    private void createEngine() {
        engine = new Engine();
    }

    private void createEntities() {
        engine.addEntity(entityFactory.createSpaceship());
    }

    private void createSystems() {
        engine.addSystem(new InputSystem());
        engine.addSystem(new PhysicSystem(world));
        engine.addSystem(new SpriteRenderSystem(batch, camera));
        engine.addSystem(new DebugPhysicSystem(world));
    }

    @Override
    public void show () {
        setupClearColor();
    }

    private void setupClearColor() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void render (float delta) {
        engine.update(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
    }

}
