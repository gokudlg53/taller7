import java.util.ArrayList;
import java.util.List;

public class MyShortestPathProblem implements ShortestPathProblem {
    private List<Integer> currentPath = new ArrayList<>();
    private int currentLength = 0;
    private final int maxDepth = 5; // Reducido para evitar recursión infinita

    @Override
    public boolean isSolution() {
        return currentPath.size() == maxDepth;
    }

    @Override
    public void applyMove(int move) {
        currentPath.add(move);
        currentLength += move;
    }

    @Override
    public void undoMove(int move) {
        if (!currentPath.isEmpty()) {
            currentLength -= move;
            currentPath.remove(currentPath.size() - 1);
        }
    }

    @Override
    public List<Integer> getPossibleMoves() {
        List<Integer> moves = new ArrayList<>();
        // Solo permite movimientos si no hemos alcanzado el límite
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
        return currentLength;
    }
}
