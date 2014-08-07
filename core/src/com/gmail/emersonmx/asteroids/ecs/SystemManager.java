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

package com.gmail.emersonmx.asteroids.ecs;

import com.badlogic.gdx.utils.Array;

public class SystemManager {

    private static final int DEFAULT_SYSTEMS_SIZE = 16;

    private Array<System> systems;

    public SystemManager() {
        this(DEFAULT_SYSTEMS_SIZE);
    }

    public SystemManager(int size) {
        systems = new Array<System>(size);
    }

    public void add(System system) {
        systems.add(system);
        system.create();
    }

    public void remove(System system) {
        system.dispose();
        systems.removeValue(system, false);
    }

    public void updateSystems(float delta) {
        for (System system : systems) {
            system.update(delta);
        }
    }

}
