package com.android.car.developeroptions.biometrics.fingerprint;

import android.content.Context;
import android.content.IntentFilter;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class FingerprintProfileStatusPreferenceController extends FingerprintStatusPreferenceController {
    public static final String KEY_FINGERPRINT_SETTINGS = "fingerprint_settings_profile";

    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public FingerprintProfileStatusPreferenceController(Context context) {
        super(context, KEY_FINGERPRINT_SETTINGS);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected boolean isUserSupported() {
        int i = this.mProfileChallengeUserId;
        return i != -10000 && this.mLockPatternUtils.isSeparateProfileChallengeAllowed(i);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected int getUserId() {
        return this.mProfileChallengeUserId;
    }
}
