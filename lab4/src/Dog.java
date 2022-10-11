public class Dog implements CardManipulator{
    public String card;
    public boolean isHuman;
    public void setCard(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }
    public Dog(){
        isHuman = false;
    }
}
