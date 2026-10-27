package defpackage;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* JADX INFO: renamed from: Cm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0048Cm {
    public Unsafe a;

    public AbstractC0048Cm(Unsafe unsafe) {
        this.a = unsafe;
    }

    public final int a(Class cls) {
        return this.a.arrayBaseOffset(cls);
    }

    public final int b(Class cls) {
        return this.a.arrayIndexScale(cls);
    }

    public abstract boolean c(Object obj, long j);

    public abstract byte d(Object obj, long j);

    public abstract double e(Object obj, long j);

    public abstract float f(Object obj, long j);

    public final int g(Object obj, long j) {
        return this.a.getInt(obj, j);
    }

    public final long h(Object obj, long j) {
        return this.a.getLong(obj, j);
    }

    public final Object i(Object obj, long j) {
        return this.a.getObject(obj, j);
    }

    public final long j(Field field) {
        return this.a.objectFieldOffset(field);
    }

    public abstract void k(Object obj, long j, boolean z);

    public abstract void l(Object obj, long j, byte b);

    public abstract void m(Object obj, long j, double d);

    public abstract void n(Object obj, long j, float f);

    public final void o(Object obj, long j, int i) {
        this.a.putInt(obj, j, i);
    }

    public final void p(Object obj, long j, long j2) {
        this.a.putLong(obj, j, j2);
    }

    public final void q(Object obj, long j, Object obj2) {
        this.a.putObject(obj, j, obj2);
    }
}
