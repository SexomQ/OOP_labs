import java.util.List;

public class Magician extends DeckManipulator{
    public Magician(){
        isHuman = true;
    }

    public List<String> deleteCard(){
        deck.remove(deck.get(deck.size()-1));
        return deck;
    }
}
