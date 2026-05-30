import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class MainShortestPath {
    public static void main(String[] args) {
        System.out.println("--- Ejecutando Camino Mas Corto (Rango 1-15) ---");
        ShortestPathProblem pathProblem = new MyShortestPathProblem(); 
        int initialBestPathLength = Integer.MAX_VALUE;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        
        ParallelShortestPath parallelShortestPath = new ParallelShortestPath(pathProblem, initialBestPathLength);
        
        long startTime = System.nanoTime();
        List<Integer> shortestPath = forkJoinPool.invoke(parallelShortestPath);
        long endTime = System.nanoTime();

        if (shortestPath != null) {
            System.out.println("Camino mas corto (paso a paso): " + shortestPath);
            
            int costoTotal = 0;
            for(int move : shortestPath) {
                costoTotal += move;
            }
            System.out.println("Costo total calculado: " + costoTotal);
        } else {
            System.out.println("No se encontro ningun camino.");
        }
        
        System.out.println("Tiempo de ejecucion: " + (endTime - startTime) / 1000000.0 + " ms");
    }
}
