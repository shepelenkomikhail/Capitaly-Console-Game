package Player;

import Field.Property;

public class Careful extends Player
{
    public Careful(String name) {
        super(name);
    }  //Constructor
    @Override
    public boolean visit(Property propertyField) //Buys unowned property if the player has enough money
    {                                                         //or builds a house on their own property without a house
        if (propertyField.getOwner() == null && this.getMoney() > 1000 * 2)
        {
            propertyField.purchase(this);
            return true;
        }
        if(propertyField.getOwner() == this && !propertyField.isHouseBuilt() && this.getMoney() > 4000 * 2)
        {
            propertyField.purchaseHouse();
            return true;
        }
        if(propertyField.getOwner() != this && propertyField.getOwner() != null ) {return propertyField.payRent(this);}
        return true;                                                                  //If the property is owned, paying the rent
    }
    @Override public String toString() {return "(C)Player: " + this.getName() + ", Money: " + this.getMoney() + ", Position: " + this.getPosition();}
}   //Show the position of player and his budget
