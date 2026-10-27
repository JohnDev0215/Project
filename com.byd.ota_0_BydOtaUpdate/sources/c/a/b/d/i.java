package c.a.b.d;

import android.text.TextUtils;

/* JADX INFO: compiled from: SensitiveInfoUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length() - 4; i2++) {
            sb.append("*");
        }
        sb.append(str.substring(str.length() - 4));
        return sb.toString();
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 4) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < str.length() - 4; i2++) {
            sb.append("*");
        }
        sb.append(str.substring(str.length() - 4));
        return sb.toString();
    }
}
