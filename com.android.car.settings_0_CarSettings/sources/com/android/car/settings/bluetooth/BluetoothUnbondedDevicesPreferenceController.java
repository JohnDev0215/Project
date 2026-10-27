package com.android.car.settings.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.bluetooth.BluetoothDeviceFilter;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothUnbondedDevicesPreferenceController extends BluetoothScanningDevicesGroupPreferenceController {
    private static final Logger LOG = new Logger((Class<?>) BluetoothUnbondedDevicesPreferenceController.class);
    private final BluetoothDeviceFilter.Filter mUnbondedDeviceTypeFilter;

    public BluetoothUnbondedDevicesPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUnbondedDeviceTypeFilter = new UnbondedDeviceTypeFilter();
    }

    @Override // com.android.car.settings.bluetooth.BluetoothDevicesGroupPreferenceController
    protected BluetoothDeviceFilter.Filter getDeviceFilter() {
        return this.mUnbondedDeviceTypeFilter;
    }

    @Override // com.android.car.settings.bluetooth.BluetoothScanningDevicesGroupPreferenceController
    protected void onDeviceClickedInternal(CachedBluetoothDevice cachedDevice) {
        if (cachedDevice.startPairing()) {
            LOG.d("startPairing");
            cachedDevice.getDevice().setPhonebookAccessPermission(1);
            cachedDevice.getDevice().setMessageAccessPermission(1);
        } else {
            BluetoothUtils.showError(getContext(), cachedDevice.getName(), R.string.bluetooth_pairing_error_message);
            refreshUi();
        }
    }

    @Override // com.android.car.settings.bluetooth.BluetoothPreferenceController, com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        int availabilityStatus = super.getAvailabilityStatus();
        if (availabilityStatus == 0 && getUserManager().hasUserRestriction("no_config_bluetooth")) {
            return 3;
        }
        return availabilityStatus;
    }

    private class UnbondedDeviceTypeFilter implements BluetoothDeviceFilter.Filter {
        private UnbondedDeviceTypeFilter() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.Filter
        public boolean matches(BluetoothDevice device) {
            int[] unbondedMajorClassFilter = BluetoothUnbondedDevicesPreferenceController.this.getContext().getResources().getIntArray(R.array.config_unbonded_device_filter_whitelist);
            boolean matches = device.getBondState() != 12;
            if (matches && unbondedMajorClassFilter.length > 0) {
                return device.getBluetoothClass() != null && ArrayUtils.contains(unbondedMajorClassFilter, device.getBluetoothClass().getMajorDeviceClass());
            }
            return matches;
        }
    }
}
