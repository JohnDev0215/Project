package org.chromium.chrome.browser.feed;

import J.N;
import defpackage.al0;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class FeedReliabilityLoggingBridge {
    public final long a = N.MaP9vcrt(this);
    public al0 b;
    public boolean c;
    public int d;

    @CalledByNative
    public void logAboveTheFoldRender(long j, int i) {
        if (this.c) {
            return;
        }
        this.b.getClass();
        this.c = true;
    }

    @CalledByNative
    public void logActionsUploadRequestStart(int i, long j) {
        this.b.getClass();
    }

    @CalledByNative
    public void logCacheReadEnd(long j, int i) {
        this.b.getClass();
    }

    @CalledByNative
    public void logCacheReadStart(long j) {
        this.b.getClass();
    }

    @CalledByNative
    public void logFeedRequestStart(int i, long j) {
        this.b.getClass();
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0089 A[PHI: r2
  0x0089: PHI (r2v1 int) = (r2v0 int), (r2v2 int) binds: [B:8:0x000b, B:18:0x001e] A[DONT_GENERATE, DONT_INLINE]] */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void logLaunchFinishedAfterStreamUpdate(int r5) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.feed.FeedReliabilityLoggingBridge.logLaunchFinishedAfterStreamUpdate(int):void");
    }

    @CalledByNative
    public void logLoadingIndicatorShown(long j) {
        this.b.getClass();
    }

    @CalledByNative
    public void logOtherLaunchStart(long j) {
        this.b.getClass();
    }

    @CalledByNative
    public void logRequestFinished(int i, long j, int i2) {
        this.b.getClass();
    }

    @CalledByNative
    public void logRequestSent(int i, long j) {
        this.b.getClass();
    }

    @CalledByNative
    public void logResponseReceived(int i, long j, long j2, long j3) {
        this.b.getClass();
    }

    @CalledByNative
    public void logSingleWebFeedRequestStart(int i, long j) {
        this.b.getClass();
    }

    @CalledByNative
    public void logWebFeedRequestStart(int i, long j) {
        this.b.getClass();
    }
}
