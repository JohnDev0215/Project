package m;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;
import m.b;
import n.g;

/* JADX INFO: compiled from: StandaloneActionMode.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b implements g.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f3737d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ActionBarContextView f3738e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.a f3739f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public WeakReference<View> f3740g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3741h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3742i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public n.g f3743j;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z3) {
        this.f3737d = context;
        this.f3738e = actionBarContextView;
        this.f3739f = aVar;
        n.g gVarW = new n.g(actionBarContextView.getContext()).W(1);
        this.f3743j = gVarW;
        gVarW.V(this);
        this.f3742i = z3;
    }

    @Override // n.g.a
    public boolean a(n.g gVar, MenuItem menuItem) {
        return this.f3739f.b(this, menuItem);
    }

    @Override // n.g.a
    public void b(n.g gVar) {
        k();
        this.f3738e.l();
    }

    @Override // m.b
    public void c() {
        if (this.f3741h) {
            return;
        }
        this.f3741h = true;
        this.f3738e.sendAccessibilityEvent(32);
        this.f3739f.d(this);
    }

    @Override // m.b
    public View d() {
        WeakReference<View> weakReference = this.f3740g;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // m.b
    public Menu e() {
        return this.f3743j;
    }

    @Override // m.b
    public MenuInflater f() {
        return new g(this.f3738e.getContext());
    }

    @Override // m.b
    public CharSequence g() {
        return this.f3738e.getSubtitle();
    }

    @Override // m.b
    public CharSequence i() {
        return this.f3738e.getTitle();
    }

    @Override // m.b
    public void k() {
        this.f3739f.a(this, this.f3743j);
    }

    @Override // m.b
    public boolean l() {
        return this.f3738e.j();
    }

    @Override // m.b
    public void m(View view) {
        this.f3738e.setCustomView(view);
        this.f3740g = view != null ? new WeakReference<>(view) : null;
    }

    @Override // m.b
    public void n(int i3) {
        o(this.f3737d.getString(i3));
    }

    @Override // m.b
    public void o(CharSequence charSequence) {
        this.f3738e.setSubtitle(charSequence);
    }

    @Override // m.b
    public void q(int i3) {
        r(this.f3737d.getString(i3));
    }

    @Override // m.b
    public void r(CharSequence charSequence) {
        this.f3738e.setTitle(charSequence);
    }

    @Override // m.b
    public void s(boolean z3) {
        super.s(z3);
        this.f3738e.setTitleOptional(z3);
    }
}
