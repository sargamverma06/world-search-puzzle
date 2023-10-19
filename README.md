# World-Search-Puzzle

This java program, generates and prints a word search puzzle for the user. The user has the 
option to generate a new word search puzzle, print out the generated puzzle, print out the 
solution to the generated puzzle, or quit the program by entering the corresponding number. 
The development environment for this project is Java 19. The technologies and tools used in this project 
include Java Collections Framework for managing the list of words, Scanner class for taking input from 
the user, and Random class for generating random numbers used for word placement in the puzzle. 

The “Puzzle” class contains instance variables to store a Scanner object for user input, an ArrayList to 
store the words, the size of the puzzle, a 2D char array to represent the puzzle, a 2D char array to 
represent the solution, a String variable to store a word, a String variable to store the direction of the 
word in the puzzle, and two int variables to store the row and column numbers of the first letter of the 
word in the puzzle. 

The Puzzle class also contains several methods, including: 

 printIntro(): This method prints the game menu and prompts the user to select an option. 

 enterWords(): This method prompts the user to enter a list of words to use in the puzzle and 
adds them to the ArrayList. 

 calcSize(): This method calculates the size of the puzzle based on the length of the longest word 
and the number of words. 

 emptyGrid(): This method generates an empty puzzle grid of the specified size. 

 createPuzzle(): This method generates the puzzle by randomly placing the words horizontally, 
vertically, or diagonally in the puzzle. 

 jumblePuzzle(): This method creates a puzzle with randomized letters for the empty spaces in 
the puzzle. 

The second part starts by importing the necessary classes and interfaces from the java.util package. It 
then defines a main method in the class "PuzzleTestHarness" that creates an instance of the "Puzzle" 
class, sets the puzzle and solution arrays, and creates a "Scanner" object to read user input. 
The program then enters a "while" loop that runs as long as the "runGame" variable is true. Within this 
loop, the program prompts the user to enter a menu option (1-4) and uses a "switch-case" statement to 
execute the corresponding functionality.\
