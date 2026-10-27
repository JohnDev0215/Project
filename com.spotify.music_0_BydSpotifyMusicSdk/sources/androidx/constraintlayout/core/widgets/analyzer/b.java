package androidx.constraintlayout.core.widgets.analyzer;

import p.e90;

/* JADX INFO: loaded from: classes.dex */
public class b extends a {
    public int m;

    public b(f fVar) {
        super(fVar);
        if (fVar instanceof d) {
            this.e = DependencyNode$Type.e;
        } else {
            this.e = DependencyNode$Type.k;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.a
    public final void d(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        for (e90 e90Var : this.k) {
            e90Var.a(e90Var);
        }
    }
}
