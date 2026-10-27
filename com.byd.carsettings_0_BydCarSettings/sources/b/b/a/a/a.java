package b.b.a.a;

import android.content.Context;

/* JADX INFO: compiled from: BydContextManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f468a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f469b;

    public static a b() {
        if (f468a == null) {
            synchronized (a.class) {
                if (f468a == null) {
                    f468a = new a();
                }
            }
        }
        return f468a;
    }

    public Context a() {
        return this.f469b;
    }

    public void c(Context context) {
        this.f469b = context;
    }
}
