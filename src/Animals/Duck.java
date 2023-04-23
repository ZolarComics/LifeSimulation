package Animals;
import Core.*;
import Plants.SimplePlant;

import java.util.HashMap;
import java.util.Random;

public class Duck extends Animal {
    Random random = new Random();

    public Duck() {
        wight = 1;
        maxSatiety = 0.015; //0,15
        currentSatiety = 0.015;
        moveByTurn = 4;
        maxCountOnLocation = 200;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(Caterpillar.class,90);
            edable.put(SimplePlant.class,100);
        }
    }

    @Override
    public void getHungry() {
        currentSatiety -= 0.002;
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
                    currentLocation.addObject(new Duck());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
