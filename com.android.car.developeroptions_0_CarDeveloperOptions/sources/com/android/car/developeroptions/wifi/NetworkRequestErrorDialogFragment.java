package com.android.car.developeroptions.wifi;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class NetworkRequestErrorDialogFragment extends InstrumentedDialogFragment {

    public enum ERROR_DIALOG_TYPE {
        TIME_OUT,
        ABORT
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1373;
    }

    public static NetworkRequestErrorDialogFragment newInstance() {
        return new NetworkRequestErrorDialogFragment();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        getActivity().finish();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        ERROR_DIALOG_TYPE error_dialog_type = ERROR_DIALOG_TYPE.TIME_OUT;
        if (getArguments() != null) {
            error_dialog_type = (ERROR_DIALOG_TYPE) getArguments().getSerializable("DIALOG_ERROR_TYPE");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (error_dialog_type == ERROR_DIALOG_TYPE.TIME_OUT) {
            builder.setMessage(R.string.network_connection_timeout_dialog_message);
            builder.setPositiveButton(R.string.network_connection_timeout_dialog_ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$NetworkRequestErrorDialogFragment$_Z8gUyX4ahO93PS9uaPjElDWqwI
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$0$NetworkRequestErrorDialogFragment(dialogInterface, i);
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$NetworkRequestErrorDialogFragment$MNhIPGO0Cao0rmjFEOT-ThJ5MXE
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$1$NetworkRequestErrorDialogFragment(dialogInterface, i);
                }
            });
        } else {
            builder.setMessage(R.string.network_connection_errorstate_dialog_message);
            builder.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.wifi.-$$Lambda$NetworkRequestErrorDialogFragment$Xigu62YzpoLW4Uoq0CjMzDbZWwc
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$2$NetworkRequestErrorDialogFragment(dialogInterface, i);
                }
            });
        }
        return builder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$0$NetworkRequestErrorDialogFragment(DialogInterface dialogInterface, int i) {
        startScanningDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$1$NetworkRequestErrorDialogFragment(DialogInterface dialogInterface, int i) {
        getActivity().finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$2$NetworkRequestErrorDialogFragment(DialogInterface dialogInterface, int i) {
        getActivity().finish();
    }

    protected void startScanningDialog() {
        NetworkRequestDialogFragment.newInstance().show(getActivity().getSupportFragmentManager(), NetworkRequestErrorDialogFragment.class.getSimpleName());
    }
}
