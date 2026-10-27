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
    private Context f1666d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ActionBarContextView f1667e;
    private b.a f;
    private WeakReference<View> g;
    private boolean h;
    private boolean i;
    private androidx.appcompat.view.menu.g j;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.f1666d = context;
        this.f1667e = actionBarContextView;
        this.f = aVar;
        androidx.appcompat.view.menu.g gVarW = new androidx.appcompat.view.menu.g(actionBarContextView.getContext()).W(1);
        this.j = gVarW;
        gVarW.V(this);
        this.i = z;
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        return this.f.b(this, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void b(androidx.appcompat.view.menu.g gVar) {
        k();
        this.f1667e.l();
    }

    @Override // b.a.o.b
    public void c() {
        if (this.h) {
            return;
        }
        this.h = true;
        this.f1667e.sendAccessibilityEvent(32);
        this.f.d(this);
    }

    @Override // b.a.o.b
    public View d() {
        WeakReference<View> weakReference = this.g;
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
        return new g(this.f1667e.getContext());
    }

    @Override // b.a.o.b
    public CharSequence g() {
        return this.f1667e.getSubtitle();
    }

    @Override // b.a.o.b
    public CharSequence i() {
        return this.f1667e.getTitle();
    }

    @Override // b.a.o.b
    public void k() {
        this.f.a(this, this.j);
    }

    @Override // b.a.o.b
    public boolean l() {
        return this.f1667e.j();
    }

    @Override // b.a.o.b
    public void m(View view) {
        this.f1667e.setCustomView(view);
        this.g = view != null ? new WeakReference<>(view) : null;
    }

    @Override // b.a.o.b
    public void n(int i) {
        o(this.f1666d.getString(i));
    }

    @Override // b.a.o.b
    public void o(CharSequence charSequence) {
        this.f1667e.setSubtitle(charSequence);
    }

    @Override // b.a.o.b
    public void q(int i) {
        r(this.f1666d.getString(i));
    }

    @Override // b.a.o.b
    public void r(CharSequence charSequence) {
        this.f1667e.setTitle(charSequence);
    }

    @Override // b.a.o.b
    public void s(boolean z) {
        super.s(z);
        this.f1667e.setTitleOptional(z);
    }
}
