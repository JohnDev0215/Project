package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: KeyboardHideHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r41 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final View e;
    public final Runnable f;
    public final q41 g = new q41(this);
    public final Rect h = new Rect();
    public it3 i;
    public boolean j;
    public int k;

    public r41(View view, zc0 zc0Var) {
        this.e = view;
        this.f = zc0Var;
    }

    public final int a() {
        it3 it3Var = this.i;
        if (it3Var == null) {
            return this.e.getRootView().getHeight();
        }
        View decorView = it3Var.a.getDecorView();
        Rect rect = this.h;
        decorView.getWindowVisibleDisplayFrame(rect);
        return Math.min(rect.height(), this.i.a.getDecorView().getHeight());
    }

    public final void b() {
        if (this.j) {
            View view = this.e;
            view.removeCallbacks(this.g);
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            this.j = false;
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        if (a() > this.k) {
            this.f.run();
            b();
        }
    }
}
