package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h<?> f1285a;

    private f(h<?> hVar) {
        this.f1285a = hVar;
    }

    public static f b(h<?> hVar) {
        return new f((h) a.g.j.h.d(hVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        h<?> hVar = this.f1285a;
        hVar.f.o(hVar, hVar, fragment);
    }

    public void c() {
        this.f1285a.f.x();
    }

    public void d(Configuration configuration) {
        this.f1285a.f.y(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.f1285a.f.z(menuItem);
    }

    public void f() {
        this.f1285a.f.A();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f1285a.f.B(menu, menuInflater);
    }

    public void h() {
        this.f1285a.f.C();
    }

    public void i() {
        this.f1285a.f.E();
    }

    public void j(boolean z) {
        this.f1285a.f.F(z);
    }

    public boolean k(MenuItem menuItem) {
        return this.f1285a.f.U(menuItem);
    }

    public void l(Menu menu) {
        this.f1285a.f.V(menu);
    }

    public void m() {
        this.f1285a.f.X();
    }

    public void n(boolean z) {
        this.f1285a.f.Y(z);
    }

    public boolean o(Menu menu) {
        return this.f1285a.f.Z(menu);
    }

    public void p() {
        this.f1285a.f.b0();
    }

    public void q() {
        this.f1285a.f.c0();
    }

    public void r() {
        this.f1285a.f.e0();
    }

    public boolean s() {
        return this.f1285a.f.k0();
    }

    public Fragment t(String str) {
        return this.f1285a.f.p0(str);
    }

    public i u() {
        return this.f1285a.f;
    }

    public void v() {
        this.f1285a.f.R0();
    }

    public View w(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f1285a.f.onCreateView(view, str, context, attributeSet);
    }

    public void x(Parcelable parcelable) {
        h<?> hVar = this.f1285a;
        if (!(hVar instanceof androidx.lifecycle.s)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        hVar.f.b1(parcelable);
    }

    public Parcelable y() {
        return this.f1285a.f.d1();
    }
}
