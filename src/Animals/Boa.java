package Animals;
import Core.*;

import java.util.HashMap;
import java.util.Random;

public class Boa extends Animal {
    Random random = new Random();

    public Boa() {
        wight = 15;
        maxSatiety = 3;
        currentSatiety = 3;
        moveByTurn = 1;
        maxCountOnLocation = 30;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(Fox.class,15);
            edable.put(Rabbit.class,20);
            edable.put(Mouse.class,40);
            edable.put(Duck.class,10);
        }
    }

    @Override
    public void getHungry() {
        currentSatiety -= 0.4;
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
                    currentLocation.addObject(new Boa());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
