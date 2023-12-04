package Game;
import Field.Field;
import Field.Property;
import Player.Player;
import java.util.List;

public class Game
{
   private final List<Player> players;
   private final List<Field> fields;
   private final List<Integer> diceThrow;
   private int playersAmount;
   public Game(List<Player> players, List<Field> fields, List<Integer> diceThrow)        //Constructor
   {
      this.players = players;
      this.fields = fields;
      this.playersAmount = players.size();
      this.diceThrow = diceThrow;
   }
    public void Play()
    {
        int round = 0;
        while (this.playersAmount > 1)       //The game goes till one player left
        {
            System.out.println("Round: " + ++round + " -----------------");
            printFields();
            System.out.println(" ");            //Writing the number of the round after each one and simulating the game
            simulate();
        }
        result();      //Printing the result
    }
    public void simulate()
    {
        int diceInd = 0;                 //Initialization of the index to get a diece throw
        for (Player player : players)    //Iterating through the players array
        {
            if(player.isBankrupt()) {continue;}     //If the player is not a bankrupt, one can play
            int dice = diceThrow.get(diceInd);      //Getting the diece throw
            System.out.println("-------- " + player.getName() + " rolled " + dice + "--------");
            player.Move(dice, fields.size());                    //Moving on the board
            Field field = fields.get(player.getPosition());
            boolean isSuccessful = field.accept(player);
            if(!isSuccessful)                            //Declaring a player as a bankrupt
            {
                System.out.println("Player " + player.getName() + " is a bankrupt");
                removeProperties(player);
                player.setBankrupt();
                playersAmount--;
                if (playersAmount == 1) {return;}
            } else {System.out.println(player);}
            diceInd++;       //Increasing the diece index
        }
    }
    public void printFields()          //Iterating through the fields and writing it
    {
        for (Field field : fields)
        {
            System.out.print("(" + (fields.indexOf(field)) + ")");
            System.out.print(field);
            System.out.print(" >>> ");
        }
    }
    public void removeProperties(Player player)       //Removing the bankrupt
    {
        for (Field field : fields)            //Iterating through the players
        {
            if(field instanceof Property property)
            {
                if(property.getOwner() == player) {property.resetOwner();}   //Changing the owner of the property
            }
        }
    }
    public void result()                      //Printing the result
    {
        for (Player player : players)
        {
            if (player.isBankrupt()) {continue;}
            System.out.println(player.getName() + " WON!!!");
            System.out.println(player.getName() + "'s budget is " + player.getMoney());
            System.out.println("Properties of the winner: ");
            for (Field field : fields)                             //Iterating through the fields
            {
                if (field instanceof Property property)
                {
                    if (property.getOwner() == player) {System.out.println("(" + this.fields.indexOf(property) + ")" + property);}
                }
            }
        }
    }
}
