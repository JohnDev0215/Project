package com.ecarx.xui.adaptapi.vehicle.bcm;

import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.Tribool;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface ILighting {
    public static final int DEVICE_ATMOSPHERE_LAMPS = 16;
    public static final int DEVICE_CORNERING_LAMPS = 6;
    public static final int DEVICE_DAYTIME_RUNNING_LAMPS = 9;
    public static final int DEVICE_DIM_DIP_LAMPS = 10;
    public static final int DEVICE_DIPPED_BEAM = 1;
    public static final int DEVICE_DRIVING_LAMPS = 3;
    public static final int DEVICE_FRONT_FOG_LAMPS = 4;
    public static final int DEVICE_FRONT_POSITION_LAMPS = 8;
    public static final int DEVICE_HAZARD_FLASHERS = 15;
    public static final int DEVICE_MAIN_BEAM = 2;
    public static final int DEVICE_REAR_FOG_LAMPS = 5;
    public static final int DEVICE_REAR_POSITION_LAMPS = 12;
    public static final int DEVICE_REVERSING_LAMPS = 14;
    public static final int DEVICE_SIDE_MARKER_LIGHTS = 11;
    public static final int DEVICE_SPOT_LIGHTS = 7;
    public static final int DEVICE_STOP_LAMPS = 13;
    public static final int FOLLOW_ME_HOME_30S = 1;
    public static final int FOLLOW_ME_HOME_60S = 2;
    public static final int FOLLOW_ME_HOME_90S = 3;
    public static final int FOLLOW_ME_HOME_OFF = 0;
    public static final int FUNC_LAMP_FOLLOW_ME_TIME = 2;
    public static final int FUNC_LAMP_TURN_EFFECT = 1;
    public static final int TURN_SIGNAL_EFFECTIVE_FASHION = 1;
    public static final int TURN_SIGNAL_EFFECTIVE_NORMAL = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FollowMeHomeTime {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LampAndSignallingFunction {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LightingAndSignallingDevice {
    }

    public interface LightingStatusListener {
        void onLampGoHomeTime(int i);

        boolean onLightDeviceBrightnessChanged(int i, int i2);

        void onLightingAndSignallingDeviceStatus(int i, boolean z);

        void onLightingReminder(int i);

        void onTurnSignalLampEffect(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TurnSignalLampEffective {
    }

    void attachLightingAndSignallingDeviceStatusListener(LightingStatusListener lightingStatusListener, int[] iArr);

    void detachLightingAndSignallingDeviceStatusListener(LightingStatusListener lightingStatusListener);

    int getFollowMeHomeTimeSetting();

    int getLightDeviceBrightness(int i);

    int getLightDeviceMaxBrightness(int i);

    int getLightDeviceMinBrightness(int i);

    int getTurnSignalLampEffective();

    FunctionStatus isFunctionSupported(int i);

    FunctionStatus isFunctionSupported(int i, int i2);

    FunctionStatus isLightDeviceSupported(int i);

    FunctionStatus isLightDeviceSupported(int i, int i2);

    Tribool isLightingOrSignallingDeviceOn(int i);

    void setFollowMeHomeTime(int i);

    boolean setLightDeviceBrightness(int i, int i2);

    boolean setLightDeviceOnOff(int i, boolean z);

    void setTurnSignalLampEffective(int i);
}
