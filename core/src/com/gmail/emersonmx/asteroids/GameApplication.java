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

package com.gmail.emersonmx.asteroids;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gmail.emersonmx.asteroids.screen.GameScreen;

public class GameApplication extends Game {

    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_CENTER_X = WINDOW_WIDTH / 2;
    public static final int WINDOW_HEIGHT = 480;
    public static final int WINDOW_CENTER_Y = WINDOW_HEIGHT / 2;

    public AssetManager manager;
    public TextureAtlas atlas;

    @Override
    public void create () {
        loadResources();
        setupScreens();
    }

    private void loadResources() {
        manager = new AssetManager();
        manager.load("asteroids.atlas", TextureAtlas.class);
        manager.finishLoading();

        atlas = manager.get("asteroids.atlas");
    }

    private void setupScreens() {
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();

        manager.dispose();
    }

    public void exit() {
        Gdx.app.exit();
    }

}
