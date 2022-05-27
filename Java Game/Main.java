public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!"); 
        Player p1 = new Player(0,"I am Player1", 0); 
        Player p2 = new Player(1,"I am Player2", 1); 


        BaseCard karte1 = new Unit(1, "BlanName", 2 , 1 , 0 , "Die Karte der Karten" , "lore" , 3 , 5 , 15 , 10 ); 

        GameField game = new GameField(); 
        game.playCardPOnField(karte1, p1);
    }
}

