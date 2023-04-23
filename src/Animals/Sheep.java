package Animals;

import Core.*;
import Plants.SimplePlant;

import java.util.HashMap;
import java.util.Random;

public class Sheep extends Animal {

    Random random = new Random();

    public Sheep() {
        wight = 70;
        maxSatiety = 15;
        currentSatiety = 15;
        moveByTurn = 3;
        maxCountOnLocation = 140;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(SimplePlant.class,100);
        }
    }

    public void getHungry() {
        currentSatiety -= 2;
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
                    currentLocation.addObject(new Sheep());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
