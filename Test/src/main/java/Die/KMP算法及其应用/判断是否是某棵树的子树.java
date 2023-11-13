package Die.KMP算法及其应用;

import java.util.*;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/2 19:09
 * @Description TODO
 */
public class 判断是否是某棵树的子树 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public static void main(String[] args) {
            TreeNode n1 = new TreeNode(4);
            TreeNode n2 = new TreeNode(5);
            TreeNode n3 = new TreeNode(1);
            TreeNode n4 = new TreeNode(2);
            n1.left = n2;
            TreeNode n5 = new TreeNode(4);
            TreeNode n6 = new TreeNode(5);
            TreeNode n7 = new TreeNode(2);
            n5.right = n6;
        }
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        //序列化两棵树,得到整型数组,每个元素类似kmp字符串中的字符
        // kmp
        ArrayList<Integer> rootList = getSubRootList(root);
        ArrayList<Integer> subRootList = getSubRootList(subRoot);
        if (subRootList.size() > rootList.size()) return false;
        else return kmp(rootList, subRootList);
    }

    public static ArrayList<Integer> getSubRootList(TreeNode head) {
        ArrayList<Integer> subRootList = new ArrayList<>();
        preTreeSerialization(head, subRootList);
        return subRootList;
    }

    public static void preTreeSerialization(TreeNode head, ArrayList<Integer> subRootList) { //树的先序序列化
        if (head == null) subRootList.add(null);
        else {
            subRootList.add(head.val);
            preTreeSerialization(head.left, subRootList);
            preTreeSerialization(head.right, subRootList);
        }
    }

    public static boolean kmp(ArrayList<Integer> root, ArrayList<Integer> subRoot) {
        int i = 0, j = 0;
        int[] next = getNext(subRoot);
        while (i < root.size() && j < subRoot.size()) {
            if (Objects.equals(root.get(i), subRoot.get(j))) {
                i++;
                j++;
            } else if (next[j] != -1) { //注意,这里不是0 ,而是-1,因为只有-1代表到达sub的头,是0的话有可能还是可以往前跳的
                j = next[j];
            } else {
                i++;
            }
        }
        return j >= subRoot.size();
    }

    public static int[] getNext(ArrayList<Integer> subList) { //子树的集合
        int[] ans = new int[subList.size()];
        ans[0] = -1;
        ans[1] = 0;
        int i = 2;
        int prefix = 0;
        while (i < ans.length) {
            if (Objects.equals(subList.get(i - 1), subList.get(prefix))) { // 当前匹配成功
                ans[i] = prefix + 1; //前缀既表示比较的下标对应的元素,同时也表示前缀的大小
                i++;
                prefix++;
            } else if (prefix != 0) { //匹配失败但是前缀还可以尝试加速
                prefix = ans[prefix]; //前缀下标的回退
            } else {
                ans[i] = 0; //biewangle1
                i++;
            }
        }
        return ans;
    }
}




