package Animals;
import Core.*;

import java.util.HashMap;
import java.util.Random;

public class Fox extends Animal {
    Random random = new Random();

    public Fox() {
        wight = 8;
        maxSatiety = 2;
        currentSatiety = 2;
        moveByTurn = 2;
        maxCountOnLocation = 30;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(Rabbit.class,70);
            edable.put(Mouse.class,90);
            edable.put(Duck.class,60);
            edable.put(Caterpillar.class,40);
        }
    }

    @Override
    public void getHungry() {
        currentSatiety -= 0.28;
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
                    currentLocation.addObject(new Fox());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
