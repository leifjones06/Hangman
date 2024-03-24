import java.util.*;
public class GetPlayers
  {
    private Scanner keyboard;
    public int players;
    public GetPlayers(Scanner keyboard)
    {
      this.keyboard = keyboard;
    }
    public int getPlayers()
    {
      try
      {
          players = keyboard.nextInt();
          keyboard.nextLine();
          if(players < 1 || players > 10)
          {
            System.out.println("Please enter a number between 1 and 10");
            return getPlayers();
          }
          return players;
      }
      catch(NoSuchElementException e)
      {
        System.out.println("Please enter a whole number");
        keyboard.nextLine();
        return getPlayers();
      }
    }
  }