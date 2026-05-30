import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ParallelShortestPath extends RecursiveTask<List<Integer>> {
    private ShortestPathProblem problem;
    private int bestPathLength;

    public ParallelShortestPath(ShortestPathProblem problem, int bestPathLength) {
        this.problem = problem;
        this.bestPathLength = bestPathLength;
    }

    @Override
    protected List<Integer> compute() {
        if (problem.isSolution()) {
            return problem.getCurrentPath();
        }

        List<Integer> shortestPath = null;
        List<Integer> moves = problem.getPossibleMoves();
        List<ParallelShortestPath> tasks = new ArrayList<>();

        for (int move : moves) {
            // Clonamos el problema aquí para aislar el hilo
            MyShortestPathProblem clonedProblem = new MyShortestPathProblem(problem.getCurrentPath());
            clonedProblem.applyMove(move);

            if (clonedProblem.getCurrentPathLength() < bestPathLength) {
                ParallelShortestPath child = new ParallelShortestPath(clonedProblem, bestPathLength);
                tasks.add(child);
                child.fork(); // Despega el hilo en paralelo de forma segura
            }
        }

        for (ParallelShortestPath task : tasks) {
            List<Integer> path = task.join();
            if (path != null && (shortestPath == null || path.size() < shortestPath.size())) {
                shortestPath = path;
                bestPathLength = path.size();
            }
        }

        return shortestPath;
    }
}
