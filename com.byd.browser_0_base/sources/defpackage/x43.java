package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import defpackage.ny0;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.tasks.tab_management.p1;
import org.chromium.chrome.browser.tasks.tab_management.s1;
import org.chromium.chrome.browser.toolbar.top.d;
import org.chromium.chrome.browser.toolbar.top.m;

/* JADX INFO: compiled from: TabSwitcherIncognitoReauthCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class x43 extends ky0 {
    public final p1 e;
    public final xy0 f;
    public final Runnable g;
    public Integer h;

    public x43(Context context, ny0 ny0Var, ny0.a aVar, Runnable runnable, Runnable runnable2, p1 p1Var, xy0 xy0Var) {
        super(context, ny0Var, aVar, runnable);
        this.e = p1Var;
        this.f = xy0Var;
        this.g = runnable2;
    }

    @Override // defpackage.jy0
    public final void a() {
        b(null, false);
        View view = this.d;
        p1 p1Var = this.e;
        if (!p1Var.c) {
            p1Var.c = true;
            p1Var.b = view;
            s1 s1Var = (s1) p1Var.a;
            boolean z = s1Var.s;
            ViewGroup viewGroup = s1Var.p;
            if (z) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                layoutParams.topMargin = s1Var.N.getResources().getDimensionPixelSize(R$dimen.toolbar_height_no_shadow);
                viewGroup.addView(view, layoutParams);
            } else {
                viewGroup.addView(view);
            }
            s1Var.F = this.g;
            s1Var.l();
        }
        m mVar = g63.this.x.k;
        d dVar = mVar.c;
        this.h = Integer.valueOf((dVar != null ? dVar.d : mVar.b.r).b.a());
    }

    @Override // defpackage.jy0
    public final void hide(int i) {
        p1 p1Var = this.e;
        if (p1Var.c) {
            p1Var.c = false;
            View view = p1Var.b;
            s1 s1Var = (s1) p1Var.a;
            s1Var.p.removeView(view);
            s1Var.F = null;
            s1Var.l();
            p1Var.b = null;
        }
        int iIntValue = this.h.intValue();
        m mVar = g63.this.x.k;
        d dVar = mVar.c;
        (dVar != null ? dVar.d : mVar.b.r).b.c(iIntValue);
        this.h = null;
        this.c.c();
    }
}
