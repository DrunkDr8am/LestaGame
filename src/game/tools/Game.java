package game.tools;

import javax.swing.*;

public class Game {

    private Ball ball;

    private State state;

    public Game(int cols, int rows, int totalBalls) {
        Ranges.setSize(new Coord(cols, rows));
        ball = new Ball(totalBalls);
    }

    public Box getBox(Coord coord) {
        return ball.get(coord);
    }

    public void start() {
        ball.start();
        state = State.PLAYED;
        checkWinner();
        if(state==State.WINNER)//шанс 1 к 1009008
            start();
    }

    public void pressRightButton(Coord coord, SaveCoord save) {
        if (gameOver()) return;
        if (ball.isBall(coord)) {
            save.setX(coord.x);
            save.setY(coord.y);
            save.setBallColor(ball.getColor(coord));
        }
    }

    public void pressLeftButton(Coord coord, SaveCoord save) {
        if (gameOver()) return;
        Coord saveCoord = new Coord(save.getX(), save.getY());
        for (Coord around : Ranges.getCoorsAround(saveCoord)) {
            if (around.equals(coord)) {
                if (ball.isEmpty(coord)) {
                    ball.set(coord, save.getBallColor());
                    ball.set(saveCoord, Box.EMPTY);
                    save.setX(coord.x);
                    save.setY(coord.y);
                }
            }
        }
        checkWinner();
    }

    private boolean gameOver() {
        if (state == State.PLAYED) return false;
        int result = JOptionPane.showConfirmDialog(null, "You WIN! Try again?", "Information", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_NO_OPTION)
            start();
        else stopGame();
        return true;
    }

    private void checkWinner() {
        if (state == State.PLAYED) {
            if (ball.checkColumns())
                state = State.WINNER;
        }
    }

    private void stopGame() {
        System.exit(1);
    }
}