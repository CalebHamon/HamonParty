/**
Uses a constructor to add a companies
This converts numbers to company names and vice versa
*/

public class Company {
    private String compName;
    private int compNum;

    public Company(int RCompNum, String RCompName)
    {
        compNum = RCompNum;
        compName = RCompName;
    }

    public String compName(){
        return compName;
    }

    public int compNum(){
        return compNum;
    }
}
