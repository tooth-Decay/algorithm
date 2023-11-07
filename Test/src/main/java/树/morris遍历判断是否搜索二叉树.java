package 树;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/5 12:27
 * @Description TODO
 */
public class morris遍历判断是否搜索二叉树 { //二叉搜索树的一个特征就是中序遍历 递增
    // 比递归中序好一点,但比纯递归差
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
        System.out.println(morries(n2));
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

    public static boolean morries(TreeNode node) {
        if (node == null) return true;
        TreeNode morris = node;
        TreeNode lr = null;
        Integer preVal = null;
        while (morris != null) {
            if (morris.left != null) {
                lr = morris.left;
                //找到morris左树的最右节点
                while (lr.right != null && lr.right != morris) {
                    lr = lr.right;
                }
                if (lr.right == null) {
                    lr.right = morris;
                    morris = morris.left;
                    continue;
                } else {  // morris 左树的最右节点的right = morris节点
                    lr.right = null;
                }
            }
            if (preVal != null && preVal > morris.val) return false;
            preVal = morris.val;
            //往右之前,先比较上次值是否 >当前值
            morris = morris.right;
        }
        return true;
    }
}
