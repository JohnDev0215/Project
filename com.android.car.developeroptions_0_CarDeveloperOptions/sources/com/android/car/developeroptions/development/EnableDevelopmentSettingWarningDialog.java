package com.android.car.developeroptions.development;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class EnableDevelopmentSettingWarningDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1219;
    }

    public static void show(DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment) {
        EnableDevelopmentSettingWarningDialog enableDevelopmentSettingWarningDialog = new EnableDevelopmentSettingWarningDialog();
        enableDevelopmentSettingWarningDialog.setTargetFragment(developmentSettingsDashboardFragment, 0);
        FragmentManager supportFragmentManager = developmentSettingsDashboardFragment.getActivity().getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag("EnableDevSettingDlg") == null) {
            enableDevelopmentSettingWarningDialog.show(supportFragmentManager, "EnableDevSettingDlg");
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dev_settings_warning_message);
        builder.setTitle(R.string.dev_settings_warning_title);
        builder.setPositiveButton(android.R.string.yes, this);
        builder.setNegativeButton(android.R.string.no, this);
        return builder.create();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        DevelopmentSettingsDashboardFragment developmentSettingsDashboardFragment = (DevelopmentSettingsDashboardFragment) getTargetFragment();
        if (i == -1) {
            developmentSettingsDashboardFragment.onEnableDevelopmentOptionsConfirmed();
        } else {
            developmentSettingsDashboardFragment.onEnableDevelopmentOptionsRejected();
        }
    }
}
