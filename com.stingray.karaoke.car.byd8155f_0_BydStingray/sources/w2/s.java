package w2;

import e2.n0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Objects;
import n2.a0;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f23271a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a0[] f23272b;
    public final q[] c;
    public final n0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i2.q f23273e;

    public s(a0[] a0VarArr, q[] qVarArr, n0 n0Var, i2.q qVar) {
        AbstractC1346c.c(a0VarArr.length == qVarArr.length);
        this.f23272b = a0VarArr;
        this.c = (q[]) qVarArr.clone();
        this.d = n0Var;
        this.f23273e = qVar;
        this.f23271a = a0VarArr.length;
    }

    public final boolean a(s sVar, int i7) {
        if (sVar == null) {
            return false;
        }
        a0 a0Var = this.f23272b[i7];
        a0 a0Var2 = sVar.f23272b[i7];
        int i10 = AbstractC1343A.f15195a;
        return Objects.equals(a0Var, a0Var2) && Objects.equals(this.c[i7], sVar.c[i7]);
    }

    public final boolean b(int i7) {
        return this.f23272b[i7] != null;
    }
}
