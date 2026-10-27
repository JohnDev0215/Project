package com.android.car.settings.bluetooth;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.car.settings.R;
import com.android.car.ui.AlertDialogBuilder;
import com.android.car.ui.preference.CarUiDialogFragment;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothDisconnectConfirmDialogFragment extends CarUiDialogFragment {
    private static final String KEY_DEVICE_ADDRESS = "device_address";
    private CachedBluetoothDevice mCachedDevice;
    private final CachedBluetoothDevice.Callback mDeviceCallback = new CachedBluetoothDevice.Callback() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothDisconnectConfirmDialogFragment$k-1ZjVSk0-O8gYq5_DmLXMs3t4k
        @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
        public final void onDeviceAttributesChanged() {
            this.f$0.dismissIfNotConnected();
        }
    };

    public static BluetoothDisconnectConfirmDialogFragment newInstance(CachedBluetoothDevice device) {
        Bundle args = new Bundle();
        args.putString(KEY_DEVICE_ADDRESS, device.getAddress());
        BluetoothDisconnectConfirmDialogFragment fragment = new BluetoothDisconnectConfirmDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        String deviceAddress = getArguments().getString(KEY_DEVICE_ADDRESS);
        LocalBluetoothManager manager = BluetoothUtils.getLocalBtManager(context);
        BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress);
        this.mCachedDevice = manager.getCachedDeviceManager().findDevice(device);
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment, androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = requireContext();
        String name = this.mCachedDevice.getName();
        if (TextUtils.isEmpty(name)) {
            name = context.getString(R.string.bluetooth_device);
        }
        String title = context.getString(R.string.bluetooth_disconnect_title);
        String message = context.getString(R.string.bluetooth_disconnect_all_profiles, name);
        return new AlertDialogBuilder(context).setTitle(title).setMessage(message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothDisconnectConfirmDialogFragment$I3EJee31YZWgKUYMBgDbC7Yfe-0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreateDialog$0$BluetoothDisconnectConfirmDialogFragment(dialogInterface, i);
            }
        }).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).create();
    }

    public /* synthetic */ void lambda$onCreateDialog$0$BluetoothDisconnectConfirmDialogFragment(DialogInterface dialog, int which) {
        this.mCachedDevice.disconnect();
    }

    @Override // com.android.car.ui.preference.CarUiDialogFragment
    protected void onDialogClosed(boolean positiveResult) {
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mCachedDevice.registerCallback(this.mDeviceCallback);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mCachedDevice.unregisterCallback(this.mDeviceCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissIfNotConnected() {
        if (!this.mCachedDevice.isConnected() && getDialog().isShowing()) {
            dismiss();
        }
    }
}
