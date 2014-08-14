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

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gmail.emersonmx.asteroids.ecs.component.SpriteRenderComponent;
import com.gmail.emersonmx.asteroids.ecs.component.TransformComponent;

public class SpriteRenderSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> transformMapper;
    private ComponentMapper<SpriteRenderComponent> spriteMapper;

    private Viewport viewport;
    private Camera camera;
    private SpriteBatch batch;

    @SuppressWarnings("unchecked")
    public SpriteRenderSystem(SpriteBatch batch, Viewport viewport) {
        super(Family.getFor(TransformComponent.class,
                            SpriteRenderComponent.class));

        this.viewport = viewport;
        this.camera = viewport.getCamera();
        this.batch = batch;

        setupMappers();
    }

    private void setupMappers() {
        transformMapper = ComponentMapper.getFor(TransformComponent.class);
        spriteMapper = ComponentMapper.getFor(SpriteRenderComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        updateViewport();
        updateTransformMatrix();
        clearScreen();
        draw(deltaTime);
    }

    private void updateViewport() {
        viewport.update();
    }

    private void updateTransformMatrix() {
        batch.setProjectionMatrix(camera.combined);
    }

    private void clearScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void draw(float deltaTime) {
        beginDraw();
        super.update(deltaTime);
        endDraw();
    }

    private void beginDraw() {
        batch.begin();
    }

    private void endDraw() {
        batch.end();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = transformMapper.get(entity);
        SpriteRenderComponent spriteComponent = spriteMapper.get(entity);
        Sprite sprite = spriteComponent.sprite;

        sprite.setCenter(transform.position.x, transform.position.y);
        sprite.setRotation(transform.rotation);

        sprite.draw(batch);
    }

}
