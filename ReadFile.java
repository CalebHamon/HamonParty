import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

/**
The main method is contained in this class, as well as all of the arraylists
the main method utilizes all the other methods in the class that are responsible to do all the things that are apart of Party
including adding a guest, looking them up or looking up a table/company
These explanations will be more flushed out by each method
The arraylist comes from constructors from other classes and uses them to keep track of everyone.
TODO: Should be nothing else left to do in the code
*/

public class ReadFile {

  public static ArrayList<Company> comp = new ArrayList<Company>();
  public static ArrayList<Attendence> roster = new ArrayList<Attendence>();
  public static ArrayList<Table> table = new ArrayList<Table>();
  static int attNum = 90;
  static int maxNum = 100;
  static int maxTables = 10;
  static int maxComp = 15;

  public static void main(String[] args) {
    String can;
    Scanner scan = new Scanner(System.in);
    String yes = "yes";
    String no = "no";

    addRoster(); //Both are reading the files
    addComp();

    for (int i = 0; i < maxTables; i++) { //creating the ten tables
      table.add(new Table(i + 1));
    }

    for (Attendence person : roster) { //puting everyone from the add roster into the actual arraylist with everyone
      addPrev(person);
    }

    for (int attNum = 0; attNum < maxNum; attNum++) { //Adding guests y/n
      do {
        System.out.println("Would you like to add a guest? (yes or no)");
        can = scan.nextLine();
      } while (!(can.equalsIgnoreCase(yes) || can.equalsIgnoreCase(no)));
      if (can.equalsIgnoreCase(yes)) {
        addGuest();
        int helpful = roster.size() - 1;
        addPrev(roster.get(helpful));
      } else {
        break;
      }
    }

    for (int i = 0; i < maxTables; i++) {//searching by table y/n
      do {
        System.out.println("Would you like to see a table guest list? (yes or no)");
        can = scan.nextLine();
      } while (!(can.equalsIgnoreCase(yes) || can.equalsIgnoreCase(no)));
      if (can.equalsIgnoreCase(yes)) {
        tabGuestList();
      } else {
        break;
      }
    }

    for (int i = 0; i < maxComp; i++) {//searching by company y/n
      do {
        System.out.println("Would you like to see a company guest list? (yes or no)");
        can = scan.nextLine();
      } while (!(can.equalsIgnoreCase(yes) || can.equalsIgnoreCase(no)));
      if (can.equalsIgnoreCase(yes)) {
        compGuestList();
      } else {
        break;
      }
    }

    for (int i = 0; i < attNum; i++) {//searching for someone y/n
      do {
        System.out.println("Would you like to look someone up? (yes or no)");
        can = scan.nextLine();
      } while (!(can.equalsIgnoreCase(yes) || can.equalsIgnoreCase(no)));
      if (can.equalsIgnoreCase(yes)) {
        guestLookUp();
      } else {
        break;
      }
    }

  }

