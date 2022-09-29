import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cat {
    List<String> catDeck = new ArrayList<String>();

    public void setCatDeck(List<String> catDeck) {
        this.catDeck = catDeck;
    }

    public List<String> getCatDeck() {
        return catDeck;
    }

    public List<String> shuffleCatDeck(){
        Collections.shuffle(catDeck);
        return catDeck;
    }
}
