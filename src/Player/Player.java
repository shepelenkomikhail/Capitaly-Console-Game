package Player;

import Field.Lucky;
import Field.Property;
import Field.Service;
import Field.IVisitor;

public abstract class Player implements IVisitor
{
    private final String Name;
    private int Money;
    private int Position;
    private boolean isBankrupt = false;

    public Player(String name)     //Constructor
    {
        this.Name = name;
        this.Money = 10000;
        this.Position = 0;
    }

    public String getName() {
        return this.Name;
    }
    public int getPosition() {
        return this.Position;
    }
    public int getMoney() {return this.Money;}
    public void setMoney(int money) {
        this.Money = money;
    }
    public boolean isBankrupt() {return this.isBankrupt;}
    public void setBankrupt() {
        this.isBankrupt = true;
    }
    public void Move(int steps, int numberOfFields) {this.Position = (this.Position + steps) % numberOfFields;}
                            //Method to move on in the board (change the position)
    @Override
    public boolean visit(Property propertyField) {
        return false;
    }
    @Override
    public boolean visit(Lucky luckyField)           //Acting depending on the special properties of the lucky field
    {
        this.Money += luckyField.getRecievedMoney();
        return true;
    }
    @Override
    public boolean visit(Service serviceField)        //Acting depending on the special properties of the service field
    {
        if(this.Money < serviceField.getMoneyToPay())
        {
            System.out.println("Player " + this.Name + " is not able to pay " +
                    serviceField.getMoneyToPay() +  " to service");
            return false;
        }
        this.Money -= serviceField.getMoneyToPay();
        return true;
    }
    @Override public String toString() {return "Player: " + this.Name + ", Money: " + this.Money + ", Position: " + this.Position;}
}           //Method converts to string a player's object

