import java.util.*;

public class Main {
    public static void main(String[] args) {
        President president = new President();
        president.setCountry("Moldova");
        president.setName("Maia Sandu");

        Scanner choice = new Scanner(System.in);
        String userChoice;

        while (true){
            System.out.println("_________This is the Game 21__________");
            System.out.println("");
            System.out.println("________________Main Menu_____________");
            System.out.println("1. Start new game");
            System.out.println("0. Quit");

            System.out.println("");
            System.out.println("Your choice: ");
            userChoice = choice.nextLine();

            if (userChoice.equals("0")){
                break;
            }
            else if (userChoice.equals("1")){
                tableMenu();
            }
            else if (userChoice.equals("test")){
                test();
            }
            else {
                System.out.println("No such choice, try smth else.");
            }

        }
    }

    public static void tableMenu(){
        String userChoice;
        Scanner choice = new Scanner(System.in);
        while (true) {
            System.out.println("________________Table Menu_____________");
            System.out.println("1. Start the game");
            System.out.println("0. Go to Main Menu");

            System.out.println("");
            System.out.println("Your choice: ");
            userChoice = choice.nextLine();

            if (userChoice.equals("0")) {
                break;
            } else if (userChoice.equals("1")) {
                StartGame();
            }
        }
    }
    public static void StartGame(){
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
            player1.setBalance(5000);
            System.out.println("Player1 name: ");
            name = nameInput.nextLine();
            player1.setName(name);

        } else if (userChoice == 2) {
            player1.setBalance(5000);
            System.out.println("Player1 name: ");
            name = nameInput.nextLine();
            player1.setName(name);

            player2.setBalance(5000);
            System.out.println("Player2 name: ");
            name = nameInput.nextLine();
            player2.setName(name);

        } else if (userChoice == 3) {
            player1.setBalance(5000);
            System.out.println("Player1 name: ");
            name = nameInput.nextLine();
            player1.setName(name);

            player2.setBalance(5000);
            System.out.println("Player2 name: ");
            name = nameInput.nextLine();
            player2.setName(name);

            player3.setBalance(5000);
            System.out.println("Player3 name: ");
            name = nameInput.nextLine();
            player3.setName(name);
        }
        else {
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
                if (noRound % 3 == 0){
                    player1.setBalance(player1.payFee());
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

                    }
                    else if (menuChoice.equals("2")){
                        //ask Barry Alen for help
                        barry.setCard(table.lastCard());
                        System.out.println(barry.getCard());

                        player1.setBalance(player1.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("3")){
                        //help from dog
                        Scanner card = new Scanner(System.in);
                        String willedCard;
                        System.out.println("Bark baaark barrrk [write like: '10D' or 'J H']: ");
                        willedCard = card.nextLine();

                        Random randomNumber = new Random();
                        int theRandomNumber;

                        theRandomNumber = randomNumber.nextInt(2);

                        if (theRandomNumber == 0) {
                            dog.setCard(willedCard);
                            player1.addCard(dog.getCard());
                        }
                        else {
                            player1.addCard("bark");
                        }

                        player1.setBalance(player1.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("4")){
                        //help from cat
                        System.out.println("Cat has run over the deck");

                        cat.setDeck(table.getShuffledDeck());
                        table.changeShuffledDeck(cat.shuffleCatDeck());

                        player1.setBalance(player1.getBalance()-25);
                        bank.setMoney(bank.payService(25));
                    }
                    else if (menuChoice.equals("5")){
                        //help from magician
                        System.out.println("Focus Pocus...");

                        magician.setDeck(player1.getHand());
                        player1.setHand(magician.deleteCard());

                        player1.setBalance(player1.getBalance()-50);
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
                } else if (playerOnePoints < dealerPoints && dealerPoints > 21){
                    player1.setBalance(player1.winRound());
                    System.out.println(player1.getName() + " won !!!");
                }

            } else if (userChoice == 2) {
                hand = table.firstRound();
                player1.setHand(hand);

                //pay fees
                if (noRound % 3 == 0){
                    player1.setBalance(player1.payFee());
                    player2.setBalance(player2.payFee());
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

                    }
                    else if (menuChoice.equals("2")){
                        //ask Barry Alen for help
                        barry.setCard(table.lastCard());
                        System.out.println(barry.getCard());

                        player1.setBalance(player1.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("3")){
                        //help from dog
                        Scanner card = new Scanner(System.in);
                        String willedCard;
                        System.out.println("Bark baaark barrrk [write like: '10D' or 'J H']: ");
                        willedCard = card.nextLine();

                        Random randomNumber = new Random();
                        int theRandomNumber;

                        theRandomNumber = randomNumber.nextInt(2);

                        if (theRandomNumber == 0) {
                            dog.setCard(willedCard);
                            player1.addCard(dog.getCard());
                        }
                        else {
                            player1.addCard("bark");
                        }

                        player1.setBalance(player1.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("4")){
                        //help from cat
                        System.out.println("Cat has run over the deck");

                        cat.setDeck(table.getShuffledDeck());
                        table.changeShuffledDeck(cat.shuffleCatDeck());

                        player1.setBalance(player1.getBalance()-25);
                        bank.setMoney(bank.payService(25));
                    }
                    else if (menuChoice.equals("5")){
                        //help from magician
                        System.out.println("Focus Pocus...");

                        magician.setDeck(player1.getHand());
                        player1.setHand(magician.deleteCard());

                        player1.setBalance(player1.getBalance()-50);
                        bank.setMoney(bank.payService(50));
                    }
                }

                //compute points
                player1.setPoints(player1.totalPoints(player1.getHand()));
                int playerOnePoints = player1.getPoints();

                hand = table.firstRound();
                player2.setHand(hand);

                //take bets
                player2.setBalance(player2.entryGame());

                //play a round
                System.out.println("------Round " + noRound + "------");
                System.out.println(player2.getName() + "'s turn");
                System.out.println("");

                menuChoice = "1";
                while (menuChoice.equals("1") || menuChoice.equals("2") || menuChoice.equals("3") || menuChoice.equals("4") || menuChoice.equals("5")) {
                    System.out.println(player2.getName() + "'s cards: " + player2.getHand());
                    System.out.println(player2.getName() + "'s balance: " + player2.getBalance());
                    System.out.println("");

                    menuChoice = playerMenu();

                    if (menuChoice.equals("1")) {
                        String drawCard;

                        drawCard = table.lastCard();
                        table.deleteCard();

                        player2.addCard(drawCard);

                    }
                    else if (menuChoice.equals("2")){
                        //ask Barry Alen for help
                        barry.setCard(table.lastCard());
                        System.out.println(barry.getCard());

                        player2.setBalance(player2.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("3")){
                        //help from dog
                        Scanner card = new Scanner(System.in);
                        String willedCard;
                        System.out.println("Bark baaark barrrk [write like: '10D' or 'J H']: ");
                        willedCard = card.nextLine();

                        Random randomNumber = new Random();
                        int theRandomNumber;

                        theRandomNumber = randomNumber.nextInt(2);

                        if (theRandomNumber == 0) {
                            dog.setCard(willedCard);
                            player2.addCard(dog.getCard());
                        }
                        else {
                            player2.addCard("bark");
                        }

                        player2.setBalance(player2.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("4")){
                        //help from cat
                        System.out.println("Cat has run over the deck");

                        cat.setDeck(table.getShuffledDeck());
                        table.changeShuffledDeck(cat.shuffleCatDeck());

                        player2.setBalance(player2.getBalance()-25);
                        bank.setMoney(bank.payService(25));
                    }
                    else if (menuChoice.equals("5")){
                        //help from magician
                        System.out.println("Focus Pocus...");

                        magician.setDeck(player2.getHand());
                        player2.setHand(magician.deleteCard());

                        player2.setBalance(player2.getBalance()-50);
                        bank.setMoney(bank.payService(50));
                    }
                }

                //compute points
                player2.setPoints(player2.totalPoints(player2.getHand()));
                int playerTwoPoints = player2.getPoints();

                //dealer's move
                dealer.setPoints(dealer.totalPoints(dealer.getHand()));
                int dealerPoints = dealer.getPoints();
                String dealerCard;

                while (dealerPoints < 17){
                    dealerCard = table.lastCard();
                    table.deleteCard();

                    dealer.addCard(dealerCard);
                    dealer.setPoints(dealer.totalPoints(dealer.getHand()));

                    dealerPoints = dealer.getPoints();
                }

                System.out.println("dealer's cards: " + dealer.getHand());

                if (playerOnePoints == dealerPoints && dealerPoints <= 21){
                    player1.setBalance(player1.drawRound());
                } else if (playerOnePoints > dealerPoints && playerOnePoints <= 21){
                    player1.setBalance(player1.winRound());
                    System.out.println(player1.getName() + " won !!!");
                } else if (playerOnePoints < dealerPoints && dealerPoints > 21){
                    player1.setBalance(player1.winRound());
                    System.out.println(player1.getName() + " won !!!");
                }

                if (playerTwoPoints == dealerPoints && dealerPoints <= 21){
                    player2.setBalance(player2.drawRound());
                } else if (playerTwoPoints > dealerPoints && playerTwoPoints <= 21){
                    player2.setBalance(player2.winRound());
                    System.out.println(player2.getName() + " won !!!");
                } else if (playerTwoPoints < dealerPoints && dealerPoints > 21){
                    player2.setBalance(player2.winRound());
                    System.out.println(player2.getName() + " won !!!");
                }

            } else if (userChoice == 3) {
                hand = table.firstRound();
                player1.setHand(hand);

                //pay fees
                if (noRound % 3 == 0){
                    player1.setBalance(player1.payFee());
                    player2.setBalance(player2.payFee());
                    player3.setBalance(player3.payFee());
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

                    }
                    else if (menuChoice.equals("2")){
                        //ask Barry Alen for help
                        barry.setCard(table.lastCard());
                        System.out.println(barry.getCard());

                        player1.setBalance(player1.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("3")){
                        //help from dog
                        Scanner card = new Scanner(System.in);
                        String willedCard;
                        System.out.println("Bark baaark barrrk [write like: '10D' or 'J H']: ");
                        willedCard = card.nextLine();

                        Random randomNumber = new Random();
                        int theRandomNumber;

                        theRandomNumber = randomNumber.nextInt(2);

                        if (theRandomNumber == 0) {
                            dog.setCard(willedCard);
                            player1.addCard(dog.getCard());
                        }
                        else {
                            player1.addCard("bark");
                        }

                        player1.setBalance(player1.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("4")){
                        //help from cat
                        System.out.println("Cat has run over the deck");

                        cat.setDeck(table.getShuffledDeck());
                        table.changeShuffledDeck(cat.shuffleCatDeck());

                        player1.setBalance(player1.getBalance()-25);
                        bank.setMoney(bank.payService(25));
                    }
                    else if (menuChoice.equals("5")){
                        //help from magician
                        System.out.println("Focus Pocus...");

                        magician.setDeck(player1.getHand());
                        player1.setHand(magician.deleteCard());

                        player1.setBalance(player1.getBalance()-50);
                        bank.setMoney(bank.payService(50));
                    }
                }

                //compute points
                player1.setPoints(player1.totalPoints(player1.getHand()));
                int playerOnePoints = player1.getPoints();

                hand = table.firstRound();
                player2.setHand(hand);

                //take bets
                player2.setBalance(player2.entryGame());

                //play a round
                System.out.println("------Round " + noRound + "------");
                System.out.println(player2.getName() + "'s turn");
                System.out.println("");

                menuChoice = "1";
                while (menuChoice.equals("1") || menuChoice.equals("2") || menuChoice.equals("3") || menuChoice.equals("4") || menuChoice.equals("5")) {
                    System.out.println(player2.getName() + "'s cards: " + player2.getHand());
                    System.out.println(player2.getName() + "'s balance: " + player2.getBalance());
                    System.out.println("");

                    menuChoice = playerMenu();

                    if (menuChoice.equals("1")) {
                        String drawCard;

                        drawCard = table.lastCard();
                        table.deleteCard();

                        player2.addCard(drawCard);

                    }
                    else if (menuChoice.equals("2")){
                        //ask Barry Alen for help
                        barry.setCard(table.lastCard());
                        System.out.println(barry.getCard());

                        player2.setBalance(player2.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("3")){
                        //help from dog
                        Scanner card = new Scanner(System.in);
                        String willedCard;
                        System.out.println("Bark baaark barrrk [write like: '10D' or 'J H']: ");
                        willedCard = card.nextLine();

                        Random randomNumber = new Random();
                        int theRandomNumber;

                        theRandomNumber = randomNumber.nextInt(2);

                        if (theRandomNumber == 0) {
                            dog.setCard(willedCard);
                            player2.addCard(dog.getCard());
                        }
                        else {
                            player2.addCard("bark");
                        }

                        player2.setBalance(player2.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("4")){
                        //help from cat
                        System.out.println("Cat has run over the deck");

                        cat.setDeck(table.getShuffledDeck());
                        table.changeShuffledDeck(cat.shuffleCatDeck());

                        player2.setBalance(player2.getBalance()-25);
                        bank.setMoney(bank.payService(25));
                    }
                    else if (menuChoice.equals("5")){
                        //help from magician
                        System.out.println("Focus Pocus...");

                        magician.setDeck(player2.getHand());
                        player2.setHand(magician.deleteCard());

                        player2.setBalance(player2.getBalance()-50);
                        bank.setMoney(bank.payService(50));
                    }
                }

                //compute points
                player2.setPoints(player2.totalPoints(player2.getHand()));
                int playerTwoPoints = player2.getPoints();

                hand = table.firstRound();
                player3.setHand(hand);

                //take bets
                player3.setBalance(player3.entryGame());

                //play a round
                System.out.println("------Round " + noRound + "------");
                System.out.println(player3.getName() + "'s turn");
                System.out.println("");

                menuChoice = "1";
                while (menuChoice.equals("1") || menuChoice.equals("2") || menuChoice.equals("3") || menuChoice.equals("4") || menuChoice.equals("5")) {
                    System.out.println(player3.getName() + "'s cards: " + player3.getHand());
                    System.out.println(player3.getName() + "'s balance: " + player3.getBalance());
                    System.out.println("");

                    menuChoice = playerMenu();

                    if (menuChoice.equals("1")) {
                        String drawCard;

                        drawCard = table.lastCard();
                        table.deleteCard();

                        player3.addCard(drawCard);

                    }
                    else if (menuChoice.equals("2")){
                        //ask Barry Alen for help
                        barry.setCard(table.lastCard());
                        System.out.println(barry.getCard());

                        player3.setBalance(player3.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("3")){
                        //help from dog
                        Scanner card = new Scanner(System.in);
                        String willedCard;
                        System.out.println("Bark baaark barrrk [write like: '10D' or 'J H']: ");
                        willedCard = card.nextLine();

                        Random randomNumber = new Random();
                        int theRandomNumber;

                        theRandomNumber = randomNumber.nextInt(2);

                        if (theRandomNumber == 0) {
                            dog.setCard(willedCard);
                            player3.addCard(dog.getCard());
                        }
                        else {
                            player3.addCard("bark");
                        }

                        player3.setBalance(player3.getBalance()-100);
                        bank.setMoney(bank.payService(100));
                    }
                    else if (menuChoice.equals("4")){
                        //help from cat
                        System.out.println("Cat has run over the deck");

                        cat.setDeck(table.getShuffledDeck());
                        table.changeShuffledDeck(cat.shuffleCatDeck());

                        player3.setBalance(player3.getBalance()-25);
                        bank.setMoney(bank.payService(25));
                    }
                    else if (menuChoice.equals("5")){
                        //help from magician
                        System.out.println("Focus Pocus...");

                        magician.setDeck(player3.getHand());
                        player3.setHand(magician.deleteCard());

                        player3.setBalance(player3.getBalance()-50);
                        bank.setMoney(bank.payService(50));
                    }
                }

                //compute points
                player3.setPoints(player3.totalPoints(player3.getHand()));
                int playerThreePoints = player3.getPoints();

                //dealer's move
                dealer.setPoints(dealer.totalPoints(dealer.getHand()));
                int dealerPoints = dealer.getPoints();
                String dealerCard;

                while (dealerPoints < 17){
                    dealerCard = table.lastCard();
                    table.deleteCard();

                    dealer.addCard(dealerCard);
                    dealer.setPoints(dealer.totalPoints(dealer.getHand()));

                    dealerPoints = dealer.getPoints();
                }

                System.out.println("dealer's cards: " + dealer.getHand());

                //compare the points with the dealer
                if (playerOnePoints == dealerPoints && dealerPoints <= 21){
                    player1.setBalance(player1.drawRound());
                } else if (playerOnePoints > dealerPoints && playerOnePoints <= 21){
                    player1.setBalance(player1.winRound());
                    System.out.println(player1.getName() + " won !!!");
                } else if (playerOnePoints < dealerPoints && dealerPoints > 21){
                    player1.setBalance(player1.winRound());
                    System.out.println(player1.getName() + " won !!!");
                }

                if (playerTwoPoints == dealerPoints && dealerPoints <= 21){
                    player2.setBalance(player2.drawRound());
                } else if (playerTwoPoints > dealerPoints && playerTwoPoints <= 21){
                    player2.setBalance(player2.winRound());
                    System.out.println(player2.getName() + " won !!!");
                } else if (playerTwoPoints < dealerPoints && dealerPoints > 21){
                    player2.setBalance(player2.winRound());
                    System.out.println(player2.getName() + " won !!!");
                }

                if (playerThreePoints == dealerPoints && dealerPoints <= 21){
                    player3.setBalance(player3.drawRound());
                } else if (playerThreePoints > dealerPoints && playerThreePoints <= 21){
                    player3.setBalance(player3.winRound());
                    System.out.println(player3.getName() + " won !!!");
                } else if (playerThreePoints < dealerPoints && dealerPoints > 21){
                    player3.setBalance(player3.winRound());
                    System.out.println(player3.getName() + " won !!!");
                }


            } else {
                System.out.println("Not possible at the moment.");
                System.out.println("The max number of players will be extended in the next update");
            }

            // see if the players want to continue
            System.out.println("Do you want to continue [true / false]: ");
            Scanner playNext = new Scanner(System.in);

            noRound++;
            play = playNext.nextBoolean();
        }
    }

    public static String playerMenu() {
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

            System.out.println("");
            System.out.println("Your choice: ");
            menuChoice = choice.nextLine();

            if (menuChoice.equals("1") || menuChoice.equals("0") || menuChoice.equals("3") || menuChoice.equals("2") || menuChoice.equals("4")|| menuChoice.equals("5")) {
                return menuChoice;
            } else {
                System.out.println("No such value");
            }
        }
    }


    public static void test(){
        //here put test stuff
        Table table1 = new Table();
        table1.setShuffledDeck();
        System.out.println(table1.getShuffledDeck());
        System.out.println(table1.getShuffledDeck().get(1));

        Player player1 = new Player();
        player1.setBalance(300);
        System.out.println(player1.checkBalance());
        player1.entryGame();
        System.out.println(player1.checkBalance());
        player1.setHand(table1.firstRound());
        System.out.println(player1.getHand());

        Player player2 = new Player();
        player2.setBalance(500);
        System.out.println(player2.checkBalance());

        System.out.println(player1.hand.get(0));
        System.out.println((player1.hand.get(0).charAt(0)));
        System.out.println(player1.cardPoints(player1.hand.get(0)));
        System.out.println(player1.totalPoints(player1.hand));

        Security nenea = new Security();
        nenea.setName("Nastea");
        nenea.setRank("Boss");
        nenea.setTable(1);
        System.out.println("Print the president");
        System.out.println(nenea.getName());

        President president = new President();
        president.setCountry("Moldova");
        president.setName("Maia Sandu");
        System.out.println("President: ");
        System.out.println(president.getName());
    }
}