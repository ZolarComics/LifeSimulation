package Plants;
import Core.*;

public class SimplePlant extends SimulationObject {

    public SimplePlant() {
        wight = 1;
        maxCountOnLocation = 200;
    }

    public void takeRoot(Location[][] locations) {
        int plantCount = 0;
        for (SimulationObject object: locations[this.x][this.y].getObjects()) {
            if (object.getClass().equals(SimplePlant.class)){
                plantCount++;
            }
        }
        if (plantCount < maxCountOnLocation) {
            locations[this.x][this.y].addObject(new SimplePlant());
        }

    }
}
