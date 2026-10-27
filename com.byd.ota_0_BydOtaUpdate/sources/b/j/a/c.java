package b.j.a;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;
import b.l.q;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e<?> f1866a;

    public c(e<?> eVar) {
        this.f1866a = eVar;
    }

    public static c b(e<?> eVar) {
        return new c((e) b.f.l.f.c(eVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        e<?> eVar = this.f1866a;
        eVar.y.o(eVar, eVar, fragment);
    }

    public void c() {
        this.f1866a.y.x();
    }

    public void d(Configuration configuration) {
        this.f1866a.y.y(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.f1866a.y.z(menuItem);
    }

    public void f() {
        this.f1866a.y.A();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f1866a.y.B(menu, menuInflater);
    }

    public void h() {
        this.f1866a.y.C();
    }

    public void i() {
        this.f1866a.y.E();
    }

    public void j(boolean z) {
        this.f1866a.y.F(z);
    }

    public boolean k(MenuItem menuItem) {
        return this.f1866a.y.U(menuItem);
    }

    public void l(Menu menu) {
        this.f1866a.y.V(menu);
    }

    public void m() {
        this.f1866a.y.X();
    }

    public void n(boolean z) {
        this.f1866a.y.Y(z);
    }

    public boolean o(Menu menu) {
        return this.f1866a.y.Z(menu);
    }

    public void p() {
        this.f1866a.y.b0();
    }

    public void q() {
        this.f1866a.y.c0();
    }

    public void r() {
        this.f1866a.y.e0();
    }

    public boolean s() {
        return this.f1866a.y.k0();
    }

    public Fragment t(String str) {
        return this.f1866a.y.p0(str);
    }

    public f u() {
        return this.f1866a.y;
    }

    public void v() {
        this.f1866a.y.R0();
    }

    public View w(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f1866a.y.onCreateView(view, str, context, attributeSet);
    }

    public void x(Parcelable parcelable) {
        e<?> eVar = this.f1866a;
        if (!(eVar instanceof q)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        eVar.y.b1(parcelable);
    }

    public Parcelable y() {
        return this.f1866a.y.d1();
    }
}
