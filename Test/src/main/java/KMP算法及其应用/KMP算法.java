package KMP算法及其应用;

import java.util.Arrays;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/2 13:50
 * @Description TODO
 */
public class KMP算法 {
    public static void main(String[] args) {
        String match = "abced";
        int[] next = next(match.toCharArray());
        System.out.println(Arrays.toString(next));
        String str = "abcdeabcde";
        int kmp = kmp(str, match);
        System.out.println(kmp);
    }

    public static int kmp(String str, String match) {
        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();
        int[] next = next(matchChars);
        int i = 0, j = 0;
        while (i < strChars.length && j < matchChars.length) {
            if (strChars[i] == matchChars[j]) {
                i++;
                j++;
            } else if (next[j] != -1) {
                j = next[j];
            } else {
                i++;
            }
        }
        return j >= matchChars.length ? i - j : -1;
    }

    public static int[] next(char[] match) {
        int[] ans = new int[match.length];
        ans[0] = -1;
        ans[1] = 0;
        int next = 0;
        int i = 2;
        while (i < match.length) {
            if (match[next] == match[i - 1]) {  //匹配上
                ans[i] = next + 1;
                i++;
                next++; //为下次使用
            } else if (next != 0) { //未匹配上
                next = ans[next]; //跳next , i不动
            } else {  //未匹配上,next 为0
                ans[i] = 0;
                i++;
            }
        }
        return ans;
    }
}
