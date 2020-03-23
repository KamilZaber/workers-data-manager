public class WorkersDataManager {
    public static void main(String args[]) {
        WorkersDatabase database = null;
        int menuChoice;

        do {
            menuChoice = ProgramFunctions.mainMenu();

            if (menuChoice == 1) {
                if((database = ProgramFunctions.readWorkersData(ProgramFunctions.askForFileName())) != null) {
                    //databaseManagementMenu();
                    database.displayData();
                } else {
                    menuChoice = 0;
                }
            } else if (menuChoice == 2) {
                database = new WorkersDatabase(ProgramFunctions.askForFileName());
                //databaseManagementMenu();
                database.displayData();
            } else if (menuChoice == 3) {
                System.exit(0);
            } else {
                System.out.println("Wrong choice. Type \"1\", \"2\" or \"3\".");
            }
        }while(menuChoice!=1 && menuChoice!=2 && menuChoice!=3);

        //database.displayData();
        //workersData.add(new Worker(7,"Kamil","Zaber","Programmist",0));
        //ProgramFunctions.writeWorkersData(database);
    }
}
