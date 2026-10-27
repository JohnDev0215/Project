package com.android.car.developeroptions.bluetooth;

import android.content.Context;
import android.os.Bundle;
import com.android.car.developeroptions.R;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* JADX INFO: loaded from: classes.dex */
public class RemoteDeviceNameDialogFragment extends BluetoothNameDialogFragment {
    private CachedBluetoothDevice mDevice;

    @Override // com.android.car.developeroptions.bluetooth.BluetoothNameDialogFragment
    protected int getDialogTitle() {
        return R.string.bluetooth_device_name;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1015;
    }

    public static RemoteDeviceNameDialogFragment newInstance(CachedBluetoothDevice cachedBluetoothDevice) {
        Bundle bundle = new Bundle(1);
        bundle.putString("cached_device", cachedBluetoothDevice.getDevice().getAddress());
        RemoteDeviceNameDialogFragment remoteDeviceNameDialogFragment = new RemoteDeviceNameDialogFragment();
        remoteDeviceNameDialogFragment.setArguments(bundle);
        return remoteDeviceNameDialogFragment;
    }

    CachedBluetoothDevice getDevice(Context context) {
        String string = getArguments().getString("cached_device");
        LocalBluetoothManager localBtManager = Utils.getLocalBtManager(context);
        return localBtManager.getCachedDeviceManager().findDevice(localBtManager.getBluetoothAdapter().getRemoteDevice(string));
    }

    @Override // com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment, com.android.settingslib.core.lifecycle.ObservableDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mDevice = getDevice(context);
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothNameDialogFragment
    protected String getDeviceName() {
        CachedBluetoothDevice cachedBluetoothDevice = this.mDevice;
        if (cachedBluetoothDevice != null) {
            return cachedBluetoothDevice.getName();
        }
        return null;
    }

    @Override // com.android.car.developeroptions.bluetooth.BluetoothNameDialogFragment
    protected void setDeviceName(String str) {
        CachedBluetoothDevice cachedBluetoothDevice = this.mDevice;
        if (cachedBluetoothDevice != null) {
            cachedBluetoothDevice.setName(str);
        }
    }
}
