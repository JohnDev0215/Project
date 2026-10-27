package p;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class tf2 extends l4 implements qi1 {
    public Context k;
    public ActionBarContextView l;
    public k4 m;
    public WeakReference n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public si1 f189p;

    @Override // p.l4
    public final void a() {
        if (this.o) {
            return;
        }
        this.o = true;
        this.m.a(this);
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
        return this.f189p;
    }

    @Override // p.l4
    public final MenuInflater d() {
        return new lh2(this.l.getContext());
    }

    @Override // p.qi1
    public final void e(si1 si1Var) {
        i();
        g4 g4Var = this.l.l;
        if (g4Var != null) {
            g4Var.l();
        }
    }

    @Override // p.l4
    public final CharSequence f() {
        return this.l.getSubtitle();
    }

    @Override // p.l4
    public final CharSequence g() {
        return this.l.getTitle();
    }

    @Override // p.qi1
    public final boolean h(si1 si1Var, MenuItem menuItem) {
        return this.m.c(this, menuItem);
    }

    @Override // p.l4
    public final void i() {
        this.m.d(this, this.f189p);
    }

    @Override // p.l4
    public final boolean j() {
        return this.l.A;
    }

    @Override // p.l4
    public final void k(View view) {
        this.l.setCustomView(view);
        this.n = view != null ? new WeakReference(view) : null;
    }

    @Override // p.l4
    public final void l(int i) {
        m(this.k.getString(i));
    }

    @Override // p.l4
    public final void m(CharSequence charSequence) {
        this.l.setSubtitle(charSequence);
    }

    @Override // p.l4
    public final void n(int i) {
        o(this.k.getString(i));
    }

    @Override // p.l4
    public final void o(CharSequence charSequence) {
        this.l.setTitle(charSequence);
    }

    @Override // p.l4
    public final void p(boolean z) {
        this.e = z;
        this.l.setTitleOptional(z);
    }
}
