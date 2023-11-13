package Die.树;

import java.util.ArrayList;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/5 9:40
 * @Description TODO
 */
public class morris序列实现树的先中后序遍历 {
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

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n1.left = n2;
        n1.right = n3;
        ArrayList<Integer> list = new ArrayList<>();
        post(n1, list);
        System.out.println(list);
    }

    public static void morris(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode morris = node;
        TreeNode lMostR = null;
        while (morris != null) {
            if (morris.left != null) {  //有左树,找到左树最右侧节点
                lMostR = morris.left;
                while (lMostR.right != null && lMostR.right != morris) { //当前右树不为null , 说明有右节点 -->可能是普通右节点(这里必定要的是普通右节点),也有可能向上到了morris节点
                    lMostR = lMostR.right;
                }
                if (lMostR.right == null) { //第一次来到morris节点 左树 的 最右节点 , 将该节点的右节点由null置为morris节点,然后morris节点->morris.left , 结束
                    lMostR.right = morris;
                    morris = morris.left;
                    continue;
                } else {  // 说明是第二次来到 morris节点的左树的最右节点 , 将该节点的右树从指向morris节点 还原成 null ,然后morris向右走
                    lMostR.right = null; //然后morris向右走和最后一句合并,共同实现效果了
                }
            }
            morris = morris.right; //不满足上面的if , 说明morris节点无左树 , morris节点往右走
        }
    }

    public static void pre(TreeNode node, ArrayList<Integer> list) {  //先序遍历 ,无左树直接打印 , 第一次第一次去往morris.left , 直接打印
        if (node == null) {
            return;
        }
        TreeNode morris = node;
        TreeNode lMostR = null;
        while (morris != null) {
            if (morris.left != null) {  //有左树,找到左树最右侧节点
                lMostR = morris.left;
                while (lMostR.right != null && lMostR.right != morris) {
                    lMostR = lMostR.right;
                }
                if (lMostR.right == null) { //第一次来到morris节点左树的最右节点
                    lMostR.right = morris;
                    list.add(morris.val);
                    morris = morris.left;
                    continue;
                } else { //第二次
                    lMostR.right = null;
                }
            } else list.add(morris.val);
            morris = morris.right;
        }
    }

    public static void mid(TreeNode node, ArrayList<Integer> list) {  //-->总结,往右移动之前打印,因为左中右
        if (node == null) {
            return;
        }
        TreeNode morris = node;
        TreeNode lMostR = null;
        while (morris != null) {
            if (morris.left != null) {  //有左树,找到左树最右侧节点
                lMostR = morris.left;
                while (lMostR.right != null && lMostR.right != morris) {
                    lMostR = lMostR.right;
                }
                if (lMostR.right == null) { //第一次来到morris节点左树的最右节点
                    lMostR.right = morris;
                    morris = morris.left;
                    continue;
                } else {
                    lMostR.right = null;
                }
            }
            list.add(morris.val);
            morris = morris.right;
        }
    }

    public static void post(TreeNode node, ArrayList<Integer> list) {
        if (node == null) return;
        // morris 来到空,循环结束
        TreeNode morris = node;
        TreeNode lr = null;
        while (morris != null) {
            if (morris.left != null) {
                lr = morris.left;
                while (lr.right != null && lr.right != morris) lr = lr.right;
                if (lr.right == null) {
                    lr.right = morris;
                    morris = morris.left;
                    continue;
                } else { // lr.right = morris
                    lr.right = null;
                    f(morris.left, list);
                }
            }
            morris = morris.right;
        }
        f(node, list);
    }

    public static void f(TreeNode node, ArrayList<Integer> list) {
        TreeNode t = reverse(node);
        TreeNode t2 = t;
        while (t != null) {
            list.add(t.val);
            t = t.right;
        }
        reverse(t2);
    }

    public static TreeNode reverse(TreeNode node) {
        TreeNode cur = node;
        TreeNode pre = node;
        cur = cur.right;
        pre.right = null;
        while (cur != null) {
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
