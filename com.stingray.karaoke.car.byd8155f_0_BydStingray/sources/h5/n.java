package h5;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public final class n extends l9.d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Unsafe f15550b;
    public static final long c;
    public static final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final long f15551e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f15552f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final long f15553g;

    static {
        Unsafe unsafe;
        try {
            try {
                unsafe = Unsafe.getUnsafe();
            } catch (PrivilegedActionException e10) {
                throw new RuntimeException("Could not initialize intrinsics", e10.getCause());
            }
        } catch (SecurityException unused) {
            unsafe = (Unsafe) AccessController.doPrivileged(new m());
        }
        try {
            d = unsafe.objectFieldOffset(p.class.getDeclaredField("n"));
            c = unsafe.objectFieldOffset(p.class.getDeclaredField("m"));
            f15551e = unsafe.objectFieldOffset(p.class.getDeclaredField("l"));
            f15552f = unsafe.objectFieldOffset(o.class.getDeclaredField("a"));
            f15553g = unsafe.objectFieldOffset(o.class.getDeclaredField("b"));
            f15550b = unsafe;
        } catch (NoSuchFieldException e11) {
            throw new RuntimeException(e11);
        }
    }

    @Override // l9.d
    public final boolean E(p pVar, C1378d c1378d, C1378d c1378d2) {
        return k.a(f15550b, pVar, c, c1378d, c1378d2);
    }

    @Override // l9.d
    public final boolean F(p pVar, Object obj, Object obj2) {
        return l.a(f15550b, pVar, f15551e, obj, obj2);
    }

    @Override // l9.d
    public final boolean G(p pVar, o oVar, o oVar2) {
        return j.a(f15550b, pVar, d, oVar, oVar2);
    }

    @Override // l9.d
    public final C1378d S(p pVar) {
        C1378d c1378d;
        C1378d c1378d2 = C1378d.d;
        do {
            c1378d = pVar.f15559m;
            if (c1378d2 == c1378d) {
                return c1378d;
            }
        } while (!E(pVar, c1378d, c1378d2));
        return c1378d;
    }

    @Override // l9.d
    public final o T(p pVar) {
        o oVar;
        o oVar2 = o.c;
        do {
            oVar = pVar.f15560n;
            if (oVar2 == oVar) {
                return oVar;
            }
        } while (!G(pVar, oVar, oVar2));
        return oVar;
    }

    @Override // l9.d
    public final void Y(o oVar, o oVar2) {
        f15550b.putObject(oVar, f15553g, oVar2);
    }

    @Override // l9.d
    public final void Z(o oVar, Thread thread) {
        f15550b.putObject(oVar, f15552f, thread);
    }
}
