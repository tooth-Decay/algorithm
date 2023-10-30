import java.util.LinkedList;

/**
 * @Author: tooth-Decay
 * @Date: 2023/10/29 8:33
 * @Description TODO  满足 子数组中的 最大值-最小值<=某个指定的数 的子数组个数
 */
//给定一个数组,返回满足 子数组中的 最大值-最小值<=某个指定的数 的子数组个数
// 内for循环和 l 其实不相关了 , r会来到第一个不满足的下标,此时 l - r 之间的所有数经历一次判断,l也会变成r,所以会有覆盖
public class 子数组的个数 {
    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 4, 5, 2, 5, 6, 7, 1};
        System.out.println(subArrays(arr, 2));
    }

    public static int subArrays(int[] arr, int number) {
        LinkedList<Integer> min = new LinkedList<>();
        LinkedList<Integer> max = new LinkedList<>();
        int l = 0, r = 0;
        int count = 0;
        for (; l < arr.length; l++) {
            for (; r < arr.length; ) {  //这里 r==l ?  不需要
                while (!min.isEmpty() && arr[r] <= arr[min.peekLast()]) {
                    min.removeLast();
                }
                min.addLast(r);
                while (!max.isEmpty() && arr[r] >= arr[max.peekLast()]) {
                    max.removeLast();
                }
                max.addLast(r);
                if (arr[max.peekFirst()] - arr[min.peekFirst()] > number) break;
                r++;
            }
            count += r - l;
            if (min.peekFirst() == l) min.removeFirst();
            if (max.peekFirst() == l) max.removeFirst();
        }
        return count;
    }

//    //暴力求解 写不出
//    public static int subArrays2(int[] arr, int number) {
//        int count = 0;
//        for (int l = 0; l < arr.length; l++) {
//            for (int r = l; r < arr.length; r++) {
//                int max = arr[r], min = arr[r];
//                for (int i = l; i <= r; i++) {
//                    if (arr[i] >= max) max = arr[i];
//                    if (min >= arr[i]) min = arr[i];
//                    if (max - min <= number) count++;
//                }
//            }
//        }
//        return count;
//    }
}
