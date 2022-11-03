package Game.Tools;

import java.util.ArrayList;
import java.util.Random;

public class Ranges {

    private static Coord size;
    private static final int[] COLUMNS_FOR_SPAWN = {0,2,4};
    private static ArrayList<Coord> allCords;
    private static Random random = new Random();

    public static void setSize(Coord _size) {
        size = _size;
        allCords = new ArrayList<Coord>();
        for (int y = 2; y < size.y; y++)
            for (int x = 0; x < size.x; x++)
                allCords.add(new Coord(x, y));
    }

    public static Coord getSize() {
        return size;
    }

    public static ArrayList<Coord> getAllCords() {
        return allCords;
    }

    static boolean inRange(Coord coord) {
        return coord.x >= 0 && coord.x < size.x &&
                coord.y >= 0 && coord.y < size.y;
    }

    static Coord getRandomCoord(){
        return new Coord(COLUMNS_FOR_SPAWN[random.nextInt(COLUMNS_FOR_SPAWN.length)],
                         random.nextInt(2, size.y));
    }

    static ArrayList<Coord> getCoorsAround (Coord coord){
        Coord around;
        ArrayList<Coord> list = new ArrayList<Coord>();
        int i,j;
        boolean onePoint=true;
        for (int x = coord.x-1;x<= coord.x+1;x++) {
            if (onePoint) {
                i=0;
                j=0;
            } else {
                i=1;
                j=1;
            }
            for (int y = coord.y - i; y <= coord.y + j; y++)
                if (inRange(around = new Coord(x, y)))
                    if (!around.equals(coord))
                        list.add(around);
            onePoint=!onePoint;
        }
        return list;
    }
}