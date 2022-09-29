public class Bank {
    int money = 0;

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void fees(int players){
        money = money + 50 * players;
    }

    public int payService(int payments){
        money = money + payments;
        return money;
    }
}
