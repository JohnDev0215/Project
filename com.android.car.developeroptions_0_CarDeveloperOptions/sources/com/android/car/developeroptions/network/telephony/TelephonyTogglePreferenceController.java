package com.android.car.developeroptions.network.telephony;

import android.content.Context;
import android.content.IntentFilter;
import com.android.car.developeroptions.core.TogglePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public abstract class TelephonyTogglePreferenceController extends TogglePreferenceController implements TelephonyAvailabilityCallback {
    protected int mSubId;

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    public abstract /* synthetic */ int getAvailabilityStatus(int i);

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public TelephonyTogglePreferenceController(Context context, String str) {
        super(context, str);
        this.mSubId = -1;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return MobileNetworkUtils.getAvailability(this.mContext, this.mSubId, new TelephonyAvailabilityCallback() { // from class: com.android.car.developeroptions.network.telephony.-$$Lambda$9RA7ztn6mFAMPuYCW9kAqeMww2k
            @Override // com.android.car.developeroptions.network.telephony.TelephonyAvailabilityCallback
            public final int getAvailabilityStatus(int i) {
                return this.f$0.getAvailabilityStatus(i);
            }
        });
    }
}
