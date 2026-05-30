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
        List<ParallelBacktracking> tasks = new ArrayList<>();

        // 1. INICIAR CRONÓMETRO EXACTO
        long startTime = System.nanoTime();

        // Creamos y disparamos las tareas en el pool común de hilos
        for (int move : initialMoves) {
            problem.applyMove(move);
            ParallelBacktracking parallelBacktracking = new ParallelBacktracking(problem, foundSolution, solutions);
            tasks.add(parallelBacktracking);
            parallelBacktracking.fork(); // Dispara el hilo de inmediato
            problem.undoMove(move);
        }

        // Obliga al hilo principal a esperar que CADA tarea termine de verdad
        for (ParallelBacktracking task : tasks) {
            task.join(); 
        }

        // 2. DETENER CRONÓMETRO INMEDIATAMENTE
        long endTime = System.nanoTime();

        // Mostrar soluciones encontradas si las hay
        while (!solutions.isEmpty()) {
            System.out.println(solutions.poll());
        }

        // 3. MOSTRAR EL TIEMPO REAL
        System.out.println("Tiempo de ejecucion: " + (endTime - startTime) / 1000000.0 + " ms");
    }
}
