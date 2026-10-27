package ts.car.diag.manager;

import android.content.Context;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile p f1619a;

    public static p a(Context context) {
        if (f1619a == null) {
            synchronized (p.class) {
                if (f1619a == null) {
                    f1619a = new p(context);
                }
            }
        }
        return f1619a;
    }

    public abstract boolean b();

    public abstract String c();

    public abstract byte d();

    public abstract byte e();

    public abstract String f();

    public abstract String g();

    public abstract byte h(String str);

    public abstract byte i(byte b2);

    public abstract byte j();

    public abstract void k(Context context);

    public abstract byte l(String str, String str2, String str3, int i);

    public abstract byte m(Byte b2);
}
