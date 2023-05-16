import java.util.LinkedList;
import java.util.Queue;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runners.JUnit4;

public class Chess {
    private static final int SIZE = 8;

    private static final int[][] MOVES = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
    };

    public static int knight(String start, String end) {
        int startX = start.charAt(0) - 'a';
        int startY = SIZE - (start.charAt(1) - '0');
        int endX = end.charAt(0) - 'a';
        int endY = SIZE - (end.charAt(1) - '0');

        int[][] board = new int[SIZE][SIZE];
        board[startY][startX] = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] currPos = queue.poll();
            int currX = currPos[0];
            int currY = currPos[1];

            if (currX == endX && currY == endY) {
                return board[currY][currX] - 1;
            }

            for (int[] move : MOVES) {
                int nextX = currX + move[0];
                int nextY = currY + move[1];

                if (isValidMove(nextX, nextY) && board[nextY][nextX] == 0) {
                    board[nextY][nextX] = board[currY][currX] + 1;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }

        return -1; // Knight cannot reach the end position
    }

    private static boolean isValidMove(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }
}

public class SolutionTest {
    @Test
    public void sampleTests() {
        assertEquals("Test for (a1, c1)", 2, Chess.knight("a1", "c1"));
        assertEquals("Test for (a1, f1)", 3, Chess.knight("a1", "f1"));
        assertEquals("Test for (a1, f3)", 3, Chess.knight("a1", "f3"));
        assertEquals("Test for (a1, f4)", 4, Chess.knight("a1", "f4"));
        assertEquals("Test for (a1, f7)", 5, Chess.knight("a1", "f7"));
    }
}
