import java.util.Scanner;

/**
 * Allows a user to manipulate a Rubik's Cube.
 * <p>
 *     A user can input their own scrambled cube or use a randomly generated one. The program prints the cube and
 *     prompts the user for a move until the cube is solved.
 * </p>
 *
 * @author Joshua Hales and Aaron Hales
 */
public class UI {
    public static final String[] COLOR_ORDER = {"W", "R", "B", "O", "G", "Y"};
    public static final String[] TURNS = {"U", "U'", "D", "D'", "L", "L'", "R", "R'", "F", "F'", "B", "B'"};

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Do you want to enter your own rubik's cube or solve a generated one?\n\tY = your own (you will enter in the tiles)\n\tN = the program will generate one\nEnter Y/N: ");
        boolean ownCube = input.nextLine().equalsIgnoreCase("Y");
        Cube cube;
        String[][][] userCube = new String[6][3][3];
        if (ownCube) {
            while (true) {
                int numTile = 1;
                for (int side = 0; side < 6; side++) {
                    for (int row = 0; row < 3; row++) {
                        for (int tile = 0; tile < 3; tile++) {
                            userCube[side][row][tile] = String.format("%s", numTile);
                            numTile++;
                        }
                    }
                }
                System.out.println("Enter in a cube starting at the white face:");
                for (int side = 0; side < 6; side++) {
                    for (int row = 0; row < 3; row++) {
                        for (int tile = 0; tile < 3; tile++) {
                            userCube[side][row][tile] = "X";
                            boolean validInput = false;
                            while (!validInput) {
                                System.out.println(printCubeNumbers(userCube));
                                System.out.println("Color codes:\n\tW = White\n\tR = Red\n\tB = Blue\n\tO =Orange\n\tG = Green\n\tY = Yellow");
                                System.out.println("faces:\n\t[1 - 9] = white face\n\t[10 - 18] = Red\n\t[19 - 27] = Blue\n\t[28-36] = Orange\n\t[37 - 45] = Green\n\t[46 - 54] = Yellow");
                                System.out.print("Enter the color at the position X (W, R, B, O, G, Y): ");
                                String userInput = input.nextLine().toUpperCase();
                                if (isValidInput(COLOR_ORDER, userInput)) {
                                    userCube[side][row][tile] = userInput.toUpperCase();
                                    validInput = true;
                                }
                            }
                        }
                    }
                }
                cube = new Cube(userCube);
                if (cube.isValid()) {
                    break;
                }
                else {
                    System.out.println("The entered cube is not valid");
                }
            }
        }
        else {
            cube = new Cube();
            cube.scramble();
        }

