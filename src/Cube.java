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
        rotateRight();
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
        String[][] currentBottom = new String[3][3];
        for (int row = 0; row < this.tiles[5].length; row++) {
            currentBottom[row] = this.tiles[5][row].clone();
        }

        String tempTile = "";
        String tempTile2 = "";

        tempTile = currentBottom[2][0];
        tempTile2 = currentBottom[0][0];
        currentBottom[2][0] = currentBottom[2][2];
        currentBottom[0][0] = tempTile;
        tempTile = tempTile2;
        tempTile2 = currentBottom[0][2];
        currentBottom[0][2] = tempTile;
        tempTile = tempTile2;
        currentBottom[2][2] = tempTile;

        tempTile = currentBottom[1][2];
        currentBottom[1][2] = currentBottom[0][1];
        tempTile2 = currentBottom[2][1];
        currentBottom[2][1] = tempTile;
        tempTile = currentBottom[1][0];
        currentBottom[1][0] = tempTile2;
        currentBottom[0][1] = tempTile;

        for (int row = 0; row < this.tiles[5].length; row++) {
            this.tiles[5][row] = currentBottom[row].clone();
        }

        String[][] currentSides = new String[4][3];
        for (int side = 0; side < 4; side++) {
            for (int row = 0; row < this.tiles[side + 1].length; row++) {
                currentSides[side][row] = this.tiles[side + 1][2][row];
            }
        }

        String[] tempRow = new String[3];
        String[] tempRow2 = new String[3];

        tempRow = currentSides[1].clone();
        tempRow2 = currentSides[2].clone();
        currentSides[1] = currentSides[0].clone();
        currentSides[2] = tempRow.clone();
        tempRow = tempRow2.clone();
        tempRow2 = currentSides[3].clone();
        currentSides[3] = tempRow.clone();
        tempRow = tempRow2.clone();
        currentSides[0] = tempRow;

        for (int side = 0; side < 4; side++) {
            this.tiles[side + 1][2] = currentSides[side].clone();
        }
    }

    private void rotateLeft() {
        String[][] currentLeft = new String[3][3];
        for (int row = 0; row < this.tiles[4].length; row++) {
            currentLeft[row] = this.tiles[4][row].clone();
        }

        String tempTile = "";
        String tempTile2 = "";

        tempTile = currentLeft[2][0];
        tempTile2 = currentLeft[0][0];
        currentLeft[2][0] = currentLeft[2][2];
        currentLeft[0][0] = tempTile;
        tempTile = tempTile2;
        tempTile2 = currentLeft[0][2];
        currentLeft[0][2] = tempTile;
        tempTile = tempTile2;
        currentLeft[2][2] = tempTile;

        tempTile = currentLeft[1][2];
        currentLeft[1][2] = currentLeft[0][1];
        tempTile2 = currentLeft[2][1];
        currentLeft[2][1] = tempTile;
        tempTile = currentLeft[1][0];
        currentLeft[1][0] = tempTile2;
        currentLeft[0][1] = tempTile;

        for (int row = 0; row < this.tiles[4].length; row++) {
            this.tiles[4][row] = currentLeft[row].clone();
        }

        String[][] currentSides = new String[4][3];
        currentSides[0][0] = this.tiles[0][2][0];
        currentSides[0][1] = this.tiles[0][1][0];
        currentSides[0][2] = this.tiles[0][0][0];
        currentSides[1][0] = this.tiles[3][0][2];
        currentSides[1][1] = this.tiles[3][1][2];
        currentSides[1][2] = this.tiles[3][2][2];
        currentSides[2][0] = this.tiles[5][0][2];
        currentSides[2][1] = this.tiles[5][1][2];
        currentSides[2][2] = this.tiles[5][2][2];
        currentSides[3][0] = this.tiles[1][2][0];
        currentSides[3][1] = this.tiles[1][1][0];
        currentSides[3][2] = this.tiles[1][0][0];

        String[] tempRow = new String[3];
        String[] tempRow2 = new String[3];
        tempRow = currentSides[1].clone();
        tempRow2 = currentSides[2].clone();
        currentSides[1] = currentSides[0].clone();
        currentSides[2] = tempRow.clone();
        tempRow = tempRow2.clone();
        tempRow2 = currentSides[3].clone();
        currentSides[3] = tempRow.clone();
        currentSides[0] = tempRow2.clone();

        this.tiles[0][2][0] = currentSides[0][0];
        this.tiles[0][1][0] = currentSides[0][1];
        this.tiles[0][0][0] = currentSides[0][2];
        this.tiles[3][0][2] = currentSides[1][0];
        this.tiles[3][1][2] = currentSides[1][1];
        this.tiles[3][2][2] = currentSides[1][2];
        this.tiles[5][0][2] = currentSides[2][0];
        this.tiles[5][1][2] = currentSides[2][1];
        this.tiles[5][2][2] = currentSides[2][2];
        this.tiles[1][2][0] = currentSides[3][0];
        this.tiles[1][1][0] = currentSides[3][1];
        this.tiles[1][0][0] = currentSides[3][2];
    }

    private void rotateRight() {
        String[][] currentLeft = new String[3][3];
        for (int row = 0; row < this.tiles[2].length; row++) {
            currentLeft[row] = this.tiles[2][row].clone();
        }

        String tempTile = "";
        String tempTile2 = "";

        tempTile = currentLeft[2][0];
        tempTile2 = currentLeft[0][0];
        currentLeft[2][0] = currentLeft[2][2];
        currentLeft[0][0] = tempTile;
        tempTile = tempTile2;
        tempTile2 = currentLeft[0][2];
        currentLeft[0][2] = tempTile;
        tempTile = tempTile2;
        currentLeft[2][2] = tempTile;

        tempTile = currentLeft[1][2];
        currentLeft[1][2] = currentLeft[0][1];
        tempTile2 = currentLeft[2][1];
        currentLeft[2][1] = tempTile;
        tempTile = currentLeft[1][0];
        currentLeft[1][0] = tempTile2;
        currentLeft[0][1] = tempTile;

        for (int row = 0; row < this.tiles[2].length; row++) {
            this.tiles[2][row] = currentLeft[row].clone();
        }

        String[][] currentSides = new String[4][3];
        currentSides[0][0] = this.tiles[0][2][2];
        currentSides[0][1] = this.tiles[0][1][2];
        currentSides[0][2] = this.tiles[0][0][2];
        currentSides[1][0] = this.tiles[3][0][0];
        currentSides[1][1] = this.tiles[3][1][0];
        currentSides[1][2] = this.tiles[3][2][0];
        currentSides[2][0] = this.tiles[5][0][0];
        currentSides[2][1] = this.tiles[5][1][0];
        currentSides[2][2] = this.tiles[5][2][0];
        currentSides[3][0] = this.tiles[1][2][2];
        currentSides[3][1] = this.tiles[1][1][2];
        currentSides[3][2] = this.tiles[1][0][2];

        String[] tempRow = new String[3];
        String[] tempRow2 = new String[3];
        tempRow = currentSides[1].clone();
        tempRow2 = currentSides[2].clone();
        currentSides[1] = currentSides[0].clone();
        currentSides[2] = tempRow.clone();
        tempRow = tempRow2.clone();
        tempRow2 = currentSides[3].clone();
        currentSides[3] = tempRow.clone();
        currentSides[0] = tempRow2.clone();

        this.tiles[0][2][2] = currentSides[0][0];
        this.tiles[0][1][2] = currentSides[0][1];
        this.tiles[0][0][2] = currentSides[0][2];
        this.tiles[3][0][0] = currentSides[1][0];
        this.tiles[3][1][0] = currentSides[1][1];
        this.tiles[3][2][0] = currentSides[1][2];
        this.tiles[5][0][0] = currentSides[2][0];
        this.tiles[5][1][0] = currentSides[2][1];
        this.tiles[5][2][0] = currentSides[2][2];
        this.tiles[1][2][2] = currentSides[3][0];
        this.tiles[1][1][2] = currentSides[3][1];
        this.tiles[1][0][2] = currentSides[3][2];
    }

    private void rotateFront() {

    }

    private void rotateBack() {

    }
}
