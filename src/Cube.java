import java.util.ArrayList;
import java.util.Arrays;

/**
 * Brief Description
 * <p>
 * Longer Description
 *
 * @author Joshua Hales and Aaron Hales
 */
public class Cube {

    private String[][][] tiles;
    private ArrayList<String> history;
    private final String[][][] solved = new String[][][]
            {
                    {
                            {"W","W","W"},
                            {"W","W","W"},
                            {"W","W","W"}
                    },
                    {
                            {"R","R","R"},
                            {"R","R","R"},
                            {"R","R","R"}
                    },
                    {
                            {"B","B","B"},
                            {"B","B","B"},
                            {"B","B","B"}
                    },
                    {
                            {"O","O","O"},
                            {"O","O","O"},
                            {"O","O","O"}
                    },
                    {
                            {"G","G","G"},
                            {"G","G","G"},
                            {"G","G","G"}
                    },
                    {
                            {"Y","Y","Y"},
                            {"Y","Y","Y"},
                            {"Y","Y","Y"}
                    }
            };

    public Cube() {

        /*
         * W = White
         * R = Red
         * B = Blue
         * O = Orange
         * G = Green
         * Y = Yellow
         */
        this.tiles = new String[][][]
                {
                    {
                        {"W","W","W"},
                        {"W","W","W"},
                        {"W","W","W"}
                    },
                    {
                        {"R","R","R"},
                        {"R","R","R"},
                        {"R","R","R"}
                    },
                    {
                        {"B","B","B"},
                        {"B","B","B"},
                        {"B","B","B"}
                    },
                    {
                        {"O","O","O"},
                        {"O","O","O"},
                        {"O","O","O"}
                    },
                    {
                        {"G","G","G"},
                        {"G","G","G"},
                        {"G","G","G"}
                    },
                    {
                        {"Y","Y","Y"},
                        {"Y","Y","Y"},
                        {"Y","Y","Y"}
                    }
                };
        this.history = new ArrayList<>();
    }

    public Cube (String[][][] tiles) {
        this.tiles = new String[3][3][3];
        for (int face = 0; face < tiles.length; face++) {
            for (int row = 0; row < tiles[face].length; row++) {
                this.tiles[face][row] = tiles[face][row].clone();
            }
        }
        this.history = new ArrayList<>();
    }

    public void scramble() {

    }

    public boolean isValid() {
        return false;
    }

    public boolean isSolved() {
        boolean solved = true;
        while (solved) {
            for (int side = 0; side < this.tiles.length; side++) {
                for (int face = 0; face < tiles.length; face++) {
                    for (int row = 0; row < this.tiles[face].length; row++) {
                        if (!this.tiles[side][face][row].equals(this.solved[side][face][row])) {
                            solved = false;
                            break;
                        }
                    }
                }
            }
        }
        return solved;
    }

    public String getTile(int face, int tile) {
        if (tile % 3 == 0) {
            tile = 4;
        }
        return tiles[face - 1][(tile%3) - 1][(tile%3) - 1];
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (String[][] face : tiles) {
            for (String[] row : face) {
                for (String tile : row) {
                    output.append(tile + " ");
                }
                output.append("\n");
            }
            output.append("\n");
        }
        output.append("history = " + history);
        return output.toString();
    }

    public void rotate(String[] commands) {
        rotateUp();
    }

    private void rotateUp() {
        String[][] currentTop = new String[3][3];
        for (int row = 0; row < this.tiles[0].length; row++) {
            for (int tile = 0; tile < this.tiles[0][0].length; tile++) {
                currentTop[row][tile] = this.tiles[0][row][tile];
            }
        }
        String[][] currentSides = new String[3][3];
        for (int face = 0; face < 5; face++) {
            for (int tile = 0; tile < this.tiles[0][0].length; tile++) {
                currentSides[face][tile] = this.tiles[face + 1][0][tile];
            }
        }
        String[] currentSideRow = new String[3];
        String[] tempSideRow = new String[3];
        for (int face = 0; face < 4; face++) {
            for (int tile = 0; tile < this.tiles[face][face].length; tile++) {
                currentSideRow[tile] = currentSides[face][tile];
                tempSideRow[tile] = currentSides[3 - face][tile];
                currentSides[3 - face][tile] = currentSideRow[tile];
                currentSideRow[tile] = tempSideRow[tile];
            }
        }
        String tempTile = "";
        String tempTileToReplace = "";

        tempTile = this.tiles[0][0][2];
        this.tiles[0][0][2] = this.tiles[0][0][0];
        tempTileToReplace = this.tiles[0][2][2];
        this.tiles[0][2][2] = tempTile;
        tempTile = tempTileToReplace;

        tempTileToReplace = this.tiles[0][2][0];
        this.tiles[0][2][0] = tempTile;
        tempTile = tempTileToReplace;

        tempTileToReplace = this.tiles[0][0][0];
        this.tiles[0][0][0] = tempTile;


        tempTile = this.tiles[0][1][2];
        this.tiles[0][1][2] = this.tiles[0][0][1];
        tempTileToReplace = this.tiles[0][2][1];
        this.tiles[0][1][2] = tempTile;
        tempTile = tempTileToReplace;

        tempTileToReplace = this.tiles[0][1][0];
        this.tiles[0][1][0] = tempTile;
        tempTile = tempTileToReplace;

        tempTileToReplace = this.tiles[0][1][0];
        this.tiles[0][1][0] = tempTile;

        for (int face = 1; face < 4; face++) {
            for (int tile = 0; tile < this.tiles[0][0].length; tile++) {
                this.tiles[face][0][tile] = currentSides[face - 1][tile];
            }
        }
    }

    private void rotateDown() {

    }

    private void rotateLeft() {

    }

    private void rotateRight() {

    }

    private void rotateFront() {

    }

    private void rotateBack() {

    }
}
