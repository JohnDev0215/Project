package e2;

import android.net.Uri;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Arrays;

/* JADX INFO: renamed from: e2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1193a {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f14021j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f14022k;
    public static final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f14023m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f14024n;
    public static final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f14025p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final String f14026q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final String f14027r;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f14028a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14029b;
    public final int c;
    public final Uri[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final F[] f14030e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int[] f14031f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long[] f14032g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f14033h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f14034i;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14021j = Integer.toString(0, 36);
        f14022k = Integer.toString(1, 36);
        l = Integer.toString(2, 36);
        f14023m = Integer.toString(3, 36);
        f14024n = Integer.toString(4, 36);
        o = Integer.toString(5, 36);
        f14025p = Integer.toString(6, 36);
        f14026q = Integer.toString(7, 36);
        f14027r = Integer.toString(8, 36);
    }

    public C1193a(long j3, int i7, int i10, int[] iArr, F[] fArr, long[] jArr, long j10, boolean z6) {
        Uri uri;
        int i11 = 0;
        AbstractC1346c.c(iArr.length == fArr.length);
        this.f14028a = j3;
        this.f14029b = i7;
        this.c = i10;
        this.f14031f = iArr;
        this.f14030e = fArr;
        this.f14032g = jArr;
        this.f14033h = j10;
        this.f14034i = z6;
        this.d = new Uri[fArr.length];
        while (true) {
            Uri[] uriArr = this.d;
            if (i11 >= uriArr.length) {
                return;
            }
            F f10 = fArr[i11];
            if (f10 == null) {
                uri = null;
            } else {
                C1192A c1192a = f10.f13879b;
                c1192a.getClass();
                uri = c1192a.f13845a;
            }
            uriArr[i11] = uri;
            i11++;
        }
    }

    public final int a(int i7) {
        int i10;
        int i11 = i7 + 1;
        while (true) {
            int[] iArr = this.f14031f;
            if (i11 >= iArr.length || this.f14034i || (i10 = iArr[i11]) == 0 || i10 == 1) {
                break;
            }
            i11++;
        }
        return i11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1193a.class != obj.getClass()) {
            return false;
        }
        C1193a c1193a = (C1193a) obj;
        return this.f14028a == c1193a.f14028a && this.f14029b == c1193a.f14029b && this.c == c1193a.c && Arrays.equals(this.f14030e, c1193a.f14030e) && Arrays.equals(this.f14031f, c1193a.f14031f) && Arrays.equals(this.f14032g, c1193a.f14032g) && this.f14033h == c1193a.f14033h && this.f14034i == c1193a.f14034i;
    }

    public final int hashCode() {
        int i7 = ((this.f14029b * 31) + this.c) * 31;
        long j3 = this.f14028a;
        int iHashCode = (Arrays.hashCode(this.f14032g) + ((Arrays.hashCode(this.f14031f) + ((Arrays.hashCode(this.f14030e) + ((i7 + ((int) (j3 ^ (j3 >>> 32)))) * 31)) * 31)) * 31)) * 31;
        long j10 = this.f14033h;
        return ((iHashCode + ((int) ((j10 >>> 32) ^ j10))) * 31) + (this.f14034i ? 1 : 0);
    }
}
