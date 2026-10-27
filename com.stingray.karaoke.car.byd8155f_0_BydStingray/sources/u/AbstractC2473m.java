package u;

import java.util.ConcurrentModificationException;
import v.AbstractC2530a;

/* JADX INFO: renamed from: u.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2473m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f22607a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object[] f22608b = new Object[0];
    public static final Object c = new Object();

    public static final void a(C2459G c2459g) {
        int i7 = c2459g.o;
        int[] iArr = c2459g.f22588m;
        Object[] objArr = c2459g.f22589n;
        int i10 = 0;
        for (int i11 = 0; i11 < i7; i11++) {
            Object obj = objArr[i11];
            if (obj != c) {
                if (i11 != i10) {
                    iArr[i10] = iArr[i11];
                    objArr[i10] = obj;
                    objArr[i11] = null;
                }
                i10++;
            }
        }
        c2459g.l = false;
        c2459g.o = i10;
    }

    public static final void b(C2466f c2466f, int i7) {
        A7.m.f(c2466f, "<this>");
        c2466f.l = new int[i7];
        c2466f.f22598m = new Object[i7];
    }

    public static long c(int i7, int i10) {
        return (((long) i10) & 4294967295L) | (((long) i7) << 32);
    }

    public static final int d(C2466f c2466f, Object obj, int i7) {
        A7.m.f(c2466f, "<this>");
        int i10 = c2466f.f22599n;
        if (i10 == 0) {
            return -1;
        }
        try {
            int iA = AbstractC2530a.a(c2466f.f22599n, i7, c2466f.l);
            if (iA < 0 || A7.m.a(obj, c2466f.f22598m[iA])) {
                return iA;
            }
            int i11 = iA + 1;
            while (i11 < i10 && c2466f.l[i11] == i7) {
                if (A7.m.a(obj, c2466f.f22598m[i11])) {
                    return i11;
                }
                i11++;
            }
            for (int i12 = iA - 1; i12 >= 0 && c2466f.l[i12] == i7; i12--) {
                if (A7.m.a(obj, c2466f.f22598m[i12])) {
                    return i12;
                }
            }
            return ~i11;
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }
}
