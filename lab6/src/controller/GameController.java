package controller;

import model.GameModel;
import model.boosters.Cat;
import model.boosters.Dog;
import model.boosters.Magician;
import model.boosters.TheFlash;
import model.initialGame.Player;
import model.testing.Bank;
import view.GameView;
import model.initialGame.*;

import java.util.List;

public class GameController {
    private GameView view;
    private Player player;
    private Table table;
    private Dealer dealer;
    private Cat cat;
    private Dog dog;
    private Magician magician;
    private TheFlash theFlash;
    private Bank bank;


    public GameController(GameView view, Player player, Dealer dealer, Table table, Cat cat, Dog dog, Magician magician, TheFlash theFlash, Bank bank)
    {
        this.view = view;
        this.player = player;
        this.table = table;
        this.dealer = dealer;
        this.cat = cat;
        this.dog = dog;
        this.magician = magician;
        this.theFlash = theFlash;
        this.bank = bank;
    }

    public void setPlayerName(String name) {
        player.setName(name);
    }

    public String getPlayerName(){
        return player.getName();
    }

    public void setPlayerBalance(int money){
        player.setBalance(money);
    }

    public int getPlayerBalance(){
        return player.getBalance();
    }

    public void setBankMoney(int money){
        bank.setMoney(money);
    }

    public void setShuffledDeck(){
        table.setShuffledDeck();
    }
    public List<String> firstRound(){
        return table.firstRound();
    }
    public void setDealerHand(List<String> hand) {
        dealer.setHand(hand);
    }
    public void setPlayerHand(List<String> hand) {
        player.setHand(hand);
    }
    public List<String> getDealerHand(){
        return dealer.getHand();
    }
    public List<String> getPlayerHand(){
        return player.getHand();
    }
    public int playerEntryGame(){
        return player.entryGame();
    }
    public int totalPointsPlayer(List<String> hand){
        return player.totalPoints(hand);
    }
    public String getLastCard () {
        return table.lastCard();
    }
    public void deleteLastCard() {
        table.deleteCard();
    }
    public void playerAddCard (String card){
        player.addCard(card);
    }
    public void setBarryCard (String card){
        theFlash.setCard(card);
    }
    public String getBarryCard (){
        return theFlash.getCard();
    }
    public void setDogCard (String card){
        dog.setCard(card);
    }
    public String getDogCard (){
        return dog.getCard();
    }
    public void setCatDeck(List<String> deck){
        cat.setDeck(deck);
    }
    public List<String> getCatDeck (){
        return cat.shuffleCatDeck();
    }
    public void changeShuffledDeck(List<String> deck){
        table.changeShuffledDeck(deck);
    }
    public void setMagicianDeck(List<String> hand){
        magician.setDeck(hand);
    }
    public List<String> magicianDeleteCard (){
        return magician.deleteCard();
    }
    public int playerGetPoints(){
        return player.getPoints();
    }
    public void playerSetPoints(int points){
        player.setPoints(points);
    }
    public int dealerGetPoints(){
        return dealer.getPoints();
    }
    public void dealerSetPoints(int points){
        dealer.setPoints(points);
    }
    public void dealerAddCard(String card){
        dealer.addCard(card);
    }
    public int drawRound(){
        return player.drawRound();
    }
    public int winRound(){
        return player.winRound();
    }
    public void setNoPlayers(int players){
        table.setNoPlayers(players);
    }
    public int playerPayFee(int money){
        return player.payFee(money);
    }
    public List<String> getTableShuffledDeck (){
        return table.getShuffledDeck();
    }
    public List<String> getTableDeck (){
        return table.getDeck();
    }
    public int dealerTotalPoints(List<String> hand){
        return dealer.totalPoints(hand);
    }
}
