package com.android.car.ui.preference;

import android.R;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import spi.b2;

/* JADX INFO: loaded from: classes2.dex */
public class EditTextPreferenceDialogFragment extends PreferenceDialogFragment implements TextView.OnEditorActionListener {
    public EditText j;
    public CharSequence k;
    public boolean l = true;

    public final EditTextPreference getEditTextPreference() {
        return (EditTextPreference) getPreference();
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    public final void onBindDialogView(@NonNull View view) {
        super.onBindDialogView(view);
        EditText editText = (EditText) b2.d(view, R.id.edit);
        this.j = editText;
        if (editText == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        }
        editText.requestFocus();
        this.j.setText(this.k);
        this.j.setInputType(1);
        this.j.setImeOptions(6);
        this.j.setOnEditorActionListener(this);
        EditText editText2 = this.j;
        editText2.setSelection(editText2.getText().length());
    }

    @Override // com.android.car.ui.preference.PreferenceDialogFragment, com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.k = getEditTextPreference().getText();
        } else {
            this.k = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
        }
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    public final void onDialogClosed(boolean z) {
        if (z) {
            String string = this.j.getText().toString();
            if (getEditTextPreference().callChangeListener(string)) {
                getEditTextPreference().setText(string);
            }
        }
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6 || !this.l) {
            return false;
        }
        getEditTextPreference().callChangeListener(textView.getText());
        dismiss();
        return true;
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", this.k);
    }
}
