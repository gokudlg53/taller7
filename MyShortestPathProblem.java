import java.util.ArrayList;
import java.util.List;

public class MyShortestPathProblem implements ShortestPathProblem {
    private final List<Integer> currentPath;
    private final int maxDepth = 4; // Controla la profundidad para que no explote

    // Constructor para el inicio
    public MyShortestPathProblem() {
        this.currentPath = new ArrayList<>();
    }

    // Constructor que usa el clonador (Para que cada hilo tenga su propia copia)
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
            moves.add(1);
            moves.add(2);
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
            sum += move;
        }
        return sum;
    }
}
