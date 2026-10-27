package com.android.car.settings.common;

import android.R;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/* JADX INFO: loaded from: classes5.dex */
public class PasswordEditTextPreferenceDialogFragment extends ValidatedEditTextPreferenceDialogFragment {
    private EditText mEditText;

    public static PasswordEditTextPreferenceDialogFragment newInstance(String key) {
        PasswordEditTextPreferenceDialogFragment fragment = new PasswordEditTextPreferenceDialogFragment();
        Bundle b = new Bundle(1);
        b.putString(PreferenceXmlParser.METADATA_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    @Override // com.android.car.settings.common.ValidatedEditTextPreferenceDialogFragment, com.android.car.ui.preference.EditTextPreferenceDialogFragment, com.android.car.ui.preference.CarUiDialogFragment
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        EditText editText = (EditText) view.findViewById(R.id.edit);
        this.mEditText = editText;
        editText.setInputType(129);
        CheckBox cb = (CheckBox) view.findViewById(com.android.car.settings.R.id.checkbox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.car.settings.common.PasswordEditTextPreferenceDialogFragment.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PasswordEditTextPreferenceDialogFragment.this.mEditText.setInputType(145);
                } else {
                    PasswordEditTextPreferenceDialogFragment.this.mEditText.setInputType(129);
                }
                PasswordEditTextPreferenceDialogFragment.this.mEditText.setSelection(PasswordEditTextPreferenceDialogFragment.this.mEditText.getText().length());
            }
        });
    }
}
