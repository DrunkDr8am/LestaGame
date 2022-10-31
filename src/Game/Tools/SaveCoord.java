package Game.Tools;

public class SaveCoord {

    public static int x=-1;

    public static int y=-1;

    public static Box ballColor=null;

    public SaveCoord(){

    }

    public SaveCoord(int x, int y, Box color) {
       this.x=x;
       this.y=y;
        ballColor = color;

    }
}
