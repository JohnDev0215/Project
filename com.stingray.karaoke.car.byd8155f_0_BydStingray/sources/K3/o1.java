package k3;

import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class o1 {
    public static final o1 c = new o1(false, false);
    public static final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String f18025e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f18026a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f18027b;

    static {
        int i7 = AbstractC1343A.f15195a;
        d = Integer.toString(0, 36);
        f18025e = Integer.toString(1, 36);
    }

    public o1(boolean z6, boolean z10) {
        this.f18026a = z6;
        this.f18027b = z10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof o1)) {
            return false;
        }
        o1 o1Var = (o1) obj;
        return this.f18026a == o1Var.f18026a && this.f18027b == o1Var.f18027b;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.f18026a), Boolean.valueOf(this.f18027b)});
    }
}
