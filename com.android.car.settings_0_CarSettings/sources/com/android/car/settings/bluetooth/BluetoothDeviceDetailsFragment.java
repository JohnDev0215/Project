package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.ui.toolbar.MenuItem;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothDeviceDetailsFragment extends SettingsFragment {
    private static final String KEY_DEVICE_ADDRESS = "device_address";
    private CachedBluetoothDevice mCachedDevice;
    private MenuItem mConnectionButton;
    private final CachedBluetoothDevice.Callback mDeviceCallback = new CachedBluetoothDevice.Callback() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothDeviceDetailsFragment$Yav9mYyhhE5womb6pSR1r8wWCm8
        @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
        public final void onDeviceAttributesChanged() {
            this.f$0.updateConnectionButtonState();
        }
    };
    private MenuItem mForgetButton;

    public static BluetoothDeviceDetailsFragment newInstance(CachedBluetoothDevice device) {
        Bundle args = new Bundle();
        args.putString(KEY_DEVICE_ADDRESS, device.getAddress());
        BluetoothDeviceDetailsFragment fragment = new BluetoothDeviceDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Arrays.asList(this.mConnectionButton, this.mForgetButton);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mConnectionButton = new MenuItem.Builder(getContext()).build();
        updateConnectionButtonState();
        this.mForgetButton = new MenuItem.Builder(getContext()).setTitle(R.string.forget).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothDeviceDetailsFragment$LcKDYLArBQoHerg3yTOfl0-oDm0
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$BluetoothDeviceDetailsFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$BluetoothDeviceDetailsFragment(MenuItem i) {
        this.mCachedDevice.unpair();
        goBack();
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.bluetooth_device_details_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        LocalBluetoothManager manager = BluetoothUtils.getLocalBtManager(context);
        if (manager == null) {
            goBack();
            return;
        }
        String deviceAddress = getArguments().getString(KEY_DEVICE_ADDRESS);
        BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress);
        CachedBluetoothDevice cachedBluetoothDeviceFindDevice = manager.getCachedDeviceManager().findDevice(remoteDevice);
        this.mCachedDevice = cachedBluetoothDeviceFindDevice;
        if (cachedBluetoothDeviceFindDevice == null) {
            goBack();
            return;
        }
        ((BluetoothDeviceNamePreferenceController) use(BluetoothDeviceNamePreferenceController.class, R.string.pk_bluetooth_device_name)).setCachedDevice(this.mCachedDevice);
        ((BluetoothDeviceProfilesPreferenceController) use(BluetoothDeviceProfilesPreferenceController.class, R.string.pk_bluetooth_device_profiles)).setCachedDevice(this.mCachedDevice);
        ((BluetoothDeviceAddressPreferenceController) use(BluetoothDeviceAddressPreferenceController.class, R.string.pk_bluetooth_device_address)).setCachedDevice(this.mCachedDevice);
    }

    @Override // com.android.car.settings.common.SettingsFragment, com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mCachedDevice.registerCallback(this.mDeviceCallback);
        updateConnectionButtonState();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mCachedDevice.unregisterCallback(this.mDeviceCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConnectionButtonState() {
        this.mConnectionButton.setEnabled(!this.mCachedDevice.isBusy());
        if (this.mCachedDevice.isConnected()) {
            this.mConnectionButton.setTitle(R.string.disconnect);
            this.mConnectionButton.setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothDeviceDetailsFragment$TFP5Q_8QGhO5VZt4SgBQYoSo8ZU
                @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
                public final void onClick(MenuItem menuItem) {
                    this.f$0.lambda$updateConnectionButtonState$1$BluetoothDeviceDetailsFragment(menuItem);
                }
            });
        } else {
            this.mConnectionButton.setTitle(R.string.connect);
            this.mConnectionButton.setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothDeviceDetailsFragment$2XQ-7hKjlcyK7s-fnG2fEd00BMA
                @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
                public final void onClick(MenuItem menuItem) {
                    this.f$0.lambda$updateConnectionButtonState$2$BluetoothDeviceDetailsFragment(menuItem);
                }
            });
        }
    }

    public /* synthetic */ void lambda$updateConnectionButtonState$1$BluetoothDeviceDetailsFragment(MenuItem view) {
        this.mCachedDevice.disconnect();
    }

    public /* synthetic */ void lambda$updateConnectionButtonState$2$BluetoothDeviceDetailsFragment(MenuItem view) {
        this.mCachedDevice.connect(true);
    }
}
