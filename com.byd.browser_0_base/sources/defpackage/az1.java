package defpackage;

import J.N;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import defpackage.l23;
import defpackage.yy1;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.payments.ServiceWorkerPaymentAppBridge;
import org.chromium.chrome.browser.payments.ui.j;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: PaymentHandlerMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class az1 extends rq3 implements vi, View.OnLayoutChangeListener {
    public final PropertyModel f;
    public final Runnable g;
    public final WebContents h;
    public final WebContents i;
    public final yy1.a j;
    public final Handler k;
    public final View l;
    public final ji m;
    public final int n;
    public int o;
    public final l23 p;
    public final zy1 q;
    public l23.b r;

    public az1(PropertyModel propertyModel, ql0 ql0Var, WebContents webContents, WebContents webContents2, j jVar, View view, int i, ji jiVar, l23 l23Var, Activity activity) {
        super(webContents2);
        this.k = new Handler();
        this.o = 0;
        this.l = view;
        this.m = jiVar;
        this.h = webContents;
        this.i = webContents2;
        this.n = i;
        this.f = propertyModel;
        propertyModel.p(dz1.b, new zx1(2, this));
        this.g = ql0Var;
        this.j = jVar;
        propertyModel.n(dz1.a, ((int) (view.getHeight() * 0.9f)) - i);
        this.p = l23Var;
        zy1 zy1Var = new zy1(this);
        this.q = zy1Var;
        ApplicationStatus.f(zy1Var, activity);
    }

    @Override // defpackage.vi
    public final void d(int i, int i2) {
        if (i != 0) {
            return;
        }
        this.o = 1;
        this.k.post(this.g);
    }

    @Override // defpackage.rq3
    public final void destroy() {
        ApplicationStatus.i(this.q);
        int i = this.o;
        WebContents webContents = this.h;
        if (i == 1) {
            ServiceWorkerPaymentAppBridge.a(webContents, 17);
        } else if (i == 2) {
            ServiceWorkerPaymentAppBridge.a(webContents, 13);
        } else if (i == 3) {
            ServiceWorkerPaymentAppBridge.a(webContents, 15);
        } else if (i == 4) {
            ServiceWorkerPaymentAppBridge.a(webContents, 14);
        }
        this.k.removeCallbacksAndMessages(null);
        k(false);
        ef2 ef2VarF = this.m.f();
        if (ef2VarF != null) {
            if2 if2Var = ef2VarF.b;
            if (if2Var.f != null) {
                if2Var.a(300, true);
            }
        }
        super.destroy();
    }

    @Override // defpackage.rq3
    public final void didChangeVisibleSecurityState() {
        if (N.Me8yLh8j(this.i)) {
            return;
        }
        this.k.post(new ql0(28, this));
    }

    @Override // defpackage.rq3
    public final void didFailLoad(boolean z, int i, GURL gurl, int i2) {
        if (z) {
            this.k.post(new wy1(this, 1));
        }
    }

    @Override // defpackage.rq3
    public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        if (navigationHandle.c || !navigationHandle.g || N.Me8yLh8j(this.i)) {
            return;
        }
        this.k.post(new ql0(28, this));
    }

    @Override // defpackage.vi
    public final void e() {
        j.d dVar = ((j) this.j).u;
        dVar.a = true;
        dVar.a();
        ji jiVar = this.m;
        ef2 ef2VarF = jiVar.f();
        if (ef2VarF != null) {
            if (!(ef2VarF.b.f != null)) {
                ef2VarF.a(jiVar.i());
            }
        }
        k(true);
    }

    public final void k(boolean z) {
        l23.b bVar;
        l23 l23Var = this.p;
        if (z && this.r == null) {
            this.r = l23Var.d(2);
        } else {
            if (z || (bVar = this.r) == null) {
                return;
            }
            l23Var.f(bVar);
            this.r = null;
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.f.n(dz1.a, ((int) (this.l.getHeight() * 0.9f)) - this.n);
    }

    @Override // defpackage.vi
    public final void a(float f) {
    }

    @Override // defpackage.vi
    public final void b(ii iiVar) {
    }

    @Override // defpackage.vi
    public final void c(int i) {
    }
}
