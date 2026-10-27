package com.ts.car.sdk.dm;

import com.ts.car.sdk.dm.data.DmDevice;
import ts.car.service.dm.data.ConnectRequest;
import ts.car.service.dm.data.UnifiedDevice;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    private static final String TAG = "Utils";

    public static UnifiedDevice convertToUnifiedDevice(DmDevice device) {
        UnifiedDevice result = new UnifiedDevice();
        result.setDeviceUuid(device.getDeviceUuid());
        if (device.getIndexOfSameDeviceName() > 0) {
            result.setFriendlyName(device.getFriendlyName() + " " + device.getIndexOfSameDeviceName());
        } else {
            result.setFriendlyName(device.getFriendlyName());
        }
        result.setCarPlayProjectionState(convertDmCapabilityState(device.getCarPlayProjectionState()));
        result.setCarlifeProjectionState(convertDmCapabilityState(device.getCarlifeProjectionState()));
        result.setAndroidAutoProjectionState(convertDmCapabilityState(device.getAndroidAutoProjectionState()));
        result.setHicarProjectionState(convertDmCapabilityState(device.getHicarProjectionState()));
        result.setHfpState(convertDmCapabilityState(device.getHfpState()));
        result.setA2dpState(convertDmCapabilityState(device.getA2dpState()));
        result.setPaired(device.isPaired());
        result.setLastProfile(convertDmConstantsCapability(device.getLastProfile()));
        result.setLastProfileTimeMillis(device.getLastProfileTimeMillis());
        return result;
    }

    public static DmDevice convertToDmdDevice(UnifiedDevice device) {
        DmDevice result = new DmDevice(device.getDeviceUuid());
        result.setFriendlyName(device.getFriendlyName());
        result.setCarPlayProjectionState(convertDmCapabilityState(device.getCarPlayProjectionState()));
        result.setCarlifeProjectionState(convertDmCapabilityState(device.getCarlifeProjectionState()));
        result.setAndroidAutoProjectionState(convertDmCapabilityState(device.getAndroidAutoProjectionState()));
        result.setHicarProjectionState(convertDmCapabilityState(device.getHicarProjectionState()));
        result.setHfpState(convertDmCapabilityState(device.getHfpState()));
        result.setA2dpState(convertDmCapabilityState(device.getA2dpState()));
        result.setPaired(device.isPaired());
        result.setLastProfile(convertDmConstantsCapability(device.getLastProfile()));
        result.setLastProfileTimeMillis(device.getLastProfileTimeMillis());
        return result;
    }

    public static int convertCommonConstantsCapability(int capability) {
        switch (capability) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            default:
                return 0;
        }
    }

    public static int convertDmConstantsCapability(int capability) {
        switch (capability) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            default:
                return 0;
        }
    }

    private static int convertDmCapabilityState(int state) {
        if (state != 1) {
            if (state == 2) {
                return 1;
            }
            if (state == 3) {
                return 3;
            }
            if (state != 4) {
                return state != 5 ? 0 : 5;
            }
            return 4;
        }
        return 2;
    }

    public static int convertConnectionType(int connectionType) {
        if (connectionType != 1) {
            return connectionType != 2 ? 0 : 2;
        }
        return 1;
    }

    public static ConnectRequest convertConnectionRquest(com.ts.car.sdk.dm.data.ConnectRequest request) {
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setConnectType(convertConnectionType(request.getConnectionType()));
        connectRequest.setCapabilityType(convertDmConstantsCapability(request.getCapabilityType()));
        connectRequest.setDevice(convertToUnifiedDevice(request.getDevice()));
        return connectRequest;
    }
}
