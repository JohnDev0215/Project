package u2;

import h2.AbstractC1343A;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class d0 implements InterfaceC2490A {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final n0 f22809n = new n0(new e2.g0("", f0.f22822j));
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f22810m = new ArrayList();

    public d0(long j3) {
        this.l = j3;
    }

    @Override // u2.InterfaceC2490A
    public final long a(long j3, n2.b0 b0Var) {
        return AbstractC1343A.j(j3, 0L, this.l);
    }

    @Override // u2.InterfaceC2490A
    public final void b(InterfaceC2522z interfaceC2522z, long j3) {
        interfaceC2522z.c(this);
    }

    @Override // u2.b0
    public final boolean d() {
        return false;
    }

    @Override // u2.b0
    public final long g() {
        return Long.MIN_VALUE;
    }

    @Override // u2.InterfaceC2490A
    public final long h() {
        return -9223372036854775807L;
    }

    @Override // u2.b0
    public final boolean i(n2.G g7) {
        return false;
    }

    @Override // u2.InterfaceC2490A
    public final n0 j() {
        return f22809n;
    }

    @Override // u2.InterfaceC2490A
    public final long k(w2.q[] qVarArr, boolean[] zArr, a0[] a0VarArr, boolean[] zArr2, long j3) {
        long j10 = AbstractC1343A.j(j3, 0L, this.l);
        for (int i7 = 0; i7 < qVarArr.length; i7++) {
            a0 a0Var = a0VarArr[i7];
            ArrayList arrayList = this.f22810m;
            if (a0Var != null && (qVarArr[i7] == null || !zArr[i7])) {
                arrayList.remove(a0Var);
                a0VarArr[i7] = null;
            }
            if (a0VarArr[i7] == null && qVarArr[i7] != null) {
                e0 e0Var = new e0(this.l);
                e0Var.a(j10);
                arrayList.add(e0Var);
                a0VarArr[i7] = e0Var;
                zArr2[i7] = true;
            }
        }
        return j10;
    }

    @Override // u2.b0
    public final long l() {
        return Long.MIN_VALUE;
    }

    @Override // u2.InterfaceC2490A
    public final void m() {
    }

    @Override // u2.InterfaceC2490A
    public final long o(long j3) {
        long j10 = AbstractC1343A.j(j3, 0L, this.l);
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f22810m;
            if (i7 >= arrayList.size()) {
                return j10;
            }
            ((e0) arrayList.get(i7)).a(j10);
            i7++;
        }
    }

    @Override // u2.InterfaceC2490A
    public final void p(long j3) {
    }

    @Override // u2.b0
    public final void q(long j3) {
    }
}
