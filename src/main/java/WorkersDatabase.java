import java.util.ArrayList;
import java.util.List;

public class WorkersDatabase {
    private String databaseName;
    private List<Worker> data;

    public WorkersDatabase(String name) {
        this.databaseName = name;
        this.data = new ArrayList<Worker>();
    }

    public WorkersDatabase(String name, List<Worker> data) {
        this.databaseName = name;
        this.data = data;
    }
    public String getDatabaseName() {
        return databaseName;
    }

    public List<Worker> getData() {
        return data;
    }

    public void displayData() {
        System.out.println("Database name: " + databaseName);
        for(Worker w: data) {
            w.displayData();
        }
    }
}
