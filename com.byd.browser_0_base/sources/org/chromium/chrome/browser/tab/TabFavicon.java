package org.chromium.chrome.browser.tab;

import J.N;
import android.graphics.Bitmap;
import defpackage.m53;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class TabFavicon extends m53 {
    public final TabImpl f;
    public final long g;
    public final int h;
    public Bitmap i;
    public int j;
    public int k;
    public GURL l;

    public TabFavicon(Tab tab) {
        super(tab);
        TabImpl tabImpl = (TabImpl) tab;
        this.f = tabImpl;
        this.h = tabImpl.d.getResources().getDimensionPixelSize(org.chromium.chrome.R$dimen.default_favicon_size);
        this.g = N.MMZhE4x7(this);
    }

    public static Bitmap d(Tab tab) {
        GURL gurl;
        TabFavicon tabFavicon = (tab == null || !tab.isInitialized()) ? null : (TabFavicon) tab.B().b(TabFavicon.class);
        if (tabFavicon == null) {
            return null;
        }
        TabImpl tabImpl = tabFavicon.f;
        if (tabImpl.isNativePage() || tabImpl.g == null) {
            return null;
        }
        return (tabFavicon.i == null || (gurl = tabFavicon.l) == null || !gurl.equals(tabImpl.getUrl())) ? (Bitmap) N.MmmF426r(tabFavicon.g, tabFavicon) : tabFavicon.i;
    }

    @Override // defpackage.m53
    public final void a(WebContents webContents) {
        N.Mmp1Icg1(this.g, this);
    }

    @Override // defpackage.m53
    public final void b() {
        N.M221q0MR(this.g, this);
    }

    @Override // defpackage.m53
    public final void c(WebContents webContents) {
        N.MP93Z_9Y(this.g, this, webContents);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x004f  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onFaviconAvailable(android.graphics.Bitmap r11, org.chromium.url.GURL r12) {
        /*
            r10 = this;
            if (r11 != 0) goto L3
            return
        L3:
            org.chromium.chrome.browser.tab.TabImpl r0 = r10.f
            org.chromium.url.GURL r1 = r0.getUrl()
            org.chromium.url.GURL r2 = r10.l
            boolean r2 = r1.equals(r2)
            r3 = 1
            r2 = r2 ^ r3
            java.lang.String r4 = "AndroidScrollOptimizations"
            int r5 = r10.h
            if (r2 != 0) goto L4f
            int r2 = r11.getWidth()
            int r6 = r11.getHeight()
            r7 = 0
            if (r2 != r5) goto L26
            if (r6 != r5) goto L26
            r8 = r3
            goto L27
        L26:
            r8 = r7
        L27:
            if (r8 == 0) goto L2a
            goto L4c
        L2a:
            int r8 = r10.j
            if (r8 != r2) goto L33
            int r9 = r10.k
            if (r9 != r6) goto L33
            goto L4c
        L33:
            int r9 = r10.k
            if (r8 == r9) goto L3a
            if (r2 != r6) goto L3a
            goto L4c
        L3a:
            if (r8 != r9) goto L3f
            if (r2 == r6) goto L3f
            goto L4d
        L3f:
            if (r8 < r5) goto L44
            if (r9 < r5) goto L44
            goto L4d
        L44:
            if (r2 <= r8) goto L48
            if (r6 >= r9) goto L4c
        L48:
            if (r2 < r8) goto L4d
            if (r6 <= r9) goto L4d
        L4c:
            r7 = r3
        L4d:
            if (r7 == 0) goto L7f
        L4f:
            android.graphics.Bitmap r2 = android.graphics.Bitmap.createScaledBitmap(r11, r5, r5, r3)
            r10.i = r2
            int r2 = r11.getWidth()
            r10.j = r2
            int r2 = r11.getHeight()
            r10.k = r2
            r10.l = r1
            sk r10 = defpackage.bq.a
            boolean r10 = J.N.M09VlOh_(r4)
            if (r10 == 0) goto L7f
            org.chromium.base.b$a r10 = r0.N()
        L6f:
            boolean r1 = r10.hasNext()
            if (r1 == 0) goto L7f
            java.lang.Object r1 = r10.next()
            n23 r1 = (defpackage.n23) r1
            r1.X(r0, r11, r12)
            goto L6f
        L7f:
            sk r10 = defpackage.bq.a
            boolean r10 = J.N.M09VlOh_(r4)
            if (r10 == 0) goto L88
            return
        L88:
            org.chromium.base.b$a r10 = r0.N()
        L8c:
            boolean r1 = r10.hasNext()
            if (r1 == 0) goto L9c
            java.lang.Object r1 = r10.next()
            n23 r1 = (defpackage.n23) r1
            r1.X(r0, r11, r12)
            goto L8c
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.TabFavicon.onFaviconAvailable(android.graphics.Bitmap, org.chromium.url.GURL):void");
    }
}
