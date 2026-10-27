package org.chromium.content.browser;

import J.N;
import android.view.MotionEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class AttributionOsLevelManager {
    public long a;

    @CalledByNative
    public AttributionOsLevelManager(long j) {
        this.a = j;
    }

    @CalledByNative
    public final void deleteRegistrations(int i, long j, long j2, GURL[] gurlArr, String[] strArr, int i2, int i3) {
        long j3 = this.a;
        if (j3 != 0) {
            N.MyRg_lPt(j3, i);
        }
    }

    @CalledByNative
    public final void getMeasurementApiStatus() {
        N.MWM1LN92(0);
    }

    @CalledByNative
    public final void nativeDestroyed() {
        this.a = 0L;
    }

    @CalledByNative
    public final void registerAttributionTrigger(GURL gurl, GURL gurl2, boolean z) {
    }

    @CalledByNative
    public final void registerAttributionSource(GURL gurl, GURL gurl2, boolean z, MotionEvent motionEvent) {
    }
}
