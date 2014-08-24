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

package com.gmail.emersonmx.asteroids.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gmail.emersonmx.asteroids.screen.GameScreen;

public class Asteroids extends Game {

    public Assets assets;
    public SpriteBatch batch;

    @Override
    public void create () {
        loadAssets();
        createSpriteBatch();
        setupScreens();
    }

    private void loadAssets() {
        assets = new Assets();
        assets.load();
    }

    private void createSpriteBatch() {
        batch = new SpriteBatch();
    }

    private void setupScreens() {
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();

        batch.dispose();
        assets.dispose();
    }

    public void exit() {
        Gdx.app.exit();
    }

}
