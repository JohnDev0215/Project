package e2;

import android.os.Bundle;
import android.os.Parcelable;
import d5.AbstractC1075D;
import d5.AbstractC1095q;
import d5.C1077F;
import h2.AbstractC1343A;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public class l0 {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final l0 f14162C = new l0(new k0());

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final String f14163D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final String f14164E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final String f14165F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final String f14166G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final String f14167H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final String f14168I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final String f14169J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final String f14170K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public static final String f14171L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final String f14172M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final String f14173N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public static final String f14174O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public static final String f14175P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public static final String f14176Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public static final String f14177R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public static final String f14178S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public static final String f14179T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public static final String f14180U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public static final String f14181V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public static final String f14182W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public static final String f14183X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public static final String f14184Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public static final String f14185Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public static final String f14186a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public static final String f14187b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public static final String f14188c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public static final String f14189d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public static final String f14190e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public static final String f14191f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public static final String f14192g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static final String f14193h0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final d5.K f14194A;
    public final d5.M B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14195a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14196b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f14197e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f14198f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f14199g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f14200h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f14201i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f14202j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f14203k;
    public final d5.d0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f14204m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final d5.d0 f14205n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f14206p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f14207q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final d5.d0 f14208r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final j0 f14209s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final d5.d0 f14210t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f14211u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f14212v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final boolean f14213w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final boolean f14214x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final boolean f14215y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final boolean f14216z;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14163D = Integer.toString(1, 36);
        f14164E = Integer.toString(2, 36);
        f14165F = Integer.toString(3, 36);
        f14166G = Integer.toString(4, 36);
        f14167H = Integer.toString(5, 36);
        f14168I = Integer.toString(6, 36);
        f14169J = Integer.toString(7, 36);
        f14170K = Integer.toString(8, 36);
        f14171L = Integer.toString(9, 36);
        f14172M = Integer.toString(10, 36);
        f14173N = Integer.toString(11, 36);
        f14174O = Integer.toString(12, 36);
        f14175P = Integer.toString(13, 36);
        f14176Q = Integer.toString(14, 36);
        f14177R = Integer.toString(15, 36);
        f14178S = Integer.toString(16, 36);
        f14179T = Integer.toString(17, 36);
        f14180U = Integer.toString(18, 36);
        f14181V = Integer.toString(19, 36);
        f14182W = Integer.toString(20, 36);
        f14183X = Integer.toString(21, 36);
        f14184Y = Integer.toString(22, 36);
        f14185Z = Integer.toString(23, 36);
        f14186a0 = Integer.toString(24, 36);
        f14187b0 = Integer.toString(25, 36);
        f14188c0 = Integer.toString(26, 36);
        f14189d0 = Integer.toString(27, 36);
        f14190e0 = Integer.toString(28, 36);
        f14191f0 = Integer.toString(29, 36);
        f14192g0 = Integer.toString(30, 36);
        f14193h0 = Integer.toString(31, 36);
    }

    public l0(k0 k0Var) {
        this.f14195a = k0Var.f14138a;
        this.f14196b = k0Var.f14139b;
        this.c = k0Var.c;
        this.d = k0Var.d;
        this.f14197e = k0Var.f14140e;
        this.f14198f = k0Var.f14141f;
        this.f14199g = k0Var.f14142g;
        this.f14200h = k0Var.f14143h;
        this.f14201i = k0Var.f14144i;
        this.f14202j = k0Var.f14145j;
        this.f14203k = k0Var.f14146k;
        this.l = k0Var.l;
        this.f14204m = k0Var.f14147m;
        this.f14205n = k0Var.f14148n;
        this.o = k0Var.o;
        this.f14206p = k0Var.f14149p;
        this.f14207q = k0Var.f14150q;
        this.f14208r = k0Var.f14151r;
        this.f14209s = k0Var.f14152s;
        this.f14210t = k0Var.f14153t;
        this.f14211u = k0Var.f14154u;
        this.f14212v = k0Var.f14155v;
        this.f14213w = k0Var.f14156w;
        this.f14214x = k0Var.f14157x;
        this.f14215y = k0Var.f14158y;
        this.f14216z = k0Var.f14159z;
        this.f14194A = d5.K.b(k0Var.f14137A);
        this.B = d5.M.v(k0Var.B);
    }

    public static l0 b(Bundle bundle) {
        j0 j0Var;
        d5.d0 d0VarH;
        k0 k0Var = new k0();
        l0 l0Var = f14162C;
        k0Var.f14138a = bundle.getInt(f14168I, l0Var.f14195a);
        k0Var.f14139b = bundle.getInt(f14169J, l0Var.f14196b);
        k0Var.c = bundle.getInt(f14170K, l0Var.c);
        k0Var.d = bundle.getInt(f14171L, l0Var.d);
        k0Var.f14140e = bundle.getInt(f14172M, l0Var.f14197e);
        k0Var.f14141f = bundle.getInt(f14173N, l0Var.f14198f);
        k0Var.f14142g = bundle.getInt(f14174O, l0Var.f14199g);
        k0Var.f14143h = bundle.getInt(f14175P, l0Var.f14200h);
        k0Var.f14144i = bundle.getInt(f14176Q, l0Var.f14201i);
        k0Var.f14145j = bundle.getInt(f14177R, l0Var.f14202j);
        k0Var.f14146k = bundle.getBoolean(f14178S, l0Var.f14203k);
        String[] stringArray = bundle.getStringArray(f14179T);
        String[] strArr = new String[0];
        if (stringArray == null) {
            stringArray = strArr;
        }
        k0Var.l = d5.I.x(stringArray);
        k0Var.f14147m = bundle.getInt(f14187b0, l0Var.f14204m);
        String[] stringArray2 = bundle.getStringArray(f14163D);
        String[] strArr2 = new String[0];
        if (stringArray2 == null) {
            stringArray2 = strArr2;
        }
        k0Var.f14148n = k0.f(stringArray2);
        k0Var.o = bundle.getInt(f14164E, l0Var.o);
        k0Var.f14149p = bundle.getInt(f14180U, l0Var.f14206p);
        k0Var.f14150q = bundle.getInt(f14181V, l0Var.f14207q);
        String[] stringArray3 = bundle.getStringArray(f14182W);
        String[] strArr3 = new String[0];
        if (stringArray3 == null) {
            stringArray3 = strArr3;
        }
        k0Var.f14151r = d5.I.x(stringArray3);
        Bundle bundle2 = bundle.getBundle(f14192g0);
        if (bundle2 != null) {
            i0 i0Var = new i0();
            j0 j0Var2 = j0.d;
            i0Var.f14120a = bundle2.getInt(j0.f14129e, j0Var2.f14132a);
            i0Var.f14121b = bundle2.getBoolean(j0.f14130f, j0Var2.f14133b);
            i0Var.c = bundle2.getBoolean(j0.f14131g, j0Var2.c);
            j0Var = new j0(i0Var);
        } else {
            i0 i0Var2 = new i0();
            j0 j0Var3 = j0.d;
            i0Var2.f14120a = bundle.getInt(f14189d0, j0Var3.f14132a);
            i0Var2.f14121b = bundle.getBoolean(f14190e0, j0Var3.f14133b);
            i0Var2.c = bundle.getBoolean(f14191f0, j0Var3.c);
            j0Var = new j0(i0Var2);
        }
        k0Var.f14152s = j0Var;
        String[] stringArray4 = bundle.getStringArray(f14165F);
        String[] strArr4 = new String[0];
        if (stringArray4 == null) {
            stringArray4 = strArr4;
        }
        k0Var.f14153t = k0.f(stringArray4);
        k0Var.f14154u = bundle.getInt(f14166G, l0Var.f14211u);
        k0Var.f14155v = bundle.getInt(f14188c0, l0Var.f14212v);
        k0Var.f14156w = bundle.getBoolean(f14167H, l0Var.f14213w);
        k0Var.f14157x = bundle.getBoolean(f14193h0, l0Var.f14214x);
        k0Var.f14158y = bundle.getBoolean(f14183X, l0Var.f14215y);
        k0Var.f14159z = bundle.getBoolean(f14184Y, l0Var.f14216z);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(f14185Z);
        if (parcelableArrayList == null) {
            d0VarH = d5.d0.f13415p;
        } else {
            C1077F c1077fU = d5.I.u();
            for (int i7 = 0; i7 < parcelableArrayList.size(); i7++) {
                Bundle bundle3 = (Bundle) parcelableArrayList.get(i7);
                bundle3.getClass();
                Bundle bundle4 = bundle3.getBundle(h0.c);
                bundle4.getClass();
                g0 g0VarB = g0.b(bundle4);
                int[] intArray = bundle3.getIntArray(h0.d);
                intArray.getClass();
                c1077fU.a(new h0(g0VarB, AbstractC2673a.o(intArray)));
            }
            d0VarH = c1077fU.h();
        }
        k0Var.f14137A = new HashMap();
        for (int i10 = 0; i10 < d0VarH.o; i10++) {
            h0 h0Var = (h0) d0VarH.get(i10);
            k0Var.f14137A.put(h0Var.f14118a, h0Var);
        }
        int[] intArray2 = bundle.getIntArray(f14186a0);
        int[] iArr = new int[0];
        if (intArray2 == null) {
            intArray2 = iArr;
        }
        k0Var.B = new HashSet();
        for (int i11 : intArray2) {
            k0Var.B.add(Integer.valueOf(i11));
        }
        return new l0(k0Var);
    }

    public k0 a() {
        k0 k0Var = new k0();
        k0Var.e(this);
        return k0Var;
    }

    public Bundle c() {
        Bundle bundle = new Bundle();
        bundle.putInt(f14168I, this.f14195a);
        bundle.putInt(f14169J, this.f14196b);
        bundle.putInt(f14170K, this.c);
        bundle.putInt(f14171L, this.d);
        bundle.putInt(f14172M, this.f14197e);
        bundle.putInt(f14173N, this.f14198f);
        bundle.putInt(f14174O, this.f14199g);
        bundle.putInt(f14175P, this.f14200h);
        bundle.putInt(f14176Q, this.f14201i);
        bundle.putInt(f14177R, this.f14202j);
        bundle.putBoolean(f14178S, this.f14203k);
        bundle.putStringArray(f14179T, (String[]) this.l.toArray(new String[0]));
        bundle.putInt(f14187b0, this.f14204m);
        bundle.putStringArray(f14163D, (String[]) this.f14205n.toArray(new String[0]));
        bundle.putInt(f14164E, this.o);
        bundle.putInt(f14180U, this.f14206p);
        bundle.putInt(f14181V, this.f14207q);
        bundle.putStringArray(f14182W, (String[]) this.f14208r.toArray(new String[0]));
        bundle.putStringArray(f14165F, (String[]) this.f14210t.toArray(new String[0]));
        bundle.putInt(f14166G, this.f14211u);
        bundle.putInt(f14188c0, this.f14212v);
        bundle.putBoolean(f14167H, this.f14213w);
        j0 j0Var = this.f14209s;
        bundle.putInt(f14189d0, j0Var.f14132a);
        String str = f14190e0;
        boolean z6 = j0Var.f14133b;
        bundle.putBoolean(str, z6);
        String str2 = f14191f0;
        boolean z10 = j0Var.c;
        bundle.putBoolean(str2, z10);
        Bundle bundle2 = new Bundle();
        bundle2.putInt(j0.f14129e, j0Var.f14132a);
        bundle2.putBoolean(j0.f14130f, z6);
        bundle2.putBoolean(j0.f14131g, z10);
        bundle.putBundle(f14192g0, bundle2);
        bundle.putBoolean(f14193h0, this.f14214x);
        bundle.putBoolean(f14183X, this.f14215y);
        bundle.putBoolean(f14184Y, this.f14216z);
        AbstractC1075D<h0> abstractC1075DValues = this.f14194A.values();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(abstractC1075DValues.size());
        for (h0 h0Var : abstractC1075DValues) {
            h0Var.getClass();
            Bundle bundle3 = new Bundle();
            bundle3.putBundle(h0.c, h0Var.f14118a.d());
            bundle3.putIntArray(h0.d, AbstractC2673a.Z(h0Var.f14119b));
            arrayList.add(bundle3);
        }
        bundle.putParcelableArrayList(f14185Z, arrayList);
        bundle.putIntArray(f14186a0, AbstractC2673a.Z(this.B));
        return bundle;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        l0 l0Var = (l0) obj;
        if (this.f14195a == l0Var.f14195a && this.f14196b == l0Var.f14196b && this.c == l0Var.c && this.d == l0Var.d && this.f14197e == l0Var.f14197e && this.f14198f == l0Var.f14198f && this.f14199g == l0Var.f14199g && this.f14200h == l0Var.f14200h && this.f14203k == l0Var.f14203k && this.f14201i == l0Var.f14201i && this.f14202j == l0Var.f14202j && this.l.equals(l0Var.l) && this.f14204m == l0Var.f14204m && this.f14205n.equals(l0Var.f14205n) && this.o == l0Var.o && this.f14206p == l0Var.f14206p && this.f14207q == l0Var.f14207q && this.f14208r.equals(l0Var.f14208r) && this.f14209s.equals(l0Var.f14209s) && this.f14210t.equals(l0Var.f14210t) && this.f14211u == l0Var.f14211u && this.f14212v == l0Var.f14212v && this.f14213w == l0Var.f14213w && this.f14214x == l0Var.f14214x && this.f14215y == l0Var.f14215y && this.f14216z == l0Var.f14216z) {
            d5.K k10 = this.f14194A;
            k10.getClass();
            if (AbstractC1095q.i(l0Var.f14194A, k10) && this.B.equals(l0Var.B)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.B.hashCode() + ((this.f14194A.hashCode() + ((((((((((((((this.f14210t.hashCode() + ((this.f14209s.hashCode() + ((this.f14208r.hashCode() + ((((((((this.f14205n.hashCode() + ((((this.l.hashCode() + ((((((((((((((((((((((this.f14195a + 31) * 31) + this.f14196b) * 31) + this.c) * 31) + this.d) * 31) + this.f14197e) * 31) + this.f14198f) * 31) + this.f14199g) * 31) + this.f14200h) * 31) + (this.f14203k ? 1 : 0)) * 31) + this.f14201i) * 31) + this.f14202j) * 31)) * 31) + this.f14204m) * 31)) * 31) + this.o) * 31) + this.f14206p) * 31) + this.f14207q) * 31)) * 31)) * 31)) * 31) + this.f14211u) * 31) + this.f14212v) * 31) + (this.f14213w ? 1 : 0)) * 31) + (this.f14214x ? 1 : 0)) * 31) + (this.f14215y ? 1 : 0)) * 31) + (this.f14216z ? 1 : 0)) * 31)) * 31);
    }
}
