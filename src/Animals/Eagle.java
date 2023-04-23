package Animals;
import Core.*;

import java.util.HashMap;
import java.util.Random;

public class Eagle extends Animal {
    Random random = new Random();

    public  Eagle() {
        wight = 6;
        maxSatiety = 1;
        currentSatiety = 1;
        moveByTurn = 3;
        maxCountOnLocation = 20;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(Fox.class,10);
            edable.put(Rabbit.class,90);
            edable.put(Mouse.class,90);
            edable.put(Duck.class,80);
        }
    }

    @Override
    public void getHungry() {
        currentSatiety -= 0.14;
    }

    @Override
    public void reproduction(Location currentLocation) throws Animal.NoPossibilityToReproduction {
//        super.reproduction(currentLocation);
        int animalCount = countAnimalByType(currentLocation);
        if (!(animalCount >= this.maxCountOnLocation)) {
            for (SimulationObject localObject : currentLocation.getObjects()) {
                if (!localObject.getClass().equals(this.getClass())) {
                    continue;
                }
                if ((this.isFemale && !localObject.isFemale) ||
                        (!this.isFemale && localObject.isFemale)) {
                    currentLocation.addObject(new Eagle());
                    return;
                }
            }
        }
        throw new Animal.NoPossibilityToReproduction();
    }
}
