package e2;

import h2.AbstractC1343A;
import java.util.Objects;

/* JADX INFO: renamed from: e2.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1210s {
    public static final String c;
    public static final String d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f14336a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f14337b;

    static {
        int i7 = AbstractC1343A.f15195a;
        c = Integer.toString(0, 36);
        d = Integer.toString(1, 36);
    }

    public C1210s(String str, String str2) {
        this.f14336a = AbstractC1343A.I(str);
        this.f14337b = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1210s.class != obj.getClass()) {
            return false;
        }
        C1210s c1210s = (C1210s) obj;
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(this.f14336a, c1210s.f14336a) && Objects.equals(this.f14337b, c1210s.f14337b);
    }

    public final int hashCode() {
        int iHashCode = this.f14337b.hashCode() * 31;
        String str = this.f14336a;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }
}
