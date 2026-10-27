package defpackage;

import android.content.Context;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.ft2;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.top.m;

/* JADX INFO: compiled from: StatusBarColorController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dt2 implements v80, m.c, ft2.a, vj3, m.b {
    public static final /* synthetic */ int s = 0;
    public final Window e;
    public final boolean f;
    public z51 g;
    public final at2 h;
    public final bt2 i;
    public y13 j;
    public ct2 l;
    public Tab m;
    public boolean n;
    public float p;
    public boolean q;
    public int r;
    public ll k = new ll();
    public int o = 0;

    /* JADX INFO: compiled from: StatusBarColorController.java */
    public interface a {
    }

    static {
        Context context = xz.a;
        int i = R$color.dark_status_bar_color;
        context.getColor(i);
        xz.a.getColor(i);
    }

    public dt2(Window window, boolean z, AppCompatActivity appCompatActivity, aq1 aq1Var, r2 r2Var, z2 z2Var) {
        this.e = window;
        this.f = z;
        yp.b(appCompatActivity, false);
        yp.b(appCompatActivity, true);
        yp.a(appCompatActivity, false);
        yp.a(appCompatActivity, true);
        if (pr1.e(appCompatActivity) && pr1.d()) {
            yp.c(appCompatActivity, R$dimen.omnibox_suggestion_dropdown_bg_elevation);
        }
        this.h = new at2(this, z2Var);
        this.i = new bt2(this);
        aq1Var.p(this.k.b(new x10(16, this)));
        r2Var.f(this);
    }

    @Override // ft2.a
    public final void a() {
        g();
    }

    @Override // org.chromium.chrome.browser.toolbar.top.m.c
    public final void b() {
        if (this.q) {
            g();
        }
    }

    @Override // org.chromium.chrome.browser.toolbar.top.m.b
    public final void c(int i) {
        if (pr1.b() && !this.f) {
            g();
        }
    }

    public final void g() {
        Context context = xz.a;
        int i = R$color.dark_status_bar_color;
        int color = context.getColor(i);
        this.r = color;
        if (this.o == 0) {
            this.o = this.e.getDecorView().getRootView().getContext().getColor(i);
        }
        z7.o(color, this.o | (-14737633), this.p * ((r1 >>> 24) / 255.0f), false);
    }

    @Override // defpackage.vj3
    public final void k(boolean z) {
        g();
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        this.h.destroy();
        z51 z51Var = this.g;
        if (z51Var != null) {
            z51Var.f(this.l);
        }
        y13 y13Var = this.j;
        if (y13Var != null) {
            y13Var.l(this.i);
        }
        ll llVar = this.k;
        if (llVar != null) {
            llVar.a();
            this.k = null;
        }
    }
}
