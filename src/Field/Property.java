package Field;

import Player.Player;

public class Property extends Field
{
    private Player Owner;
    private Boolean isHouseBuilt;
    public Property()       //Constructor
    {
        this.Owner = null;
        this.isHouseBuilt = false;
    }
    public Player getOwner() {
        return this.Owner;
    }
    public void resetOwner() {
        this.Owner = null;
    }
    public Boolean isHouseBuilt() {return this.isHouseBuilt;}
    public void purchase(Player player)          //Purchasing the property
    {
        if(player.getMoney() < 1000) {return;}
        player.setMoney(player.getMoney() - 1000);         //Decreasing a budget of the player and setting a new owner
        this.Owner = player;
    }
    public Boolean payRent(Player purchaser)       //Paying the rent
    {
        int amount =  this.isHouseBuilt ? 2000 : 500;      //If there is a house, player should pay 2000, otherwise 500
        if(purchaser.getMoney() < amount)             //Checking if the player has enough money
        {
            System.out.println("Player " + purchaser.getName() + " is not able to pay " + amount +
                    " to " + this.Owner.getName());
            return false;
        }
        purchaser.setMoney(purchaser.getMoney() - amount);
        this.Owner.setMoney(this.Owner.getMoney() + amount);        //Changing the budgets
        return true;
    }
    public void purchaseHouse()                           //Purchasing the house
    {
        if(this.Owner.getMoney() < 4000) {return;}
        this.isHouseBuilt = true;
        this.Owner.setMoney(this.Owner.getMoney() - 4000);
    }
    @Override
    public boolean accept(IVisitor visitor) {
        return visitor.visit(this);
    }
    @Override
    public String toString()      //Giving the string representation (who is the owner od property)
    {
        String ownerName = (Owner != null) ? Owner.getName() : "";
        return "Property [Owner: " + ownerName + "]";
    }
}
