package v0;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;
import x0.q;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f<?> f8357a;

    public d(f<?> fVar) {
        this.f8357a = fVar;
    }

    public static d b(f<?> fVar) {
        return new d((f) n0.h.f(fVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        f<?> fVar = this.f8357a;
        fVar.f8363h.v(fVar, fVar, fragment);
    }

    public void c() {
        this.f8357a.f8363h.E();
    }

    public void d(Configuration configuration) {
        this.f8357a.f8363h.F(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.f8357a.f8363h.G(menuItem);
    }

    public void f() {
        this.f8357a.f8363h.H();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f8357a.f8363h.I(menu, menuInflater);
    }

    public void h() {
        this.f8357a.f8363h.J();
    }

    public void i() {
        this.f8357a.f8363h.L();
    }

    public void j(boolean z7) {
        this.f8357a.f8363h.M(z7);
    }

    public boolean k(MenuItem menuItem) {
        return this.f8357a.f8363h.b0(menuItem);
    }

    public void l(Menu menu) {
        this.f8357a.f8363h.c0(menu);
    }

    public void m() {
        this.f8357a.f8363h.e0();
    }

    public void n(boolean z7) {
        this.f8357a.f8363h.f0(z7);
    }

    public boolean o(Menu menu) {
        return this.f8357a.f8363h.g0(menu);
    }

    public void p() {
        this.f8357a.f8363h.i0();
    }

    public void q() {
        this.f8357a.f8363h.j0();
    }

    public void r() {
        this.f8357a.f8363h.l0();
    }

    public boolean s() {
        return this.f8357a.f8363h.r0();
    }

    public Fragment t(String str) {
        return this.f8357a.f8363h.x0(str);
    }

    public g u() {
        return this.f8357a.f8363h;
    }

    public void v() {
        this.f8357a.f8363h.W0();
    }

    public View w(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f8357a.f8363h.onCreateView(view, str, context, attributeSet);
    }

    public void x(Parcelable parcelable) {
        f<?> fVar = this.f8357a;
        if (!(fVar instanceof q)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        fVar.f8363h.f1(parcelable);
    }

    public Parcelable y() {
        return this.f8357a.f8363h.h1();
    }
}
