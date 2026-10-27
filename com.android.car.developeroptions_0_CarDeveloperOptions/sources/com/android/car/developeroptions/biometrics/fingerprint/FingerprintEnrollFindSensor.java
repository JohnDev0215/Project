package com.android.car.developeroptions.biometrics.fingerprint;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.biometrics.BiometricEnrollBase;
import com.android.car.developeroptions.biometrics.BiometricEnrollSidecar;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;

/* JADX INFO: loaded from: classes.dex */
public class FingerprintEnrollFindSensor extends BiometricEnrollBase {
    private FingerprintFindSensorAnimation mAnimation;
    private boolean mNextClicked;
    private FingerprintEnrollSidecar mSidecar;

    protected int getContentView() {
        return R.layout.fingerprint_enroll_find_sensor;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 241;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollBase, com.android.car.developeroptions.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getContentView());
        FooterBarMixin footerBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        this.mFooterBarMixin = footerBarMixin;
        FooterButton.Builder builder = new FooterButton.Builder(this);
        builder.setText(R.string.skip_label);
        builder.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.-$$Lambda$DAy2CUjQwjoO8K3BXGIjVkqW8B8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onSkipButtonClick(view);
            }
        });
        builder.setButtonType(7);
        builder.setTheme(R.style.SudGlifButton_Secondary);
        footerBarMixin.setSecondaryButton(builder.build());
        setHeaderText(R.string.security_settings_fingerprint_enroll_find_sensor_title);
        startLookingForFingerprint();
        KeyEvent.Callback callbackFindViewById = findViewById(R.id.fingerprint_sensor_location_animation);
        if (callbackFindViewById instanceof FingerprintFindSensorAnimation) {
            this.mAnimation = (FingerprintFindSensorAnimation) callbackFindViewById;
        } else {
            this.mAnimation = null;
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        FingerprintFindSensorAnimation fingerprintFindSensorAnimation = this.mAnimation;
        if (fingerprintFindSensorAnimation != null) {
            fingerprintFindSensorAnimation.startAnimation();
        }
    }

    private void startLookingForFingerprint() {
        FingerprintEnrollSidecar fingerprintEnrollSidecar = (FingerprintEnrollSidecar) getSupportFragmentManager().findFragmentByTag("sidecar");
        this.mSidecar = fingerprintEnrollSidecar;
        if (fingerprintEnrollSidecar == null) {
            this.mSidecar = new FingerprintEnrollSidecar();
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.add(this.mSidecar, "sidecar");
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        }
        this.mSidecar.setListener(new BiometricEnrollSidecar.Listener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollFindSensor.1
            @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.Listener
            public void onEnrollmentHelp(int i, CharSequence charSequence) {
            }

            @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.Listener
            public void onEnrollmentProgressChange(int i, int i2) {
                FingerprintEnrollFindSensor.this.mNextClicked = true;
                FingerprintEnrollFindSensor.this.proceedToEnrolling(true);
            }

            @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.Listener
            public void onEnrollmentError(int i, CharSequence charSequence) {
                if (FingerprintEnrollFindSensor.this.mNextClicked && i == 5) {
                    FingerprintEnrollFindSensor.this.mNextClicked = false;
                    FingerprintEnrollFindSensor.this.proceedToEnrolling(false);
                }
            }
        });
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        FingerprintFindSensorAnimation fingerprintFindSensorAnimation = this.mAnimation;
        if (fingerprintFindSensorAnimation != null) {
            fingerprintFindSensorAnimation.pauseAnimation();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        FingerprintFindSensorAnimation fingerprintFindSensorAnimation = this.mAnimation;
        if (fingerprintFindSensorAnimation != null) {
            fingerprintFindSensorAnimation.stopAnimation();
        }
    }

    protected void onSkipButtonClick(View view) {
        setResult(2);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void proceedToEnrolling(boolean z) {
        FingerprintEnrollSidecar fingerprintEnrollSidecar = this.mSidecar;
        if (fingerprintEnrollSidecar != null) {
            if (z && fingerprintEnrollSidecar.cancelEnrollment()) {
                return;
            }
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.remove(this.mSidecar);
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            this.mSidecar = null;
            startActivityForResult(getFingerprintEnrollingIntent(), 5);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 4) {
            if (i2 == -1 && intent != null) {
                this.mToken = intent.getByteArrayExtra("hw_auth_token");
                overridePendingTransition(R.anim.sud_slide_next_in, R.anim.sud_slide_next_out);
                getIntent().putExtra("hw_auth_token", this.mToken);
                startLookingForFingerprint();
                return;
            }
            finish();
            return;
        }
        if (i != 5) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        if (i2 == 1) {
            setResult(1);
            finish();
            return;
        }
        if (i2 == 2) {
            setResult(2);
            finish();
        } else if (i2 == 3) {
            setResult(3);
            finish();
        } else if (Utils.getFingerprintManagerOrNull(this).getEnrolledFingerprints().size() >= getResources().getInteger(android.R.integer.config_defaultNightDisplayCustomStartTime)) {
            finish();
        } else {
            startLookingForFingerprint();
        }
    }
}
