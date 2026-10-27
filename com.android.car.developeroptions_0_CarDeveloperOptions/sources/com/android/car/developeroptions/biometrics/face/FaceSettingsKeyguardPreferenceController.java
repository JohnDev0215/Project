package com.android.car.developeroptions.biometrics.face;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class FaceSettingsKeyguardPreferenceController extends FaceSettingsPreferenceController {
    private static final int DEFAULT = 1;
    static final String KEY = "security_settings_face_keyguard";
    private static final int OFF = 0;
    private static final int ON = 1;

    @Override // com.android.car.developeroptions.biometrics.face.FaceSettingsPreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceSettingsPreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceSettingsPreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceSettingsPreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceSettingsPreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceSettingsPreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceSettingsPreferenceController, com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public FaceSettingsKeyguardPreferenceController(Context context, String str) {
        super(context, str);
    }

    public FaceSettingsKeyguardPreferenceController(Context context) {
        this(context, KEY);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return FaceSettings.isAvailable(this.mContext) && !adminDisabled() && Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "face_unlock_keyguard_enabled", 1, getUserId()) == 1;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "face_unlock_keyguard_enabled", z ? 1 : 0, getUserId());
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        if (!FaceSettings.isAvailable(this.mContext)) {
            preference.setEnabled(false);
        } else if (adminDisabled()) {
            preference.setEnabled(false);
        } else {
            preference.setEnabled(true);
        }
    }

    private boolean adminDisabled() {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService("device_policy");
        return (devicePolicyManager == null || (devicePolicyManager.getKeyguardDisabledFeatures(null, UserHandle.myUserId()) & 128) == 0) ? false : true;
    }
}
