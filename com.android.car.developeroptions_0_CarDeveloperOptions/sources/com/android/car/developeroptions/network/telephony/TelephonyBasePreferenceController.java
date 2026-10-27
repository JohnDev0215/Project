package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.IntentFilter;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public abstract class TelephonyBasePreferenceController extends BasePreferenceController implements TelephonyAvailabilityCallback {
    protected int mSubId;

    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    public abstract /* synthetic */ int getAvailabilityStatus(int i);

    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public TelephonyBasePreferenceController(Context context, String str) {
        super(context, str);
        this.mSubId = -1;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return MobileNetworkUtils.getAvailability(this.mContext, this.mSubId, new TelephonyAvailabilityCallback() { // from class: com.android.car.developeroptions.network.telephony.-$$Lambda$LG20LHplvhFcQNZVRw9HcE7zCpc
            @Override // com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
            public final int getAvailabilityStatus(int i) {
                return this.f$0.getAvailabilityStatus(i);
            }
        });
    }
}
