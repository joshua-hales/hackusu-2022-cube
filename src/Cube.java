import java.util.ArrayList;
import java.util.Random;

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
        int numTurns = 10000;
        Random random = new Random();
        String[] options = new String[] {"U", "U'", "D", "D'", "L", "L'", "R", "R'", "F", "F'", "B", "B'"};
        String[] moves = new String[numTurns];
        for (int i = 0; i < numTurns; i++) {
            moves[i] = options[random.nextInt(options.length)];
        }
        rotate(moves);
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

    public void rotate(String command) {
        int times = 1;
        if (command.endsWith("'")) {
            times = 3;
        }
        for (int i = 0; i < times; i++) {
            if (command.startsWith("U")) {
                rotateUp();
            } else if (command.startsWith("D")) {
                rotateDown();
            } else if (command.startsWith("L")) {
                rotateLeft();
            } else if (command.startsWith("R")) {
                rotateRight();
            } else if (command.startsWith("F")) {
                rotateFront();
            } else if (command.startsWith("B")) {
                rotateBack();
            }
        }
        history.add(command);
    }

    public void rotate(String[] commands) {
        for (String command : commands) {
            this.rotate(command);
        }
    }

    private void rotateUp() {
        String[][] currentTop = new String[3][3];
        for (int row = 0; row < this.tiles[0].length; row++) {
            currentTop[row] = this.tiles[0][row].clone();
        }

        String[][] newTop = new String[3][3];
        newTop = rotateFace(currentTop).clone();

        for (int row = 0; row < this.tiles[0].length; row++) {
            this.tiles[0][row] = newTop[row].clone();
        }

        String[][] currentSides = new String[4][3];
        currentSides[0][0] = this.tiles[1][0][2];
        currentSides[0][1] = this.tiles[1][0][1];
        currentSides[0][2] = this.tiles[1][0][0];

        currentSides[1][0] = this.tiles[4][0][2];
        currentSides[1][1] = this.tiles[4][0][1];
        currentSides[1][2] = this.tiles[4][0][0];

        currentSides[2][0] = this.tiles[3][0][2];
        currentSides[2][1] = this.tiles[3][0][1];
        currentSides[2][2] = this.tiles[3][0][0];

        currentSides[3][0] = this.tiles[2][0][2];
        currentSides[3][1] = this.tiles[2][0][1];
        currentSides[3][2] = this.tiles[2][0][0];

        String[][] newSides = this.rotateSides(currentSides).clone();

        this.tiles[1][0][2] = newSides[0][0];
        this.tiles[1][0][1] = newSides[0][1];
        this.tiles[1][0][0] = newSides[0][2];

        this.tiles[4][0][2] = newSides[1][0];
        this.tiles[4][0][1] = newSides[1][1];
        this.tiles[4][0][0] = newSides[1][2];

        this.tiles[3][0][2] = newSides[2][0];
        this.tiles[3][0][1] = newSides[2][1];
        this.tiles[3][0][0] = newSides[2][2];

        this.tiles[2][0][2] = newSides[3][0];
        this.tiles[2][0][1] = newSides[3][1];
        this.tiles[2][0][0] = newSides[3][2];
    }

    private void rotateDown() {
        String[][] currentBottom = new String[3][3];
        for (int row = 0; row < this.tiles[5].length; row++) {
            currentBottom[row] = this.tiles[5][row].clone();
        }

        String[][] newBottom = new String[3][3];
        newBottom = rotateFace(currentBottom).clone();

        for (int row = 0; row < this.tiles[5].length; row++) {
            this.tiles[5][row] = newBottom[row].clone();
        }

        String[][] currentSides = new String[4][3];
        for (int side = 0; side < 4; side++) {
            for (int row = 0; row < this.tiles[side + 1].length; row++) {
                currentSides[side][row] = this.tiles[side + 1][2][row];
            }
        }

        String[][] newSides = this.rotateSides(currentSides).clone();

        for (int side = 0; side < 4; side++) {
            this.tiles[side + 1][2] = newSides[side].clone();
        }
    }

    private void rotateLeft() {
        String[][] currentLeft = new String[3][3];
        for (int row = 0; row < this.tiles[4].length; row++) {
            currentLeft[row] = this.tiles[4][row].clone();
        }

        String[][] newLeft = new String[3][3];
        newLeft = rotateFace(currentLeft).clone();

        for (int row = 0; row < this.tiles[4].length; row++) {
            this.tiles[4][row] = newLeft[row].clone();
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

        String[][] newSides = this.rotateSides(currentSides).clone();

        this.tiles[0][2][0] = newSides[0][0];
        this.tiles[0][1][0] = newSides[0][1];
        this.tiles[0][0][0] = newSides[0][2];
        this.tiles[3][0][2] = newSides[1][0];
        this.tiles[3][1][2] = newSides[1][1];
        this.tiles[3][2][2] = newSides[1][2];
        this.tiles[5][0][2] = newSides[2][0];
        this.tiles[5][1][2] = newSides[2][1];
        this.tiles[5][2][2] = newSides[2][2];
        this.tiles[1][2][0] = newSides[3][0];
        this.tiles[1][1][0] = newSides[3][1];
        this.tiles[1][0][0] = newSides[3][2];
    }

    private void rotateRight() {
        String[][] currentRight = new String[3][3];
        for (int row = 0; row < this.tiles[2].length; row++) {
            currentRight[row] = this.tiles[2][row].clone();
        }

        String[][] newRight = new String[3][3];
        newRight = rotateFace(currentRight).clone();

        for (int row = 0; row < this.tiles[2].length; row++) {
            this.tiles[2][row] = newRight[row].clone();
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

        String[][] newSides = this.rotateSides(currentSides).clone();

        this.tiles[0][2][2] = newSides[0][0];
        this.tiles[0][1][2] = newSides[0][1];
        this.tiles[0][0][2] = newSides[0][2];
        this.tiles[3][0][0] = newSides[1][0];
        this.tiles[3][1][0] = newSides[1][1];
        this.tiles[3][2][0] = newSides[1][2];
        this.tiles[5][0][0] = newSides[2][0];
        this.tiles[5][1][0] = newSides[2][1];
        this.tiles[5][2][0] = newSides[2][2];
        this.tiles[1][2][2] = newSides[3][0];
        this.tiles[1][1][2] = newSides[3][1];
        this.tiles[1][0][2] = newSides[3][2];
    }

    private void rotateFront() {
        String[][] currentFront = new String[3][3];
        for (int row = 0; row < this.tiles[1].length; row++) {
            currentFront[row] = this.tiles[1][row].clone();
        }

        String[][] newFront = new String[3][3];
        newFront = rotateFace(currentFront).clone();

        for (int row = 0; row < this.tiles[1].length; row++) {
            this.tiles[1][row] = newFront[row].clone();
        }

        String[][] currentSides = new String[4][3];
        currentSides[0][0] = this.tiles[0][2][0];
        currentSides[0][1] = this.tiles[0][2][1];
        currentSides[0][2] = this.tiles[0][2][2];

        currentSides[1][0] = this.tiles[2][0][0];
        currentSides[1][1] = this.tiles[2][1][0];
        currentSides[1][2] = this.tiles[2][2][0];

        currentSides[2][0] = this.tiles[5][2][0];
        currentSides[2][1] = this.tiles[5][2][1];
        currentSides[2][2] = this.tiles[5][2][2];

        currentSides[3][0] = this.tiles[4][2][2];
        currentSides[3][1] = this.tiles[4][1][2];
        currentSides[3][2] = this.tiles[4][0][2];

        String[][] newSides = this.rotateSides(currentSides).clone();

        this.tiles[0][2][0] = newSides[0][0];
        this.tiles[0][2][1] = newSides[0][1];
        this.tiles[0][2][2] = newSides[0][2];

        this.tiles[2][0][0] = newSides[1][0];
        this.tiles[2][1][0] = newSides[1][1];
        this.tiles[2][2][0] = newSides[1][2];

        this.tiles[5][2][0] = newSides[2][0];
        this.tiles[5][2][1] = newSides[2][1];
        this.tiles[5][2][2] = newSides[2][2];

        this.tiles[4][2][2] = newSides[3][0];
        this.tiles[4][1][2] = newSides[3][1];
        this.tiles[4][0][2] = newSides[3][2];
    }

    private void rotateBack() {
        String[][] currentBack = new String[3][3];
        for (int row = 0; row < this.tiles[3].length; row++) {
            currentBack[row] = this.tiles[3][row].clone();
        }

        String[][] newBack = new String[3][3];
        newBack = rotateFace(currentBack).clone();

        for (int row = 0; row < this.tiles[3].length; row++) {
            this.tiles[3][row] = newBack[row].clone();
        }

        String[][] currentSides = new String[4][3];
        currentSides[0][0] = this.tiles[0][0][2];
        currentSides[0][1] = this.tiles[0][0][1];
        currentSides[0][2] = this.tiles[0][0][0];

        currentSides[1][0] = this.tiles[4][0][0];
        currentSides[1][1] = this.tiles[4][1][0];
        currentSides[1][2] = this.tiles[4][2][0];

        currentSides[2][0] = this.tiles[5][0][2];
        currentSides[2][1] = this.tiles[5][0][1];
        currentSides[2][2] = this.tiles[5][0][0];

        currentSides[3][0] = this.tiles[2][2][2];
        currentSides[3][1] = this.tiles[2][1][2];
        currentSides[3][2] = this.tiles[2][0][2];

        String[][] newSides = this.rotateSides(currentSides).clone();

        this.tiles[0][0][2] = newSides[0][0];
        this.tiles[0][0][1] = newSides[0][1];
        this.tiles[0][0][0] = newSides[0][2];

        this.tiles[4][0][0] = newSides[1][0];
        this.tiles[4][1][0] = newSides[1][1];
        this.tiles[4][2][0] = newSides[1][2];

        this.tiles[5][0][2] = newSides[2][0];
        this.tiles[5][0][1] = newSides[2][1];
        this.tiles[5][0][0] = newSides[2][2];

        this.tiles[2][2][2] = newSides[3][0];
        this.tiles[2][1][2] = newSides[3][1];
        this.tiles[2][0][2] = newSides[3][2];
    }

    private String[][] rotateFace(String[][] face) {
        String tempTile = "";
        String tempTile2 = "";

        tempTile = face[2][0];
        tempTile2 = face[0][0];
        face[2][0] = face[2][2];
        face[0][0] = tempTile;
        tempTile = tempTile2;
        tempTile2 = face[0][2];
        face[0][2] = tempTile;
        tempTile = tempTile2;
        face[2][2] = tempTile;

        tempTile = face[1][2];
        face[1][2] = face[0][1];
        tempTile2 = face[2][1];
        face[2][1] = tempTile;
        tempTile = face[1][0];
        face[1][0] = tempTile2;
        face[0][1] = tempTile;

        return face;
    }

    private String[][] rotateSides(String[][] sides) {
        String[] tempRow = new String[3];
        String[] tempRow2 = new String[3];
        tempRow = sides[1].clone();
        tempRow2 = sides[2].clone();
        sides[1] = sides[0].clone();
        sides[2] = tempRow.clone();
        tempRow = tempRow2.clone();
        tempRow2 = sides[3].clone();
        sides[3] = tempRow.clone();
        sides[0] = tempRow2.clone();

        return sides;
    }
}
