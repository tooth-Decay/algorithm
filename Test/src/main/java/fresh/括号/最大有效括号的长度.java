package fresh.括号;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/12 12:01
 * @Description TODO
 */
public class 最大有效括号的长度 {
    public static void main(String[] args) {
        String s = "())";
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        char[] str = s.toCharArray();
        int[] t = new int[str.length];
        int maxLength = 0;
        int l = 0;
        for (int i = 1; i < str.length; i++) {
            if (str[i] == ')') {
                l = i - t[i - 1] - 1;
                if (l >= 0 && str[l] == '(') {
                    if (l > 0) t[i] = t[i - 1] + t[l - 1] + 2;
                    else t[i] = t[i - 1] + 2;
                }
            }
            maxLength = Math.max(maxLength, t[i]);
        }
        return maxLength;
    }
}
