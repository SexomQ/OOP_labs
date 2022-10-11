import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cat implements DeckManipulator{
    public boolean isHuman;

    public List<String> deck = new ArrayList<String>();

    public List<String> getDeck() {
        return deck;
    }

    public void setDeck(List<String> deck) {
        this.deck = deck;
    }
    public Cat(){
        isHuman = false;
    }

    public List<String> shuffleCatDeck(){
        Collections.shuffle(deck);
        return deck;
    }
}
