package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.os.ParcelUuid;
import android.util.Log;
import com.android.internal.util.ArrayUtils;

/* JADX INFO: loaded from: classes3.dex */
public final class BluetoothDeviceFilter {
    public static final Filter ALL_FILTER;
    public static final Filter BONDED_DEVICE_FILTER;
    private static final Filter[] FILTERS;
    private static final String TAG = "BluetoothDeviceFilter";
    public static final Filter UNBONDED_DEVICE_FILTER;

    public interface Filter {
        boolean matches(BluetoothDevice bluetoothDevice);
    }

    static {
        ALL_FILTER = new AllFilter();
        BONDED_DEVICE_FILTER = new BondedDeviceFilter();
        UNBONDED_DEVICE_FILTER = new UnbondedDeviceFilter();
        FILTERS = new Filter[]{ALL_FILTER, new AudioFilter(), new TransferFilter(), new PanuFilter(), new NapFilter()};
    }

    private BluetoothDeviceFilter() {
    }

    public static Filter getFilter(int filterType) {
        if (filterType >= 0) {
            Filter[] filterArr = FILTERS;
            if (filterType < filterArr.length) {
                return filterArr[filterType];
            }
        }
        Log.w(TAG, "Invalid filter type " + filterType + " for device picker");
        return ALL_FILTER;
    }

    private static final class AllFilter implements Filter {
        private AllFilter() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.Filter
        public boolean matches(BluetoothDevice device) {
            return true;
        }
    }

    private static final class BondedDeviceFilter implements Filter {
        private BondedDeviceFilter() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.Filter
        public boolean matches(BluetoothDevice device) {
            return device.getBondState() == 12;
        }
    }

    private static final class UnbondedDeviceFilter implements Filter {
        private UnbondedDeviceFilter() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.Filter
        public boolean matches(BluetoothDevice device) {
            return device.getBondState() != 12;
        }
    }

    private static abstract class ClassUuidFilter implements Filter {
        abstract boolean matches(ParcelUuid[] parcelUuidArr, BluetoothClass bluetoothClass);

        private ClassUuidFilter() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.Filter
        public boolean matches(BluetoothDevice device) {
            return matches(device.getUuids(), device.getBluetoothClass());
        }
    }

    private static final class AudioFilter extends ClassUuidFilter {
        private AudioFilter() {
            super();
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.ClassUuidFilter
        boolean matches(ParcelUuid[] uuids, BluetoothClass btClass) {
            if (uuids != null) {
                if (BluetoothUuid.containsAnyUuid(uuids, A2dpProfile.SINK_UUIDS) || BluetoothUuid.containsAnyUuid(uuids, HeadsetProfile.UUIDS)) {
                    return true;
                }
            } else if (btClass != null && (btClass.doesClassMatch(1) || btClass.doesClassMatch(0))) {
                return true;
            }
            return false;
        }
    }

    private static final class TransferFilter extends ClassUuidFilter {
        private TransferFilter() {
            super();
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.ClassUuidFilter
        boolean matches(ParcelUuid[] uuids, BluetoothClass btClass) {
            if (uuids == null || !ArrayUtils.contains(uuids, BluetoothUuid.OBEX_OBJECT_PUSH)) {
                return btClass != null && btClass.doesClassMatch(2);
            }
            return true;
        }
    }

    private static final class PanuFilter extends ClassUuidFilter {
        private PanuFilter() {
            super();
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.ClassUuidFilter
        boolean matches(ParcelUuid[] uuids, BluetoothClass btClass) {
            if (uuids == null || !ArrayUtils.contains(uuids, BluetoothUuid.PANU)) {
                return btClass != null && btClass.doesClassMatch(4);
            }
            return true;
        }
    }

    private static final class NapFilter extends ClassUuidFilter {
        private NapFilter() {
            super();
        }

        @Override // com.android.settingslib.bluetooth.BluetoothDeviceFilter.ClassUuidFilter
        boolean matches(ParcelUuid[] uuids, BluetoothClass btClass) {
            if (uuids == null || !ArrayUtils.contains(uuids, BluetoothUuid.NAP)) {
                return btClass != null && btClass.doesClassMatch(5);
            }
            return true;
        }
    }
}
