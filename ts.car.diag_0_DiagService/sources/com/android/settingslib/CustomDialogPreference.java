package com.android.settingslib;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.DialogPreference;
import androidx.preference.PreferenceDialogFragment;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class CustomDialogPreference extends DialogPreference {
    private DialogInterface.OnShowListener V;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class CustomPreferenceDialogFragment extends PreferenceDialogFragment {
        private CustomDialogPreference h() {
            return (CustomDialogPreference) a();
        }

        @Override // androidx.preference.PreferenceDialogFragment
        protected void c(View view) {
            super.c(view);
            h().N0(view);
        }

        @Override // androidx.preference.PreferenceDialogFragment
        public void e(boolean z) {
            h().P0(z);
        }

        @Override // androidx.preference.PreferenceDialogFragment
        protected void f(AlertDialog.Builder builder) {
            super.f(builder);
            h().R0(this);
            h().Q0(builder, this);
        }

        @Override // androidx.preference.PreferenceDialogFragment, android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            super.onClick(dialogInterface, i);
            h().O0(dialogInterface, i);
        }

        @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            Dialog dialogOnCreateDialog = super.onCreateDialog(bundle);
            dialogOnCreateDialog.setOnShowListener(h().M0());
            return dialogOnCreateDialog;
        }
    }

    public CustomDialogPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DialogInterface.OnShowListener M0() {
        return this.V;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R0(CustomPreferenceDialogFragment customPreferenceDialogFragment) {
    }

    protected void N0(View view) {
    }

    protected void O0(DialogInterface dialogInterface, int i) {
    }

    protected void P0(boolean z) {
    }

    protected void Q0(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
    }
}
