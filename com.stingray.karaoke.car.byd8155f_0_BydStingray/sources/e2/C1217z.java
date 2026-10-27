package e2;

import android.os.Bundle;
import h2.AbstractC1343A;

/* JADX INFO: renamed from: e2.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1217z {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C1217z f14370f = new C1217z(new C1216y());

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f14371g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f14372h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f14373i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f14374j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f14375k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f14376a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f14377b;
    public final long c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f14378e;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14371g = Integer.toString(0, 36);
        f14372h = Integer.toString(1, 36);
        f14373i = Integer.toString(2, 36);
        f14374j = Integer.toString(3, 36);
        f14375k = Integer.toString(4, 36);
    }

    public C1217z(C1216y c1216y) {
        long j3 = c1216y.f14367a;
        long j10 = c1216y.f14368b;
        long j11 = c1216y.c;
        float f10 = c1216y.d;
        float f11 = c1216y.f14369e;
        this.f14376a = j3;
        this.f14377b = j10;
        this.c = j11;
        this.d = f10;
        this.f14378e = f11;
    }

    public static C1217z b(Bundle bundle) {
        C1216y c1216y = new C1216y();
        C1217z c1217z = f14370f;
        c1216y.f14367a = bundle.getLong(f14371g, c1217z.f14376a);
        c1216y.f14368b = bundle.getLong(f14372h, c1217z.f14377b);
        c1216y.c = bundle.getLong(f14373i, c1217z.c);
        c1216y.d = bundle.getFloat(f14374j, c1217z.d);
        c1216y.f14369e = bundle.getFloat(f14375k, c1217z.f14378e);
        return new C1217z(c1216y);
    }

    public final C1216y a() {
        C1216y c1216y = new C1216y();
        c1216y.f14367a = this.f14376a;
        c1216y.f14368b = this.f14377b;
        c1216y.c = this.c;
        c1216y.d = this.d;
        c1216y.f14369e = this.f14378e;
        return c1216y;
    }

    public final Bundle c() {
        Bundle bundle = new Bundle();
        C1217z c1217z = f14370f;
        long j3 = c1217z.f14376a;
        long j10 = this.f14376a;
        if (j10 != j3) {
            bundle.putLong(f14371g, j10);
        }
        long j11 = c1217z.f14377b;
        long j12 = this.f14377b;
        if (j12 != j11) {
            bundle.putLong(f14372h, j12);
        }
        long j13 = c1217z.c;
        long j14 = this.c;
        if (j14 != j13) {
            bundle.putLong(f14373i, j14);
        }
        float f10 = c1217z.d;
        float f11 = this.d;
        if (f11 != f10) {
            bundle.putFloat(f14374j, f11);
        }
        float f12 = c1217z.f14378e;
        float f13 = this.f14378e;
        if (f13 != f12) {
            bundle.putFloat(f14375k, f13);
        }
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1217z)) {
            return false;
        }
        C1217z c1217z = (C1217z) obj;
        return this.f14376a == c1217z.f14376a && this.f14377b == c1217z.f14377b && this.c == c1217z.c && this.d == c1217z.d && this.f14378e == c1217z.f14378e;
    }

    public final int hashCode() {
        long j3 = this.f14376a;
        long j10 = this.f14377b;
        int i7 = ((((int) (j3 ^ (j3 >>> 32))) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        long j11 = this.c;
        int i10 = (i7 + ((int) ((j11 >>> 32) ^ j11))) * 31;
        float f10 = this.d;
        int iFloatToIntBits = (i10 + (f10 != 0.0f ? Float.floatToIntBits(f10) : 0)) * 31;
        float f11 = this.f14378e;
        return iFloatToIntBits + (f11 != 0.0f ? Float.floatToIntBits(f11) : 0);
    }
}
