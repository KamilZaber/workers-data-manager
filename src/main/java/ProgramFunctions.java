import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ProgramFunctions {
    public static int mainMenu() {
        Scanner scan = new Scanner(System.in);
        int menuChoice;

        System.out.println("Workers Database Manager 1.0\n\nMenu:\n1. Load database. \n2. Create new database.\n3. Exit the program");

        try{
            menuChoice = scan.nextInt();
        }catch(InputMismatchException e) {
            return 0;
        }

        return menuChoice;
    }

    public static String askForFileName() {
        Scanner scan = new Scanner(System.in);
        String fileName;

        System.out.println("Type filename: ");
        fileName = scan.nextLine();

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

    public static void displayMap(HashMap<String,Float> map) {
        for(String s: map.keySet()) {
            System.out.print(s + " = " + map.get(s) + "\n");
        }
    }

    public static WorkersDatabase readWorkersData(String fileName) {
        WorkersDatabase database;
        Gson gson = new Gson();
        try {
            database = new WorkersDatabase(fileName, convertArrayToList(gson.fromJson(new String(Files.readAllBytes(Paths.get(fileName))),Worker[].class)));
        }catch(IOException e) {
            System.out.println("Problem with reading the file occured.");
            database = null;
        }
        return database;
    }

    public static void writeWorkersData(String fileName, WorkersDatabase database) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer;
        try {
            writer = new FileWriter(fileName);
            writer.write(gson.toJson(database.getData()));
            writer.close();
        }catch(IOException e){
            System.out.println("Problem with creating the file occured. Exiting the program.");
            System.exit(0);
        }
    }
}
