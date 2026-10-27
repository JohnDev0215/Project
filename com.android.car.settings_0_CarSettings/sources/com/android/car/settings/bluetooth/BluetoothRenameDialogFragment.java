package com.android.car.settings.bluetooth;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.car.settings.R;
import com.android.car.ui.AlertDialogBuilder;
import com.android.car.ui.preference.CarUiDialogFragment;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BluetoothRenameDialogFragment extends CarUiDialogFragment implements TextWatcher, TextView.OnEditorActionListener {
    private static final int BLUETOOTH_NAME_MAX_LENGTH_BYTES = 248;
    private static final String KEY_NAME = "device_name";
    private AlertDialog mAlertDialog;
    private String mDeviceName;

    protected abstract String getDeviceName();

    protected abstract int getDialogTitle();

    protected abstract void setDeviceName(String str);

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String deviceName = getDeviceName();
        if (savedInstanceState != null) {
            deviceName = savedInstanceState.getString(KEY_NAME, deviceName);
        }
        createAlertDialog(deviceName);
        return this.mAlertDialog;
    }

    private void createAlertDialog(String deviceName) {
        InputFilter[] inputFilters = {new Utf8ByteLengthFilter(BLUETOOTH_NAME_MAX_LENGTH_BYTES)};
        AlertDialogBuilder builder = new AlertDialogBuilder(requireActivity()).setTitle(getDialogTitle()).setPositiveButton(R.string.bluetooth_rename_button, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRenameDialogFragment$qqg5KLqan2nwXVPZCFbABXKY3E0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$createAlertDialog$0$BluetoothRenameDialogFragment(dialogInterface, i);
            }
        }).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).setEditBox(deviceName, this, inputFilters, 1);
        AlertDialog alertDialogCreate = builder.create();
        this.mAlertDialog = alertDialogCreate;
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothRenameDialogFragment$FSOGjuxR69rEtKCuc9lbzdg9J5Y
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                this.f$0.lambda$createAlertDialog$1$BluetoothRenameDialogFragment(dialogInterface);
            }
        });
    }

    public /* synthetic */ void lambda$createAlertDialog$0$BluetoothRenameDialogFragment(DialogInterface dialog, int which) {
        setDeviceName(getEditedName());
    }

    public /* synthetic */ void lambda$createAlertDialog$1$BluetoothRenameDialogFragment(DialogInterface d) {
        InputMethodManager imm;
        EditText deviceNameView = getDeviceNameView();
        String str = this.mDeviceName;
        if (str != null) {
            deviceNameView.setSelection(str.length());
        }
        deviceNameView.setOnEditorActionListener(this);
        deviceNameView.setImeOptions(6);
        if (deviceNameView.requestFocus() && (imm = (InputMethodManager) requireContext().getSystemService("input_method")) != null) {
            imm.showSoftInput(deviceNameView, 1);
        }
        refreshRenameButton();
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onDialogClosed(boolean positiveResult) {
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_NAME, getEditedName());
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        refreshRenameButton();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mAlertDialog = null;
    }

    protected void updateDeviceName() {
        String deviceName = getDeviceName();
        if (deviceName != null) {
            this.mDeviceName = deviceName;
            EditText deviceNameView = getDeviceNameView();
            if (deviceNameView != null) {
                deviceNameView.setText(deviceName);
            }
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.mDeviceName = s.toString();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
        refreshRenameButton();
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId != 6) {
            return false;
        }
        String editedName = getEditedName();
        if (TextUtils.isEmpty(editedName)) {
            return false;
        }
        setDeviceName(editedName);
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.mAlertDialog.dismiss();
            return true;
        }
        return true;
    }

    private void refreshRenameButton() {
        Button renameButton;
        AlertDialog alertDialog = this.mAlertDialog;
        if (alertDialog != null && (renameButton = alertDialog.getButton(-1)) != null) {
            String editedName = getEditedName();
            renameButton.setEnabled((TextUtils.isEmpty(editedName) || Objects.equals(editedName, getDeviceName())) ? false : true);
        }
    }

    private String getEditedName() {
        return this.mDeviceName.trim();
    }

    private EditText getDeviceNameView() {
        return (EditText) this.mAlertDialog.getWindow().findViewById(R.id.textbox);
    }
}
