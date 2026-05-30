import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class MainShortestPath {
    public static void main(String[] args) {
        System.out.println("--- Ejecutando Camino Mas Corto ---");
        ShortestPathProblem pathProblem = new MyShortestPathProblem(); 
        int initialBestPathLength = Integer.MAX_VALUE;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        
        ParallelShortestPath parallelShortestPath = new ParallelShortestPath(pathProblem, initialBestPathLength);
        
        // 1. INICIAR CRONÓMETRO EXACTO
        long startTime = System.nanoTime();
        
        // invoke() bloquea el hilo principal de manera limpia hasta que todo el árbol paralelo termine
        List<Integer> shortestPath = forkJoinPool.invoke(parallelShortestPath);
        
        // 2. DETENER CRONÓMETRO INMEDIATAMENTE
        long endTime = System.nanoTime();

        if (shortestPath != null) {
            System.out.println("Camino mas corto: " + shortestPath);
            System.out.println("Longitud del camino: " + shortestPath.size());
        } else {
            System.out.println("No se encontro ningun camino.");
        }
        
        // 3. MOSTRAR EL TIEMPO REAL
        System.out.println("Tiempo de ejecucion: " + (endTime - startTime) / 1000000.0 + " ms");
    }
}
