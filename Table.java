import java.util.ArrayList;

public class Table {
	
	private int maxSize = 10;
    private static int occ;
    private int tabNum;
    private ArrayList<Attendence> rosterTab;

    public Table(int RTabNum){
        rosterTab = new ArrayList<Attendence>();
        occ = 0;
        tabNum = RTabNum;
        
    }

    public void rostTabAdd(Attendence person){
        rosterTab.add(person);
    }

    public void occAdd(){
        occ++;
    }

    public ArrayList<Attendence> whoRoster(){
        return rosterTab;
    }

    public int limit(){
        return occ;
    }

}
    
