package p;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public class bx2 extends ax2 {
    public w41 n;
    public w41 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public w41 f109p;

    public bx2(fx2 fx2Var, WindowInsets windowInsets) {
        super(fx2Var, windowInsets);
        this.n = null;
        this.o = null;
        this.f109p = null;
    }

    @Override // p.dx2
    public w41 g() {
        if (this.o == null) {
            this.o = w41.b(this.c.getMandatorySystemGestureInsets());
        }
        return this.o;
    }

    @Override // p.dx2
    public w41 i() {
        if (this.n == null) {
            this.n = w41.b(this.c.getSystemGestureInsets());
        }
        return this.n;
    }

    @Override // p.dx2
    public w41 k() {
        if (this.f109p == null) {
            this.f109p = w41.b(this.c.getTappableElementInsets());
        }
        return this.f109p;
    }

    @Override // p.xw2, p.dx2
    public fx2 l(int i, int i2, int i3, int i4) {
        return fx2.g(this.c.inset(i, i2, i3, i4), null);
    }

    @Override // p.yw2, p.dx2
    public void q(w41 w41Var) {
    }
}
