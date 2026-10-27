package defpackage;

import android.os.Handler;
import org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: MerchantTrustBottomSheetMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pf1 extends WebContentsDelegateAndroid {
    public final /* synthetic */ qf1 a;

    public pf1(qf1 qf1Var) {
        this.a = qf1Var;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getTopControlsHeight() {
        return this.a.d;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void loadingStateChanged(boolean z) {
        qf1 qf1Var = this.a;
        WebContents webContents = qf1Var.i;
        if (!(webContents != null && webContents.k())) {
            new Handler().postDelayed(new ql0(13, this), 50L);
            return;
        }
        PropertyModel propertyModel = qf1Var.h;
        if (propertyModel == null) {
            return;
        }
        propertyModel.m(wi.c, 0.0f);
        qf1Var.h.l(wi.d, true);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void openNewTab(GURL gurl, String str, ResourceRequestBody resourceRequestBody, int i, boolean z) {
        this.a.a(gurl);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final boolean shouldCreateWebContents(GURL gurl) {
        this.a.a(gurl);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0027  */
    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void visibleSSLStateChanged() {
        /*
            r4 = this;
            qf1 r4 = r4.a
            org.chromium.ui.modelutil.PropertyModel r0 = r4.h
            if (r0 != 0) goto L7
            return
        L7:
            org.chromium.content_public.browser.WebContents r0 = r4.i
            int r0 = defpackage.n3.o0(r0)
            org.chromium.ui.modelutil.PropertyModel r1 = r4.h
            org.chromium.ui.modelutil.PropertyModel$p r2 = defpackage.wi.e
            if (r0 == 0) goto L27
            r3 = 3
            if (r0 == r3) goto L24
            r3 = 4
            if (r0 == r3) goto L24
            r3 = 5
            if (r0 == r3) goto L21
            r3 = 6
            if (r0 == r3) goto L27
            r0 = 0
            goto L29
        L21:
            int r0 = org.chromium.chrome.R$drawable.omnibox_not_secure_warning
            goto L29
        L24:
            int r0 = org.chromium.chrome.R$drawable.omnibox_https_valid
            goto L29
        L27:
            int r0 = org.chromium.chrome.R$drawable.omnibox_info
        L29:
            r1.n(r2, r0)
            org.chromium.ui.modelutil.PropertyModel r0 = r4.h
            org.chromium.ui.modelutil.PropertyModel$r<org.chromium.url.GURL> r1 = defpackage.wi.a
            org.chromium.content_public.browser.WebContents r4 = r4.i
            org.chromium.url.GURL r4 = r4.s()
            r0.p(r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pf1.visibleSSLStateChanged():void");
    }
}
