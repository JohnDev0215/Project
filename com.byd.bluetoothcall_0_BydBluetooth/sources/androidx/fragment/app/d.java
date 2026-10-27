package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.t;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f<?> f1370a;

    private d(f<?> fVar) {
        this.f1370a = fVar;
    }

    public static d b(f<?> fVar) {
        return new d((f) b.g.j.h.d(fVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        f<?> fVar = this.f1370a;
        fVar.f.o(fVar, fVar, fragment);
    }

    public void c() {
        this.f1370a.f.x();
    }

    public void d(Configuration configuration) {
        this.f1370a.f.y(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.f1370a.f.z(menuItem);
    }

    public void f() {
        this.f1370a.f.A();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f1370a.f.B(menu, menuInflater);
    }

    public void h() {
        this.f1370a.f.C();
    }

    public void i() {
        this.f1370a.f.E();
    }

    public void j(boolean z) {
        this.f1370a.f.F(z);
    }

    public boolean k(MenuItem menuItem) {
        return this.f1370a.f.U(menuItem);
    }

    public void l(Menu menu) {
        this.f1370a.f.V(menu);
    }

    public void m() {
        this.f1370a.f.X();
    }

    public void n(boolean z) {
        this.f1370a.f.Y(z);
    }

    public boolean o(Menu menu) {
        return this.f1370a.f.Z(menu);
    }

    public void p() {
        this.f1370a.f.b0();
    }

    public void q() {
        this.f1370a.f.c0();
    }

    public void r() {
        this.f1370a.f.e0();
    }

    public boolean s() {
        return this.f1370a.f.k0();
    }

    public Fragment t(String str) {
        return this.f1370a.f.q0(str);
    }

    public g u() {
        return this.f1370a.f;
    }

    public void v() {
        this.f1370a.f.S0();
    }

    public View w(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f1370a.f.onCreateView(view, str, context, attributeSet);
    }

    public void x(Parcelable parcelable) {
        f<?> fVar = this.f1370a;
        if (!(fVar instanceof t)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        fVar.f.c1(parcelable);
    }

    public Parcelable y() {
        return this.f1370a.f.e1();
    }
}
