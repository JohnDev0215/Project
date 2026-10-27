package com.android.car.developeroptions.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class LocalDeviceNameDialogFragment extends BluetoothNameDialogFragment {
    private BluetoothAdapter mBluetoothAdapter;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.bluetooth.LocalDeviceNameDialogFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED".equals(action) || ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action) && intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE) == 12)) {
                LocalDeviceNameDialogFragment.this.updateDeviceName();
            }
        }
    };

    @Override // com.android.car.developeroptions.bluetooth.BluetoothNameDialogFragment
    protected int getDialogTitle() {
        return R.string.bluetooth_rename_device;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 538;
    }

    public static LocalDeviceNameDialogFragment newInstance() {
        return new LocalDeviceNameDialogFragment();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothNameDialogFragment, com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED");
        getActivity().registerReceiver(this.mReceiver, intentFilter);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(this.mReceiver);
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothNameDialogFragment
    protected String getDeviceName() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            return null;
        }
        return this.mBluetoothAdapter.getName();
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothNameDialogFragment
    protected void setDeviceName(String str) {
        this.mBluetoothAdapter.setName(str);
    }
}
