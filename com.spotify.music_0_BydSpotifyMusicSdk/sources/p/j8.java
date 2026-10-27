package p;

import android.view.MenuItem;
import android.view.ViewGroup;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class j8 implements k4 {
    public final k4 c;
    public final /* synthetic */ s8 e;

    public j8(s8 s8Var, gh2 gh2Var) {
        this.e = s8Var;
        this.c = gh2Var;
    }

    @Override // p.k4
    public final void a(l4 l4Var) {
        this.c.a(l4Var);
        s8 s8Var = this.e;
        if (s8Var.E != null) {
            s8Var.t.getDecorView().removeCallbacks(s8Var.F);
        }
        if (s8Var.D != null) {
            tu2 tu2Var = s8Var.G;
            if (tu2Var != null) {
                tu2Var.b();
            }
            tu2 tu2VarA = zt2.a(s8Var.D);
            tu2VarA.a(0.0f);
            s8Var.G = tu2VarA;
            tu2VarA.d(new i8(2, this));
        }
        w7 w7Var = s8Var.v;
        if (w7Var != null) {
            w7Var.f();
        }
        s8Var.C = null;
        ViewGroup viewGroup = s8Var.I;
        WeakHashMap weakHashMap = zt2.a;
        mt2.c(viewGroup);
        s8Var.G();
    }

    @Override // p.k4
    public final boolean b(l4 l4Var, si1 si1Var) {
        return this.c.b(l4Var, si1Var);
    }

    @Override // p.k4
    public final boolean c(l4 l4Var, MenuItem menuItem) {
        return this.c.c(l4Var, menuItem);
    }

    @Override // p.k4
    public final boolean d(l4 l4Var, si1 si1Var) {
        ViewGroup viewGroup = this.e.I;
        WeakHashMap weakHashMap = zt2.a;
        mt2.c(viewGroup);
        return this.c.d(l4Var, si1Var);
    }
}
