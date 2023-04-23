package Animals;
import Core.*;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Random;

public class Bear extends Animal {
    Random random = new Random();

    public Bear(){
        wight = 500;
        maxSatiety = 80;
        currentSatiety = 80;
        moveByTurn = 2;
        maxCountOnLocation = 5;
        isFemale = random.nextBoolean();
        edable  = new HashMap<>(); {
            edable.put(Boa.class,80);
            edable.put(Horse.class,40);
            edable.put(Deer.class,80);
            edable.put(Rabbit.class,80);
            edable.put(Mouse.class,90);
            edable.put(Goat.class,70);
            edable.put(Sheep.class,70);
            edable.put(Boar.class,50);
            edable.put(Buffalo.class,20);
            edable.put(Duck.class,10);
        }
    }

    @Override
    public void getHungry() {
        currentSatiety -= 11.4;
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
                    currentLocation.addObject(new Bear());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
