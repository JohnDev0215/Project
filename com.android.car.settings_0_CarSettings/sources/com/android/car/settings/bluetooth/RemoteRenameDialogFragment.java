package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import com.android.car.settings.R;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* JADX INFO: loaded from: classes5.dex */
public class RemoteRenameDialogFragment extends BluetoothRenameDialogFragment {
    private static final String KEY_CACHED_DEVICE_ADDRESS = "cached_device";
    public static final String TAG = "RemoteDeviceBluetoothRename";
    private CachedBluetoothDevice mCachedDevice;

    public static RemoteRenameDialogFragment newInstance(CachedBluetoothDevice device) {
        Bundle args = new Bundle(1);
        args.putString(KEY_CACHED_DEVICE_ADDRESS, device.getAddress());
        RemoteRenameDialogFragment fragment = new RemoteRenameDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        String deviceAddress = getArguments().getString(KEY_CACHED_DEVICE_ADDRESS);
        LocalBluetoothManager manager = BluetoothUtils.getLocalBtManager(context);
        BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress);
        this.mCachedDevice = manager.getCachedDeviceManager().findDevice(device);
    }

    @Override // com.android.car.settings.bluetooth.BluetoothRenameDialogFragment
    protected int getDialogTitle() {
        return R.string.bluetooth_rename_device;
    }

    @Override // com.android.car.settings.bluetooth.BluetoothRenameDialogFragment
    protected String getDeviceName() {
        CachedBluetoothDevice cachedBluetoothDevice = this.mCachedDevice;
        if (cachedBluetoothDevice != null) {
            return cachedBluetoothDevice.getName();
        }
        return null;
    }

    @Override // com.android.car.settings.bluetooth.BluetoothRenameDialogFragment
    protected void setDeviceName(String deviceName) {
        CachedBluetoothDevice cachedBluetoothDevice = this.mCachedDevice;
        if (cachedBluetoothDevice != null) {
            cachedBluetoothDevice.setName(deviceName);
        }
    }
}
