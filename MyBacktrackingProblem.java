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
    public int getCurrentPathLength() {
        int sum = 0;
        for (int move : currentPath) {
            // Ejemplo: Los números bajos (1, 2, 3) tienen un costo alto (ej. 100).
            // Los números altos (14, 15) tienen un costo bajo (ej. 1).
            if (move <= 3) {
                sum += 100; 
            } else {
                sum += (16 - move); // Los más altos son más baratos
            }
        }
        return sum;
    }

    @Override
    public Object getSolution() {
        return "Solucion General Encontrada a profundidad " + targetSteps;
    }
}
