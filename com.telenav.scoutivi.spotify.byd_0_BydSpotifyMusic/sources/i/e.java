package i;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.e;
import androidx.appcompat.widget.ActionBarContextView;
import i.b;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: StandaloneActionMode.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b implements e.a {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Context f6768h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ActionBarContextView f6769i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private b.a f6770j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private WeakReference<View> f6771k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f6772l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f6773m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private androidx.appcompat.view.menu.e f6774n;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z6) {
        this.f6768h = context;
        this.f6769i = actionBarContextView;
        this.f6770j = aVar;
        androidx.appcompat.view.menu.e eVarS = new androidx.appcompat.view.menu.e(actionBarContextView.getContext()).S(1);
        this.f6774n = eVarS;
        eVarS.R(this);
        this.f6773m = z6;
    }

    @Override // androidx.appcompat.view.menu.e.a
    public boolean a(androidx.appcompat.view.menu.e eVar, MenuItem menuItem) {
        return this.f6770j.a(this, menuItem);
    }

    @Override // androidx.appcompat.view.menu.e.a
    public void b(androidx.appcompat.view.menu.e eVar) {
        k();
        this.f6769i.l();
    }

    @Override // i.b
    public void c() {
        if (this.f6772l) {
            return;
        }
        this.f6772l = true;
        this.f6769i.sendAccessibilityEvent(32);
        this.f6770j.c(this);
    }

    @Override // i.b
    public View d() {
        WeakReference<View> weakReference = this.f6771k;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // i.b
    public Menu e() {
        return this.f6774n;
    }

    @Override // i.b
    public MenuInflater f() {
        return new g(this.f6769i.getContext());
    }

    @Override // i.b
    public CharSequence g() {
        return this.f6769i.getSubtitle();
    }

    @Override // i.b
    public CharSequence i() {
        return this.f6769i.getTitle();
    }

    @Override // i.b
    public void k() {
        this.f6770j.b(this, this.f6774n);
    }

    @Override // i.b
    public boolean l() {
        return this.f6769i.j();
    }

    @Override // i.b
    public void m(View view) {
        this.f6769i.setCustomView(view);
        this.f6771k = view != null ? new WeakReference<>(view) : null;
    }

    @Override // i.b
    public void n(int i7) {
        o(this.f6768h.getString(i7));
    }

    @Override // i.b
    public void o(CharSequence charSequence) {
        this.f6769i.setSubtitle(charSequence);
    }

    @Override // i.b
    public void q(int i7) {
        r(this.f6768h.getString(i7));
    }

    @Override // i.b
    public void r(CharSequence charSequence) {
        this.f6769i.setTitle(charSequence);
    }

    @Override // i.b
    public void s(boolean z6) {
        super.s(z6);
        this.f6769i.setTitleOptional(z6);
    }
}
