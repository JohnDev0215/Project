package com.android.car.ui.preference;

import android.R;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.preference.EditTextPreference;
import com.android.car.ui.utils.CarUiUtils;

/* JADX INFO: loaded from: classes7.dex */
public class EditTextPreferenceDialogFragment extends PreferenceDialogFragment implements TextView.OnEditorActionListener {
    private static final String SAVE_STATE_TEXT = "EditTextPreferenceDialogFragment.text";
    private boolean mAllowEnterToSubmit = true;
    private EditText mEditText;
    private CharSequence mText;

    public static EditTextPreferenceDialogFragment newInstance(String key) {
        EditTextPreferenceDialogFragment fragment = new EditTextPreferenceDialogFragment();
        Bundle b = new Bundle(1);
        b.putString("key", key);
        fragment.setArguments(b);
        return fragment;
    }

    @Override // com.android.car.ui.preference.PreferenceDialogFragment, com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.mText = getEditTextPreference().getText();
        } else {
            this.mText = savedInstanceState.getCharSequence(SAVE_STATE_TEXT);
        }
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(SAVE_STATE_TEXT, this.mText);
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        EditText editText = (EditText) CarUiUtils.findViewByRefId(view, R.id.edit);
        this.mEditText = editText;
        if (editText == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        }
        editText.requestFocus();
        this.mEditText.setText(this.mText);
        this.mEditText.setInputType(1);
        this.mEditText.setImeOptions(6);
        this.mEditText.setOnEditorActionListener(this);
        EditText editText2 = this.mEditText;
        editText2.setSelection(editText2.getText().length());
    }

    private EditTextPreference getEditTextPreference() {
        return (EditTextPreference) getPreference();
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected boolean needInputMethod() {
        return true;
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            String value = this.mEditText.getText().toString();
            if (getEditTextPreference().callChangeListener(value)) {
                getEditTextPreference().setText(value);
            }
        }
    }

    public void setAllowEnterToSubmit(boolean isAllowed) {
        this.mAllowEnterToSubmit = isAllowed;
    }

    public boolean getAllowEnterToSubmit() {
        return this.mAllowEnterToSubmit;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == 6 && this.mAllowEnterToSubmit) {
            CharSequence newValue = v.getText();
            getEditTextPreference().callChangeListener(newValue);
            dismiss();
            return true;
        }
        return false;
    }
}
