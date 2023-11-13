package fresh.括号;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/12 11:00
 * @Description TODO
 */
public class 括号匹配 {
    /**
     * 如果是左括号一律入栈,如果是右括号,如果栈顶是与之匹配的左括号,左括号出栈
     * 循环结束,检查是否留存未被匹配的左括号
     */
    public boolean isValid(String s) { // 用栈的方式
        LinkedList<Character> dq = new LinkedList<>();
        char[] str = s.toCharArray();
        for (var c : str) {
            if (c == '(' || c == '{' || c == '[') dq.addLast(c);
            else if (c == ']') {
                if (!dq.isEmpty() && dq.peekLast() == '[') dq.removeLast();
                else return false;
            } else if (c == '}') {
                if (!dq.isEmpty() && dq.peekLast() == '{') dq.removeLast();
                else return false;
            } else {
                if (!dq.isEmpty() && dq.peekLast() == '(') dq.removeLast();
                else return false;
            }
        }
        if (!dq.isEmpty()) return false;
        return true;
    }

    public boolean isValid2(String s) {  //失败,原因是加加减减操作未能考虑括号的先后性 ([)]
        char[] str = s.toCharArray();
        int xl = 0, xxl = 0, xxxl = 0;
        for (var c : str) {
            if (c == '(') xl++;
            else if (c == '{') xxl++;
            else if (c == '[') xxxl++;
            else if (c == ')') {
                xl--;
                if (xl < 0) return false;
            } else if (c == '}') {
                xxl--;
                if (xxl < 0) return false;
            } else {
                xxxl--;
                if (xxxl < 0) return false;
            }
        }
        return xl == 0 && xxl == 0 && xxxl == 0;
    }
}
