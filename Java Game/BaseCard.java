public class  BaseCard  {
    int cardId;
    String name;
     int rare;
     int cost;
     int fraktion;
     String text; 
     String lore; 
     int specialEffekt;



    public String getCardName(BaseCard card) {
    return this.name; 
    }

    public int getCardId (BaseCard card ) {
        return this.cardId;
    }

    public int getSpecialEffectId(BaseCard card) {
        return this.specialEffekt; 
    }

    public int getCost (BaseCard card ) {
        return this.cost;
    }


}






