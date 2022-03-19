import java.util.ArrayList;

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
        this.tiles = new String[6][3][3];
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
        for (int side = 0; side < this.tiles.length; side++) {
            for (int face = 0; face < this.tiles[side].length; face++) {
                for (int row = 0; row < this.tiles[face].length; row++) {
                    if (!this.tiles[side][face][row].equals(this.solved[side][face][row])) {
                        solved = false;
                        break;
                    }
                }
            }
        }
        return solved;
    }

    public String getTile(int face, int tile) {
        int col = tile % 3;
        int row = (tile - 1) / 3;
        if (col == 0) {
            col = 3;
        }
        return tiles[face - 1][row][col - 1];
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
        String[][] currentSides = new String[4][3];
        for (int face = 0; face < 3; face++) {
            for (int tile = 0; tile < this.tiles[0][0].length; tile++) {
                currentSides[face][tile] = this.tiles[face + 2][0][tile];
            }
        }

        String[] currentSideRow;
        String[] tempSideRow;
        currentSideRow = this.tiles[1][0].clone();
        for (int face = 0; face < 3; face++) {
            tempSideRow = this.tiles[4 - face][0].clone();
            this.tiles[4 - face][0] = currentSideRow.clone();
            currentSideRow = tempSideRow.clone();
        }

        String[][] currentTop = new String[3][3];
        for (int row = 0; row < this.tiles[0].length; row++) {
            for (int tile = 0; tile < this.tiles[0][row].length; tile++) {
                currentTop[row][tile] = this.tiles[0][row][tile];
            }
        }
        String tempTile = "";
        String tempTileToReplace = "";

        tempTile = currentTop[0][2];
        currentTop[0][2] = currentTop[0][0];
        tempTileToReplace = currentTop[2][2];
        currentTop[2][2] = tempTile;
        tempTile = tempTileToReplace;

        tempTileToReplace = currentTop[2][0];
        currentTop[2][0] = tempTile;
        tempTile = tempTileToReplace;

        currentTop[0][0] = tempTile;


        tempTile = currentTop[1][2];
        currentTop[1][2] = currentTop[0][1];
        tempTileToReplace = currentTop[2][1];
        currentTop[2][1] = tempTile;
        tempTile = currentTop[1][0];
        currentTop[1][0] = tempTileToReplace;
        currentTop[0][1] = tempTile;

        for (int face = 0; face < 3; face++) {
            for (int tile = 0; tile < this.tiles[0][0].length; tile++) {
                this.tiles[face + 1][0][tile] = currentSides[face][tile];
            }
        }
        for (int row = 0; row < this.tiles[0].length; row++) {
            for (int tile = 0; tile < this.tiles[0].length; tile++) {
                this.tiles[0][row][tile] = currentTop[row][tile];
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
