package huawei.oj.p4333;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 考勤系统.
 * <p>
 * Created by yfwz100 on 16/5/9.
 */
public class Main {
    private final static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    static class AttandentRecord {

        int id;
        String name;
        List<Date> checkTime = new ArrayList<Date>();

        AttandentRecord(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int getCheckTimes() {
            return checkTime.size();
        }

        Date getMinCheckTime() {
            return Collections.min(checkTime);
        }

        Date getMaxCheckTime() {
            return Collections.max(checkTime);
        }

        String getWorkTime() {
            switch (getStatus()) {
                case 0:
                case 2:
                case 4:
                case 6:
                    Date a = getMinCheckTime(), b = getMaxCheckTime();
                    long delta = (b.getTime() - a.getTime()) / 1000;
                    long hour = delta / 3600;
                    long min = delta % 3600 / 60;
                    return String.format("%dH%dM", hour, min);
                default:
                    return "0H0M";
            }
        }

        int getStatus() {
            Date a = getMinCheckTime(), b = getMaxCheckTime();
            int times = getCheckTimes();
            try {
                Date start = sdf.parse("8:00"), end = sdf.parse("17:30");
                if (times > 1) {
                    if (a.before(start) && b.after(end)) {
                        return 0;
                    } else if ((b.compareTo(start) <= 0) || (a.compareTo(end) >= 0)) {
                        return 1;
                    } else {
                        int status = 0;
                        if (a.compareTo(start) >= 0) {
                            status |= 2;
                        }
                        if (b.compareTo(end) <= 0) {
                            status |= 4;
                        }
                        return status;
                    }
                } else {
                    return 3;
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return 3;
            }
        }

        String getStatusText() {
            switch (getStatus()) {
                case 0:
                    return "NORMAL";
                case 1:
                    return "ABSENT";
                case 2:
                    return "WORK LATE";
                case 4:
                    return "LEAVE EARLY";
                case 6:
                    return "WORK LATE&LEAVE EARLY";
                default:
                    return "PUNCH ABNORMAL";
            }
        }

        @Override
        public String toString() {
            return String.format("ID = %d\nNAME = %s\nTYPE = %s\nCHECK IN = %s\nCHECK OUT =%s\nWORK TIME = %s",
                    id,
                    name,
                    getStatusText(),
                    sdf.format(getMinCheckTime()),
                    getCheckTimes() > 1 ? " " + sdf.format(getMaxCheckTime()) : "",
                    getWorkTime()
            );
        }
    }

    /**
     * 考勤系统:统计考勤时间
     *
     * @param inFilePath  输入文件
     * @param outFilePath 输出文件
     * @return 正常返回true，异常返回false
     */
    public static boolean attandentSystem(String inFilePath, String outFilePath) {
        try {
            Scanner in = new Scanner(new File(inFilePath));

            Map<Integer, AttandentRecord> recordMap = new TreeMap<>();
            while (in.hasNextLine()) {
                Scanner lin = new Scanner(in.nextLine()).useDelimiter(",");
                int id = lin.nextInt();
                String name = lin.next();
                Date checkTime = sdf.parse(lin.next());

                AttandentRecord rec = recordMap.get(id);
                if (rec == null) {
                    rec = new AttandentRecord(id, name);
                    recordMap.put(id, rec);
                }
                rec.checkTime.add(checkTime);
            }

            if (recordMap.size() > 0) {
                PrintStream out = new PrintStream(outFilePath);

                Iterator<AttandentRecord> arItr = recordMap.values().iterator();
                AttandentRecord rec = arItr.next();
                out.print(rec);
                while (arItr.hasNext()) {
                    out.println();
                    out.println();
                    out.print(arItr.next());
                }

                return true;
            } else {
                return false;
            }
        } catch (FileNotFoundException e) {
            return false;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void main(String... args) {
        attandentSystem("/Users/yfwz100/ws/amateur/OJ/src/huawei/oj/p4333/in.txt", "/Users/yfwz100/ws/amateur/OJ/src/huawei/oj/p4333/out.txt");
        boolean isSame = isSame("/Users/yfwz100/ws/amateur/OJ/src/huawei/oj/p4333/out.txt", "/Users/yfwz100/ws/amateur/OJ/src/huawei/oj/p4333/check.txt");
        System.out.println(isSame);
    }

    private static Vector<String> readOutputFile(String filePath) {
        Vector<String> vector = new Vector<String>();
        try {
            FileInputStream fs = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fs);
            BufferedReader br = new BufferedReader(isr);

            String data = "";
            while ((data = br.readLine()) != null) {
                if (data.length() == 0) {
                    continue;
                }
                vector.add(data);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件路径错误！");
        } catch (IOException e) {
            throw new RuntimeException("文件读取失败！");
        }
        return vector;
    }

    private static boolean isSame(String output, String targetPath) {
        Vector<String> vector = readOutputFile(output);
        Vector<String> tgVector = readOutputFile(targetPath);

        if (vector.size() != tgVector.size()) {
            return false;
        }
        for (int i = 0; i < vector.size(); i++) {
            if (!vector.get(i).equals(tgVector.get(i))) {
                System.err.println(vector.get(i) + " : " + tgVector.get(i));
                return false;
            }
        }
        return true;
    }
}
