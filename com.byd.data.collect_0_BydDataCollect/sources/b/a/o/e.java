package b.a.o;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.widget.ActionBarContextView;
import b.a.o.b;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: StandaloneActionMode.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b implements g.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f2184d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ActionBarContextView f2185e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b.a f2186f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private WeakReference<View> f2187g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2188h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f2189i;
    private androidx.appcompat.view.menu.g j;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.f2184d = context;
        this.f2185e = actionBarContextView;
        this.f2186f = aVar;
        androidx.appcompat.view.menu.g gVarW = new androidx.appcompat.view.menu.g(actionBarContextView.getContext()).W(1);
        this.j = gVarW;
        gVarW.V(this);
        this.f2189i = z;
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        return this.f2186f.b(this, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void b(androidx.appcompat.view.menu.g gVar) {
        k();
        this.f2185e.l();
    }

    @Override // b.a.o.b
    public void c() {
        if (this.f2188h) {
            return;
        }
        this.f2188h = true;
        this.f2185e.sendAccessibilityEvent(32);
        this.f2186f.d(this);
    }

    @Override // b.a.o.b
    public View d() {
        WeakReference<View> weakReference = this.f2187g;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // b.a.o.b
    public Menu e() {
        return this.j;
    }

    @Override // b.a.o.b
    public MenuInflater f() {
        return new g(this.f2185e.getContext());
    }

    @Override // b.a.o.b
    public CharSequence g() {
        return this.f2185e.getSubtitle();
    }

    @Override // b.a.o.b
    public CharSequence i() {
        return this.f2185e.getTitle();
    }

    @Override // b.a.o.b
    public void k() {
        this.f2186f.a(this, this.j);
    }

    @Override // b.a.o.b
    public boolean l() {
        return this.f2185e.j();
    }

    @Override // b.a.o.b
    public void m(View view) {
        this.f2185e.setCustomView(view);
        this.f2187g = view != null ? new WeakReference<>(view) : null;
    }

    @Override // b.a.o.b
    public void n(int i2) {
        o(this.f2184d.getString(i2));
    }

    @Override // b.a.o.b
    public void o(CharSequence charSequence) {
        this.f2185e.setSubtitle(charSequence);
    }

    @Override // b.a.o.b
    public void q(int i2) {
        r(this.f2184d.getString(i2));
    }

    @Override // b.a.o.b
    public void r(CharSequence charSequence) {
        this.f2185e.setTitle(charSequence);
    }

    @Override // b.a.o.b
    public void s(boolean z) {
        super.s(z);
        this.f2185e.setTitleOptional(z);
    }
}
