package e2;

import a.AbstractC0904a;
import android.os.Bundle;
import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class V {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f14002j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f14003k;
    public static final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f14004m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f14005n;
    public static final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f14006p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f14007a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14008b;
    public final F c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f14009e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f14010f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f14011g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f14012h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f14013i;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14002j = Integer.toString(0, 36);
        f14003k = Integer.toString(1, 36);
        l = Integer.toString(2, 36);
        f14004m = Integer.toString(3, 36);
        f14005n = Integer.toString(4, 36);
        o = Integer.toString(5, 36);
        f14006p = Integer.toString(6, 36);
    }

    public V(Object obj, int i7, F f10, Object obj2, int i10, long j3, long j10, int i11, int i12) {
        this.f14007a = obj;
        this.f14008b = i7;
        this.c = f10;
        this.d = obj2;
        this.f14009e = i10;
        this.f14010f = j3;
        this.f14011g = j10;
        this.f14012h = i11;
        this.f14013i = i12;
    }

    public static V c(Bundle bundle) {
        int i7 = bundle.getInt(f14002j, 0);
        Bundle bundle2 = bundle.getBundle(f14003k);
        return new V(null, i7, bundle2 == null ? null : F.a(bundle2), null, bundle.getInt(l, 0), bundle.getLong(f14004m, 0L), bundle.getLong(f14005n, 0L), bundle.getInt(o, -1), bundle.getInt(f14006p, -1));
    }

    public final boolean a(V v10) {
        return this.f14008b == v10.f14008b && this.f14009e == v10.f14009e && this.f14010f == v10.f14010f && this.f14011g == v10.f14011g && this.f14012h == v10.f14012h && this.f14013i == v10.f14013i && AbstractC0904a.A(this.c, v10.c);
    }

    public final V b(boolean z6, boolean z10) {
        if (z6 && z10) {
            return this;
        }
        return new V(this.f14007a, z10 ? this.f14008b : 0, z6 ? this.c : null, this.d, z10 ? this.f14009e : 0, z6 ? this.f14010f : 0L, z6 ? this.f14011g : 0L, z6 ? this.f14012h : -1, z6 ? this.f14013i : -1);
    }

    public final Bundle d(int i7) {
        Bundle bundle = new Bundle();
        int i10 = this.f14008b;
        if (i7 < 3 || i10 != 0) {
            bundle.putInt(f14002j, i10);
        }
        F f10 = this.c;
        if (f10 != null) {
            bundle.putBundle(f14003k, f10.d(false));
        }
        int i11 = this.f14009e;
        if (i7 < 3 || i11 != 0) {
            bundle.putInt(l, i11);
        }
        long j3 = this.f14010f;
        if (i7 < 3 || j3 != 0) {
            bundle.putLong(f14004m, j3);
        }
        long j10 = this.f14011g;
        if (i7 < 3 || j10 != 0) {
            bundle.putLong(f14005n, j10);
        }
        int i12 = this.f14012h;
        if (i12 != -1) {
            bundle.putInt(o, i12);
        }
        int i13 = this.f14013i;
        if (i13 != -1) {
            bundle.putInt(f14006p, i13);
        }
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || V.class != obj.getClass()) {
            return false;
        }
        V v10 = (V) obj;
        return a(v10) && AbstractC0904a.A(this.f14007a, v10.f14007a) && AbstractC0904a.A(this.d, v10.d);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f14007a, Integer.valueOf(this.f14008b), this.c, this.d, Integer.valueOf(this.f14009e), Long.valueOf(this.f14010f), Long.valueOf(this.f14011g), Integer.valueOf(this.f14012h), Integer.valueOf(this.f14013i)});
    }
}
