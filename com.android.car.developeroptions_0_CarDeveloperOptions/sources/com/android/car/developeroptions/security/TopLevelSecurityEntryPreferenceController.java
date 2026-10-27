package com.android.car.developeroptions.security;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.face.FaceManager;
import android.hardware.fingerprint.FingerprintManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class TopLevelSecurityEntryPreferenceController extends BasePreferenceController {
    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 1;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public TopLevelSecurityEntryPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        FingerprintManager fingerprintManagerOrNull = Utils.getFingerprintManagerOrNull(this.mContext);
        FaceManager faceManagerOrNull = Utils.getFaceManagerOrNull(this.mContext);
        if (faceManagerOrNull != null && faceManagerOrNull.isHardwareDetected()) {
            return this.mContext.getText(R.string.security_dashboard_summary_face);
        }
        if (fingerprintManagerOrNull != null && fingerprintManagerOrNull.isHardwareDetected()) {
            return this.mContext.getText(R.string.security_dashboard_summary);
        }
        return this.mContext.getText(R.string.security_dashboard_summary_no_fingerprint);
    }
}
