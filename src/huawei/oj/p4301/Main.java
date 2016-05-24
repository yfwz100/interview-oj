package huawei.oj.p4301;

/**
 * Created by yfwz100 on 16/5/23.
 */
public class Main {

    public static void checkIP(String inputStr, StringBuffer outputStr) {
        if (inputStr.matches("((0?[0-9]{1,2}|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}(0?[0-9]{1,2}|1[0-9]{2}|2[0-4][0-9]|25[0-5])")) {
            outputStr.append("YES");
        } else {
            outputStr.append("NO");
        }
    }

    public static void main(String ... args) {
        StringBuffer sb = new StringBuffer();
        checkIP("0909.138.15.249", sb);
        System.out.println(sb);
    }
}
