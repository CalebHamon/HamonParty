import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
Uses a constructor to add a roster
Rest of the methods return various types of parts from each object
Also uses readfile for companies to convert names to numbers and vice versa
/*

public class Attendence {
	
    public static ArrayList<Company> comp = new ArrayList<Company>();
	private String fName;
    private String lName;
    private int compf;

    public Attendence(String RLName, String RFName, int RComp)
    {
        addComp();
        lName = RLName;
        fName = RFName;
        compf = RComp;
    }

    public String toString()
    {
        return fName + " " + lName + " from " +  comp.get(compf -1).compName();
    }

    public int compNum(){
        return compf;
    }

    public String fname(){
        return fName;
    }

    public String lname(){
        return lName;
    }

    public static void addComp(){
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
    
}
