import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        President president = new President();
        president.setCountry("Moldova");
        president.setName("Maia Sandu");

                tableMenu();
    }

    public static void tableMenu(){
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

    public static void StartGame() {
        String name;
        List<String> hand;
        boolean play = true;

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
        Player player2 = new Player();
        Player player3 = new Player();

        if (userChoice == 1) {
            player1.setBalance(500);
            System.out.println("Player1 name: ");
            name = nameInput.nextLine();
            player1.setName(name);

        } else if (userChoice == 2) {
            player1.setBalance(500);
            System.out.println("Player1 name: ");
            name = nameInput.nextLine();
            player1.setName(name);

            player2.setBalance(500);
            System.out.println("Player2 name: ");
            name = nameInput.nextLine();
            player2.setName(name);

        } else if (userChoice == 3) {
            player1.setBalance(500);
            System.out.println("Player1 name: ");
            name = nameInput.nextLine();
            player1.setName(name);

            player2.setBalance(500);
            System.out.println("Player2 name: ");
            name = nameInput.nextLine();
            player2.setName(name);

            player3.setBalance(500);
            System.out.println("Player3 name: ");
            name = nameInput.nextLine();
            player3.setName(name);
        } else {
            System.out.println("Not possible at the moment.");
            System.out.println("The max number of players will be extended in the next update");
        }

        //initiate dealer
        Dealer dealer = new Dealer();

        //play round
        int noRound = 1;
        while (play) {
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
                    player1.setBalance(player1.payFee());
                    System.out.println("---The players paid the tax---");
                }

                //take bets
                player1.setBalance(player1.entryGame());

//            play a round
                System.out.println("------Round " + noRound + "------");
                System.out.println(player1.getName() + "'s turn");
                System.out.println("");

                String menuChoice = "1";
                while (menuChoice.equals("1") || menuChoice.equals("2") || menuChoice.equals("3") || menuChoice.equals("4") || menuChoice.equals("5")) {
                    System.out.println(player1.getName() + "'s cards: " + player1.getHand());
                    System.out.println(player1.getName() + "'s balance: " + player1.getBalance());
                    System.out.println("");

                    menuChoice = playerMenu();

                    if (menuChoice.equals("1")) {
                        String drawCard;

                        drawCard = table.lastCard();
                        table.deleteCard();

                        player1.addCard(drawCard);

                    } else if (menuChoice.equals("2")) {
                        //ask Barry Alen for help
                        barry.setCard(table.lastCard());
                        System.out.println(barry.getCard());

                        player1.setBalance(player1.getBalance() - 100);
                        bank.setMoney(bank.payService(100));
                    } else if (menuChoice.equals("3")) {
                        //help from dog
                        Scanner card = new Scanner(System.in);
                        String willedCard;
                        willedCard = card.nextLine();

                        Random randomNumber = new Random();
                        int theRandomNumber;

                        theRandomNumber = randomNumber.nextInt(2);

                        if (theRandomNumber == 0) {
                            dog.setCard(willedCard);
                            player1.addCard(dog.getCard());
                        } else {
                            player1.addCard("bark");
                        }

                        player1.setBalance(player1.getBalance() - 100);
                        bank.setMoney(bank.payService(100));
                    } else if (menuChoice.equals("4")) {
                        //help from cat
                        System.out.println("Cat has run over the deck");

                        cat.setDeck(table.getShuffledDeck());
                        table.changeShuffledDeck(cat.shuffleCatDeck());

                        player1.setBalance(player1.getBalance() - 25);
                        bank.setMoney(bank.payService(25));
                    } else if (menuChoice.equals("5")) {
                        //help from magician
                        System.out.println("Focus Pocus...");

                        magician.setDeck(player1.getHand());
                        player1.setHand(magician.deleteCard());

                        player1.setBalance(player1.getBalance() - 50);
                        bank.setMoney(bank.payService(50));
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
                } else if (playerOnePoints > dealerPoints && playerOnePoints <= 21) {
                    player1.setBalance(player1.winRound());
                    System.out.println(player1.getName() + " won !!!");
                } else if (playerOnePoints < dealerPoints && dealerPoints > 21) {
                    player1.setBalance(player1.winRound());
                    System.out.println(player1.getName() + " won !!!");
                }


            }
        }
    }
    public static String playerMenu () {
        Scanner choice = new Scanner(System.in);
        String menuChoice;

        while (true) {
            System.out.println("________________Player Menu_____________");
            System.out.println("1. Add one card");
            System.out.println("2. Ask Barry Alen to check the next card in deck for you [100$]");
            System.out.println("3. Help from a dog (brings you the card you need with probability 50%) [100$]");
            System.out.println("4. Help from a cat (shuffles the deck for you) [25$]");
            System.out.println("5. Help from magician (make last card in hand disapear) [50$]");
            System.out.println("0. Pass");

        }
    }

}
