package k3;

import android.os.Bundle;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class B1 {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final e2.V f17722k;
    public static final B1 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final String f17723m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final String f17724n;
    public static final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final String f17725p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final String f17726q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final String f17727r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final String f17728s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final String f17729t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final String f17730u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final String f17731v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e2.V f17732a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f17733b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f17734e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f17735f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f17736g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f17737h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f17738i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f17739j;

    static {
        e2.V v10 = new e2.V(null, 0, null, null, 0, 0L, 0L, -1, -1);
        f17722k = v10;
        l = new B1(v10, false, -9223372036854775807L, -9223372036854775807L, 0L, 0, 0L, -9223372036854775807L, -9223372036854775807L, 0L);
        int i7 = AbstractC1343A.f15195a;
        f17723m = Integer.toString(0, 36);
        f17724n = Integer.toString(1, 36);
        o = Integer.toString(2, 36);
        f17725p = Integer.toString(3, 36);
        f17726q = Integer.toString(4, 36);
        f17727r = Integer.toString(5, 36);
        f17728s = Integer.toString(6, 36);
        f17729t = Integer.toString(7, 36);
        f17730u = Integer.toString(8, 36);
        f17731v = Integer.toString(9, 36);
    }

    public B1(e2.V v10, boolean z6, long j3, long j10, long j11, int i7, long j12, long j13, long j14, long j15) {
        AbstractC1346c.c(z6 == (v10.f14012h != -1));
        this.f17732a = v10;
        this.f17733b = z6;
        this.c = j3;
        this.d = j10;
        this.f17734e = j11;
        this.f17735f = i7;
        this.f17736g = j12;
        this.f17737h = j13;
        this.f17738i = j14;
        this.f17739j = j15;
    }

    public static B1 b(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(f17723m);
        return new B1(bundle2 == null ? f17722k : e2.V.c(bundle2), bundle.getBoolean(f17724n, false), bundle.getLong(o, -9223372036854775807L), bundle.getLong(f17725p, -9223372036854775807L), bundle.getLong(f17726q, 0L), bundle.getInt(f17727r, 0), bundle.getLong(f17728s, 0L), bundle.getLong(f17729t, -9223372036854775807L), bundle.getLong(f17730u, -9223372036854775807L), bundle.getLong(f17731v, 0L));
    }

    public final B1 a(boolean z6, boolean z10) {
        if (z6 && z10) {
            return this;
        }
        return new B1(this.f17732a.b(z6, z10), z6 && this.f17733b, this.c, z6 ? this.d : -9223372036854775807L, z6 ? this.f17734e : 0L, z6 ? this.f17735f : 0, z6 ? this.f17736g : 0L, z6 ? this.f17737h : -9223372036854775807L, z6 ? this.f17738i : -9223372036854775807L, z6 ? this.f17739j : 0L);
    }

    public final Bundle c(int i7) {
        Bundle bundle = new Bundle();
        e2.V v10 = this.f17732a;
        if (i7 < 3 || !f17722k.a(v10)) {
            bundle.putBundle(f17723m, v10.d(i7));
        }
        boolean z6 = this.f17733b;
        if (z6) {
            bundle.putBoolean(f17724n, z6);
        }
        long j3 = this.c;
        if (j3 != -9223372036854775807L) {
            bundle.putLong(o, j3);
        }
        long j10 = this.d;
        if (j10 != -9223372036854775807L) {
            bundle.putLong(f17725p, j10);
        }
        long j11 = this.f17734e;
        if (i7 < 3 || j11 != 0) {
            bundle.putLong(f17726q, j11);
        }
        int i10 = this.f17735f;
        if (i10 != 0) {
            bundle.putInt(f17727r, i10);
        }
        long j12 = this.f17736g;
        if (j12 != 0) {
            bundle.putLong(f17728s, j12);
        }
        long j13 = this.f17737h;
        if (j13 != -9223372036854775807L) {
            bundle.putLong(f17729t, j13);
        }
        long j14 = this.f17738i;
        if (j14 != -9223372036854775807L) {
            bundle.putLong(f17730u, j14);
        }
        long j15 = this.f17739j;
        if (i7 < 3 || j15 != 0) {
            bundle.putLong(f17731v, j15);
        }
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || B1.class != obj.getClass()) {
            return false;
        }
        B1 b12 = (B1) obj;
        return this.c == b12.c && this.f17732a.equals(b12.f17732a) && this.f17733b == b12.f17733b && this.d == b12.d && this.f17734e == b12.f17734e && this.f17735f == b12.f17735f && this.f17736g == b12.f17736g && this.f17737h == b12.f17737h && this.f17738i == b12.f17738i && this.f17739j == b12.f17739j;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f17732a, Boolean.valueOf(this.f17733b)});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SessionPositionInfo {PositionInfo {mediaItemIndex=");
        e2.V v10 = this.f17732a;
        sb.append(v10.f14008b);
        sb.append(", periodIndex=");
        sb.append(v10.f14009e);
        sb.append(", positionMs=");
        sb.append(v10.f14010f);
        sb.append(", contentPositionMs=");
        sb.append(v10.f14011g);
        sb.append(", adGroupIndex=");
        sb.append(v10.f14012h);
        sb.append(", adIndexInAdGroup=");
        sb.append(v10.f14013i);
        sb.append("}, isPlayingAd=");
        sb.append(this.f17733b);
        sb.append(", eventTimeMs=");
        sb.append(this.c);
        sb.append(", durationMs=");
        sb.append(this.d);
        sb.append(", bufferedPositionMs=");
        sb.append(this.f17734e);
        sb.append(", bufferedPercentage=");
        sb.append(this.f17735f);
        sb.append(", totalBufferedDurationMs=");
        sb.append(this.f17736g);
        sb.append(", currentLiveOffsetMs=");
        sb.append(this.f17737h);
        sb.append(", contentDurationMs=");
        sb.append(this.f17738i);
        sb.append(", contentBufferedPositionMs=");
        return android.hardware.bydauto.ac.tsimpl.a.f(this.f17739j, "}", sb);
    }
}
