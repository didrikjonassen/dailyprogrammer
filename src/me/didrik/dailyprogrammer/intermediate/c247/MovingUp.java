package me.didrik.dailyprogrammer.intermediate.c247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MovingUp {
    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Integer[] mn = Arrays.stream(stdin.readLine().split(", ")).map(Integer::parseInt).toArray(Integer[]::new);
        int m = mn[0], n = mn[1];
        Boolean[][] b = new Boolean[n][m];
        for (int i = 0; i < n; i++)
            b[i] = stdin.readLine().chars().mapToObj(c -> c == 'X').toArray(Boolean[]::new);

        long total = 1;
        int rightmost = 0;
        int lastx = 0, lasty = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0; k < m; k++) {
                if (!b[i][k]) continue;
                if (k < rightmost) { System.out.println(-1); System.exit(0); }
                rightmost = k;
                total *= calculatePaths(lastx, lasty, k, i);
                lasty = i;
                lastx = k;
            }
        }
        System.out.println(total);
    }

    // TODO: 14.01.2016 https://en.wikipedia.org/wiki/Delannoy_number
    private static long calculatePaths(int lastx, int lasty, int x, int y) {
        int dx = x - lastx, dy = lasty - y;
        if (dx == 0 || dy == 0) return 1;
        long[][] grid = new long[dy + 1][dx + 1];
        for (int i = 0; i <= dx; i++) grid[0][i] = 1;
        for (int i = 0; i <= dy; i++) grid[i][0] = 1;
        for (int i = 1; i <= dy; i++) {
            for (int k = 1; k <= dx; k++) {
                grid[i][k] = grid[i - 1][k - 1] + grid[i - 1][k] + grid[i][k - 1];
            }
        }
        return grid[dy][dx];
    }

}
