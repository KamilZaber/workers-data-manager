import java.util.Scanner;

public class WorkersDataManager {
    public static void main(String args[]) {
        WorkersDatabase database = null;
        Scanner scan;
        String fileName;
        int menuChoice;

        ProgramFunctions.displayMenu();
        scan = new Scanner(System.in);
        menuChoice = scan.nextInt();
        if(menuChoice == 1) {
            scan.nextLine();
            do {
                System.out.println("Type filename: ");
                fileName = scan.nextLine();
                database = ProgramFunctions.readWorkersData(fileName);
            }while(database == null);
        }else if(menuChoice == 2) {
            scan.nextLine();
            System.out.println("Type filename: ");
            fileName = scan.nextLine();
            database = new WorkersDatabase(fileName);
        }else {
            System.out.println("Wrong choice. Type \"1\" or \"2\".");
        }

        database.displayData();
        //workersData.add(new Worker(7,"Kamil","Zaber","Programmist",0));
        //ProgramFunctions.writeWorkersData(database);
    }
}
