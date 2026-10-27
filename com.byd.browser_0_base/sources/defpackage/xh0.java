package defpackage;

import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.c;
import org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: EphemeralTabMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class xh0 extends WebContentsDelegateAndroid {
    public final /* synthetic */ c a;

    public xh0(c cVar) {
        this.a = cVar;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final int getTopControlsHeight() {
        return this.a.d;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public final void loadingStateChanged(boolean z) {
        c cVar = this.a;
        WebContents webContents = cVar.e;
        if (!(webContents != null && webContents.k())) {
            new Handler().postDelayed(new wv(26, this), 64L);
            return;
        }
        zh0 zh0Var = cVar.f;
        if (zh0Var == null) {
            return;
        }
        ViewGroup viewGroup = zh0Var.l;
        int i = R$id.progress_bar;
        ((ProgressBar) viewGroup.findViewById(i)).setProgress(Math.round(0.0f));
        ((ProgressBar) cVar.f.l.findViewById(i)).setVisibility(0);
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

    /* JADX WARN: Removed duplicated region for block: B:18:0x0025  */
    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void visibleSSLStateChanged() {
        /*
            r3 = this;
            org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.c r3 = r3.a
            zh0 r0 = r3.f
            if (r0 != 0) goto L7
            return
        L7:
            org.chromium.content_public.browser.WebContents r0 = r3.e
            int r0 = defpackage.n3.o0(r0)
            zh0 r1 = r3.f
            if (r0 == 0) goto L25
            r2 = 3
            if (r0 == r2) goto L22
            r2 = 4
            if (r0 == r2) goto L22
            r2 = 5
            if (r0 == r2) goto L1f
            r2 = 6
            if (r0 == r2) goto L25
            r0 = 0
            goto L27
        L1f:
            int r0 = org.chromium.chrome.R$drawable.omnibox_not_secure_warning
            goto L27
        L22:
            int r0 = org.chromium.chrome.R$drawable.omnibox_https_valid
            goto L27
        L25:
            int r0 = org.chromium.chrome.R$drawable.omnibox_info
        L27:
            android.view.ViewGroup r1 = r1.l
            int r2 = org.chromium.chrome.R$id.security_icon
            android.view.View r1 = r1.findViewById(r2)
            android.widget.ImageView r1 = (android.widget.ImageView) r1
            r1.setImageResource(r0)
            zh0 r0 = r3.f
            org.chromium.content_public.browser.WebContents r3 = r3.e
            org.chromium.url.GURL r3 = r3.s()
            android.view.ViewGroup r0 = r0.l
            int r1 = org.chromium.chrome.R$id.origin
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 1
            java.lang.String r3 = defpackage.oj2.j(r1, r3)
            r0.setText(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.xh0.visibleSSLStateChanged():void");
    }
}
