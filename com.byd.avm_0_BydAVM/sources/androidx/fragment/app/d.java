package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.q;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f<?> f1000a;

    private d(f<?> fVar) {
        this.f1000a = fVar;
    }

    public static d b(f<?> fVar) {
        return new d((f) b.g.j.h.d(fVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        f<?> fVar = this.f1000a;
        fVar.f.o(fVar, fVar, fragment);
    }

    public void c() {
        this.f1000a.f.x();
    }

    public void d(Configuration configuration) {
        this.f1000a.f.y(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.f1000a.f.z(menuItem);
    }

    public void f() {
        this.f1000a.f.A();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f1000a.f.B(menu, menuInflater);
    }

    public void h() {
        this.f1000a.f.C();
    }

    public void i() {
        this.f1000a.f.E();
    }

    public void j(boolean z) {
        this.f1000a.f.F(z);
    }

    public boolean k(MenuItem menuItem) {
        return this.f1000a.f.U(menuItem);
    }

    public void l(Menu menu) {
        this.f1000a.f.V(menu);
    }

    public void m() {
        this.f1000a.f.X();
    }

    public void n(boolean z) {
        this.f1000a.f.Y(z);
    }

    public boolean o(Menu menu) {
        return this.f1000a.f.Z(menu);
    }

    public void p() {
        this.f1000a.f.b0();
    }

    public void q() {
        this.f1000a.f.c0();
    }

    public void r() {
        this.f1000a.f.e0();
    }

    public boolean s() {
        return this.f1000a.f.k0();
    }

    public Fragment t(String str) {
        return this.f1000a.f.p0(str);
    }

    public g u() {
        return this.f1000a.f;
    }

    public void v() {
        this.f1000a.f.R0();
    }

    public View w(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f1000a.f.onCreateView(view, str, context, attributeSet);
    }

    public void x(Parcelable parcelable) {
        f<?> fVar = this.f1000a;
        if (!(fVar instanceof q)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        fVar.f.b1(parcelable);
    }

    public Parcelable y() {
        return this.f1000a.f.d1();
    }
}
