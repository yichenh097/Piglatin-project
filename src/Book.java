//A few assumptions.......

//Words will be separated by spaces. 
//There can be punctuation in a word, we will only add/keep punctuation at the end of a string if it is at the end of a string.
//    for examples: Hello.==> Ellohay.    Good-bye! ==> Ood-byegay!    so... ==> osay...
import java.io .IOException;
import java.util.Scanner;
import java.net.URL;
public class Book
{
  private static final String punctuation = ".!?,\':;\"";

  public static String pigLatin(String word)
  {
     String currentWord = "";
     int p = 0;
     boolean active = false;
     int wordCount = 0;

      for (int i = 0; i < word.length(); i++) {
        if ( i < 30000){
          if(word.substring(i, i + 3).equals("***")){
            currentWord = "";
            active = true;
            System.out.println(word.substring(i, i + 3));
          }
        }

          if ( i > 100000){
            if(word.substring(i, i + 3).equals("***")){
              System.out.println(word.substring(i,i+3));
              System.out.println(wordCount + " (this is excluding : top and bottom legal stuff (ALICE))");
              return currentWord;
            }
          }
        
        if(active){
          if (word.charAt(i) == ' ' && word.charAt(i + 1) != ' '){
            String first = word.substring(p, p + 1);
            String last = word.substring(p + 1, i);
            String vowels = "aeiou";
            if(p == i - 1){
              currentWord += " " + word.charAt(i-1) + "yay"; // if single letter
            } else if (vowels.indexOf(first) >= 0){
              currentWord += word.substring(p,i) + "yay";
            }
            else if ((punctuation.indexOf(Character.toString(word.charAt(i-1))) > -1) || (punctuation.indexOf(Character.toString(word.charAt(p))) > -1)){
              if(punctuation.indexOf(Character.toString(word.charAt(p))) > -1){ //if caps in front
                currentWord += " " + word.charAt(p) + Character.toUpperCase(word.charAt(p+2)) + word.substring(p + 3, i ) + Character.toLowerCase(word.charAt( p+1 )) +  "ay";
              } else if(word.charAt(i - 3) == ' '){ //does not count for "oneword"
                currentWord += " " + word.charAt(i-2) + "yay" + word.charAt(i-1); //if single letter and punc
              } else { //just punc
                currentWord += " " + Character.toUpperCase(last.charAt(0)) + last.substring(1, last.length() - 1) + Character.toLowerCase(first.charAt(0)) + "ay" + word.charAt(i-1);
              }
            } 
            else if (Character.isUpperCase(first.charAt(0))) { //notcaps
              currentWord += " " + Character.toUpperCase(last.charAt(0)) + last.substring(1) + Character.toLowerCase(first.charAt(0)) + "ay"; 
            } else {
              currentWord += " " + word.substring(p + 1, i) + first + "ay";
            }
            wordCount ++; 
            p = i + 1;
          } else {
            continue;
          }
        }
        
    }
    String first = word.substring(p, p + 1);
    String last = word.substring(p + 1);
    if (Character.isUpperCase(first.charAt(0))) {
      currentWord += " " + Character.toUpperCase(last.charAt(0)) + last.substring(1, last.length() - 1) + Character.toLowerCase(first.charAt(0)) + "ay" + "."; 
    } else {
      currentWord += " " + word.substring(p + 1) + first + "ay";
    }
    System.out.println(wordCount);
    return currentWord;
  }
  
  
  private String bookText;
  
  public Book(String link){
      readBook(link);
  }
  private void readBook(String link){
   try {
    URL url = new URL(link);
    Scanner s = new Scanner(url.openStream());
    while(s.hasNext()){
      String text = s.nextLine();
      bookText += text;
    }
   }
   catch(IOException ex) {
      ex.printStackTrace();
   }
  }
  @Override
  public String toString(){
   return bookText;
  }
}  