package defpackage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;
import android.view.Window;
import org.chromium.chrome.R$bool;
import org.chromium.chrome.R$color;

/* JADX INFO: compiled from: TabbedNavigationBarColorController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z53 {
    public final Window a;
    public final ViewGroup b;
    public final Context c;
    public final rq0 d;
    public final int e;
    public final boolean f;
    public final y13 g;
    public final w53 h;
    public final x53 i;
    public z51 j;
    public rm0 k;
    public ll l = new ll();
    public int m;
    public boolean n;
    public boolean o;
    public boolean p;
    public float q;

    public z53(Window window, y13 y13Var, aq1 aq1Var, rq0 rq0Var) {
        this.a = window;
        ViewGroup viewGroup = (ViewGroup) window.getDecorView().getRootView();
        this.b = viewGroup;
        Context context = viewGroup.getContext();
        this.c = context;
        this.e = context.getColor(R$color.default_scrim_color);
        this.d = rq0Var;
        this.f = context.getResources().getBoolean(R$bool.window_light_navigation_bar);
        this.g = y13Var;
        w53 w53Var = new w53(this);
        this.h = w53Var;
        y13Var.m(w53Var);
        x53 x53Var = new x53(this);
        this.i = x53Var;
        rq0Var.h(x53Var);
        aq1Var.p(this.l.b(new x10(2, this)));
        a();
    }

    @SuppressLint({"NewApi"})
    public final void a() {
        boolean zR;
        ViewGroup viewGroup = this.b;
        boolean zA = g90.a(viewGroup.getContext());
        y13 y13Var = this.g;
        boolean z = false;
        if (zA || oj2.z(viewGroup.getContext())) {
            zR = y13Var.r();
        } else {
            z51 z51Var = this.j;
            zR = y13Var.r() && !(z51Var != null && z51Var.q(2) && !this.o);
        }
        boolean z2 = (zR & (!uh3.j())) | this.p;
        this.n = z2;
        Context context = this.c;
        Window window = this.a;
        int color = z2 ? context.getColor(R$color.toolbar_background_primary_dark) : ii2.a(window.getContext());
        if (color == this.m) {
            return;
        }
        this.m = color;
        window.setNavigationBarColor(color);
        window.setNavigationBarDividerColor(this.n ? context.getColor(R$color.bottom_system_nav_divider_color_light) : ii2.e(window.getContext()));
        if (!this.n && this.f) {
            z = true;
        }
        uh3.m(viewGroup, z);
    }
}
