import java.util.ArrayList;
import java.util.List;

interface DeckManipulator extends Booster{
    public List<String> getDeck();

    public void setDeck(List<String> deck);

}
