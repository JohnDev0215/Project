package n3;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class M {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final K f19838b;
    public static final K c;
    public static final K d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final K f19839e = new K(3, true);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f19840a;

    static {
        boolean z6 = false;
        f19838b = new K(1, z6);
        c = new K(2, z6);
        d = new K(0, z6);
    }

    public M(boolean z6) {
        this.f19840a = z6;
    }

    public abstract Object a(String str, Bundle bundle);

    public abstract String b();

    public Object c(Object obj, String str) {
        return d(str);
    }

    public abstract Object d(String str);

    public abstract void e(Bundle bundle, String str, Object obj);

    public boolean f(Object obj, Object obj2) {
        return A7.m.a(obj, obj2);
    }

    public final String toString() {
        return b();
    }
}
