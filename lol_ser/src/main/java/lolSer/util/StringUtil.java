package lolSer.util;

import java.util.StringTokenizer;

public class StringUtil {
    private static int progressDot = 1;

    public static String[] stringToStringArray(String str, int index) {
        StringTokenizer st = new StringTokenizer(str, ",");
        String[] stl = new String[index];
        for (int i = 0; i < index; i++) {
            stl[i] = st.nextToken();
        }

        return stl;
    }
    public static String checkSpace(String str) {
        if(str.length() == 0 ) return str;
        StringBuilder newString = new StringBuilder();
        StringTokenizer st = new StringTokenizer(str, " ");
        while (st.hasMoreTokens()) {
            newString.append(st.nextToken()).append("%20");
        }
        newString.delete(newString.length() - 3, newString.length());
        return newString.toString();
    }

    public static String loadingString(String str) {
        //게임 실행을 기다리는중.
        StringBuilder st = new StringBuilder(str);
        if (progressDot == 3) {
            progressDot = 0;
            st.replace(st.indexOf("."), st.toString().length(), "");
        }
        st.append(".");
        progressDot++;

        return st.toString();
    }
    public static String classNameWithOutPackage(String str) {
        StringTokenizer st = new StringTokenizer(str, ".");
        String lastToken = "";
        while(st.hasMoreTokens()) {
            lastToken = st.nextToken();
        }
        return lastToken;
    }

    public static int stringLengthReturnFontSize(String str) {
        String strTrim = str.trim();
        if(strTrim.length() > 13) return 13;
        if(strTrim.length() > 10) return 15;
        else return 20;
    }
}
