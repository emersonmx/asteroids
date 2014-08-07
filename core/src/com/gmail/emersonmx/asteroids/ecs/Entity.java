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

import com.badlogic.gdx.utils.ObjectMap;

public class Entity {

    private static final int DEFAULT_COMPONENT_SIZE = 10;

    private ObjectMap<Class<? extends Component>, Component> components;

    public Entity() {
        components = new ObjectMap<Class<? extends Component>,
                                   Component>(DEFAULT_COMPONENT_SIZE);
    }

    public <T extends Component> boolean contains(Class<T> componentClass) {
        return components.containsKey(componentClass);
    }

    public Component get(Class<? extends Component> componentClass) {
        return components.get(componentClass);
    }

    public void add(Component component) {
        components.put(component.getClass(), component);
    }

    public <T extends Component> void remove(Class<T> componentClass) {
        components.remove(componentClass);
    }

}
