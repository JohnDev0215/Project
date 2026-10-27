package com.android.car.developeroptions.biometrics.fingerprint;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.fingerprint.FingerprintManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class FingerprintStatusPreferenceController extends BiometricStatusPreferenceController {
    private static final String KEY_FINGERPRINT_SETTINGS = "fingerprint_settings";
    protected final FingerprintManager mFingerprintManager;

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public FingerprintStatusPreferenceController(Context context) {
        this(context, KEY_FINGERPRINT_SETTINGS);
    }

    public FingerprintStatusPreferenceController(Context context, String str) {
        super(context, str);
        this.mFingerprintManager = Utils.getFingerprintManagerOrNull(context);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected boolean isDeviceSupported() {
        FingerprintManager fingerprintManager = this.mFingerprintManager;
        return fingerprintManager != null && fingerprintManager.isHardwareDetected();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected boolean hasEnrolledBiometrics() {
        return this.mFingerprintManager.hasEnrolledFingerprints(getUserId());
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected String getSummaryTextEnrolled() {
        int size = this.mFingerprintManager.getEnrolledFingerprints(getUserId()).size();
        return this.mContext.getResources().getQuantityString(R.plurals.security_settings_fingerprint_preference_summary, size, Integer.valueOf(size));
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected String getSummaryTextNoneEnrolled() {
        return this.mContext.getString(R.string.security_settings_fingerprint_preference_summary_none);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected String getSettingsClassName() {
        return FingerprintSettings.class.getName();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected String getEnrollClassName() {
        return FingerprintEnrollIntroduction.class.getName();
    }
}
