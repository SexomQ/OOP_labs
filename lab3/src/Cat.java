import java.util.Collections;
import java.util.List;

public class Cat extends DeckManipulator{
    public Cat(){
        isHuman = false;
    }

    public List<String> shuffleCatDeck(){
        Collections.shuffle(deck);
        return deck;
    }
}
