package defpackage;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import defpackage.b1;
import defpackage.p1;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: StandaloneActionMode.java */
/* JADX INFO: loaded from: classes.dex */
public class e1 extends b1 implements p1.a {
    public Context d;
    public ActionBarContextView e;
    public b1.a f;
    public WeakReference<View> g;
    public boolean h;
    public boolean i;
    public p1 j;

    public e1(Context context, ActionBarContextView actionBarContextView, b1.a aVar, boolean z) {
        this.d = context;
        this.e = actionBarContextView;
        this.f = aVar;
        p1 p1VarW = new p1(actionBarContextView.getContext()).W(1);
        this.j = p1VarW;
        p1VarW.V(this);
        this.i = z;
    }

    @Override // p1.a
    public boolean a(p1 p1Var, MenuItem menuItem) {
        return this.f.b(this, menuItem);
    }

    @Override // p1.a
    public void b(p1 p1Var) {
        k();
        this.e.l();
    }

    @Override // defpackage.b1
    public void c() {
        if (this.h) {
            return;
        }
        this.h = true;
        this.e.sendAccessibilityEvent(32);
        this.f.d(this);
    }

    @Override // defpackage.b1
    public View d() {
        WeakReference<View> weakReference = this.g;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // defpackage.b1
    public Menu e() {
        return this.j;
    }

    @Override // defpackage.b1
    public MenuInflater f() {
        return new g1(this.e.getContext());
    }

    @Override // defpackage.b1
    public CharSequence g() {
        return this.e.getSubtitle();
    }

    @Override // defpackage.b1
    public CharSequence i() {
        return this.e.getTitle();
    }

    @Override // defpackage.b1
    public void k() {
        this.f.a(this, this.j);
    }

    @Override // defpackage.b1
    public boolean l() {
        return this.e.j();
    }

    @Override // defpackage.b1
    public void m(View view) {
        this.e.setCustomView(view);
        this.g = view != null ? new WeakReference<>(view) : null;
    }

    @Override // defpackage.b1
    public void n(int i) {
        o(this.d.getString(i));
    }

    @Override // defpackage.b1
    public void o(CharSequence charSequence) {
        this.e.setSubtitle(charSequence);
    }

    @Override // defpackage.b1
    public void q(int i) {
        r(this.d.getString(i));
    }

    @Override // defpackage.b1
    public void r(CharSequence charSequence) {
        this.e.setTitle(charSequence);
    }

    @Override // defpackage.b1
    public void s(boolean z) {
        super.s(z);
        this.e.setTitleOptional(z);
    }
}
