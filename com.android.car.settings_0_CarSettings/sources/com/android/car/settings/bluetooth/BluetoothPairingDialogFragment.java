package com.android.car.settings.bluetooth;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.car.ui.preference.CarUiDialogFragment;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothPairingDialogFragment extends CarUiDialogFragment implements TextWatcher, DialogInterface.OnClickListener {
    private static final Logger LOG = new Logger((Class<?>) BluetoothPairingDialogFragment.class);
    private AlertDialog.Builder mBuilder;
    private AlertDialog mDialog;
    private BluetoothPairingController mPairingController;
    private BluetoothPairingDialog mPairingDialogActivity;
    private EditText mPairingView;

    public interface BluetoothPairingDialogListener {
        void onDialogNegativeClick(BluetoothPairingDialogFragment bluetoothPairingDialogFragment);

        void onDialogPositiveClick(BluetoothPairingDialogFragment bluetoothPairingDialogFragment);
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
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

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onDialogClosed(boolean positiveResult) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
        Button positiveButton = this.mDialog.getButton(-1);
        if (positiveButton != null) {
            positiveButton.setEnabled(this.mPairingController.isPasskeyValid(s));
        }
        this.mPairingController.updateUserInput(s.toString());
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        if (which == -1) {
            this.mPairingController.onDialogPositiveClick(this);
        } else if (which == -2) {
            this.mPairingController.onDialogNegativeClick(this);
        }
        this.mPairingDialogActivity.dismiss();
    }

    protected AlertDialog getmDialog() {
        return this.mDialog;
    }

    void setPairingController(BluetoothPairingController pairingController) {
        if (isPairingControllerSet()) {
            throw new IllegalStateException("The controller can only be set once. Forcibly replacing it will lead to undefined behavior");
        }
        this.mPairingController = pairingController;
    }

    boolean isPairingControllerSet() {
        return this.mPairingController != null;
    }

    void setPairingDialogActivity(BluetoothPairingDialog pairingDialogActivity) {
        if (isPairingDialogActivitySet()) {
            throw new IllegalStateException("The pairing dialog activity can only be set once");
        }
        this.mPairingDialogActivity = pairingDialogActivity;
    }

    boolean isPairingDialogActivitySet() {
        return this.mPairingDialogActivity != null;
    }

    private AlertDialog setupDialog() {
        int dialogType = this.mPairingController.getDialogType();
        if (dialogType == 0) {
            AlertDialog dialog = createUserEntryDialog();
            return dialog;
        }
        if (dialogType == 1) {
            AlertDialog dialog2 = createConsentDialog();
            return dialog2;
        }
        if (dialogType == 2) {
            AlertDialog dialog3 = createDisplayPasskeyOrPinDialog();
            return dialog3;
        }
        LOG.e("Incorrect pairing type received, not showing any dialog");
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
        AlertDialog dialog = this.mBuilder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothPairingDialogFragment$IgYl40smG31jWyI0dzXGeiOHf2A
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                this.f$0.lambda$createUserEntryDialog$0$BluetoothPairingDialogFragment(dialogInterface);
            }
        });
        return dialog;
    }

    public /* synthetic */ void lambda$createUserEntryDialog$0$BluetoothPairingDialogFragment(DialogInterface d) {
        InputMethodManager imm;
        if (TextUtils.isEmpty(getPairingViewText())) {
            this.mDialog.getButton(-1).setEnabled(false);
        }
        EditText editText = this.mPairingView;
        if (editText != null && editText.requestFocus() && (imm = (InputMethodManager) getContext().getSystemService("input_method")) != null) {
            imm.showSoftInput(this.mPairingView, 1);
        }
    }

    private View createPinEntryView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.bluetooth_pin_entry, (ViewGroup) null);
        TextView messageViewCaptionHint = (TextView) view.findViewById(R.id.pin_values_hint);
        TextView messageView2 = (TextView) view.findViewById(R.id.message_below_pin);
        CheckBox alphanumericPin = (CheckBox) view.findViewById(R.id.alphanumeric_pin);
        CheckBox contactSharing = (CheckBox) view.findViewById(R.id.phonebook_sharing_message_entry_pin);
        contactSharing.setText(getString(R.string.bluetooth_pairing_shares_phonebook, this.mPairingController.getDeviceName()));
        EditText pairingView = (EditText) view.findViewById(R.id.text);
        contactSharing.setVisibility(this.mPairingController.isProfileReady() ? 8 : 0);
        contactSharing.setOnCheckedChangeListener(this.mPairingController);
        contactSharing.setChecked(this.mPairingController.getContactSharingState());
        this.mPairingView = pairingView;
        pairingView.setInputType(2);
        pairingView.addTextChangedListener(this);
        alphanumericPin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothPairingDialogFragment$XSuJ8LqPXybegRXYRFMsqa5SJyI
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f$0.lambda$createPinEntryView$1$BluetoothPairingDialogFragment(compoundButton, z);
            }
        });
        int messageId = this.mPairingController.getDeviceVariantMessageId();
        int messageIdHint = this.mPairingController.getDeviceVariantMessageHintId();
        int maxLength = this.mPairingController.getDeviceMaxPasskeyLength();
        alphanumericPin.setVisibility(this.mPairingController.pairingCodeIsAlphanumeric() ? 0 : 8);
        if (messageId != -1) {
            messageView2.setText(messageId);
        } else {
            messageView2.setVisibility(8);
        }
        if (messageIdHint != -1) {
            messageViewCaptionHint.setText(messageIdHint);
        } else {
            messageViewCaptionHint.setVisibility(8);
        }
        pairingView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        return view;
    }

    public /* synthetic */ void lambda$createPinEntryView$1$BluetoothPairingDialogFragment(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
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
        AlertDialog dialog = this.mBuilder.create();
        return dialog;
    }

    private AlertDialog createConsentDialog() {
        return createConfirmationDialog();
    }

    private AlertDialog createDisplayPasskeyOrPinDialog() {
        this.mBuilder.setTitle(getString(R.string.bluetooth_pairing_request, this.mPairingController.getDeviceName()));
        this.mBuilder.setView(createView());
        this.mBuilder.setNegativeButton(getString(android.R.string.cancel), this);
        AlertDialog dialog = this.mBuilder.create();
        this.mPairingController.notifyDialogDisplayed();
        return dialog;
    }

    private View createView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.bluetooth_pin_confirm, (ViewGroup) null);
        TextView pairingViewCaption = (TextView) view.findViewById(R.id.pairing_caption);
        TextView pairingViewContent = (TextView) view.findViewById(R.id.pairing_subhead);
        TextView messagePairing = (TextView) view.findViewById(R.id.pairing_code_message);
        View contactSharingContainer = view.findViewById(R.id.phonebook_sharing_message_confirm_pin_container);
        TextView contactSharingText = (TextView) view.findViewById(R.id.phonebook_sharing_message_confirm_pin_text);
        CheckBox contactSharing = (CheckBox) view.findViewById(R.id.phonebook_sharing_message_confirm_pin);
        contactSharingText.setText(getString(R.string.bluetooth_pairing_shares_phonebook, this.mPairingController.getDeviceName()));
        contactSharingContainer.setVisibility(this.mPairingController.isProfileReady() ? 8 : 0);
        contactSharing.setChecked(this.mPairingController.getContactSharingState());
        contactSharing.setOnCheckedChangeListener(this.mPairingController);
        messagePairing.setVisibility(this.mPairingController.isDisplayPairingKeyVariant() ? 0 : 8);
        if (this.mPairingController.hasPairingContent()) {
            pairingViewCaption.setVisibility(0);
            pairingViewContent.setVisibility(0);
            pairingViewContent.setText(this.mPairingController.getPairingContent());
        }
        return view;
    }
}
