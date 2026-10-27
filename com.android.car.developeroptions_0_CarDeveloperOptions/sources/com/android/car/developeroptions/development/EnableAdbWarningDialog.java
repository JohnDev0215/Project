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
public class EnableAdbWarningDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1222;
    }

    public static void show(Fragment fragment) {
        FragmentManager supportFragmentManager = fragment.getActivity().getSupportFragmentManager();
        if (supportFragmentManager.findFragmentByTag("EnableAdbDialog") == null) {
            EnableAdbWarningDialog enableAdbWarningDialog = new EnableAdbWarningDialog();
            enableAdbWarningDialog.setTargetFragment(fragment, 0);
            enableAdbWarningDialog.show(supportFragmentManager, "EnableAdbDialog");
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.adb_warning_title);
        builder.setMessage(R.string.adb_warning_message);
        builder.setPositiveButton(android.R.string.yes, this);
        builder.setNegativeButton(android.R.string.no, this);
        return builder.create();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        AdbDialogHost adbDialogHost = (AdbDialogHost) getTargetFragment();
        if (adbDialogHost == null) {
            return;
        }
        if (i == -1) {
            adbDialogHost.onEnableAdbDialogConfirmed();
        } else {
            adbDialogHost.onEnableAdbDialogDismissed();
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        AdbDialogHost adbDialogHost = (AdbDialogHost) getTargetFragment();
        if (adbDialogHost == null) {
            return;
        }
        adbDialogHost.onEnableAdbDialogDismissed();
    }
}
