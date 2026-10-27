package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class si0 implements ri0 {
    public static String a(String str) {
        return (str == null || str.isEmpty()) ? str : ((k1) com.google.common.hash.d.b()).b(str).toString();
    }
}
