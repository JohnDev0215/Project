package defpackage;

import android.net.Uri;

/* JADX INFO: compiled from: DebugStrings.kt */
/* JADX INFO: loaded from: classes.dex */
public class d70 implements i31 {
    public static d70 a;
    public static d70 b;

    public /* synthetic */ d70() {
    }

    public static final String d(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final String h(b00 b00Var) {
        Object objV;
        if (b00Var instanceof ta0) {
            return b00Var.toString();
        }
        try {
            objV = b00Var + '@' + d(b00Var);
        } catch (Throwable th) {
            objV = a8.v(th);
        }
        if (vb2.a(objV) != null) {
            objV = ((Object) b00Var.getClass().getName()) + '@' + d(b00Var);
        }
        return (String) objV;
    }

    public void c(float f, float f2, xj2 xj2Var) {
        throw null;
    }

    public boolean e(String str) {
        return false;
    }

    public void f(String str) {
        Uri.parse(str);
    }

    public d70(int i) {
    }

    @Override // defpackage.i31
    public final void a(int i) {
    }

    @Override // defpackage.i31
    public final void b(int i) {
    }

    public String g(String str, String str2, String str3) {
        return str;
    }
}
