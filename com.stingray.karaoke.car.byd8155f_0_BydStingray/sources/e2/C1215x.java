package e2;

import F0.E0;
import android.net.Uri;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: renamed from: e2.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1215x {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f14355i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f14356j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f14357k;
    public static final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f14358m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f14359n;
    public static final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f14360p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final UUID f14361a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Uri f14362b;
    public final d5.K c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f14363e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f14364f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final d5.I f14365g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final byte[] f14366h;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14355i = Integer.toString(0, 36);
        f14356j = Integer.toString(1, 36);
        f14357k = Integer.toString(2, 36);
        l = Integer.toString(3, 36);
        f14358m = Integer.toString(4, 36);
        f14359n = Integer.toString(5, 36);
        o = Integer.toString(6, 36);
        f14360p = Integer.toString(7, 36);
    }

    public C1215x(E0 e02) {
        AbstractC1346c.j((e02.c && ((Uri) e02.f2016e) == null) ? false : true);
        UUID uuid = (UUID) e02.d;
        uuid.getClass();
        this.f14361a = uuid;
        this.f14362b = (Uri) e02.f2016e;
        this.c = (d5.K) e02.f2017f;
        this.d = e02.f2014a;
        this.f14364f = e02.c;
        this.f14363e = e02.f2015b;
        this.f14365g = (d5.I) e02.f2018g;
        byte[] bArr = (byte[]) e02.f2019h;
        this.f14366h = bArr != null ? Arrays.copyOf(bArr, bArr.length) : null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1215x)) {
            return false;
        }
        C1215x c1215x = (C1215x) obj;
        if (this.f14361a.equals(c1215x.f14361a)) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.f14362b, c1215x.f14362b) && Objects.equals(this.c, c1215x.c) && this.d == c1215x.d && this.f14364f == c1215x.f14364f && this.f14363e == c1215x.f14363e && this.f14365g.equals(c1215x.f14365g) && Arrays.equals(this.f14366h, c1215x.f14366h)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = this.f14361a.hashCode() * 31;
        Uri uri = this.f14362b;
        return Arrays.hashCode(this.f14366h) + ((this.f14365g.hashCode() + ((((((((this.c.hashCode() + ((iHashCode + (uri != null ? uri.hashCode() : 0)) * 31)) * 31) + (this.d ? 1 : 0)) * 31) + (this.f14364f ? 1 : 0)) * 31) + (this.f14363e ? 1 : 0)) * 31)) * 31);
    }
}
