package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.e0;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final i<?> f2333a;

    private g(i<?> iVar) {
        this.f2333a = iVar;
    }

    public static g b(i<?> iVar) {
        return new g((i) h0.h.g(iVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        i<?> iVar = this.f2333a;
        iVar.f2339j.j(iVar, iVar, fragment);
    }

    public void c() {
        this.f2333a.f2339j.y();
    }

    public void d(Configuration configuration) {
        this.f2333a.f2339j.A(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.f2333a.f2339j.B(menuItem);
    }

    public void f() {
        this.f2333a.f2339j.C();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.f2333a.f2339j.D(menu, menuInflater);
    }

    public void h() {
        this.f2333a.f2339j.E();
    }

    public void i() {
        this.f2333a.f2339j.G();
    }

    public void j(boolean z6) {
        this.f2333a.f2339j.H(z6);
    }

    public boolean k(MenuItem menuItem) {
        return this.f2333a.f2339j.J(menuItem);
    }

    public void l(Menu menu) {
        this.f2333a.f2339j.K(menu);
    }

    public void m() {
        this.f2333a.f2339j.M();
    }

    public void n(boolean z6) {
        this.f2333a.f2339j.N(z6);
    }

    public boolean o(Menu menu) {
        return this.f2333a.f2339j.O(menu);
    }

    public void p() {
        this.f2333a.f2339j.Q();
    }

    public void q() {
        this.f2333a.f2339j.R();
    }

    public void r() {
        this.f2333a.f2339j.T();
    }

    public boolean s() {
        return this.f2333a.f2339j.a0(true);
    }

    public FragmentManager t() {
        return this.f2333a.f2339j;
    }

    public void u() {
        this.f2333a.f2339j.Q0();
    }

    public View v(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f2333a.f2339j.u0().onCreateView(view, str, context, attributeSet);
    }

    public void w(Parcelable parcelable) {
        i<?> iVar = this.f2333a;
        if (!(iVar instanceof e0)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        iVar.f2339j.f1(parcelable);
    }

    public Parcelable x() {
        return this.f2333a.f2339j.h1();
    }
}
