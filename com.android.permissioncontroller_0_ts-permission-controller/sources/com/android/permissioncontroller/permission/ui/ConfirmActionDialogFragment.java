package com.android.permissioncontroller.permission.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.DialogFragment;
import com.android.permissioncontroller.R;

/* JADX INFO: loaded from: classes8.dex */
public final class ConfirmActionDialogFragment extends DialogFragment {
    public static final String ARG_ACTION = "ACTION";
    public static final String ARG_MESSAGE = "MESSAGE";

    public interface OnActionConfirmedListener {
        void onActionConfirmed(String str);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return new AlertDialog.Builder(getContext()).setMessage(getArguments().getString(ARG_MESSAGE)).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.grant_dialog_button_deny_anyway, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.-$$Lambda$ConfirmActionDialogFragment$qRT26DBx6csU9zfeRIX9y3OQm2E
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreateDialog$0$ConfirmActionDialogFragment(dialogInterface, i);
            }
        }).create();
    }

    public /* synthetic */ void lambda$onCreateDialog$0$ConfirmActionDialogFragment(DialogInterface dialog, int which) {
        KeyEventDispatcher.Component activity = getActivity();
        if (activity instanceof OnActionConfirmedListener) {
            String groupName = getArguments().getString(ARG_ACTION);
            ((OnActionConfirmedListener) activity).onActionConfirmed(groupName);
        }
    }
}
