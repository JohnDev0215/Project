package p;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class uw2 extends ww2 {
    public final WindowInsets.Builder a;

    public uw2() {
        this.a = tw2.b();
    }

    @Override // p.ww2
    public fx2 b() {
        a();
        fx2 fx2VarG = fx2.g(this.a.build(), null);
        fx2VarG.a.o(null);
        return fx2VarG;
    }

    @Override // p.ww2
    public void c(w41 w41Var) {
        this.a.setStableInsets(w41Var.c());
    }

    @Override // p.ww2
    public void d(w41 w41Var) {
        this.a.setSystemWindowInsets(w41Var.c());
    }

    public uw2(fx2 fx2Var) {
        WindowInsets.Builder builderB;
        super(fx2Var);
        WindowInsets windowInsetsF = fx2Var.f();
        if (windowInsetsF != null) {
            builderB = tw2.c(windowInsetsF);
        } else {
            builderB = tw2.b();
        }
        this.a = builderB;
    }
}
