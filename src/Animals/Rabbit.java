package Animals;
import Core.*;
import Plants.SimplePlant;

import java.util.HashMap;
import java.util.Random;

public class Rabbit extends Animal {
    Random random = new Random();

    public Rabbit() {
        wight = 2;
        maxSatiety = 0.45;
        currentSatiety = 0.45;
        moveByTurn = 2;
        maxCountOnLocation = 150;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(SimplePlant.class,100);
        }
    }

    @Override
    public void getHungry() {
        currentSatiety -= 0.064;
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
                    currentLocation.addObject(new Rabbit());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
