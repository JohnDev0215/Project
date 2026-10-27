package k3;

import B2.C0074n;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import d5.C1077F;
import d5.C1078G;
import e2.BinderC1198f;
import e2.C1193a;
import e2.C1194b;
import e2.C1195c;
import e2.C1202j;
import e2.C1217z;
import g2.C1289b;
import g2.C1290c;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class q1 {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final q1 f18034F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final String f18035G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final String f18036H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final String f18037I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final String f18038J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final String f18039K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public static final String f18040L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final String f18041M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final String f18042N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public static final String f18043O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public static final String f18044P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public static final String f18045Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public static final String f18046R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public static final String f18047S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public static final String f18048T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public static final String f18049U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public static final String f18050V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public static final String f18051W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public static final String f18052X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public static final String f18053Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public static final String f18054Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public static final String f18055a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public static final String f18056b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public static final String f18057c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public static final String f18058d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public static final String f18059e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public static final String f18060f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public static final String f18061g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static final String f18062h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public static final String f18063i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public static final String f18064j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public static final String f18065k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public static final String f18066l0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final long f18067A;
    public final long B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final long f18068C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final e2.n0 f18069D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final e2.l0 f18070E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e2.O f18071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18072b;
    public final B1 c;
    public final e2.V d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e2.V f18073e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f18074f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e2.P f18075g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f18076h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f18077i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final e2.f0 f18078j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f18079k;
    public final e2.q0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final e2.I f18080m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f18081n;
    public final C1195c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C1290c f18082p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C1202j f18083q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f18084r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final boolean f18085s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f18086t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f18087u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final boolean f18088v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final boolean f18089w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f18090x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f18091y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final e2.I f18092z;

    static {
        B1 b12 = B1.l;
        e2.V v10 = B1.f17722k;
        e2.P p10 = e2.P.d;
        e2.q0 q0Var = e2.q0.d;
        e2.b0 b0Var = e2.f0.f14097a;
        e2.I i7 = e2.I.f13914K;
        f18034F = new q1(null, 0, b12, v10, v10, 0, p10, 0, false, q0Var, b0Var, 0, i7, 1.0f, C1195c.f14046g, C1290c.c, C1202j.f14122e, 0, false, false, 1, 0, 1, false, false, i7, 5000L, 15000L, 3000L, e2.n0.f14225b, e2.l0.f14162C);
        int i10 = AbstractC1343A.f15195a;
        f18035G = Integer.toString(1, 36);
        f18036H = Integer.toString(2, 36);
        f18037I = Integer.toString(3, 36);
        f18038J = Integer.toString(4, 36);
        f18039K = Integer.toString(5, 36);
        f18040L = Integer.toString(6, 36);
        f18041M = Integer.toString(7, 36);
        f18042N = Integer.toString(8, 36);
        f18043O = Integer.toString(9, 36);
        f18044P = Integer.toString(10, 36);
        f18045Q = Integer.toString(11, 36);
        f18046R = Integer.toString(12, 36);
        f18047S = Integer.toString(13, 36);
        f18048T = Integer.toString(14, 36);
        f18049U = Integer.toString(15, 36);
        f18050V = Integer.toString(16, 36);
        f18051W = Integer.toString(17, 36);
        f18052X = Integer.toString(18, 36);
        f18053Y = Integer.toString(19, 36);
        f18054Z = Integer.toString(20, 36);
        f18055a0 = Integer.toString(21, 36);
        f18056b0 = Integer.toString(22, 36);
        f18057c0 = Integer.toString(23, 36);
        f18058d0 = Integer.toString(24, 36);
        f18059e0 = Integer.toString(25, 36);
        f18060f0 = Integer.toString(26, 36);
        f18061g0 = Integer.toString(27, 36);
        f18062h0 = Integer.toString(28, 36);
        f18063i0 = Integer.toString(29, 36);
        f18064j0 = Integer.toString(30, 36);
        f18065k0 = Integer.toString(31, 36);
        f18066l0 = Integer.toString(32, 36);
    }

    public q1(e2.O o, int i7, B1 b12, e2.V v10, e2.V v11, int i10, e2.P p10, int i11, boolean z6, e2.q0 q0Var, e2.f0 f0Var, int i12, e2.I i13, float f10, C1195c c1195c, C1290c c1290c, C1202j c1202j, int i14, boolean z10, boolean z11, int i15, int i16, int i17, boolean z12, boolean z13, e2.I i18, long j3, long j10, long j11, e2.n0 n0Var, e2.l0 l0Var) {
        this.f18071a = o;
        this.f18072b = i7;
        this.c = b12;
        this.d = v10;
        this.f18073e = v11;
        this.f18074f = i10;
        this.f18075g = p10;
        this.f18076h = i11;
        this.f18077i = z6;
        this.l = q0Var;
        this.f18078j = f0Var;
        this.f18079k = i12;
        this.f18080m = i13;
        this.f18081n = f10;
        this.o = c1195c;
        this.f18082p = c1290c;
        this.f18083q = c1202j;
        this.f18084r = i14;
        this.f18085s = z10;
        this.f18086t = z11;
        this.f18087u = i15;
        this.f18090x = i16;
        this.f18091y = i17;
        this.f18088v = z12;
        this.f18089w = z13;
        this.f18092z = i18;
        this.f18067A = j3;
        this.B = j10;
        this.f18068C = j11;
        this.f18069D = n0Var;
        this.f18070E = l0Var;
    }

    public static q1 r(int i7, Bundle bundle) {
        e2.O o;
        d5.d0 d0VarR;
        d5.d0 d0VarR2;
        e2.f0 d0Var;
        d5.d0 d0VarH;
        C1290c c1290c;
        C1202j c1202jC;
        e2.n0 n0Var;
        IBinder binder = bundle.getBinder(f18066l0);
        if (binder instanceof p1) {
            return ((p1) binder).c;
        }
        Bundle bundle2 = bundle.getBundle(f18052X);
        if (bundle2 == null) {
            o = null;
        } else {
            String string = bundle2.getString(e2.O.f13987q);
            String string2 = bundle2.getString(e2.O.f13988r);
            String string3 = bundle2.getString(e2.O.f13989s);
            if (!TextUtils.isEmpty(string2)) {
                try {
                    Class<?> cls = Class.forName(string2, true, e2.O.class.getClassLoader());
                    remoteException = Throwable.class.isAssignableFrom(cls) ? (Throwable) cls.getConstructor(String.class).newInstance(string3) : null;
                    if (remoteException == null) {
                        remoteException = new RemoteException(string3);
                    }
                } catch (Throwable unused) {
                    remoteException = new RemoteException(string3);
                }
            }
            Throwable th = remoteException;
            int i10 = bundle2.getInt(e2.O.o, 1000);
            Bundle bundle3 = bundle2.getBundle(e2.O.f13990t);
            if (bundle3 == null) {
                bundle3 = Bundle.EMPTY;
            }
            o = new e2.O(string, th, i10, bundle3, bundle2.getLong(e2.O.f13986p, SystemClock.elapsedRealtime()));
        }
        int i11 = bundle.getInt(f18054Z, 0);
        Bundle bundle4 = bundle.getBundle(f18053Y);
        B1 b1B = bundle4 == null ? B1.l : B1.b(bundle4);
        Bundle bundle5 = bundle.getBundle(f18055a0);
        e2.V vC = bundle5 == null ? B1.f17722k : e2.V.c(bundle5);
        Bundle bundle6 = bundle.getBundle(f18056b0);
        e2.V vC2 = bundle6 == null ? B1.f17722k : e2.V.c(bundle6);
        int i12 = bundle.getInt(f18057c0, 0);
        Bundle bundle7 = bundle.getBundle(f18035G);
        e2.P p10 = bundle7 == null ? e2.P.d : new e2.P(bundle7.getFloat(e2.P.f13993e, 1.0f), bundle7.getFloat(e2.P.f13994f, 1.0f));
        int i13 = bundle.getInt(f18036H, 0);
        boolean z6 = bundle.getBoolean(f18037I, false);
        Bundle bundle8 = bundle.getBundle(f18038J);
        if (bundle8 == null) {
            d0Var = e2.f0.f14097a;
        } else {
            C0074n c0074n = new C0074n(25);
            IBinder binder2 = bundle8.getBinder(e2.f0.f14098b);
            if (binder2 == null) {
                C1078G c1078g = d5.I.f13384m;
                d0VarR = d5.d0.f13415p;
            } else {
                d0VarR = AbstractC1346c.r(c0074n, BinderC1198f.a(binder2));
            }
            C0074n c0074n2 = new C0074n(26);
            IBinder binder3 = bundle8.getBinder(e2.f0.c);
            if (binder3 == null) {
                C1078G c1078g2 = d5.I.f13384m;
                d0VarR2 = d5.d0.f13415p;
            } else {
                d0VarR2 = AbstractC1346c.r(c0074n2, BinderC1198f.a(binder3));
            }
            int[] intArray = bundle8.getIntArray(e2.f0.d);
            if (intArray == null) {
                int i14 = d0VarR.o;
                int[] iArr = new int[i14];
                for (int i15 = 0; i15 < i14; i15++) {
                    iArr[i15] = i15;
                }
                intArray = iArr;
            }
            d0Var = new e2.d0(d0VarR, d0VarR2, intArray);
        }
        int i16 = bundle.getInt(f18065k0, 0);
        Bundle bundle9 = bundle.getBundle(f18039K);
        e2.q0 q0Var = bundle9 == null ? e2.q0.d : new e2.q0(bundle9.getInt(e2.q0.f14329e, 0), bundle9.getInt(e2.q0.f14330f, 0), bundle9.getFloat(e2.q0.f14331g, 1.0f));
        Bundle bundle10 = bundle.getBundle(f18040L);
        e2.I iB = bundle10 == null ? e2.I.f13914K : e2.I.b(bundle10);
        float f10 = bundle.getFloat(f18041M, 1.0f);
        Bundle bundle11 = bundle.getBundle(f18042N);
        C1195c c1195cA = bundle11 == null ? C1195c.f14046g : C1195c.a(bundle11);
        Bundle bundle12 = bundle.getBundle(f18058d0);
        if (bundle12 == null) {
            c1290c = C1290c.c;
        } else {
            ArrayList parcelableArrayList = bundle12.getParcelableArrayList(C1290c.d);
            if (parcelableArrayList == null) {
                d0VarH = d5.d0.f13415p;
            } else {
                C1077F c1077fU = d5.I.u();
                for (int i17 = 0; i17 < parcelableArrayList.size(); i17++) {
                    Bundle bundle13 = (Bundle) parcelableArrayList.get(i17);
                    bundle13.getClass();
                    c1077fU.a(C1289b.b(bundle13));
                }
                d0VarH = c1077fU.h();
            }
            c1290c = new C1290c(d0VarH, bundle12.getLong(C1290c.f14800e));
        }
        C1290c c1290c2 = c1290c;
        Bundle bundle14 = bundle.getBundle(f18043O);
        if (bundle14 == null) {
            c1202jC = C1202j.f14122e;
        } else {
            int i18 = bundle14.getInt(C1202j.f14123f, 0);
            int i19 = bundle14.getInt(C1202j.f14124g, 0);
            int i20 = bundle14.getInt(C1202j.f14125h, 0);
            String string4 = bundle14.getString(C1202j.f14126i);
            E5.z zVar = new E5.z(i18);
            zVar.c = i19;
            zVar.d = i20;
            AbstractC1346c.c(i18 != 0 || string4 == null);
            zVar.f1842e = string4;
            c1202jC = zVar.c();
        }
        C1202j c1202j = c1202jC;
        int i21 = bundle.getInt(f18044P, 0);
        boolean z10 = bundle.getBoolean(f18045Q, false);
        boolean z11 = bundle.getBoolean(f18046R, false);
        int i22 = bundle.getInt(f18047S, 1);
        int i23 = bundle.getInt(f18048T, 0);
        int i24 = bundle.getInt(f18049U, 1);
        boolean z12 = bundle.getBoolean(f18050V, false);
        boolean z13 = bundle.getBoolean(f18051W, false);
        Bundle bundle15 = bundle.getBundle(f18059e0);
        e2.I iB2 = bundle15 == null ? e2.I.f13914K : e2.I.b(bundle15);
        long j3 = bundle.getLong(f18060f0, i7 < 4 ? 0L : 5000L);
        long j10 = bundle.getLong(f18061g0, i7 < 4 ? 0L : 15000L);
        long j11 = bundle.getLong(f18062h0, i7 >= 4 ? 3000L : 0L);
        Bundle bundle16 = bundle.getBundle(f18064j0);
        if (bundle16 == null) {
            n0Var = e2.n0.f14225b;
        } else {
            ArrayList parcelableArrayList2 = bundle16.getParcelableArrayList(e2.n0.c);
            n0Var = new e2.n0(parcelableArrayList2 == null ? d5.d0.f13415p : AbstractC1346c.r(new C0074n(29), parcelableArrayList2));
        }
        Bundle bundle17 = bundle.getBundle(f18063i0);
        return new q1(o, i11, b1B, vC, vC2, i12, p10, i13, z6, q0Var, d0Var, i16, iB, f10, c1195cA, c1290c2, c1202j, i21, z10, z11, i22, i23, i24, z12, z13, iB2, j3, j10, j11, n0Var, bundle17 == null ? e2.l0.f14162C : e2.l0.b(bundle17));
    }

    public final q1 a(C1195c c1195c) {
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, c1195c, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 b(e2.n0 n0Var) {
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, n0Var, this.f18070E);
    }

    public final q1 c(int i7, boolean z6) {
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, i7, z6, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 d(int i7, int i10, boolean z6) {
        boolean z10 = this.f18091y == 3 && z6 && i10 == 0;
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, z6, i7, i10, this.f18091y, z10, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 e(e2.P p10) {
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, p10, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 f(int i7, e2.O o) {
        boolean z6 = i7 == 3 && this.f18086t && this.f18090x == 0;
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(o, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, i7, z6, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 g(e2.I i7) {
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, i7, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 h(int i7, e2.V v10, e2.V v11) {
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, v10, v11, i7, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 i(int i7) {
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, i7, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 j(B1 b12) {
        e2.f0 f0Var = this.f18078j;
        AbstractC1346c.j(f0Var.p() || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 k(boolean z6) {
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, z6, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 l(e2.f0 f0Var) {
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 m(w1 w1Var, int i7) {
        B1 b12 = this.c;
        e2.V v10 = b12.f17732a;
        B1 b13 = new B1(new e2.V(v10.f14007a, i7, v10.c, v10.d, v10.f14009e, v10.f14010f, v10.f14011g, v10.f14012h, v10.f14013i), b12.f17733b, b12.c, b12.d, b12.f17734e, b12.f17735f, b12.f17736g, b12.f17737h, b12.f17738i, b12.f17739j);
        AbstractC1346c.j(w1Var.p() || b13.f17732a.f14008b < w1Var.o());
        return new q1(this.f18071a, this.f18072b, b13, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, w1Var, 0, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 n(e2.f0 f0Var, B1 b12, int i7) {
        AbstractC1346c.j(f0Var.p() || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, i7, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    public final q1 o(e2.l0 l0Var) {
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, this.f18081n, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, l0Var);
    }

    public final q1 p(float f10) {
        e2.f0 f0Var = this.f18078j;
        boolean zP = f0Var.p();
        B1 b12 = this.c;
        AbstractC1346c.j(zP || b12.f17732a.f14008b < f0Var.o());
        return new q1(this.f18071a, this.f18072b, b12, this.d, this.f18073e, this.f18074f, this.f18075g, this.f18076h, this.f18077i, this.l, f0Var, this.f18079k, this.f18080m, f10, this.o, this.f18082p, this.f18083q, this.f18084r, this.f18085s, this.f18086t, this.f18087u, this.f18090x, this.f18091y, this.f18088v, this.f18089w, this.f18092z, this.f18067A, this.B, this.f18068C, this.f18069D, this.f18070E);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final k3.q1 q(e2.S r41, boolean r42, boolean r43) {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k3.q1.q(e2.S, boolean, boolean):k3.q1");
    }

    public final e2.F s() {
        e2.f0 f0Var = this.f18078j;
        if (f0Var.p()) {
            return null;
        }
        return f0Var.m(this.c.f17732a.f14008b, new e2.e0(), 0L).c;
    }

    public final Bundle t(int i7) {
        long j3;
        long j10;
        int i10;
        Bundle bundle;
        int i11;
        e2.c0 c0Var;
        long j11;
        e2.F[] fArr;
        Bundle bundleD;
        Bundle bundle2 = new Bundle();
        e2.O o = this.f18071a;
        if (o != null) {
            bundle2.putBundle(f18052X, o.b());
        }
        int i12 = this.f18072b;
        if (i12 != 0) {
            bundle2.putInt(f18054Z, i12);
        }
        B1 b12 = this.c;
        if (i7 < 3 || !b12.equals(B1.l)) {
            bundle2.putBundle(f18053Y, b12.c(i7));
        }
        e2.V v10 = this.d;
        if (i7 < 3 || !B1.f17722k.a(v10)) {
            bundle2.putBundle(f18055a0, v10.d(i7));
        }
        e2.V v11 = this.f18073e;
        if (i7 < 3 || !B1.f17722k.a(v11)) {
            bundle2.putBundle(f18056b0, v11.d(i7));
        }
        int i13 = this.f18074f;
        if (i13 != 0) {
            bundle2.putInt(f18057c0, i13);
        }
        e2.P p10 = e2.P.d;
        e2.P p11 = this.f18075g;
        if (!p11.equals(p10)) {
            Bundle bundle3 = new Bundle();
            bundle3.putFloat(e2.P.f13993e, p11.f13995a);
            bundle3.putFloat(e2.P.f13994f, p11.f13996b);
            bundle2.putBundle(f18035G, bundle3);
        }
        int i14 = this.f18076h;
        if (i14 != 0) {
            bundle2.putInt(f18036H, i14);
        }
        boolean z6 = this.f18077i;
        if (z6) {
            bundle2.putBoolean(f18037I, z6);
        }
        e2.b0 b0Var = e2.f0.f14097a;
        e2.f0 f0Var = this.f18078j;
        boolean z10 = false;
        long j12 = 0;
        if (f0Var.equals(b0Var)) {
            j3 = 0;
        } else {
            ArrayList arrayList = new ArrayList();
            int iO = f0Var.o();
            e2.e0 e0Var = new e2.e0();
            int i15 = 0;
            while (true) {
                j10 = -9223372036854775807L;
                if (i15 >= iO) {
                    break;
                }
                e2.e0 e0VarM = f0Var.m(i15, e0Var, j12);
                e0VarM.getClass();
                Bundle bundle4 = new Bundle();
                if (!e2.F.f13872g.equals(e0VarM.c)) {
                    bundle4.putBundle(e2.e0.f14078t, e0VarM.c.d(false));
                }
                long j13 = e0VarM.f14087e;
                if (j13 != -9223372036854775807L) {
                    bundle4.putLong(e2.e0.f14079u, j13);
                }
                long j14 = e0VarM.f14088f;
                if (j14 != -9223372036854775807L) {
                    bundle4.putLong(e2.e0.f14080v, j14);
                }
                long j15 = e0VarM.f14089g;
                if (j15 != -9223372036854775807L) {
                    bundle4.putLong(e2.e0.f14081w, j15);
                }
                boolean z11 = e0VarM.f14090h;
                if (z11) {
                    bundle4.putBoolean(e2.e0.f14082x, z11);
                }
                boolean z12 = e0VarM.f14091i;
                if (z12) {
                    bundle4.putBoolean(e2.e0.f14083y, z12);
                }
                C1217z c1217z = e0VarM.f14092j;
                if (c1217z != null) {
                    bundle4.putBundle(e2.e0.f14084z, c1217z.c());
                }
                boolean z13 = e0VarM.f14093k;
                if (z13) {
                    bundle4.putBoolean(e2.e0.f14070A, z13);
                }
                long j16 = e0VarM.l;
                if (j16 != 0) {
                    bundle4.putLong(e2.e0.B, j16);
                }
                long j17 = e0VarM.f14094m;
                if (j17 != -9223372036854775807L) {
                    bundle4.putLong(e2.e0.f14071C, j17);
                }
                int i16 = e0VarM.f14095n;
                if (i16 != 0) {
                    bundle4.putInt(e2.e0.f14072D, i16);
                }
                int i17 = e0VarM.o;
                if (i17 != 0) {
                    bundle4.putInt(e2.e0.f14073E, i17);
                }
                long j18 = e0VarM.f14096p;
                if (j18 != 0) {
                    bundle4.putLong(e2.e0.f14074F, j18);
                }
                arrayList.add(bundle4);
                i15++;
                j12 = 0;
            }
            ArrayList arrayList2 = new ArrayList();
            int iH = f0Var.h();
            e2.c0 c0Var2 = new e2.c0();
            int i18 = 0;
            while (i18 < iH) {
                e2.c0 c0VarF = f0Var.f(i18, c0Var2, z10);
                c0VarF.getClass();
                Bundle bundle5 = new Bundle();
                int i19 = c0VarF.c;
                if (i19 != 0) {
                    bundle5.putInt(e2.c0.f14055h, i19);
                }
                int i20 = iH;
                e2.c0 c0Var3 = c0Var2;
                long j19 = c0VarF.d;
                if (j19 != j10) {
                    bundle5.putLong(e2.c0.f14056i, j19);
                }
                long j20 = c0VarF.f14061e;
                if (j20 != 0) {
                    bundle5.putLong(e2.c0.f14057j, j20);
                }
                boolean z14 = c0VarF.f14062f;
                if (z14) {
                    bundle5.putBoolean(e2.c0.f14058k, z14);
                }
                if (c0VarF.f14063g.equals(C1194b.f14037f)) {
                    bundle = bundle2;
                    i11 = i20;
                    c0Var = c0Var3;
                    j11 = -9223372036854775807L;
                } else {
                    C1194b c1194b = c0VarF.f14063g;
                    c1194b.getClass();
                    Bundle bundle6 = new Bundle();
                    ArrayList<? extends Parcelable> arrayList3 = new ArrayList<>();
                    C1193a[] c1193aArr = c1194b.f14045e;
                    int length = c1193aArr.length;
                    i11 = i20;
                    int i21 = 0;
                    while (i21 < length) {
                        int i22 = length;
                        C1193a c1193a = c1193aArr[i21];
                        c1193a.getClass();
                        C1193a[] c1193aArr2 = c1193aArr;
                        Bundle bundle7 = new Bundle();
                        e2.c0 c0Var4 = c0Var3;
                        bundle7.putLong(C1193a.f14021j, c1193a.f14028a);
                        bundle7.putInt(C1193a.f14022k, c1193a.f14029b);
                        bundle7.putInt(C1193a.f14026q, c1193a.c);
                        bundle7.putParcelableArrayList(C1193a.l, new ArrayList<>(Arrays.asList(c1193a.d)));
                        ArrayList<? extends Parcelable> arrayList4 = new ArrayList<>();
                        e2.F[] fArr2 = c1193a.f14030e;
                        int length2 = fArr2.length;
                        Bundle bundle8 = bundle2;
                        int i23 = 0;
                        while (i23 < length2) {
                            int i24 = length2;
                            e2.F f10 = fArr2[i23];
                            if (f10 == null) {
                                bundleD = null;
                                fArr = fArr2;
                            } else {
                                fArr = fArr2;
                                bundleD = f10.d(true);
                            }
                            arrayList4.add(bundleD);
                            i23++;
                            length2 = i24;
                            fArr2 = fArr;
                        }
                        bundle7.putParcelableArrayList(C1193a.f14027r, arrayList4);
                        bundle7.putIntArray(C1193a.f14023m, c1193a.f14031f);
                        bundle7.putLongArray(C1193a.f14024n, c1193a.f14032g);
                        bundle7.putLong(C1193a.o, c1193a.f14033h);
                        bundle7.putBoolean(C1193a.f14025p, c1193a.f14034i);
                        arrayList3.add(bundle7);
                        i21++;
                        length = i22;
                        c1193aArr = c1193aArr2;
                        c0Var3 = c0Var4;
                        bundle2 = bundle8;
                    }
                    bundle = bundle2;
                    c0Var = c0Var3;
                    if (!arrayList3.isEmpty()) {
                        bundle6.putParcelableArrayList(C1194b.f14039h, arrayList3);
                    }
                    long j21 = c1194b.f14044b;
                    if (j21 != 0) {
                        bundle6.putLong(C1194b.f14040i, j21);
                    }
                    long j22 = c1194b.c;
                    j11 = -9223372036854775807L;
                    if (j22 != -9223372036854775807L) {
                        bundle6.putLong(C1194b.f14041j, j22);
                    }
                    int i25 = c1194b.d;
                    if (i25 != 0) {
                        bundle6.putInt(C1194b.f14042k, i25);
                    }
                    bundle5.putBundle(e2.c0.l, bundle6);
                }
                arrayList2.add(bundle5);
                i18++;
                j10 = j11;
                iH = i11;
                c0Var2 = c0Var;
                bundle2 = bundle;
                z10 = false;
            }
            Bundle bundle9 = bundle2;
            j3 = 0;
            int[] iArr = new int[iO];
            boolean z15 = true;
            if (iO > 0) {
                i10 = 0;
                iArr[0] = f0Var.a(true);
            } else {
                i10 = 0;
            }
            int i26 = 1;
            while (i26 < iO) {
                iArr[i26] = f0Var.e(iArr[i26 - 1], i10, z15);
                i26++;
                z15 = true;
                i10 = 0;
            }
            Bundle bundle10 = new Bundle();
            bundle10.putBinder(e2.f0.f14098b, new BinderC1198f(arrayList));
            bundle10.putBinder(e2.f0.c, new BinderC1198f(arrayList2));
            bundle10.putIntArray(e2.f0.d, iArr);
            bundle2 = bundle9;
            bundle2.putBundle(f18038J, bundle10);
        }
        int i27 = this.f18079k;
        if (i27 != 0) {
            bundle2.putInt(f18065k0, i27);
        }
        e2.q0 q0Var = e2.q0.d;
        e2.q0 q0Var2 = this.l;
        if (!q0Var2.equals(q0Var)) {
            Bundle bundle11 = new Bundle();
            int i28 = q0Var2.f14332a;
            if (i28 != 0) {
                bundle11.putInt(e2.q0.f14329e, i28);
            }
            int i29 = q0Var2.f14333b;
            if (i29 != 0) {
                bundle11.putInt(e2.q0.f14330f, i29);
            }
            float f11 = q0Var2.c;
            if (f11 != 1.0f) {
                bundle11.putFloat(e2.q0.f14331g, f11);
            }
            bundle2.putBundle(f18039K, bundle11);
        }
        e2.I i30 = e2.I.f13914K;
        e2.I i31 = this.f18080m;
        if (!i31.equals(i30)) {
            bundle2.putBundle(f18040L, i31.c());
        }
        float f12 = this.f18081n;
        if (f12 != 1.0f) {
            bundle2.putFloat(f18041M, f12);
        }
        C1195c c1195c = C1195c.f14046g;
        C1195c c1195c2 = this.o;
        if (!c1195c2.equals(c1195c)) {
            bundle2.putBundle(f18042N, c1195c2.c());
        }
        C1290c c1290c = C1290c.c;
        C1290c c1290c2 = this.f18082p;
        if (!c1290c2.equals(c1290c)) {
            Bundle bundle12 = new Bundle();
            C1077F c1077fU = d5.I.u();
            int i32 = 0;
            while (true) {
                d5.I i33 = c1290c2.f14801a;
                if (i32 >= i33.size()) {
                    break;
                }
                if (((C1289b) i33.get(i32)).d == null) {
                    c1077fU.a((C1289b) i33.get(i32));
                }
                i32++;
            }
            d5.d0 d0VarH = c1077fU.h();
            ArrayList<? extends Parcelable> arrayList5 = new ArrayList<>(d0VarH.o);
            C1078G c1078gListIterator = d0VarH.listIterator(0);
            while (c1078gListIterator.hasNext()) {
                C1289b c1289b = (C1289b) c1078gListIterator.next();
                Bundle bundleC = c1289b.c();
                Bitmap bitmap = c1289b.d;
                if (bitmap != null) {
                    bundleC.putParcelable(C1289b.f14782v, bitmap);
                }
                arrayList5.add(bundleC);
            }
            bundle12.putParcelableArrayList(C1290c.d, arrayList5);
            bundle12.putLong(C1290c.f14800e, c1290c2.f14802b);
            bundle2.putBundle(f18058d0, bundle12);
        }
        C1202j c1202j = C1202j.f14122e;
        C1202j c1202j2 = this.f18083q;
        if (!c1202j2.equals(c1202j)) {
            Bundle bundle13 = new Bundle();
            int i34 = c1202j2.f14127a;
            if (i34 != 0) {
                bundle13.putInt(C1202j.f14123f, i34);
            }
            int i35 = c1202j2.f14128b;
            if (i35 != 0) {
                bundle13.putInt(C1202j.f14124g, i35);
            }
            int i36 = c1202j2.c;
            if (i36 != 0) {
                bundle13.putInt(C1202j.f14125h, i36);
            }
            String str = c1202j2.d;
            if (str != null) {
                bundle13.putString(C1202j.f14126i, str);
            }
            bundle2.putBundle(f18043O, bundle13);
        }
        int i37 = this.f18084r;
        if (i37 != 0) {
            bundle2.putInt(f18044P, i37);
        }
        boolean z16 = this.f18085s;
        if (z16) {
            bundle2.putBoolean(f18045Q, z16);
        }
        boolean z17 = this.f18086t;
        if (z17) {
            bundle2.putBoolean(f18046R, z17);
        }
        int i38 = this.f18087u;
        if (i38 != 1) {
            bundle2.putInt(f18047S, i38);
        }
        int i39 = this.f18090x;
        if (i39 != 0) {
            bundle2.putInt(f18048T, i39);
        }
        int i40 = this.f18091y;
        if (i40 != 1) {
            bundle2.putInt(f18049U, i40);
        }
        boolean z18 = this.f18088v;
        if (z18) {
            bundle2.putBoolean(f18050V, z18);
        }
        boolean z19 = this.f18089w;
        if (z19) {
            bundle2.putBoolean(f18051W, z19);
        }
        e2.I i41 = e2.I.f13914K;
        e2.I i42 = this.f18092z;
        if (!i42.equals(i41)) {
            bundle2.putBundle(f18059e0, i42.c());
        }
        long j23 = i7 < 6 ? j3 : 5000L;
        long j24 = this.f18067A;
        if (j24 != j23) {
            bundle2.putLong(f18060f0, j24);
        }
        long j25 = i7 < 6 ? j3 : 15000L;
        long j26 = this.B;
        if (j26 != j25) {
            bundle2.putLong(f18061g0, j26);
        }
        long j27 = i7 < 6 ? j3 : 3000L;
        long j28 = this.f18068C;
        if (j28 != j27) {
            bundle2.putLong(f18062h0, j28);
        }
        e2.n0 n0Var = e2.n0.f14225b;
        e2.n0 n0Var2 = this.f18069D;
        if (!n0Var2.equals(n0Var)) {
            Bundle bundle14 = new Bundle();
            bundle14.putParcelableArrayList(e2.n0.c, AbstractC1346c.y(n0Var2.f14226a, new C0074n(28)));
            bundle2.putBundle(f18064j0, bundle14);
        }
        e2.l0 l0Var = e2.l0.f14162C;
        e2.l0 l0Var2 = this.f18070E;
        if (!l0Var2.equals(l0Var)) {
            bundle2.putBundle(f18063i0, l0Var2.c());
        }
        return bundle2;
    }
}
