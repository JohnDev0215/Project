package b.b.e;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import b.b.e.b;
import b.b.e.j.g;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: StandaloneActionMode.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b implements g.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f652d;
    public ActionBarContextView e;
    public b.a f;
    public WeakReference<View> g;
    public boolean h;
    public boolean i;
    public b.b.e.j.g j;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.f652d = context;
        this.e = actionBarContextView;
        this.f = aVar;
        b.b.e.j.g gVarW = new b.b.e.j.g(actionBarContextView.getContext()).W(1);
        this.j = gVarW;
        gVarW.V(this);
        this.i = z;
    }

    @Override // b.b.e.j.g.a
    public boolean a(b.b.e.j.g gVar, MenuItem menuItem) {
        return this.f.b(this, menuItem);
    }

    @Override // b.b.e.j.g.a
    public void b(b.b.e.j.g gVar) {
        k();
        this.e.l();
    }

    @Override // b.b.e.b
    public void c() {
        if (this.h) {
            return;
        }
        this.h = true;
        this.e.sendAccessibilityEvent(32);
        this.f.d(this);
    }

    @Override // b.b.e.b
    public View d() {
        WeakReference<View> weakReference = this.g;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // b.b.e.b
    public Menu e() {
        return this.j;
    }

    @Override // b.b.e.b
    public MenuInflater f() {
        return new g(this.e.getContext());
    }

    @Override // b.b.e.b
    public CharSequence g() {
        return this.e.getSubtitle();
    }

    @Override // b.b.e.b
    public CharSequence i() {
        return this.e.getTitle();
    }

    @Override // b.b.e.b
    public void k() {
        this.f.a(this, this.j);
    }

    @Override // b.b.e.b
    public boolean l() {
        return this.e.j();
    }

    @Override // b.b.e.b
    public void m(View view) {
        this.e.setCustomView(view);
        this.g = view != null ? new WeakReference<>(view) : null;
    }

    @Override // b.b.e.b
    public void n(int i) {
        o(this.f652d.getString(i));
    }

    @Override // b.b.e.b
    public void o(CharSequence charSequence) {
        this.e.setSubtitle(charSequence);
    }

    @Override // b.b.e.b
    public void q(int i) {
        r(this.f652d.getString(i));
    }

    @Override // b.b.e.b
    public void r(CharSequence charSequence) {
        this.e.setTitle(charSequence);
    }

    @Override // b.b.e.b
    public void s(boolean z) {
        super.s(z);
        this.e.setTitleOptional(z);
    }
}
