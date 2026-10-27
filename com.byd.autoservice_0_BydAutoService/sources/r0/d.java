package r0;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;
import t0.q;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f<?> f4677a;

    public d(f<?> fVar) {
        this.f4677a = fVar;
    }

    public static d b(f<?> fVar) {
        return new d((f) k0.h.d(fVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        f<?> fVar = this.f4677a;
        fVar.f4683f.o(fVar, fVar, fragment);
    }

    public void c() {
        this.f4677a.f4683f.x();
    }

    public void d(Configuration configuration) {
        this.f4677a.f4683f.y(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.f4677a.f4683f.z(menuItem);
    }

    public void f() {
        this.f4677a.f4683f.A();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f4677a.f4683f.B(menu, menuInflater);
    }

    public void h() {
        this.f4677a.f4683f.C();
    }

    public void i() {
        this.f4677a.f4683f.E();
    }

    public void j(boolean z3) {
        this.f4677a.f4683f.F(z3);
    }

    public boolean k(MenuItem menuItem) {
        return this.f4677a.f4683f.U(menuItem);
    }

    public void l(Menu menu) {
        this.f4677a.f4683f.V(menu);
    }

    public void m() {
        this.f4677a.f4683f.X();
    }

    public void n(boolean z3) {
        this.f4677a.f4683f.Y(z3);
    }

    public boolean o(Menu menu) {
        return this.f4677a.f4683f.Z(menu);
    }

    public void p() {
        this.f4677a.f4683f.b0();
    }

    public void q() {
        this.f4677a.f4683f.c0();
    }

    public void r() {
        this.f4677a.f4683f.e0();
    }

    public boolean s() {
        return this.f4677a.f4683f.k0();
    }

    public Fragment t(String str) {
        return this.f4677a.f4683f.p0(str);
    }

    public g u() {
        return this.f4677a.f4683f;
    }

    public void v() {
        this.f4677a.f4683f.R0();
    }

    public View w(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f4677a.f4683f.onCreateView(view, str, context, attributeSet);
    }

    public void x(Parcelable parcelable) {
        f<?> fVar = this.f4677a;
        if (!(fVar instanceof q)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        fVar.f4683f.b1(parcelable);
    }

    public Parcelable y() {
        return this.f4677a.f4683f.d1();
    }
}
