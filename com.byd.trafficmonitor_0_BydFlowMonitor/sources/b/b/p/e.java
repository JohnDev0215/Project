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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f1539d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ActionBarContextView f1540e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.a f1541f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public WeakReference<View> f1542g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1543h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1544i;
    public b.b.p.j.g j;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.f1539d = context;
        this.f1540e = actionBarContextView;
        this.f1541f = aVar;
        b.b.p.j.g gVarW = new b.b.p.j.g(actionBarContextView.getContext()).W(1);
        this.j = gVarW;
        gVarW.V(this);
        this.f1544i = z;
    }

    @Override // b.b.p.j.g.a
    public boolean a(b.b.p.j.g gVar, MenuItem menuItem) {
        return this.f1541f.b(this, menuItem);
    }

    @Override // b.b.p.j.g.a
    public void b(b.b.p.j.g gVar) {
        k();
        this.f1540e.l();
    }

    @Override // b.b.p.b
    public void c() {
        if (this.f1543h) {
            return;
        }
        this.f1543h = true;
        this.f1540e.sendAccessibilityEvent(32);
        this.f1541f.d(this);
    }

    @Override // b.b.p.b
    public View d() {
        WeakReference<View> weakReference = this.f1542g;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // b.b.p.b
    public Menu e() {
        return this.j;
    }

    @Override // b.b.p.b
    public MenuInflater f() {
        return new g(this.f1540e.getContext());
    }

    @Override // b.b.p.b
    public CharSequence g() {
        return this.f1540e.getSubtitle();
    }

    @Override // b.b.p.b
    public CharSequence i() {
        return this.f1540e.getTitle();
    }

    @Override // b.b.p.b
    public void k() {
        this.f1541f.a(this, this.j);
    }

    @Override // b.b.p.b
    public boolean l() {
        return this.f1540e.j();
    }

    @Override // b.b.p.b
    public void m(View view) {
        this.f1540e.setCustomView(view);
        this.f1542g = view != null ? new WeakReference<>(view) : null;
    }

    @Override // b.b.p.b
    public void n(int i2) {
        o(this.f1539d.getString(i2));
    }

    @Override // b.b.p.b
    public void o(CharSequence charSequence) {
        this.f1540e.setSubtitle(charSequence);
    }

    @Override // b.b.p.b
    public void q(int i2) {
        r(this.f1539d.getString(i2));
    }

    @Override // b.b.p.b
    public void r(CharSequence charSequence) {
        this.f1540e.setTitle(charSequence);
    }

    @Override // b.b.p.b
    public void s(boolean z) {
        super.s(z);
        this.f1540e.setTitleOptional(z);
    }
}
