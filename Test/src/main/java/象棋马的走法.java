/**
 * @Author: tooth-Decay
 * @Date: 2023/10/29 5:33
 * @Description TODO
 */
public class 象棋马的走法 {
    //中国象棋棋盘 9 * 10
    // x最大9   y最大10  出发默认坐标(0,0),问走到(x,y)的走法有几种
    public static void main(String[] args) {
        System.out.println(horse(2, 1, 2));
        System.out.println(horseDP(2, 1, 2));
    }

    public static int horse(int x, int y, int steps) {
        if (steps == 0) return x == 0 && y == 0 ? 1 : 0;
        if (x < 0 | x >= 9 | y < 0 | y >= 10) return 0;
        return horse(x + 1, y + 2, steps - 1)
                + horse(x + 2, y + 1, steps - 1)
                + horse(x + 2, y - 1, steps - 1)
                + horse(x + 1, y - 2, steps - 1)
                + horse(x - 1, y - 2, steps - 1)
                + horse(x - 2, y - 1, steps - 1)
                + horse(x - 2, y + 1, steps - 1)
                + horse(x - 1, y + 2, steps - 1);
    }

    /**
     * 建立象棋棋盘内所有可能性的一张表 9 * 10 * steps,第0层的内容已经初始化好,每一层的内容依赖上一层的内容. x1,y1,steps1均从1开始
     */
    public static int horseDP(int x, int y, int steps) {
        int[][][] dp = new int[9][10][steps + 1];
        //if (steps == 0) return x == 0 && y == 0 ? 1 : 0;
        dp[0][0][0] = 1;
        for (int steps1 = 1; steps1 <= steps; steps1++) {
            for (int x1 = 1; x1 < 9; x1++) {
                for (int y1 = 1; y1 < 10; y1++) {
                    dp[x1][y1][steps] = getWays(x1 + 1, y1 + 2, steps - 1, dp)
                            + getWays(x1 + 2, y1 + 1, steps - 1, dp)
                            + getWays(x1 + 2, y1 - 1, steps - 1, dp)
                            + getWays(x1 + 1, y1 - 2, steps - 1, dp)
                            + getWays(x1 - 1, y1 - 2, steps - 1, dp)
                            + getWays(x1 - 2, y1 - 1, steps - 1, dp)
                            + getWays(x1 - 2, y1 + 1, steps - 1, dp)
                            + getWays(x1 - 1, y1 + 2, steps - 1, dp);

                }
            }
        }
        return dp[x][y][steps];
    }

    public static int getWays(int x, int y, int steps, int[][][] dp) {
        //if (x < 0 | x >= 9 | y < 0 | y >= 10) return 0;
        if (x < 0 | x >= 9 | y < 0 | y >= 10) return 0;
        return dp[x][y][steps];
    }
}
