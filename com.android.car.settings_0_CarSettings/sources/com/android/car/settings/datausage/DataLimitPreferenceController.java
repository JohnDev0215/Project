package com.android.car.settings.datausage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.datausage.UsageBytesThresholdPickerDialog;

/* JADX INFO: loaded from: classes5.dex */
public class DataLimitPreferenceController extends DataWarningAndLimitBasePreferenceController<PreferenceGroup> implements Preference.OnPreferenceChangeListener, ConfirmationDialogFragment.ConfirmListener, Preference.OnPreferenceClickListener {
    private static final long GIB_IN_BYTES = 1073741824;
    static final float LIMIT_BYTES_MULTIPLIER = 1.2f;
    private TwoStatePreference mEnableDataLimitPreference;
    private Preference mSetDataLimitPreference;
    private final UsageBytesThresholdPickerDialog.BytesThresholdPickedListener mThresholdPickedListener;

    public /* synthetic */ void lambda$new$0$DataLimitPreferenceController(long numBytes) {
        getNetworkPolicyEditor().setPolicyLimitBytes(getNetworkTemplate(), numBytes);
        refreshUi();
    }

    public DataLimitPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mThresholdPickedListener = new UsageBytesThresholdPickerDialog.BytesThresholdPickedListener() { // from class: com.android.car.settings.datausage.-$$Lambda$DataLimitPreferenceController$V1fyGp4VLxzsve6x4arx4vcUGMM
            @Override // com.android.car.settings.datausage.UsageBytesThresholdPickerDialog.BytesThresholdPickedListener
            public final void onThresholdPicked(long j) {
                this.f$0.lambda$new$0$DataLimitPreferenceController(j);
            }
        };
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        TwoStatePreference twoStatePreference = (TwoStatePreference) ((PreferenceGroup) getPreference()).findPreference(getContext().getString(R.string.pk_data_set_limit));
        this.mEnableDataLimitPreference = twoStatePreference;
        twoStatePreference.setOnPreferenceChangeListener(this);
        Preference preferenceFindPreference = ((PreferenceGroup) getPreference()).findPreference(getContext().getString(R.string.pk_data_limit));
        this.mSetDataLimitPreference = preferenceFindPreference;
        preferenceFindPreference.setOnPreferenceClickListener(this);
        ConfirmationDialogFragment.resetListeners((ConfirmationDialogFragment) getFragmentController().findDialogByTag(ConfirmationDialogFragment.TAG), this, null, null);
        UsageBytesThresholdPickerDialog dialog = (UsageBytesThresholdPickerDialog) getFragmentController().findDialogByTag(UsageBytesThresholdPickerDialog.TAG);
        if (dialog != null) {
            dialog.setBytesThresholdPickedListener(this.mThresholdPickedListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preference) {
        long limitBytes = getNetworkPolicyEditor().getPolicyLimitBytes(getNetworkTemplate());
        if (limitBytes == -1) {
            this.mEnableDataLimitPreference.setChecked(false);
            this.mSetDataLimitPreference.setSummary((CharSequence) null);
        } else {
            this.mEnableDataLimitPreference.setChecked(true);
            this.mSetDataLimitPreference.setSummary(DataUsageUtils.bytesToIecUnits(getContext(), limitBytes));
        }
        this.mSetDataLimitPreference.setEnabled(this.mEnableDataLimitPreference.isChecked());
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean enabled = ((Boolean) newValue).booleanValue();
        if (!enabled) {
            getNetworkPolicyEditor().setPolicyLimitBytes(getNetworkTemplate(), -1L);
            refreshUi();
            return true;
        }
        ConfirmationDialogFragment dialogFragment = new ConfirmationDialogFragment.Builder(getContext()).setTitle(R.string.data_usage_limit_dialog_title).setMessage(R.string.data_usage_limit_dialog_mobile).setPositiveButton(android.R.string.ok, this).setNegativeButton(android.R.string.cancel, (ConfirmationDialogFragment.RejectListener) null).build();
        getFragmentController().showDialog(dialogFragment, ConfirmationDialogFragment.TAG);
        return false;
    }

    @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
    public void onConfirm(Bundle arguments) {
        long warningBytes = getNetworkPolicyEditor().getPolicyWarningBytes(getNetworkTemplate());
        long minLimitBytes = 0;
        if (warningBytes != -1) {
            minLimitBytes = (long) (warningBytes * LIMIT_BYTES_MULTIPLIER);
        }
        long limitBytes = Math.max(5368709120L, minLimitBytes);
        getNetworkPolicyEditor().setPolicyLimitBytes(getNetworkTemplate(), limitBytes);
        refreshUi();
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        UsageBytesThresholdPickerDialog dialog = UsageBytesThresholdPickerDialog.newInstance(R.string.data_usage_limit_editor_title, getNetworkPolicyEditor().getPolicyLimitBytes(getNetworkTemplate()));
        dialog.setBytesThresholdPickedListener(this.mThresholdPickedListener);
        getFragmentController().showDialog(dialog, UsageBytesThresholdPickerDialog.TAG);
        return true;
    }
}
