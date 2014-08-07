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

package com.gmail.emersonmx.asteroids.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gmail.emersonmx.asteroids.GameApplication;

public class DesktopLauncher {

    public static void main (String[] arg) {
        LwjglApplicationConfiguration config =
            new LwjglApplicationConfiguration();

        config.title = "Asteroids";
        config.width = GameApplication.WINDOW_WIDTH;
        config.height = GameApplication.WINDOW_HEIGHT;

        new LwjglApplication(new GameApplication(), config);
    }

}
