package b.b.m.g;

import java.text.DecimalFormat;

/* JADX INFO: compiled from: NumberFormatter.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {
    public static String a(int i, double d2) {
        String str = "0.00";
        if (i == 0) {
            str = "0";
        } else if (i == 1) {
            str = "0.0";
        } else if (i != 2) {
            if (i == 3) {
                str = "0.000";
            } else if (i == 4) {
                str = "0.0000";
            }
        }
        return new DecimalFormat(str).format(d2);
    }
}
