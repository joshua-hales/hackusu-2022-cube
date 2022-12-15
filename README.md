# HackUSU 2022: Rubik's Cube Program

A Java command line program that allows users to input a scrambled cube and then prompts for rotations until the cube
is solved.

This program was created for the HackUSU 2022 hackathon and was a finalist in the general category. The program is
written in Java and uses JUnit for unit testing. This program is **not** a solver and is not implemented optimally.

You can read about the development process [here](https://joshua-hales.github.io).


## Usage

To compile the program, run the following command in the `src` directory:

```bash
javac UI.java
```

The program is run from the `src` directory with the following command:

```bash
java UI
```

The program will prompt if you want to input a cube or generate a random cube. If you choose to input a cube, you will
be prompted to enter the color for each tile, with the current tile marked with an `X`, until all tiles have been 
entered. The program will display the flattened cube at each prompt as shown below.

```
                ++====+====+====++
                || 36 | 35 | 34 ||
                ++----+----+----++
                || 33 | 32 | 31 ||
                ++----+----+----++
                || 30 | 29 | 28 ||
++====+====+====++====+====+====++====+====+====++
|| 43 | 40 | 37 ||  X |  2 |  3 || 21 | 24 | 27 ||
++----+----+----++----+----+----++----+----+----++
|| 44 | 41 | 38 ||  4 |  5 |  6 || 20 | 23 | 26 ||
++----+----+----++----+----+----++----+----+----++
|| 45 | 42 | 39 ||  7 |  8 |  9 || 19 | 22 | 25 ||
++====+====+====++====+====+====++====+====+====++
                || 10 | 11 | 12 ||
                ++----+----+----++
                || 13 | 14 | 15 ||
                ++----+----+----++
                || 16 | 17 | 18 ||
                ++====+====+====++
                || 54 | 53 | 52 ||
                ++----+----+----++
                || 51 | 50 | 49 ||
                ++----+----+----++
                || 48 | 47 | 46 ||
                ++====+====+====++
```

After the cube is entered or a random cube is generated, the program will prompt for rotations until the cube is solved.
The cube is displayed after each rotation and the program will prompt for the next rotation. Rotations are entered as a
character for the face and done relative to that face (`L`). The rotation will be clockwise unless the character is
followed by a `'` character, which causes a counterclockwise rotation (`L'`). The faces and their associated colors are
as follows:

| Face   | Face Char | Color  | Color Char |
|--------|-----------|--------|------------|
| Top    | `T`       | White  | `W`        |
| Bottom | `D`       | Yellow | `Y`        |
| Left   | `L`       | Green  | `G`        |
| Right  | `R`       | Blue   | `B`        |
| Front  | `F`       | Red    | `R`        |
| Back   | `B`       | Orange | `O`        |

The flattened cube is displayed as follows:

```
             ++===+===+===++
             || O | O | O ||
             ++---+---+---++
             || O | O | O ||
             ++---+---+---++
             || O | O | O ||
++===+===+===++===+===+===++===+===+===++
|| G | G | G || W | W | W || B | B | B ||
++---+---+---++---+---+---++---+---+---++
|| G | G | G || W | W | W || B | B | B ||
++---+---+---++---+---+---++---+---+---++
|| G | G | G || W | W | W || B | B | B ||
++===+===+===++===+===+===++===+===+===++
             || R | R | R ||
             ++---+---+---++
             || R | R | R ||
             ++---+---+---++
             || R | R | R ||
             ++===+===+===++
             || Y | Y | Y ||
             ++---+---+---++
             || Y | Y | Y ||
             ++---+---+---++
             || Y | Y | Y ||
             ++===+===+===++
```

The program can be terminated at any time by entering `^C`.

## Documentation

Limited documentation is available in the [`doc`](doc) directory. It describes the initial intended functionality. There
are also diagrams to help visualize cube operations and implementation. The diagrams were created using
[Lucidchart](https://www.lucidchart.com/pages/).
