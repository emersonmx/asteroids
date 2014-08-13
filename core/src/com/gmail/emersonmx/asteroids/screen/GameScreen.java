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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.gmail.emersonmx.asteroids.GameApplication;

public class GameScreen extends ScreenAdapter {

    private final GameApplication app;

    public GameScreen(GameApplication app) {
        this.app = app;
    }

    @Override
    public void show () {
        setupGL();
    }

    private void setupGL() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void render (float delta) {
        updateViewport();
        updateProjectionMatrix();
        clearScreen();
        draw();
    }

    private void updateViewport() {
        app.viewport.update();
    }

    private void updateProjectionMatrix() {
        app.batch.setProjectionMatrix(app.camera.combined);
    }

    private void clearScreen() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void draw() {
        drawBegin();
        drawEnd();
    }

    private void drawBegin() {
        app.batch.begin();
    }

    private void drawEnd() {
        app.batch.end();
    }

}
