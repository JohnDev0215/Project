package e2;

import android.os.Bundle;
import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a0 extends X {
    public static final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f14035e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f14036b;
    public final boolean c;

    static {
        int i7 = AbstractC1343A.f15195a;
        d = Integer.toString(1, 36);
        f14035e = Integer.toString(2, 36);
    }

    public a0() {
        this.f14036b = false;
        this.c = false;
    }

    @Override // e2.X
    public final boolean b() {
        return this.f14036b;
    }

    @Override // e2.X
    public final Bundle c() {
        Bundle bundle = new Bundle();
        bundle.putInt(X.f14014a, 3);
        bundle.putBoolean(d, this.f14036b);
        bundle.putBoolean(f14035e, this.c);
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a0)) {
            return false;
        }
        a0 a0Var = (a0) obj;
        return this.c == a0Var.c && this.f14036b == a0Var.f14036b;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.f14036b), Boolean.valueOf(this.c)});
    }

    public a0(boolean z6) {
        this.f14036b = true;
        this.c = z6;
    }
}
