//To import all the classes and interfaces within java
import java.util.*;


class PuzzleTestHarness {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        Puzzle myPuzzle = new Puzzle();

        
        // created two arrays. one for the generated puzzle, the other is solution
        int puzzSize = 0;
        myPuzzle.setPuzzSize(puzzSize);
        char[][] puzzle = myPuzzle.emptyGrid(myPuzzle.getPuzzSize());
        char[][] solution = myPuzzle.emptyGrid(myPuzzle.getPuzzSize());
        System.out.println(" \n Welcome to my word search generator \n");
        
        // while loop runs the program
        boolean runGame = true;
        while(runGame) {
            // array list holds prompted words
            ArrayList<String> words = new ArrayList<String>();
            myPuzzle.printIntro();
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Enter your menu option (1-4): ");
                if (input.hasNextInt()) {
                    int menuOpt = input.nextInt();
                    validInput = true;
                    switch (menuOpt) {

                        // generate a puzzle and solution
            case 1 :  System.out.print("\n type 'done' when your finsished entering words \n");
                        myPuzzle.enterWords(input, words);
                        puzzSize = myPuzzle.calcSize(words);
                        solution = myPuzzle.emptyGrid(puzzSize);
                        solution = myPuzzle.createPuzzle(solution, words, rand);
                        myPuzzle.setSolution(solution);
                        puzzle = myPuzzle.jumblePuzzle(solution, rand);
                        myPuzzle.setPuzzle(puzzle);
                        break;

                        // display puzzle. puzzle size is 0 if no words have been added
            case 2 :  if(puzzSize == 0) {
                            System.out.println("\n A word search must be generated in order to print");
                        } else {
                            myPuzzle.displayPuzzle(myPuzzle.getPuzzle());
                        }
                        break;
                        
                        // display solution
            case 3 :  if(puzzSize == 0) {
                           System.out.println("\n A word search must be generated in order to print");
                      } else {
                            myPuzzle.displayPuzzle(myPuzzle.getSolution());
                        }
                        break;

                        // quit
            case 4 :  runGame = false;
                        break;

                        // Display a message in case of invalid option input
            default  :  System.out.println("Invalid Option: Please select a valid option between 1 and 4");
        }
                    // rest of the switch case code here
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    input.nextLine(); // consume the invalid input
                }
            }
           
        }
    } 
    
}