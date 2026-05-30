import java.util.ArrayList;
import java.util.List;

public class MyShortestPathProblem implements ShortestPathProblem {
    private final List<Integer> currentPath;
    private final int maxDepth = 4;

    public MyShortestPathProblem() {
        this.currentPath = new ArrayList<>();
    }

    public MyShortestPathProblem(List<Integer> path) {
        this.currentPath = new ArrayList<>(path);
    }

    @Override
    public boolean isSolution() {
        return currentPath.size() == maxDepth;
    }

    @Override
    public void applyMove(int move) {
        currentPath.add(move);
    }

    @Override
    public void undoMove(int move) {
        if (!currentPath.isEmpty()) {
            currentPath.remove(currentPath.size() - 1);
        }
    }

    @Override
    public List<Integer> getPossibleMoves() {
        List<Integer> moves = new ArrayList<>();
        if (currentPath.size() < maxDepth) {
            // Permitimos del 1 al 10
            for (int i = 1; i <= 10; i++) {
                moves.add(i);
            }
        }
        return moves;
    }

    @Override
    public List<Integer> getCurrentPath() {
        return new ArrayList<>(currentPath);
    }

    @Override
    public int getCurrentPathLength() {
        int sum = 0;
        for (int move : currentPath) {
            // Regla: 1 vale 10, 10 vale 1
            sum += (11 - move);
        }
        return sum;
    }
}
