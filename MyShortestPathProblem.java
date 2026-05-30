import java.util.ArrayList;
import java.util.List;

public class MyShortestPathProblem implements ShortestPathProblem {
    private List<Integer> currentPath = new ArrayList<>();
    private int currentLength = 0;
    private final int maxDepth = 12; 

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
