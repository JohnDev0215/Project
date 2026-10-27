package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.r;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f<?> f1122a;

    private d(f<?> fVar) {
        this.f1122a = fVar;
    }

    public static d b(f<?> fVar) {
        return new d((f) b.g.j.h.d(fVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        f<?> fVar = this.f1122a;
        fVar.f.p(fVar, fVar, fragment);
    }

    public void c() {
        this.f1122a.f.y();
    }

    public void d(Configuration configuration) {
        this.f1122a.f.z(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.f1122a.f.A(menuItem);
    }

    public void f() {
        this.f1122a.f.B();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f1122a.f.C(menu, menuInflater);
    }

    public void h() {
        this.f1122a.f.D();
    }

    public void i() {
        this.f1122a.f.F();
    }

    public void j(boolean z) {
        this.f1122a.f.G(z);
    }

    public boolean k(MenuItem menuItem) {
        return this.f1122a.f.V(menuItem);
    }

    public void l(Menu menu) {
        this.f1122a.f.W(menu);
    }

    public void m() {
        this.f1122a.f.Y();
    }

    public void n(boolean z) {
        this.f1122a.f.Z(z);
    }

    public boolean o(Menu menu) {
        return this.f1122a.f.a0(menu);
    }

    public void p() {
        this.f1122a.f.c0();
    }

    public void q() {
        this.f1122a.f.d0();
    }

    public void r() {
        this.f1122a.f.f0();
    }

    public boolean s() {
        return this.f1122a.f.l0();
    }

    public Fragment t(String str) {
        return this.f1122a.f.q0(str);
    }

    public g u() {
        return this.f1122a.f;
    }

    public void v() {
        this.f1122a.f.S0();
    }

    public View w(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f1122a.f.onCreateView(view, str, context, attributeSet);
    }

    public void x(Parcelable parcelable) {
        f<?> fVar = this.f1122a;
        if (!(fVar instanceof r)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        fVar.f.c1(parcelable);
    }

    public Parcelable y() {
        return this.f1122a.f.e1();
    }
}
