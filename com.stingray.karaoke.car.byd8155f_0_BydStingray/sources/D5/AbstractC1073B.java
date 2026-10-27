package d5;

import java.util.Collection;

/* JADX INFO: renamed from: d5.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1073B extends K {
    @Override // d5.K
    public final AbstractC1075D e() {
        throw new AssertionError("should never be called");
    }

    @Override // d5.K
    /* JADX INFO: renamed from: f */
    public final AbstractC1075D values() {
        c0 c0Var = ((c0) this).f13412s;
        M m10 = c0Var.f13387m;
        if (m10 != null) {
            return m10;
        }
        g0 g0VarD = c0Var.d();
        c0Var.f13387m = g0VarD;
        return g0VarD;
    }

    @Override // d5.K, java.util.Map
    public final Collection values() {
        c0 c0Var = ((c0) this).f13412s;
        M m10 = c0Var.f13387m;
        if (m10 != null) {
            return m10;
        }
        g0 g0VarD = c0Var.d();
        c0Var.f13387m = g0VarD;
        return g0VarD;
    }
}
