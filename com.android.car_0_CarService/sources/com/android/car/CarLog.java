package com.android.car;

import com.android.launcher3.icons.cache.BaseIconCache;

/* JADX INFO: loaded from: classes3.dex */
public class CarLog {
    private static final int MAX_TAG_LEN = 23;
    public static final String TAG_AM = "CAR.AM";
    public static final String TAG_APP_FOCUS = "CAR.APP_FOCUS";
    public static final String TAG_AUDIO = "CAR.AUDIO";
    public static final String TAG_CABIN = "CAR.CABIN";
    public static final String TAG_CAMERA = "CAR.CAMERA";
    public static final String TAG_CAN_BUS = "CAR.CAN_BUS";
    public static final String TAG_CLUSTER = "CAR.CLUSTER";
    public static final String TAG_DIAGNOSTIC = "CAR.DIAGNOSTIC";
    public static final String TAG_HAL = "CAR.HAL";
    public static final String TAG_HVAC = "CAR.HVAC";
    public static final String TAG_INFO = "CAR.INFO";
    public static final String TAG_INPUT = "CAR.INPUT";
    public static final String TAG_MEDIA = "CAR.MEDIA";
    public static final String TAG_MONITORING = "CAR.MONITORING";
    public static final String TAG_NAV = "CAR.NAV";
    public static final String TAG_OAS = "CAR.OAS";
    public static final String TAG_OCCUPANT = "CAR.OCCUPANT";
    public static final String TAG_PACKAGE = "CAR.PACKAGE";
    public static final String TAG_POWER = "CAR.POWER";
    public static final String TAG_PROJECTION = "CAR.PROJECTION";
    public static final String TAG_PROPERTY = "CAR.PROPERTY";
    public static final String TAG_SENSOR = "CAR.SENSOR";
    public static final String TAG_SERVICE = "CAR.SERVICE";
    public static final String TAG_STORAGE = "CAR.STORAGE";
    public static final String TAG_SYS = "CAR.SYS";
    public static final String TAG_TEST = "CAR.TEST";
    public static final String TAG_USER = "CAR.USER";
    public static final String TAG_VENDOR_EXT = "CAR.VENDOR_EXT";
    public static final String TAG_WATCHDOG = "CAR.WATCHDOG";

    public static String concatTag(String tagPrefix, Class clazz) {
        String tag = tagPrefix + BaseIconCache.EMPTY_CLASS_NAME + clazz.getSimpleName();
        if (tag.length() > 23) {
            return tag.substring(0, 23);
        }
        return tag;
    }
}
