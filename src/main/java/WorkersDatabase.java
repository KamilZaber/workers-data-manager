import java.util.ArrayList;
import java.util.List;

public class WorkersDatabase {
    private String databaseName;
    private List<Worker> data;
    private int firstAvailableID;

    public WorkersDatabase(String name) {
        this.databaseName = name;
        this.data = new ArrayList<Worker>();
        this.firstAvailableID = 1;
    }

    public WorkersDatabase(String name, List<Worker> data) {
        this.databaseName = name;
        this.data = data;
        this.firstAvailableID = data.get(data.size() - 1).getID() + 1;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public List<Worker> getData() {
        return data;
    }

    public int getFirstAvailableID() {
        return firstAvailableID;
    }

    public void displayData() {
        System.out.println("Database name: " + databaseName + "\n");
        if(data.size() == 0){
            System.out.println("There are no records in this database.");
        }else {
            for (Worker w : data) {
                w.displayData();
            }
        }
        System.out.println();
    }

    public void addWorker(Worker w){
        data.add(w);
        firstAvailableID++;
    }

    public boolean removeWorker(int ID) {
        boolean IDExists = false;
        int index = 0;

        for(Worker w: data) {
            if(w.getID() == ID) {
                IDExists = true;
                break;
            }
            index++;
        }

        if(IDExists) {
            data.remove(index);
            if(data.size() > 0) {
                firstAvailableID = data.get(data.size() - 1).getID() + 1;
            }else {
                firstAvailableID = 1;
            }
        }

        return IDExists;
    }
}
