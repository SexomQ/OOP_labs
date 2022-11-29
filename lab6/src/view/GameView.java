package view;

import java.util.Scanner;

public class GameView {
    public String tableMenu() {
        String userChoice;
        Scanner choice = new Scanner(System.in);
        while (true) {
            System.out.println("__________This is the Game 21__________");
            System.out.println("");
            System.out.println("________________Table Menu_____________");
            System.out.println("1. Start the game");
            System.out.println("0. Exit the game");

            System.out.println("");
            System.out.println("Your choice: ");
            userChoice = choice.nextLine();

            if (userChoice.equals("0")) {
                break;
            } else if (userChoice.equals("1")) {
                break;
            }
            else if (userChoice.equals("test")){
                break;
            }
            else {
                System.out.println("No such choice, try smth else.");
            }
        }
        return userChoice;
    }


}
