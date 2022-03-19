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
}
