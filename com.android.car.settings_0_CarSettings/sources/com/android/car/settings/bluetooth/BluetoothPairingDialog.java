package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothPairingDialog extends FragmentActivity {
    public static final String FRAGMENT_TAG = "bluetooth.pairing.fragment";
    private BluetoothPairingController mBluetoothPairingController;
    private boolean mReceiverRegistered = false;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.bluetooth.BluetoothPairingDialog.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(action)) {
                int bondState = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                if (bondState == 12 || bondState == 10) {
                    BluetoothPairingDialog.this.dismiss();
                    return;
                }
                return;
            }
            if ("android.bluetooth.device.action.PAIRING_CANCEL".equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (device == null || BluetoothPairingDialog.this.mBluetoothPairingController.deviceEquals(device)) {
                    BluetoothPairingDialog.this.dismiss();
                }
            }
        }
    };

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addSystemFlags(524288);
        Intent intent = getIntent();
        this.mBluetoothPairingController = new BluetoothPairingController(intent, this);
        boolean fragmentFound = true;
        BluetoothPairingDialogFragment bluetoothFragment = (BluetoothPairingDialogFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (bluetoothFragment != null && (bluetoothFragment.isPairingControllerSet() || bluetoothFragment.isPairingDialogActivitySet())) {
            bluetoothFragment.dismiss();
            bluetoothFragment = null;
        }
        if (bluetoothFragment == null) {
            fragmentFound = false;
            bluetoothFragment = new BluetoothPairingDialogFragment();
        }
        bluetoothFragment.setPairingController(this.mBluetoothPairingController);
        bluetoothFragment.setPairingDialogActivity(this);
        if (!fragmentFound) {
            bluetoothFragment.show(getSupportFragmentManager(), FRAGMENT_TAG);
        }
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.device.action.PAIRING_CANCEL"));
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.device.action.BOND_STATE_CHANGED"));
        this.mReceiverRegistered = true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mReceiverRegistered) {
            this.mReceiverRegistered = false;
            unregisterReceiver(this.mReceiver);
        }
    }

    void dismiss() {
        if (!isFinishing()) {
            finish();
        }
    }
}
