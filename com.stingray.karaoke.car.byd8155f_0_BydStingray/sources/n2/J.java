package n2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Objects;
import u2.C2492C;

/* JADX INFO: loaded from: classes.dex */
public final class J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2492C f19550a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f19551b;
    public final long c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f19552e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f19553f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f19554g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f19555h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f19556i;

    public J(C2492C c2492c, long j3, long j10, long j11, long j12, boolean z6, boolean z10, boolean z11, boolean z12) {
        boolean z13 = true;
        AbstractC1346c.c(!z12 || z10);
        AbstractC1346c.c(!z11 || z10);
        if (z6 && (z10 || z11 || z12)) {
            z13 = false;
        }
        AbstractC1346c.c(z13);
        this.f19550a = c2492c;
        this.f19551b = j3;
        this.c = j10;
        this.d = j11;
        this.f19552e = j12;
        this.f19553f = z6;
        this.f19554g = z10;
        this.f19555h = z11;
        this.f19556i = z12;
    }

    public final J a(long j3) {
        if (j3 == this.c) {
            return this;
        }
        return new J(this.f19550a, this.f19551b, j3, this.d, this.f19552e, this.f19553f, this.f19554g, this.f19555h, this.f19556i);
    }

    public final J b(long j3) {
        if (j3 == this.f19551b) {
            return this;
        }
        return new J(this.f19550a, j3, this.c, this.d, this.f19552e, this.f19553f, this.f19554g, this.f19555h, this.f19556i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || J.class != obj.getClass()) {
            return false;
        }
        J j3 = (J) obj;
        if (this.f19551b == j3.f19551b && this.c == j3.c && this.d == j3.d && this.f19552e == j3.f19552e && this.f19553f == j3.f19553f && this.f19554g == j3.f19554g && this.f19555h == j3.f19555h && this.f19556i == j3.f19556i) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.f19550a, j3.f19550a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((((((this.f19550a.hashCode() + 527) * 31) + ((int) this.f19551b)) * 31) + ((int) this.c)) * 31) + ((int) this.d)) * 31) + ((int) this.f19552e)) * 31) + (this.f19553f ? 1 : 0)) * 31) + (this.f19554g ? 1 : 0)) * 31) + (this.f19555h ? 1 : 0)) * 31) + (this.f19556i ? 1 : 0);
    }
}
