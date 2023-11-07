package 树;

import com.sun.source.tree.Tree;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/5 14:31
 * @Description TODO
 */
public class morris序求树的最小深度 {
    public static void main(String[] args) {

        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n2.left = n1;
        n2.right = n3;
        n1.left = n4;
        n1.right = n5;
        n5.right = n7;

        System.out.println(morris(n2));

    }

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
    }

    public static int morris(TreeNode root) {
        if (root == null) return 0;
        TreeNode m = root;
        TreeNode lr = null;
        int curLevel = 0; //时刻记录当前节点的高度
        int lrNums = 0; // 当前节点 左树头节点 到 左树最右节点的 高度差
        int minHeight = Integer.MAX_VALUE;
        while (m != null) {
            if (m.left != null) {  // m有左树不是叶子节点
                lr = m.left;
                lrNums = 1;
                while (lr.right != m && lr.right != null) {
                    lr = lr.right;
                    lrNums++;
                }
                // 出来的lr 来到 m节点左树的最右节点
                if (lr.right == null) { //lr不一定是叶子
                    lr.right = m;
                    m = m.left;
                    curLevel++;
                    continue;
                } else {  // 第二次来到lr
                    lr.right = null;
                    if (lr.left == null) {
                        minHeight = Math.min(minHeight, curLevel);
                    }
                    curLevel -= lrNums;
                }
            } else {
                curLevel++;
            }
            m = m.right;
        }
        //单独考虑最右侧的节点高度
        int rNums = 1;
        TreeNode r = root;
        while (r.right != null) {
            r = r.right;
            rNums++;
        }
        if (r.left == null) minHeight = Math.min(minHeight, rNums);
        return minHeight;
    }
}
