package com.android.car.developeroptions.biometrics.face;

import android.content.Context;
import android.content.IntentFilter;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class FaceProfileStatusPreferenceController extends FaceStatusPreferenceController {
    public static final String KEY_FACE_SETTINGS = "face_settings_profile";

    @Override // com.android.car.developeroptions.biometrics.face.FaceStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.biometrics.face.FaceStatusPreferenceController, com.android.car.developeroptions.biometrics.BiometricStatusPreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public FaceProfileStatusPreferenceController(Context context) {
        super(context, KEY_FACE_SETTINGS);
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
