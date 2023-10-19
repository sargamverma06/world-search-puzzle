//To import all the classes and interfaces within java
import java.util.*;

class Puzzle{

    //Declaring instance variables

    Scanner input;
    ArrayList<String> words;
    int puzzSize;
    char[][] puzzle;
    char[][] solution;
    String word;
    String direction;
    int rowNum;
    int colNum;


    //Constructor that takes no arguments
    Puzzle(){

    }

    //Constructor that takes nine arguments
    public Puzzle(Scanner input, ArrayList<String> words, int puzzSize, char[][] puzzle, char[][] solution,
            String word, String direction, int rowNum, int colNum) {
        this.input = input;
        this.words = words;
        this.puzzSize = puzzSize;
        this.puzzle = puzzle;
        this.solution = solution;
        this.word = word;
        this.direction = direction;
        this.rowNum = rowNum;
        this.colNum = colNum;
    }

    //Setters and getter methods
    
    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public int getPuzzSize() {
        return puzzSize;
    }

    public void setPuzzSize(int puzzSize) {
        this.puzzSize = puzzSize;
    }

    public char[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(char[][] puzzle) {
        this.puzzle = puzzle;
    }

    public char[][] getSolution() {
        return solution;
    }

    public void setSolution(char[][] solution) {
        this.solution = solution;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }
    
    public void printIntro() {
        System.out.println("Select an option: \n \n" +
        "1 - Enter a new word to in order to start the puzzle  \n" +
        "2 - Display the puzzle \n" +
        "3 - Print the solution to your word search \n" +
        "4 - Exit the program \n");   
        System.out.println("Your option: ");              
    }

    

    // method to get words from the user and add to arrayList
    public ArrayList<String> enterWords(Scanner input, ArrayList<String> words) {
        String nextWord;
        do {
            System.out.print("Enter word: ");
            nextWord = input.nextLine().toLowerCase();
            if (!nextWord.equals("done")) {
                words.add(nextWord);
            }
        } while (!nextWord.equals("done"));
        return words;
    }

    // calculates the puzzle size based on longest word and qty of words
    public int calcSize(ArrayList<String> words) {
        int maxLength = 0;
        for (String word : words) {
            if (word.length() > maxLength) {
                maxLength = word.length();
            }
        }
        return maxLength + 1 + words.size() / 2;
    }

    // generates a skeloton puzzle based on the given size
    
    public char[][] emptyGrid(int puzzSize) {
        char[][] puzzle = new char[puzzSize][puzzSize];
        for (int m = 0; m < puzzSize; m++) {
            for (int n = 0; n < puzzSize ; n++) {
                puzzle[m][n] = '-';
                }
            }
            return puzzle;
        }




    // generates puzzle solution. words are sent as parameter -- > see get words method
    public char[][] createPuzzle(char[][] puzzle, ArrayList<String> words, Random rand) {
        int diagonals = 0;
            for(int i = 0; i < words.size(); i++) {
                int num = rand.nextInt(3);
                if (num == 0 && diagonals < 2) {
                   // randomized word placement is based on the direction parameter String
                   randomWordPosition(puzzle, words.get(i), "Diagonal", rand);
                   diagonals++;
                } else if (num == 2) {
                   randomWordPosition(puzzle, words.get(i), "Horizontal", rand);
                } else {
                   randomWordPosition(puzzle, words.get(i), "Vertical", rand);
                }    
            }  
        return puzzle;
    }
    // randomly place words in the puzzle grid
    public void randomWordPosition(char[][] puzzle, String word, String direction, Random rand) {
        int rowNum = rowIndex(word, direction, rand, puzzle.length);
        int colNum = colIndex(word, direction, rand, puzzle.length);
         if (verifyWordPlacement(puzzle, word, direction, rowNum, colNum)) {
             fillWord(puzzle, word, direction, rowNum, colNum);
         } else {
             randomWordPosition(puzzle, word, direction, rand);
         }
     } 
 
     // randomized column selction.
     public int colIndex(String word, String direction, Random rand, int pSize) {
         int colNum = rand.nextInt(pSize);
         if (direction.equals("Horizontal") || direction.equals("Diagonal")) {
             colNum = rand.nextInt(pSize - word.length());
         }
         return colNum;
     }
 
     // randomized row selction
     public int rowIndex(String word, String direction, Random rand, int pSize) {
         int rowNum = rand.nextInt(pSize);
         if (direction.equals("Vertical") || direction.equals("Diagonal")) {
             rowNum = rand.nextInt(pSize - word.length());
         }
         return rowNum;
     }
 


     
    // generates puzzle with randomized letters
    public char[][] jumblePuzzle(char[][] solution, Random rand) {
        char[][] puzzle = new char[solution.length][solution.length];
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                if (solution[i][j] != '-') {
                    puzzle[i][j] = solution[i][j];
                } else {
                    puzzle[i][j] = (char)(rand.nextInt(26) + 'a');
                }
            }
        }
        return puzzle;
    }


    // puzzle is passed as paramter to print out charcters
    public void displayPuzzle(char[][] puzzle) {
        System.out.println(" ");
        for (int m = 0; m < puzzle.length; m++) {
            for (int n = 0; n < puzzle.length; n++ ) {
            System.out.print(puzzle[m][n] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // method to check if the word can in these cells
    public boolean verifyWordPlacement(char[][] puzzle, String word, String direction, int rowNum, int colNum) {
        boolean validPlace = true;
        for (int i = 0; i < word.length(); i++) {
            char cell = puzzle[rowNum][colNum];
                // if statement allows words to intersect 
                // if the next charcter is the same as the 
                // the character to be placed at location
                if(cell != word.charAt(i) && cell != '-') {
                    validPlace = false;
                }
                colNum = colStep(colNum, direction);
                rowNum = rowStep(rowNum, direction);
            }
            return validPlace;
    }

    // places a word at location. collumn and row is incremented based on direction 
    public void fillWord(char[][] puzzle, String word, String direction, int rowNum, int colNum) {
        for (int i = 0; i < word.length(); i++) {
            puzzle[rowNum][colNum] = word.charAt(i);
            colNum = colStep(colNum, direction);
            rowNum = rowStep(rowNum, direction);
        }
    }

    // increments the collumn to place the next character
    public int colStep(int colNum, String direction) {
        if (direction.equals("Horizontal") || direction.equals("Diagonal")) {
            colNum++;
        }
        return colNum;  
    }

    // increments the row to place the next character
    public int rowStep(int rowNum, String direction) {
        if (direction.equals("Vertical") || direction.equals("Diagonal")) {
            rowNum++;
        }
        return rowNum;
    }

    
   
  
}