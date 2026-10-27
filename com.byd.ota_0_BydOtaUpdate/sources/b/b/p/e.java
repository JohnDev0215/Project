package b.b.p;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import b.b.p.b;
import b.b.p.j.g;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: StandaloneActionMode.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b implements g.a {
    public WeakReference<View> E3;
    public boolean F3;
    public boolean G3;
    public b.b.p.j.g H3;
    public Context s;
    public ActionBarContextView x;
    public b.a y;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.s = context;
        this.x = actionBarContextView;
        this.y = aVar;
        b.b.p.j.g gVarS = new b.b.p.j.g(actionBarContextView.getContext()).S(1);
        this.H3 = gVarS;
        gVarS.R(this);
        this.G3 = z;
    }

    @Override // b.b.p.j.g.a
    public boolean a(b.b.p.j.g gVar, MenuItem menuItem) {
        return this.y.b(this, menuItem);
    }

    @Override // b.b.p.j.g.a
    public void b(b.b.p.j.g gVar) {
        k();
        this.x.l();
    }

    @Override // b.b.p.b
    public void c() {
        if (this.F3) {
            return;
        }
        this.F3 = true;
        this.x.sendAccessibilityEvent(32);
        this.y.d(this);
    }

    @Override // b.b.p.b
    public View d() {
        WeakReference<View> weakReference = this.E3;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // b.b.p.b
    public Menu e() {
        return this.H3;
    }

    @Override // b.b.p.b
    public MenuInflater f() {
        return new g(this.x.getContext());
    }

    @Override // b.b.p.b
    public CharSequence g() {
        return this.x.getSubtitle();
    }

    @Override // b.b.p.b
    public CharSequence i() {
        return this.x.getTitle();
    }

    @Override // b.b.p.b
    public void k() {
        this.y.a(this, this.H3);
    }

    @Override // b.b.p.b
    public boolean l() {
        return this.x.j();
    }

    @Override // b.b.p.b
    public void m(View view) {
        this.x.setCustomView(view);
        this.E3 = view != null ? new WeakReference<>(view) : null;
    }

    @Override // b.b.p.b
    public void n(int i2) {
        o(this.s.getString(i2));
    }

    @Override // b.b.p.b
    public void o(CharSequence charSequence) {
        this.x.setSubtitle(charSequence);
    }

    @Override // b.b.p.b
    public void q(int i2) {
        r(this.s.getString(i2));
    }

    @Override // b.b.p.b
    public void r(CharSequence charSequence) {
        this.x.setTitle(charSequence);
    }

    @Override // b.b.p.b
    public void s(boolean z) {
        super.s(z);
        this.x.setTitleOptional(z);
    }
}
