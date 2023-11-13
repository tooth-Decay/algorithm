package Die;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Author: tooth-Decay
 * @Date: 2023/10/30 14:38
 * @Description TODO 求得单调栈子数组最小值乘子数组之和最大为多少 ,数组均为正整数
 * 所以以每个正整数为开头,找到最右边第一个比它小的数的下标,然乎利用前缀和 快速计算数组和 * 当前最小数即可
 * 解决办法就是单调栈 , 遍历每一个数,找到该数 左右范围内 不大于它的数,前缀和数组快速计算出 和,然后乘以该数
 * 因为 子数组之和 加上与该数做乘法,所以正整数比较合适  ,之和正,该数负   之和负,该数一定是最小的负数
 */
public class 单调栈子数组最小值乘子数组之和 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 1, 4, 3, 3, 1, 3, 1, 2, 3};
        System.out.println(Arrays.toString(prefixSum(arr)));
        System.out.println(Arrays.deepToString(monotonous(arr)));
        System.out.println(maxSumXcur(arr));
    }

    public static int maxSumXcur(int[] arr) {
        int[][] monotonous = monotonous(arr);
        int[] prefixSum = dp(arr);
        int max = Integer.MIN_VALUE;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            int l = monotonous[i][0] == -1 ? 0 : monotonous[i][0];//是-1,代表左边没有比自己更小的,意味着可以取到0 ,那为什么左边不用加1,因为前缀和压根没把它算在里面.
            int r = monotonous[i][1] == -1 ? length - 1 : monotonous[i][1] - 1; //右边减去1,因为改下标是第一个不符合的下标,前缀和不能算里边
            int sum = 0;  //因为都是正整数
            if (l == 0 && r == length - 1) sum = prefixSum[length - 1]; // 代表整个数组之和
            else sum = prefixSum[r] - prefixSum[l];
            max = Math.max(max, sum * arr[i]);
        }
        return max;
    }

    //如果数组内有重复值
    public static int[][] monotonous(int[] arr) {
        int[][] ans = new int[arr.length][2];
        LinkedList<ArrayList<Integer>> dq = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!dq.isEmpty() && arr[dq.peekLast().get(0)] > arr[i]) { //栈不为空,并且找到当前数比栈顶的数小,那么栈顶的数的右边最近的数的下标就是i
                for (Integer e : dq.removeLast()) {
                    ans[e][1] = i;
                    if (dq.isEmpty()) ans[e][0] = -1; //如果当前的list是最后一个,那么左边比自己小的就是-1,否则就是压在它下面的集合的最末尾的元素.
                    else ans[e][0] = dq.peekLast().get(dq.peekLast().size() - 1);
                }
            }
            if (!dq.isEmpty() && arr[i] == arr[dq.peekLast().get(0)]) dq.peekLast().add(i);
            else {
                ArrayList<Integer> newList = new ArrayList<>();
                newList.add(i);
                dq.add(newList);
            }
        }
        //for 循环结束 ,dq内的元素处理
        while (!dq.isEmpty()) {
            for (Integer e : dq.removeLast()) {
                ans[e][1] = -1; // 现在都是 从小到大排列的元素
                if (dq.isEmpty()) ans[e][0] = -1;
                else ans[e][0] = dq.peekLast().get(dq.peekLast().size() - 1);
            }
        }
        return ans;
    }

    public static int[] prefixSum(int arr[]) {  //前缀和数组
        int[] sum = new int[arr.length];
        process(arr, arr.length - 1, sum);
        return sum;
    }

    public static int process(int[] arr, int index, int[] sum) {
        if (index == 0) {
            sum[index] = arr[0];
            return sum[index];
        }
        sum[index] = arr[index] + process(arr, index - 1, sum);
        return sum[index];
    }

    public static int[] dp(int[] arr) { //前缀和数组
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = arr[i] + dp[i - 1];
        }
        return dp;
    }
}
