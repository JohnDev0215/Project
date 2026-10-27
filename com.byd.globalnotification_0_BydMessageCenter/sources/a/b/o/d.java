package a.b.o;

import a.b.o.a;
import a.b.o.i.g;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class d extends a implements g.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f97c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionBarContextView f98d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a.InterfaceC0001a f99e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public WeakReference<View> f100f;
    public boolean g;
    public a.b.o.i.g h;

    public d(Context context, ActionBarContextView actionBarContextView, a.InterfaceC0001a interfaceC0001a, boolean z) {
        this.f97c = context;
        this.f98d = actionBarContextView;
        this.f99e = interfaceC0001a;
        a.b.o.i.g gVar = new a.b.o.i.g(actionBarContextView.getContext());
        gVar.l = 1;
        this.h = gVar;
        gVar.f180e = this;
    }

    @Override // a.b.o.i.g.a
    public void a(a.b.o.i.g gVar) {
        i();
        a.b.p.c cVar = this.f98d.f222d;
        if (cVar != null) {
            cVar.n();
        }
    }

    @Override // a.b.o.i.g.a
    public boolean b(a.b.o.i.g gVar, MenuItem menuItem) {
        return this.f99e.c(this, menuItem);
    }

    @Override // a.b.o.a
    public void c() {
        if (this.g) {
            return;
        }
        this.g = true;
        this.f98d.sendAccessibilityEvent(32);
        this.f99e.b(this);
    }

    @Override // a.b.o.a
    public View d() {
        WeakReference<View> weakReference = this.f100f;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // a.b.o.a
    public Menu e() {
        return this.h;
    }

    @Override // a.b.o.a
    public MenuInflater f() {
        return new f(this.f98d.getContext());
    }

    @Override // a.b.o.a
    public CharSequence g() {
        return this.f98d.getSubtitle();
    }

    @Override // a.b.o.a
    public CharSequence h() {
        return this.f98d.getTitle();
    }

    @Override // a.b.o.a
    public void i() {
        this.f99e.a(this, this.h);
    }

    @Override // a.b.o.a
    public boolean j() {
        return this.f98d.r;
    }

    @Override // a.b.o.a
    public void k(View view) {
        this.f98d.setCustomView(view);
        this.f100f = view != null ? new WeakReference<>(view) : null;
    }

    @Override // a.b.o.a
    public void l(int i) {
        this.f98d.setSubtitle(this.f97c.getString(i));
    }

    @Override // a.b.o.a
    public void m(CharSequence charSequence) {
        this.f98d.setSubtitle(charSequence);
    }

    @Override // a.b.o.a
    public void n(int i) {
        this.f98d.setTitle(this.f97c.getString(i));
    }

    @Override // a.b.o.a
    public void o(CharSequence charSequence) {
        this.f98d.setTitle(charSequence);
    }

    @Override // a.b.o.a
    public void p(boolean z) {
        this.f91b = z;
        this.f98d.setTitleOptional(z);
    }
}
