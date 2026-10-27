package ts.car.diag.manager;

import android.content.Context;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile k f1605a;

    public static j c(Context context) {
        if (f1605a == null) {
            synchronized (k.class) {
                if (f1605a == null) {
                    f1605a = new k(context);
                }
            }
        }
        return f1605a;
    }

    public abstract byte a();

    public abstract byte b();

    public abstract byte[] d();

    public abstract byte e();

    public abstract void f(Context context);

    public abstract byte g();

    public abstract byte h();

    public abstract void i(u uVar);
}
