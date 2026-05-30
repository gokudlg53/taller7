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
            // Ofrecemos opciones diferentes en cada paso del camino
            moves.add(5);
            moves.add(3);
            moves.add(8);
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
        // Simulamos penalizaciones: en el segundo paso (índice 1), el movimiento 3 es carísimo
        for (int i = 0; i < currentPath.size(); i++) {
            int move = currentPath.get(i);
            if (i == 1 && move == 3) {
                sum += 50; // Penalización enorme por elegir el 3 aquí
            } else {
                sum += move;
            }
        }
        return sum;
    }
}
