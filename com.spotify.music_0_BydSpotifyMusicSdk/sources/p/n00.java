package p;

import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes.dex */
public final class n00 implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ CoordinatorLayout c;

    public n00(CoordinatorLayout coordinatorLayout) {
        this.c = coordinatorLayout;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        this.c.p(0);
        return true;
    }
}
