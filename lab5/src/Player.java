import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player implements GameActors{
    public String name;
    public int balance;
    public List<String> hand = new ArrayList<String>();
    public int points;

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public List<String> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setHand(List<String> hand) {
        this.hand = hand;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int checkBalance(){
        return balance;
    }

    public void addCard(String card){
        hand.add(card);
    }
    public int entryGame(){
        balance = balance - 100;
        return balance;
    }

    public int winRound(){
        balance = balance + 200;
        return balance;
    }

    public int drawRound(){
        balance = balance + 100;
        return balance;
    }

    public int payFee(){
        balance = balance - 50;
        return balance;
    }

    public int cardPoints(String card){
        char value = card.charAt(0);

        if (value == '2'){
            return 2;
        }
        else if (value == '3'){
            return 3;
        }
        else if (value == '4'){
            return 4;
        }
        else if (value == '5'){
            return 5;
        }
        else if (value == '6'){
            return 6;
        }
        else if (value == '7'){
            return 7;
        }
        else if (value == '8'){
            return 8;
        }
        else if (value == '9'){
            return 9;
        }
        else if (value == 'Q' || value == '1' || value == 'K' || value == 'J'){
            return 10;
        }
        else if (value == 'A'){
            Scanner choice = new Scanner(System.in);
            int ace;
            ace = 0;
            while (ace != 1 || ace != 11) {
                System.out.println("Choose the points for Ace [1 or 11]: ");
                ace = choice.nextInt();

                if (ace == 1) {
                    return ace;
                } else if (ace == 11) {
                    return ace;
                } else {
                    System.out.println("Not a valid value");
                }
            }
            return ace;
        }
        else {return 0;}
    }

    public int totalPoints(List<String> playerHand){
        int playerPoints = 0;
        for (int i = 0; i < playerHand.size(); i++){
            playerPoints = playerPoints + cardPoints(playerHand.get(i));
        }
        return playerPoints;
    }
}
