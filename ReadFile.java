import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

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

    addRoster();
    addComp();

    for (int i = 0; i < maxTables; i++) {
      table.add(new Table(i + 1));
    }

    for (Attendence person : roster) {
      addPrev(person);
    }

    for (int attNum = 0; attNum < maxNum; attNum++) {
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

    for (int i = 0; i < maxTables; i++) {
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

    for (int i = 0; i < maxComp; i++) {
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

    for (int i = 0; i < attNum; i++) {
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

  public static int checkAva(int compNum) {
    int zero = 0;
    int ten = 10;

    for (int i = 0; i < ten; i++) {
      boolean ava = true; // Reset ava for each table

      if (table.get(i).limit() != zero) {

        int rosterSize = table.get(i).whoRoster().size();
        if (rosterSize < ten) {
          for (int g = 0; g < rosterSize; g++) {
            if (table.get(i).whoRoster().get(g).compNum() == compNum) {
              ava = false;
              break;
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

  public static void addPrev(Attendence person) {
    int placehold;
    placehold = person.compNum();
    int p2 = checkAva(placehold);
    if (p2 != -1) {
      table.get(p2).rostTabAdd(person);
      table.get(p2).occAdd();
    } else {
      attNum--;
    }
  }

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
    int compListSize = comp.size();
    for (int i = 0; i < compListSize; i++) {
      if (can.equalsIgnoreCase(comp.get(i).compName())) {
        int placeholder = comp.get(i).compNum();
        set[2] = Integer.toString(comp.get(i).compNum());
        int p2 = checkAva(placeholder);
        if (p2 != -1) {
          Attendence b1 = new Attendence(set[0], set[1], Integer.parseInt(set[2]));
          roster.add(b1);
          attNum++;
        } else {
          System.out.println("Sorry, " + set[1] + " " + set[0] + " could not be added due to a limit");
        }
        break;
      }
    }
  }

  public static void tabGuestList() {
    int bucket;
    do {
      System.out.println("Which table? (1-10)?");
      Scanner scan = new Scanner(System.in);
      bucket = scan.nextInt();
    } while (!((bucket > 0) && (bucket <= 10)));
    bucket = bucket - 1;
    int maxAttTab = table.get(bucket).whoRoster().size();
    for (int g = 0; g < maxAttTab; g++) {
      System.out.println(table.get(bucket).whoRoster().get(g).toString());
    }
  }

  public static void compGuestList() {
    Scanner scan = new Scanner(System.in);
    String sbucket;
    System.out.println("What Company would you like to search?");
    System.out.println("Please only respond with the following companies");
    System.out.println("Wal-Mart, Kroger, Amazon, Lowes, Best Western, KMart, Fusian, Heinz");
    System.out.println("Gucci, Prada, Nike, Dodge, Maserati, Razor, AMD");
    sbucket = scan.nextLine();
    for (int g = 0; g < maxComp; g++) {
      if (sbucket.equalsIgnoreCase(comp.get(g).compName())) {
        for (Attendence person : roster) {
          if (person.compNum() == comp.get(g).compNum()) {
            System.out.println(person.toString());
          }
        }
      }
    }
  }

  public static void guestLookUp() {
    Scanner scan = new Scanner(System.in);
    String sbucket;
    System.out.println("Who would you like to look up? (Last Name Only Please)");
    System.out.println("(A typo or person who is not on the list will reprompt the question!)");
    sbucket = scan.nextLine();
    for (int i = 0; i < maxTables; i++) {
      int tabRostSize = table.get(i).whoRoster().size();
      for (int g = 0; g < tabRostSize ; g++) {
        if(sbucket.equals(table.get(i).whoRoster().get(g).lname())){
          int tabNum = i +1;
          int seatNum = g + 1;
          System.out.println(sbucket + " sits at table " + tabNum + " in seat number " + seatNum);
          break;
        }
      }
    }
  }

}
