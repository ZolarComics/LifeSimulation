package Core;

import Core.SimulationObject;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private List<SimulationObject> objects;
    private int x;
    private int y;

    public Location(int x, int y) {
        this.y = y;
        this.x = x;
        objects = new ArrayList<>();
    }

    public List<SimulationObject> getObjects() {
        return objects;
    }

    public void setObjects(List<SimulationObject> objects) {
        this.objects = objects;
    }

    public void addObject(SimulationObject object) {
        objects.add(object);
        object.x = this.x;
        object.y = this.y;
    }

    public void deleteObject(SimulationObject object) {
        objects.remove(object);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
