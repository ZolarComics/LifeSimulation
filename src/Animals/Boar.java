package Animals;
import Core.*;
import Plants.SimplePlant;

import java.util.HashMap;
import java.util.Random;

public class Boar extends Animal {
    Random random = new Random();

    public Boar() {
        wight = 400;
        maxSatiety = 50;
        currentSatiety = 50;
        moveByTurn = 2;
        maxCountOnLocation = 50;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(Mouse.class,50);
            edable.put(Caterpillar.class,90);
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
                    currentLocation.addObject(new Boar());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
