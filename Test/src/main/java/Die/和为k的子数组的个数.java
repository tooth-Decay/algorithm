package Die;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/7 15:08
 * @Description TODO
 */
public class 和为k的子数组的个数 { //递推
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 1};
        int k = 3;
        System.out.println(subarraySum(nums, k));
    }

    public int subarraySum2(int[] nums, int k) { //暴力解
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int subarraySum(int[] nums, int k) {
        int subarrayNums = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); //防止 sum - k = 0 时 出现问题, 尤其当第一个元素和k 相同
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                subarrayNums += map.get(sum - k);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, 1);
            } else {
                map.put(sum, map.get(sum) + 1);
            }
        }
        return subarrayNums;
    }
}
