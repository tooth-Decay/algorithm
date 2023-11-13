package fresh;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/12 19:13
 * @Description TODO
 */
public class 二叉树最大路径和 {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(-2);

        t1.left = new TreeNode(1);
        System.out.println(maxPathSum(t1));


    }

    public static class Info {
        int treeMax;
        int subTreeMax;

        public Info(int treeMax, int subTreeMax) {
            this.treeMax = treeMax;
            this.subTreeMax = subTreeMax;
        }
    }

    public static int maxPathSum(TreeNode root) {
        return r(root).treeMax;
    }

    public static Info r(TreeNode node) {

        if (node == null) return null;
        Info lInfo = r(node.left);
        Info rInfo = r(node.right);

        int p1, p2, p3, p4, p5, p6;
        p1 = p2 = p3 = p4 = p6 = Integer.MIN_VALUE;
        if (lInfo != null) {
            p1 = lInfo.treeMax;
            p2 = lInfo.subTreeMax + node.val;
        }
        if (rInfo != null) {
            p3 = rInfo.treeMax;
            p4 = rInfo.subTreeMax + node.val;
        }
        p5 = node.val;
        if (lInfo != null && rInfo != null) {
            p6 = node.val + lInfo.subTreeMax + rInfo.subTreeMax;
        }
        int treeMax, subTreeMax;
        treeMax = Math.max(Math.max(p1, Math.max(p2, p3)), Math.max(p4, Math.max(p5, p6)));
        subTreeMax = Math.max(p5, Math.max(p2, p4));
        return new Info(treeMax, subTreeMax);

    }
}

class TreeNode {
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
