package com.bydux.api.common.config;

/* JADX INFO: loaded from: classes3.dex */
public class ConfigValue {

    public static class Ap {
        public static final boolean DEFAULT_AP_ENABLE = false;
        public static final boolean DEFAULT_MOBILE_ENABLE = true;
        public static final boolean DEFAULT_WIFI_ENABLE = true;
    }

    public static class Audio {
        public static final int EQ_BAND_COUNT = 10;
        public static final int EQ_GAIN_MAX = 7;
        public static final int EQ_GAIN_MIN = -7;
        public static final int FADE_BALANCE_MAX = 7;
        public static final int FADE_BALANCE_MIN = -7;
    }

    public static class Brightness {
        public static final int BRIGHTNESS_MAX = 100;
        public static final int BRIGHTNESS_MIN = 10;
    }

    public static class CarConfig {
        public static final int HIGH = 2;
        public static final int LOW = 0;
        public static final int MID = 1;
    }

    public static class CarType {
        public static final int BYDUX = 0;
    }

    public static class Tier1Type {
        public static final int BYD = 0;
        public static final int CHELIANTIANXIA = 2;
        public static final int ZHONGKECHAUNGDA = 1;
    }
}
