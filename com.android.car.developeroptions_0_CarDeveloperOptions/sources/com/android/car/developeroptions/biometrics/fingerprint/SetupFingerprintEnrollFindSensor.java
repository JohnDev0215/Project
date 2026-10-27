package com.android.car.developeroptions.biometrics.fingerprint;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SetupWizardUtils;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class SetupFingerprintEnrollFindSensor extends FingerprintEnrollFindSensor {
    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollFindSensor
    protected int getContentView() {
        return R.layout.fingerprint_enroll_find_sensor;
    }

    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollFindSensor, com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 247;
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

    @Override // com.android.car.developeroptions.biometrics.fingerprint.FingerprintEnrollFindSensor
    protected void onSkipButtonClick(View view) {
        new SkipFingerprintDialog().show(getSupportFragmentManager());
    }

    public static class SkipFingerprintDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 573;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            return onCreateDialogBuilder().create();
        }

        public AlertDialog.Builder onCreateDialogBuilder() {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(R.string.setup_fingerprint_enroll_skip_title);
            builder.setPositiveButton(R.string.skip_anyway_button_label, this);
            builder.setNegativeButton(R.string.go_back_button_label, this);
            builder.setMessage(R.string.setup_fingerprint_enroll_skip_after_adding_lock_text);
            return builder;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            FragmentActivity activity;
            if (i == -1 && (activity = getActivity()) != null) {
                activity.setResult(2);
                activity.finish();
            }
        }

        public void show(FragmentManager fragmentManager) {
            show(fragmentManager, "skip_dialog");
        }
    }
}
