package byog.lab5;
import org.junit.Test;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private final int HEIGHT = 30;
    private final int WIDTH = 60;
    private static TERenderer window = new TERenderer();
    private static TETile[][] world;

    public HexWorld() {
        window.initialize(WIDTH, HEIGHT);
        world = new TETile[WIDTH][HEIGHT];
        // initialize the tiles by filling NOTHING(black tile)
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public void addHexagon(int xPos, int yPos, TETile[][] tiles) {
        copyTiles(tiles, xPos, yPos);
    }

    /**
     * create tiles according to size and filler 'teTile'
     * @param size
     * @param teTile
     * @return TETile[][] a 2D array which contains tiles filled by tiles
     */
    public TETile[][] createTiles(int size, TETile teTile) {
        int width = size + 2 * (size - 1),
                height = 2 * size;
        TETile[][] tiles = new TETile[height][width];
        fillUpperTiles(size, tiles, teTile);
        fillLowerTiles(size, tiles, teTile);
        fillNullTiles(size, tiles);
        return tiles;
    }

    // fill upper tiles
    private void fillUpperTiles(int size, TETile[][] tiles, TETile teTile) {
        int width = size + 2 * (size - 1),
                height = 2 * size;
        for (int i = 0; i < height / 2; i++) {
            int beginIndex = size - 1 - i,
                    endIndex = beginIndex + size + 2 * i;
            for (int j = beginIndex; j < endIndex; j++) {
                tiles[i][j] = teTile;
            }
        }
    }

    // fill lower tiles
    private void fillLowerTiles(int size, TETile[][] tiles, TETile teTile) {
        int width = size + 2 * (size - 1),
                height = 2 * size;
        for (int i = height / 2; i < height; i++) {
            int beginIndex = i % size,
                    endIndex = width - (i % size);
            for (int j = beginIndex; j < endIndex; j++) {
                tiles[i][j] = teTile;
            }
        }
    }

    /**
     * fill null tiles
     * @param size
     * @param tiles
     */
    private void fillNullTiles(int size, TETile[][] tiles) {
        int width = size + 2 * (size - 1),
                height = 2 * size;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (tiles[i][j] == null) {
                    tiles[i][j] = Tileset.NOTHING;
                }
            }
        }
    }

    // there is a subtle bug in the nested loop
    // if the width is smaller than yPos, the graph will be messy...
    private void copyTiles(TETile[][] tiles, int xPos, int yPos) {
        int height = tiles.length,
                width = tiles[0].length;
        for (int i = xPos; i < xPos + height; i++) {
            for (int j = yPos; j < yPos + width; j++) {
                this.world[i][j] = tiles[i % xPos][j % yPos];
            }
        }
    }

    private static void printDescription(TETile[][] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                System.out.printf(tiles[i][j].description() + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testCreate() {
        TETile[][] tiles = createTiles(3, Tileset.GRASS);
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                System.out.print(tiles[i][j].description() + " ");
            }
            System.out.println();
        }
    }

    // I got the wrong direction for the whole time... it initializes by [WIDTH][HEIGHT]
    // but what i do is to initialize by the reversed direction... Damn!!!
    // maybe i should leave it for a while...
    private static void locateTiles(TETile[][] tiles, int xPos, int yPos) {
        int height = tiles.length, width = tiles[0].length;
        int rowIndex = 0, columnIndex;
        for (int i = xPos; i < xPos + height; i++) {
            columnIndex = 0;
            for (int j = yPos; j < yPos + width; j++) {
                world[i][j] = tiles[rowIndex][columnIndex];
                System.out.printf(rowIndex + "" + columnIndex + tiles[rowIndex][columnIndex].description() + " ");
                columnIndex++;
            }
            rowIndex++;
            System.out.println();
        }
    }

    //TODO: Draw the hexagonal 

    public static void main(String[] args) {
        HexWorld hexWorld = new HexWorld();
        TETile[][] tiles = hexWorld.createTiles(3, Tileset.GRASS);
//        hexWorld.addHexagon(30, 15, tiles);
        locateTiles(tiles, 10, 3);
        locateTiles(tiles, 16, 3);
        window.renderFrame(world);
    }

}
