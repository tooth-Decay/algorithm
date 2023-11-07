import java.util.Arrays;

/**
 * @Author: tooth-Decay
 * @Date: 2023/11/4 9:45
 * @Description TODO
 */
public class manatuer字符串回文 {
    public static void main(String[] args) {
        String s = "cddb";
        int[] msg = manatuer(s);
        System.out.println(Arrays.toString(msg));
        int length = msg[0];
        int start = msg[1];
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + length; i++) {
            sb.append(s.charAt(i));
        }
        System.out.println(sb);

    }

    public static int[] manatuer(String string) {
        String newStr = strProcess(string);
        char[] newStrCharArray = newStr.toCharArray();
        int length = newStr.length();
        int[] pRadius = new int[length];
        int r = -1;
        int center = -1;
        int maxPLength = 0;

        int start = 0;
        for (int i = 0; i < length; i++) {
            pRadius[i] = i < r ? Math.min(pRadius[2 * center - i], r - i) : 1;//i<r说明被上次i的回文半径包住
            while (i + pRadius[i] < length && i - pRadius[i] >= 0) {
                if (newStrCharArray[i + pRadius[i]] == newStrCharArray[i - pRadius[i]]) pRadius[i]++;
                else break;
            }
            int newR = i + pRadius[i];
            if (newR > r) {
                r = newR;
                center = i;
            }
            if (pRadius[i] > maxPLength) {
                maxPLength = pRadius[i];
                start = (i - maxPLength + 1) / 2;
            }
        }
        return new int[]{maxPLength - 1, start};
    }

    public static String strProcess(String string) {
        StringBuilder newStr = new StringBuilder();
        int i = 0;
        while (i < string.length()) {
            newStr.append("X").append(string.charAt(i));
            i++;
        }
        newStr.append("X");
        return newStr.toString();
    }
}
