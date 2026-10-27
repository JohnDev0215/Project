package com.android.car.developeroptions.biometrics.fingerprint;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction;
import com.android.settingslib.HelpUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.span.LinkSpan;

/* JADX INFO: loaded from: classes.dex */
public class FingerprintEnrollIntroduction extends BiometricEnrollIntroduction {
    private FingerprintManager mFingerprintManager;

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected int getConfirmLockTitleResId() {
        return R.string.security_settings_fingerprint_preference_title;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected int getDescriptionResDisabledByAdmin() {
        return R.string.security_settings_fingerprint_enroll_introduction_message_unlock_disabled;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected String getExtraKeyForBiometric() {
        return "for_fingerprint";
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected int getHeaderResDefault() {
        return R.string.security_settings_fingerprint_enroll_introduction_title;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected int getHeaderResDisabledByAdmin() {
        return R.string.security_settings_fingerprint_enroll_introduction_title_unlock_disabled;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected int getLayoutResource() {
        return R.layout.fingerprint_enroll_introduction;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 243;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction, com.android.car.developeroptions.biometrics.BiometricEnrollBase, com.android.car.developeroptions.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFingerprintManager = Utils.getFingerprintManagerOrNull(this);
        FooterBarMixin footerBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        this.mFooterBarMixin = footerBarMixin;
        FooterButton.Builder builder = new FooterButton.Builder(this);
        builder.setText(R.string.security_settings_face_enroll_introduction_cancel);
        builder.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.-$$Lambda$FingerprintEnrollIntroduction$-3HkjiDXhsLgsfLZT2wif944ahw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onCancelButtonClick(view);
            }
        });
        builder.setButtonType(7);
        builder.setTheme(R.style.SudGlifButton_Secondary);
        footerBarMixin.setSecondaryButton(builder.build());
        FooterBarMixin footerBarMixin2 = this.mFooterBarMixin;
        FooterButton.Builder builder2 = new FooterButton.Builder(this);
        builder2.setText(R.string.wizard_next);
        builder2.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.-$$Lambda$FingerprintEnrollIntroduction$SoOftku3OQiHAzWcY0s6DLTsjKg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onNextButtonClick(view);
            }
        });
        builder2.setButtonType(5);
        builder2.setTheme(R.style.SudGlifButton_Primary);
        footerBarMixin2.setPrimaryButton(builder2.build());
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected boolean isDisabledByAdmin() {
        return RestrictedLockUtilsInternal.checkIfKeyguardFeaturesDisabled(this, 32, this.mUserId) != null;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollBase
    protected FooterButton getNextButton() {
        FooterBarMixin footerBarMixin = this.mFooterBarMixin;
        if (footerBarMixin != null) {
            return footerBarMixin.getPrimaryButton();
        }
        return null;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected TextView getErrorTextView() {
        return (TextView) findViewById(R.id.error_text);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected int checkMaxEnrolled() {
        if (this.mFingerprintManager == null) {
            return R.string.fingerprint_intro_error_unknown;
        }
        if (this.mFingerprintManager.getEnrolledFingerprints(this.mUserId).size() >= getResources().getInteger(android.R.integer.config_defaultNightDisplayCustomStartTime)) {
            return R.string.fingerprint_intro_error_max;
        }
        return 0;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected long getChallenge() {
        FingerprintManager fingerprintManagerOrNull = Utils.getFingerprintManagerOrNull(this);
        this.mFingerprintManager = fingerprintManagerOrNull;
        if (fingerprintManagerOrNull == null) {
            return 0L;
        }
        return fingerprintManagerOrNull.preEnroll();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected Intent getEnrollingIntent() {
        return new Intent(this, (Class<?>) FingerprintEnrollFindSensor.class);
    }

    @Override // com.google.android.setupdesign.span.LinkSpan.OnClickListener
    public void onClick(LinkSpan linkSpan) {
        if ("url".equals(linkSpan.getId())) {
            Intent helpIntent = HelpUtils.getHelpIntent(this, getString(R.string.help_url_fingerprint), FingerprintEnrollIntroduction.class.getName());
            if (helpIntent == null) {
                Log.w("FingerprintIntro", "Null help intent.");
                return;
            }
            try {
                startActivityForResult(helpIntent, 3);
            } catch (ActivityNotFoundException e) {
                Log.w("FingerprintIntro", "Activity was not found for intent, " + e);
            }
        }
    }
}
