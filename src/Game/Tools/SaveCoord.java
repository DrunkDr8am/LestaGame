package Game.Tools;

public class SaveCoord {

    private   int x;
    private  int y;
    private  Box ballColor;

    public SaveCoord(int x, int y, Box ballColor) {
        this.x = x;
        this.y = y;
        this.ballColor=ballColor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Box getBallColor() {
        return ballColor;
    }

    public void setBallColor(Box ballColor) {
        this.ballColor = ballColor;
    }
}
