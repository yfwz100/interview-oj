package huawei.oj.p4270;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 从前缀和中序遍历恢复二叉树。
 *
 * Created by yfwz100 on 16/5/17.
 */
public class Main {

    private static class TreeFormatException extends Exception {
    }

    private static class TreeNode {
        TreeNode left, right;
        char value;

        TreeNode(char value) {
            this.value = value;
        }

        static TreeNode buildFromPreMid(List<Character> pre, List<Character> mid) throws TreeFormatException {
            if (pre == null || pre.isEmpty() || mid == null || mid.isEmpty()) {
                return null;
            }
            try {
                int idx = mid.indexOf(pre.get(0));
                TreeNode head = new TreeNode(pre.get(0));
                head.left = buildFromPreMid(pre.subList(1, idx + 1), mid.subList(0, idx));
                head.right = buildFromPreMid(pre.subList(idx + 1, pre.size()), mid.subList(idx + 1, mid.size()));
                return head;
            } catch (IllegalArgumentException e) {
                throw new TreeFormatException();
            }
        }

        String postFormat() {
            StringBuilder sb = new StringBuilder();
            if (left != null) {
                sb.append(left.postFormat());
            }
            if (right != null) {
                sb.append(right.postFormat());
            }
            sb.append(value);
            return sb.toString();
        }
    }

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String preStr = sc.next(), midStr = sc.next();
            List<Character> pre = new ArrayList<>(), mid = new ArrayList<>();
            for (int i = 0; i < preStr.length(); i++) {
                pre.add(preStr.charAt(i));
            }
            for (int i = 0; i < midStr.length(); i++) {
                mid.add(midStr.charAt(i));
            }
            try {
                TreeNode ret = TreeNode.buildFromPreMid(pre, mid);
                if (ret == null) {
                    System.out.println("No");
                } else {
                    System.out.println(ret.postFormat());
                }
            } catch (TreeFormatException e) {
                System.out.println("No");
            }
        }
    }
}
