import java.util.ArrayList;
import java.util.List;

public class DeckManipulator extends Booster{
    public List<String> deck = new ArrayList<String>();

    public List<String> getDeck() {
        return deck;
    }

    public void setDeck(List<String> deck) {
        this.deck = deck;
    }

}
