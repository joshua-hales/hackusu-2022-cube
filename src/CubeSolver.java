/**
 * Solves a Cube using human friendly methods
 *
 * @author Joshua Hales and Aaron Hales
 */
public class CubeSolver {
    private Cube cube;

    public CubeSolver(Cube cube) {
        this.cube = cube;
    }

    public void solve() {
        solveTop();
        solveMid();
        solveBottom();
    }

    private void solveTop() {
        whiteCross();
        whiteCorners();
    }

    private void whiteCross() {

    }

    private void whiteCorners() {

    }

    private void solveMid() {

    }

    private void solveBottom() {
        yellowCross();
        yellowCorners();
        yellowEdges();
    }

    private void yellowCross() {

    }

    private void yellowCorners() {

    }

    private void yellowEdges() {

    }
}
