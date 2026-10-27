package com.android.car.developeroptions.security;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class UnificationConfirmationDialog extends InstrumentedDialogFragment {
    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 532;
    }

    public static UnificationConfirmationDialog newInstance(boolean z) {
        UnificationConfirmationDialog unificationConfirmationDialog = new UnificationConfirmationDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean("compliant", z);
        unificationConfirmationDialog.setArguments(bundle);
        return unificationConfirmationDialog;
    }

    public void show(SecuritySettings securitySettings) {
        FragmentManager childFragmentManager = securitySettings.getChildFragmentManager();
        if (childFragmentManager.findFragmentByTag("unification_dialog") == null) {
            show(childFragmentManager, "unification_dialog");
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        final SecuritySettings securitySettings = (SecuritySettings) getParentFragment();
        boolean z = getArguments().getBoolean("compliant");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.lock_settings_profile_unification_dialog_title);
        builder.setMessage(z ? R.string.lock_settings_profile_unification_dialog_body : R.string.lock_settings_profile_unification_dialog_uncompliant_body);
        builder.setPositiveButton(z ? R.string.lock_settings_profile_unification_dialog_confirm : R.string.lock_settings_profile_unification_dialog_uncompliant_confirm, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.security.-$$Lambda$UnificationConfirmationDialog$zFEkTBpkve5KbZu-r0j1-Fq4KFM
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                securitySettings.startUnification();
            }
        });
        builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        return builder.create();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        ((SecuritySettings) getParentFragment()).updateUnificationPreference();
    }
}