        while (!cube.isSolved()) {
            for (int side = 0; side < cube.getNumSides(); side++) {
                for (int row = 0; row < cube.getRowLength(side); row++) {
                    userCube[side][row] = cube.getWholeCube()[side][row].clone();
                }
            }
            System.out.println(printCube(userCube));
            System.out.println("Turns:\n\tT = top face\n\tD = Bottom face\n\tL = left face\n\tR = right face\n\tF = front face\n\tB = back face\n\tturn' = inverse turn");
            System.out.print("Enter a turn (keep in mind top face is white, to do an inverse turn do turn' (eg. R')):");
            String turn = input.nextLine();
            boolean validTurn = false;
            while (!validTurn) {
                if (turn.toUpperCase().startsWith("T")) {
                    String newTurn = turn.toUpperCase().replace("T", "U");
                    turn = newTurn;
                }
                if (isValidInput(TURNS, turn)) {
                    validTurn = true;
                    cube.rotate(turn);
                }
            }
        }
        for (int side = 0; side < cube.getNumSides(); side++) {
            for (int row = 0; row < cube.getRowLength(side); row++) {
                userCube[side][row] = cube.getWholeCube()[side][row].clone();
            }
        }
        System.out.println(printCube(userCube));
        System.out.println(String.format("     Congratulations!!!!\n" +
                           "You solved the rubik's cube!\nIt took %d turns to solve\nThose turns were %s", cube.getHistory().size(), cube.getHistory()));
    }

    public static boolean isValidInput(String[] options, String entered) {
        boolean valid = false;
        for (int option = 0; option < options.length; option++) {
            if (entered.equalsIgnoreCase(options[option])) {
                return true;
            }
        }
        return false;
    }

    public static String printCube(String[][][] cube) {
        String output = "";
        output += "             ++===+===+===++\n";
        output += String.format("             || %s | %s | %s ||\n", cube[3][2][2], cube[3][2][1], cube[3][2][0]);
        output += String.format("             ++---+---+---++\n");
        output += String.format("             || %s | %s | %s ||\n", cube[3][1][2], cube[3][1][1], cube[3][1][0]);
        output += String.format("             ++---+---+---++\n");
        output += String.format("             || %s | %s | %s ||\n", cube[3][0][2], cube[3][0][1], cube[3][0][0]);
        output += String.format("++===+===+===++===+===+===++===+===+===++\n");
        output += String.format("|| %s | %s | %s || %s | %s | %s || %s | %s | %s ||\n", cube[4][2][0], cube[4][1][0], cube[4][0][0], cube[0][0][0], cube[0][0][1], cube[0][0][2], cube[2][0][2], cube[2][1][2], cube[2][2][2]);
        output += String.format("++---+---+---++---+---+---++---+---+---++\n");
        output += String.format("|| %s | %s | %s || %s | %s | %s || %s | %s | %s ||\n", cube[4][2][1], cube[4][1][1], cube[4][0][1], cube[0][1][0], cube[0][1][1], cube[0][1][2], cube[2][0][1], cube[2][1][1], cube[2][2][1]);
        output += String.format("++---+---+---++---+---+---++---+---+---++\n");
        output += String.format("|| %s | %s | %s || %s | %s | %s || %s | %s | %s ||\n", cube[4][2][2], cube[4][1][2], cube[4][0][2], cube[0][2][0], cube[0][2][1], cube[0][2][2], cube[2][0][0], cube[2][1][0], cube[2][2][0]);
        output += String.format("++===+===+===++===+===+===++===+===+===++\n");
        output += String.format("             || %s | %s | %s ||\n", cube[1][0][0], cube[1][0][1], cube[1][0][2]);
        output += String.format("             ++---+---+---++\n");
        output += String.format("             || %s | %s | %s ||\n", cube[1][1][0], cube[1][1][1], cube[1][1][2]);
        output += String.format("             ++---+---+---++\n");
        output += String.format("             || %s | %s | %s ||\n", cube[1][2][0], cube[1][2][1], cube[1][2][2]);
        output += "             ++===+===+===++\n";
        output += String.format("             || %s | %s | %s ||\n", cube[5][2][2], cube[5][2][1], cube[5][2][0]);
        output += String.format("             ++---+---+---++\n");
        output += String.format("             || %s | %s | %s ||\n", cube[5][1][2], cube[5][1][1], cube[5][1][0]);
        output += String.format("             ++---+---+---++\n");
        output += String.format("             || %s | %s | %s ||\n", cube[5][0][2], cube[5][0][1], cube[5][0][0]);
        output += "             ++===+===+===++";
        return output;

    }

    public static String printCubeNumbers(String[][][] cube) {
        String output = "";
        output += "                ++====+====+====++\n";
        output += String.format("                || %s | %s | %s ||\n", cube[3][2][2], cube[3][2][1], cube[3][2][0]);
        output += String.format("                ++----+----+----++\n");
        output += String.format("                || %s | %s | %s ||\n", cube[3][1][2], cube[3][1][1], cube[3][1][0]);
        output += String.format("                ++----+----+----++\n");
        output += String.format("                || %s | %s | %s ||\n", cube[3][0][2], cube[3][0][1], cube[3][0][0]);
        output += String.format("++====+====+====++====+====+====++====+====+====++\n");
        output += String.format("|| %s | %s | %s ||  %s |  %s |  %s || %s | %s | %s ||\n", cube[4][2][0], cube[4][1][0], cube[4][0][0], cube[0][0][0], cube[0][0][1], cube[0][0][2], cube[2][0][2], cube[2][1][2], cube[2][2][2]);
        output += String.format("++----+----+----++----+----+----++----+----+----++\n");
        output += String.format("|| %s | %s | %s ||  %s |  %s |  %s || %s | %s | %s ||\n", cube[4][2][1], cube[4][1][1], cube[4][0][1], cube[0][1][0], cube[0][1][1], cube[0][1][2], cube[2][0][1], cube[2][1][1], cube[2][2][1]);
        output += String.format("++----+----+----++----+----+----++----+----+----++\n");
        output += String.format("|| %s | %s | %s ||  %s |  %s |  %s || %s | %s | %s ||\n", cube[4][2][2], cube[4][1][2], cube[4][0][2], cube[0][2][0], cube[0][2][1], cube[0][2][2], cube[2][0][0], cube[2][1][0], cube[2][2][0]);
        output += String.format("++====+====+====++====+====+====++====+====+====++\n");
        output += String.format("                || %s | %s | %s ||\n", cube[1][0][0], cube[1][0][1], cube[1][0][2]);
        output += String.format("                ++----+----+----++\n");
        output += String.format("                || %s | %s | %s ||\n", cube[1][1][0], cube[1][1][1], cube[1][1][2]);
        output += String.format("                ++----+----+----++\n");
        output += String.format("                || %s | %s | %s ||\n", cube[1][2][0], cube[1][2][1], cube[1][2][2]);
        output += "                ++====+====+====++\n";
        output += String.format("                || %s | %s | %s ||\n", cube[5][2][2], cube[5][2][1], cube[5][2][0]);
        output += String.format("                ++----+----+----++\n");
        output += String.format("                || %s | %s | %s ||\n", cube[5][1][2], cube[5][1][1], cube[5][1][0]);
        output += String.format("                ++----+----+----++\n");
        output += String.format("                || %s | %s | %s ||\n", cube[5][0][2], cube[5][0][1], cube[5][0][0]);
        output += "                ++====+====+====++";
        return output;

    }
}
