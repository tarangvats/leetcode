public class main {
    public static void main(String[] args) {
        Solution solution = new Solution();
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
