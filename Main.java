import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicBoolean;
public class Main {
    public static void main(String[] args) {
        BacktrackingProblem problem = new MyBacktrackingProblem(); // Reemplaza esto con la instancia de tu problema de backtracking
        AtomicBoolean foundSolution = new AtomicBoolean(false);
        ConcurrentLinkedQueue<Object> solutions = new ConcurrentLinkedQueue<>();
        List<Integer> initialMoves = problem.getPossibleMoves();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<ParallelBacktracking> tasks = new ArrayList<>();
        // Crea un task para cada movimiento inicial posible
        for (int move : initialMoves) {
        problem.applyMove(move);
        ParallelBacktracking parallelBacktracking = new ParallelBacktracking(problem, foundSolution, solutions);
        tasks.add(parallelBacktracking);
        problem.undoMove(move);
        }
        // Ejecuta los tasks en paralelo usando ForkJoinPool
        forkJoinPool.invokeAll(tasks);
        // Imprime las soluciones encontradas
        while (!solutions.isEmpty()) {
        System.out.println(solutions.poll());
        }
    }
}