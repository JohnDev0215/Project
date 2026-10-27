package defpackage;

import java.util.Objects;
import org.chromium.content_public.browser.RenderFrameHost;

/* JADX INFO: compiled from: DigitalGoodsFactoryImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class x90 implements w90 {
    public final RenderFrameHost e;
    public final ke0 f;
    public final v90 g;

    public x90(RenderFrameHost renderFrameHost) {
        this.e = renderFrameHost;
        Objects.requireNonNull(renderFrameHost);
        this.f = new ke0(6, renderFrameHost);
        this.g = new v90(((s50) pp.d()).j());
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0038  */
    @Override // defpackage.w90
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void W0(java.lang.String r4, y90.e r5) {
        /*
            r3 = this;
            ke0 r0 = r3.f
            java.lang.Object r1 = r0.f
            org.chromium.content_public.browser.RenderFrameHost r1 = (org.chromium.content_public.browser.RenderFrameHost) r1
            org.chromium.url.GURL r1 = r1.g()
            org.chromium.chrome.browser.profiles.Profile r2 = org.chromium.chrome.browser.profiles.Profile.d()
            J.N.MyLo2O3q(r2, r1)
            java.lang.String r1 = "AppStoreBilling"
            boolean r1 = J.N.M1X7xdZV(r1)
            if (r1 != 0) goto L1a
            goto L38
        L1a:
            org.chromium.content_public.browser.RenderFrameHost r1 = r3.e
            org.chromium.content_public.browser.WebContents r1 = defpackage.n3.R(r1)
            android.app.Activity r1 = defpackage.z7.n(r1)
            boolean r2 = r1 instanceof org.chromium.chrome.browser.customtabs.CustomTabActivity
            if (r2 != 0) goto L29
            goto L38
        L29:
            org.chromium.chrome.browser.customtabs.CustomTabActivity r1 = (org.chromium.chrome.browser.customtabs.CustomTabActivity) r1
            xf3 r1 = r1.l1
            r2 = 0
            if (r1 != 0) goto L32
            r1 = r2
            goto L36
        L32:
            il2 r1 = r1.a
            boolean r1 = r1.l
        L36:
            if (r1 != 0) goto L3a
        L38:
            r2 = 3
            goto L43
        L3a:
            java.lang.String r1 = "https://play.google.com/billing"
            boolean r4 = r1.equals(r4)
            if (r4 != 0) goto L43
            r2 = 2
        L43:
            if (r2 != 0) goto L54
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            z90 r1 = new z90
            v90 r3 = r3.g
            r1.<init>(r3, r0)
            r5.a(r4, r1)
            goto L5c
        L54:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r4 = 0
            r5.a(r3, r4)
        L5c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x90.W0(java.lang.String, y90$e):void");
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
