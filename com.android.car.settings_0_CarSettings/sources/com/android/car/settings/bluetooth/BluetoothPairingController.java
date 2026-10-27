package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.widget.CompoundButton;
import com.android.car.settings.R;
import com.android.car.settings.bluetooth.BluetoothPairingDialogFragment;
import com.android.car.settings.common.Logger;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfile;
import java.util.Locale;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothPairingController implements CompoundButton.OnCheckedChangeListener, BluetoothPairingDialogFragment.BluetoothPairingDialogListener {
    private static final int BLUETOOTH_PASSKEY_MAX_LENGTH = 6;
    private static final int BLUETOOTH_PIN_MAX_LENGTH = 16;
    public static final int CONFIRMATION_DIALOG = 1;
    public static final int DISPLAY_PASSKEY_DIALOG = 2;
    public static final int INVALID_DIALOG_TYPE = -1;
    private static final Logger LOG = new Logger((Class<?>) BluetoothPairingController.class);
    public static final int USER_ENTRY_DIALOG = 0;
    private LocalBluetoothManager mBluetoothManager;
    private BluetoothDevice mDevice;
    private String mDeviceName;
    private int mPasskey;
    private String mPasskeyFormatted;
    private LocalBluetoothProfile mPbapClientProfile;
    private int mType;
    private String mUserInput;

    public BluetoothPairingController(Intent intent, Context context) {
        this.mBluetoothManager = BluetoothUtils.getLocalBtManager(context);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        this.mDevice = bluetoothDevice;
        if (this.mBluetoothManager == null) {
            throw new IllegalStateException("Could not obtain LocalBluetoothManager");
        }
        if (bluetoothDevice == null) {
            throw new IllegalStateException("Could not find BluetoothDevice");
        }
        this.mType = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
        this.mPasskey = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", Integer.MIN_VALUE);
        this.mDeviceName = this.mBluetoothManager.getCachedDeviceManager().getName(this.mDevice);
        this.mPbapClientProfile = this.mBluetoothManager.getProfileManager().getPbapClientProfile();
        this.mPasskeyFormatted = formatKey(this.mPasskey);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            this.mDevice.setPhonebookAccessPermission(1);
        } else {
            this.mDevice.setPhonebookAccessPermission(2);
        }
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPairingDialogFragment.BluetoothPairingDialogListener
    public void onDialogPositiveClick(BluetoothPairingDialogFragment dialog) {
        if (getDialogType() == 0) {
            onPair(this.mUserInput);
        } else {
            onPair(null);
        }
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPairingDialogFragment.BluetoothPairingDialogListener
    public void onDialogNegativeClick(BluetoothPairingDialogFragment dialog) {
        onCancel();
    }

    public int getDialogType() {
        switch (this.mType) {
            case 0:
            case 1:
            case 7:
                return 0;
            case 2:
            case 3:
            case 6:
                return 1;
            case 4:
            case 5:
                return 2;
            default:
                return -1;
        }
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public boolean isProfileReady() {
        LocalBluetoothProfile localBluetoothProfile = this.mPbapClientProfile;
        return localBluetoothProfile != null && localBluetoothProfile.isProfileReady();
    }

    public boolean getContactSharingState() {
        int phonebookAccessPermission = this.mDevice.getPhonebookAccessPermission();
        if (phonebookAccessPermission != 1) {
            return phonebookAccessPermission != 2 && this.mDevice.getBluetoothClass().getDeviceClass() == 1032;
        }
        return true;
    }

    public boolean isPasskeyValid(Editable s) {
        boolean requires16Digits = this.mType == 7;
        if (s.length() < 16 || !requires16Digits) {
            return s.length() > 0 && !requires16Digits;
        }
        return true;
    }

    public int getDeviceVariantMessageId() {
        int i = this.mType;
        if (i != 0) {
            if (i == 1) {
                return R.string.bluetooth_enter_passkey_other_device;
            }
            if (i != 7) {
                return -1;
            }
        }
        return R.string.bluetooth_enter_pin_other_device;
    }

    public int getDeviceVariantMessageHintId() {
        int i = this.mType;
        if (i == 0 || i == 1) {
            return R.string.bluetooth_pin_values_hint;
        }
        if (i == 7) {
            return R.string.bluetooth_pin_values_hint_16_digits;
        }
        return -1;
    }

    public int getDeviceMaxPasskeyLength() {
        int i = this.mType;
        if (i == 0) {
            return 16;
        }
        if (i == 1) {
            return 6;
        }
        if (i == 7) {
            return 16;
        }
        return 0;
    }

    public boolean pairingCodeIsAlphanumeric() {
        return this.mType != 1;
    }

    protected void notifyDialogDisplayed() {
        int i = this.mType;
        if (i == 4) {
            this.mDevice.setPairingConfirmation(true);
        } else if (i == 5) {
            this.mDevice.setPin(this.mPasskeyFormatted);
        }
    }

    public boolean isDisplayPairingKeyVariant() {
        int i = this.mType;
        if (i == 4 || i == 5 || i == 6) {
            return true;
        }
        return false;
    }

    public boolean hasPairingContent() {
        int i = this.mType;
        if (i == 2 || i == 4 || i == 5) {
            return true;
        }
        return false;
    }

    public String getPairingContent() {
        if (hasPairingContent()) {
            return this.mPasskeyFormatted;
        }
        return null;
    }

    protected void updateUserInput(String input) {
        this.mUserInput = input;
    }

    private String formatKey(int passkey) {
        int i = this.mType;
        if (i == 2 || i == 4) {
            return String.format(Locale.US, "%06d", Integer.valueOf(passkey));
        }
        if (i != 5) {
            return null;
        }
        return String.format("%04d", Integer.valueOf(passkey));
    }

    private void onPair(String passkey) {
        LOG.d("Pairing dialog accepted");
        switch (this.mType) {
            case 0:
            case 7:
                this.mDevice.setPin(passkey);
                break;
            case 1:
                Integer.parseInt(passkey);
                break;
            case 2:
            case 3:
                this.mDevice.setPairingConfirmation(true);
                break;
            case 4:
            case 5:
            case 6:
                break;
            default:
                LOG.e("Incorrect pairing type received");
                break;
        }
    }

    public void onCancel() {
        LOG.d("Pairing dialog canceled");
        this.mDevice.cancelPairing();
    }

    public boolean deviceEquals(BluetoothDevice device) {
        return this.mDevice == device;
    }
}
