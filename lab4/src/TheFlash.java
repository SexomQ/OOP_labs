public class TheFlash implements CardManipulator{
    public String card;
    public boolean isHuman;
    public void setCard(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }
    public TheFlash(){
        isHuman = true;
    }
}
