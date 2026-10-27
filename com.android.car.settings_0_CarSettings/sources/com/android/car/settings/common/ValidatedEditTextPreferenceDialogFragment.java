package com.android.car.settings.common;

import android.R;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.android.car.settings.common.ValidatedEditTextPreference;
import com.android.car.ui.preference.EditTextPreferenceDialogFragment;

/* JADX INFO: loaded from: classes5.dex */
public class ValidatedEditTextPreferenceDialogFragment extends EditTextPreferenceDialogFragment implements TextView.OnEditorActionListener {
    private EditText mEditText;
    private final EditTextWatcher mTextWatcher = new EditTextWatcher();
    private ValidatedEditTextPreference.Validator mValidator;

    public static ValidatedEditTextPreferenceDialogFragment newInstance(String key) {
        ValidatedEditTextPreferenceDialogFragment fragment = new ValidatedEditTextPreferenceDialogFragment();
        Bundle b = new Bundle(1);
        b.putString(PreferenceXmlParser.METADATA_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    @Override // com.android.car.ui.preference.EditTextPreferenceDialogFragment, com.android.car.ui.preference.CarUiDialogFragment
    protected void onBindDialogView(View view) {
        ValidatedEditTextPreference.Validator validator;
        super.onBindDialogView(view);
        this.mEditText = (EditText) view.findViewById(R.id.edit);
        if ((getPreference() instanceof ValidatedEditTextPreference) && (validator = ((ValidatedEditTextPreference) getPreference()).getValidator()) != null) {
            attachValidatorToView(view, validator);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        allowDialogSubmissionOnlyIfValidInput((AlertDialog) getDialog());
    }

    private void attachValidatorToView(View view, ValidatedEditTextPreference.Validator validator) {
        this.mValidator = validator;
        EditText editText = (EditText) view.findViewById(R.id.edit);
        if (this.mValidator != null && editText != null) {
            editText.removeTextChangedListener(this.mTextWatcher);
            editText.addTextChangedListener(this.mTextWatcher);
        }
    }

    private class EditTextWatcher implements TextWatcher {
        private EditTextWatcher() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            ValidatedEditTextPreferenceDialogFragment validatedEditTextPreferenceDialogFragment = ValidatedEditTextPreferenceDialogFragment.this;
            validatedEditTextPreferenceDialogFragment.allowDialogSubmissionOnlyIfValidInput((AlertDialog) validatedEditTextPreferenceDialogFragment.getDialog());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void allowDialogSubmissionOnlyIfValidInput(AlertDialog dialog) {
        ValidatedEditTextPreference.Validator validator;
        EditText editText;
        if (dialog != null && (validator = this.mValidator) != null && (editText = this.mEditText) != null) {
            boolean valid = validator.isTextValid(editText.getText().toString());
            dialog.getButton(-1).setEnabled(valid);
            setAllowEnterToSubmit(valid);
        }
    }
}
