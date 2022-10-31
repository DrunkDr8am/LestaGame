package Game.Tools;

import javax.swing.*;

public class Game {

    private Ball ball;

    private GameState state;

    public Game(int cols, int rows, int totalBalls) {
        Ranges.setSize(new Coord(cols, rows));
        ball = new Ball(totalBalls);
    }

    public Box getBox(Coord coord) {
        return ball.get(coord);
    }

    public GameState getState() {
        return state;
    }

    public void start() {
        ball.start();
        state = GameState.PLAYED;
    }

    public void pressRightButton(Coord coord) {
        if (gameOver())return;
        if (ball.isBall(coord)) {
            SaveCoord.x = coord.x;
            SaveCoord.y = coord.y;
            SaveCoord.ballColor = ball.getColor(coord);
        }
        //выбран ли шарик и если да, то запомнить его координаты
    }

    public void pressLeftButton(Coord coord, SaveCoord save) {

        if (gameOver())return;

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
        checkWinner();
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

    private boolean gameOver() {
        if (state==GameState.PLAYED) return false;
        int result=JOptionPane.showConfirmDialog(null, "You WIN! Try again?","Information",JOptionPane.YES_NO_OPTION);
                if(result==JOptionPane.YES_NO_OPTION)
            start();
                else stopGame();

            return true;
    }

    private void checkWinner(){
        if (state == GameState.PLAYED){
            if (ball.checkColumns())
                state=GameState.WINNER;

        }

    }

    private void stopGame(){
        System.exit(1);
    }

}
