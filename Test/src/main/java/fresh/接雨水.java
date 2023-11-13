package fresh;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/13 12:00
 * @Description TODO
 */
public class 接雨水 {
    public int trap(int[] height) {
        if (height == null || height.length < 3) return 0;
        int len = height.length;
        int rain = 0;
        int l = 1;
        int r = len - 2;
        int lMaxHeight = height[0];
        int rMaxHeight = height[len - 1];
        while (l <= r) {
            if (lMaxHeight < rMaxHeight) {//左边短板
                rain += Math.max(lMaxHeight - height[l], 0);
                lMaxHeight = Math.max(lMaxHeight, height[l++]);
            } else if (rMaxHeight < lMaxHeight) {
                rain += Math.max(rMaxHeight - height[r], 0);
                rMaxHeight = Math.max(rMaxHeight, height[r--]);
            } else {
                rain += Math.max(lMaxHeight - height[l], 0) + Math.max(rMaxHeight - height[r], 0);
                lMaxHeight = Math.max(lMaxHeight, height[l++]);
                rMaxHeight = Math.max(rMaxHeight, height[r--]);
            }
        }
        return rain;
    }
}
