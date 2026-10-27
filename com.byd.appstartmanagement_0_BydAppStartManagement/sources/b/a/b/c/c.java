package b.a.b.c;

/* JADX INFO: compiled from: StringUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    public static String a(String str, Object... objArr) {
        String str2;
        try {
            str2 = String.format(str, objArr);
        } catch (Exception e) {
            e.printStackTrace();
            str2 = null;
        }
        return str2 == null ? "" : str2;
    }

    public static boolean b(String str) {
        return str == null || str.isEmpty();
    }
}
