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

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

    private AssetManager manager;
    private TextureAtlas atlas;

    public Sprite spaceship;
    public Sprite bullet;

    public void load() {
        loadResources();
        setupResources();
    }

    private void loadResources() {
        manager = new AssetManager();
        manager.load("asteroids.atlas", TextureAtlas.class);
        manager.finishLoading();
    }

    private void setupResources() {
        atlas = manager.get("asteroids.atlas");

        spaceship = atlas.createSprite("game/spaceship");
        bullet = atlas.createSprite("game/bullet");
    }

    @Override
    public void dispose() {
        if (manager != null) {
            manager.dispose();
        }
    }

}
