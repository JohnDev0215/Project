package org.chromium.chrome.browser.metrics;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidSessionDurationsServiceState {
    public final long a;
    public final long b;

    @CalledByNative
    public AndroidSessionDurationsServiceState(long j, long j2) {
        this.b = j2;
        this.a = j;
    }

    @CalledByNative
    public final long getLastReportedDuration() {
        return this.b;
    }

    @CalledByNative
    public final long getSessionStartTime() {
        return this.a;
    }
}
