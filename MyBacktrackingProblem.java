import java.util.ArrayList;
import java.util.List;

public class MyBacktrackingProblem implements BacktrackingProblem {
    private int step = 0;
    private final int targetSteps = 5;

    @Override
    public boolean isSolution() {
        return step == targetSteps;
    }

    @Override
    public void applyMove(int move) {
        step++;
    }

    @Override
    public void undoMove(int move) {
        step--;
    }

    @Override
    public List<Integer> getPossibleMoves() {
        List<Integer> moves = new ArrayList<>();
        if (step < targetSteps) {
            moves.add(1);
            moves.add(2);
            moves.add(3);
        }
        return moves;
    }

    @Override
    public Object getSolution() {
        return "Solucion General Encontrada a profundidad " + targetSteps;
    }
}
