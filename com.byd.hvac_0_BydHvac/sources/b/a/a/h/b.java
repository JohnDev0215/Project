package b.a.a.h;

import android.content.Context;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile b f236a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f237b;

    public static b b() {
        if (f236a == null) {
            synchronized (b.class) {
                if (f236a == null) {
                    f236a = new b();
                }
            }
        }
        return f236a;
    }

    public Context a() {
        return this.f237b;
    }

    public void c(Context context) {
        this.f237b = context;
    }
}
