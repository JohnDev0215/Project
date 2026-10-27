package com.byd.dilink.hardware.sensor;

/* JADX INFO: loaded from: classes.dex */
public class DiLinkSensorPrivacyConstants {
    public static final long TIME_PERIOD_FOR_CLOSE = 1004;
    public static final long TIME_PERIOD_FOR_ONE_TIME = 1001;
    public static final long TIME_PERIOD_FOR_ONE_YEAR = 1003;
    public static final long TIME_PERIOD_FOR_SIX_MONTH = 1002;

    public static class Sensors {
        public static final int CAMERA = 2;
        public static final int MICROPHONE = 1;
    }

    public static class Sources {
        public static final int DIALOG = 3;
        public static final int OTHER = 5;
        public static final int QS_TILE = 1;
        public static final int SAFETY_CENTER = 6;
        public static final int SETTINGS = 2;
        public static final int SHELL = 4;
    }
}
