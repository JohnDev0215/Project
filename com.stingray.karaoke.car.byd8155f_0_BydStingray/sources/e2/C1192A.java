package e2;

import android.net.Uri;
import d5.C1077F;
import h2.AbstractC1343A;
import java.util.List;
import java.util.Objects;

/* JADX INFO: renamed from: e2.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1192A {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f13839i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f13840j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f13841k;
    public static final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f13842m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f13843n;
    public static final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f13844p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Uri f13845a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13846b;
    public final C1215x c;
    public final C1211t d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f13847e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f13848f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d5.I f13849g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f13850h;

    static {
        int i7 = AbstractC1343A.f15195a;
        f13839i = Integer.toString(0, 36);
        f13840j = Integer.toString(1, 36);
        f13841k = Integer.toString(2, 36);
        l = Integer.toString(3, 36);
        f13842m = Integer.toString(4, 36);
        f13843n = Integer.toString(5, 36);
        o = Integer.toString(6, 36);
        f13844p = Integer.toString(7, 36);
    }

    public C1192A(Uri uri, String str, C1215x c1215x, C1211t c1211t, List list, String str2, d5.I i7, long j3) {
        this.f13845a = uri;
        this.f13846b = L.l(str);
        this.c = c1215x;
        this.d = c1211t;
        this.f13847e = list;
        this.f13848f = str2;
        this.f13849g = i7;
        C1077F c1077fU = d5.I.u();
        for (int i10 = 0; i10 < i7.size(); i10++) {
            E e10 = (E) i7.get(i10);
            D d = new D();
            d.f13856a = e10.f13867a;
            d.f13857b = e10.f13868b;
            d.c = e10.c;
            d.d = e10.d;
            d.f13858e = e10.f13869e;
            d.f13859f = e10.f13870f;
            d.f13860g = e10.f13871g;
            c1077fU.c(new C(d));
        }
        c1077fU.h();
        this.f13850h = j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1192A)) {
            return false;
        }
        C1192A c1192a = (C1192A) obj;
        if (this.f13845a.equals(c1192a.f13845a)) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.f13846b, c1192a.f13846b) && Objects.equals(this.c, c1192a.c) && Objects.equals(this.d, c1192a.d) && this.f13847e.equals(c1192a.f13847e) && Objects.equals(this.f13848f, c1192a.f13848f) && this.f13849g.equals(c1192a.f13849g) && Long.valueOf(this.f13850h).equals(Long.valueOf(c1192a.f13850h))) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = this.f13845a.hashCode() * 31;
        String str = this.f13846b;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        C1215x c1215x = this.c;
        int iHashCode3 = (iHashCode2 + (c1215x == null ? 0 : c1215x.hashCode())) * 31;
        C1211t c1211t = this.d;
        int iHashCode4 = (this.f13847e.hashCode() + ((iHashCode3 + (c1211t == null ? 0 : c1211t.hashCode())) * 31)) * 31;
        String str2 = this.f13848f;
        return (int) ((((long) ((this.f13849g.hashCode() + ((iHashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31)) * 31)) * 31) + this.f13850h);
    }
}
