import java.util.Scanner;
public class NumberGuessGame {
    static Scanner scan = new Scanner(System.in);

    //global variables
    static final int GUESS_LIMIT = 3;
    static int roundWon = 0;
    static boolean gameOver = false;
    public static void main(String[] args) {
        System.out.println("\n************    Welcome to the number guess game!   **************\n");
        System.out.println("\tNote, in this game, your guess should be from 1 to 100, inclusively.\n");
        System.out.print("Enter the name: ");
        String name = scan.nextLine();
        while(!gameOver) {
            //main option menu
            System.out.printf("\nHello %s, please select your choice from the following options:\n\n", name);
            System.out.println("Enter < A > for single round game ");
            System.out.println("Enter < B > for multiple round game ");
            System.out.println("Enter < C > to exit ");
            System.out.print("Select: ");
            char mainChoice = scan.next().trim().toUpperCase().charAt(0);
            scan.nextLine();
            if (mainChoice == 'A') {
                game();//invoke game method
            } else if (mainChoice == 'B') {
                int roundCounter = 0;
                boolean continuity;
                while (true) {
                    roundCounter++;//count rounds
                    System.out.print("_____________________  ");
                    System.out.print("Guess round #" + roundCounter);
                    System.out.print("  _______________________\n");
                    game();//invoke game method
                    System.out.println("\nDo you want to play the next round? (Y/N)");
                    char choice = scan.next().toUpperCase().charAt(0);
                    scan.nextLine();
                    if (choice == 'N') {
                        continuity = true;
                        break;
                    } else if (choice != 'Y') {
                        System.out.println(name + ", your input is invalid!");
                    }
                }
                if(continuity) {
                    System.out.println("____________________________________________________");
                    System.out.println(name + ", your guess was: " + (roundWon * 100.0 / roundCounter) + " % Successful!");
                    System.out.println("____________________________________________________");
                    System.out.println("____________________________________________________");
                }
            } else if (mainChoice == 'C') {
                System.out.println("Goodbye " + name + "!");
                System.exit(0);
            } else {
                System.out.println("Invalid choice");
            }

        }
    }

    //game method
    public static void game(){
        int winningNUmber = 1+(int)(Math.random()*100);//generate random numbers from 1 to 100, inclusively.
        int numberOfGuesses = 0;
        while(numberOfGuesses < GUESS_LIMIT) {
            numberOfGuesses++;
            int luckyNumber = 0;
            boolean digitValid = false;
            System.out.print("Enter your guess: ");
            String luckyNumbers = scan.nextLine();
            for(int i = 0 ; i< luckyNumbers.length(); i++) {
                if(Character.isDigit(luckyNumbers.charAt(i))) {
                    digitValid = true;
                }
                else {
                    digitValid = false;
                }
            }
            if(digitValid) {
                boolean won = false;
                luckyNumber = Integer.parseInt(luckyNumbers);
                if(luckyNumber == winningNUmber) {
                    won = true;
                    numberOfGameWon(won);//invoke a function to count the total game won for multiple round game option
                    String winningMessage = "You win!";
                    if(numberOfGuesses == 1) {
                        System.out.println(winningMessage + " in your first trial");
                    }
                    else if(numberOfGuesses == 2) {
                        System.out.println(winningMessage + " in your second trial");
                    }
                    else{
                        System.out.println(winningMessage + " in last trial");
                    }
                    break;
                }
                else if(luckyNumber < winningNUmber && numberOfGuesses < GUESS_LIMIT) {
                    System.out.println("Try again! the guess should be more than "+luckyNumber);
                }
                else if( luckyNumber > winningNUmber && numberOfGuesses < GUESS_LIMIT) {
                    System.out.println("Try again! the guess should be less than "+luckyNumber);
                }
                if(numberOfGuesses == GUESS_LIMIT && !won) {
                    System.out.println("The winning number was " + winningNUmber);
                }
            }
            else if(!digitValid) {
                System.out.println("Try again! the guess should be digit");
                System.out.println("____________________________________________________");
            }

        }
    }

    //a method that count the total round won.
    public static void numberOfGameWon(boolean won){
        if(won) {
            roundWon++;
        }
    }
}
