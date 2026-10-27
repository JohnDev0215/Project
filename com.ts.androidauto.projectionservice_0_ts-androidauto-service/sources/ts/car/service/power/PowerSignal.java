package ts.car.service.power;

/* JADX INFO: loaded from: classes2.dex */
public class PowerSignal {
    public static final int INVALID = -1;

    public static final class SusdLevel {
        public static final int SUSD_POWER_DYN_LEVEL_OFF = 6;
        public static final int SUSD_POWER_DYN_LEVEL_ON = 5;
        public static final int SUSD_POWER_STA_LEVEL1 = 2;
        public static final int SUSD_POWER_STA_LEVEL2 = 3;
        public static final int SUSD_POWER_STA_LEVEL2_TIMOUT = 11;
        public static final int SUSD_POWER_STA_LEVEL_OFF = 4;
        public static final int SUSD_POWER_SYS_ERROR_OFF = 8;
        public static final int SUSD_POWER_SYS_ERROR_ON = 7;
        public static final int SUSD_POWER_THERMAL_HIGH = 9;
        public static final int SUSD_POWER_THERMAL_HIGH_TIMOUT = 12;
        public static final int SUSD_POWER_THERMAL_NORMAL = 14;
        public static final int SUSD_POWER_VOLTAGE_TIMOUT = 13;
        public static final int SUSD_TRANPORT_OFF = 0;
        public static final int SUSD_TRANPORT_ON = 1;
        public static final int SUSD_TRANPORT_ON_TIMEOUT = 10;
    }

    public static final class Type {
        public static final int POWER_SAVE_MODE = 2;
        public static final int VOLTAGE = 1;
    }

    public static final class VoltageLevel {
        public static final int VOLTAGE_CRITICAL_HIGH = 1;
        public static final int VOLTAGE_CRITICAL_LOW = 5;
        public static final int VOLTAGE_HIGH = 2;
        public static final int VOLTAGE_LOW = 4;
        public static final int VOLTAGE_NORMAL = 3;
    }
}
