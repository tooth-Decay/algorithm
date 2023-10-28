import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: tooth-Decay
 * @Date: 2023/10/27 16:51
 * @Description TODO 滑动窗口最大值
 * 注意队列中的 内容是下标 ,但是比较的时候是nums[i] 和 nums[队列中存储的下标] 比较
 */
public class 滑动窗口最大值 {
    public static void main(String[] args) {
        int[] demo = {7, 2, 4};
        int k = 2;
        System.out.println(Arrays.toString(maxSlidingWindow(demo, k)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k <= 0 || nums.length == 0 || nums == null) return null;
        if (nums.length == 1) return nums;
        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> dq = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) { //如果新进来的下标对应的值>=队列中的较新的下标对应的值,新进来的下标会依次替换掉队列中的下标
                dq.removeLast();
            }
            dq.addLast(i);
            //判断下标是否不在窗口内   下标 <=  队列中最新的下标 - k  代表下标过期
            if (dq.peekFirst() <= dq.peekLast() - k) dq.removeFirst();
            //如果窗口内中的下标满k大小,那就将该队列中的下标对应的值加入ans,队列中的元素个数和窗口k的大小没有关系
            if (i - k + 1 >= 0) ans[i - k + 1] = nums[dq.peekFirst()]; //注意ans内容是下标对应的值
        }
        return ans;
    }
}
