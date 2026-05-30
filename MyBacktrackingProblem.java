import java.util.ArrayList;
import java.util.List;

public class MyBacktrackingProblem implements BacktrackingProblem {
    private final List<Integer> currentPath;
    private final int maxDepth = 4;

    public MyBacktrackingProblem() {
        this.currentPath = new ArrayList<>();
    }

    public MyBacktrackingProblem(List<Integer> path) {
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
            for (int i = 1; i <= 5; i++) { // Ajusta el rango según tu ejercicio original
                moves.add(i);
            }
        }
        return moves;
    }

    @Override
    public List<Integer> getCurrentPath() {
        return new ArrayList<>(currentPath);
    }
}
