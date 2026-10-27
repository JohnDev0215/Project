package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class kb {
    public final mb<?> a;

    public kb(mb<?> mbVar) {
        this.a = mbVar;
    }

    public static kb b(mb<?> mbVar) {
        return new kb((mb) z8.d(mbVar, "callbacks == null"));
    }

    public void a(Fragment fragment) {
        mb<?> mbVar = this.a;
        mbVar.f.o(mbVar, mbVar, fragment);
    }

    public void c() {
        this.a.f.x();
    }

    public void d(Configuration configuration) {
        this.a.f.y(configuration);
    }

    public boolean e(MenuItem menuItem) {
        return this.a.f.z(menuItem);
    }

    public void f() {
        this.a.f.A();
    }

    public boolean g(Menu menu, MenuInflater menuInflater) {
        return this.a.f.B(menu, menuInflater);
    }

    public void h() {
        this.a.f.C();
    }

    public void i() {
        this.a.f.E();
    }

    public void j(boolean z) {
        this.a.f.F(z);
    }

    public boolean k(MenuItem menuItem) {
        return this.a.f.U(menuItem);
    }

    public void l(Menu menu) {
        this.a.f.V(menu);
    }

    public void m() {
        this.a.f.X();
    }

    public void n(boolean z) {
        this.a.f.Y(z);
    }

    public boolean o(Menu menu) {
        return this.a.f.Z(menu);
    }

    public void p() {
        this.a.f.b0();
    }

    public void q() {
        this.a.f.c0();
    }

    public void r() {
        this.a.f.e0();
    }

    public boolean s() {
        return this.a.f.k0();
    }

    public Fragment t(String str) {
        return this.a.f.p0(str);
    }

    public nb u() {
        return this.a.f;
    }

    public void v() {
        this.a.f.R0();
    }

    public View w(View view, String str, Context context, AttributeSet attributeSet) {
        return this.a.f.onCreateView(view, str, context, attributeSet);
    }

    public void x(Parcelable parcelable) {
        mb<?> mbVar = this.a;
        if (!(mbVar instanceof pc)) {
            throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
        }
        mbVar.f.b1(parcelable);
    }

    public Parcelable y() {
        return this.a.f.d1();
    }
}
