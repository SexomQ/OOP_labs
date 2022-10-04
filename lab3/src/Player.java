public class Player extends GameActors{

    public int entryGame(){
        balance = balance - 100;
        return balance;
    }

    public int winRound(){
        balance = balance + 200;
        return balance;
    }

    public int drawRound(){
        balance = balance + 100;
        return balance;
    }

    public int payFee(){
        balance = balance - 50;
        return balance;
    }
}
