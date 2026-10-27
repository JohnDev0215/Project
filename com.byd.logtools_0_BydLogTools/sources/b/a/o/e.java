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
    private Context f2176d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ActionBarContextView f2177e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b.a f2178f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private WeakReference<View> f2179g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f2180h;
    private boolean i;
    private androidx.appcompat.view.menu.g j;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.f2176d = context;
        this.f2177e = actionBarContextView;
        this.f2178f = aVar;
        androidx.appcompat.view.menu.g gVarW = new androidx.appcompat.view.menu.g(actionBarContextView.getContext()).W(1);
        this.j = gVarW;
        gVarW.V(this);
        this.i = z;
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        return this.f2178f.b(this, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void b(androidx.appcompat.view.menu.g gVar) {
        k();
        this.f2177e.l();
    }

    @Override // b.a.o.b
    public void c() {
        if (this.f2180h) {
            return;
        }
        this.f2180h = true;
        this.f2177e.sendAccessibilityEvent(32);
        this.f2178f.d(this);
    }

    @Override // b.a.o.b
    public View d() {
        WeakReference<View> weakReference = this.f2179g;
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
        return new g(this.f2177e.getContext());
    }

    @Override // b.a.o.b
    public CharSequence g() {
        return this.f2177e.getSubtitle();
    }

    @Override // b.a.o.b
    public CharSequence i() {
        return this.f2177e.getTitle();
    }

    @Override // b.a.o.b
    public void k() {
        this.f2178f.a(this, this.j);
    }

    @Override // b.a.o.b
    public boolean l() {
        return this.f2177e.j();
    }

    @Override // b.a.o.b
    public void m(View view) {
        this.f2177e.setCustomView(view);
        this.f2179g = view != null ? new WeakReference<>(view) : null;
    }

    @Override // b.a.o.b
    public void n(int i) {
        o(this.f2176d.getString(i));
    }

    @Override // b.a.o.b
    public void o(CharSequence charSequence) {
        this.f2177e.setSubtitle(charSequence);
    }

    @Override // b.a.o.b
    public void q(int i) {
        r(this.f2176d.getString(i));
    }

    @Override // b.a.o.b
    public void r(CharSequence charSequence) {
        this.f2177e.setTitle(charSequence);
    }

    @Override // b.a.o.b
    public void s(boolean z) {
        super.s(z);
        this.f2177e.setTitleOptional(z);
    }
}
