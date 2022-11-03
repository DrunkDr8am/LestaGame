package game.tools;

class Matrix {

    private Box[][] matrix;

    public Matrix() {
        matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coord coord : Ranges.getAllCords())
            matrix[coord.x][coord.y] = Box.EMPTY;
    }

    public Box getBox(Coord coord) {
        if (Ranges.inRange(coord))
            return matrix[coord.x][coord.y];
        return null;
    }

    public void setBox(Coord coord, Box box) {
        if (Ranges.inRange(coord))
            matrix[coord.x][coord.y] = box;
    }
}