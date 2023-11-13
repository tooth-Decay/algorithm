package fresh.括号;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/12 13:09
 * @Description TODO
 */
public class 有效括号的最大嵌套深度 {
    public int maxDepth(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int count = 0;
        int maxDepth = 0;
        for (var c : str) {
            if (c == '(') {
                count++;
                maxDepth = Math.max(maxDepth, count);
            } else if (c == ')') count--;
        }
        return maxDepth;
    }
}
