import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ProgramFunctions {
    public static void displayMainMenu() {
        System.out.println("Workers Database Manager 1.0\n\nMenu:\n1. Load database. \n2. Create new database.\n3. Exit the program\n");
    }

    public static void displayDatabaseManagementMenu(String name) {
        System.out.println("Database: " + name + "\n\n1. Display database.\n2. Add worker.\n3. Remove worker.\n4. Save database.\n5. Exit database.\n");
    }

    public static void mainMenu() {
        WorkersDatabase database;
        Scanner scan = new Scanner(System.in);
        int menuChoice;
        String name;

        displayMainMenu();

        while(true){
            System.out.print("Choose function: ");

            try{
                menuChoice = scan.nextInt();
            }catch(InputMismatchException e) {
                menuChoice = 0;
                scan.nextLine();
            }

            if (menuChoice == 1) {
                while(true) {
                    name = askForFileName();
                    if(name != "fail") {
                        break;
                    }else {
                        System.out.println("Filename can't be empty and must end with '.json'.\n");
                    }
                }
                if ((database = ProgramFunctions.readWorkersData(name)) != null) {
                    break;
                } else {
                    displayMainMenu();
                    System.out.println("Problem with reading the file occured.\n");
                }
            } else if (menuChoice == 2) {
                while(true) {
                    name = askForFileName();
                    if(name != "fail") {
                        database = new WorkersDatabase(name);
                        break;
                    }else {
                        System.out.println("Filename can't be empty and must end with '.json'.\n");
                    }
                }
                break;
            } else if (menuChoice == 3) {
                System.exit(0);
            } else {
                displayMainMenu();
                System.out.println("Wrong choice. Type \"1\", \"2\" or \"3\".\n");
            }
        }

        databaseManagementMenu(database);
    }

    public static void databaseManagementMenu(WorkersDatabase database) {
        Scanner scan = new Scanner(System.in);
        int menuChoice;
        String tempString = "";
        float tempFloat = 0;
        int tempInt = 0;
        Worker newWorker;

        displayDatabaseManagementMenu(database.getDatabaseName());

        while(true){
            System.out.print("Choose function: ");

            try {
                menuChoice = scan.nextInt();
            }catch(InputMismatchException e) {
                menuChoice = 0;
                scan.nextLine();
            }

            if(menuChoice == 1) {
                displayDatabaseManagementMenu(database.getDatabaseName());
                database.displayData();
            }else if(menuChoice == 2) {
                newWorker = new Worker(database.getFirstAvailableID());
                scan.nextLine();

                while(true){
                    System.out.print("Type name: ");
                    tempString = scan.nextLine();
                    if(tempString.isEmpty() || tempString.charAt(0) == ' ') {
                        System.out.println("Field can't be empty or start with space.\n");
                    }else {
                        break;
                    }
                }
                newWorker.setName(tempString);

                while(true) {
                    System.out.print("Type surname: ");
                    tempString = scan.nextLine();
                    if(tempString.isEmpty() || tempString.charAt(0) == ' ') {
                        System.out.println("Field can't be empty or start with space.\n");
                    }else {
                        break;
                    }
                }
                newWorker.setSurname(tempString);

                while(true) {
                    System.out.print("Type job: ");
                    tempString = scan.nextLine();
                    if(tempString.isEmpty() || tempString.charAt(0) == ' ') {
                        System.out.println("Field can't be empty or start with space.\n");
                    }else {
                        break;
                    }
                }
                newWorker.setJob(tempString);

                while(true){
                    System.out.print("Type salary: ");
                    try{
                        tempFloat = scan.nextFloat();
                        if(tempFloat == 0) {
                            System.out.println("You can't type '0'.\n");
                        }else {
                            break;
                        }
                    }catch(InputMismatchException e) {
                        System.out.println("You must type correct number.\n");
                        scan.nextLine();
                    }
                }
                newWorker.setSalary(tempFloat);

                database.addWorker(newWorker);
                displayDatabaseManagementMenu(database.getDatabaseName());
                System.out.println("New worker added to database!\n");
            }else if(menuChoice == 3) {
                while(true) {
                    System.out.print("Type the ID of worker you want to remove: ");

                    try {
                        tempInt = scan.nextInt();
                        if(tempInt == 0) {
                            System.out.println("You can't type '0'.\n");
                        } else {
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("You must type correct  number.\n");
                        scan.nextLine();
                    }
                }

                displayDatabaseManagementMenu(database.getDatabaseName());
                if(database.removeWorker(tempInt)) {
                    System.out.println("Worker removed from database!\n");
                }else {
                    System.out.println("Worker with this ID does not exist.\n");
                }
            }else if(menuChoice == 4) {
                displayDatabaseManagementMenu(database.getDatabaseName());
                if(writeWorkersData(database.getDatabaseName(),database)) {
                    System.out.println("Database successfully saved!\n");
                }else {
                    System.out.println("Problem with saving the file occured.\n");
                }
            }else if(menuChoice == 5) {
                break;
            }else {
                displayDatabaseManagementMenu(database.getDatabaseName());
                System.out.println("Wrong choice. Type \"1\", \"2\", \"3\", \"4\" or \"5\".\n");
            }
        }
        mainMenu();
    }

    public static String askForFileName() {
        Scanner scan = new Scanner(System.in);
        String fileName;

        System.out.print("Type filename: ");
        fileName = scan.nextLine();
        if((fileName.isEmpty()) || (fileName.length() < 6) || !(fileName.substring(fileName.length() - 5).equals(".json"))) {
            fileName = "fail";
        }

        return fileName;
    }
    public static <T> List<T> convertArrayToList(T array[])
    {
        List<T> list = new ArrayList<T>();
        for (T t : array) {
            list.add(t);
        }
        return list;
    }

    /*public static void displayMap(HashMap<String,Float> map) {
        for(String s: map.keySet()) {
            System.out.print(s + " = " + map.get(s) + "\n");
        }
    }*/

    public static WorkersDatabase readWorkersData(String fileName) {
        WorkersDatabase database;
        Gson gson = new Gson();
        try {
            database = new WorkersDatabase(fileName, convertArrayToList(gson.fromJson(new String(Files.readAllBytes(Paths.get(fileName))),Worker[].class)));
        }catch(IOException e) {
            database = null;
        }
        return database;
    }

    public static boolean writeWorkersData(String fileName, WorkersDatabase database) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer;
        boolean success = true;
        try {
            writer = new FileWriter(fileName);
            writer.write(gson.toJson(database.getData()));
            writer.close();
        }catch(IOException e){
            success = false;
        }
        return success;
    }
}
