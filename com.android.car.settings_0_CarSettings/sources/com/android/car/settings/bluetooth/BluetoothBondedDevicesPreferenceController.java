package com.android.car.settings.bluetooth;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.common.CarUxRestrictionsHelper;
import com.android.car.settings.common.FragmentController;
import com.android.settingslib.bluetooth.BluetoothDeviceFilter;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothBondedDevicesPreferenceController extends BluetoothDevicesGroupPreferenceController {
    public BluetoothBondedDevicesPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.bluetooth.BluetoothDevicesGroupPreferenceController
    protected BluetoothDeviceFilter.Filter getDeviceFilter() {
        return BluetoothDeviceFilter.BONDED_DEVICE_FILTER;
    }

    @Override // com.android.car.settings.bluetooth.BluetoothDevicesGroupPreferenceController
    protected BluetoothDevicePreference createDevicePreference(final CachedBluetoothDevice cachedDevice) {
        BluetoothDevicePreference pref = super.createDevicePreference(cachedDevice);
        pref.setSecondaryActionIcon(R.drawable.ic_settings_gear);
        pref.setOnSecondaryActionClickListener(new Runnable() { // from class: com.android.car.settings.bluetooth.-$$Lambda$BluetoothBondedDevicesPreferenceController$7h83gjbvj5I7sL-maQChYIgksco
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$createDevicePreference$0$BluetoothBondedDevicesPreferenceController(cachedDevice);
            }
        });
        return pref;
    }

    public /* synthetic */ void lambda$createDevicePreference$0$BluetoothBondedDevicesPreferenceController(CachedBluetoothDevice cachedDevice) {
        getFragmentController().launchFragment(BluetoothDeviceDetailsFragment.newInstance(cachedDevice));
    }

    @Override // com.android.car.settings.bluetooth.BluetoothDevicesGroupPreferenceController
    protected void onDeviceClicked(CachedBluetoothDevice cachedDevice) {
        if (cachedDevice.isConnected()) {
            getFragmentController().showDialog(BluetoothDisconnectConfirmDialogFragment.newInstance(cachedDevice), null);
        } else {
            cachedDevice.connect(true);
        }
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.settingslib.bluetooth.BluetoothCallback
    public void onDeviceBondStateChanged(CachedBluetoothDevice cachedDevice, int bondState) {
        refreshUi();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.bluetooth.BluetoothDevicesGroupPreferenceController, com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        super.updateState(preferenceGroup);
        boolean hasUserRestriction = getUserManager().hasUserRestriction("no_config_bluetooth");
        updateActionVisibility(preferenceGroup, !hasUserRestriction);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.settings.common.PreferenceController
    protected void onApplyUxRestrictions(CarUxRestrictions uxRestrictions) {
        super.onApplyUxRestrictions(uxRestrictions);
        if (CarUxRestrictionsHelper.isNoSetup(uxRestrictions)) {
            updateActionVisibility((PreferenceGroup) getPreference(), false);
        }
    }

    private void updateActionVisibility(PreferenceGroup group, boolean isActionVisible) {
        for (int i = 0; i < group.getPreferenceCount(); i++) {
            ((BluetoothDevicePreference) group.getPreference(i)).setSecondaryActionVisible(isActionVisible);
        }
    }
}
