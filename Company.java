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
