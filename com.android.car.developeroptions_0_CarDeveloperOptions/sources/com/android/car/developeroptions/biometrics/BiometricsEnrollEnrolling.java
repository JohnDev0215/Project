package com.android.car.developeroptions.biometrics;

import android.content.Intent;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.biometrics.BiometricEnrollSidecar;

/* JADX INFO: loaded from: classes.dex */
public abstract class BiometricsEnrollEnrolling extends BiometricEnrollBase implements BiometricEnrollSidecar.Listener {
    protected BiometricEnrollSidecar mSidecar;

    protected abstract Intent getFinishIntent();

    protected abstract BiometricEnrollSidecar getSidecar();

    protected abstract boolean shouldStartAutomatically();

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (shouldStartAutomatically()) {
            startEnrollment();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        BiometricEnrollSidecar biometricEnrollSidecar = this.mSidecar;
        if (biometricEnrollSidecar != null) {
            biometricEnrollSidecar.setListener(null);
        }
        if (isChangingConfigurations()) {
            return;
        }
        BiometricEnrollSidecar biometricEnrollSidecar2 = this.mSidecar;
        if (biometricEnrollSidecar2 != null) {
            biometricEnrollSidecar2.cancelEnrollment();
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.remove(this.mSidecar);
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        BiometricEnrollSidecar biometricEnrollSidecar = this.mSidecar;
        if (biometricEnrollSidecar != null) {
            biometricEnrollSidecar.setListener(null);
            this.mSidecar.cancelEnrollment();
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.remove(this.mSidecar);
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            this.mSidecar = null;
        }
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSkipButtonClick(View view) {
        setResult(2);
        finish();
    }

    public void startEnrollment() {
        BiometricEnrollSidecar biometricEnrollSidecar = (BiometricEnrollSidecar) getSupportFragmentManager().findFragmentByTag("sidecar");
        this.mSidecar = biometricEnrollSidecar;
        if (biometricEnrollSidecar == null) {
            this.mSidecar = getSidecar();
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.add(this.mSidecar, "sidecar");
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        }
        this.mSidecar.setListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void launchFinish(byte[] bArr) {
        Intent finishIntent = getFinishIntent();
        finishIntent.addFlags(637534208);
        finishIntent.putExtra("hw_auth_token", bArr);
        int i = this.mUserId;
        if (i != -10000) {
            finishIntent.putExtra("android.intent.extra.USER_ID", i);
        }
        startActivity(finishIntent);
        overridePendingTransition(R.anim.sud_slide_next_in, R.anim.sud_slide_next_out);
        finish();
    }
}