  //Read file for the txt of the partyguests
  //The method scans each line of the txt file, splits them into one line each
  //the line then is split into an array where it then goes to an array as one object
  public static void addRoster() {
    try {
      File myObj = new File("partyguests.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] set = new String[4];
        set = data.split(",");
        Attendence b1 = new Attendence(set[1], set[2], Integer.parseInt(set[3]));
        roster.add(b1);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }


  //Same thing as the other readfile, except it does the reading for companies rather than people
  public static void addComp() {
    try {
      File myObj = new File("companies.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        String[] set = new String[2];
        set = data.split(",");
        Company b1 = new Company(Integer.parseInt(set[0]), set[1]);
        comp.add(b1);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }


  //This method checks to see what tables are available for people to sit at
  //Returning which table number it can sit at or -1 if the table is not found
  public static int checkAva(int compNum) {
    int zero = 0;
    int ten = 10;

    for (int i = 0; i < ten; i++) {
      boolean ava = true; // Reset ava for each table

      if (table.get(i).limit() != zero) {
  
        int rosterSize = table.get(i).whoRoster().size(); //checking each table for ava
        if (rosterSize < ten) {
          for (int g = 0; g < rosterSize; g++) {
            if (table.get(i).whoRoster().get(g).compNum() == compNum) {
              ava = false;
              break; //break if found
            }
          }
          if (ava) {
            return i;
          }
        }

      } else if (table.get(i).limit() == zero) {
        return i;
      }
    }
    return -1; // No available table found
  }

  //This adds the people to the tables from ava
  //it passes in a person to then at them to whatever table they need to go to
  public static void addPrev(Attendence person) {
    int placehold;
    placehold = person.compNum();
    int p2 = checkAva(placehold); //returns table number
    if (p2 != -1) {
      table.get(p2).rostTabAdd(person); //adding the person to the table
      table.get(p2).occAdd(); //adding 1 to the max amount of people at the table
    } else {
      attNum--; //remove the guest if not ava
    }
  }

  //This method adds additional guests to the party
  //Goes through everything known about the person to add them to a correct table
  //Uses checkava and addprev to add them to a table
  public static void addGuest() {
    String can;
    Scanner scan = new Scanner(System.in);
    String[] set = new String[4];
    System.out.println("What is the last name of the guest you would like to add?");
    can = scan.nextLine();
    set[0] = can;
    System.out.println("What is the first name of the guest you would like to add?");
    can = scan.nextLine();
    set[1] = can;
    System.out
        .println("What is the company of the guest you would like to add? (type in the company exactly how it is!)");
    can = scan.nextLine();
    int compListSize = comp.size(); //all prev code is to add to an array what is known about the person
    for (int i = 0; i < compListSize; i++) {
      if (can.equalsIgnoreCase(comp.get(i).compName())) {
        int placeholder = comp.get(i).compNum();
        set[2] = Integer.toString(comp.get(i).compNum());
        int p2 = checkAva(placeholder); //converting their company to a number
        if (p2 != -1) {
          Attendence b1 = new Attendence(set[0], set[1], Integer.parseInt(set[2])); //adding the person/object to the arraylist
          roster.add(b1);
          attNum++;
        } else {
          System.out.println("Sorry, " + set[1] + " " + set[0] + " could not be added due to a limit");
        }
        break;
      }
    }
  }

  //The method prints out the table guest list
  //Using the table arraylist, it is able to print out where everyone is at and where they are seated
  public static void tabGuestList() {
    int bucket;
    do {
      System.out.println("Which table? (1-10)?");
      Scanner scan = new Scanner(System.in);
      bucket = scan.nextInt();
    } while (!((bucket > 0) && (bucket <= 10)));
    bucket = bucket - 1;
    int maxAttTab = table.get(bucket).whoRoster().size(); //arraylist then imbedded in another arraylist to get who is at the table
    for (int g = 0; g < maxAttTab; g++) {
      System.out.println(table.get(bucket).whoRoster().get(g).toString());
    }
  }

  //Same thing as the table guest list only now for companies
  //Though it is easier as it only needs to convert name to a number and print everyoe by their number
  public static void compGuestList() {
    Scanner scan = new Scanner(System.in);
    String sbucket;
    System.out.println("What Company would you like to search?");
    System.out.println("Please only respond with the following companies");
    System.out.println("Wal-Mart, Kroger, Amazon, Lowes, Best Western, KMart, Fusian, Heinz");
    System.out.println("Gucci, Prada, Nike, Dodge, Maserati, Razor, AMD");
    sbucket = scan.nextLine();
    for (int g = 0; g < maxComp; g++) {
      if (sbucket.equalsIgnoreCase(comp.get(g).compName())) { //checking if company real
        for (Attendence person : roster) {
          if (person.compNum() == comp.get(g).compNum()) { //finding person associated with company
            System.out.println(person.toString());
          }
        }
      }
    }
  }

  //Uses last name of person to find where they are seated
  //Then uses table arraylist to then find where they are by a sequential search
  public static void guestLookUp() {
    Scanner scan = new Scanner(System.in); //prompts
    String sbucket;
    System.out.println("Who would you like to look up? (Last Name Only Please)");
    System.out.println("(A typo or person who is not on the list will reprompt the question!)");
    sbucket = scan.nextLine();
    for (int i = 0; i < maxTables; i++) { //actual searching
      int tabRostSize = table.get(i).whoRoster().size();
      for (int g = 0; g < tabRostSize ; g++) {
        if(sbucket.equals(table.get(i).whoRoster().get(g).lname())){
          int tabNum = i +1;
          int seatNum = g + 1;
          System.out.println(sbucket + " sits at table " + tabNum + " in seat number " + seatNum);
          break; //break away from list if found
        }
      }
    }
  }

}
