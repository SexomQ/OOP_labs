import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.*;
import java.lang.Thread;
import java.io.*;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
                tableMenu();
    }

    public static void tableMenu() throws InterruptedException {
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
                StartGame();
            }
            else if (userChoice.equals("test")){
//                test();
            }
            else {
                System.out.println("No such choice, try smth else.");
            }
        }
    }

    public static void StartGame() throws InterruptedException {
        String name;
        List<String> hand;
        boolean play = true;

        //initialize the president
        President president = new President();
        president.setCountry("Moldova");
        president.setName("Maia Sandu");
        president.setFee(5);

        //initialize table
        Table table = new Table();

        //initialize security
        Security nenea = new Security();
        nenea.setName("Nastea");
        nenea.setRank("Boss");
        nenea.setTable(1);

        //initialize bank
        Bank bank = new Bank();
        bank.setMoney(50);

        //initialize TheFlash
        TheFlash barry = new TheFlash();

        //initialize Dog
        Dog dog = new Dog();

        //initialize Cat
        Cat cat = new Cat();

        //initialize Magician
        Magician magician = new Magician();

        //initialize players
        Scanner choice = new Scanner(System.in);
        Scanner nameInput = new Scanner(System.in);
        int userChoice;
        System.out.println("Number of players (MAX 3): ");
        userChoice = choice.nextInt();
        table.setNoPlayers(userChoice);

        Player player1 = new Player();

        if (userChoice == 1) {
            player1.setBalance(1000);
            System.out.println("Player1 name: ");
            name = nameInput.nextLine();
            player1.setName(name);

        }

        //------------------------- some poo code here ---------------------------

        //initiate dealer
        Dealer dealer = new Dealer();

        //play round
        int noRound = 1;
        while (play) {
            boolean credit = false;
            //create a new deck of cards
            table.setShuffledDeck();

            hand = table.firstRound();
            dealer.setHand(hand);

            if (userChoice == 1) {
                //initialize player hand
                hand = table.firstRound();
                player1.setHand(hand);

                //take fees
                if (noRound % 3 == 0) {
                    player1.setBalance(player1.payFee(president.getFee()));
                    System.out.println("---The players paid the tax---");
                }

                //take bets
                player1.setBalance(player1.entryGame());

//            play a round
                System.out.println("------Round " + noRound + "------");
                System.out.println(player1.getName() + "'s turn");
                System.out.println("");

                Random rando = new Random();
                int numRand = rando.nextInt(4);

                boolean cont = true;
                int count = 0;
                String menuChoice = "1";
                while ((menuChoice.equals("1") || menuChoice.equals("2") || menuChoice.equals("3") || menuChoice.equals("4") || menuChoice.equals("5")) && (cont == true )){
                    System.out.println(player1.getName() + "'s cards: " + player1.getHand());
                    System.out.println(player1.getName() + "'s balance: " + player1.getBalance());
                    System.out.println("");
                    if (player1.totalPoints(player1.getHand()) >= 19 && player1.totalPoints(player1.getHand()) <= 21) {break;}
                    System.out.println(player1.totalPoints(player1.getHand()));
                    menuChoice = playerMenu(player1.totalPoints(player1.getHand()), table.getShuffledDeck());

                    if (menuChoice.equals("1")) {
                        String drawCard;

                        drawCard = table.lastCard();
                        table.deleteCard();

                        player1.addCard(drawCard);
                        System.out.println("The player has drawn a card");

                    } else if (menuChoice.equals("2")) {
                        //ask Barry Alen for help
                        barry.setCard(table.lastCard());
                        System.out.println(barry.getCard());

                        player1.setBalance(player1.getBalance() - 17);
                        bank.setMoney(bank.payService(17));
                        System.out.println("The player has asked Barry Alen to check the last card in the deck");
                    } else if (menuChoice.equals("3")) {
                        //help from dog
                        Random randomNumber = new Random();
                        int theRandomNumber;

                        theRandomNumber = randomNumber.nextInt(52);
                        String willedCard;
                        willedCard = table.getDeck().get(theRandomNumber);

                        theRandomNumber = randomNumber.nextInt(2);

                        if (theRandomNumber == 0) {
                            dog.setCard(willedCard);
                            player1.addCard(dog.getCard());
                        } else {
                            player1.addCard("bark");
                        }

                        player1.setBalance(player1.getBalance() - 17);
                        bank.setMoney(bank.payService(17));
                        System.out.println("The player has asked the dog to bring " + willedCard + " .");
                    } else if (menuChoice.equals("4")) {
                        //help from cat
                        System.out.println("Cat has run over the deck");

                        cat.setDeck(table.getShuffledDeck());
                        table.changeShuffledDeck(cat.shuffleCatDeck());

                        player1.setBalance(player1.getBalance() - 10);
                        bank.setMoney(bank.payService(10));
                        System.out.println("The player has chosen the help from the cat.");
                    } else if (menuChoice.equals("5") && (player1.getHand().size() != 0)) {
                        //help from magician
                        System.out.println("Focus Pocus...");

                        magician.setDeck(player1.getHand());
                        player1.setHand(magician.deleteCard());

                        player1.setBalance(player1.getBalance() - 30);
                        bank.setMoney(bank.payService(30));
                        System.out.println("The player has chosen the help from the magician.");
                    }

                }

                //compute points
                player1.setPoints(player1.totalPoints(player1.getHand()));
                int playerOnePoints = player1.getPoints();

                //dealer's move
                dealer.setPoints(dealer.totalPoints(dealer.getHand()));
                int dealerPoints = dealer.getPoints();
                String dealerCard;

                while (dealerPoints < 17) {
                    dealerCard = table.lastCard();
                    table.deleteCard();

                    dealer.addCard(dealerCard);
                    dealer.setPoints(dealer.totalPoints(dealer.getHand()));

                    dealerPoints = dealer.getPoints();
                }

                System.out.println("dealer's cards: " + dealer.getHand());

                if (playerOnePoints == dealerPoints && dealerPoints <= 21) {
                    player1.setBalance(player1.drawRound());
                    System.out.println("The game is even.");
                } else if (playerOnePoints > dealerPoints && playerOnePoints <= 21) {
                    player1.setBalance(player1.winRound());
                    System.out.println(player1.getName() + " won !!!");
                } else if (playerOnePoints < dealerPoints && dealerPoints > 21) {
                    player1.setBalance(player1.winRound());
                    System.out.println(player1.getName() + " won !!!");
                } else {
                    System.out.println(player1.getName() + " lost!");
                }

                noRound++;
                Thread.sleep(2000);

                if (player1.getBalance() <= 100){
                    int randomNum = rando.nextInt(2);

                    if (randomNum == 0 && !credit){
                        System.out.println(player1.getName() + "has decided to borrow 500$ from the bank");
                        bank.setMoney(bank.getMoney()-500);
                        player1.setBalance(player1.getBalance()+500);
                        credit = true;
                    }
                    else {
                        System.out.println(player1.getName() + " has decided to stop here.");
                        play = false;
                    }
                }

                if (noRound % 500 == 0){
                    president.setFee(president.getFee()+5);
                    System.out.println("The president has decided to rise the taxes by 5$");
                }

                if (player1.getBalance() >= 800 && credit){
                    System.out.println(player1.getName() + " The player has paid 600$ back to the bank");
                    player1.setBalance(player1.getBalance() - 600);
                    credit = false;
                }

            }
        }
    }
    public static String playerMenu (int points, List<String> deck) {
        String menuChoice;
        Random random = new Random();
        int bonus = random.nextInt(2);

        if (points < 17){
            menuChoice = "1";
        } else if (points > 21) {
            menuChoice = "5";
        } else if (points >= 17 && points <= 21 && bonus == 0) {
            int randNum = random.nextInt(3);
            if (randNum == 0) {
                menuChoice = "2";
            } else if (randNum == 1) {
                menuChoice = "3";
            } else {
                menuChoice = "4";
            }
        } else {
            menuChoice = "0";
        }
        return menuChoice;
    }
}
