package com.android.settingslib;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.preference.EditTextPreference;
import androidx.preference.EditTextPreferenceDialogFragment;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class CustomEditTextPreference extends EditTextPreference {

    /* JADX INFO: compiled from: caradapter-bug */
    public static class CustomPreferenceDialogFragment extends EditTextPreferenceDialogFragment {
        private CustomEditTextPreference j() {
            return (CustomEditTextPreference) a();
        }

        @Override // androidx.preference.EditTextPreferenceDialogFragment, androidx.preference.PreferenceDialogFragment
        protected void c(View view) {
            super.c(view);
            j().N0(view);
        }

        @Override // androidx.preference.EditTextPreferenceDialogFragment, androidx.preference.PreferenceDialogFragment
        public void e(boolean z) {
            super.e(z);
            j().P0(z);
        }

        @Override // androidx.preference.PreferenceDialogFragment
        protected void f(AlertDialog.Builder builder) {
            super.f(builder);
            j().R0(this);
            j().Q0(builder, this);
        }

        @Override // androidx.preference.PreferenceDialogFragment, android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            super.onClick(dialogInterface, i);
            j().O0(dialogInterface, i);
        }
    }

    public CustomEditTextPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R0(CustomPreferenceDialogFragment customPreferenceDialogFragment) {
    }

    @CallSuper
    protected void N0(View view) {
        EditText editText = (EditText) view.findViewById(R.id.edit);
        if (editText != null) {
            editText.setInputType(16385);
            editText.requestFocus();
        }
    }

    protected void O0(DialogInterface dialogInterface, int i) {
    }

    protected void P0(boolean z) {
    }

    protected void Q0(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
    }
}
