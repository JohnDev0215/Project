package com.ts.car.input;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StringUtils {
    private static final Long INVALID = -1L;
    private static final int UNKNOWN = -1;

    public static String join(List<Integer> list) {
        if (list == null) {
            return ConfigParser.EMPTY;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(String.valueOf(list.get(i)));
        }
        return sb.toString();
    }

    public static int str2Int(String value) {
        try {
            int result = Integer.parseInt(value);
            return result;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public static Long str2Long(String value) {
        Long result = INVALID;
        try {
            if (!ConfigParser.isEmpty(value)) {
                return Long.valueOf(Long.parseLong(value));
            }
            return result;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return result;
        }
    }
}
