package org.chromium.chrome.browser.toolbar.top;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import defpackage.af1;
import defpackage.ak;
import defpackage.bn0;
import defpackage.ck;
import defpackage.cs2;
import defpackage.hw0;
import defpackage.jj2;
import defpackage.ll;
import defpackage.n62;
import defpackage.nw2;
import defpackage.qo;
import defpackage.x10;
import defpackage.y13;
import java.util.function.BooleanSupplier;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.toolbar.top.m;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: StartSurfaceToolbarMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements ck.a {
    public float A;
    public boolean B;
    public org.chromium.chrome.browser.logo.a C;
    public ObjectAnimator D;
    public final Callback<Boolean> E;
    public m.a F;
    public final PropertyModel e;
    public final Callback<hw0> f;
    public final boolean g;
    public final boolean h;
    public final nw2<ak> i;
    public final boolean j;
    public final boolean k;
    public final BooleanSupplier l;
    public final af1 m;
    public final e n;
    public final f o;
    public final Callback<LoadUrlParams> p;
    public final boolean q;
    public final boolean r;
    public final ck s;
    public final boolean t;
    public final Context u;
    public y13 v;
    public org.chromium.chrome.browser.toolbar.a w;
    public int y;
    public ll z = new ll();
    public int x = 0;

    public g(Context context, PropertyModel propertyModel, n62 n62Var, boolean z, af1 af1Var, ck ckVar, jj2 jj2Var, boolean z2, boolean z3, boolean z4, bn0 bn0Var, ll.b bVar, boolean z5, boolean z6, boolean z7, x10 x10Var, m.a aVar) {
        this.e = propertyModel;
        this.f = n62Var;
        this.g = z;
        this.m = af1Var;
        this.i = jj2Var;
        this.k = z3;
        this.j = z4;
        this.l = bn0Var;
        this.p = bVar;
        this.r = z6;
        this.s = ckVar;
        ckVar.d(this);
        this.t = z7;
        this.q = z5;
        this.E = x10Var;
        this.F = aVar;
        this.u = context;
        this.h = z2;
        this.n = new e(this);
        this.o = new f(this);
    }

    @Override // ck.a
    public final void a(boolean z) {
        if (z || this.e.k(cs2.g)) {
            d(this.i.get());
        }
    }

    public final boolean b() {
        if (!this.q) {
            int i = this.x;
            if (i != 2 && i != 4) {
                return false;
            }
        } else if (this.y != 2) {
            return false;
        }
        return true;
    }

    public final boolean c() {
        if (this.q) {
            if (this.y != 16) {
                return false;
            }
        } else if (this.x != 1) {
            return false;
        }
        return true;
    }

    public final void d(ak akVar) {
        boolean z = akVar.a() && !this.v.r();
        PropertyModel propertyModel = this.e;
        if (!z) {
            propertyModel.l(cs2.g, false);
            return;
        }
        ak.a aVarB = akVar.b();
        propertyModel.p(cs2.d, aVarB.b);
        propertyModel.p(cs2.e, aVarB.a.getConstantState().newDrawable());
        propertyModel.p(cs2.f, aVarB.d);
        propertyModel.l(cs2.g, true);
        this.f.onResult(aVarB.f);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e() {
        /*
            r5 = this;
            org.chromium.ui.modelutil.PropertyModel r0 = r5.e
            r1 = 2
            r2 = 0
            boolean r3 = r5.q
            if (r3 == 0) goto Lc
            int r4 = r5.y
            if (r4 != r1) goto L15
        Lc:
            if (r3 != 0) goto L1e
            int r3 = r5.x
            if (r3 == r1) goto L1e
            r1 = 4
            if (r3 == r1) goto L1e
        L15:
            org.chromium.ui.modelutil.PropertyModel$n r1 = defpackage.cs2.u
            r0.l(r1, r2)
            r5.f()
            return
        L1e:
            org.chromium.ui.modelutil.PropertyModel$n r1 = defpackage.cs2.u
            boolean r3 = r5.g
            r4 = 1
            if (r3 == 0) goto L39
            y13 r3 = r5.v
            if (r3 != 0) goto L2a
            goto L36
        L2a:
            org.chromium.chrome.browser.tabmodel.TabModel r3 = r3.g(r4)
            int r3 = r3.getCount()
            if (r3 == 0) goto L36
            r3 = r4
            goto L37
        L36:
            r3 = r2
        L37:
            if (r3 == 0) goto L3a
        L39:
            r2 = r4
        L3a:
            r0.l(r1, r2)
            r5.f()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.toolbar.top.g.e():void");
    }

    public final void f() {
        PropertyModel.n nVar = cs2.m;
        PropertyModel.n nVar2 = cs2.l;
        PropertyModel propertyModel = this.e;
        propertyModel.l(nVar, propertyModel.k(nVar2) && !propertyModel.k(cs2.u));
    }

    public final void g() {
        this.e.l(cs2.l, b() || (qo.f().c() && !this.j));
        f();
    }

    public final void h(float f) {
        boolean zC = c();
        PropertyModel propertyModel = this.e;
        if (!zC || propertyModel.k(cs2.i)) {
            propertyModel.m(cs2.v, 0.0f);
        } else {
            this.A = f;
            propertyModel.m(cs2.v, f);
        }
    }

    public void setOnNewTabClickHandler(View.OnClickListener onClickListener) {
        this.e.p(cs2.b, onClickListener);
    }
}
