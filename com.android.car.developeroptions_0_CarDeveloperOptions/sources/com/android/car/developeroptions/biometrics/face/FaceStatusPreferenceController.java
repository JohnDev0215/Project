package com.android.car.developeroptions.biometrics.face;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.face.FaceManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Settings$FaceSettingsActivity;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class FaceStatusPreferenceController extends BiometricStatusPreferenceController {
    private static final String KEY_FACE_SETTINGS = "face_settings";
    protected final FaceManager mFaceManager;

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

    public FaceStatusPreferenceController(Context context) {
        this(context, KEY_FACE_SETTINGS);
    }

    public FaceStatusPreferenceController(Context context, String str) {
        super(context, str);
        this.mFaceManager = Utils.getFaceManagerOrNull(context);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected boolean isDeviceSupported() {
        FaceManager faceManager = this.mFaceManager;
        return faceManager != null && faceManager.isHardwareDetected();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected boolean hasEnrolledBiometrics() {
        return this.mFaceManager.hasEnrolledTemplates(getUserId());
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected String getSummaryTextEnrolled() {
        return this.mContext.getResources().getString(R.string.security_settings_face_preference_summary);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected String getSummaryTextNoneEnrolled() {
        return this.mContext.getResources().getString(R.string.security_settings_face_preference_summary_none);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected String getSettingsClassName() {
        return Settings$FaceSettingsActivity.class.getName();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController
    protected String getEnrollClassName() {
        return FaceEnrollIntroduction.class.getName();
    }
}
