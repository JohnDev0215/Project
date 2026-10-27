package e2;

import android.os.Bundle;
import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class r extends X {
    public static final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f14334e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f14335b;
    public final boolean c;

    static {
        int i7 = AbstractC1343A.f15195a;
        d = Integer.toString(1, 36);
        f14334e = Integer.toString(2, 36);
    }

    public r() {
        this.f14335b = false;
        this.c = false;
    }

    @Override // e2.X
    public final boolean b() {
        return this.f14335b;
    }

    @Override // e2.X
    public final Bundle c() {
        Bundle bundle = new Bundle();
        bundle.putInt(X.f14014a, 0);
        bundle.putBoolean(d, this.f14335b);
        bundle.putBoolean(f14334e, this.c);
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.c == rVar.c && this.f14335b == rVar.f14335b;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.f14335b), Boolean.valueOf(this.c)});
    }

    public r(boolean z6) {
        this.f14335b = true;
        this.c = z6;
    }
}
