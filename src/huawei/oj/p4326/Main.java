package huawei.oj.p4326;

/**
 * Created by yfwz100 on 16/5/11.
 */
public class Main {

    public static float getRateFromString(String pString, char c) {
        int counter = 0;
        for (int i=0; i<pString.length(); i++) {
            char s = pString.charAt(i);
            if (s == c) {
                counter ++;
            }
        }
        return (float)(Math.round((double)counter / pString.length() * 100) / 100f);
    }

    public static void main(String ... args) {
        System.out.println(getRateFromString("wo shi, yi zhi.xiao xiao niao", ' '));
    }
}
