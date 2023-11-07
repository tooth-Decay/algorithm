package KMP算法及其应用;

import java.util.Arrays;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/2 17:50
 * @Description TODO
 */
public class 判断是否是某个字符串的旋转 {
    public static void main(String[] args) {
        String s = "abcdeabcde";
//        int[] next = getNext(s.toCharArray());
//        System.out.println(Arrays.toString(next));
        String goal = "abced";

        System.out.println(kmp(s, goal));
    }

    public static boolean rotateString(String s, String goal) {
        String s1 = s + s;
        return kmp(s1, goal);
    }

    public static boolean kmp(String s, String goal) {
        char[] sCharArray = s.toCharArray();
        char[] goalCharArray = goal.toCharArray();
        int[] next = getNext(goalCharArray);
        int i = 0;
        int j = 0;
        while (i < sCharArray.length && j < goalCharArray.length) {
            if (sCharArray[i] == goalCharArray[j]) { //匹配上了,携手共进
                i++;
                j++;
            } else if (next[j] != -1) {  //当前字符没匹配上,goalCharArray 是否有更小的前缀可以跳转,
                j = next[j];
            } else { //没匹配上,并且没有更小的前缀可以跳了.
                i++;
            }
        }
        return j >= goalCharArray.length;
    }

    public static int[] getNext(char[] goalCharArray) {
        int[] next = new int[goalCharArray.length];
        next[0] = -1;
        next[1] = 0; // 因为前缀和后缀不能是同一个整体
        int prefix = 0; //第一次是 i=2 ,比较的是 i-1 和 0 ,所以第一次prefix 是0
        int i = 2;
        while (i < goalCharArray.length) {
            if (goalCharArray[i - 1] == goalCharArray[prefix]) {
                next[i] = prefix + 1;
                i++;
                prefix++;
            } else if (prefix != 0) { //说明还有更小的前缀可以作为可能性 ,=0说明无有效前缀
                prefix = next[prefix];
            } else {  //不匹配且 无最小前缀可以选择 ,
                next[i] = 0;
                i++;
            }
        }
        return next;
    }
}
