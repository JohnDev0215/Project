package com.android.car.settings.wifi.networkrequest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.android.car.settings.R;
import com.android.car.ui.AlertDialogBuilder;

/* JADX INFO: loaded from: classes5.dex */
public class NetworkRequestErrorDialogFragment extends DialogFragment {
    public static final String DIALOG_TYPE = "DIALOG_ERROR_TYPE";

    public enum ERROR_DIALOG_TYPE {
        TIME_OUT,
        ABORT
    }

    public static NetworkRequestErrorDialogFragment newInstance() {
        return new NetworkRequestErrorDialogFragment();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        getActivity().finish();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ERROR_DIALOG_TYPE msgType = ERROR_DIALOG_TYPE.TIME_OUT;
        if (getArguments() != null) {
            msgType = (ERROR_DIALOG_TYPE) getArguments().getSerializable(DIALOG_TYPE);
        }
        AlertDialogBuilder builder = new AlertDialogBuilder(getContext());
        if (msgType == ERROR_DIALOG_TYPE.TIME_OUT) {
            builder.setMessage(R.string.network_connection_timeout_dialog_message).setPositiveButton(R.string.network_connection_timeout_dialog_ok, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.wifi.networkrequest.-$$Lambda$NetworkRequestErrorDialogFragment$anH_nFIj6_yc57fEU8DeF8BbmhA
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$0$NetworkRequestErrorDialogFragment(dialogInterface, i);
                }
            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.wifi.networkrequest.-$$Lambda$NetworkRequestErrorDialogFragment$XaL6smOk71Yu59G0zKLVaFRI9BE
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$1$NetworkRequestErrorDialogFragment(dialogInterface, i);
                }
            });
        } else {
            builder.setMessage(R.string.network_connection_errorstate_dialog_message).setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.wifi.networkrequest.-$$Lambda$NetworkRequestErrorDialogFragment$STJwZaNcNWUQMvrXYcc4sVmzL7Q
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$2$NetworkRequestErrorDialogFragment(dialogInterface, i);
                }
            });
        }
        return builder.create();
    }

    public /* synthetic */ void lambda$onCreateDialog$0$NetworkRequestErrorDialogFragment(DialogInterface dialog, int which) {
        startScanningDialog();
    }

    public /* synthetic */ void lambda$onCreateDialog$1$NetworkRequestErrorDialogFragment(DialogInterface dialog, int which) {
        getActivity().finish();
    }

    public /* synthetic */ void lambda$onCreateDialog$2$NetworkRequestErrorDialogFragment(DialogInterface dialog, int which) {
        getActivity().finish();
    }

    protected void startScanningDialog() {
        NetworkRequestDialogFragment fragment = NetworkRequestDialogFragment.newInstance();
        fragment.show(getActivity().getSupportFragmentManager(), NetworkRequestDialogFragment.class.getSimpleName());
    }
}
