package u2;

import e2.C1194b;
import h2.AbstractC1343A;

/* JADX INFO: renamed from: u2.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2501d extends r {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f22805f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f22806g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f22807h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f22808i;

    public C2501d(e2.f0 f0Var, long j3, long j10) throws C2502e {
        super(f0Var);
        boolean z6 = false;
        if (f0Var.h() != 1) {
            throw new C2502e(0);
        }
        e2.e0 e0VarM = f0Var.m(0, new e2.e0(), 0L);
        long jMax = Math.max(0L, j3);
        if (!e0VarM.f14093k && jMax != 0 && !e0VarM.f14090h) {
            throw new C2502e(1);
        }
        long jMax2 = j10 == Long.MIN_VALUE ? e0VarM.f14094m : Math.max(0L, j10);
        long j11 = e0VarM.f14094m;
        if (j11 != -9223372036854775807L) {
            jMax2 = jMax2 > j11 ? j11 : jMax2;
            if (jMax > jMax2) {
                throw new C2502e(2, jMax, jMax2);
            }
        }
        this.f22805f = jMax;
        this.f22806g = jMax2;
        this.f22807h = jMax2 != -9223372036854775807L ? jMax2 - jMax : -9223372036854775807L;
        if (e0VarM.f14091i && (jMax2 == -9223372036854775807L || (j11 != -9223372036854775807L && jMax2 == j11))) {
            z6 = true;
        }
        this.f22808i = z6;
    }

    @Override // u2.r, e2.f0
    public final e2.c0 f(int i7, e2.c0 c0Var, boolean z6) {
        this.f22896e.f(0, c0Var, z6);
        long j3 = c0Var.f14061e - this.f22805f;
        long j10 = this.f22807h;
        c0Var.i(c0Var.f14059a, c0Var.f14060b, 0, j10 == -9223372036854775807L ? -9223372036854775807L : j10 - j3, j3, C1194b.f14037f, false);
        return c0Var;
    }

    @Override // u2.r, e2.f0
    public final e2.e0 m(int i7, e2.e0 e0Var, long j3) {
        this.f22896e.m(0, e0Var, 0L);
        long j10 = e0Var.f14096p;
        long j11 = this.f22805f;
        e0Var.f14096p = j10 + j11;
        e0Var.f14094m = this.f22807h;
        e0Var.f14091i = this.f22808i;
        long j12 = e0Var.l;
        if (j12 != -9223372036854775807L) {
            long jMax = Math.max(j12, j11);
            e0Var.l = jMax;
            long j13 = this.f22806g;
            if (j13 != -9223372036854775807L) {
                jMax = Math.min(jMax, j13);
            }
            e0Var.l = jMax - j11;
        }
        long jT = AbstractC1343A.T(j11);
        long j14 = e0Var.f14087e;
        if (j14 != -9223372036854775807L) {
            e0Var.f14087e = j14 + jT;
        }
        long j15 = e0Var.f14088f;
        if (j15 != -9223372036854775807L) {
            e0Var.f14088f = j15 + jT;
        }
        return e0Var;
    }
}
