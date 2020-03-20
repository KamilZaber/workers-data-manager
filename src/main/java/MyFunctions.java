import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyFunctions {
    public static <T> List<T> convertArrayToList(T array[])
    {
        List<T> list = new ArrayList<T>();
        for (T t : array) {
            list.add(t);
        }
        return list;
    }

    public static void displayList(List<Worker> list) {
        for(Worker w: list) {
            w.displayData();
        }
    }
    public static void displayMap(HashMap<String,Float> map) {
        for(String s: map.keySet()) {
            System.out.print(s + " = " + map.get(s) + "\n");
        }
    }

    public static List<Worker> readWorkersData(String fileName) {
        List<Worker> workersData = null;
        Gson gson = new Gson();
        try {
            workersData = convertArrayToList(gson.fromJson(new String(Files.readAllBytes(Paths.get(fileName))),Worker[].class));
        }catch(IOException e) {
            System.out.println("Problem with reading a file occured. Trying to create new datafile.");
            try{
                FileWriter writer = new FileWriter(fileName);
                if(writer != null) {
                    System.out.println("Created new datafile.");
                    writer.close();
                    System.exit(0);
                }
            }catch(IOException e2) {
                System.out.println("Problem with creating new datafile occured.");
                System.exit(0);
            }
        }
        return workersData;
    }

    public static void writeWorkersData(List<Worker> workersData) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = null;
        try {
            writer = new FileWriter("workers_data.json");
            writer.write(gson.toJson(workersData));
            writer.close();
        }catch(IOException e){
            System.out.println("Problem with creating the file occured.");
        }
    }
}
