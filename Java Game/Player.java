public class Player extends GameField  {
    int playerId;
    String playerName; 
    int playerLife = 30; 


    public Player(int playerId , String playerName , int playerLife){
        this.playerId = playerId;
        this.playerName = playerName; 
        this.playerLife = playerLife;
    }

    public int getPlayerId (Player player ) {
        return this.playerId;
    }



    public void decreasePlayerLife(Player player, int a) {
        this.playerLife = playerLife - a;
    } 
        
    
}
