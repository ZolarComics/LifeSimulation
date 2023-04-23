package Animals;
import Core.*;
import Plants.SimplePlant;

import java.util.HashMap;
import java.util.Random;

public class Mouse extends Animal {
    Random random = new Random();

    public Mouse() {
        wight = 0.05;// 0,05
        maxSatiety = 0.01; //0,01
        currentSatiety = 0.01;
        moveByTurn = 1;
        maxCountOnLocation = 500;
        isFemale = random.nextBoolean();
        edable = new HashMap<>(); {
            edable.put(Caterpillar.class,90);
            edable.put(SimplePlant.class,100);
        }
    }

    @Override
    public void getHungry() {
        currentSatiety -= 0.0014;
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
                    currentLocation.addObject(new Mouse());
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }
}
