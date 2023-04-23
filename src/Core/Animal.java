package Core;

import java.util.*;


public abstract class Animal extends SimulationObject {
    public int moveByTurn;
    public double maxSatiety; // Переделать в double
    public  double   currentSatiety;
    protected Map<Class<? extends SimulationObject>, Integer> edable = new HashMap<>();

    public Animal() {
        //Создать переопределяемый метод, который отнимет голод в зависимости от животного
        // Предполодительно сделать так: один ход - одни день, сделать так, чтобы если животное не ело 7 дней - умирвет,
        // => currentSatiety \ 7 = голод за один день
    }

    public void eat(Location currentLocation) throws NothingToEatException { // Работает
        if (currentSatiety <= 0){
            return;
        }
        Random random = new Random();
        int chans;
        for (SimulationObject localObject: currentLocation.getObjects()) {
            if (edable.get(localObject.getClass()) != null) {
                chans  = edable.get(localObject.getClass());
            } else {
                continue;
            }
            if (random.nextInt(1,101) <= chans) {
                this.currentSatiety += localObject.wight;
                if (this.currentSatiety > this.maxSatiety) {
                    this.currentSatiety = this.maxSatiety;
                }
                currentLocation.deleteObject(localObject);
                return;
            }
        }
        throw new NothingToEatException();
    }

    public void reproduction(Location currentLocation) throws NoPossibilityToReproduction { //Работает
        int animalCount = countAnimalByType(currentLocation);
        if (!(animalCount >= this.maxCountOnLocation)) {
            for (SimulationObject localObject: currentLocation.getObjects()) {
                if (!localObject.getClass().equals(this.getClass())) {
                    continue;
                }
                if ((this.isFemale && !(localObject.isFemale)) ||
                        (!(this.isFemale) && localObject.isFemale)) {
//                currentLocation.addObject(); Как здась указать аргумент так, чтобы создавался новый объект того
//                того же класса, что и this?
                    return;
                }
            }
        }
        throw new NoPossibilityToReproduction();
    }

    public void move(Location[][] locations) { // Работает. Изменить так, чтобы если животное уже стоит в углу, то не может
        //двигать за поле.
        if (moveByTurn <= 0) {
            return;
        }
        int valueX = this.x;
        int valueY = this.y;
        Random rand = new Random();
        
        if (rand.nextBoolean()){
            valueX += moveByTurn * chooseDirection();
        } else {
            valueY += moveByTurn * chooseDirection();
        }
        
        valueX = checkForEdge(valueX, locations.length-1);
        valueY = checkForEdge(valueY, locations[valueX].length-1);//
        
        locations[this.x][this.y].deleteObject(this);
        locations[valueX][valueY].addObject(this);
    }

    //CORE
    public boolean isHunger() { // Переопределять
        return this.currentSatiety <= this.maxSatiety / 2;
    }

    public void getHungry() {

    }

    public void lookForFood(Location[][] locations) {// Работает
        try {
            eat(locations[this.x][this.y]);
        } catch (NothingToEatException e) {
             move(locations);
        }
    }

    public boolean isDiedOfHunger() {
        return currentSatiety <= 0;
    }

    public void lookForPare(Location[][] locations) {// Работает
        try {
            reproduction(locations[this.x][this.y]);
        } catch (NoPossibilityToReproduction e) {
            move(locations);
        }
    }

    //FOR REPRODUCTION
    public int countAnimalByType(Location location) {
        int typeCount = 0;
        for (SimulationObject object: location.getObjects()) {
            if (object.getClass().equals(this.getClass())) {
                typeCount++;
            }
        }
        return typeCount;
    }

    public static class NoPossibilityToReproduction extends Exception {
    }

    //FOR MOVE
    public int chooseDirection() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            return -1;
        }
        return 1;
    }

    public int checkForEdge(int value, int loca) {
        if (value > loca){
            value = loca;
        } else if (value < 0) {
            value = 0;
        }
        return value;
    }

    //FOR EAT
    static class NothingToEatException extends Exception {
    }
}
