package Animals;
import Core.*;

import java.util.HashMap;
import java.util.Random;

public class Wolf extends Animal {
    Random random = new Random();

    public Wolf() {
        moveByTurn = 3;
        maxSatiety = 8;
        currentSatiety = 8;
        wight = 50;
        maxCountOnLocation = 30;
        isFemale = random.nextBoolean();
        edable = new HashMap<>();{
            edable.put(Sheep.class, 70);
            edable.put(Horse.class,10);
            edable.put(Deer.class,15);
            edable.put(Rabbit.class,60);
            edable.put(Mouse.class,80);
            edable.put(Goat.class,60);
            edable.put(Boar.class,15);
            edable.put(Buffalo.class,10);
            edable.put(Duck.class,40);
        }
    }

    public void getHungry() {
        currentSatiety -= 1;
    }

    @Override
    public void reproduction(Location currentLocation) throws NoPossibilityToReproduction {
//        super.reproduction(currentLocation);
        int animalCount = countAnimalByType(currentLocation);
        if (!(animalCount >= this.maxCountOnLocation)) {
            for (SimulationObject localObject: currentLocation.getObjects()) {
                if (!localObject.getClass().equals(this.getClass())) {
                    continue;
                }
                if ((this.isFemale && !localObject.isFemale) ||
                        (!this.isFemale && localObject.isFemale)) {
                    currentLocation.addObject(new Wolf());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
