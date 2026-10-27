package p;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class lk2 {
    public static final AtomicIntegerFieldUpdater b = AtomicIntegerFieldUpdater.newUpdater(lk2.class, "_size");
    private volatile int _size;
    public ni0[] a;

    public final void a(ni0 ni0Var) {
        ni0Var.d((oi0) this);
        ni0[] ni0VarArr = this.a;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = b;
        if (ni0VarArr == null) {
            ni0VarArr = new ni0[4];
            this.a = ni0VarArr;
        } else if (atomicIntegerFieldUpdater.get(this) >= ni0VarArr.length) {
            Object[] objArrCopyOf = Arrays.copyOf(ni0VarArr, atomicIntegerFieldUpdater.get(this) * 2);
            v03.i(objArrCopyOf, "copyOf(this, newSize)");
            ni0VarArr = (ni0[]) objArrCopyOf;
            this.a = ni0VarArr;
        }
        int i = atomicIntegerFieldUpdater.get(this);
        atomicIntegerFieldUpdater.set(this, i + 1);
        ni0VarArr[i] = ni0Var;
        ni0Var.e = i;
        c(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.ni0 b(int r9) {
        /*
            r8 = this;
            p.ni0[] r0 = r8.a
            p.v03.h(r0)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = p.lk2.b
            int r2 = r1.get(r8)
            r3 = -1
            int r2 = r2 + r3
            r1.set(r8, r2)
            int r2 = r1.get(r8)
            if (r9 >= r2) goto L7a
            int r2 = r1.get(r8)
            r8.d(r9, r2)
            int r2 = r9 + (-1)
            int r2 = r2 / 2
            if (r9 <= 0) goto L3a
            r4 = r0[r9]
            p.v03.h(r4)
            r5 = r0[r2]
            p.v03.h(r5)
            int r4 = r4.compareTo(r5)
            if (r4 >= 0) goto L3a
            r8.d(r9, r2)
            r8.c(r2)
            goto L7a
        L3a:
            int r2 = r9 * 2
            int r4 = r2 + 1
            int r5 = r1.get(r8)
            if (r4 < r5) goto L45
            goto L7a
        L45:
            p.ni0[] r5 = r8.a
            p.v03.h(r5)
            int r2 = r2 + 2
            int r6 = r1.get(r8)
            if (r2 >= r6) goto L63
            r6 = r5[r2]
            p.v03.h(r6)
            r7 = r5[r4]
            p.v03.h(r7)
            int r6 = r6.compareTo(r7)
            if (r6 >= 0) goto L63
            goto L64
        L63:
            r2 = r4
        L64:
            r4 = r5[r9]
            p.v03.h(r4)
            r5 = r5[r2]
            p.v03.h(r5)
            int r4 = r4.compareTo(r5)
            if (r4 > 0) goto L75
            goto L7a
        L75:
            r8.d(r9, r2)
            r9 = r2
            goto L3a
        L7a:
            int r9 = r1.get(r8)
            r9 = r0[r9]
            p.v03.h(r9)
            r2 = 0
            r9.d(r2)
            r9.e = r3
            int r1 = r1.get(r8)
            r0[r1] = r2
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: p.lk2.b(int):p.ni0");
    }

    public final void c(int i) {
        while (i > 0) {
            Object[] objArr = this.a;
            v03.h(objArr);
            int i2 = (i - 1) / 2;
            ni0 ni0Var = objArr[i2];
            v03.h(ni0Var);
            Object obj = objArr[i];
            v03.h(obj);
            if (ni0Var.compareTo(obj) <= 0) {
                return;
            }
            d(i, i2);
            i = i2;
        }
    }

    public final void d(int i, int i2) {
        ni0[] ni0VarArr = this.a;
        v03.h(ni0VarArr);
        ni0 ni0Var = ni0VarArr[i2];
        v03.h(ni0Var);
        ni0 ni0Var2 = ni0VarArr[i];
        v03.h(ni0Var2);
        ni0VarArr[i] = ni0Var;
        ni0VarArr[i2] = ni0Var2;
        ni0Var.e = i;
        ni0Var2.e = i2;
    }
}
