package p;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;
import p.b;
import q.g;

/* JADX INFO: compiled from: StandaloneActionMode.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b implements g.a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f6903f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ActionBarContextView f6904g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b.a f6905h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public WeakReference<View> f6906i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f6907j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6908k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public q.g f6909l;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z7) {
        this.f6903f = context;
        this.f6904g = actionBarContextView;
        this.f6905h = aVar;
        q.g gVarW = new q.g(actionBarContextView.getContext()).W(1);
        this.f6909l = gVarW;
        gVarW.V(this);
        this.f6908k = z7;
    }

    @Override // q.g.a
    public boolean a(q.g gVar, MenuItem menuItem) {
        return this.f6905h.c(this, menuItem);
    }

    @Override // q.g.a
    public void b(q.g gVar) {
        k();
        this.f6904g.l();
    }

    @Override // p.b
    public void c() {
        if (this.f6907j) {
            return;
        }
        this.f6907j = true;
        this.f6904g.sendAccessibilityEvent(32);
        this.f6905h.b(this);
    }

    @Override // p.b
    public View d() {
        WeakReference<View> weakReference = this.f6906i;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // p.b
    public Menu e() {
        return this.f6909l;
    }

    @Override // p.b
    public MenuInflater f() {
        return new g(this.f6904g.getContext());
    }

    @Override // p.b
    public CharSequence g() {
        return this.f6904g.getSubtitle();
    }

    @Override // p.b
    public CharSequence i() {
        return this.f6904g.getTitle();
    }

    @Override // p.b
    public void k() {
        this.f6905h.a(this, this.f6909l);
    }

    @Override // p.b
    public boolean l() {
        return this.f6904g.j();
    }

    @Override // p.b
    public void m(View view) {
        this.f6904g.setCustomView(view);
        this.f6906i = view != null ? new WeakReference<>(view) : null;
    }

    @Override // p.b
    public void n(int i8) {
        o(this.f6903f.getString(i8));
    }

    @Override // p.b
    public void o(CharSequence charSequence) {
        this.f6904g.setSubtitle(charSequence);
    }

    @Override // p.b
    public void q(int i8) {
        r(this.f6903f.getString(i8));
    }

    @Override // p.b
    public void r(CharSequence charSequence) {
        this.f6904g.setTitle(charSequence);
    }

    @Override // p.b
    public void s(boolean z7) {
        super.s(z7);
        this.f6904g.setTitleOptional(z7);
    }
}
