#MVC implementation

Here I implemented the MVC architecture pattern.

Representation of directory ierarchy:

src:
    controller:
        GameController.java
    view:
        GameView.java
    model:
        boosters:
            Booster.java
            CardManipulator.java (inherit from Booster)
            Cat.java (inherit from DeckManipulator)
            DeckManipulator.java (inherit from Booster)
            Dog.java (inherit from CardManipulator)
            Magician.java (inherit from CardManipulator)
            TheFlash.java (inherit from DeckManipulator)
        initialGame:
            InitialGame.java
            GameActors.java
            Dealer.java
            Player.java
            Table.java
        testing:
            Test.java
            Bank.java
    Main2.java
            