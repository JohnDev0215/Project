package com.android.car.developeroptions.development;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class DisableLogPersistWarningDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1225;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void show(LogPersistDialogHost logPersistDialogHost) {
        if (logPersistDialogHost instanceof Fragment) {
            Fragment fragment = (Fragment) logPersistDialogHost;
            FragmentManager supportFragmentManager = fragment.getActivity().getSupportFragmentManager();
            if (supportFragmentManager.findFragmentByTag("DisableLogPersistDlg") == null) {
                DisableLogPersistWarningDialog disableLogPersistWarningDialog = new DisableLogPersistWarningDialog();
                disableLogPersistWarningDialog.setTargetFragment(fragment, 0);
                disableLogPersistWarningDialog.show(supportFragmentManager, "DisableLogPersistDlg");
            }
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dev_logpersist_clear_warning_title);
        builder.setMessage(R.string.dev_logpersist_clear_warning_message);
        builder.setPositiveButton(android.R.string.yes, this);
        builder.setNegativeButton(android.R.string.no, this);
        return builder.create();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        LogPersistDialogHost logPersistDialogHost = (LogPersistDialogHost) getTargetFragment();
        if (logPersistDialogHost == null) {
            return;
        }
        if (i == -1) {
            logPersistDialogHost.onDisableLogPersistDialogConfirmed();
        } else {
            logPersistDialogHost.onDisableLogPersistDialogRejected();
        }
    }
}
