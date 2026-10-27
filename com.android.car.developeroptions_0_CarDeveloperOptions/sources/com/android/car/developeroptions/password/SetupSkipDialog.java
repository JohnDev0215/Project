package com.android.car.developeroptions.password;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class SetupSkipDialog extends InstrumentedDialogFragment implements DialogInterface.OnClickListener {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 573;
    }

    public static SetupSkipDialog newInstance(boolean z) {
        SetupSkipDialog setupSkipDialog = new SetupSkipDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean("frp_supported", z);
        setupSkipDialog.setArguments(bundle);
        return setupSkipDialog;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return onCreateDialogBuilder().create();
    }

    public AlertDialog.Builder onCreateDialogBuilder() {
        Bundle arguments = getArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setPositiveButton(R.string.skip_anyway_button_label, this);
        builder.setNegativeButton(R.string.go_back_button_label, this);
        builder.setTitle(R.string.lock_screen_intro_skip_title);
        builder.setMessage(arguments.getBoolean("frp_supported") ? R.string.lock_screen_intro_skip_dialog_text_frp : R.string.lock_screen_intro_skip_dialog_text);
        return builder;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i != -1) {
            return;
        }
        FragmentActivity activity = getActivity();
        activity.setResult(11);
        activity.finish();
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, "skip_dialog");
    }
}
