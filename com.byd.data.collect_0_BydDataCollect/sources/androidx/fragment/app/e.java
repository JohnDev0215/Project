package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final g<?> f1433a;

    private e(g<?> gVar) {
        this.f1433a = gVar;
    }

    public static e b(g<?> gVar) {
        return new e((g) b.g.j.h.d(gVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        g<?> gVar = this.f1433a;
        gVar.f1439f.g(gVar, gVar, fragment);
    }

    public void c() {
        this.f1433a.f1439f.r();
    }

    public void d(Configuration configuration) {
        this.f1433a.f1439f.s(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.f1433a.f1439f.t(menuItem);
    }

    public void f() {
        this.f1433a.f1439f.u();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f1433a.f1439f.v(menu, menuInflater);
    }

    public void h() {
        this.f1433a.f1439f.w();
    }

    public void i() {
        this.f1433a.f1439f.y();
    }

    public void j(boolean z) {
        this.f1433a.f1439f.z(z);
    }

    public boolean k(MenuItem menuItem) {
        return this.f1433a.f1439f.A(menuItem);
    }

    public void l(Menu menu) {
        this.f1433a.f1439f.B(menu);
    }

    public void m() {
        this.f1433a.f1439f.D();
    }

    public void n(boolean z) {
        this.f1433a.f1439f.E(z);
    }

    public boolean o(Menu menu) {
        return this.f1433a.f1439f.F(menu);
    }

    public void p() {
        this.f1433a.f1439f.H();
    }

    public void q() {
        this.f1433a.f1439f.I();
    }

    public void r() {
        this.f1433a.f1439f.K();
    }

    public boolean s() {
        return this.f1433a.f1439f.Q(true);
    }

    public Fragment t(String str) {
        return this.f1433a.f1439f.Z(str);
    }

    public j u() {
        return this.f1433a.f1439f;
    }

    public void v() {
        this.f1433a.f1439f.C0();
    }

    public View w(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f1433a.f1439f.h0().onCreateView(view, str, context, attributeSet);
    }

    public void x(Parcelable parcelable) {
        g<?> gVar = this.f1433a;
        if (!(gVar instanceof androidx.lifecycle.s)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        gVar.f1439f.P0(parcelable);
    }

    public Parcelable y() {
        return this.f1433a.f1439f.R0();
    }
}
