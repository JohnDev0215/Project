package defpackage;

import defpackage.bi0;
import defpackage.y83;
import java.lang.Comparable;
import java.util.Arrays;

/* JADX INFO: compiled from: ThreadSafeHeap.kt */
/* JADX INFO: loaded from: classes.dex */
public class x83<T extends y83 & Comparable<? super T>> {
    private volatile /* synthetic */ int _size = 0;
    public T[] a;

    public final void a(T t) {
        t.e((bi0.b) this);
        T[] tArr = this.a;
        if (tArr == null) {
            tArr = (T[]) new y83[4];
            this.a = tArr;
        } else if (this._size >= tArr.length) {
            Object[] objArrCopyOf = Arrays.copyOf(tArr, this._size * 2);
            i21.d(objArrCopyOf, "copyOf(this, newSize)");
            tArr = (T[]) ((y83[]) objArrCopyOf);
            this.a = tArr;
        }
        int i = this._size;
        this._size = i + 1;
        tArr[i] = t;
        t.setIndex(i);
        e(i);
    }

    public final boolean b() {
        return this._size == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final T c(int r8) {
        /*
            r7 = this;
            T extends y83 & java.lang.Comparable<? super T>[] r0 = r7.a
            defpackage.i21.b(r0)
            int r1 = r7._size
            r2 = -1
            int r1 = r1 + r2
            r7._size = r1
            int r1 = r7._size
            if (r8 >= r1) goto L72
            int r1 = r7._size
            r7.f(r8, r1)
            int r1 = r8 + (-1)
            int r1 = r1 / 2
            if (r8 <= 0) goto L33
            r3 = r0[r8]
            defpackage.i21.b(r3)
            java.lang.Comparable r3 = (java.lang.Comparable) r3
            r4 = r0[r1]
            defpackage.i21.b(r4)
            int r3 = r3.compareTo(r4)
            if (r3 >= 0) goto L33
            r7.f(r8, r1)
            r7.e(r1)
            goto L72
        L33:
            int r1 = r8 * 2
            int r1 = r1 + 1
            int r3 = r7._size
            if (r1 < r3) goto L3c
            goto L72
        L3c:
            T extends y83 & java.lang.Comparable<? super T>[] r3 = r7.a
            defpackage.i21.b(r3)
            int r4 = r1 + 1
            int r5 = r7._size
            if (r4 >= r5) goto L5a
            r5 = r3[r4]
            defpackage.i21.b(r5)
            java.lang.Comparable r5 = (java.lang.Comparable) r5
            r6 = r3[r1]
            defpackage.i21.b(r6)
            int r5 = r5.compareTo(r6)
            if (r5 >= 0) goto L5a
            r1 = r4
        L5a:
            r4 = r3[r8]
            defpackage.i21.b(r4)
            java.lang.Comparable r4 = (java.lang.Comparable) r4
            r3 = r3[r1]
            defpackage.i21.b(r3)
            int r3 = r4.compareTo(r3)
            if (r3 > 0) goto L6d
            goto L72
        L6d:
            r7.f(r8, r1)
            r8 = r1
            goto L33
        L72:
            int r8 = r7._size
            r8 = r0[r8]
            defpackage.i21.b(r8)
            r1 = 0
            r8.e(r1)
            r8.setIndex(r2)
            int r7 = r7._size
            r0[r7] = r1
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x83.c(int):y83");
    }

    public final T d() {
        T t;
        synchronized (this) {
            t = this._size > 0 ? (T) c(0) : null;
        }
        return t;
    }

    public final void e(int i) {
        while (i > 0) {
            Object[] objArr = this.a;
            i21.b(objArr);
            int i2 = (i - 1) / 2;
            Object obj = objArr[i2];
            i21.b(obj);
            Object obj2 = objArr[i];
            i21.b(obj2);
            if (((Comparable) obj).compareTo(obj2) <= 0) {
                return;
            }
            f(i, i2);
            i = i2;
        }
    }

    public final void f(int i, int i2) {
        T[] tArr = this.a;
        i21.b(tArr);
        T t = tArr[i2];
        i21.b(t);
        T t2 = tArr[i];
        i21.b(t2);
        tArr[i] = t;
        tArr[i2] = t2;
        t.setIndex(i);
        t2.setIndex(i2);
    }
}
