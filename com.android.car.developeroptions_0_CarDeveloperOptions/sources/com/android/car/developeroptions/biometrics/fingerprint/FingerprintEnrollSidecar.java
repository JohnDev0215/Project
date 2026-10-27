package com.android.car.developeroptions.biometrics.fingerprint;

import android.app.Activity;
import android.hardware.fingerprint.FingerprintManager;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.biometrics.BiometricEnrollSidecar;

/* JADX INFO: loaded from: classes.dex */
public class FingerprintEnrollSidecar extends BiometricEnrollSidecar {
    private FingerprintManager.EnrollmentCallback mEnrollmentCallback = new FingerprintManager.EnrollmentCallback() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollSidecar.1
        public void onEnrollmentProgress(int i) {
            FingerprintEnrollSidecar.super.onEnrollmentProgress(i);
        }

        public void onEnrollmentHelp(int i, CharSequence charSequence) {
            FingerprintEnrollSidecar.super.onEnrollmentHelp(i, charSequence);
        }

        public void onEnrollmentError(int i, CharSequence charSequence) {
            FingerprintEnrollSidecar.super.onEnrollmentError(i, charSequence);
        }
    };
    private FingerprintManager mFingerprintManager;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 245;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mFingerprintManager = Utils.getFingerprintManagerOrNull(activity);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar
    protected void startEnrollment() {
        super.startEnrollment();
        int i = this.mUserId;
        if (i != -10000) {
            this.mFingerprintManager.setActiveUser(i);
        }
        this.mFingerprintManager.enroll(this.mToken, this.mEnrollmentCancel, 0, this.mUserId, this.mEnrollmentCallback);
    }
}
