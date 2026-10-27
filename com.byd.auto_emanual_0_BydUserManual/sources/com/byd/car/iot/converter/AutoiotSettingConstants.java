package com.byd.car.iot.converter;

import android.util.Pair;
import com.byd.foundation.utils.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class AutoiotSettingConstants {

    public static class Attr {
        public static final String AUTOIOT_BATTERY_LEVEL = "btry_btry lvl";
        public static final String AUTOIOT_CONTROL_CHANGE_MODE = "mode_Change Mode";
        public static final String AUTOIOT_CONTROL_FAN_MODE = "mode_Fan Mode";
        public static final String AUTOIOT_CONTROL_ROT_DEGREE = "rot_Degree";
        public static final String AUTOIOT_CONTROL_TEMP_MODE = "mode_Temp Mode";
        public static final String AUTOIOT_CONTROL_VIRATE_MODE = "mode_Vibrate Mode";
        public static final String AUTOIOT_DEVICE_STATE = "state_Device Work State";
        public static final String AUTOIOT_DEVICE_WORK_STATE = "state_Device Work State";
        public static final String AUTOIOT_ISOFIX_LOCK_STATE = "state_Isofix Lock State";
        public static final String AUTOIOT_LOCK_SWITCH = "switch_Lock Switch";
        public static final String MODE_CHANGE_MODE = "mode_Change Mode";
        public static final String MODE_FAN_MODE = "mode_Fan Mode";
        public static final String MODE_TEMP_MODE = "mode_Temp Mode";
        public static final String MODE_VIRATE_MODE = "mode_Vibrate Mode";
        public static final String STATE_BUCKLE_LOCK = "state_Buckle Lock";
        public static final String STATE_CURRENT_ORIENTATION = "state_Current Orientation";
        public static final String STATE_LOCK_UP_DOWN_STATE = "state_Lock UpDown State";
    }

    public static class DeviceIds {
        public static final String AUTOIOT_EYEMASSAGER = "eyeMassager";
        public static final String AUTOIOT_LOCK = "lock";
        public static final String AUTOIOT_SEAT = "seat";
    }

    public static Pair<String, String> parserAttr(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split("_");
        if (strArrSplit.length <= 1) {
            return null;
        }
        return Pair.create(strArrSplit[0], strArrSplit[1]);
    }

    public static String assembleAttr(String str, String str2) {
        return str + "_" + str2;
    }

    public static DeviceTypeEnum deviceType(String str) {
        String[] strArrSplit = str.split("_");
        if (strArrSplit.length <= 1) {
            return DeviceTypeEnum.UN_KNOWN_DEVICE;
        }
        return DeviceTypeEnum.findByName(strArrSplit[0]);
    }
}
