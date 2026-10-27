package kotlinx.coroutines.flow;

import java.util.Arrays;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.channels.BufferOverflow;
import p.ad2;
import p.ar;
import p.bd2;
import p.c00;
import p.cd2;
import p.dd2;
import p.dv0;
import p.fv0;
import p.h2;
import p.i2;
import p.lq2;
import p.rp;
import p.rz0;
import p.tz2;
import p.v03;
import p.vr2;
import p.y10;
import p.zp;

/* JADX INFO: loaded from: classes.dex */
public class g extends h2 implements ad2, fv0, dv0, rz0 {
    public final int m;
    public final int n;
    public final BufferOverflow o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object[] f90p;
    public long q;
    public long r;
    public int s;
    public int t;

    public g(int i, int i2, BufferOverflow bufferOverflow) {
        this.m = i;
        this.n = i2;
        this.o = bufferOverflow;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0133 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0122 A[Catch: all -> 0x0107, TryCatch #2 {all -> 0x0107, blocks: (B:66:0x011a, B:68:0x0122, B:72:0x0135, B:75:0x013c, B:76:0x0142, B:77:0x0143, B:64:0x0109, B:57:0x00f1, B:59:0x00f5), top: B:105:0x00f1 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static kotlin.coroutines.intrinsics.CoroutineSingletons f(kotlinx.coroutines.flow.g r19, p.fv0 r20, p.c00 r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.g.f(kotlinx.coroutines.flow.g, p.fv0, p.c00):kotlin.coroutines.intrinsics.CoroutineSingletons");
    }

    @Override // p.rz0
    public final dv0 a(y10 y10Var, int i, BufferOverflow bufferOverflow) {
        return ((i == 0 || i == -3) && bufferOverflow == BufferOverflow.c) ? this : new ar(this, y10Var, i, bufferOverflow);
    }

    @Override // p.fv0
    public final Object b(Object obj, c00 c00Var) {
        c00[] c00VarArrI;
        bd2 bd2Var;
        if (l(obj)) {
            return lq2.a;
        }
        zp zpVar = new zp(1, vr2.k(c00Var));
        zpVar.r();
        c00[] c00VarArrI2 = vr2.c;
        synchronized (this) {
            try {
                if (m(obj)) {
                    zpVar.k(lq2.a);
                    c00VarArrI = i(c00VarArrI2);
                    bd2Var = null;
                } else {
                    bd2 bd2Var2 = new bd2(this, ((long) (this.s + this.t)) + j(), obj, zpVar);
                    h(bd2Var2);
                    this.t++;
                    if (this.n == 0) {
                        c00VarArrI2 = i(c00VarArrI2);
                    }
                    c00VarArrI = c00VarArrI2;
                    bd2Var = bd2Var2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (bd2Var != null) {
            zpVar.t(new rp(1, bd2Var));
        }
        for (c00 c00Var2 : c00VarArrI) {
            if (c00Var2 != null) {
                c00Var2.k(lq2.a);
            }
        }
        Object objQ = zpVar.q();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        if (objQ != coroutineSingletons) {
            objQ = lq2.a;
        }
        return objQ == coroutineSingletons ? objQ : lq2.a;
    }

    @Override // p.dv0
    public final Object c(fv0 fv0Var, c00 c00Var) {
        return f(this, fv0Var, c00Var);
    }

    public final Object d(dd2 dd2Var, c00 c00Var) {
        zp zpVar = new zp(1, vr2.k(c00Var));
        zpVar.r();
        synchronized (this) {
            if (n(dd2Var) < 0) {
                dd2Var.b = zpVar;
            } else {
                zpVar.k(lq2.a);
            }
        }
        Object objQ = zpVar.q();
        return objQ == CoroutineSingletons.c ? objQ : lq2.a;
    }

    public final void e() {
        if (this.n != 0 || this.t > 1) {
            Object[] objArr = this.f90p;
            v03.h(objArr);
            while (this.t > 0) {
                long j = j();
                int i = this.s;
                int i2 = this.t;
                if (objArr[((int) ((j + ((long) (i + i2))) - 1)) & (objArr.length - 1)] != cd2.a) {
                    return;
                }
                this.t = i2 - 1;
                cd2.a(objArr, j() + ((long) (this.s + this.t)), null);
            }
        }
    }

    public final void g() {
        i2[] i2VarArr;
        Object[] objArr = this.f90p;
        v03.h(objArr);
        cd2.a(objArr, j(), null);
        this.s--;
        long j = j() + 1;
        if (this.q < j) {
            this.q = j;
        }
        if (this.r < j) {
            if (this.e != 0 && (i2VarArr = this.c) != null) {
                for (i2 i2Var : i2VarArr) {
                    if (i2Var != null) {
                        dd2 dd2Var = (dd2) i2Var;
                        long j2 = dd2Var.a;
                        if (j2 >= 0 && j2 < j) {
                            dd2Var.a = j;
                        }
                    }
                }
            }
            this.r = j;
        }
    }

    public final void h(Object obj) {
        int i = this.s + this.t;
        Object[] objArrK = this.f90p;
        if (objArrK == null) {
            objArrK = k(0, 2, null);
        } else if (i >= objArrK.length) {
            objArrK = k(i, objArrK.length * 2, objArrK);
        }
        cd2.a(objArrK, j() + ((long) i), obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [p.c00[]] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r6v3 */
    public final c00[] i(c00[] c00VarArr) {
        i2[] i2VarArr;
        dd2 dd2Var;
        zp zpVar;
        int length = c00VarArr.length;
        if (this.e != 0 && (i2VarArr = this.c) != null) {
            int length2 = i2VarArr.length;
            int i = 0;
            c00VarArr = c00VarArr;
            while (i < length2) {
                i2 i2Var = i2VarArr[i];
                if (i2Var != null && (zpVar = (dd2Var = (dd2) i2Var).b) != null && n(dd2Var) >= 0) {
                    int length3 = c00VarArr.length;
                    c00VarArr = c00VarArr;
                    if (length >= length3) {
                        Object[] objArrCopyOf = Arrays.copyOf((Object[]) c00VarArr, Math.max(2, c00VarArr.length * 2));
                        v03.i(objArrCopyOf, "copyOf(this, newSize)");
                        c00VarArr = objArrCopyOf;
                    }
                    ((c00[]) c00VarArr)[length] = zpVar;
                    dd2Var.b = null;
                    length++;
                }
                i++;
                c00VarArr = c00VarArr;
            }
        }
        return (c00[]) c00VarArr;
    }

    public final long j() {
        return Math.min(this.r, this.q);
    }

    public final Object[] k(int i, int i2, Object[] objArr) {
        if (i2 <= 0) {
            throw new IllegalStateException("Buffer size overflow".toString());
        }
        Object[] objArr2 = new Object[i2];
        this.f90p = objArr2;
        if (objArr == null) {
            return objArr2;
        }
        long j = j();
        for (int i3 = 0; i3 < i; i3++) {
            long j2 = ((long) i3) + j;
            cd2.a(objArr2, j2, objArr[((int) j2) & (objArr.length - 1)]);
        }
        return objArr2;
    }

    public final boolean l(Object obj) {
        int i;
        boolean z;
        c00[] c00VarArrI = vr2.c;
        synchronized (this) {
            if (m(obj)) {
                c00VarArrI = i(c00VarArrI);
                z = true;
            } else {
                z = false;
            }
        }
        for (c00 c00Var : c00VarArrI) {
            if (c00Var != null) {
                c00Var.k(lq2.a);
            }
        }
        return z;
    }

    public final boolean m(Object obj) {
        int i = this.e;
        int i2 = this.m;
        if (i == 0) {
            if (i2 != 0) {
                h(obj);
                int i3 = this.s + 1;
                this.s = i3;
                if (i3 > i2) {
                    g();
                }
                this.r = j() + ((long) this.s);
            }
            return true;
        }
        int i4 = this.s;
        int i5 = this.n;
        if (i4 >= i5 && this.r <= this.q) {
            int iOrdinal = this.o.ordinal();
            if (iOrdinal == 0) {
                return false;
            }
            if (iOrdinal == 2) {
                return true;
            }
        }
        h(obj);
        int i6 = this.s + 1;
        this.s = i6;
        if (i6 > i5) {
            g();
        }
        long j = j() + ((long) this.s);
        long j2 = this.q;
        if (((int) (j - j2)) > i2) {
            p(j2 + 1, this.r, j() + ((long) this.s), j() + ((long) this.s) + ((long) this.t));
        }
        return true;
    }

    public final long n(dd2 dd2Var) {
        long j = dd2Var.a;
        if (j < j() + ((long) this.s)) {
            return j;
        }
        if (this.n <= 0 && j <= j() && this.t != 0) {
            return j;
        }
        return -1L;
    }

    public final Object o(dd2 dd2Var) {
        Object obj;
        c00[] c00VarArrQ = vr2.c;
        synchronized (this) {
            long jN = n(dd2Var);
            if (jN < 0) {
                obj = cd2.a;
            } else {
                long j = dd2Var.a;
                Object[] objArr = this.f90p;
                v03.h(objArr);
                Object obj2 = objArr[((int) jN) & (objArr.length - 1)];
                if (obj2 instanceof bd2) {
                    obj2 = ((bd2) obj2).k;
                }
                dd2Var.a = jN + 1;
                Object obj3 = obj2;
                c00VarArrQ = q(j);
                obj = obj3;
            }
        }
        for (c00 c00Var : c00VarArrQ) {
            if (c00Var != null) {
                c00Var.k(lq2.a);
            }
        }
        return obj;
    }

    public final void p(long j, long j2, long j3, long j4) {
        long jMin = Math.min(j2, j);
        for (long j5 = j(); j5 < jMin; j5++) {
            Object[] objArr = this.f90p;
            v03.h(objArr);
            cd2.a(objArr, j5, null);
        }
        this.q = j;
        this.r = j2;
        this.s = (int) (j3 - jMin);
        this.t = (int) (j4 - j3);
    }

    public final c00[] q(long j) {
        long j2;
        long j3;
        c00[] c00VarArr;
        long j4;
        i2[] i2VarArr;
        long j5 = this.r;
        c00[] c00VarArr2 = vr2.c;
        if (j > j5) {
            return c00VarArr2;
        }
        long j6 = j();
        long j7 = ((long) this.s) + j6;
        int i = this.n;
        if (i == 0 && this.t > 0) {
            j7++;
        }
        if (this.e != 0 && (i2VarArr = this.c) != null) {
            for (i2 i2Var : i2VarArr) {
                if (i2Var != null) {
                    long j8 = ((dd2) i2Var).a;
                    if (j8 >= 0 && j8 < j7) {
                        j7 = j8;
                    }
                }
            }
        }
        if (j7 <= this.r) {
            return c00VarArr2;
        }
        long j9 = j() + ((long) this.s);
        int iMin = this.e > 0 ? Math.min(this.t, i - ((int) (j9 - j7))) : this.t;
        long j10 = ((long) this.t) + j9;
        int i2 = 1;
        if (iMin > 0) {
            c00[] c00VarArr3 = new c00[iMin];
            Object[] objArr = this.f90p;
            v03.h(objArr);
            long j11 = j9;
            int i3 = 0;
            while (true) {
                if (j9 >= j10) {
                    j2 = j7;
                    j3 = j10;
                    break;
                }
                j2 = j7;
                Object obj = objArr[((int) j9) & (objArr.length - i2)];
                tz2 tz2Var = cd2.a;
                if (obj != tz2Var) {
                    v03.g(obj, "null cannot be cast to non-null type kotlinx.coroutines.flow.SharedFlowImpl.Emitter");
                    bd2 bd2Var = (bd2) obj;
                    int i4 = i3 + 1;
                    j3 = j10;
                    c00VarArr3[i3] = bd2Var.l;
                    cd2.a(objArr, j9, tz2Var);
                    cd2.a(objArr, j11, bd2Var.k);
                    j4 = 1;
                    j11++;
                    if (i4 >= iMin) {
                        break;
                    }
                    i3 = i4;
                } else {
                    j3 = j10;
                    j4 = 1;
                }
                j9 += j4;
                j7 = j2;
                j10 = j3;
                i2 = 1;
            }
            c00VarArr = c00VarArr3;
            j9 = j11;
        } else {
            j2 = j7;
            j3 = j10;
            c00VarArr = c00VarArr2;
        }
        int i5 = (int) (j9 - j6);
        long j12 = this.e == 0 ? j9 : j2;
        long jMax = Math.max(this.q, j9 - ((long) Math.min(this.m, i5)));
        if (i == 0 && jMax < j3) {
            Object[] objArr2 = this.f90p;
            v03.h(objArr2);
            if (v03.b(objArr2[((int) jMax) & (objArr2.length - 1)], cd2.a)) {
                j9++;
                jMax++;
            }
        }
        p(jMax, j12, j9, j3);
        e();
        return (c00VarArr.length == 0) ^ true ? i(c00VarArr) : c00VarArr;
    }
}
