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

        System.out.println(cube);
        cube.rotate(new String[] {});
        System.out.println(cube);
    }
}
