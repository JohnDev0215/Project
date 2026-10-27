package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.d;
import defpackage.an3;
import defpackage.bn3;
import defpackage.cp0;
import defpackage.cs1;
import defpackage.dp0;
import defpackage.ep0;
import defpackage.eq0;
import defpackage.es1;
import defpackage.fp0;
import defpackage.fs1;
import defpackage.ge2;
import defpackage.gf1;
import defpackage.hp0;
import defpackage.jp0;
import defpackage.kp0;
import defpackage.lp0;
import defpackage.ls1;
import defpackage.n2;
import defpackage.np0;
import defpackage.wx;
import defpackage.x2;
import defpackage.zr1;
import defpackage.zv;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class FragmentActivity extends ComponentActivity implements n2.a {
    public static final /* synthetic */ int B = 0;
    public boolean y;
    public boolean z;
    public final fp0 w = new fp0(new a());
    public final androidx.lifecycle.g x = new androidx.lifecycle.g(this);
    public boolean A = true;

    public class a extends hp0<FragmentActivity> implements cs1, ls1, es1, fs1, bn3, zr1, x2, ge2, np0, gf1 {
        public a() {
            super(FragmentActivity.this);
        }

        @Override // defpackage.gf1
        public final void A0(FragmentManager.c cVar) {
            FragmentActivity.this.A0(cVar);
        }

        @Override // defpackage.ls1
        public final void D(dp0 dp0Var) {
            FragmentActivity.this.D(dp0Var);
        }

        @Override // defpackage.ls1
        public final void F0(dp0 dp0Var) {
            FragmentActivity.this.F0(dp0Var);
        }

        @Override // defpackage.cs1
        public final void H0(wx<Configuration> wxVar) {
            FragmentActivity.this.H0(wxVar);
        }

        @Override // defpackage.hp0
        public final void J(PrintWriter printWriter, String[] strArr) {
            FragmentActivity.this.dump("  ", null, printWriter, strArr);
        }

        @Override // defpackage.hp0
        public final FragmentActivity K() {
            return FragmentActivity.this;
        }

        @Override // defpackage.hp0
        public final LayoutInflater L() {
            FragmentActivity fragmentActivity = FragmentActivity.this;
            return fragmentActivity.getLayoutInflater().cloneInContext(fragmentActivity);
        }

        @Override // defpackage.hp0
        public final void M() {
            FragmentActivity.this.invalidateOptionsMenu();
        }

        @Override // defpackage.gf1
        public final void U0(FragmentManager.c cVar) {
            FragmentActivity.this.U0(cVar);
        }

        @Override // defpackage.x2
        public final androidx.activity.result.a X() {
            return FragmentActivity.this.n;
        }

        @Override // defpackage.es1
        public final void Y(ep0 ep0Var) {
            FragmentActivity.this.Y(ep0Var);
        }

        @Override // defpackage.np0
        public final void a(Fragment fragment) {
            FragmentActivity.this.z1(fragment);
        }

        @Override // defpackage.bn3
        public final an3 h0() {
            return FragmentActivity.this.h0();
        }

        @Override // defpackage.b71
        public final androidx.lifecycle.g j1() {
            return FragmentActivity.this.x;
        }

        @Override // defpackage.fs1
        public final void l0(kp0 kp0Var) {
            FragmentActivity.this.l0(kp0Var);
        }

        @Override // defpackage.es1
        public final void o1(ep0 ep0Var) {
            FragmentActivity.this.o1(ep0Var);
        }

        @Override // defpackage.vd1
        public final View r(int i) {
            return FragmentActivity.this.findViewById(i);
        }

        @Override // defpackage.ge2
        public final androidx.savedstate.a s0() {
            return FragmentActivity.this.i.b;
        }

        @Override // defpackage.fs1
        public final void t(kp0 kp0Var) {
            FragmentActivity.this.t(kp0Var);
        }

        @Override // defpackage.cs1
        public final void u(jp0 jp0Var) {
            FragmentActivity.this.u(jp0Var);
        }

        @Override // defpackage.zr1
        public final OnBackPressedDispatcher v() {
            return FragmentActivity.this.k;
        }

        @Override // defpackage.vd1
        public final boolean x() {
            Window window = FragmentActivity.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }
    }

    public FragmentActivity() {
        int i = 0;
        this.i.b.b("android:support:lifecycle", new cp0(i, this));
        H0(new dp0(0, this));
        this.q.add(new ep0(i, this));
        addOnContextAvailableListener(new zv(this, 1));
    }

    public static boolean y1(FragmentManager fragmentManager) {
        boolean zY1 = false;
        for (Fragment fragment : fragmentManager.c.j()) {
            if (fragment != null) {
                hp0<?> hp0Var = fragment.w;
                if ((hp0Var == null ? null : hp0Var.K()) != null) {
                    zY1 |= y1(fragment.J());
                }
                eq0 eq0Var = fragment.T;
                d.b bVar = d.b.STARTED;
                if (eq0Var != null) {
                    eq0Var.b();
                    if (eq0Var.h.c.compareTo(bVar) >= 0) {
                        fragment.T.h.g();
                        zY1 = true;
                    }
                }
                if (fragment.S.c.compareTo(bVar) >= 0) {
                    fragment.S.g();
                    zY1 = true;
                }
            }
        }
        return zY1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005d  */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void dump(java.lang.String r6, java.io.FileDescriptor r7, java.io.PrintWriter r8, java.lang.String[] r9) {
        /*
            Method dump skipped, instruction units count: 296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentActivity.dump(java.lang.String, java.io.FileDescriptor, java.io.PrintWriter, java.lang.String[]):void");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        this.w.a();
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.x.e(d.a.ON_CREATE);
        lp0 lp0Var = this.w.a.n;
        lp0Var.F = false;
        lp0Var.G = false;
        lp0Var.M.h = false;
        lp0Var.r(1);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = this.w.a.n.f.onCreateView(view, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : viewOnCreateView;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.w.a.n.i();
        this.x.e(d.a.ON_DESTROY);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 6) {
            return this.w.a.n.g();
        }
        return false;
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.z = false;
        this.w.a.n.r(5);
        this.x.e(d.a.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        this.x.e(d.a.ON_RESUME);
        lp0 lp0Var = this.w.a.n;
        lp0Var.F = false;
        lp0Var.G = false;
        lp0Var.M.h = false;
        lp0Var.r(7);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.w.a();
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    @Override // android.app.Activity
    public void onResume() {
        fp0 fp0Var = this.w;
        fp0Var.a();
        super.onResume();
        this.z = true;
        fp0Var.a.n.w(true);
    }

    @Override // android.app.Activity
    public void onStart() {
        fp0 fp0Var = this.w;
        fp0Var.a();
        super.onStart();
        this.A = false;
        boolean z = this.y;
        hp0<?> hp0Var = fp0Var.a;
        if (!z) {
            this.y = true;
            lp0 lp0Var = hp0Var.n;
            lp0Var.F = false;
            lp0Var.G = false;
            lp0Var.M.h = false;
            lp0Var.r(4);
        }
        hp0Var.n.w(true);
        this.x.e(d.a.ON_START);
        lp0 lp0Var2 = hp0Var.n;
        lp0Var2.F = false;
        lp0Var2.G = false;
        lp0Var2.M.h = false;
        lp0Var2.r(5);
    }

    @Override // android.app.Activity
    public final void onStateNotSaved() {
        this.w.a();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.A = true;
        while (y1(x1())) {
        }
        lp0 lp0Var = this.w.a.n;
        lp0Var.G = true;
        lp0Var.M.h = true;
        lp0Var.r(4);
        this.x.e(d.a.ON_STOP);
    }

    public final lp0 x1() {
        return this.w.a.n;
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View viewOnCreateView = this.w.a.n.f.onCreateView(null, str, context, attributeSet);
        return viewOnCreateView == null ? super.onCreateView(str, context, attributeSet) : viewOnCreateView;
    }

    @Override // n2.a
    @Deprecated
    public final void K0() {
    }

    @Deprecated
    public void z1(Fragment fragment) {
    }
}
