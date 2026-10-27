package p;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class yw2 extends xw2 {
    public w41 m;

    public yw2(fx2 fx2Var, WindowInsets windowInsets) {
        super(fx2Var, windowInsets);
        this.m = null;
    }

    @Override // p.dx2
    public fx2 b() {
        return fx2.g(this.c.consumeStableInsets(), null);
    }

    @Override // p.dx2
    public fx2 c() {
        return fx2.g(this.c.consumeSystemWindowInsets(), null);
    }

    @Override // p.dx2
    public final w41 h() {
        if (this.m == null) {
            WindowInsets windowInsets = this.c;
            this.m = w41.a(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        }
        return this.m;
    }

    @Override // p.dx2
    public boolean m() {
        return this.c.isConsumed();
    }

    @Override // p.dx2
    public void q(w41 w41Var) {
        this.m = w41Var;
    }
}
