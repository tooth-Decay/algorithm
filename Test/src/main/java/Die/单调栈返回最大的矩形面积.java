package Die;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


/**
 * @Author: tooth-Decay
 * @Date: 2023/10/30 17:02
 * @Description TODO
 */
public class 单调栈返回最大的矩形面积 {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 2, 2, 3, 4, 5};
        System.out.println(Arrays.deepToString(monotonous(arr)));
        System.out.println(maxRecArea(arr));
    }

    public static int maxRecArea(int[] arr) {
        int[][] monotonous = monotonous(arr);
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int l = 0, r = 0, distance = 0; // 左右边界初始化为0
            l = monotonous[i][0] == -1 ? 0 : monotonous[i][0] + 1; //如果左边-1,代表往左可以走到尽头都不会出现值<当前下标对应的值的下标
            r = monotonous[i][1] == -1 ? arr.length - 1 : monotonous[i][1] - 1; // 同理 , 不是-1,则需要进行左边范围+1,右边范围-1,将范围都设置为闭区间
            distance = r - l + 1;
            maxArea = Math.max(maxArea, distance * arr[i]);
        }
        return maxArea;
    }

    public static int[][] monotonous(int[] arr) {
        int[][] ans = new int[arr.length][2];
        LinkedList<ArrayList<Integer>> dq = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!dq.isEmpty() && arr[dq.peekLast().get(0)] > arr[i]) { //栈不为空,并且当前栈顶的索引对应的值 > 当前索引对应的值
                for (Integer e : dq.removeLast()) {
                    ans[e][1] = i;
                    if (dq.isEmpty()) ans[e][0] = -1;
                    else ans[e][0] = dq.peekLast().get(dq.peekLast().size() - 1);
                }
            }
            if (!dq.isEmpty() && arr[dq.peekLast().get(0)] == arr[i]) { //如果栈不为空,并且栈顶的集合正好存储的是相同值的索引,则直接添加栈顶的集合内
                dq.peekLast().add(i);
            } else { //否则只能自己新建一个集合,存放当前下标
                ArrayList<Integer> newList = new ArrayList<>();
                newList.add(i);
                dq.add(newList);
            }
        }
        while (!dq.isEmpty()) { //此时栈内存在 小->大的一堆集合
            for (Integer e : dq.removeLast()) {
                ans[e][1] = -1; //相对右边没有比它小的,只有比它大的值或者说没有值
                if (dq.isEmpty()) ans[e][0] = -1;
                else {
                    ans[e][0] = dq.peekLast().get(dq.peekLast().size() - 1);
                }
            }
        }
        return ans;
    }
}
