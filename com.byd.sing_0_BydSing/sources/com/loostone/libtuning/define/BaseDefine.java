package com.loostone.libtuning.define;

/* JADX INFO: loaded from: classes.dex */
public class BaseDefine {

    public static class DongleUpdateType {
        public static final int DETECTION = 1;
        public static final int DOWNLOAD = 2;
        public static final int UNKNOWN = 0;
        public static final int UPGRADE_ERROR = 5;
        public static final int UPGRADE_FINISH = 4;
        public static final int UPGRADE_PROCESS = 6;
        public static final int VERIFY = 3;
    }

    public static class SystemPropertiesKey {
        public static final String AMPLIFIER_BRAND = "amplifierBrand";
        public static final String AMPLIFIER_COUNT = "amplifierCount";
        public static final String CAR_TYPE_CODE = "car_type_code";
        public static final String SCREEN_LIFT_STATE = "screen_lift_state";
    }
}
