import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author: tooth-Decay
 * @Date: 2023/10/29 13:34
 * @Description TODO  左右两侧离我最小的
 */
public class 单调栈找寻左右两侧小于我的最近索引 {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 2};
        int[] arr2 = new int[]{1, 2, 3, 1, 4, 3, 3, 1, 3, 1, 2, 31};
        System.out.println(Arrays.deepToString(monotonous(arr)));
        System.out.println(Arrays.deepToString(monotonous2(arr2)));
        System.out.println(Arrays.deepToString(monotonous2(arr)));

    }

    //数组无重复值
    public static int[][] monotonous(int[] arr) {
        if (arr == null | arr.length == 0) return null;
        int[][] ans = new int[arr.length][2];
        LinkedList<Integer> dq = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!dq.isEmpty() && arr[dq.peekLast()] > arr[i]) { //此时栈内有元素,并且栈顶元素 大于 当前元素 ,比较的是具体值,别把下标和具体值进行比较
                Integer out = dq.removeLast();
                ans[out][0] = dq.isEmpty() ? -1 : dq.peekLast();
                ans[out][1] = i;
            }
            dq.addLast(i);
        }
        while (!dq.isEmpty()) {   // 此时呆在栈里的右边就没有比自己还小的了
            Integer out = dq.removeLast();
            ans[out][1] = -1;
            ans[out][0] = dq.isEmpty() ? -1 : dq.peekLast();
        }
        return ans;
    }

    //数组中的元素可以重复 : 相邻重复 , 不相邻重复 ,就算是不相邻的重复值(数组上不相邻),最终也可能堆叠到相邻(栈上相邻)
    public static int[][] monotonous2(int[] arr) {
        if (arr == null | arr.length == 0) return null;
        int[][] ans = new int[arr.length][2];
        LinkedList<ArrayList<Integer>> dq = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!dq.isEmpty() && arr[dq.peekLast().get(0)] > arr[i]) {
                // >
                ArrayList<Integer> out = dq.removeLast();
                for (var index : out) {
                    if (dq.isEmpty()) {
                        ans[index][0] = -1;
                        ans[index][1] = i;
                    } else {
                        ArrayList<Integer> nowTop = dq.peekLast();
                        Integer last = nowTop.get(nowTop.size() - 1);
                        ans[index][0] = last;
                        ans[index][1] = i;
                    }
                }
            }
            if (!dq.isEmpty() && arr[dq.peekLast().get(0)] == arr[i]) { //相等的判断加在需要新建集合的前面.因为上面的那个while依旧会有干扰作用
                dq.peekLast().add(i);
                continue;
            }
            ArrayList<Integer> newList = new ArrayList<>();
            newList.add(i);
            dq.add(newList);
        }
        while (!dq.isEmpty()) {
            ArrayList<Integer> out = dq.removeLast();
            for (var index : out) {
                ans[index][1] = -1;
                if (dq.isEmpty()) ans[index][0] = -1;
                else {
                    ArrayList<Integer> nowTop = dq.peekLast();
                    Integer topIndex = nowTop.get(nowTop.size() - 1);
                    ans[index][0] = topIndex;
                }
            }
        }
        return ans;

    }
}
