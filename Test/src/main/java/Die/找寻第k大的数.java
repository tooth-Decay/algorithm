package Die;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/3 12:00
 * @Description TODO
 */
public class 找寻第k大的数 {
    private static final Random random = new Random();

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] nums = null;
        if (nums != null && nums.length != 0) {
            for (var num : nums) {
                list.add(num);
            }
        }
        int k = 1;
        list.add(-3);
        System.out.println(process(list, list.size() - k, 0, list.size() - 1));
        list.add(-2);
        System.out.println(process(list, list.size() - k, 0, list.size() - 1));

        list.add(-4);
        System.out.println(process(list, list.size() - k, 0, list.size() - 1));

        list.add(0);
        System.out.println(process(list, list.size() - k, 0, list.size() - 1));

        list.add(4);
        System.out.println(process(list, list.size() - k, 0, list.size() - 1));


    }

    public static int process(ArrayList<Integer> list, int index, int l, int r) {
        if (l == r) return list.get(l); // 两侧我只走一侧,很不幸来到最后一次递归,那就是要找的
        //随机选择一个数划分左右边界,看index 是否命中
        int randomNum = list.get(random.nextInt(r - l + 1) + l);
        int[] boundary = patition(list, l, r, randomNum);
        if (index <= boundary[1] && index >= boundary[0]) return list.get(index);
        else if (index < boundary[0]) return process(list, index, l, boundary[0] - 1);
        else return process(list, index, boundary[1] + 1, r);
    }

    public static int[] patition(ArrayList<Integer> list, int l, int r, int num) { //将>num , =num,<num的分成三块
        int lBoundary = l - 1;
        int gBoundary = r + 1;
        int cur = l;
        while (cur < gBoundary) {  // 别写成左边界 < 右边界了 ,中间有数的前提下就是 死循环了
            Integer listGetCur = list.get(cur);
            if (listGetCur > num) swap(list, cur, --gBoundary);
            else if (listGetCur < num) swap(list, ++lBoundary, cur++);
            else cur++;
        }
        return new int[]{lBoundary + 1, gBoundary - 1};
    }

    public static void swap(ArrayList<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }


}
