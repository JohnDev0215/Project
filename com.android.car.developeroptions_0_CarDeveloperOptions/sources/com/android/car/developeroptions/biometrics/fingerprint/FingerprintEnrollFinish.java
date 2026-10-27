package com.android.car.developeroptions.biometrics.fingerprint;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.view.View;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.biometrics.BiometricEnrollBase;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;

/* JADX INFO: loaded from: classes.dex */
public class FingerprintEnrollFinish extends BiometricEnrollBase {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 242;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollBase, com.android.car.developeroptions.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.fingerprint_enroll_finish);
        setHeaderText(R.string.security_settings_fingerprint_enroll_finish_title);
        FooterBarMixin footerBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        this.mFooterBarMixin = footerBarMixin;
        FooterButton.Builder builder = new FooterButton.Builder(this);
        builder.setText(R.string.fingerprint_enroll_button_add);
        builder.setButtonType(7);
        builder.setTheme(R.style.SudGlifButton_Secondary);
        footerBarMixin.setSecondaryButton(builder.build());
        FooterBarMixin footerBarMixin2 = this.mFooterBarMixin;
        FooterButton.Builder builder2 = new FooterButton.Builder(this);
        builder2.setText(R.string.security_settings_fingerprint_enroll_done);
        builder2.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.-$$Lambda$JZ5-70y0iWrJIrjX2tcR8yJKGkQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onNextButtonClick(view);
            }
        });
        builder2.setButtonType(5);
        builder2.setTheme(R.style.SudGlifButton_Primary);
        footerBarMixin2.setPrimaryButton(builder2.build());
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        FooterButton secondaryButton = this.mFooterBarMixin.getSecondaryButton();
        FingerprintManager fingerprintManagerOrNull = Utils.getFingerprintManagerOrNull(this);
        boolean z = false;
        if (fingerprintManagerOrNull != null && fingerprintManagerOrNull.getEnrolledFingerprints(this.mUserId).size() >= getResources().getInteger(android.R.integer.config_defaultNightDisplayCustomStartTime)) {
            z = true;
        }
        if (z) {
            secondaryButton.setVisibility(4);
        } else {
            secondaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.fingerprint.-$$Lambda$FingerprintEnrollFinish$P0iPEtb6Ij9981MKxjCj61icUUo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.onAddAnotherButtonClick(view);
                }
            });
        }
    }

    protected void onNextButtonClick(View view) {
        setResult(1);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAddAnotherButtonClick(View view) {
        startActivityForResult(getFingerprintEnrollingIntent(), 1);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 != 0) {
            setResult(i2, intent);
            finish();
        } else {
            super.onActivityResult(i, i2, intent);
        }
    }
}
