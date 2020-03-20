import java.util.List;

public class WorkersDataManager {
    public static void main(String args[]) {
        List<Worker> workersData = MyFunctions.readWorkersData("workers_data.json");
        MyFunctions.displayList(workersData);
        //workersData.add(new Worker(7,"Kamil","Zaber","Programmist",0));
        //MyFunctions.writeWorkersData(workersData);
    }
}
