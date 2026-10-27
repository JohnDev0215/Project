package defpackage;

import android.app.Activity;
import android.graphics.RectF;
import android.view.MotionEvent;
import defpackage.dx2;
import java.util.ArrayList;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: OverlayPanel.java */
/* JADX INFO: loaded from: classes.dex */
public final class it1 extends lt1 implements ApplicationStatus.c, dx2.a, ls0, je2 {
    public float o;
    public float p;
    public boolean q;
    public float r;
    public float s;

    @Override // defpackage.je2
    public final boolean G(long j) {
        return true;
    }

    public final void K(float f) {
        this.q = true;
        int iC = C(this.k - ((218.0f * f) / 2000.0f));
        long jR = a8.r(Math.round(Math.abs(((D(Integer.valueOf(iC)) - this.k) * 2000.0f) / f)), 116L, 350L);
        Integer numValueOf = Integer.valueOf(iC);
        this.m = numValueOf;
        this.n = 14;
        D(numValueOf);
        if (jR > 0) {
            throw null;
        }
    }

    public final void M() {
        if (this.q) {
            return;
        }
        this.q = true;
        int iC = C(this.k);
        long jR = a8.r(Math.round(Math.abs(((D(Integer.valueOf(iC)) - this.k) * 2000.0f) / 1750.0f)), 116L, 350L);
        Integer numValueOf = Integer.valueOf(iC);
        this.m = numValueOf;
        this.n = 13;
        D(numValueOf);
        if (jR > 0) {
            throw null;
        }
    }

    @Override // defpackage.je2
    public final void M0(float f, float f2, float f3, int i) {
        if (f2 == this.s && f == this.r) {
            return;
        }
        this.r = f;
        this.s = f2;
        float f4 = this.e;
        if (f != f4 || f2 != this.f || f3 != this.g) {
            this.e = f;
            this.f = f2;
            this.g = f3;
            boolean z = true;
            if (!(f <= 680.0f)) {
                f = 600.0f;
            }
            this.h = f;
            D(4);
            if (E()) {
                boolean z2 = f4 <= 680.0f;
                boolean z3 = this.e <= 680.0f;
                if ((!z3 || !z2) && (z3 || z2 || f != f4)) {
                    z = false;
                }
                if (z) {
                    Integer num = this.m;
                    if (num != null && num.intValue() == 0) {
                        H(D(0));
                        throw null;
                    }
                    Integer num2 = this.m;
                    int i2 = this.n;
                    this.m = num2;
                    this.n = i2;
                    D(num2);
                    throw null;
                }
                this.l.getHandler().post(new kt1(this));
            }
        }
        if (E()) {
            throw null;
        }
    }

    public final void O(float f) {
        H(a8.o(this.o - f, D(4), D(2)));
        throw null;
    }

    public final boolean P(float f, float f2) {
        float f3 = this.j;
        if (f2 >= f3 && f2 <= f3 + this.k) {
            float f4 = this.i;
            if (f >= f4 && f <= f4 + this.h) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.je2
    public final ke2 U(RectF rectF, ResourceManager resourceManager) {
        return null;
    }

    @Override // defpackage.je2
    public final boolean a() {
        if (!E()) {
            return false;
        }
        I(2, true);
        return true;
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        vj1.h.getClass();
        if (vj1.o(null) && (i == 4 || i == 3)) {
            return;
        }
        if (i == 3 || i == 5 || i == 6) {
            I(0, false);
        }
    }

    @Override // defpackage.ls0
    public final void e() {
        M();
    }

    @Override // dx2.a
    public final void f(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
        K(f4 * 0.0f);
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return null;
    }

    @Override // dx2.a
    public final void h(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
        O(f2 * 0.0f);
        throw null;
    }

    @Override // defpackage.ls0
    public final void j(int i, float f, float f2, boolean z) {
        this.q = true;
        if (!P(f, f2)) {
            I(7, true);
        } else {
            P(f, f2);
        }
    }

    @Override // dx2.a
    public final void k() {
        M();
    }

    @Override // defpackage.ls0
    public final void l(int i, float f, float f2, boolean z) {
        this.p = f2;
        this.q = false;
        this.o = this.k;
    }

    @Override // defpackage.vb
    public final int o() {
        return !a() ? 1 : 0;
    }

    @Override // defpackage.je2
    public final boolean o0() {
        if (this.k > 0.0f) {
            throw null;
        }
        return false;
    }

    @Override // dx2.a
    public final boolean q(int i) {
        return i == 3 && E();
    }

    @Override // defpackage.je2
    public final boolean q1() {
        return E();
    }

    @Override // defpackage.ls0
    public final void s(float f, float f2, float f3, float f4, float f5) {
        O(f2 - this.p);
        throw null;
    }

    @Override // defpackage.ls0
    public final void t(float f, float f2) {
        K(f2);
    }

    @Override // dx2.a
    public final void v(int i) {
        this.q = false;
        this.o = this.k;
    }

    @Override // defpackage.je2
    public final EventFilter x() {
        return null;
    }

    @Override // defpackage.je2
    public final void N() {
    }

    @Override // defpackage.ls0
    public final void u() {
    }

    @Override // defpackage.je2
    public final void f0(ArrayList arrayList) {
    }

    @Override // defpackage.lt1
    public final void I(int i, boolean z) {
    }

    @Override // defpackage.ls0
    public final void r(float f, float f2) {
    }
}
