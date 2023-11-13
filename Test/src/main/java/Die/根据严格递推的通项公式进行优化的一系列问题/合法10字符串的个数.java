package Die.根据严格递推的通项公式进行优化的一系列问题;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/2 10:17
 * @Description TODO
 */
//规定字符串由0和1组成,每个0右边必须有1,返回n长度的合法字符串的所有可能性有几种
public class 合法10字符串的个数 {
    public static void main(String[] args) {
        //代码略,本质是斐波那契数列问题
        //位置f(n)字符1,依赖f(n-1)是1的情况 和 f(n-2) , f(n-2)的出现说明f(n-1)我是0字符,决定权是f(n-2)是1字符的可能性数
    }

}
