package p;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class vc1 {
    public static final Object b = new Object();
    public static volatile vc1 c;
    public final int a;

    public vc1(int i) {
        this.a = i;
    }

    public static vc1 c() {
        vc1 vc1Var;
        synchronized (b) {
            try {
                if (c == null) {
                    c = new vc1(3);
                }
                vc1Var = c;
            } catch (Throwable th) {
                throw th;
            }
        }
        return vc1Var;
    }

    public static String d(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(23);
        sb.append("WM-");
        if (length >= 20) {
            sb.append(str.substring(0, 20));
        } else {
            sb.append(str);
        }
        return sb.toString();
    }

    public final void a(String str, String str2) {
        if (this.a <= 6) {
            Log.e(str, str2);
        }
    }

    public final void b(String str, String str2, Throwable th) {
        if (this.a <= 6) {
            Log.e(str, str2, th);
        }
    }
}
