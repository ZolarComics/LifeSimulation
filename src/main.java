import Animals.*;
import Core.*;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println(0.01 / 2);
        Random random = new Random();
        Scanner in = new Scanner(System.in);
//        int locationSizeX = in.nextInt();
//        int locationSizeY = in.nextInt();
        int locationSizeX = 3;
        int locationSizeY = 3;
        Location[][] locations = new Location[locationSizeX][locationSizeY];
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                locations[i][j] = new Location(i,j);
            }
        }
        System.out.println(Arrays.deepToString(locations));

        int x = random.nextInt(0,locationSizeX);
        int y = random.nextInt(0,locationSizeY);

        for (int i = 0; i < 1; i++) {
            locations[x][y].addObject(new Bear());
            locations[x][y].addObject(new Wolf());
            locations[x][y].addObject(new Horse());
            locations[x][y].addObject(new Sheep());
            locations[x][y].addObject(new Boa());
            System.out.println(locations[x][y]);
//            System.out.println(locations[x][y].getObjects().get(i).isFemale);
        }
        System.out.println(locations[x][y].getObjects());
        Bear bear = (Bear) locations[x][y].getObjects().get(0);
        System.out.println(bear.x);
        System.out.println(bear.y);
        bear.currentSatiety -= 5;
        System.out.println(bear.currentSatiety);
        bear.lookForFood(locations);
//        Wolf wolf = (Wolf) locations[x][y].getObjects().get(1);
//        System.out.println(wolf.x);
//        System.out.println(wolf.y);
//        wolf.currentSatiety -= 5;
//        System.out.println(wolf.currentSatiety);
//        wolf.lookForFood(locations);
        System.out.println(locations[x][y].getObjects());
        System.out.println(bear.currentSatiety);
        System.out.println(bear.x);
        System.out.println(bear.y);
        bear.getHungry();
        System.out.println(bear.currentSatiety);
//        System.out.println(wolf.currentSatiety);
//        System.out.println(wolf.x);
//        System.out.println(wolf.y);
    }


}
