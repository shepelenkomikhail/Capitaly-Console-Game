package Player;

import Field.Property;

public class Tactical extends Player
{
    private static boolean purchasing = true;
    public Tactical(String name) {super(name);}     //Constructor
    @Override
    public boolean visit(Property propertyField)      //Buying the properties and building the houses using every second chance
    {
        if (propertyField.getOwner() == null && purchasing)
        {
            propertyField.purchase(this);
            purchasing = false;
            return true;
        }
        if(propertyField.getOwner() == this && purchasing && !propertyField.isHouseBuilt())
        {
            propertyField.purchaseHouse();
            purchasing = false;
            return true;
        }
        if(propertyField.getOwner() != this && propertyField.getOwner() != null )
        {
            return propertyField.payRent(this);       //If the property is owned, paying the rent
        }
        return true;
    }
    @Override public String toString()    //Show the position of player and his budget
    {
        return "(T)Player: " + this.getName() + ", Money: " + this.getMoney() + ", Position: " + this.getPosition();
    }
}
