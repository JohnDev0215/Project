package p;

import androidx.work.BackoffPolicy;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.OverwritingInputMerger;
import androidx.work.WorkInfo$State;

/* JADX INFO: loaded from: classes.dex */
public final class ky2 {
    public final String a;
    public WorkInfo$State b;
    public final String c;
    public final String d;
    public final w50 e;
    public final w50 f;
    public final long g;
    public final long h;
    public final long i;
    public final wy j;
    public final int k;
    public final BackoffPolicy l;
    public final long m;
    public long n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final long f145p;
    public boolean q;
    public final OutOfQuotaPolicy r;
    public final int s;
    public final int t;
    public final long u;
    public final int v;
    public final int w;

    static {
        v03.i(vc1.d("WorkSpec"), "tagWithPrefix(\"WorkSpec\")");
    }

    public ky2(String str, WorkInfo$State workInfo$State, String str2, String str3, w50 w50Var, w50 w50Var2, long j, long j2, long j3, wy wyVar, int i, BackoffPolicy backoffPolicy, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy, int i2, int i3, long j8, int i4, int i5) {
        v03.j(str, "id");
        v03.j(workInfo$State, "state");
        v03.j(str2, "workerClassName");
        v03.j(str3, "inputMergerClassName");
        v03.j(w50Var, "input");
        v03.j(w50Var2, "output");
        v03.j(wyVar, "constraints");
        v03.j(backoffPolicy, "backoffPolicy");
        v03.j(outOfQuotaPolicy, "outOfQuotaPolicy");
        this.a = str;
        this.b = workInfo$State;
        this.c = str2;
        this.d = str3;
        this.e = w50Var;
        this.f = w50Var2;
        this.g = j;
        this.h = j2;
        this.i = j3;
        this.j = wyVar;
        this.k = i;
        this.l = backoffPolicy;
        this.m = j4;
        this.n = j5;
        this.o = j6;
        this.f145p = j7;
        this.q = z;
        this.r = outOfQuotaPolicy;
        this.s = i2;
        this.t = i3;
        this.u = j8;
        this.v = i4;
        this.w = i5;
    }

    public final long a() {
        boolean z = this.b == WorkInfo$State.c && this.k > 0;
        long j = this.n;
        boolean zC = c();
        BackoffPolicy backoffPolicy = this.l;
        v03.j(backoffPolicy, "backoffPolicy");
        long j2 = this.u;
        long j3 = Long.MAX_VALUE;
        int i = this.s;
        if (j2 != Long.MAX_VALUE && zC) {
            if (i == 0) {
                return j2;
            }
            long j4 = j + 900000;
            return j2 < j4 ? j4 : j2;
        }
        if (z) {
            BackoffPolicy backoffPolicy2 = BackoffPolicy.e;
            int i2 = this.k;
            long j5 = this.m;
            long jScalb = backoffPolicy == backoffPolicy2 ? j5 * ((long) i2) : (long) Math.scalb(j5, i2 - 1);
            if (jScalb > 18000000) {
                jScalb = 18000000;
            }
            j3 = jScalb + j;
        } else {
            long j6 = this.g;
            if (zC) {
                long j7 = this.h;
                long j8 = i == 0 ? j + j6 : j + j7;
                long j9 = this.i;
                j3 = (j9 == j7 || i != 0) ? j8 : (j7 - j9) + j8;
            } else if (j != -1) {
                j3 = j + j6;
            }
        }
        return j3;
    }

    public final boolean b() {
        return !v03.b(wy.i, this.j);
    }

