package com.android.car.developeroptions.biometrics.face;

import android.content.ComponentName;
import android.content.Intent;
import android.hardware.face.FaceManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupcompat.util.WizardManagerHelper;
import com.google.android.setupdesign.span.LinkSpan;
import com.google.android.setupdesign.view.IllustrationVideoView;

/* JADX INFO: loaded from: classes.dex */
public class FaceEnrollIntroduction extends BiometricEnrollIntroduction {
    private FaceManager mFaceManager;
    private View mIllustrationAccessibility;
    private IllustrationVideoView mIllustrationNormal;
    private FaceEnrollAccessibilityToggle mSwitchDiversity;
    private CompoundButton.OnCheckedChangeListener mSwitchDiversityListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.android.car.developeroptions.biometrics.face.FaceEnrollIntroduction.1
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                FaceEnrollIntroduction.this.mIllustrationNormal.stop();
                FaceEnrollIntroduction.this.mIllustrationNormal.setVisibility(4);
                FaceEnrollIntroduction.this.mIllustrationAccessibility.setVisibility(0);
            } else {
                FaceEnrollIntroduction.this.mIllustrationNormal.setVisibility(0);
                FaceEnrollIntroduction.this.mIllustrationNormal.start();
                FaceEnrollIntroduction.this.mIllustrationAccessibility.setVisibility(4);
            }
        }
    };

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected int getConfirmLockTitleResId() {
        return R.string.security_settings_face_preference_title;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected int getDescriptionResDisabledByAdmin() {
        return R.string.security_settings_face_enroll_introduction_message_unlock_disabled;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected String getExtraKeyForBiometric() {
        return "for_face";
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected int getHeaderResDefault() {
        return R.string.security_settings_face_enroll_introduction_title;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected int getHeaderResDisabledByAdmin() {
        return R.string.security_settings_face_enroll_introduction_title_unlock_disabled;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected int getLayoutResource() {
        return R.layout.face_enroll_introduction;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1506;
    }

    @Override // com.google.android.setupdesign.span.LinkSpan.OnClickListener
    public void onClick(LinkSpan linkSpan) {
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction, com.android.car.developeroptions.biometrics.BiometricEnrollBase, com.android.car.developeroptions.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFaceManager = Utils.getFaceManagerOrNull(this);
        final Button button = (Button) findViewById(R.id.accessibility_button);
        final View viewFindViewById = findViewById(R.id.footer_layout);
        button.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.face.-$$Lambda$FaceEnrollIntroduction$0THe7WBNgo2eIbJ3lLau24sXZ1w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreate$0$FaceEnrollIntroduction(button, viewFindViewById, view);
            }
        });
        FaceEnrollAccessibilityToggle faceEnrollAccessibilityToggle = (FaceEnrollAccessibilityToggle) findViewById(R.id.toggle_diversity);
        this.mSwitchDiversity = faceEnrollAccessibilityToggle;
        faceEnrollAccessibilityToggle.setListener(this.mSwitchDiversityListener);
        this.mIllustrationNormal = (IllustrationVideoView) findViewById(R.id.illustration_normal);
        this.mIllustrationAccessibility = findViewById(R.id.illustration_accessibility);
        this.mFooterBarMixin = (FooterBarMixin) getLayout().getMixin(FooterBarMixin.class);
        if (WizardManagerHelper.isAnySetupWizard(getIntent())) {
            FooterBarMixin footerBarMixin = this.mFooterBarMixin;
            FooterButton.Builder builder = new FooterButton.Builder(this);
            builder.setText(R.string.skip_label);
            builder.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.face.-$$Lambda$FaceEnrollIntroduction$P7mtplpnm6n3QOMX2xug5R4NoQg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.onSkipButtonClick(view);
                }
            });
            builder.setButtonType(7);
            builder.setTheme(R.style.SudGlifButton_Secondary);
            footerBarMixin.setSecondaryButton(builder.build());
        } else {
            FooterBarMixin footerBarMixin2 = this.mFooterBarMixin;
            FooterButton.Builder builder2 = new FooterButton.Builder(this);
            builder2.setText(R.string.security_settings_face_enroll_introduction_cancel);
            builder2.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.face.-$$Lambda$FaceEnrollIntroduction$aFgMbrE2eJ54mXY4arr22QFMKxk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.onCancelButtonClick(view);
                }
            });
            builder2.setButtonType(2);
            builder2.setTheme(R.style.SudGlifButton_Secondary);
            footerBarMixin2.setSecondaryButton(builder2.build());
        }
        FooterBarMixin footerBarMixin3 = this.mFooterBarMixin;
        FooterButton.Builder builder3 = new FooterButton.Builder(this);
        builder3.setText(R.string.wizard_next);
        builder3.setListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.biometrics.face.-$$Lambda$FaceEnrollIntroduction$XwwhsA19alQweeLPW9MzeAImodw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.onNextButtonClick(view);
            }
        });
        builder3.setButtonType(5);
        builder3.setTheme(R.style.SudGlifButton_Primary);
        footerBarMixin3.setPrimaryButton(builder3.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreate$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreate$0$FaceEnrollIntroduction(Button button, View view, View view2) {
        this.mSwitchDiversity.setChecked(true);
        button.setVisibility(8);
        this.mSwitchDiversity.setVisibility(0);
        view.setVisibility(8);
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mSwitchDiversityListener.onCheckedChanged(this.mSwitchDiversity.getSwitch(), this.mSwitchDiversity.isChecked());
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected boolean isDisabledByAdmin() {
        return RestrictedLockUtilsInternal.checkIfKeyguardFeaturesDisabled(this, 128, this.mUserId) != null;
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
        if (this.mFaceManager == null) {
            return R.string.face_intro_error_unknown;
        }
        if (this.mFaceManager.getEnrolledFaces(this.mUserId).size() >= getResources().getInteger(android.R.integer.config_defaultNightDisplayCustomEndTime)) {
            return R.string.face_intro_error_max;
        }
        return 0;
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected long getChallenge() {
        FaceManager faceManagerOrNull = Utils.getFaceManagerOrNull(this);
        this.mFaceManager = faceManagerOrNull;
        if (faceManagerOrNull == null) {
            return 0L;
        }
        return faceManagerOrNull.generateChallenge();
    }

    @Override // com.android.car.developeroptions.biometrics.BiometricEnrollIntroduction
    protected Intent getEnrollingIntent() {
        String string = getString(R.string.config_face_enroll);
        Intent intent = new Intent();
        if (!TextUtils.isEmpty(string)) {
            intent.setComponent(ComponentName.unflattenFromString(string));
        } else {
            intent.setClass(this, FaceEnrollEnrolling.class);
        }
        intent.putExtra("accessibility_diversity", !this.mSwitchDiversity.isChecked());
        WizardManagerHelper.copyWizardManagerExtras(getIntent(), intent);
        return intent;
    }
}
