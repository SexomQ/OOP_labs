package model.initialGame;


import java.util.List;

public interface GameActors extends InitialGame {
    public int getBalance();
    public String getName();
    public List<String> getHand();
    public int getPoints();
    public void setHand(List<String> hand);
    public void setPoints(int points);
    public int checkBalance();
    public void addCard(String card);
    public int cardPoints(String card);
    public int totalPoints(List<String> playerHand);
}
