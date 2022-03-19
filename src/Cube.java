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

    public Cube() {
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
        return false;
    }

    public String getTile(int face, int tile) {
        return tiles[face - 1][(tile%3) - 1][(tile%3) - 1];
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        return "Cube{" +
                "tiles=" + Arrays.toString(tiles) +
                ", history=" + history +
                '}';
    }

    public void rotate(String[] commands) {

    }

    private void rotateUp() {

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
