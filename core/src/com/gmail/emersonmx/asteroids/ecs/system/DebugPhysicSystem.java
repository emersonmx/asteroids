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

package com.gmail.emersonmx.asteroids.ecs.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.gmail.emersonmx.asteroids.GameApplication;
import com.gmail.emersonmx.asteroids.util.ConverterUtil;

public class DebugPhysicSystem extends EntitySystem {

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    public DebugPhysicSystem(World world) {
        this.world = world;
        createRenderer();
        createCamera();
    }

    private void createRenderer() {
        renderer = new Box2DDebugRenderer();
    }

    private void createCamera() {
        float width = ConverterUtil.pixelToUnit(GameApplication.WINDOW_WIDTH);
        float height = ConverterUtil.pixelToUnit(GameApplication.WINDOW_HEIGHT);
        camera = new OrthographicCamera(width, height);
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void update(float deltaTime) {
        camera.update();
        renderer.render(world, camera.combined);
    }

}
