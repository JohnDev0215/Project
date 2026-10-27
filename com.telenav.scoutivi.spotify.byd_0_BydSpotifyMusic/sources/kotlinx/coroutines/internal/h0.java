package kotlinx.coroutines.internal;

import java.lang.Comparable;
import java.util.Arrays;
import kotlinx.coroutines.internal.i0;

/* JADX INFO: compiled from: ThreadSafeHeap.kt */
/* JADX INFO: loaded from: classes.dex */
public class h0<T extends i0 & Comparable<? super T>> {
    private volatile int _size = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private T[] f7646a;

    private final T[] f() {
        T[] tArr = this.f7646a;
        if (tArr == null) {
            T[] tArr2 = (T[]) new i0[4];
            this.f7646a = tArr2;
            return tArr2;
        }
        if (c() < tArr.length) {
            return tArr;
        }
        Object[] objArrCopyOf = Arrays.copyOf(tArr, c() * 2);
        kotlin.jvm.internal.l.d(objArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        T[] tArr3 = (T[]) ((i0[]) objArrCopyOf);
        this.f7646a = tArr3;
        return tArr3;
    }

    private final void j(int i7) {
        this._size = i7;
    }

    private final void k(int i7) {
        while (true) {
            int i8 = (i7 * 2) + 1;
            if (i8 >= c()) {
                return;
            }
            T[] tArr = this.f7646a;
            kotlin.jvm.internal.l.c(tArr);
            int i9 = i8 + 1;
            if (i9 < c()) {
                T t6 = tArr[i9];
                kotlin.jvm.internal.l.c(t6);
                T t7 = tArr[i8];
                kotlin.jvm.internal.l.c(t7);
                if (((Comparable) t6).compareTo(t7) < 0) {
                    i8 = i9;
                }
            }
            T t8 = tArr[i7];
            kotlin.jvm.internal.l.c(t8);
            T t9 = tArr[i8];
            kotlin.jvm.internal.l.c(t9);
            if (((Comparable) t8).compareTo(t9) <= 0) {
                return;
            }
            m(i7, i8);
            i7 = i8;
        }
    }

    private final void l(int i7) {
        while (i7 > 0) {
            T[] tArr = this.f7646a;
            kotlin.jvm.internal.l.c(tArr);
            int i8 = (i7 - 1) / 2;
            T t6 = tArr[i8];
            kotlin.jvm.internal.l.c(t6);
            T t7 = tArr[i7];
            kotlin.jvm.internal.l.c(t7);
            if (((Comparable) t6).compareTo(t7) <= 0) {
                return;
            }
            m(i7, i8);
            i7 = i8;
        }
    }

    private final void m(int i7, int i8) {
        T[] tArr = this.f7646a;
        kotlin.jvm.internal.l.c(tArr);
        T t6 = tArr[i8];
        kotlin.jvm.internal.l.c(t6);
        T t7 = tArr[i7];
        kotlin.jvm.internal.l.c(t7);
        tArr[i7] = t6;
        tArr[i8] = t7;
        t6.a(i7);
        t7.a(i8);
    }

    public final void a(T t6) {
        t6.b(this);
        i0[] i0VarArrF = f();
        int iC = c();
        j(iC + 1);
        i0VarArrF[iC] = t6;
        t6.a(iC);
        l(iC);
    }

    public final T b() {
        T[] tArr = this.f7646a;
        if (tArr != null) {
            return tArr[0];
        }
        return null;
    }

    public final int c() {
        return this._size;
    }

    public final boolean d() {
        return c() == 0;
    }

    public final T e() {
        T t6;
        synchronized (this) {
            t6 = (T) b();
        }
        return t6;
    }

    public final boolean g(T t6) {
        boolean z6;
        synchronized (this) {
            if (t6.c() == null) {
                z6 = false;
            } else {
                h(t6.d());
                z6 = true;
            }
        }
        return z6;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T h(int r6) {
        /*
            r5 = this;
            T extends kotlinx.coroutines.internal.i0 & java.lang.Comparable<? super T>[] r0 = r5.f7646a
            kotlin.jvm.internal.l.c(r0)
            int r1 = r5.c()
            r2 = -1
            int r1 = r1 + r2
            r5.j(r1)
            int r1 = r5.c()
            if (r6 >= r1) goto L3d
            int r1 = r5.c()
            r5.m(r6, r1)
            int r1 = r6 + (-1)
            int r1 = r1 / 2
            if (r6 <= 0) goto L3a
            r3 = r0[r6]
            kotlin.jvm.internal.l.c(r3)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            r4 = r0[r1]
            kotlin.jvm.internal.l.c(r4)
            int r3 = r3.compareTo(r4)
            if (r3 >= 0) goto L3a
            r5.m(r6, r1)
            r5.l(r1)
            goto L3d
        L3a:
            r5.k(r6)
        L3d:
            int r6 = r5.c()
            r6 = r0[r6]
            kotlin.jvm.internal.l.c(r6)
            r1 = 0
            r6.b(r1)
            r6.a(r2)
            int r2 = r5.c()
            r0[r2] = r1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.h0.h(int):kotlinx.coroutines.internal.i0");
    }

    public final T i() {
        T t6;
        synchronized (this) {
            t6 = c() > 0 ? (T) h(0) : null;
        }
        return t6;
    }
}
