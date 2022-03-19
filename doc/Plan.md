# Software Development Plan

## Phase 0: Requirements Specification *(10%)*

**Deliver:**

* A detailed written description of the problem this program aims to solve.
* Describe what a *good* solution looks like.
    * List what you already know how to do.
    * Point out any challenges that you can foresee.

This program aims to be a rubik's cube solver using human friendly methods. It does not seek to be a
demonstration of fastest or most optimal solutions. Rendering of the cube is also not a primary
consideration.

A scrambled cube pattern is either input by the user or created by the program from a solved cube.
The program then searches for a solution and displays to the user instructions to follow.

### Questions and Ideas

* How is a cube displayed?
    * Animated 3d cube (probably too hard for timeframe and current skills)
    * Stationary 3d cube that can be flipped with a button (probably still difficult)
    * Unfolded faces view (not very pretty or easy to follow, but way easier to code)
* How do users input a scrambled cube?
    * Tap on faces
        * Multiple taps to cycle color options **or**
        * Pick from color palette
* How are solving instructions displayed?
    * Animated cube (again difficult)
    * Animated unfolded cube
    * List of text instructions (are instructions relative to specific faces?)
* What method of solving is being used?
    * Solving by layers
    * Maybe use a search algorithm to find best solutions between checkpoints?
* Does the program recognize invalid cube patterns?
    * Can be at least partially done by checking that all pieces are accounted for (corners, etc.)
* How is a cube represented in code?
    * An array


## Phase 1: System Analysis *(10%)*

**Deliver:**

* List all of the data that is used by the program, making note of where it comes from.
* Explain what form the output will take.
* Describe what algorithms and formulae will be used (but don't write them yet).

## Phase 2: Design *(30%)*

**Deliver:**

* Function signatures that include:
    * Descriptive names.
    * Parameter lists.
    * Documentation strings that explain the purpose, inputs and outputs.
* Pseudocode that captures how each function works.
    * Explain what happens in the face of good and bad input.
    * Write a few specific examples that occurred to you.

## Phase 3: Implementation *(15%)*

**Deliver:**

* (More or less) working Python code.
* Note any relevant and interesting events that happened while you wrote the code.
    * e.g. things you learned, things that didn't go according to plan

## Phase 4: Testing & Debugging *(30%)*

**Deliver:**

* A set of test cases that you have personally run on your computer.
    * Include a description of what happened for each test case.
    * For any bugs discovered, describe their cause and remedy.
* Write your test cases in plain language such that a non-coder could run them and replicate your experience.

## Phase 5: Deployment *(5%)*

**Deliver:**

* Your repository pushed to GitLab.
* **Verify** that your final commit was received by browsing to its project page on GitLab.
    * Review the project to ensure that all required files are present and in correct locations.
* **Validate** that your submission is complete and correct by cloning it to a new location on your computer and
  re-running it.
    * Run through your test cases to avoid nasty surprises.

## Phase 6: Maintenance

**Deliver:**

* Write brief and honest answers to these questions: *(Note: do this before you complete **Phase 5: Deployment**)*
    * What parts of your program are sloppily written and hard to understand?
        * Are there parts of your program which you aren't quite sure how/why they work?
        * If a bug is reported in a few months, how long would it take you to find the cause?
    * Will your documentation make sense to
        * anybody besides yourself?
        * yourself in six month's time?
    * How easy will it be to add a new feature to this program in a year?
    * Will your program continue to work after upgrading
        * your computer's hardware?
        * the operating system?
        * to the next version of Python?
