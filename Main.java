import java.util.*;
public class Main
{
  public static void main(String[] args)
  {
    Scanner keyboard = new Scanner(System.in);
    String randWord = "";
    int incor = 0;
    System.out.println("Enter number of players (max 10): ");
    GetPlayers app1 = new GetPlayers(keyboard);
    app1.getPlayers();
    System.out.println("Enter the number of your selected category: ");
    GetCategory app2 = new GetCategory(keyboard);
    app2.getCategory();
    String chosenWord = app2.getWord(randWord);
    GuessWord app3 = new GuessWord(keyboard, app1);
    app3.getWord(chosenWord);
    app3.display();
    incor = app3.guess(incor);
    keyboard.close();
    app3.displayPoints();
  }
}