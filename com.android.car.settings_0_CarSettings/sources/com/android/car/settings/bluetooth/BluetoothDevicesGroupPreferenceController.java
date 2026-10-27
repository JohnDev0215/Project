package com.android.car.settings.bluetooth;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.common.FragmentController;
import com.android.settingslib.bluetooth.BluetoothDeviceFilter;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BluetoothDevicesGroupPreferenceController extends BluetoothPreferenceController<PreferenceGroup> {
    private final Preference.OnPreferenceClickListener mDevicePreferenceClickListener;
    private final Map<CachedBluetoothDevice, BluetoothDevicePreference> mPreferenceMap;

    protected abstract BluetoothDeviceFilter.Filter getDeviceFilter();

    protected abstract void onDeviceClicked(CachedBluetoothDevice cachedBluetoothDevice);

    public /* synthetic */ boolean lambda$new$0$BluetoothDevicesGroupPreferenceController(Preference preference) {
        onDeviceClicked(((BluetoothDevicePreference) preference).getCachedDevice());
        return true;
    }

    public BluetoothDevicesGroupPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mPreferenceMap = new HashMap();
        this.mDevicePreferenceClickListener = new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothDevicesGroupPreferenceController$2xpM_SJAP_EshlcE8T3sVhGeDLA
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$new$0$BluetoothDevicesGroupPreferenceController(preference);
            }
        };
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    protected BluetoothDevicePreference createDevicePreference(CachedBluetoothDevice cachedDevice) {
        return new BluetoothDevicePreference(getContext(), cachedDevice);
    }

    protected Map<CachedBluetoothDevice, BluetoothDevicePreference> getPreferenceMap() {
        return this.mPreferenceMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        Collection<?> cachedDevices = getBluetoothManager().getCachedDeviceManager().getCachedDevicesCopy();
        Set<CachedBluetoothDevice> devicesToRemove = new HashSet<>(this.mPreferenceMap.keySet());
        devicesToRemove.removeAll(cachedDevices);
        for (CachedBluetoothDevice deviceToRemove : devicesToRemove) {
            removePreference(deviceToRemove);
        }
        for (CachedBluetoothDevice cachedDevice : cachedDevices) {
            if (getDeviceFilter().matches(cachedDevice.getDevice())) {
                addPreference(cachedDevice);
            } else {
                removePreference(cachedDevice);
            }
        }
        preferenceGroup.setVisible(preferenceGroup.getPreferenceCount() > 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public final void onBluetoothStateChanged(int bluetoothState) {
        super.onBluetoothStateChanged(bluetoothState);
        if (bluetoothState == 13) {
            ((PreferenceGroup) getPreference()).removeAll();
            this.mPreferenceMap.clear();
        }
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public final void onDeviceAdded(CachedBluetoothDevice cachedDevice) {
        refreshUi();
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public final void onDeviceDeleted(CachedBluetoothDevice cachedDevice) {
        refreshUi();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void addPreference(CachedBluetoothDevice cachedDevice) {
        if (!this.mPreferenceMap.containsKey(cachedDevice)) {
            BluetoothDevicePreference devicePreference = createDevicePreference(cachedDevice);
            devicePreference.setOnPreferenceClickListener(this.mDevicePreferenceClickListener);
            this.mPreferenceMap.put(cachedDevice, devicePreference);
            ((PreferenceGroup) getPreference()).addPreference(devicePreference);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void removePreference(CachedBluetoothDevice cachedDevice) {
        if (this.mPreferenceMap.containsKey(cachedDevice)) {
            ((PreferenceGroup) getPreference()).removePreference(this.mPreferenceMap.get(cachedDevice));
            this.mPreferenceMap.remove(cachedDevice);
        }
    }
}
