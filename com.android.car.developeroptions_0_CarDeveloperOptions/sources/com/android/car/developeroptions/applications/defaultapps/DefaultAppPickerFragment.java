package com.android.car.developeroptions.applications.defaultapps;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.car.developeroptions.fuelgauge.BatteryUtils;
import com.android.car.developeroptions.widget.RadioButtonPickerFragment;
import com.android.car.developeroptions.widget.RadioButtonPreference;
import com.android.settingslib.applications.DefaultAppInfo;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.widget.CandidateInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class DefaultAppPickerFragment extends RadioButtonPickerFragment {
    protected PackageManager mPm;

    protected CharSequence getConfirmationMessage(CandidateInfo candidateInfo) {
        return null;
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mPm = context.getPackageManager();
        BatteryUtils.getInstance(context);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment, com.android.car.developeroptions.widget.RadioButtonPreference.OnClickListener
    public void onRadioButtonClicked(RadioButtonPreference radioButtonPreference) {
        String key = radioButtonPreference.getKey();
        CharSequence confirmationMessage = getConfirmationMessage(getCandidate(key));
        FragmentActivity activity = getActivity();
        if (TextUtils.isEmpty(confirmationMessage)) {
            super.onRadioButtonClicked(radioButtonPreference);
        } else if (activity != null) {
            newConfirmationDialogFragment(key, confirmationMessage).show(activity.getSupportFragmentManager(), "DefaultAppConfirm");
        }
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    protected void onRadioButtonConfirmed(String str) {
        MetricsFeatureProvider metricsFeatureProvider = this.mMetricsFeatureProvider;
        metricsFeatureProvider.action(metricsFeatureProvider.getAttribution(getActivity()), 1000, getMetricsCategory(), str, 0);
        super.onRadioButtonConfirmed(str);
    }

    @Override // com.android.car.developeroptions.widget.RadioButtonPickerFragment
    public void bindPreferenceExtra(RadioButtonPreference radioButtonPreference, String str, CandidateInfo candidateInfo, String str2, String str3) {
        if (candidateInfo instanceof DefaultAppInfo) {
            if (TextUtils.equals(str3, str)) {
                radioButtonPreference.setSummary(R.string.system_app);
                return;
            }
            DefaultAppInfo defaultAppInfo = (DefaultAppInfo) candidateInfo;
            if (TextUtils.isEmpty(defaultAppInfo.summary)) {
                return;
            }
            radioButtonPreference.setSummary(defaultAppInfo.summary);
        }
    }

    protected ConfirmationDialogFragment newConfirmationDialogFragment(String str, CharSequence charSequence) {
        ConfirmationDialogFragment confirmationDialogFragment = new ConfirmationDialogFragment();
        confirmationDialogFragment.init(this, str, charSequence);
        return confirmationDialogFragment;
    }

    public static class ConfirmationDialogFragment extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
        private DialogInterface.OnClickListener mCancelListener;

        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 791;
        }

        public void init(DefaultAppPickerFragment defaultAppPickerFragment, String str, CharSequence charSequence) {
            Bundle bundle = new Bundle();
            bundle.putString("extra_key", str);
            bundle.putCharSequence("extra_message", charSequence);
            setArguments(bundle);
            setTargetFragment(defaultAppPickerFragment, 0);
        }

        public void setCancelListener(DialogInterface.OnClickListener onClickListener) {
            this.mCancelListener = onClickListener;
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            Bundle arguments = getArguments();
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(arguments.getCharSequence("extra_message"));
            builder.setPositiveButton(android.R.string.ok, this);
            builder.setNegativeButton(android.R.string.cancel, this.mCancelListener);
            return builder.create();
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Fragment targetFragment = getTargetFragment();
            if (targetFragment instanceof DefaultAppPickerFragment) {
                ((DefaultAppPickerFragment) targetFragment).onRadioButtonConfirmed(getArguments().getString("extra_key"));
            }
        }
    }
}
