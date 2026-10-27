package e2;

import B2.C0074n;
import android.os.Bundle;
import android.os.Parcelable;
import d5.C1078G;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class g0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f14102f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f14103g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14104a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f14105b;
    public final int c;
    public final C1208p[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f14106e;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14102f = Integer.toString(0, 36);
        f14103g = Integer.toString(1, 36);
    }

    public g0(String str, C1208p... c1208pArr) {
        AbstractC1346c.c(c1208pArr.length > 0);
        this.f14105b = str;
        this.d = c1208pArr;
        this.f14104a = c1208pArr.length;
        int iG = L.g(c1208pArr[0].f14316n);
        this.c = iG == -1 ? L.g(c1208pArr[0].f14315m) : iG;
        String str2 = c1208pArr[0].d;
        str2 = (str2 == null || str2.equals("und")) ? "" : str2;
        int i7 = c1208pArr[0].f14309f | 16384;
        for (int i10 = 1; i10 < c1208pArr.length; i10++) {
            String str3 = c1208pArr[i10].d;
            if (!str2.equals((str3 == null || str3.equals("und")) ? "" : str3)) {
                c("languages", c1208pArr[0].d, c1208pArr[i10].d, i10);
                return;
            } else {
                if (i7 != (c1208pArr[i10].f14309f | 16384)) {
                    c("role flags", Integer.toBinaryString(c1208pArr[0].f14309f), Integer.toBinaryString(c1208pArr[i10].f14309f), i10);
                    return;
                }
            }
        }
    }

    public static g0 b(Bundle bundle) {
        d5.d0 d0VarR;
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(f14102f);
        if (parcelableArrayList == null) {
            C1078G c1078g = d5.I.f13384m;
            d0VarR = d5.d0.f13415p;
        } else {
            d0VarR = AbstractC1346c.r(new C0074n(27), parcelableArrayList);
        }
        return new g0(bundle.getString(f14103g, ""), (C1208p[]) d0VarR.toArray(new C1208p[0]));
    }

    public static void c(String str, String str2, String str3, int i7) {
        AbstractC1346c.q("TrackGroup", "", new IllegalStateException("Different " + str + " combined in one TrackGroup: '" + str2 + "' (track 0) and '" + str3 + "' (track " + i7 + ")"));
    }

    public final g0 a(String str) {
        return new g0(str, this.d);
    }

    public final Bundle d() {
        Bundle bundle = new Bundle();
        C1208p[] c1208pArr = this.d;
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(c1208pArr.length);
        for (C1208p c1208p : c1208pArr) {
            arrayList.add(c1208p.d(true));
        }
        bundle.putParcelableArrayList(f14102f, arrayList);
        bundle.putString(f14103g, this.f14105b);
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || g0.class != obj.getClass()) {
            return false;
        }
        g0 g0Var = (g0) obj;
        return this.f14105b.equals(g0Var.f14105b) && Arrays.equals(this.d, g0Var.d);
    }

    public final int hashCode() {
        if (this.f14106e == 0) {
            this.f14106e = Arrays.hashCode(this.d) + M5.b.c(this.f14105b, 527, 31);
        }
        return this.f14106e;
    }
}
