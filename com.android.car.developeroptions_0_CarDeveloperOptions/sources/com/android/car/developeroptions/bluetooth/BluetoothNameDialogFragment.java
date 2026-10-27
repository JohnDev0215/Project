package com.android.car.developeroptions.bluetooth;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
abstract class BluetoothNameDialogFragment extends InstrumentedDialogFragment implements TextWatcher, TextView.OnEditorActionListener {
    AlertDialog mAlertDialog;
    private boolean mDeviceNameEdited;
    private boolean mDeviceNameUpdated;
    EditText mDeviceNameView;
    private Button mOkButton;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    protected abstract String getDeviceName();

    protected abstract int getDialogTitle();

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    protected abstract void setDeviceName(String str);

    BluetoothNameDialogFragment() {
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        String deviceName = getDeviceName();
        if (bundle != null) {
            deviceName = bundle.getString("device_name", deviceName);
            this.mDeviceNameEdited = bundle.getBoolean("device_name_edited", false);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getDialogTitle());
        builder.setView(createDialogView(deviceName));
        builder.setPositiveButton(R.string.bluetooth_rename_button, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.bluetooth.-$$Lambda$BluetoothNameDialogFragment$JRqjeQukWJAu2q9FowNZL7wjc2I
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreateDialog$0$BluetoothNameDialogFragment(dialogInterface, i);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
        AlertDialog alertDialogCreate = builder.create();
        this.mAlertDialog = alertDialogCreate;
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.car.developeroptions.bluetooth.-$$Lambda$BluetoothNameDialogFragment$Rt6xQqt0JmeOaNsUSSOePhm1q9k
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                this.f$0.lambda$onCreateDialog$1$BluetoothNameDialogFragment(dialogInterface);
            }
        });
        return this.mAlertDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$0$BluetoothNameDialogFragment(DialogInterface dialogInterface, int i) {
        setDeviceName(this.mDeviceNameView.getText().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$1$BluetoothNameDialogFragment(DialogInterface dialogInterface) {
        InputMethodManager inputMethodManager;
        EditText editText = this.mDeviceNameView;
        if (editText == null || !editText.requestFocus() || (inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.showSoftInput(this.mDeviceNameView, 1);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("device_name", this.mDeviceNameView.getText().toString());
        bundle.putBoolean("device_name_edited", this.mDeviceNameEdited);
    }

    private View createDialogView(String str) {
        View viewInflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.dialog_edittext, (ViewGroup) null);
        EditText editText = (EditText) viewInflate.findViewById(R.id.edittext);
        this.mDeviceNameView = editText;
        editText.setFilters(new InputFilter[]{new BluetoothLengthDeviceNameFilter()});
        this.mDeviceNameView.setText(str);
        if (!TextUtils.isEmpty(str)) {
            this.mDeviceNameView.setSelection(str.length());
        }
        this.mDeviceNameView.addTextChangedListener(this);
        com.android.car.developeroptions.Utils.setEditTextCursorPosition(this.mDeviceNameView);
        this.mDeviceNameView.setOnEditorActionListener(this);
        return viewInflate;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        setDeviceName(textView.getText().toString());
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return true;
        }
        this.mAlertDialog.dismiss();
        return true;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mAlertDialog = null;
        this.mDeviceNameView = null;
        this.mOkButton = null;
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mOkButton == null) {
            Button button = this.mAlertDialog.getButton(-1);
            this.mOkButton = button;
            button.setEnabled(this.mDeviceNameEdited);
        }
    }

    void updateDeviceName() {
        String deviceName = getDeviceName();
        if (deviceName != null) {
            this.mDeviceNameUpdated = true;
            this.mDeviceNameEdited = false;
            this.mDeviceNameView.setText(deviceName);
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (this.mDeviceNameUpdated) {
            this.mDeviceNameUpdated = false;
            this.mOkButton.setEnabled(false);
            return;
        }
        this.mDeviceNameEdited = true;
        Button button = this.mOkButton;
        if (button != null) {
            button.setEnabled(editable.toString().trim().length() != 0);
        }
    }
}
