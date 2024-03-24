import java.util.*;
import java.io.*;
public class GetCategory
  {
    private String chosenFile;
    private Scanner keyboard;
    private String[] fileNames;
    public String randWord;
    public GetCategory(String randWord)
    {
      this.randWord = randWord;
    }
    public GetCategory(Scanner keyboard)
    {
      this.keyboard = keyboard;
    }
    public int getCategory()
    {
      String[] fileNames = {"Animals.txt", "Body Parts.txt", "Countries.txt", "Food and Cooking.txt", "Music.txt", "Occupations.txt", "Science.txt", "Sports.txt"}; //Keep .txt for getWord method
      for(int i = 0; i < fileNames.length; i++)
        {
          String fileNoExt = fileNames[i].replaceFirst("[.][^.]+$", ""); //Removes .txt extension
          System.out.println((i + 1) + ". " + fileNoExt);
        }
      System.out.println((fileNames.length + 1) + ". Random");
      try
        {
          int num = keyboard.nextInt();
          if(num == 9)
          {
            Random random = new Random();
            num = random.nextInt(fileNames.length) + 1;
          }
          if(num < 1 || num > fileNames.length)
          {
            System.out.println("Please enter a number between 1 and " + (fileNames.length + 1));
            return getCategory();
          }
          chosenFile = fileNames[num - 1];
          return num;
        }
      catch(NoSuchElementException e)
      {
        System.out.println("Please enter a valid number");
        keyboard.next();
        getCategory();
        return -1;
      }
    }
    public String getWord(String randWord)
    {
      try
        {
          Scanner inFile = new Scanner(new File(chosenFile));
          List<String> words = new ArrayList<String>();
          while(inFile.hasNextLine())
            {
              String word = inFile.nextLine();
              if(word.length() <=10)
              {
                words.add(word);
              }
            }
            Random random = new Random();
            int randInd = random.nextInt(words.size());
            randWord = words.get(randInd);
            return randWord;
        }
      catch(FileNotFoundException e)
        {
          System.out.println("File not found");
          return "null";
        }
    }
  }