/**
 * Brief Description
 * <p>
 * Longer Description
 *
 * @author Joshua Hales and Aaron Hales
 */
public class TestingCube {
    public static void main(String[] args) {
        Cube cube = new Cube();
        CubeSolver cubeSolver = new CubeSolver(cube);

//        System.out.println(cube);
//        cube.rotate(new String[] {"F"});
//        System.out.println(cube);

        Cube cube1 = new Cube(new String[][][]
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
                });
        System.out.println(cube1);
        cube1.rotate(new String[] {"F"});
        System.out.println(cube1);
    }
}
