import java.util.*;

public class Table implements InitialGame{
    private int noPlayers;
    private final String[] stack = {"2 S", "3 S", "4 S", "5 S", "6 S", "7 S", "8 S", "9 S", "10S", "J S", "Q S", "K S", "A S", "2 C", "3 C", "4 C", "5 C", "6 C", "7 C", "8 C", "9 C", "10C", "J C", "Q C", "K C", "A C", "2 D", "3 D", "4 D", "5 D", "6 D", "7 D", "8 D", "9 D", "10D", "J D", "Q D", "K D", "A D", "2 H", "3 H", "4 H", "5 H", "6 H", "7 H", "8 H", "9 H", "10H", "J H", "Q H", "K H", "A H"};
    List<String> deck = Arrays.asList(stack);
    private List<String> shuffledDeck = new ArrayList<String>();

    public List<String> getShuffledDeck() {
        return shuffledDeck;
    }
    public int noTable;

    public int getNoPlayers() {
        return noPlayers;
    }

    //shuffle the deck
    public void setShuffledDeck() {
        Random rand = new Random();
        shuffledDeck.addAll(deck);
        Collections.shuffle(shuffledDeck);
    }

    public void changeShuffledDeck(List<String> newDeck){
        shuffledDeck = newDeck;
    }

    public void setNoPlayers(int noPlayers) {
        this.noPlayers = noPlayers;
    }

    public void setNoTable(int noTable) {
        this.noTable = noTable;
    }

    public int getNoTable() {
        return noTable;
    }

    public String getType() {
        return type;
    }

    //creating a hand of 2 cards
    public List<String> firstRound(){
        List<String> firstCards = new ArrayList<String>();
        firstCards.add(shuffledDeck.get(shuffledDeck.size()-1));
        shuffledDeck.remove(shuffledDeck.size()-1);
        firstCards.add(shuffledDeck.get(shuffledDeck.size()-1));
        shuffledDeck.remove(shuffledDeck.size()-1);
        return firstCards;
    }

    public String lastCard(){
        return shuffledDeck.get(shuffledDeck.size()-1);
    }

    public void deleteCard(){
        shuffledDeck.remove(shuffledDeck.size()-1);
    }

}
