package com.android.packageinstaller.handheld;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import com.android.packageinstaller.UninstallerActivity;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorDialogFragment extends DialogFragment {
    public static final String TEXT = "com.android.packageinstaller.arg.text";
    public static final String TITLE = "com.android.packageinstaller.arg.title";

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity()).setMessage(getArguments().getInt("com.android.packageinstaller.arg.text")).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null);
        if (getArguments().containsKey("com.android.packageinstaller.arg.title")) {
            b.setTitle(getArguments().getInt("com.android.packageinstaller.arg.title"));
        }
        return b.create();
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (isAdded()) {
            if (getActivity() instanceof UninstallerActivity) {
                ((UninstallerActivity) getActivity()).dispatchAborted();
            }
            getActivity().setResult(1);
            getActivity().finish();
        }
    }
}
