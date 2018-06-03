import java.io.BufferedReader;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static RouteManager routeManager;

    public static void main(String[] args) {
        routeManager = new FirstRouteManagerImpl();
        List<String[]> cities = readFile(args[0]);

        for (String[] city : cities) {
            routeManager.addConnection(city[0].trim(), city[1].trim());
        }

        System.out.println(routeManager.connected("New York", "D.C."));
        System.out.println(routeManager.connected("New York", "Cincinatti"));
        printRoute(routeManager.getRoute("New York", "New York"));
        printRoute(routeManager.getRoute("D.C.", "New York"));
        printRoute(routeManager.getRoute("New York", "Cincinatti"));
        System.out.println(routeManager.connected("Baltimore", "Boston"));
        printRoute(routeManager.getRoute("Baltimore", "Boston"));
    }

    static List<String[]> readFile(String fileName) {
        FileReader fileReader = new FileReader(fileName);
        return fileReader.cities;
    }

    static void printRoute(List<String> route) {
        if (route.isEmpty()) {
            System.out.println("No such route");
            return;
        }
        for (String city : route) {
            System.out.print(city + " -> ");
        }
        System.out.println();
    }

}

class FileReader {

    List<String[]> cities = new ArrayList<>();

    public FileReader(String filename) {
        URL url = getClass().getResource(filename);
        File file = new File(url.getPath());

        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                String[] line = st.split(",");
                cities.add(line);
            }
        } catch (Exception e) { }
    }

}