package microsoft.p201604.p2;


import java.util.Scanner;

/**
 * 题目: 路由表防火墙
 * <p>
 * Created by yfwz100 on 16/4/6.
 */
public class Main {

    interface IPRuleModel {
        /**
         * Add rule to the model.
         *
         * @param method a string of "allow" or "deny", else ignored.
         * @param ip the IP address.
         */
        void addRule(String method, String ip);

        /**
         * Check if the given IP is allowed.
         *
         * @param ip the IP to checked.
         * @return true if allowed.
         */
        boolean isAllowed(String ip);
    }

    private static class TrieIPRuleModel implements IPRuleModel {

        private static final int LEFT = 0, RIGHT = 1;

        private static class TrieNode {
            enum State {
                ALLOW, DENY, DEFAULT
            }

            State state = State.DEFAULT;
            TrieNode[] next = new TrieNode[2];

            TrieNode() {
            }
        }

        TrieNode root = new TrieNode();

        @Override
        public void addRule(String method, String ip) {
            boolean allowed = "allow".equals(method);
            String[] rulePart = ip.split("/");
            int mask = 32;
            if (rulePart.length > 1) {
                mask = Integer.parseInt(rulePart[1]);
                ip = rulePart[0];
            }
            int addr = 0;
            for (String part : ip.split("\\.")) {
                int p = Integer.parseInt(part);
                addr = (addr << 8) + p;
            }
            TrieNode current = root;
            for (int i = 0; i < mask; i++) {
                if (current.state != TrieNode.State.DEFAULT) {
                    return;
                }
                int t = (addr >> (31 - i)) & 1;
                if (current.next[t] == null) {
                    current.next[t] = new TrieNode();
                }
                current = current.next[t];
            }
            // 如果之前没有这项规则
            if (current.state == TrieNode.State.DEFAULT) {
                current.state = allowed ? TrieNode.State.ALLOW : TrieNode.State.DENY;
            }
        }

        @Override
        public boolean isAllowed(String ip) {
            boolean allowed = true;
            TrieNode current = root;
            int addr = 0;
            for (String part : ip.split("\\.")) {
                int p = Integer.parseInt(part);
                addr = (addr << 8) + p;
            }
            for (int i = 0; i < 32; i++) {
                if (current.state != TrieNode.State.DEFAULT) {
                    allowed = (current.state != TrieNode.State.DENY);
                }
                int t = (addr >> (31 - i)) & 1;
                if (current.next[t] == null) {
                    break;
                }
                current = current.next[t];
            }
            return allowed;
        }
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            IPRuleModel model = new TrieIPRuleModel();
            for (int i = 0; i < N; i++) {
                model.addRule(sc.next(), sc.next());
            }
            for (int i = 0; i < M; i++) {
                System.out.println(model.isAllowed(sc.next()) ? "YES" : "NO");
            }

        }
    }
}
