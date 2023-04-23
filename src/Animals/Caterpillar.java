package Animals;
import Core.*;
import Plants.SimplePlant;

import java.util.HashMap;
import java.util.Random;

public class Caterpillar extends Animal {
    Random random = new Random();

    public Caterpillar() { //Перенести на SimulationObject
        wight = 0.01;// 0,01
        maxSatiety = 0;
        currentSatiety = 0;
        moveByTurn = 0;
        maxCountOnLocation = 1000;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(SimplePlant.class,100);
        }
    }

    @Override
    public void reproduction(Location currentLocation) throws NoPossibilityToReproduction {
//        super.reproduction(currentLocation);
        int animalCount = countAnimalByType(currentLocation);
        if (!(animalCount >= this.maxCountOnLocation)) {
            for (SimulationObject localObject : currentLocation.getObjects()) {
                if (!localObject.getClass().equals(this.getClass())) {
                    continue;
                }
                if ((this.isFemale && !localObject.isFemale) ||
                        (!this.isFemale && localObject.isFemale)) {
                    currentLocation.addObject(new Caterpillar());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
