package Die;

import java.util.HashMap;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/7 11:11
 * @Description TODO
 */
public class 和等于k的最长子数组长度 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 1, 2, 1};
        System.out.println(nums(arr, 3));
    }

    public static int nums(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // key 存储当前值 , value 存储最早出现位置
        map.put(0, -1); // -1 索引
        int len = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int wantNum = sum - k;
            if (map.containsKey(wantNum)) {
                len = Math.max(len, i - map.get(wantNum));
            }
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return len;
    }
}
