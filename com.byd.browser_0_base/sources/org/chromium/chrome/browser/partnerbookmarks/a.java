package org.chromium.chrome.browser.partnerbookmarks;

import org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader;

/* JADX INFO: compiled from: PartnerBookmarksReader.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements PartnerBookmarksReader.FetchFaviconCallback {
    public final /* synthetic */ String a;
    public final /* synthetic */ PartnerBookmarksReader b;

    public a(PartnerBookmarksReader partnerBookmarksReader, String str) {
        this.b = partnerBookmarksReader;
        this.a = str;
    }

    @Override // org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader.FetchFaviconCallback
    public final void onFaviconFetch() {
        synchronized (this.b.d) {
            this.b.e++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004e A[Catch: all -> 0x0053, TryCatch #0 {, blocks: (B:6:0x0010, B:7:0x001a, B:9:0x0020, B:10:0x0030, B:12:0x0042, B:14:0x0046, B:19:0x004e, B:20:0x0051), top: B:25:0x0010 }] */
    @Override // org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader.FetchFaviconCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onFaviconFetched(int r6) {
        /*
            r5 = this;
            java.lang.String r0 = "PartnerBookmark.FaviconThrottleFetchResult"
            r1 = 8
            defpackage.oj2.U(r6, r1, r0)
            org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader r0 = r5.b
            java.lang.Object r0 = r0.d
            monitor-enter(r0)
            r1 = 6
            r2 = 1
            if (r6 != r1) goto L30
            org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader r1 = r5.b     // Catch: java.lang.Throwable -> L53
            r1.g = r2     // Catch: java.lang.Throwable -> L53
            java.util.HashSet r1 = org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader.j     // Catch: java.lang.Throwable -> L53
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L53
        L1a:
            boolean r3 = r1.hasNext()     // Catch: java.lang.Throwable -> L53
            if (r3 == 0) goto L30
            java.lang.Object r3 = r1.next()     // Catch: java.lang.Throwable -> L53
            org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader$a r3 = (org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader.a) r3     // Catch: java.lang.Throwable -> L53
            java.lang.String r4 = r5.a     // Catch: java.lang.Throwable -> L53
            java.lang.String r4 = J.N.McXhQJZC(r4)     // Catch: java.lang.Throwable -> L53
            r3.f(r4)     // Catch: java.lang.Throwable -> L53
            goto L1a
        L30:
            org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader r1 = r5.b     // Catch: java.lang.Throwable -> L53
            sw1 r1 = r1.a     // Catch: java.lang.Throwable -> L53
            java.lang.String r3 = r5.a     // Catch: java.lang.Throwable -> L53
            r1.b(r6, r3)     // Catch: java.lang.Throwable -> L53
            org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader r5 = r5.b     // Catch: java.lang.Throwable -> L53
            int r6 = r5.e     // Catch: java.lang.Throwable -> L53
            int r6 = r6 - r2
            r5.e = r6     // Catch: java.lang.Throwable -> L53
            if (r6 != 0) goto L4b
            boolean r6 = r5.h     // Catch: java.lang.Throwable -> L53
            if (r6 == 0) goto L4b
            boolean r6 = r5.i     // Catch: java.lang.Throwable -> L53
            if (r6 == 0) goto L4b
            goto L4c
        L4b:
            r2 = 0
        L4c:
            if (r2 == 0) goto L51
            r5.a()     // Catch: java.lang.Throwable -> L53
        L51:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L53
            return
        L53:
            r5 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L53
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.partnerbookmarks.a.onFaviconFetched(int):void");
    }
}
