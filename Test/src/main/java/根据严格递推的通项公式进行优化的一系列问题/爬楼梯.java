package 根据严格递推的通项公式进行优化的一系列问题;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/1 19:39
 * @Description TODO
 */
public class 爬楼梯 {
    public static void main(String[] args) {
        System.out.println(climbStairs(4));
        System.out.println(climbStairs3(4));
    }

    public static int climbStairs(int n) {
        if (n == 1 | n == 0) return 1;
        else return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static int climbStairs2(int n) {
        int[] ans = new int[n + 1];
        ans[0] = 1;
        ans[1] = 1;
        for (int i = 2; i <= n; i++) {
            ans[i] = ans[i - 1] + ans[i - 2];
        }
        return ans[n];
    }

    public static int climbStairs3(int n) {
        if (n == 1) return 1;
        int[][] base = new int[][]{{2, 1}};
        int[][] matrix = new int[][]{{1, 1}, {1, 0}};
        return mm(base, me(matrix, n))[0][0];
    }

    public static int[][] me(int[][] matrix, int e) {
        e = e - 2;
        int[][] ans = new int[][]{{1, 0}, {0, 1}}; //ans 一开始为单位矩阵
        while (e != 0) {
            if ((e & 1) != 0) ans = mm(ans, matrix);//当前二进制权位上为1,将对应的matrix的权位对应的十进制次方的矩阵 和 ans相乘
            matrix = mm(matrix, matrix);//
            e >>= 1; //e 能产生的二进制的长度 ? 实际长度去除无意义的0之后的长度,然后依次右移,什么时候全为无意义的0就结束.
        }
        return ans;
    }

    public static int[][] mm(int[][] matrix1, int[][] matrix2) {
        int[][] ans = new int[matrix1.length][matrix2[0].length]; // 相乘得到的矩阵规模是是矩阵1的行 * 矩阵2的列
        for (int row = 0; row < matrix1.length; row++) {
            for (int col = 0; col < matrix2[0].length; col++) {
                for (int x = 0; x < matrix1[0].length; x++) {
                    ans[row][col] += matrix1[row][x] * matrix2[x][col]; //x 在矩阵相乘的时候 分别在矩阵1充当列,在矩阵2充当行
                }
            }
        }
        return ans;
    }
}
