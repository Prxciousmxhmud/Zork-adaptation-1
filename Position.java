
public class Position {
    public int y = 0;
    public int x = 0;
    // constructor 

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // room position 

    public int roomposition() {
        int ycoord = (int) (Math.random() * 10);
        // room posiiton = floor, y
        // returns a random position on the floor 
        return ycoord;
    }
    // shows that item is not on the map

    public Position notonmap() {
        Position nopos;
        nopos = new Position(-1, -1);
        return nopos;
    }
}

