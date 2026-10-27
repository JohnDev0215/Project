package io.branch.referral;

import android.content.Context;

/* JADX INFO: loaded from: classes7.dex */
class TrackingController {
    private boolean trackingDisabled = true;

    TrackingController(Context context) {
        updateTrackingState(context);
    }

    boolean isTrackingDisabled() {
        return this.trackingDisabled;
    }

    void updateTrackingState(Context context) {
        this.trackingDisabled = PrefHelper.getInstance(context).getBool("bnc_tracking_state");
    }
}
