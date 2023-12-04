import Field.Field;
import Player.Careful;
import Player.Greedy;
import Player.Tactical;
import Player.Player;
import Field.Lucky;
import Field.Property;
import Field.Service;
import Game.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)    //Main void, start point of the program
    {
        String file = "src/input.txt";
        List<Field> fields = new ArrayList<>();
        List<Player> players = new ArrayList<>();                   //Initialization of the arrays to store the input
        List<Integer> diceThrow = new ArrayList<>();
        try {
            ReadInputFile(file, fields, players, diceThrow);
        } catch (FileNotFoundException e) {
            System.out.println("File is not found: " + file);
            return;
        } catch (IOException e) {
            System.out.println("Error reading file!" + e.getMessage());                          //Catching the possible errors
        } catch (InvalidStrategyException e) {
            System.out.println("File has an invalid strategy: " + e.getMessage());
        } catch (EmptyFileException e) {
            System.out.println("The input file is empty: " + e.getMessage());
            return;
        }
        Game game = new Game(players, fields, diceThrow);    //Creating a game object
        game.Play();                              //Starting the game
    }
    public static void ReadInputFile(String file, List<Field> fields, List<Player> players, List<Integer> diceThrow)
            throws IOException, InvalidStrategyException, EmptyFileException
    {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            if (line == null)
            {
                bufferedReader.close();
                throw new EmptyFileException("The input file is empty!");
            }
            int numberOfFields = Integer.parseInt(line);        //Read the num of fields
            for (int i = 0; i < numberOfFields; i++) {                               //Iterating through the fields
                String[]parts = bufferedReader.readLine().split(" ");
                String type = parts[0];
                switch (type.toLowerCase()) {
                    case "property":
                        fields.add(new Property());
                        break;
                    case "lucky":
                        fields.add(new Lucky(Integer.parseInt(parts[1])));
                        break;
                    case "service":                                           //Create a field object depending on its type
                        fields.add(new Service(Integer.parseInt(parts[1])));
                        break;
                    default:
                        throw new RuntimeException("Field type is wrong: " + type);     //Errors related to unknown field types
                }
            }
            int numberOfPlayers = Integer.parseInt(bufferedReader.readLine());     //Read the num of players
            for(int i = 0; i < numberOfPlayers; i++)                                 //Iterating through the players
            {
                 String[]parts = bufferedReader.readLine().split(" ");
                 String name = parts[0];
                 String strategy = parts[1];
                 switch (strategy.toLowerCase()) {
                     case "greedy":
                         players.add(new Greedy(name));
                         break;
                     case "careful":                           //Create a player object depending on its type
                         players.add(new Careful(name));
                         break;
                     case "tactical":
                         players.add(new Tactical(name));
                         break;
                     default:
                         throw new InvalidStrategyException(strategy);  //Errors related to unknown player strategies
                 }
            }
            int rollsAmount = Integer.parseInt(bufferedReader.readLine()); //Read the num of diece throws
            for(int i = 0; i < rollsAmount; i++) {diceThrow.add(Integer.parseInt(bufferedReader.readLine()));}   //Iterating through it
    }
    public static class InvalidStrategyException extends Exception       //The class to catch errors related to invalid strategies in the input file
    {
        public InvalidStrategyException(String strategy) {super("Invalid strategy: " + strategy);}
    }
    public static class EmptyFileException extends Exception
    {
        public EmptyFileException(String message) {super("The file is empty " + message);}
    }
}