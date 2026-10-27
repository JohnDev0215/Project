package android.companion;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanFilter;
import android.net.wifi.ScanResult;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDeviceFilterUtils {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "BluetoothDeviceFilterUtils";

    private BluetoothDeviceFilterUtils() {
    }

    static String patternToString(Pattern pattern) {
        if (pattern == null) {
            return null;
        }
        return pattern.pattern();
    }

    static Pattern patternFromString(String str) {
        if (str == null) {
            return null;
        }
        return Pattern.compile(str);
    }

    static boolean matchesAddress(String str, BluetoothDevice bluetoothDevice) {
        return str == null || (bluetoothDevice != null && str.equals(bluetoothDevice.getAddress()));
    }

    static boolean matchesServiceUuids(List<ParcelUuid> list, List<ParcelUuid> list2, BluetoothDevice bluetoothDevice) {
        for (int i = 0; i < list.size(); i++) {
            if (!matchesServiceUuid(list.get(i), list2.get(i), bluetoothDevice)) {
                return false;
            }
        }
        return true;
    }

    static boolean matchesServiceUuid(ParcelUuid parcelUuid, ParcelUuid parcelUuid2, BluetoothDevice bluetoothDevice) {
        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if (parcelUuid != null) {
            if (!ScanFilter.matchesServiceUuids(parcelUuid, parcelUuid2, uuids == null ? Collections.emptyList() : Arrays.asList(uuids))) {
                return false;
            }
        }
        return true;
    }

    static boolean matchesName(Pattern pattern, BluetoothDevice bluetoothDevice) {
        String name;
        return pattern == null || !(bluetoothDevice == null || (name = bluetoothDevice.getName()) == null || !pattern.matcher(name).find());
    }

    static boolean matchesName(Pattern pattern, ScanResult scanResult) {
        String str;
        return pattern == null || !(scanResult == null || (str = scanResult.SSID) == null || !pattern.matcher(str).find());
    }

    private static void debugLogMatchResult(boolean z, BluetoothDevice bluetoothDevice, Object obj) {
        Log.i(LOG_TAG, getDeviceDisplayNameInternal(bluetoothDevice) + (z ? " ~ " : " !~ ") + obj);
    }

    private static void debugLogMatchResult(boolean z, ScanResult scanResult, Object obj) {
        Log.i(LOG_TAG, getDeviceDisplayNameInternal(scanResult) + (z ? " ~ " : " !~ ") + obj);
    }

    public static String getDeviceDisplayNameInternal(BluetoothDevice bluetoothDevice) {
        return TextUtils.firstNotEmpty(bluetoothDevice.getAlias(), bluetoothDevice.getAddress());
    }

    public static String getDeviceDisplayNameInternal(ScanResult scanResult) {
        return TextUtils.firstNotEmpty(scanResult.SSID, scanResult.BSSID);
    }

    public static String getDeviceMacAddress(Parcelable parcelable) {
        if (parcelable instanceof BluetoothDevice) {
            return ((BluetoothDevice) parcelable).getAddress();
        }
        if (parcelable instanceof ScanResult) {
            return ((ScanResult) parcelable).BSSID;
        }
        if (parcelable instanceof android.bluetooth.le.ScanResult) {
            return getDeviceMacAddress(((android.bluetooth.le.ScanResult) parcelable).getDevice());
        }
        throw new IllegalArgumentException("Unknown device type: " + parcelable);
    }
}
