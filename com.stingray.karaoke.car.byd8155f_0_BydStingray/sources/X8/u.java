package X8;

import S8.K;
import S8.L;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f10580b = AtomicIntegerFieldUpdater.newUpdater(u.class, "_size$volatile");
    private volatile /* synthetic */ int _size$volatile;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public K[] f10581a;

    public final void a(K k10) {
        k10.d((L) this);
        K[] kArr = this.f10581a;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f10580b;
        if (kArr == null) {
            kArr = new K[4];
            this.f10581a = kArr;
        } else if (atomicIntegerFieldUpdater.get(this) >= kArr.length) {
            Object[] objArrCopyOf = Arrays.copyOf(kArr, atomicIntegerFieldUpdater.get(this) * 2);
            A7.m.e(objArrCopyOf, "copyOf(...)");
            kArr = (K[]) objArrCopyOf;
            this.f10581a = kArr;
        }
        int i7 = atomicIntegerFieldUpdater.get(this);
        atomicIntegerFieldUpdater.set(this, i7 + 1);
        kArr[i7] = k10;
        k10.f8089m = i7;
        c(i7);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final S8.K b(int r9) {
        /*
            r8 = this;
            S8.K[] r0 = r8.f10581a
            A7.m.c(r0)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = X8.u.f10580b
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
            A7.m.c(r4)
            r5 = r0[r2]
            A7.m.c(r5)
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
            S8.K[] r5 = r8.f10581a
            A7.m.c(r5)
            int r2 = r2 + 2
            int r6 = r1.get(r8)
            if (r2 >= r6) goto L63
            r6 = r5[r2]
            A7.m.c(r6)
            r7 = r5[r4]
            A7.m.c(r7)
            int r6 = r6.compareTo(r7)
            if (r6 >= 0) goto L63
            goto L64
        L63:
            r2 = r4
        L64:
            r4 = r5[r9]
            A7.m.c(r4)
            r5 = r5[r2]
            A7.m.c(r5)
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
            A7.m.c(r9)
            r2 = 0
            r9.d(r2)
            r9.f8089m = r3
            int r8 = r1.get(r8)
            r0[r8] = r2
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: X8.u.b(int):S8.K");
    }

    public final void c(int i7) {
        while (i7 > 0) {
            K[] kArr = this.f10581a;
            A7.m.c(kArr);
            int i10 = (i7 - 1) / 2;
            K k10 = kArr[i10];
            A7.m.c(k10);
            K k11 = kArr[i7];
            A7.m.c(k11);
            if (k10.compareTo(k11) <= 0) {
                return;
            }
            d(i7, i10);
            i7 = i10;
        }
    }

    public final void d(int i7, int i10) {
        K[] kArr = this.f10581a;
        A7.m.c(kArr);
        K k10 = kArr[i10];
        A7.m.c(k10);
        K k11 = kArr[i7];
        A7.m.c(k11);
        kArr[i7] = k10;
        kArr[i10] = k11;
        k10.f8089m = i7;
        k11.f8089m = i10;
    }
}
