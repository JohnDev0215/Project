package F1;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes.dex */
public abstract class P extends O {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public y1.b f2316k;

    public P(V v10, WindowInsets windowInsets) {
        super(v10, windowInsets);
        this.f2316k = null;
    }

    @Override // F1.U
    public V b() {
        return V.c(this.c.consumeStableInsets(), null);
    }

    @Override // F1.U
    public V c() {
        return V.c(this.c.consumeSystemWindowInsets(), null);
    }

    @Override // F1.U
    public final y1.b g() {
        if (this.f2316k == null) {
            WindowInsets windowInsets = this.c;
            this.f2316k = y1.b.a(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
        }
        return this.f2316k;
    }

    @Override // F1.U
    public boolean j() {
        return this.c.isConsumed();
    }
}
