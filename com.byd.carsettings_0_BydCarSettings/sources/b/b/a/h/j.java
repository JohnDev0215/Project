package b.b.a.h;

import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: StringUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class j {
    public static String a(String str, Object... objArr) {
        String str2;
        try {
            str2 = String.format(str, objArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            str2 = null;
        }
        return str2 == null ? "" : str2;
    }

    public static String b(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    public static boolean c(String str) {
        return str == null || str.isEmpty();
    }
}
