package w2;

import e2.C1208p;
import e2.g0;

/* JADX INFO: loaded from: classes.dex */
public abstract class m {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final g0 f23245m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f23246n;
    public final C1208p o;

    public m(int i7, g0 g0Var, int i10) {
        this.l = i7;
        this.f23245m = g0Var;
        this.f23246n = i10;
        this.o = g0Var.d[i10];
    }

    public abstract int a();

    public abstract boolean b(m mVar);
}
