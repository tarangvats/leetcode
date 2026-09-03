public class MinMoves {
    public static void main(String[] args) {
        MinMovesSolution solution = new MinMovesSolution();
        String[] classroom = {
            "S..L",
            ".#..",
            "..L."
        };
        int maxEnergy = 5;
        int result = solution.minMoves(classroom, maxEnergy);
        System.out.println("Minimum moves: " + result);
    }
}
