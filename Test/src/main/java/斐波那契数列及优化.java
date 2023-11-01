/**
 * @Author: tooth-Decay
 * @Date: 2023/11/1 16:59
 * @Description TODO
 */
public class 斐波那契数列及优化 {
    public static void main(String[] args) {
        System.out.println(fb(40));
        System.out.println(fbDP(40));
        System.out.println(fbMatrix(40));

    }

    public static int fb(int n) {
        if (n == 1 || n == 2) return 1;
        else return fb(n - 1) + fb(n - 2);
    }

    public static int fbDP(int n) {
        int[] ans = new int[n + 1];
        ans[1] = 1;
        ans[2] = 1;
        for (int i = 3; i <= n; i++) {
            ans[i] = ans[i - 1] + ans[i - 2];
        }
        return ans[n];
    }

    public static int fbMatrix(int n) {
        int[][] base = new int[][]{{1, 1}}; //
        /*
         *   | 1  1 |
             | 1  0 |  的 n-2 次幂
        */
        int[][] matrix1110 = mm(n - 2);
        return mm2(base, matrix1110)[0][0];
    }

    //    | 1  1 |
    //    | 1  0 |  的 n-2 次幂 ,将n-2 转换成 二进制,1的位置就将连续自乘结果 相乘
    public static int[][] mm(int n_2) {  //
        int[][] matrix = new int[][]{{1, 1}, {1, 0}};
        int[][] ans = new int[][]{{1, 0}, {0, 1}};
        while (n_2 != 0) { //意思就是把二进制上是1的时候
            if ((n_2 & 1) != 0) ans = mm2(ans, matrix);
            matrix = mm2(matrix, matrix); //自己和自己相乘
            n_2 >>= 1;
        }
        return ans;
    }

    private static int[][] mm2(int[][] matrix, int[][] matrix2) {
        //得到答案的矩阵的大小是 第一个矩阵的行*第二个矩阵的列
        int[][] ans = new int[matrix.length][matrix2[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix2[0].length; col++) {
                for (int tmp = 0; tmp < matrix[0].length; tmp++) { //是矩阵1的列数或者矩阵2的行数
                    ans[row][col] += matrix[row][tmp] * matrix2[tmp][col]; //是若干行列乘积之和
                }
            }
        }
        return ans;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            System.out.print("| ");
            for (int col = 0; col < ints.length; col++) {
                if (col == ints.length - 1) System.out.print(ints[col] + " |");
                else System.out.print(ints[col] + " ");
            }
            System.out.println();
        }
    }
}
