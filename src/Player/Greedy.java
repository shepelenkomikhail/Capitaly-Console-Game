package Player;

import Field.Property;

public class Greedy extends Player
{
    public Greedy(String name) {
        super(name);
    } //Constructor
    @Override
    public boolean visit(Property propertyField)       //Buys a properties and builds the houses thereS
    {
        if(propertyField.getOwner() == null)
        {
            propertyField.purchase(this);
            return true;
        }                                                                           //If the property is owned, paying the rent
        if(propertyField.getOwner() != this && propertyField.getOwner() != null ) {return propertyField.payRent(this);}
        if(propertyField.getOwner() == this && !propertyField.isHouseBuilt())
        {
            propertyField.purchaseHouse();
            return true;
        }
        return true;
    }
    @Override public String toString() {return "(G)Player: " + this.getName() + ", Money: " + this.getMoney() + ", Position: " + this.getPosition();}
}   //Show the position of player and his budget
