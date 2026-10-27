package org.chromium.components.site_engagement;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class SiteEngagementService {
    public long a;

    public SiteEngagementService(long j) {
        this.a = j;
    }

    @CalledByNative
    public static SiteEngagementService create(long j) {
        return new SiteEngagementService(j);
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        this.a = 0L;
    }
}
