package com.android.car.developeroptions.biometrics.fingerprint;

import android.content.Intent;
import com.android.car.developeroptions.SetupWizardUtils;

/* JADX INFO: loaded from: classes.dex */
public class SetupFingerprintEnrollEnrolling extends FingerprintEnrollEnrolling {
    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollEnrolling, com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 246;
    }

    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollEnrolling, com.android.car.developeroptions.biometrics.BiometricsEnrollEnrolling
    protected Intent getFinishIntent() {
        Intent intent = new Intent(this, (Class<?>) SetupFingerprintEnrollFinish.class);
        SetupWizardUtils.copySetupExtras(getIntent(), intent);
        return intent;
    }
}
