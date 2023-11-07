import java.awt.event.MouseAdapter;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/7 11:11
 * @Description TODO
 */
public class 最大子数组长度等于sum {
    public static void main(String[] args) {
        int[] arr = {4, 1, 1, 0, 4, 2, 0, 0, 0, 0, 1, 2, 3, 0, 0, 0, 0, 0, 0};
        System.out.println(nums(arr, 6));
    }

    public static int nums(int[] arr, int num) {
        int l = 0, r = 0, sum = 0, max = 0;
        while (r < arr.length) {
            if ((sum + arr[r]) < num) {
                sum += arr[r];
                r++;
            } else if ((sum + arr[r]) == num) {
                max = Math.max(max, r - l + 1);
                sum += arr[r];
                r++;
            } else {
                sum -= arr[l];
                l++;

            }
        }
        return max;
    }
}
