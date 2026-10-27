package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final i<?> f693a;

    private g(i<?> iVar) {
        this.f693a = iVar;
    }

    @NonNull
    public static g b(@NonNull i<?> iVar) {
        androidx.core.d.e.c(iVar, "callbacks == null");
        return new g(iVar);
    }

    public void a(@Nullable Fragment fragment) {
        i<?> iVar = this.f693a;
        iVar.f.j(iVar, iVar, fragment);
    }

    public void c() {
        this.f693a.f.y();
    }

    public void d(@NonNull Configuration configuration) {
        this.f693a.f.A(configuration);
    }

    public boolean e(@NonNull MenuItem menuItem) {
        return this.f693a.f.B(menuItem);
    }

    public void f() {
        this.f693a.f.C();
    }

    public boolean g(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        return this.f693a.f.D(menu, menuInflater);
    }

    public void h() {
        this.f693a.f.E();
    }

    public void i() {
        this.f693a.f.G();
    }

    public void j(boolean z) {
        this.f693a.f.H(z);
    }

    public boolean k(@NonNull MenuItem menuItem) {
        return this.f693a.f.J(menuItem);
    }

    public void l(@NonNull Menu menu) {
        this.f693a.f.K(menu);
    }

    public void m() {
        this.f693a.f.M();
    }

    public void n(boolean z) {
        this.f693a.f.N(z);
    }

    public boolean o(@NonNull Menu menu) {
        return this.f693a.f.O(menu);
    }

    public void p() {
        this.f693a.f.Q();
    }

    public void q() {
        this.f693a.f.R();
    }

    public void r() {
        this.f693a.f.T();
    }

    public boolean s() {
        return this.f693a.f.a0(true);
    }

    @NonNull
    public FragmentManager t() {
        return this.f693a.f;
    }

    public void u() {
        this.f693a.f.O0();
    }

    @Nullable
    public View v(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        return this.f693a.f.t0().onCreateView(view, str, context, attributeSet);
    }

    public void w(@Nullable Parcelable parcelable) {
        i<?> iVar = this.f693a;
        if (!(iVar instanceof androidx.lifecycle.q)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        iVar.f.Z0(parcelable);
    }

    @Nullable
    public Parcelable x() {
        return this.f693a.f.b1();
    }
}
