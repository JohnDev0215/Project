package com.android.car.settings.datausage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.datausage.UsageBytesThresholdPickerDialog;
import com.android.settingslib.net.DataUsageController;

/* JADX INFO: loaded from: classes5.dex */
public class DataWarningPreferenceController extends DataWarningAndLimitBasePreferenceController<PreferenceGroup> implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private final DataUsageController mDataUsageController;
    private TwoStatePreference mEnableDataWarningPreference;
    private Preference mSetDataWarningPreference;
    private final UsageBytesThresholdPickerDialog.BytesThresholdPickedListener mThresholdPickedListener;

    public /* synthetic */ void lambda$new$0$DataWarningPreferenceController(long numBytes) {
        getNetworkPolicyEditor().setPolicyWarningBytes(getNetworkTemplate(), numBytes);
        refreshUi();
    }

    public DataWarningPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mThresholdPickedListener = new UsageBytesThresholdPickerDialog.BytesThresholdPickedListener() { // from class: com.android.car.settings.datausage.-$$Lambda$DataWarningPreferenceController$p_mISwuddO7KGFJR3FJZP7-S6-E
            @Override // com.android.car.settings.datausage.UsageBytesThresholdPickerDialog.BytesThresholdPickedListener
            public final void onThresholdPicked(long j) {
                this.f$0.lambda$new$0$DataWarningPreferenceController(j);
            }
        };
        this.mDataUsageController = new DataUsageController(getContext());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        TwoStatePreference twoStatePreference = (TwoStatePreference) ((PreferenceGroup) getPreference()).findPreference(getContext().getString(R.string.pk_data_set_warning));
        this.mEnableDataWarningPreference = twoStatePreference;
        twoStatePreference.setOnPreferenceChangeListener(this);
        Preference preferenceFindPreference = ((PreferenceGroup) getPreference()).findPreference(getContext().getString(R.string.pk_data_warning));
        this.mSetDataWarningPreference = preferenceFindPreference;
        preferenceFindPreference.setOnPreferenceClickListener(this);
        UsageBytesThresholdPickerDialog dialog = (UsageBytesThresholdPickerDialog) getFragmentController().findDialogByTag(UsageBytesThresholdPickerDialog.TAG);
        if (dialog != null) {
            dialog.setBytesThresholdPickedListener(this.mThresholdPickedListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preference) {
        long warningBytes = getNetworkPolicyEditor().getPolicyWarningBytes(getNetworkTemplate());
        if (warningBytes == -1) {
            this.mSetDataWarningPreference.setSummary((CharSequence) null);
            this.mEnableDataWarningPreference.setChecked(false);
        } else {
            this.mSetDataWarningPreference.setSummary(DataUsageUtils.bytesToIecUnits(getContext(), warningBytes));
            this.mEnableDataWarningPreference.setChecked(true);
        }
        this.mSetDataWarningPreference.setEnabled(this.mEnableDataWarningPreference.isChecked());
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        boolean enabled = ((Boolean) newValue).booleanValue();
        getNetworkPolicyEditor().setPolicyWarningBytes(getNetworkTemplate(), enabled ? this.mDataUsageController.getDefaultWarningLevel() : -1L);
        refreshUi();
        return true;
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        UsageBytesThresholdPickerDialog dialog = UsageBytesThresholdPickerDialog.newInstance(R.string.data_usage_warning_editor_title, getNetworkPolicyEditor().getPolicyWarningBytes(getNetworkTemplate()));
        dialog.setBytesThresholdPickedListener(this.mThresholdPickedListener);
        getFragmentController().showDialog(dialog, UsageBytesThresholdPickerDialog.TAG);
        return true;
    }
}
