import java.util.ArrayList;
import java.util.List;

public class Magician {
    List<String> MagicianDeck = new ArrayList<String>();

    public void setMagicianDeck(List<String> magicianDeck) {
        MagicianDeck = magicianDeck;
    }

    public List<String> getMagicianDeck() {
        return MagicianDeck;
    }

    public List<String> deleteCard(){
        MagicianDeck.remove(MagicianDeck.get(MagicianDeck.size()-1));
        return MagicianDeck;
    }
}
