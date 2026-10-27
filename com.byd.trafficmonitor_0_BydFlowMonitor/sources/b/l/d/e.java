package b.l.d;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g<?> f2810a;

    public e(g<?> gVar) {
        this.f2810a = gVar;
    }

    public static e b(g<?> gVar) {
        return new e((g) b.h.l.g.d(gVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        g<?> gVar = this.f2810a;
        gVar.f2816f.k(gVar, gVar, fragment);
    }

    public void c() {
        this.f2810a.f2816f.z();
    }

    public void d(Configuration configuration) {
        this.f2810a.f2816f.B(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.f2810a.f2816f.C(menuItem);
    }

    public void f() {
        this.f2810a.f2816f.D();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f2810a.f2816f.E(menu, menuInflater);
    }

    public void h() {
        this.f2810a.f2816f.F();
    }

    public void i() {
        this.f2810a.f2816f.H();
    }

    public void j(boolean z) {
        this.f2810a.f2816f.I(z);
    }

    public boolean k(MenuItem menuItem) {
        return this.f2810a.f2816f.K(menuItem);
    }

    public void l(Menu menu) {
        this.f2810a.f2816f.L(menu);
    }

    public void m() {
        this.f2810a.f2816f.N();
    }

    public void n(boolean z) {
        this.f2810a.f2816f.O(z);
    }

    public boolean o(Menu menu) {
        return this.f2810a.f2816f.P(menu);
    }

    public void p() {
        this.f2810a.f2816f.R();
    }

    public void q() {
        this.f2810a.f2816f.S();
    }

    public void r() {
        this.f2810a.f2816f.U();
    }

    public boolean s() {
        return this.f2810a.f2816f.b0(true);
    }

    public FragmentManager t() {
        return this.f2810a.f2816f;
    }

    public void u() {
        this.f2810a.f2816f.U0();
    }

    public View v(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f2810a.f2816f.v0().onCreateView(view, str, context, attributeSet);
    }

    public void w(Parcelable parcelable) {
        g<?> gVar = this.f2810a;
        if (!(gVar instanceof b.n.t)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        gVar.f2816f.h1(parcelable);
    }

    public Parcelable x() {
        return this.f2810a.f2816f.j1();
    }
}
