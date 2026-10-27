package com.android.car.developeroptions.backup;

import android.R;
import android.app.Dialog;
import android.app.backup.IBackupManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.car.developeroptions.widget.ToggleSwitch;

/* JADX INFO: loaded from: classes.dex */
public class ToggleBackupSettingFragment extends SettingsPreferenceFragment implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    private IBackupManager mBackupManager;
    private Dialog mConfirmDialog;
    private Preference mSummaryPreference;
    protected SwitchBar mSwitchBar;
    protected ToggleSwitch mToggleSwitch;
    private boolean mWaitingForConfirmationDialog = false;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 81;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBackupManager = IBackupManager.Stub.asInterface(ServiceManager.getService("backup"));
        PreferenceScreen preferenceScreenCreatePreferenceScreen = getPreferenceManager().createPreferenceScreen(getActivity());
        setPreferenceScreen(preferenceScreenCreatePreferenceScreen);
        Preference preference = new Preference(this, getPrefContext()) { // from class: com.android.car.developeroptions.backup.ToggleBackupSettingFragment.1
            @Override // androidx.preference.Preference
            public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
                super.onBindViewHolder(preferenceViewHolder);
                ((TextView) preferenceViewHolder.findViewById(R.id.summary)).setText(getSummary());
            }
        };
        this.mSummaryPreference = preference;
        preference.setPersistent(false);
        this.mSummaryPreference.setLayoutResource(com.android.car.developeroptions.R.layout.text_description_preference);
        preferenceScreenCreatePreferenceScreen.addPreference(this.mSummaryPreference);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        SwitchBar switchBar = ((SettingsActivity) getActivity()).getSwitchBar();
        this.mSwitchBar = switchBar;
        this.mToggleSwitch = switchBar.getSwitch();
        if (Settings.Secure.getInt(getContentResolver(), "user_full_data_backup_aware", 0) != 0) {
            this.mSummaryPreference.setSummary(com.android.car.developeroptions.R.string.fullbackup_data_summary);
        } else {
            this.mSummaryPreference.setSummary(com.android.car.developeroptions.R.string.backup_data_summary);
        }
        try {
            this.mSwitchBar.setCheckedInternal(this.mBackupManager == null ? false : this.mBackupManager.isBackupEnabled());
        } catch (RemoteException unused) {
            this.mSwitchBar.setEnabled(false);
        }
        getActivity().setTitle(com.android.car.developeroptions.R.string.backup_data_title);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mToggleSwitch.setOnBeforeCheckedChangeListener(null);
        this.mSwitchBar.hide();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mToggleSwitch.setOnBeforeCheckedChangeListener(new ToggleSwitch.OnBeforeCheckedChangeListener() { // from class: com.android.car.developeroptions.backup.ToggleBackupSettingFragment.2
            @Override // com.android.car.developeroptions.widget.ToggleSwitch.OnBeforeCheckedChangeListener
            public boolean onBeforeCheckedChanged(ToggleSwitch toggleSwitch, boolean z) {
                if (!z) {
                    ToggleBackupSettingFragment.this.showEraseBackupDialog();
                    return true;
                }
                ToggleBackupSettingFragment.this.setBackupEnabled(true);
                ToggleBackupSettingFragment.this.mSwitchBar.setCheckedInternal(true);
                return true;
            }
        });
        this.mSwitchBar.show();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        Dialog dialog = this.mConfirmDialog;
        if (dialog != null && dialog.isShowing()) {
            this.mConfirmDialog.dismiss();
        }
        this.mConfirmDialog = null;
        super.onStop();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            this.mWaitingForConfirmationDialog = false;
            setBackupEnabled(false);
            this.mSwitchBar.setCheckedInternal(false);
        } else if (i == -2) {
            this.mWaitingForConfirmationDialog = false;
            setBackupEnabled(true);
            this.mSwitchBar.setCheckedInternal(true);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        if (this.mWaitingForConfirmationDialog) {
            setBackupEnabled(true);
            this.mSwitchBar.setCheckedInternal(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEraseBackupDialog() {
        CharSequence text;
        if (Settings.Secure.getInt(getContentResolver(), "user_full_data_backup_aware", 0) != 0) {
            text = getResources().getText(com.android.car.developeroptions.R.string.fullbackup_erase_dialog_message);
        } else {
            text = getResources().getText(com.android.car.developeroptions.R.string.backup_erase_dialog_message);
        }
        this.mWaitingForConfirmationDialog = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(text);
        builder.setTitle(com.android.car.developeroptions.R.string.backup_erase_dialog_title);
        builder.setPositiveButton(R.string.ok, this);
        builder.setNegativeButton(R.string.cancel, this);
        builder.setOnDismissListener(this);
        this.mConfirmDialog = builder.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBackupEnabled(boolean z) {
        IBackupManager iBackupManager = this.mBackupManager;
        if (iBackupManager != null) {
            try {
                iBackupManager.setBackupEnabled(z);
            } catch (RemoteException e) {
                Log.e("ToggleBackupSettingFragment", "Error communicating with BackupManager", e);
            }
        }
    }
}
