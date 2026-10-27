package defpackage;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Handler;
import android.util.SparseBooleanArray;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.r30;
import java.util.Objects;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;

/* JADX INFO: compiled from: PartialCustomTabDisplayManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class cw1 extends r30 implements bx {
    public final c01 A;
    public final Activity e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final boolean j;
    public final r30.a k;
    public final q2 l;
    public final rq0 m;
    public final boolean n;
    public final boolean o;
    public final boolean p;
    public final int q;
    public final int r;
    public final ow1 s;
    public final SparseBooleanArray t = new SparseBooleanArray();
    public yv1 u;
    public int v;
    public View w;
    public CustomTabToolbar x;
    public int y;
    public final fw1 z;

    public cw1(AppCompatActivity appCompatActivity, int i, int i2, int i3, boolean z, z70 z70Var, q2 q2Var, rq0 rq0Var, boolean z2, boolean z3, boolean z4, int i4, int i5, int i6) {
        c01 c01Var = new c01(6, this);
        this.A = c01Var;
        this.e = appCompatActivity;
        this.h = i;
        this.i = i2;
        this.f = i3;
        this.j = z;
        this.k = z70Var;
        this.m = rq0Var;
        this.n = z2;
        this.o = z3;
        this.p = z4;
        this.g = i4;
        this.q = i5;
        this.r = i6;
        this.l = q2Var;
        q2Var.f(this);
        this.s = ow1.a(appCompatActivity, new wq0(26, this));
        this.z = new fw1();
        int iN = n();
        this.v = iN;
        this.u = c01Var.i(iN, i5, i6, false);
    }

    public static int o(Activity activity, int i, int i2, nw2<Integer> nw2Var, int i3, boolean z) {
        vj1.h.getClass();
        if (vj1.o(activity)) {
            return 3;
        }
        if (i == 0 && i2 == 0) {
            return 3;
        }
        return (i <= 0 || i2 <= 0) ? i > 0 ? (nw2Var.get().intValue() < i3 || !z) ? 3 : 2 : i2 > 0 ? 1 : 3 : (nw2Var.get().intValue() < i3 || !z) ? 1 : 2;
    }

    @Override // defpackage.r30
    public final void Y0() {
        this.u.Y0();
    }

    @Override // defpackage.r30, defpackage.ym0
    public final void c() {
        this.u.c();
    }

    @Override // defpackage.r30, defpackage.ym0
    public final void d() {
        this.u.d();
    }

    @Override // defpackage.r30
    public final boolean e() {
        yv1 yv1Var = this.u;
        yv1Var.getClass();
        return yv1Var instanceof aw1;
    }

    @Override // defpackage.r30
    public final void g() {
        this.u.g();
    }

    @Override // defpackage.r30
    public final void i(Runnable runnable) {
        this.u.i(runnable);
    }

    @Override // defpackage.r30
    public final void k(View view, CustomTabToolbar customTabToolbar, int i) {
        this.w = view;
        this.x = customTabToolbar;
        this.y = i;
        this.u.k(view, customTabToolbar, i);
    }

    @Override // defpackage.r30
    public final void m(float f) {
        this.u.m(f);
    }

    public final int n() {
        Activity activity = this.e;
        int i = this.i;
        int i2 = this.h;
        ow1 ow1Var = this.s;
        Objects.requireNonNull(ow1Var);
        return o(activity, i, i2, new yo(4, ow1Var), this.f, bq.p.a());
    }

    @Override // defpackage.bx
    public final void onConfigurationChanged(Configuration configuration) {
        int iN = n();
        if (iN == this.v) {
            this.u.z(configuration.orientation);
            return;
        }
        yv1 yv1Var = this.u;
        SparseBooleanArray sparseBooleanArray = this.t;
        if (yv1Var != null) {
            sparseBooleanArray.put(yv1Var.s(), this.u.x());
            this.u.g();
        }
        boolean z = sparseBooleanArray.get(iN, false);
        int i = this.r;
        this.u = this.A.i(iN, this.q, i, z);
        this.v = iN;
        new Handler().postDelayed(new aw(23, this), 150L);
    }
}
