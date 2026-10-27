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
public class ClearAdbKeysWarningDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1223;
    }

    public static void show(Fragment fragment) {
        FragmentManager supportFragmentManager = fragment.getActivity().getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag("ClearAdbKeysDlg") == null) {
            ClearAdbKeysWarningDialog clearAdbKeysWarningDialog = new ClearAdbKeysWarningDialog();
            clearAdbKeysWarningDialog.setTargetFragment(fragment, 0);
            clearAdbKeysWarningDialog.show(supportFragmentManager, "ClearAdbKeysDlg");
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.adb_keys_warning_message);
        builder.setPositiveButton(android.R.string.ok, this);
        builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
        return builder.create();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        AdbClearKeysDialogHost adbClearKeysDialogHost = (AdbClearKeysDialogHost) getTargetFragment();
        if (adbClearKeysDialogHost == null) {
            return;
        }
        adbClearKeysDialogHost.onAdbClearKeysDialogConfirmed();
    }
}
