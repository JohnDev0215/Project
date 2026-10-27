package com.android.car.ui.preference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import com.android.car.settings.common.PreferenceXmlParser;

/* JADX INFO: loaded from: classes6.dex */
public class SeekbarPreferenceDialogFragment extends PreferenceDialogFragment {
    public static SeekbarPreferenceDialogFragment newInstance(String key) {
        SeekbarPreferenceDialogFragment fragment = new SeekbarPreferenceDialogFragment();
        Bundle b = new Bundle(1);
        b.putString(PreferenceXmlParser.METADATA_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        DialogFragmentCallbacks dialogPreference = (DialogFragmentCallbacks) getPreference();
        dialogPreference.onBindDialogView(view);
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        super.onClick(dialog, which);
        DialogFragmentCallbacks dialogPreference = (DialogFragmentCallbacks) getPreference();
        dialogPreference.onClick(dialog, which);
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onDialogClosed(boolean positiveResult) {
        DialogFragmentCallbacks dialogPreference = (DialogFragmentCallbacks) getPreference();
        dialogPreference.onDialogClosed(positiveResult);
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        DialogFragmentCallbacks dialogPreference = (DialogFragmentCallbacks) getPreference();
        dialogPreference.onPrepareDialogBuilder(builder);
    }
}
