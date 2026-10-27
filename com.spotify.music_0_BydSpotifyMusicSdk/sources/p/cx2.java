package p;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public final class cx2 extends bx2 {
    public static final fx2 q = fx2.g(WindowInsets.CONSUMED, null);

    public cx2(fx2 fx2Var, WindowInsets windowInsets) {
        super(fx2Var, windowInsets);
    }

    @Override // p.xw2, p.dx2
    public final void d(View view) {
    }

    @Override // p.xw2, p.dx2
    public w41 f(int i) {
        return w41.b(this.c.getInsets(ex2.a(i)));
    }
}
