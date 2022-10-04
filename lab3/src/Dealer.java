public class Dealer extends GameActors{
    public Dealer(){
        name = "dealer";
        balance = 99999;
    }

    @Override
    public int cardPoints(String card){
        char value = card.charAt(0);

        if (value == '2'){
            return 2;
        }
        else if (value == '3'){
            return 3;
        }
        else if (value == '4'){
            return 4;
        }
        else if (value == '5'){
            return 5;
        }
        else if (value == '6'){
            return 6;
        }
        else if (value == '7'){
            return 7;
        }
        else if (value == '8'){
            return 8;
        }
        else if (value == '9'){
            return 9;
        }
        else if (value == 'Q' || value == '1' || value == 'K' || value == 'J'){
            return 10;
        }
        else{
            return 11;
        }
    }
}
