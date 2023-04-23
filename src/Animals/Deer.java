package Animals;
import Core.*;
import Plants.SimplePlant;

import java.util.HashMap;
import java.util.Random;

public class Deer extends Animal {
    Random random = new Random();

    public Deer() {
        wight = 300;
        maxSatiety = 50;
        currentSatiety = 50;
        moveByTurn = 4;
        maxCountOnLocation = 20;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(SimplePlant.class,100);
        }
    }

    @Override
    public void getHungry() {
        currentSatiety -= 7;
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
                    currentLocation.addObject(new Deer());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
