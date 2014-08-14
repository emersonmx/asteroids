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

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gmail.emersonmx.asteroids.GameApplication;

public class BaseScreen extends ScreenAdapter {

    protected SpriteBatch batch;
    protected Viewport viewport;
    protected OrthographicCamera camera;

    protected final GameApplication app;

    public BaseScreen(GameApplication app) {
        this.app = app;

        create();
    }

    private void create() {
        createBatch();
        createViewport();
        setupCamera();
    }

    private void createBatch() {
        batch = new SpriteBatch();
    }

    private void createViewport() {
        viewport = new FitViewport(GameApplication.WINDOW_WIDTH,
                                   GameApplication.WINDOW_HEIGHT);
    }

    private void setupCamera() {
        camera = (OrthographicCamera) viewport.getCamera();
        camera.setToOrtho(false, GameApplication.WINDOW_WIDTH,
                          GameApplication.WINDOW_HEIGHT);
    }

	@Override
	public void resize (int width, int height) {
        viewport.update(width, height, true);
	}

	@Override
	public void dispose () {
        batch.dispose();
	}

}
