package microsoft.p201604.p2;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 路由表
 * <p>
 * Created by yfwz100 on 16/4/6.
 */
public class Main {

    private static class IPRule {
        boolean allowed;
        String prefix;

        IPRule(boolean allowed, String prefix) {
            this.allowed = allowed;
            this.prefix = prefix;
        }

        static IPRule parseLine(String line) {
            String[] tokens = line.split("[\\s\\/]");
            boolean allowed = false;
            if (tokens[0].equals("allow")) {
                allowed = true;
            }
            String prefix = ipBin(tokens[1]);
            if (tokens.length > 2) {
                prefix = prefix.substring(0, Integer.parseInt(tokens[2]));
            }
            return new IPRule(allowed, prefix);
        }

        public boolean match(String ip) {
            return ip.startsWith(prefix);
        }

        @Override
        public String toString() {
            return (allowed ? "allow" : "deny") + " " + prefix;
        }
    }

    private static String ipBin(String ip) {
        String prefix = "";
        String[] parts = ip.split("\\.");
        for (String part : parts) {
            String bin = Integer.toBinaryString(Integer.parseInt(part));
//            System.out.println(bin);
            if (bin.length() < 8) {
                int size = 8 - bin.length();
                for (int i = 0; i < size; i++) {
                    bin = "0" + bin;
                }
            }
//                System.out.println(bin + " " + bin.length());
            prefix += bin;
        }
        return prefix;
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            sc.nextLine();
//            sc.nextLine();
            List<IPRule> rules = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                rules.add(IPRule.parseLine(sc.nextLine()));
//                System.out.println(rules.get(rules.size() - 1).prefix);
            }
            ip:
            for (int i = 0; i < M; i++) {
                String ip = ipBin(sc.nextLine());
                for (IPRule rule : rules) {
                    if (rule.match(ip)) {
                        System.out.println(rule.allowed ? "YES" : "NO");
//                        System.out.println(rule.prefix);
//                        System.out.println(ip);
//                        System.out.println(ip.startsWith(rule.prefix));
                        continue ip;
                    }
                }
                System.out.println("YES");
            }

        }
    }
}
