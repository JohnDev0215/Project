package com.ts.bluetooth.profile;

import com.ts.bluetooth.profile.Device;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class MyBluetoothClass {
    public static final int ALLOCATE = 4;
    public static final int ERROR = -16777216;
    public static final int[] PROFILES;
    public static final int PROFILE_A2DP = 1;
    public static final int PROFILE_A2DP_1 = 2;
    public static final int PROFILE_A2DP_SINK = 6;
    public static final int PROFILE_AVRCP = 3;
    public static final int PROFILE_HEADSET = 0;
    public static final int PROFILE_HFP = 0;
    public static final int PROFILE_HID = 3;
    public static final int PROFILE_MAP = 4;
    public static final int PROFILE_NAP = 5;
    public static final int PROFILE_OPP = 2;
    public static final int PROFILE_PAN = 5;
    public static final int PROFILE_PANU = 4;
    public static final int PROFILE_PBAP = 1;
    public static final Map<Integer, Integer> PROFILE_TO_SDK;
    private final int mClass;

    public MyBluetoothClass(int classInt) {
        this.mClass = classInt;
    }

    public boolean equals(Object o) {
        return (o instanceof MyBluetoothClass) && this.mClass == ((MyBluetoothClass) o).mClass;
    }

    public int hashCode() {
        return this.mClass;
    }

    public String toString() {
        return Integer.toHexString(this.mClass);
    }

    public boolean hasService(int service) {
        return ((this.mClass & Service.BITMASK) & service) != 0;
    }

    public int getMajorDeviceClass() {
        return this.mClass & 7936;
    }

    public int getDeviceClass() {
        return this.mClass & 8188;
    }

    public int getClassOfDevice() {
        return this.mClass;
    }

    public byte[] getClassOfDeviceBytes() {
        byte[] bytes = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN).putInt(this.mClass).array();
        return Arrays.copyOfRange(bytes, 1, bytes.length);
    }

    static {
        HashMap map = new HashMap(2);
        PROFILE_TO_SDK = map;
        map.put(0, 0);
        PROFILE_TO_SDK.put(1, 2);
        PROFILES = new int[]{0, 1, 2, 3, 4, 5, 6};
    }

    public boolean doesClassMatch(int profile) {
        int deviceClass;
        int deviceClass2;
        int deviceClass3;
        if (profile == 1) {
            return hasService(262144) || (deviceClass3 = getDeviceClass()) == 1044 || deviceClass3 == 1048 || deviceClass3 == 1056 || deviceClass3 == 1064;
        }
        if (profile == 6) {
            return hasService(524288) || (deviceClass2 = getDeviceClass()) == 1060 || deviceClass2 == 1064 || deviceClass2 == 1068;
        }
        if (profile == 0) {
            return hasService(262144) || (deviceClass = getDeviceClass()) == 1028 || deviceClass == 1032 || deviceClass == 1056;
        }
        if (profile == 2) {
            if (hasService(1048576)) {
                return true;
            }
            switch (getDeviceClass()) {
                case 256:
                case Device.COMPUTER_DESKTOP /* 260 */:
                case Device.COMPUTER_SERVER /* 264 */:
                case Device.COMPUTER_LAPTOP /* 268 */:
                case Device.COMPUTER_HANDHELD_PC_PDA /* 272 */:
                case Device.COMPUTER_PALM_SIZE_PC_PDA /* 276 */:
                case Device.COMPUTER_WEARABLE /* 280 */:
                case 512:
                case Device.PHONE_CELLULAR /* 516 */:
                case Device.PHONE_CORDLESS /* 520 */:
                case Device.PHONE_SMART /* 524 */:
                case Device.PHONE_MODEM_OR_GATEWAY /* 528 */:
                case Device.PHONE_ISDN /* 532 */:
                    return true;
                default:
                    return false;
            }
        }
        if (profile == 3) {
            return (getDeviceClass() & 1280) == 1280;
        }
        if (profile == 4 || profile == 5) {
            return hasService(131072) || (getDeviceClass() & Device.Major.NETWORKING) == 768;
        }
        return false;
    }
}
