package com.android.car.developeroptions.backup;

import android.content.Context;
import android.content.IntentFilter;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class BackupDataPreferenceController extends BasePreferenceController {
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

    public BackupDataPreferenceController(Context context, String str) {
        super(context, str);
        this.mPSCD = PrivacySettingsConfigData.getInstance();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        if (PrivacySettingsUtils.isAdminUser(this.mContext)) {
            return PrivacySettingsUtils.isInvisibleKey(this.mContext, "backup_data") ? 3 : 0;
        }
        return 4;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (this.mPSCD.isBackupGray()) {
            preference.setEnabled(false);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        if (this.mPSCD.isBackupGray()) {
            return null;
        }
        if (this.mPSCD.isBackupEnabled()) {
            return this.mContext.getText(R.string.accessibility_feature_state_on);
        }
        return this.mContext.getText(R.string.accessibility_feature_state_off);
    }
}
