package Animals;
import Core.*;
import Plants.SimplePlant;

import java.util.HashMap;
import java.util.Random;

public class Buffalo extends Animal {
    Random random = new Random();

    public Buffalo() {
        wight = 700;
        maxSatiety = 100;
        currentSatiety = 100;
        moveByTurn = 3;
        maxCountOnLocation = 10;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(SimplePlant.class,100);
        }
    }

    @Override
    public void getHungry() {
        currentSatiety -= 14;
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
                    currentLocation.addObject(new Buffalo());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
