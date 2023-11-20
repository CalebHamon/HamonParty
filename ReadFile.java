import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class ReadFile {
  public ReadFile() {
    try {
      File myObj = new File("partyguests (1).txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] set = new String[4];
			set = data.split(",");
			//parse int
			//roster.add(Attendee)
		}    
		myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
