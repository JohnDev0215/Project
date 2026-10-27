package com.android.car.developeroptions.bluetooth;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPairingDialogFragment extends InstrumentedDialogFragment implements TextWatcher, DialogInterface.OnClickListener {
    private AlertDialog.Builder mBuilder;
    private AlertDialog mDialog;
    private BluetoothPairingController mPairingController;
    private BluetoothPairingDialog mPairingDialogActivity;
    private EditText mPairingView;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 613;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        if (!isPairingControllerSet()) {
            throw new IllegalStateException("Must call setPairingController() before showing dialog");
        }
        if (!isPairingDialogActivitySet()) {
            throw new IllegalStateException("Must call setPairingDialogActivity() before showing dialog");
        }
        this.mBuilder = new AlertDialog.Builder(getActivity());
        AlertDialog alertDialog = setupDialog();
        this.mDialog = alertDialog;
        alertDialog.setCanceledOnTouchOutside(false);
        return this.mDialog;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        Button button = this.mDialog.getButton(-1);
        if (button != null) {
            button.setEnabled(this.mPairingController.isPasskeyValid(editable));
        }
        this.mPairingController.updateUserInput(editable.toString());
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            this.mPairingController.onDialogPositiveClick(this);
        } else if (i == -2) {
            this.mPairingController.onDialogNegativeClick(this);
        }
        this.mPairingDialogActivity.dismiss();
    }

    void setPairingController(BluetoothPairingController bluetoothPairingController) {
        if (isPairingControllerSet()) {
            throw new IllegalStateException("The controller can only be set once. Forcibly replacing it will lead to undefined behavior");
        }
        this.mPairingController = bluetoothPairingController;
    }

    boolean isPairingControllerSet() {
        return this.mPairingController != null;
    }

    void setPairingDialogActivity(BluetoothPairingDialog bluetoothPairingDialog) {
        if (isPairingDialogActivitySet()) {
            throw new IllegalStateException("The pairing dialog activity can only be set once");
        }
        this.mPairingDialogActivity = bluetoothPairingDialog;
    }

    boolean isPairingDialogActivitySet() {
        return this.mPairingDialogActivity != null;
    }

    private AlertDialog setupDialog() {
        int dialogType = this.mPairingController.getDialogType();
        if (dialogType == 0) {
            return createUserEntryDialog();
        }
        if (dialogType == 1) {
            return createConsentDialog();
        }
        if (dialogType == 2) {
            return createDisplayPasskeyOrPinDialog();
        }
        Log.e("BTPairingDialogFragment", "Incorrect pairing type received, not showing any dialog");
        return null;
    }

    CharSequence getPairingViewText() {
        EditText editText = this.mPairingView;
        if (editText != null) {
            return editText.getText();
        }
        return null;
    }

    private AlertDialog createUserEntryDialog() {
        this.mBuilder.setTitle(getString(R.string.bluetooth_pairing_request, this.mPairingController.getDeviceName()));
        this.mBuilder.setView(createPinEntryView());
        this.mBuilder.setPositiveButton(getString(android.R.string.ok), this);
        this.mBuilder.setNegativeButton(getString(android.R.string.cancel), this);
        AlertDialog alertDialogCreate = this.mBuilder.create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.car.developeroptions.bluetooth.-$$Lambda$BluetoothPairingDialogFragment$UFUp9x15EJAlkLqBiB1SnSO8c54
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                this.f$0.lambda$createUserEntryDialog$0$BluetoothPairingDialogFragment(dialogInterface);
            }
        });
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createUserEntryDialog$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createUserEntryDialog$0$BluetoothPairingDialogFragment(DialogInterface dialogInterface) {
        InputMethodManager inputMethodManager;
        if (TextUtils.isEmpty(getPairingViewText())) {
            this.mDialog.getButton(-1).setEnabled(false);
        }
        EditText editText = this.mPairingView;
        if (editText == null || !editText.requestFocus() || (inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.showSoftInput(this.mPairingView, 1);
    }

    private View createPinEntryView() {
        View viewInflate = getActivity().getLayoutInflater().inflate(R.layout.bluetooth_pin_entry, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.pin_values_hint);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.message_below_pin);
        CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.alphanumeric_pin);
        CheckBox checkBox2 = (CheckBox) viewInflate.findViewById(R.id.phonebook_sharing_message_entry_pin);
        checkBox2.setText(getString(R.string.bluetooth_pairing_shares_phonebook, this.mPairingController.getDeviceName()));
        EditText editText = (EditText) viewInflate.findViewById(R.id.text);
        checkBox2.setVisibility(this.mPairingController.isProfileReady() ? 8 : 0);
        this.mPairingController.setContactSharingState();
        checkBox2.setOnCheckedChangeListener(this.mPairingController);
        checkBox2.setChecked(this.mPairingController.getContactSharingState());
        this.mPairingView = editText;
        editText.setInputType(2);
        editText.addTextChangedListener(this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.car.developeroptions.bluetooth.-$$Lambda$BluetoothPairingDialogFragment$xeUIh5kLb3J8qvvhTwojtY8joPs
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f$0.lambda$createPinEntryView$1$BluetoothPairingDialogFragment(compoundButton, z);
            }
        });
        int deviceVariantMessageId = this.mPairingController.getDeviceVariantMessageId();
        int deviceVariantMessageHintId = this.mPairingController.getDeviceVariantMessageHintId();
        int deviceMaxPasskeyLength = this.mPairingController.getDeviceMaxPasskeyLength();
        checkBox.setVisibility(this.mPairingController.pairingCodeIsAlphanumeric() ? 0 : 8);
        if (deviceVariantMessageId != -1) {
            textView2.setText(deviceVariantMessageId);
        } else {
            textView2.setVisibility(8);
        }
        if (deviceVariantMessageHintId != -1) {
            textView.setText(deviceVariantMessageHintId);
        } else {
            textView.setVisibility(8);
        }
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(deviceMaxPasskeyLength)});
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createPinEntryView$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createPinEntryView$1$BluetoothPairingDialogFragment(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.mPairingView.setInputType(1);
        } else {
            this.mPairingView.setInputType(2);
        }
    }

    private AlertDialog createConfirmationDialog() {
        this.mBuilder.setTitle(getString(R.string.bluetooth_pairing_request, this.mPairingController.getDeviceName()));
        this.mBuilder.setView(createView());
        this.mBuilder.setPositiveButton(getString(R.string.bluetooth_pairing_accept), this);
        this.mBuilder.setNegativeButton(getString(R.string.bluetooth_pairing_decline), this);
        return this.mBuilder.create();
    }

    private AlertDialog createConsentDialog() {
        return createConfirmationDialog();
    }

    private AlertDialog createDisplayPasskeyOrPinDialog() {
        this.mBuilder.setTitle(getString(R.string.bluetooth_pairing_request, this.mPairingController.getDeviceName()));
        this.mBuilder.setView(createView());
        this.mBuilder.setNegativeButton(getString(android.R.string.cancel), this);
        AlertDialog alertDialogCreate = this.mBuilder.create();
        this.mPairingController.notifyDialogDisplayed();
        return alertDialogCreate;
    }

    private View createView() {
        View viewInflate = getActivity().getLayoutInflater().inflate(R.layout.bluetooth_pin_confirm, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.pairing_caption);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.pairing_subhead);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.pairing_code_message);
        CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.phonebook_sharing_message_confirm_pin);
        checkBox.setText(getString(R.string.bluetooth_pairing_shares_phonebook, this.mPairingController.getDeviceName()));
        checkBox.setVisibility(this.mPairingController.isProfileReady() ? 8 : 0);
        this.mPairingController.setContactSharingState();
        checkBox.setChecked(this.mPairingController.getContactSharingState());
        checkBox.setOnCheckedChangeListener(this.mPairingController);
        textView3.setVisibility(this.mPairingController.isDisplayPairingKeyVariant() ? 0 : 8);
        if (this.mPairingController.hasPairingContent()) {
            textView.setVisibility(0);
            textView2.setVisibility(0);
            textView2.setText(this.mPairingController.getPairingContent());
        }
        return viewInflate;
    }
}
