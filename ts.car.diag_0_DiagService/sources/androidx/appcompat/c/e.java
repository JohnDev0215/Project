package androidx.appcompat.c;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.c.b;
import androidx.appcompat.view.menu.f;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class e extends b implements f.a {
    private Context e;
    private ActionBarContextView f;
    private b.a g;
    private WeakReference<View> h;
    private boolean i;
    private androidx.appcompat.view.menu.f j;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.e = context;
        this.f = actionBarContextView;
        this.g = aVar;
        androidx.appcompat.view.menu.f fVar = new androidx.appcompat.view.menu.f(actionBarContextView.getContext());
        fVar.S(1);
        this.j = fVar;
        fVar.R(this);
    }

    @Override // androidx.appcompat.view.menu.f.a
    public boolean a(@NonNull androidx.appcompat.view.menu.f fVar, @NonNull MenuItem menuItem) {
        return this.g.b(this, menuItem);
    }

    @Override // androidx.appcompat.view.menu.f.a
    public void b(@NonNull androidx.appcompat.view.menu.f fVar) {
        k();
        this.f.s();
    }

    @Override // androidx.appcompat.c.b
    public void c() {
        if (this.i) {
            return;
        }
        this.i = true;
        this.f.sendAccessibilityEvent(32);
        this.g.d(this);
    }

    @Override // androidx.appcompat.c.b
    public View d() {
        WeakReference<View> weakReference = this.h;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // androidx.appcompat.c.b
    public Menu e() {
        return this.j;
    }

    @Override // androidx.appcompat.c.b
    public MenuInflater f() {
        return new g(this.f.getContext());
    }

    @Override // androidx.appcompat.c.b
    public CharSequence g() {
        return this.f.i();
    }

    @Override // androidx.appcompat.c.b
    public CharSequence i() {
        return this.f.j();
    }

    @Override // androidx.appcompat.c.b
    public void k() {
        this.g.a(this, this.j);
    }

    @Override // androidx.appcompat.c.b
    public boolean l() {
        return this.f.m();
    }

    @Override // androidx.appcompat.c.b
    public void m(View view) {
        this.f.o(view);
        this.h = view != null ? new WeakReference<>(view) : null;
    }

    @Override // androidx.appcompat.c.b
    public void n(int i) {
        o(this.e.getString(i));
    }

    @Override // androidx.appcompat.c.b
    public void o(CharSequence charSequence) {
        this.f.p(charSequence);
    }

    @Override // androidx.appcompat.c.b
    public void q(int i) {
        r(this.e.getString(i));
    }

    @Override // androidx.appcompat.c.b
    public void r(CharSequence charSequence) {
        this.f.q(charSequence);
    }

    @Override // androidx.appcompat.c.b
    public void s(boolean z) {
        super.s(z);
        this.f.r(z);
    }
}
