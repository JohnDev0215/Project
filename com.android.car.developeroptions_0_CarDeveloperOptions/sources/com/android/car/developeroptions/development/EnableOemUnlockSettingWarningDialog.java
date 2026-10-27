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
public class EnableOemUnlockSettingWarningDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1220;
    }

    public static void show(Fragment fragment) {
        FragmentManager supportFragmentManager = fragment.getActivity().getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag("EnableOemUnlockDlg") == null) {
            EnableOemUnlockSettingWarningDialog enableOemUnlockSettingWarningDialog = new EnableOemUnlockSettingWarningDialog();
            enableOemUnlockSettingWarningDialog.setTargetFragment(fragment, 0);
            enableOemUnlockSettingWarningDialog.show(supportFragmentManager, "EnableOemUnlockDlg");
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.confirm_enable_oem_unlock_title);
        builder.setMessage(R.string.confirm_enable_oem_unlock_text);
        builder.setPositiveButton(R.string.enable_text, this);
        builder.setNegativeButton(android.R.string.cancel, this);
        return builder.create();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        OemUnlockDialogHost oemUnlockDialogHost = (OemUnlockDialogHost) getTargetFragment();
        if (oemUnlockDialogHost == null) {
            return;
        }
        if (i == -1) {
            oemUnlockDialogHost.onOemUnlockDialogConfirmed();
        } else {
            oemUnlockDialogHost.onOemUnlockDialogDismissed();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        OemUnlockDialogHost oemUnlockDialogHost = (OemUnlockDialogHost) getTargetFragment();
        if (oemUnlockDialogHost == null) {
            return;
        }
        oemUnlockDialogHost.onOemUnlockDialogDismissed();
    }
}
