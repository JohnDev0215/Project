package com.android.car.settings.common;

import android.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.android.car.ui.AlertDialogBuilder;
import com.android.car.ui.preference.CarUiDialogFragment;

/* JADX INFO: loaded from: classes5.dex */
public class ErrorDialog extends CarUiDialogFragment {
    private static final String DIALOG_TAG = "ErrorDialogTag";
    private static final String ERROR_DIALOG_TITLE_KEY = "error_dialog_title";

    public static ErrorDialog show(Fragment parent, int title) {
        ErrorDialog dialog = newInstance(title);
        dialog.setTargetFragment(parent, 0);
        dialog.show(parent.getFragmentManager(), DIALOG_TAG);
        return dialog;
    }

    public static ErrorDialog newInstance(int title) {
        ErrorDialog dialog = new ErrorDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(ERROR_DIALOG_TITLE_KEY, title);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialogBuilder(getContext()).setTitle(getArguments().getInt(ERROR_DIALOG_TITLE_KEY)).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).create();
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onDialogClosed(boolean positiveResult) {
    }
}
