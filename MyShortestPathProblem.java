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
            for (int i = 1; i <= 15; i++) {
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
            // Penalización: si es 1, 2 o 3, el costo es alto (100)
            // Si es mayor a 3, el costo es (16 - move), premiando los números más altos
            if (move <= 3) {
                sum += 100;
            } else {
                sum += (16 - move);
            }
        }
        return sum;
    }
}
