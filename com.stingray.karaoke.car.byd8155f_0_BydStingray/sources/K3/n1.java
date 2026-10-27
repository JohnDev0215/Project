package k3;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Pair;
import android.util.SparseBooleanArray;
import e2.C1206n;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import l3.C1755k;

/* JADX INFO: loaded from: classes.dex */
public abstract class n1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C1755k f18018a = new C1755k(null);

    public static Object a(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static Object b(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() != 0) {
            return creator.createFromParcel(parcel);
        }
        return null;
    }

    public static void c(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }

    public static boolean d(B1 b12, B1 b13) {
        e2.V v10 = b12.f17732a;
        int i7 = v10.f14008b;
        e2.V v11 = b13.f17732a;
        return i7 == v11.f14008b && v10.f14009e == v11.f14009e && v10.f14012h == v11.f14012h && v10.f14013i == v11.f14013i;
    }

    public static int e(long j3, long j10) {
        if (j3 == -9223372036854775807L || j10 == -9223372036854775807L) {
            return 0;
        }
        if (j10 == 0) {
            return 100;
        }
        return AbstractC1343A.i((int) ((j3 * 100) / j10), 0, 100);
    }

    public static long f(q1 q1Var, long j3, long j10, long j11) {
        boolean zEquals = q1Var.c.equals(B1.l);
        B1 b12 = q1Var.c;
        boolean z6 = zEquals || j10 < b12.c;
        if (!q1Var.f18088v) {
            return (z6 || j3 == -9223372036854775807L) ? b12.f17732a.f14010f : j3;
        }
        if (!z6 && j3 != -9223372036854775807L) {
            return j3;
        }
        if (j11 == -9223372036854775807L) {
            j11 = SystemClock.elapsedRealtime() - b12.c;
        }
        long j12 = b12.f17732a.f14010f + ((long) (j11 * q1Var.f18075g.f13995a));
        long j13 = b12.d;
        return j13 != -9223372036854775807L ? Math.min(j12, j13) : j12;
    }

    public static e2.S g(e2.S s6, e2.S s10) {
        if (s6 == null || s10 == null) {
            return e2.S.f13999b;
        }
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        int i7 = 0;
        while (true) {
            C1206n c1206n = s6.f14000a;
            if (i7 >= c1206n.f14224a.size()) {
                AbstractC1346c.j(!false);
                return new e2.S(new C1206n(sparseBooleanArray));
            }
            if (s10.a(c1206n.b(i7))) {
                int iB = c1206n.b(i7);
                AbstractC1346c.j(!false);
                sparseBooleanArray.append(iB, true);
            }
            i7++;
        }
    }

    public static Pair h(q1 q1Var, o1 o1Var, q1 q1Var2, o1 o1Var2, e2.S s6) {
        boolean z6 = o1Var2.f18026a;
        boolean z10 = o1Var2.f18027b;
        if (z6 && s6.a(17) && !o1Var.f18026a) {
            q1Var2 = q1Var2.l(q1Var.f18078j);
            o1Var2 = new o1(false, z10);
        }
        if (z10 && s6.a(30) && !o1Var.f18027b) {
            q1Var2 = q1Var2.b(q1Var.f18069D);
            o1Var2 = new o1(o1Var2.f18026a, false);
        }
        return new Pair(q1Var2, o1Var2);
    }

    public static void i(e2.W w10, C1681z0 c1681z0) {
        int i7 = c1681z0.f18171b;
        d5.I i10 = c1681z0.f18170a;
        if (i7 == -1) {
            if (w10.R0(20)) {
                w10.L0(i10);
                return;
            } else {
                if (i10.isEmpty()) {
                    return;
                }
                w10.V((e2.F) i10.get(0));
                return;
            }
        }
        boolean zR0 = w10.R0(20);
        long j3 = c1681z0.c;
        if (zR0) {
            w10.m(i10, c1681z0.f18171b, j3);
        } else {
            if (i10.isEmpty()) {
                return;
            }
            w10.E((e2.F) i10.get(0), j3);
        }
    }

    public static void j(Parcel parcel, Parcelable parcelable) {
        if (parcelable == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcelable.writeToParcel(parcel, 0);
        }
    }
}
