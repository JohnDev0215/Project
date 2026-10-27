package com.android.car.developeroptions;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import com.android.settingslib.CustomEditTextPreferenceCompat;

/* JADX INFO: loaded from: classes.dex */
class EditPinPreference extends CustomEditTextPreferenceCompat {
    private OnPinEnteredListener mPinListener;

    interface OnPinEnteredListener {
        void onPinEntered(EditPinPreference editPinPreference, boolean z);
    }

    public EditPinPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public EditPinPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnPinEnteredListener(OnPinEnteredListener onPinEnteredListener) {
        this.mPinListener = onPinEnteredListener;
    }

    @Override // com.android.settingslib.CustomEditTextPreferenceCompat
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        EditText editText = (EditText) view.findViewById(android.R.id.edit);
        if (editText != null) {
            editText.setInputType(18);
            editText.setTextAlignment(5);
        }
    }

    public boolean isDialogOpen() {
        Dialog dialog = getDialog();
        return dialog != null && dialog.isShowing();
    }

    @Override // com.android.settingslib.CustomEditTextPreferenceCompat
    protected void onDialogClosed(boolean z) {
        super.onDialogClosed(z);
        OnPinEnteredListener onPinEnteredListener = this.mPinListener;
        if (onPinEnteredListener != null) {
            onPinEnteredListener.onPinEntered(this, z);
        }
    }

    public void showPinDialog() {
        Dialog dialog = getDialog();
        if (dialog == null || !dialog.isShowing()) {
            onClick();
        }
    }
}
