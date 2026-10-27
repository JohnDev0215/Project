package com.ecarx.xui.adaptapi.vehicle.power;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface IPowerManager {
    public static final int ENERGY_SAVING_HINE_CANCLED = 2;
    public static final int ENERGY_SAVING_MODE_DEFAULT = 2147483637;
    public static final int ENERGY_SAVING_MODE_HINT = 1;

    @Deprecated
    public static final int INFOTAINMENT_MODE_LOW_BATTERY = 4;
    public static final int INFOTAINMENT_MODE_OFF = 0;
    public static final int INFOTAINMENT_MODE_ON = 1;
    public static final int INFOTAINMENT_MODE_PARTIAL = 2;
    public static final int INFOTAINMENT_MODE_UNKNOWN = Integer.MIN_VALUE;

    @Deprecated
    public static final int INFOTAINMENT_MODE_USAGE_INACTIVE = 5;
    public static final int INFOTAINMENT_MODE_WELCOME = 3;
    public static final int POWER_MODE_ACCESSORY = 4;
    public static final int POWER_MODE_CRANK = 6;
    public static final int POWER_MODE_ENERGY_SAVING = 9;
    public static final int POWER_MODE_INFOTAINMENT = 8;
    public static final int POWER_MODE_OFF_ASLEEP = 0;
    public static final int POWER_MODE_OFF_AWAKE = 1;
    public static final int POWER_MODE_RAP = 7;
    public static final int POWER_MODE_RAP_UNLOCK = 3;
    public static final int POWER_MODE_RUN = 5;
    public static final int POWER_MODE_SHUTDOWN = 10;
    public static final int POWER_MODE_UNKNOWN = Integer.MIN_VALUE;
    public static final int POWER_MODE_UNLOCK = 2;
    public static final int REASON_DEFAULT = 0;
    public static final int REASON_HVAC_HARDKEY = 1;
    public static final int SHUTDOWN_HINE_CANCLED = 2;
    public static final int SHUTDOWN_MODE_DEFAULT = 2147483637;
    public static final int SHUTDOWN_MODE_HINT = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DisplayOnOffReason {
    }

    public interface IPowerModeObserver {
        void onPowerModeChanged(int i, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PowerEnergySavingSubMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PowerInfotainmentMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PowerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PowerShutDownSubMode {
    }

    boolean attachPowerModeObserver(IPowerModeObserver iPowerModeObserver);

    void detachPowerModeObserver(IPowerModeObserver iPowerModeObserver);

    int getPowerMode();

    int getSubMode(int i);

    boolean releasePowerKeepLock(String str);

    boolean requestPowerkeepLock(String str);

    void setResetSettingsRequest(int i);

    void trunDisplayOff(int i);

    void trunDisplayOn(int i);
}
