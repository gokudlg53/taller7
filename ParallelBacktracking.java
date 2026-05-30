import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.RecursiveAction;

public class ParallelBacktracking extends RecursiveAction {
    private BacktrackingProblem problem;
    private AtomicBoolean foundSolution;
    private ConcurrentLinkedQueue<Object> solutions;

    public ParallelBacktracking(BacktrackingProblem problem, AtomicBoolean foundSolution, ConcurrentLinkedQueue<Object> solutions) {
        this.problem = problem;
        this.foundSolution = foundSolution;
        this.solutions = solutions;
    }

    @Override
    protected void compute() {
        if (foundSolution.get()) {
            return;
        }
        if (problem.isSolution()) {
            solutions.add(problem.getSolution());
            foundSolution.set(true);
            return;
        }
        List<Integer> moves = problem.getPossibleMoves();
        List<ParallelBacktracking> tasks = new ArrayList<>();

        for (int move : moves) {
            if (foundSolution.get()) {
                return;
            }
            problem.applyMove(move);
            ParallelBacktracking child = new ParallelBacktracking(problem, foundSolution, solutions);
            tasks.add(child);
            problem.undoMove(move);
        }
        invokeAll(tasks);
    }
}
