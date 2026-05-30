import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainGeneral {
    public static void main(String[] args) {
        // Ejecución 1: Backtracking General
        System.out.println("--- Ejecutando Backtracking General ---");
        BacktrackingProblem problem = new MyBacktrackingProblem(); 
        AtomicBoolean foundSolution = new AtomicBoolean(false);
        ConcurrentLinkedQueue<Object> solutions = new ConcurrentLinkedQueue<>();
        List<Integer> initialMoves = problem.getPossibleMoves();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<ParallelBacktracking> tasks = new ArrayList<>();

        for (int move : initialMoves) {
            problem.applyMove(move);
            ParallelBacktracking parallelBacktracking = new ParallelBacktracking(problem, foundSolution, solutions);
            tasks.add(parallelBacktracking);
            problem.undoMove(move);
        }
        forkJoinPool.invokeAll(tasks);

        while (!solutions.isEmpty()) {
            System.out.println(solutions.poll());
        }

        // Ejecución 2: Camino Más Corto
        System.out.println("\n--- Ejecutando Camino Mas Corto ---");
        ShortestPathProblem pathProblem = new MyShortestPathProblem(); 
        int initialBestPathLength = Integer.MAX_VALUE;
        ParallelShortestPath parallelShortestPath = new ParallelShortestPath(pathProblem, initialBestPathLength);
        List<Integer> shortestPath = forkJoinPool.invoke(parallelShortestPath);

        if (shortestPath != null) {
            System.out.println("Camino más corto: " + shortestPath);
            System.out.println("Longitud del camino: " + shortestPath.size());
        } else {
            System.out.println("No se encontró ningún camino.");
        }
    }
}
