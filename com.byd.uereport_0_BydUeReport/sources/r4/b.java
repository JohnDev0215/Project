package r4;

import android.content.Context;

/* JADX INFO: compiled from: DateUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static String a(byte b8) {
        String str = "";
        for (int i8 = 0; i8 < 7; i8++) {
            byte b9 = (byte) (((byte) (b8 >> 1)) << 1);
            str = b9 == b8 ? "0|" + str : "1|" + str;
            b8 = (byte) (b9 >> 1);
        }
        return str.substring(0, str.length() - 1);
    }

    public static String b(int i8) {
        if (i8 > 9) {
            return i8 + "";
        }
        return "0" + i8;
    }

    public static int c(Context context) {
        String strF = e.f(context, "KEY_CHARGE_CYCLE");
        String[] strArrSplit = strF.split("\\|");
        int i8 = 0;
        for (String str : strArrSplit) {
            if ("1".equals(str)) {
                i8++;
            }
        }
        if (i8 == 0) {
            return 0;
        }
        if (i8 == 7) {
            return 1;
        }
        if (strF.contains("1|1")) {
            return 3;
        }
        return ("1".equals(strArrSplit[0]) && "1".equals(strArrSplit[6])) ? 3 : 2;
    }

    public static int d(Context context) {
        String strF = e.f(context, "KEY_USECAR_CYCLE");
        String[] strArrSplit = strF.split("\\|");
        int i8 = 0;
        for (String str : strArrSplit) {
            if ("1".equals(str)) {
                i8++;
            }
        }
        if (i8 == 0) {
            return 0;
        }
        if (i8 == 7) {
            return 1;
        }
        if (strF.contains("1|1")) {
            return 3;
        }
        return ("1".equals(strArrSplit[0]) && "1".equals(strArrSplit[6])) ? 3 : 2;
    }
}
