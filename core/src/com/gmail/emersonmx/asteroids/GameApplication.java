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

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class GameApplication extends ApplicationAdapter {

    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = 480;

	@Override
	public void create () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
	}

	@Override
	public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

}
