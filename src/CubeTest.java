import org.junit.Assert;

/**
 * Tests functionality of Cube
 *
 * @author Joshua Hales and Aaron Hales
 */
public class CubeTest {

    @org.junit.Test
    public void CubeConstructor() {
        Cube cube1 = new Cube();
        Assert.assertNotNull("Cube empty constructor must not be null", cube1);

        Cube cube2 = new Cube(new String[][][]{
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
        });
        Assert.assertNotNull("Cube array constructor must not be null", cube2);
    }

    @org.junit.Test
    public void CubeGetTile() {
        String[][][] tiles1 = new String[][][]
                {
                        {
                                {"W1","W2","W3"},
                                {"W4","W5","W6"},
                                {"W7","W8","W9"}
                        },
                        {
                                {"R1","R2","R3"},
                                {"R4","R5","R6"},
                                {"R7","R8","R9"}
                        },
                        {
                                {"B1","B2","B3"},
                                {"B4","B5","B6"},
                                {"B7","B8","B9"}
                        },
                        {
                                {"O1","O2","O3"},
                                {"O4","O5","O6"},
                                {"O7","O8","O9"}
                        },
                        {
                                {"G1","G2","G3"},
                                {"G4","G5","G6"},
                                {"G7","G8","G9"}
                        },
                        {
                                {"Y1","Y2","Y3"},
                                {"Y4","Y5","Y6"},
                                {"Y7","Y8","Y9"}
                        }
                };
        Cube cube1 = new Cube(tiles1);
        for (int face = 0; face < tiles1.length; face++) {
            int tileNum = 1;
            for (int row = 0; row < tiles1[face].length; row++) {
                for (int tile = 0; tile < tiles1[face][row].length; tile++) {
                    Assert.assertEquals("Tiles should be accessible", tiles1[face][row][tile], cube1.getTile(face + 1, tileNum));
                    tileNum++;
                }
            }
        }
    }

    @org.junit.Test
    public void CubeIsSolved() {
        Cube cube1 = new Cube();
        Assert.assertTrue("Initialized cube should be solved", cube1.isSolved());
        cube1.rotate(new String[] {"U"});
        Assert.assertFalse("Solved cube should not be solved after turn", cube1.isSolved());
        cube1.rotate(new String[] {"U", "U", "U"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());
        cube1.rotate(new String[] {"D", "D", "D", "D"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());
        cube1.rotate(new String[] {"L", "L", "L", "L"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());
        cube1.rotate(new String[] {"R", "R", "R", "R"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());
        cube1.rotate(new String[] {"F", "F", "F", "F"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());
        cube1.rotate(new String[] {"B", "B", "B", "B"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());

        cube1.rotate(new String[] {"U'"});
        Assert.assertFalse("Solved cube should not be solved after turn", cube1.isSolved());
        cube1.rotate(new String[] {"U'", "U'", "U'"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());
        cube1.rotate(new String[] {"D'", "D'", "D'", "D'"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());
        cube1.rotate(new String[] {"L'", "L'", "L'", "L'"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());
        cube1.rotate(new String[] {"R'", "R'", "R'", "R'"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());
        cube1.rotate(new String[] {"F'", "F'", "F'", "F'"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());
        cube1.rotate(new String[] {"B'", "B'", "B'", "B'"});
        Assert.assertTrue("Solved cube should be solved after 4 of the same turn", cube1.isSolved());

        cube1.scramble();
        Assert.assertFalse("Cube should not be solved after a scramble", cube1.isSolved());

    }
}
