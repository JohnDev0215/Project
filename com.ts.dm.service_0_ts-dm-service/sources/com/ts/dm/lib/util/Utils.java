package com.ts.dm.lib.util;

import android.text.TextUtils;
import android.util.SparseIntArray;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.data.DeviceRecord;
import com.ts.dm.lib.devicemanager.counter.ILastProfileTimeCounter;
import ts.car.dm.fsdk.interfaces.BluetoothDevice;
import ts.car.dm.fsdk.interfaces.ProjectionDevice;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    private static final String TAG = "Utils";

    public static boolean safeEquals(String s1, String s2) {
        if (s1 == null) {
            return false;
        }
        return s1.equals(s2);
    }

    public static DmDevice createDmDevice(DeviceRecord record) {
        DmDevice result = new DmDevice(record.getMacAddress());
        result.setFriendlyName(record.getFriendlyName());
        result.setMacAddress(record.getMacAddress());
        result.setPaired(record.isPaired());
        if (record.isPaired()) {
            result.setHfpState(2);
            result.setA2dpState(2);
        } else {
            result.setHfpState(0);
            result.setA2dpState(0);
        }
        result.setLastProfile(record.getLastProfile());
        result.setLastProfileTimeMillis(record.getLastProfileTimeMillis());
        result.setAndroidAutoProjectionState(record.getAndroidAutoProjectionState());
        result.setCarlifeProjectionState(record.getCarlifeProjectionState());
        result.setCarPlayProjectionState(record.getCarPlayProjectionState());
        result.setHicarProjectionState(record.getHicarProjectionState());
        result.setIndexOfSameDeviceName(record.getIndexOfSameDeviceName());
        return result;
    }

    public static DmDevice createDmDevice(ProjectionDevice device, int type, ILastProfileTimeCounter lastProfileTimeCounter) {
        DmDevice result = new DmDevice(device.getDeviceId());
        result.setFriendlyName(device.getDeviceName());
        result.setMacAddress(device.getMacAddress());
        result.setUsbSerialNumber(device.getUsbSerialNumber());
        result.setPaired(false);
        result.setLastProfileTimeMillis(lastProfileTimeCounter.generateLastProfileTime());
        return result;
    }

    public static DmDevice createDmDevice(BluetoothDevice bluetoothDevice, ILastProfileTimeCounter lastProfileTimeCounter) {
        DmDevice result = new DmDevice(bluetoothDevice.getMacAddress());
        result.setLastProfileTimeMillis(lastProfileTimeCounter.generateLastProfileTime());
        updateDmDeviceBondStatus(result, bluetoothDevice, 2, lastProfileTimeCounter);
        return result;
    }

    public static boolean updateDmDeviceBondStatus(DmDevice dmDevice, BluetoothDevice bluetoothDevice, int bondStatus, ILastProfileTimeCounter lastProfileTimeCounter) {
        boolean changed = updateDmDeviceInfo(dmDevice, bluetoothDevice);
        if (bondStatus == 2) {
            if (!dmDevice.isPaired()) {
                dmDevice.setPaired(true);
                changed = true;
            }
            if (updateBluetoothProfileStatus(dmDevice, bluetoothDevice, lastProfileTimeCounter)) {
                return true;
            }
            return changed;
        }
        if (dmDevice.isPaired()) {
            dmDevice.setPaired(false);
            changed = true;
        }
        if (dmDevice.getHfpState() != 0) {
            dmDevice.setHfpState(0);
            changed = true;
        }
        if (dmDevice.getA2dpState() != 0) {
            dmDevice.setA2dpState(0);
            return true;
        }
        return changed;
    }

    private static boolean updateDmDeviceInfo(DmDevice dmDevice, BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        boolean changed = false;
        if (!TextUtils.isEmpty(bluetoothDevice.getMacAddress()) && !safeEquals(dmDevice.getMacAddress(), bluetoothDevice.getMacAddress())) {
            dmDevice.setMacAddress(bluetoothDevice.getMacAddress());
            changed = true;
        }
        if (!TextUtils.isEmpty(bluetoothDevice.getName()) && !safeEquals(dmDevice.getFriendlyName(), bluetoothDevice.getName())) {
            dmDevice.setFriendlyName(bluetoothDevice.getName());
            return true;
        }
        return changed;
    }

    public static boolean updateBluetoothProfileStatus(DmDevice dmDevice, BluetoothDevice bluetoothDevice, ILastProfileTimeCounter lastProfileTimeCounter) {
        SparseIntArray profiles;
        boolean changed = updateDmDeviceInfo(dmDevice, bluetoothDevice);
        if (dmDevice.isPaired() && (profiles = bluetoothDevice.getProfiles()) != null && profiles.size() > 0) {
            for (int i = 0; i < profiles.size(); i++) {
                int profile = profiles.keyAt(i);
                int newState = convertBluetoothDeviceStatus(profiles.get(profile));
                if (profile == 1) {
                    if (newState != dmDevice.getHfpState()) {
                        dmDevice.setHfpState(newState);
                        if (newState == 4) {
                            dmDevice.setLastProfileTimeMillis(lastProfileTimeCounter.generateLastProfileTime());
                        }
                        changed = true;
                    }
                } else if (profile == 2 && newState != dmDevice.getA2dpState()) {
                    dmDevice.setA2dpState(newState);
                    if (newState == 4) {
                        dmDevice.setLastProfileTimeMillis(lastProfileTimeCounter.generateLastProfileTime());
                    }
                    changed = true;
                }
            }
        }
        return changed;
    }

    public static int convertBluetoothDeviceStatus(int status) {
        if (status == 2) {
            return 5;
        }
        if (status != 3) {
            return status != 4 ? 2 : 4;
        }
        return 3;
    }

    public static int convertProjectionDeviceStatus(int status) {
        if (status != 0) {
            if (status == 1) {
                return 2;
            }
            if (status == 2) {
                return 5;
            }
            if (status != 3) {
                return status != 4 ? 0 : 4;
            }
            return 3;
        }
        return 1;
    }

    public static boolean isSameDevice(BluetoothDevice bluetoothDevice, DmDevice dmDevice) {
        if (bluetoothDevice == null || dmDevice == null) {
            return false;
        }
        return safeEquals(bluetoothDevice.getMacAddress(), dmDevice.getMacAddress());
    }

    public static boolean isSameDevice(ProjectionDevice projectionDevice, DmDevice dmDevice) {
        if (projectionDevice == null || dmDevice == null) {
            return false;
        }
        return safeEquals(dmDevice.getDeviceUuid(), projectionDevice.getMacAddress()) || safeEquals(dmDevice.getDeviceUuid(), projectionDevice.getUsbSerialNumber()) || safeEquals(dmDevice.getMacAddress(), projectionDevice.getMacAddress()) || safeEquals(dmDevice.getUsbSerialNumber(), projectionDevice.getUsbSerialNumber());
    }
}
