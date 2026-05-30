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

        // 1. INICIAR CRONÓMETRO
        long startTime = System.nanoTime();

        for (int move : initialMoves) {
            problem.applyMove(move);
            ParallelBacktracking parallelBacktracking = new ParallelBacktracking(problem, foundSolution, solutions);
            
            // Ejecuta de forma directa y segura en el pool de hilos
            forkJoinPool.execute(parallelBacktracking);
            
            problem.undoMove(move);
        }

        // Una pequeña espera para dar tiempo a que los hilos procesen antes de leer la cola
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 2. DETENER CRONÓMETRO
        long endTime = System.nanoTime();

        // Mostrar soluciones encontradas
        while (!solutions.isEmpty()) {
            System.out.println(solutions.poll());
        }

        // 3. MOSTRAR EL TIEMPO EN PANTALLA
        System.out.println("Tiempo de ejecucion: " + (endTime - startTime) / 1000000.0 + " ms");
    }
}
