package com.android.car.developeroptions.backup;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class DataManagementPreferenceController extends BasePreferenceController {
    private PrivacySettingsConfigData mPSCD;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
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

    public DataManagementPreferenceController(Context context, String str) {
        super(context, str);
        this.mPSCD = PrivacySettingsConfigData.getInstance();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (PrivacySettingsUtils.isAdminUser(this.mContext)) {
            return !(this.mPSCD.getManageIntent() != null && this.mPSCD.isBackupEnabled()) ? 3 : 0;
        }
        return 4;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (isAvailable()) {
            preference.setIntent(this.mPSCD.getManageIntent());
            CharSequence manageLabel = this.mPSCD.getManageLabel();
            if (manageLabel != null) {
                preference.setTitle(manageLabel);
            }
        }
    }
}
