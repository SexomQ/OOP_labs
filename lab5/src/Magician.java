import java.util.ArrayList;
import java.util.List;

public class Magician implements DeckManipulator{
    public boolean isHuman;

    public List<String> deck = new ArrayList<String>();

    public List<String> getDeck() {
        return deck;
    }

    public void setDeck(List<String> deck) {
        this.deck = deck;
    }
    public Magician(){
        isHuman = true;
    }

    public List<String> deleteCard(){
        deck.remove(deck.get(deck.size()-1));
        return deck;
    }
}
