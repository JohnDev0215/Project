package defpackage;

import android.util.LruCache;
import org.chromium.base.SysUtils;

/* JADX INFO: compiled from: AppIndexingUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class m6 {
    public LruCache<String, a> a;
    public final l6 b;

    /* JADX INFO: compiled from: AppIndexingUtil.java */
    public static class a {
        public long a;
        public boolean b;
    }

    public m6(a23 a23Var) {
        if (a23Var == null || !(!SysUtils.isLowEndDevice())) {
            return;
        }
        this.b = new l6(this, a23Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(org.chromium.chrome.browser.tab.Tab r10) {
        /*
            r9 = this;
            org.chromium.url.GURL r0 = r10.getUrl()
            boolean r0 = defpackage.bk3.i(r0)
            boolean r1 = org.chromium.base.SysUtils.isLowEndDevice()
            r2 = 1
            r1 = r1 ^ r2
            r3 = 0
            if (r1 == 0) goto L1b
            boolean r1 = r10.isIncognito()
            if (r1 != 0) goto L1b
            if (r0 == 0) goto L1b
            r0 = r2
            goto L1c
        L1b:
            r0 = r3
        L1c:
            if (r0 != 0) goto L1f
            return
        L1f:
            org.chromium.url.GURL r0 = r10.getUrl()
            java.lang.String r0 = r0.j()
            r1 = 100
            if (r0 != 0) goto L2c
            goto L52
        L2c:
            android.util.LruCache<java.lang.String, m6$a> r4 = r9.a
            if (r4 != 0) goto L37
            android.util.LruCache r4 = new android.util.LruCache
            r4.<init>(r1)
            r9.a = r4
        L37:
            android.util.LruCache<java.lang.String, m6$a> r4 = r9.a
            java.lang.Object r4 = r4.get(r0)
            m6$a r4 = (m6.a) r4
            if (r4 == 0) goto L52
            long r5 = android.os.SystemClock.elapsedRealtime()
            long r7 = r4.a
            long r5 = r5 - r7
            r7 = 3600000(0x36ee80, double:1.7786363E-317)
            int r4 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r4 <= 0) goto L50
            goto L52
        L50:
            r4 = r2
            goto L53
        L52:
            r4 = r3
        L53:
            r5 = 3
            java.lang.String r6 = "CopylessPaste.CacheHit"
            if (r4 == 0) goto L82
            if (r0 != 0) goto L5b
            goto L77
        L5b:
            android.util.LruCache<java.lang.String, m6$a> r10 = r9.a
            if (r10 != 0) goto L66
            android.util.LruCache r10 = new android.util.LruCache
            r10.<init>(r1)
            r9.a = r10
        L66:
            android.util.LruCache<java.lang.String, m6$a> r9 = r9.a
            java.lang.Object r9 = r9.get(r0)
            m6$a r9 = (m6.a) r9
            if (r9 == 0) goto L77
            boolean r9 = r9.b
            if (r9 != 0) goto L75
            goto L77
        L75:
            r9 = r2
            goto L78
        L77:
            r9 = r3
        L78:
            if (r9 == 0) goto L7e
            defpackage.oj2.U(r3, r5, r6)
            return
        L7e:
            defpackage.oj2.U(r2, r5, r6)
            goto Laf
        L82:
            r1 = 2
            defpackage.oj2.U(r1, r5, r6)
            org.chromium.content_public.browser.WebContents r10 = r10.c()
            if (r10 != 0) goto L8d
            goto L9a
        L8d:
            org.chromium.content_public.browser.RenderFrameHost r10 = r10.y()
            if (r10 != 0) goto L94
            goto L9a
        L94:
            boolean r1 = r10.a()
            if (r1 != 0) goto L9c
        L9a:
            r10 = 0
            goto La4
        L9c:
            nb0$a r1 = defpackage.nb0.a
            y11$c r10 = r10.e(r1)
            mb0 r10 = (defpackage.mb0) r10
        La4:
            if (r10 != 0) goto La7
            return
        La7:
            k6 r1 = new k6
            r1.<init>(r9, r10, r0)
            r10.b0(r1)
        Laf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.m6.a(org.chromium.chrome.browser.tab.Tab):void");
    }
}
