
public class Map {
    private int Width = 0;
    private int Height = 0;
    char[][] worldmap;
    final private char EMPTY = '.';

    public Map(int width, int height) {
        this.Width = width;
        this.Height = height;
        worldmap = new char[width][height];
        this.world();
    }

    public void placeRoom(Position pos, char symbol) {
        worldmap[pos.x][pos.y] = symbol;
    }

    // display map array 
    public String display() {
        String maps = new String();
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {
                maps += worldmap[x][y];
            }
            maps += "\n";
        }
        return maps;
    }

    // need to restrict the rooms the user can see to visited and not visited, should onlysee the room on their floor or past visited rooms 
    public void world() {
// empty area value == "."
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {

                worldmap[x][y] = EMPTY;
            }
        }
    }

    public boolean outOfBounds(int x, int y) {
        return x > Width || x < 0 || y > Height || y < 0;
    }
}
