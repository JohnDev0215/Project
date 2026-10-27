package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.android.car.settings.R;

/* JADX INFO: loaded from: classes5.dex */
public class LocalRenameDialogFragment extends BluetoothRenameDialogFragment {
    public static final String TAG = "LocalBluetoothRename";
    private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private final IntentFilter mFilter = new IntentFilter("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED");
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.bluetooth.LocalRenameDialogFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LocalRenameDialogFragment.this.updateDeviceName();
        }
    };

    @Override // com.android.car.settings.bluetooth.BluetoothRenameDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        requireContext().registerReceiver(this.mReceiver, this.mFilter);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        requireContext().unregisterReceiver(this.mReceiver);
    }

    @Override // com.android.car.settings.bluetooth.BluetoothRenameDialogFragment
    protected int getDialogTitle() {
        return R.string.bluetooth_rename_vehicle;
    }

    @Override // com.android.car.settings.bluetooth.BluetoothRenameDialogFragment
    protected String getDeviceName() {
        if (this.mBluetoothAdapter.isEnabled()) {
            return this.mBluetoothAdapter.getName();
        }
        return null;
    }

    @Override // com.android.car.settings.bluetooth.BluetoothRenameDialogFragment
    protected void setDeviceName(String deviceName) {
        this.mBluetoothAdapter.setName(deviceName);
    }
}
