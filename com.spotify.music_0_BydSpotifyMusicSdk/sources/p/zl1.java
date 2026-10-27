package p;

import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class zl1 implements zi0 {
    public final Integer a;
    public final Integer b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final List l;
    public final int m;
    public final int n;
    public final Set o;

    public zl1(Integer num, Integer num2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, List list, int i, int i2, Set set) {
        v03.j(list, "rateLimiterRules");
        v03.j(set, "effects");
        this.a = num;
        this.b = num2;
        this.c = z;
        this.d = z2;
        this.e = z3;
        this.f = z4;
        this.g = z5;
        this.h = z6;
        this.i = z7;
        this.j = z8;
        this.k = z9;
        this.l = list;
        this.m = i;
        this.n = i2;
        this.o = set;
    }

    public static zl1 a(zl1 zl1Var, boolean z, boolean z2, boolean z3, boolean z4, Set set, int i) {
        Integer num = (i & 1) != 0 ? zl1Var.a : null;
        Integer num2 = (i & 2) != 0 ? zl1Var.b : null;
        boolean z5 = (i & 4) != 0 ? zl1Var.c : false;
        boolean z6 = (i & 8) != 0 ? zl1Var.d : z;
        boolean z7 = (i & 16) != 0 ? zl1Var.e : z2;
        boolean z8 = (i & 32) != 0 ? zl1Var.f : z3;
        boolean z9 = (i & 64) != 0 ? zl1Var.g : false;
        boolean z10 = (i & 128) != 0 ? zl1Var.h : z4;
        boolean z11 = (i & 256) != 0 ? zl1Var.i : false;
        boolean z12 = (i & 512) != 0 ? zl1Var.j : false;
        boolean z13 = (i & 1024) != 0 ? zl1Var.k : false;
        List list = (i & 2048) != 0 ? zl1Var.l : null;
        int i2 = (i & 4096) != 0 ? zl1Var.m : 0;
        int i3 = (i & 8192) != 0 ? zl1Var.n : 0;
        Set set2 = (i & 16384) != 0 ? zl1Var.o : set;
        zl1Var.getClass();
        v03.j(list, "rateLimiterRules");
        v03.j(set2, "effects");
        return new zl1(num, num2, z5, z6, z7, z8, z9, z10, z11, z12, z13, list, i2, i3, set2);
    }

    public static Integer b(Integer num) {
        if (num != null) {
            num.intValue();
            if (new i51(1, 3600, 1).a(num.intValue())) {
                return num;
            }
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zl1)) {
            return false;
        }
        zl1 zl1Var = (zl1) obj;
        return v03.b(this.a, zl1Var.a) && v03.b(this.b, zl1Var.b) && this.c == zl1Var.c && this.d == zl1Var.d && this.e == zl1Var.e && this.f == zl1Var.f && this.g == zl1Var.g && this.h == zl1Var.h && this.i == zl1Var.i && this.j == zl1Var.j && this.k == zl1Var.k && v03.b(this.l, zl1Var.l) && this.m == zl1Var.m && this.n == zl1Var.n && v03.b(this.o, zl1Var.o);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [int] */
    /* JADX WARN: Type inference failed for: r3v10, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12, types: [int] */
    /* JADX WARN: Type inference failed for: r3v14, types: [int] */
    /* JADX WARN: Type inference failed for: r3v16, types: [int] */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2, types: [int] */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v33 */
    /* JADX WARN: Type inference failed for: r3v4, types: [int] */
    /* JADX WARN: Type inference failed for: r3v6, types: [int] */
    /* JADX WARN: Type inference failed for: r3v8, types: [int] */
    public final int hashCode() {
        Integer num = this.a;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.b;
        int iHashCode2 = (iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
        boolean z = this.c;
        ?? r3 = z;
        if (z) {
            r3 = 1;
        }
        int i = (iHashCode2 + r3) * 31;
        boolean z2 = this.d;
        ?? r32 = z2;
        if (z2) {
            r32 = 1;
        }
        int i2 = (i + r32) * 31;
        boolean z3 = this.e;
        ?? r33 = z3;
        if (z3) {
            r33 = 1;
        }
        int i3 = (i2 + r33) * 31;
        boolean z4 = this.f;
        ?? r34 = z4;
        if (z4) {
            r34 = 1;
        }
        int i4 = (i3 + r34) * 31;
        boolean z5 = this.g;
        ?? r35 = z5;
        if (z5) {
            r35 = 1;
        }
        int i5 = (i4 + r35) * 31;
        boolean z6 = this.h;
        ?? r36 = z6;
        if (z6) {
            r36 = 1;
        }
        int i6 = (i5 + r36) * 31;
        boolean z7 = this.i;
        ?? r37 = z7;
        if (z7) {
            r37 = 1;
        }
        int i7 = (i6 + r37) * 31;
        boolean z8 = this.j;
        ?? r38 = z8;
        if (z8) {
            r38 = 1;
        }
        int i8 = (i7 + r38) * 31;
        boolean z9 = this.k;
        return this.o.hashCode() + hf2.a(this.n, hf2.a(this.m, (this.l.hashCode() + ((i8 + (z9 ? 1 : z9)) * 31)) * 31, 31), 31);
    }

    public final String toString() {
        return "MutableEventSenderConfig(defaultEventSyncInterval=" + this.a + ", eventSyncInterval=" + this.b + ", shouldAlwaysSendFullEss=" + this.c + ", isStartupSendEnabled=" + this.d + ", isOnlineSendEnabled=" + this.e + ", isDynamicBatchMixedAuthSendEnabled=" + this.f + ", isBackgroundSendEnabled=" + this.g + ", isSendOnBCDEventEnabled=" + this.h + ", isAuthFlowEnabled=" + this.i + ", isEventStatesPersistingEnabled=" + this.j + ", isRateLimiterEnabled=" + this.k + ", rateLimiterRules=" + this.l + ", heartbeatInterval=" + this.m + ", heartbeatRetryInterval=" + this.n + ", effects=" + this.o + ')';
    }
}
