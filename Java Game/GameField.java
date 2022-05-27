import java.util.HashMap;

public class GameField {
    int i = 0;                  // Zähler für Spielfeldgröße 
    int j = 0; 

    HashMap<BaseCard,Float> hmPlayer1 = new HashMap<BaseCard, Float>(); 
    HashMap<BaseCard,Float> hmPlayer2 = new HashMap<BaseCard, Float>();


    HashMap<BaseCard,Float> hmGravePlayer1 = new HashMap<BaseCard, Float>(); 
    HashMap<BaseCard,Float> hmGravePlayer2 = new HashMap<BaseCard, Float>();
    
    public void atkEnemyCard(Unit atkCard , Unit defCard ,  Player player){    // Es muss der gegnerische Player uebergeben werden 
        int a = atkCard.getAtk(atkCard);
        defCard.setDef(defCard, a);
        System.out.println("Du hast " + a + " Schaden an der Karte " + defCard.getCardName(defCard) + " verrsacht ");
        int h = defCard.getDef(defCard);
        if(a > h ){
            a = a - h; 
            defCard.setLife(defCard, a);
            defCard.setDef(defCard, h);
            int l = defCard.getLife(defCard);
            if (l <=0 ){
                destroyCard(defCard, player);
                System.out.println("Die gegnerische Karte " + defCard.getCardName(defCard) + " wurde zerstört ");  // Textadverture 
            }
    	}
        else {
            h = h - a; 
            defCard.setDef(defCard, h);
            }
        
    }

    public void atkEnemyPlayer(Unit atkCard , Player player){       // Gegnerischer Player muss übergebenwerden 
        int a = atkCard.getAtk(atkCard);
        player.decreasePlayerLife(player,a);
    }

    public void specialEffect(){
        
    }

    public void playCardPOnField(BaseCard playCradFromHand , Player player){    // Es muss der eigene Spieler übergeben werden 
        if (player.getPlayerId(player) == 0 ){
            if ( i <=7 ){                                                       // Zähler um nicht mehr als 8 Karten auf dem Spielfeld zu erlauben 
                hmPlayer1.put(playCradFromHand, 0.004f);
                System.out.println("Player1 hast " + playCradFromHand.getCardName(playCradFromHand) + " auf das Spielfeld gelegt " );    // Textadverture 
                i++;
            }
            else{
                System.out.println("Spielfeld bereits voll"); 
                }
           }
        else if ( player.getPlayerId(player) == 1){             // PlayerId um den Spieler zu identifizieren nur 0 oder 1 möglich 
            if ( j <=7 ){                                       // Zähler um nicht mehr als 8 Karten auf dem Spielfeld zu erlauben 
                hmPlayer2.put(playCradFromHand, 0.004f);
                System.out.println("Player 2 hat " + playCradFromHand.getCardName(playCradFromHand) + " auf das Spielfeld gelegt " );   // Textadverture 
                j++;   
            }
            else{
                System.out.println("Spielfeld bereits voll");   // Hier muss noch eine Exeption hin falls PlayerId != 0 oder 1 
                }
            }
        }

        
        

       public void destroyCard(BaseCard card , Player player){  // Karten die zerstört werden werden automatisch auf den Friedhof gelegt 
        if (player.getPlayerId(player) == 0 ){
            if ( i != 0 ) { 
           hmPlayer1.remove(card,0.004f);
           hmGravePlayer1.put(card,0.09f);
           i--;
            }  
        else {
           System.out.println("Zug ungültig keien Karte auf dem Spielfeld");
            }
        }
        else if (player.getPlayerId(player) == 1 ){
            if ( j != 0 ) { 
           hmPlayer2.remove(card,0.004f);
           hmGravePlayer2.put(card,0.09f);
           j--;
        }  
        else {
           System.out.println("Zug ungültig keien Karte auf dem Spielfeld");
            }
        }
    }


    public void enemyCardToOwnGrave() {
    }

    public void addToGravePlayer1(BaseCard card, Player player) {
        hmGravePlayer1.put(card, 0.09f);
    }


}
