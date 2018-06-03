import java.util.*;

public class BFSRouteManagerImpl implements RouteManager {

    private Map<String, Set<String>> cities = new HashMap<>();

    public boolean connected(String city1, String city2) {
        return ! bfs(city1, city2).isEmpty();
    }

    public List<String> getRoute(String city1, String city2) {
        return bfs(city1, city2);
    }

    private List<String> bfs(String city1, String city2) {
        if (! cities.containsKey(city1) || ! cities.containsKey(city2)) {
            return Collections.EMPTY_LIST;
        } else if (city1.equals(city2)) {
            return Arrays.asList(city1);
        }

        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(city1));

        Set<String> visited = new HashSet<>();

        while (! queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastCity = path.get(path.size() - 1);

            if (! visited.contains(lastCity)) {
                for (String adj : cities.get(lastCity)) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(adj);
                    queue.add(newPath);

                    if (adj.equals(city2)) {
                        return newPath;
                    }
                }
                visited.add(lastCity);
            }
        }
        return Collections.EMPTY_LIST;
    }

    public void addConnection(String city1, String city2) {
        add(city1, city2);
        add(city2, city1);
    }

    private void add(String origin, String destination) {
        if (cities.containsKey(origin)) {
            Set<String> destinations = cities.get(origin);
            destinations.add(destination);
        } else {
            cities.put(origin, new HashSet<>(Arrays.asList(destination)));
        }
    }

}
