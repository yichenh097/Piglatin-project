import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
class App {
  public static void main(String[] args) {
    Book Book = new Book("https://www.gutenberg.org/cache/epub/11/pg11.txt");
    try{
      File myFile = new File("book.txt");
      if (myFile.createNewFile()){
        System.out.println("created");
      } else {
        System.out.println("no");
      }
    } catch (IOException e){
      System.out.println("error");
      e.printStackTrace();
    }
    try {
      FileWriter write = new FileWriter("book.txt");
      write.write(Book.pigLatin(Book.toString()));
      write.close();
    } catch(IOException e){
      e.printStackTrace();
    }
    //System.out.println(aBook.pigLatin("I can now spreak in a Pig-Latin. I can translate words and sentences for the students at Foothill High School."));
  }
    
    //System.out.println(Book.pigLatin("Translation Test I. I! I \"Bam this should work\". Last! word"));
}

