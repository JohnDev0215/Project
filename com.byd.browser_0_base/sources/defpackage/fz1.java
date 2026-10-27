package defpackage;

import android.os.Handler;
import org.chromium.components.omnibox.R$string;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: PaymentHandlerToolbarMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fz1 extends rq3 {
    public final PropertyModel f;
    public Handler g;
    public final WebContents h;
    public final a i;

    /* JADX INFO: compiled from: PaymentHandlerToolbarMediator.java */
    public interface a {
    }

    public fz1(PropertyModel propertyModel, WebContents webContents, a aVar) {
        super(webContents);
        this.h = webContents;
        this.f = propertyModel;
        this.i = aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    @Override // defpackage.rq3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void didChangeVisibleSecurityState() {
        /*
            r5 = this;
            fz1$a r0 = r5.i
            r1 = r0
            ez1 r1 = (defpackage.ez1) r1
            org.chromium.content_public.browser.WebContents r1 = r1.a
            int r1 = defpackage.n3.o0(r1)
            ez1 r0 = (defpackage.ez1) r0
            boolean r2 = r0.c
            r3 = 0
            int r2 = defpackage.z7.C(r1, r2, r3, r3)
            org.chromium.ui.modelutil.PropertyModel$p r4 = defpackage.gz1.e
            org.chromium.ui.modelutil.PropertyModel r5 = r5.f
            r5.n(r4, r2)
            if (r1 == 0) goto L30
            r2 = 3
            if (r1 == r2) goto L2d
            r2 = 4
            if (r1 == r2) goto L2d
            r2 = 5
            if (r1 == r2) goto L2a
            r2 = 6
            if (r1 == r2) goto L30
            goto L32
        L2a:
            int r3 = org.chromium.components.omnibox.R$string.accessibility_security_btn_dangerous
            goto L32
        L2d:
            int r3 = org.chromium.components.omnibox.R$string.accessibility_security_btn_secure
            goto L32
        L30:
            int r3 = org.chromium.components.omnibox.R$string.accessibility_security_btn_warn
        L32:
            android.app.Activity r0 = r0.b
            android.content.res.Resources r0 = r0.getResources()
            java.lang.String r0 = r0.getString(r3)
            org.chromium.ui.modelutil.PropertyModel$r<java.lang.String> r1 = defpackage.gz1.f
            r5.p(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fz1.didChangeVisibleSecurityState():void");
    }

    @Override // defpackage.rq3
    public final void didFailLoad(boolean z, int i, GURL gurl, int i2) {
        if (i2 != 1) {
            return;
        }
        this.f.l(gz1.d, false);
    }

    @Override // defpackage.rq3
    public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        if (navigationHandle.g) {
            PropertyModel.r<GURL> rVar = gz1.a;
            GURL gurlS = this.h.s();
            PropertyModel propertyModel = this.f;
            propertyModel.p(rVar, gurlS);
            propertyModel.l(gz1.d, false);
        }
    }

    @Override // defpackage.rq3
    public final void didStartNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        if (navigationHandle.c) {
            return;
        }
        ez1 ez1Var = (ez1) this.i;
        int iC = z7.C(0, ez1Var.c, false, false);
        PropertyModel.p pVar = gz1.e;
        PropertyModel propertyModel = this.f;
        propertyModel.n(pVar, iC);
        propertyModel.p(gz1.f, ez1Var.b.getResources().getString(R$string.accessibility_security_btn_warn));
    }

    @Override // defpackage.rq3
    public final void f(ct0 ct0Var, GURL gurl, boolean z, int i) {
        if (i != 1) {
            return;
        }
        Handler handler = new Handler();
        this.g = handler;
        handler.postDelayed(new qm0(22, this), 64L);
    }

    @Override // defpackage.rq3
    public final void loadProgressChanged(float f) {
        if (f == 1.0d) {
            return;
        }
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.g = null;
        }
        PropertyModel.n nVar = gz1.d;
        PropertyModel propertyModel = this.f;
        propertyModel.l(nVar, true);
        propertyModel.m(gz1.c, Math.max(f, 0.05f));
    }

    @Override // defpackage.rq3
    public final void titleWasSet(String str) {
        this.f.p(gz1.b, str);
    }
}
