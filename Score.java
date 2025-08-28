
public class Score {
// need to be different variables 

    private double points;
    private final int PUZZLE_VALUE = 10;
    private int rooms;
    private int puzzles;

    public Score(int startingScore) {
        this.points = 100;
        rooms = 0;
        puzzles = 0;
    }

    public void visitRoom() {
        rooms++;
    }

    public void solvePuzzle() {
        puzzles += PUZZLE_VALUE;
    }

    public double getScore() {
        points = points - rooms + puzzles;
        return points;
    }
}
