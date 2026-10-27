package e2;

import android.os.Bundle;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends X {
    public static final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f14015e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14016b;
    public final float c;

    static {
        int i7 = AbstractC1343A.f15195a;
        d = Integer.toString(1, 36);
        f14015e = Integer.toString(2, 36);
    }

    public Y(float f10, int i7) {
        boolean z6 = false;
        AbstractC1346c.b("maxStars must be a positive integer", i7 > 0);
        if (f10 >= 0.0f && f10 <= i7) {
            z6 = true;
        }
        AbstractC1346c.b("starRating is out of range [0, maxStars]", z6);
        this.f14016b = i7;
        this.c = f10;
    }

    @Override // e2.X
    public final boolean b() {
        return this.c != -1.0f;
    }

    @Override // e2.X
    public final Bundle c() {
        Bundle bundle = new Bundle();
        bundle.putInt(X.f14014a, 2);
        bundle.putInt(d, this.f14016b);
        bundle.putFloat(f14015e, this.c);
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Y)) {
            return false;
        }
        Y y10 = (Y) obj;
        return this.f14016b == y10.f14016b && this.c == y10.c;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f14016b), Float.valueOf(this.c)});
    }

    public Y(int i7) {
        AbstractC1346c.b("maxStars must be a positive integer", i7 > 0);
        this.f14016b = i7;
        this.c = -1.0f;
    }
}
