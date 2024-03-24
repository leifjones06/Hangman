import java.util.*;
public class GuessWord
{
  private String word;
  private Scanner keyboard;
  private int currentPlayer = 0;
  private int numPlayers;
  private int[] points;
  private int[] consecCor;
  private StringBuilder blanks = new StringBuilder();
  public GuessWord(Scanner keyboard, GetPlayers playersGetter)
  {
    this.keyboard = keyboard;
    this.numPlayers = playersGetter.players;
    this.points = new int[numPlayers];
    this.consecCor = new int[numPlayers];
  }
  public String getWord(String str)
  {
    word = str;
    return word;
  }
  public void display()
  {
    System.out.print("\033[H\033[2J");  
    System.out.flush(); // Clears console along w/ above line
    for(int i = 0; i < word.length(); i++)
      {
        blanks.append("_ "); // Requires own variable to easily replace blanks when player guesses correctly
      }
    System.out.print(blanks);
  }
  public int guess(int incor)
  {
    try
      {
        System.out.println();
        System.out.println("Player " + (currentPlayer + 1) + "'s turn");
        System.out.println("Enter a letter: ");
        String letter = keyboard.next();
        if(letter.length() > 1)
        {
          System.out.println("Please enter only one letter");
          return guess(incor);
        }
        else if(!Character.isLetter(letter.charAt(0)))
        {
          System.out.println("Please enter a letter");
          return guess(incor);
        }
        boolean correct = false;
          for(int i = 0; i < word.length(); i++)
            {
              if(word.toUpperCase().charAt(i) == letter.toUpperCase().charAt(0))
              {
                blanks.setCharAt(i * 2, letter.toUpperCase().charAt(0)); // *2 accounts for spaces; doesn't display properly otherwise
                correct = true;
              }
            }
          System.out.println();
          System.out.println(blanks);
          if(correct)
            {
              consecCor[currentPlayer]++;
              if(points[currentPlayer] == 0)
              {
                points[currentPlayer] += 5;
              }
              else
              {
                points[currentPlayer] = points[currentPlayer] * consecCor[currentPlayer];
              }
              if(blanks.indexOf("_") == -1)
              {
                System.out.println("\nCongratulations! You guessed the word!");
                return incor;
              }
            }
          else
          {
            consecCor[currentPlayer] = 0;
            System.out.println("Incorrect!");
            incor++;
            if(incor >= 6)
            {
              System.out.println("You lost! The word was " + word);
              return incor;
            }
          }
        currentPlayer = (currentPlayer + 1) % numPlayers;
        return guess(incor);
      }
    catch(NoSuchElementException e)
      {
        System.out.println("Please enter a letter");
        return guess(incor);
      }
  }
  public void displayPoints()
  {
    System.out.println();
    for(int i = 0; i < numPlayers; i++)
      {
        System.out.println("Player " + (i + 1) + " has " + points[i] + " points");
      }
    int maxPoints = Integer.MIN_VALUE;
    List<Integer> winners = new ArrayList<Integer>();
    for(int i = 0; i < numPlayers; i++)
      {
        if(points[i] > maxPoints) // Determines highest number of points
        {
          maxPoints = points[i];
        }
      }
    for(int i = 0; i < numPlayers && numPlayers > 1; i++) // Finds all players with highest points
      {
        if(points[i] == maxPoints)
        {
          winners.add(i + 1);
        }
      }
    if(winners.size() == 1)
    {
      System.out.println("Player " + winners.get(0) + " wins!");
    }
    else if(winners.size() == 2)
    {
      System.out.println("It's a tie between players " + winners.get(0) + " and " + winners.get(1) + "!");
    }
    else if(numPlayers > 1)
    {
       System.out.print("It's a tie between players ");
        for (int i = 0; i < winners.size(); i++)
        {
          System.out.print(winners.get(i));
          if (i < winners.size() - 2)
            {
              System.out.print(", ");
            }
          else if(i < winners.size() - 1)
          {
            System.out.print(", and ");
          }
        }
      System.out.print("!");
    }
  }
}