package com.android.car.developeroptions.deletionhelper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class ActivationWarningFragment extends DialogFragment {
    public static ActivationWarningFragment newInstance() {
        return new ActivationWarningFragment();
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.automatic_storage_manager_activation_warning);
        builder.setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
        return builder.create();
    }
}
