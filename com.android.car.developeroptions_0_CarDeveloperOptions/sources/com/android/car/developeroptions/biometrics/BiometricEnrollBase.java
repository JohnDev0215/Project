package com.android.car.developeroptions.biometrics;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.widget.TextView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SetupWizardUtils;
import com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollEnrolling;
import com.android.car.developeroptions.core.InstrumentedActivity;
import com.android.car.developeroptions.password.ChooseLockSettingsHelper;
import com.google.android.setupcompat.template.FooterBarMixin;
import com.google.android.setupcompat.template.FooterButton;
import com.google.android.setupdesign.GlifLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class BiometricEnrollBase extends InstrumentedActivity {
    protected FooterBarMixin mFooterBarMixin;
    protected boolean mLaunchedConfirmLock;
    protected byte[] mToken;
    protected int mUserId;

    @Override // com.android.car.developeroptions.core.InstrumentedActivity, com.android.settingslib.core.lifecycle.ObservableActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        byte[] byteArrayExtra = getIntent().getByteArrayExtra("hw_auth_token");
        this.mToken = byteArrayExtra;
        if (bundle != null && byteArrayExtra == null) {
            this.mLaunchedConfirmLock = bundle.getBoolean("launched_confirm_lock");
            this.mToken = bundle.getByteArray("hw_auth_token");
        }
        this.mUserId = getIntent().getIntExtra("android.intent.extra.USER_ID", UserHandle.myUserId());
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper
    protected void onApplyThemeResource(Resources.Theme theme, int i, boolean z) {
        int theme2 = SetupWizardUtils.getTheme(getIntent());
        theme.applyStyle(R.style.SetupWizardPartnerResource, true);
        super.onApplyThemeResource(theme, theme2, z);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("launched_confirm_lock", this.mLaunchedConfirmLock);
        bundle.putByteArray("hw_auth_token", this.mToken);
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        initViews();
    }

    protected void initViews() {
        getWindow().setStatusBarColor(0);
    }

    protected GlifLayout getLayout() {
        return (GlifLayout) findViewById(R.id.setup_wizard_layout);
    }

    protected void setHeaderText(int i, boolean z) {
        TextView headerTextView = getLayout().getHeaderTextView();
        CharSequence text = headerTextView.getText();
        CharSequence text2 = getText(i);
        if (text != text2 || z) {
            if (!TextUtils.isEmpty(text)) {
                headerTextView.setAccessibilityLiveRegion(1);
            }
            getLayout().setHeaderText(text2);
            setTitle(text2);
        }
    }

    protected void setHeaderText(int i) {
        setHeaderText(i, false);
    }

    protected FooterButton getNextButton() {
        FooterBarMixin footerBarMixin = this.mFooterBarMixin;
        if (footerBarMixin != null) {
            return footerBarMixin.getPrimaryButton();
        }
        return null;
    }

    protected Intent getFingerprintEnrollingIntent() {
        Intent intent = new Intent();
        intent.setClassName("com.android.car.developeroptions", FingerprintEnrollEnrolling.class.getName());
        intent.putExtra("hw_auth_token", this.mToken);
        int i = this.mUserId;
        if (i != -10000) {
            intent.putExtra("android.intent.extra.USER_ID", i);
        }
        return intent;
    }

    protected void launchConfirmLock(int i, long j) {
        boolean zLaunchConfirmationActivity;
        ChooseLockSettingsHelper chooseLockSettingsHelper = new ChooseLockSettingsHelper(this);
        if (this.mUserId == -10000) {
            zLaunchConfirmationActivity = chooseLockSettingsHelper.launchConfirmationActivity(4, getString(i), null, null, j);
        } else {
            zLaunchConfirmationActivity = chooseLockSettingsHelper.launchConfirmationActivity(4, getString(i), (CharSequence) null, (CharSequence) null, j, this.mUserId);
        }
        if (!zLaunchConfirmationActivity) {
            finish();
        } else {
            this.mLaunchedConfirmLock = true;
        }
    }
}
