import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainGeneral {
    public static void main(String[] args) {
        System.out.println("--- Ejecutando Backtracking General ---");
        BacktrackingProblem problem = new MyBacktrackingProblem(); 
        AtomicBoolean foundSolution = new AtomicBoolean(false);
        ConcurrentLinkedQueue<Object> solutions = new ConcurrentLinkedQueue<>();
        List<Integer> initialMoves = problem.getPossibleMoves();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<ParallelBacktracking> tasks = new ArrayList<>();

        // Iniciar cronómetro
        long startTime = System.nanoTime();

        for (int move : initialMoves) {
            problem.applyMove(move);
            ParallelBacktracking parallelBacktracking = new ParallelBacktracking(problem, foundSolution, solutions);
            tasks.add(parallelBacktracking);
            problem.undoMove(move);
        }
        forkJoinPool.invokeAll(tasks);

        // Detener cronómetro
        long endTime = System.nanoTime();

        while (!solutions.isEmpty()) {
            System.out.println(solutions.poll());
        }

        // Mostrar el tiempo total en milisegundos
        System.out.println("Tiempo de ejecucion: " + (endTime - startTime) / 1000000.0 + " ms");
    }
}