    public final boolean c() {
        return this.h != 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ky2)) {
            return false;
        }
        ky2 ky2Var = (ky2) obj;
        return v03.b(this.a, ky2Var.a) && this.b == ky2Var.b && v03.b(this.c, ky2Var.c) && v03.b(this.d, ky2Var.d) && v03.b(this.e, ky2Var.e) && v03.b(this.f, ky2Var.f) && this.g == ky2Var.g && this.h == ky2Var.h && this.i == ky2Var.i && v03.b(this.j, ky2Var.j) && this.k == ky2Var.k && this.l == ky2Var.l && this.m == ky2Var.m && this.n == ky2Var.n && this.o == ky2Var.o && this.f145p == ky2Var.f145p && this.q == ky2Var.q && this.r == ky2Var.r && this.s == ky2Var.s && this.t == ky2Var.t && this.u == ky2Var.u && this.v == ky2Var.v && this.w == ky2Var.w;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v25, types: [int] */
    /* JADX WARN: Type inference failed for: r0v34 */
    /* JADX WARN: Type inference failed for: r0v35 */
    public final int hashCode() {
        int iHashCode = (Long.hashCode(this.f145p) + ((Long.hashCode(this.o) + ((Long.hashCode(this.n) + ((Long.hashCode(this.m) + ((this.l.hashCode() + hf2.a(this.k, (this.j.hashCode() + ((Long.hashCode(this.i) + ((Long.hashCode(this.h) + ((Long.hashCode(this.g) + ((this.f.hashCode() + ((this.e.hashCode() + hf2.b(this.d, hf2.b(this.c, (this.b.hashCode() + (this.a.hashCode() * 31)) * 31, 31), 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31, 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        boolean z = this.q;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return Integer.hashCode(this.w) + hf2.a(this.v, (Long.hashCode(this.u) + hf2.a(this.t, hf2.a(this.s, (this.r.hashCode() + ((iHashCode + r0) * 31)) * 31, 31), 31)) * 31, 31);
    }

    public final String toString() {
        return hf2.d(new StringBuilder("{WorkSpec: "), this.a, '}');
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ky2(String str, WorkInfo$State workInfo$State, String str2, String str3, w50 w50Var, w50 w50Var2, long j, long j2, long j3, wy wyVar, int i, BackoffPolicy backoffPolicy, long j4, long j5, long j6, long j7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy, int i2, long j8, int i3, int i4, int i5) {
        w50 w50Var3;
        w50 w50Var4;
        WorkInfo$State workInfo$State2 = (i5 & 2) != 0 ? WorkInfo$State.c : workInfo$State;
        String name = (i5 & 8) != 0 ? OverwritingInputMerger.class.getName() : str3;
        if ((i5 & 16) != 0) {
            w50 w50Var5 = w50.c;
            v03.i(w50Var5, "EMPTY");
            w50Var3 = w50Var5;
        } else {
            w50Var3 = w50Var;
        }
        if ((i5 & 32) != 0) {
            w50 w50Var6 = w50.c;
            v03.i(w50Var6, "EMPTY");
            w50Var4 = w50Var6;
        } else {
            w50Var4 = w50Var2;
        }
        this(str, workInfo$State2, str2, name, w50Var3, w50Var4, (i5 & 64) != 0 ? 0L : j, (i5 & 128) != 0 ? 0L : j2, (i5 & 256) != 0 ? 0L : j3, (i5 & 512) != 0 ? wy.i : wyVar, (i5 & 1024) != 0 ? 0 : i, (i5 & 2048) != 0 ? BackoffPolicy.c : backoffPolicy, (i5 & 4096) != 0 ? 30000L : j4, (i5 & 8192) != 0 ? -1L : j5, (i5 & 16384) != 0 ? 0L : j6, (32768 & i5) != 0 ? -1L : j7, (65536 & i5) != 0 ? false : z, (131072 & i5) != 0 ? OutOfQuotaPolicy.c : outOfQuotaPolicy, (262144 & i5) != 0 ? 0 : i2, 0, (1048576 & i5) != 0 ? Long.MAX_VALUE : j8, (2097152 & i5) != 0 ? 0 : i3, (i5 & 4194304) != 0 ? -256 : i4);
    }
}
