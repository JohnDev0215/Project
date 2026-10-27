package com.android.car.developeroptions.biometrics.fingerprint;

import android.content.Intent;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SetupWizardUtils;

/* JADX INFO: loaded from: classes.dex */
public class SetupFingerprintEnrollFinish extends FingerprintEnrollFinish {
    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollFinish, com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 248;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollBase
    protected Intent getFingerprintEnrollingIntent() {
        Intent intent = new Intent(this, (Class<?>) SetupFingerprintEnrollEnrolling.class);
        intent.putExtra("hw_auth_token", this.mToken);
        int i = this.mUserId;
        if (i != -10000) {
            intent.putExtra("android.intent.extra.USER_ID", i);
        }
        SetupWizardUtils.copySetupExtras(getIntent(), intent);
        return intent;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollBase
    protected void initViews() {
        super.initViews();
        getNextButton().setText(this, R.string.next_label);
    }
}
