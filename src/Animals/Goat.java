package Animals;
import Core.*;
import Plants.SimplePlant;

import java.util.HashMap;
import java.util.Random;

public class Goat extends Animal {
    Random random = new Random();

    public Goat() {
        wight = 60;
        maxSatiety = 10;
        currentSatiety = 10;
        moveByTurn = 3;
        maxCountOnLocation = 140;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(SimplePlant.class,100);
        }
    }

    @Override
    public void getHungry() {
        currentSatiety -= 1.4;
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
                    currentLocation.addObject(new Goat());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
