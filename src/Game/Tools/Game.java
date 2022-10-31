package Game.Tools;

public class Game {

    private Ball ball;

    public Game(int cols, int rows, int totalBalls) {
        Ranges.setSize(new Coord(cols, rows));
        ball = new Ball(totalBalls);
    }

    public Box getBox(Coord coord) {
        return ball.get(coord);
    }

    public void start() {
        ball.start();
    }

    public void pressRightButton(Coord coord) {
        if (ball.isBall(coord)) {
            //ball.set(coord, Box.EMPTY);
            SaveCoord.x = coord.x;
            SaveCoord.y = coord.y;
            SaveCoord.ballColor = ball.getColor(coord);
        }
        //выбран ли шарик и если да, то запомнить его координаты
    }

    public void pressLeftButton(Coord coord, SaveCoord save) {

        Coord saveCoord = new Coord(SaveCoord.x,SaveCoord.y);

        for (Coord around : Ranges.getCoorsAround(saveCoord)){

            if (around.equals(coord)) {
                if (ball.isEmpty(coord)) {
                    ball.set(coord, SaveCoord.ballColor);
                    ball.set(saveCoord, Box.EMPTY);
                    SaveCoord.x=coord.x;
                    SaveCoord.y=coord.y;
                }
            }
        }






        //проверить
        // 1) нажата ли левая кнопка мыши?
        // 2) занята ли клетка
        // 3) рядом ли клетка
        // 4) эта клетка существует?
        //
        //выполнить
        // 1)передвинуть шарик
        // 2)удалить шарик с прошлой клетки
        // 3)поменять сохраненные координаты
    }

}
