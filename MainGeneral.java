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

        for (int move : initialMoves) {
            problem.applyMove(move);
            ParallelBacktracking parallelBacktracking = new ParallelBacktracking(problem, foundSolution, solutions);
            forkJoinPool.execute(parallelBacktracking);
            problem.undoMove(move);
        }

        // Espera activa simple hasta que termine o encuentre solución
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        while (!solutions.isEmpty()) {
            System.out.println(solutions.poll());
        }
    }
}
