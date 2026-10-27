package p;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class nw2 extends l4 implements qi1 {
    public final Context k;
    public final si1 l;
    public k4 m;
    public WeakReference n;
    public final /* synthetic */ ow2 o;

    public nw2(ow2 ow2Var, Context context, j8 j8Var) {
        this.o = ow2Var;
        this.k = context;
        this.m = j8Var;
        si1 si1Var = new si1(context);
        si1Var.l = 1;
        this.l = si1Var;
        si1Var.e = this;
    }

    @Override // p.l4
    public final void a() {
        ow2 ow2Var = this.o;
        if (ow2Var.q != this) {
            return;
        }
        if (ow2Var.x) {
            ow2Var.r = this;
            ow2Var.s = this.m;
        } else {
            this.m.a(this);
        }
        this.m = null;
        ow2Var.H0(false);
        ActionBarContextView actionBarContextView = ow2Var.n;
        if (actionBarContextView.s == null) {
            actionBarContextView.e();
        }
        ow2Var.k.setHideOnContentScrollEnabled(ow2Var.C);
        ow2Var.q = null;
    }

    @Override // p.l4
    public final View b() {
        WeakReference weakReference = this.n;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    @Override // p.l4
    public final si1 c() {
        return this.l;
    }

    @Override // p.l4
    public final MenuInflater d() {
        return new lh2(this.k);
    }

    @Override // p.qi1
    public final void e(si1 si1Var) {
        if (this.m == null) {
            return;
        }
        i();
        g4 g4Var = this.o.n.l;
        if (g4Var != null) {
            g4Var.l();
        }
    }

    @Override // p.l4
    public final CharSequence f() {
        return this.o.n.getSubtitle();
    }

    @Override // p.l4
    public final CharSequence g() {
        return this.o.n.getTitle();
    }

    @Override // p.qi1
    public final boolean h(si1 si1Var, MenuItem menuItem) {
        k4 k4Var = this.m;
        if (k4Var != null) {
            return k4Var.c(this, menuItem);
        }
        return false;
    }

    @Override // p.l4
    public final void i() {
        if (this.o.q != this) {
            return;
        }
        si1 si1Var = this.l;
        si1Var.w();
        try {
            this.m.d(this, si1Var);
        } finally {
            si1Var.v();
        }
    }

    @Override // p.l4
    public final boolean j() {
        return this.o.n.A;
    }

    @Override // p.l4
    public final void k(View view) {
        this.o.n.setCustomView(view);
        this.n = new WeakReference(view);
    }

    @Override // p.l4
    public final void l(int i) {
        m(this.o.i.getResources().getString(i));
    }

    @Override // p.l4
    public final void m(CharSequence charSequence) {
        this.o.n.setSubtitle(charSequence);
    }

    @Override // p.l4
    public final void n(int i) {
        o(this.o.i.getResources().getString(i));
    }

    @Override // p.l4
    public final void o(CharSequence charSequence) {
        this.o.n.setTitle(charSequence);
    }

    @Override // p.l4
    public final void p(boolean z) {
        this.e = z;
        this.o.n.setTitleOptional(z);
    }
}
