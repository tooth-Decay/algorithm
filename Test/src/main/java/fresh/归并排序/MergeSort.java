package fresh.归并排序;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/13 10:09
 * @Description TODO
 */
public class MergeSort {
    public static void re(int l, int r, int[] arr) {
        if (l == r) return;
        int mid = l + ((r - l) >> 1);
        re(l, mid, arr);
        re(mid + 1, r, arr);
        merge(l, mid, r, arr);
    }

    private static void merge(int l, int mid, int r, int[] arr) {
        int[] tmp = new int[r - l + 1];
        int pl = l, pr = mid + 1, i = 0;
        while (pl <= mid && pr <= r) {
            tmp[i++] = arr[pl] >= arr[pr] ? arr[pl++] : arr[pr++];
        }
        while (pl <= mid) tmp[i++] = arr[pl++];
        while (pr <= r) tmp[i++] = arr[pr++];
        for (int j = 0; j < tmp.length; j++) {
            arr[l + j] = tmp[j];
        }
    }
}
