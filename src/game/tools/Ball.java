package game.tools;

import game.panel.GamePanel;

class Ball {

    private Matrix ballMap;
    private int totalBalls;

    Ball(int totalBalls) {
        this.totalBalls = totalBalls;
    }

    void start() {
        ballMap = new Matrix();
        for (int y = 2; y < GamePanel.ROWS; y++)
            for (int x = 0; x < GamePanel.COLS; x++) {
                if (x % 2 != 0 && y % 2 == 0) {
                    placeBlocks(x, y);
                }
            }
        for (int i = 0; i < totalBalls; i++) {
            placeBalls(i);
        }
    }

    private void placeBlocks(int x, int y) {
        Coord coord = new Coord(x, y);
        ballMap.setBox(coord, Box.PATH);
    }

    Box get(Coord coord) {
        return ballMap.getBox(coord);
    }

    private void placeBalls(int i) {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.EMPTY != ballMap.getBox(coord))
                continue;
            if (i < 5)
                ballMap.setBox(coord, Box.B1);
            else if (i < 10)
                ballMap.setBox(coord, Box.B2);
            else ballMap.setBox(coord, Box.B3);
            break;
        }
    }

    boolean isBall(Coord coord) {
        return Box.B1 == ballMap.getBox(coord) || Box.B2 == ballMap.getBox(coord) || Box.B3 == ballMap.getBox(coord);
    }

    boolean isEmpty(Coord coord) {
        return Box.EMPTY == ballMap.getBox(coord);
    }

    void set(Coord coord, Box color) {
        ballMap.setBox(coord, color);
    }

    Box getColor(Coord coord) {
        if (Box.B1 == ballMap.getBox(coord))
            return Box.B1;
        else if (Box.B2 == ballMap.getBox(coord))
            return Box.B2;
        else if (Box.B3 == ballMap.getBox(coord))
            return Box.B3;
        else return null;
    }

    boolean checkColumns() {
        Coord coord;
        for (int x = 0; x < GamePanel.COLS; x = x + 2) {
            for (int y = 2; y < GamePanel.ROWS; y++) {
                coord = new Coord(x, y);
                if (x == 0 && getColor(coord) != Box.B2)
                    return false;
                if (x == 2 && getColor(coord) != Box.B1)
                    return false;
                if (x == 4 && getColor(coord) != Box.B3)
                    return false;
            }
        }
        return true;
    }
}