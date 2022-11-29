import controller.GameController;
import model.GameModel;
import model.boosters.Cat;
import model.boosters.Dog;
import model.boosters.Magician;
import model.boosters.TheFlash;
import model.initialGame.Player;
import model.initialGame.Table;
import model.testing.Bank;
import model.initialGame.Dealer;
import view.GameView;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.lang.Thread;

public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        String source;
        GameView view = new GameView();
        source = view.tableMenu();

        while (true) {
            if (source.equals("0")) {
                break;
            } else if (source.equals("1")) {
                StartGame(view);
            }
            else if (source.equals("test")) {
//                test();
            }
        }
    }

    public static void StartGame(GameView view) throws InterruptedException {
        String name;
        List<String> hand;
        boolean play = true;

        //initialize table
        Table table = new Table();

        //initiate dealer
        Dealer dealer = new Dealer();

        //initialize bank
        Bank bank = new Bank();

        //initialize model.boosters.TheFlash
        TheFlash barry = new TheFlash();

        //initialize model.boosters.Dog
        Dog dog = new Dog();

        //initialize model.boosters.Cat
        Cat cat = new Cat();

        //initialize model.boosters.Magician
        Magician magician = new Magician();

        //initialize players
        Scanner choice = new Scanner(System.in);
        Scanner nameInput = new Scanner(System.in);
        int userChoice;
//        System.out.println("Number of players (max 1): ");
        userChoice = 1;
        Player player1 = new Player();

        GameController controller = new GameController(view, player1, dealer, table, cat, dog, magician, barry, bank);

        controller.setNoPlayers(userChoice);

        if (userChoice == 1) {
            controller.setPlayerBalance(1000);
            System.out.println("Player1 name: ");
            name = nameInput.nextLine();
            controller.setPlayerName(name);

        }

        //------------------------- some poo code here ---------------------------

        //play round
        int noRound = 1;
        while (play) {
            boolean credit = false;
            //create a new deck of cards
            controller.setShuffledDeck();

            hand = controller.firstRound();
            controller.setDealerHand(hand);

            if (userChoice == 1) {
                //initialize player hand
                hand = controller.firstRound();
                controller.setPlayerHand(hand);

                //take fees
                if (noRound % 3 == 0) {
                    controller.setPlayerBalance(controller.playerPayFee(5));
                    System.out.println("---The players paid the tax---");
                }

                //take bets
                controller.setPlayerBalance(controller.playerEntryGame());

                // play a round
                System.out.println("------Round " + noRound + "------");
                System.out.println(controller.getPlayerName() + "'s turn");
                System.out.println("");

                Random rando = new Random();
                int numRand = rando.nextInt(4);

                boolean cont = true;
                int count = 0;
                String menuChoice = "1";
                while ((menuChoice.equals("1") || menuChoice.equals("2") || menuChoice.equals("3") || menuChoice.equals("4") || menuChoice.equals("5")) && (cont == true )){
                    System.out.println(controller.getPlayerName() + "'s cards: " + controller.getPlayerHand());
                    System.out.println(controller.getPlayerName() + "'s balance: " + controller.getPlayerBalance());
                    System.out.println("");
                    if (controller.totalPointsPlayer(controller.getPlayerHand()) >= 19 && controller.totalPointsPlayer(controller.getPlayerHand()) <= 21) {break;}
                    System.out.println(controller.totalPointsPlayer(controller.getPlayerHand()));
                    menuChoice = playerMenu(controller.totalPointsPlayer(controller.getPlayerHand()), controller.getTableShuffledDeck());

                    if (menuChoice.equals("1")) {
                        String drawCard;

                        drawCard = controller.getLastCard();
                        controller.deleteLastCard();

                        controller.playerAddCard(drawCard);
                        System.out.println("The player has drawn a card");

                    } else if (menuChoice.equals("2")) {
                        //ask Barry Alen for help
                        controller.setBarryCard(controller.getLastCard());
                        System.out.println(controller.getBarryCard());

                        controller.setPlayerBalance(controller.getPlayerBalance() - 17);
                        controller.setBankMoney(bank.payService(17));
                        System.out.println("The player has asked Barry Alen to check the last card in the deck");
                    } else if (menuChoice.equals("3")) {
                        //help from dog
                        Random randomNumber = new Random();
                        int theRandomNumber;

                        theRandomNumber = randomNumber.nextInt(52);
                        String willedCard;
                        willedCard = controller.getTableDeck().get(theRandomNumber);

                        theRandomNumber = randomNumber.nextInt(2);

                        if (theRandomNumber == 0) {
                            controller.setDogCard(willedCard);
                            controller.playerAddCard(controller.getDogCard());
                        } else {
                            controller.playerAddCard("bark");
                        }

                        controller.setPlayerBalance(controller.getPlayerBalance() - 17);
                        controller.setBankMoney(bank.payService(17));
                        System.out.println("The player has asked the dog to bring " + willedCard + " .");
                    } else if (menuChoice.equals("4")) {
                        //help from cat
                        System.out.println("cat has run over the deck");

                        controller.setCatDeck(controller.getTableShuffledDeck());
                        controller.changeShuffledDeck(controller.getCatDeck());

                        controller.setPlayerBalance(controller.getPlayerBalance() - 10);
                        controller.setBankMoney(bank.payService(10));
                        System.out.println("The player has chosen the help from the cat.");
                    } else if (menuChoice.equals("5") && (controller.getPlayerHand().size() != 0)) {
                        //help from magician
                        System.out.println("Focus Pocus...");

                        controller.setMagicianDeck(controller.getPlayerHand());
                        controller.setPlayerHand(controller.magicianDeleteCard());

                        controller.setPlayerBalance(controller.getPlayerBalance() - 30);
                        controller.setBankMoney(bank.payService(30));
                        System.out.println("The player has chosen the help from the magician.");
                    }

                }

                //compute points
                controller.playerSetPoints(controller.totalPointsPlayer(controller.getPlayerHand()));
                int playerOnePoints = controller.playerGetPoints();

                //dealer's move
                controller.dealerSetPoints(controller.dealerTotalPoints(controller.getDealerHand()));
                int dealerPoints = controller.dealerGetPoints();
                String dealerCard;

                while (dealerPoints < 17) {
                    dealerCard = controller.getLastCard();
                    controller.deleteLastCard();

                    controller.dealerAddCard(dealerCard);
                    controller.dealerSetPoints(controller.dealerTotalPoints(controller.getDealerHand()));

                    dealerPoints = dealer.getPoints();
                }

                System.out.println("dealer's cards: " + controller.getDealerHand());

                if (playerOnePoints == dealerPoints && dealerPoints <= 21) {
                    controller.setPlayerBalance(controller.drawRound());
                    System.out.println("The game is even.");
                } else if (playerOnePoints > dealerPoints && playerOnePoints <= 21) {
                    controller.setPlayerBalance(controller.winRound());
                    System.out.println(controller.getPlayerName() + " won !!!");
                } else if (playerOnePoints < dealerPoints && dealerPoints > 21) {
                    controller.setPlayerBalance(controller.winRound());
                    System.out.println(controller.getPlayerName() + " won !!!");
                } else {
                    System.out.println(controller.getPlayerName() + " lost!");
                }

                noRound++;
                Thread.sleep(2000);

                if (controller.getPlayerBalance() <= 100){
                    int randomNum = rando.nextInt(2);

                    if (randomNum == 0 && !credit){
                        System.out.println(controller.getPlayerName() + "has decided to borrow 500$ from the bank");
                        controller.setBankMoney(bank.getMoney()-500);
                        controller.setPlayerBalance(controller.getPlayerBalance()+500);
                        credit = true;
                    }
                    else {
                        System.out.println(controller.getPlayerName() + " has decided to stop here.");
                        play = false;
                    }
                }

                if (noRound % 500 == 0){
                    System.out.println("The president has decided to rise the taxes by 5$");
                }

                if (controller.getPlayerBalance() >= 800 && credit){
                    System.out.println(controller.getPlayerName() + " The player has paid 600$ back to the bank");
                    controller.setPlayerBalance(controller.getPlayerBalance() - 600);
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
