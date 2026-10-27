package e2;

import android.os.Bundle;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class N extends X {
    public static final String c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f13985b;

    static {
        int i7 = AbstractC1343A.f15195a;
        c = Integer.toString(1, 36);
    }

    public N() {
        this.f13985b = -1.0f;
    }

    @Override // e2.X
    public final boolean b() {
        return this.f13985b != -1.0f;
    }

    @Override // e2.X
    public final Bundle c() {
        Bundle bundle = new Bundle();
        bundle.putInt(X.f14014a, 1);
        bundle.putFloat(c, this.f13985b);
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof N) {
            return this.f13985b == ((N) obj).f13985b;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f13985b)});
    }

    public N(float f10) {
        AbstractC1346c.b("percent must be in the range of [0, 100]", f10 >= 0.0f && f10 <= 100.0f);
        this.f13985b = f10;
    }
}
