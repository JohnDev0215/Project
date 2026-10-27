package com.ecarx.xui.adaptapi.vehicle;

import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.Tribool;
import com.ecarx.xui.adaptapi.vehicle.settings.IAmbientLightAdjust;
import com.ecarx.xui.adaptapi.vehicle.settings.IBackLightAdjust;
import com.ecarx.xui.adaptapi.vehicle.settings.ILaneKeep;
import com.ecarx.xui.adaptapi.vehicle.settings.ITrunkLid;
import com.ecarx.xui.adaptapi.vehicle.settings.IUnlockDoorSettings;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public interface ISettings {
    public static final int BACK_DOOR_AUTO_UNLOCK_DISTANCE_1 = 1;
    public static final int BACK_DOOR_AUTO_UNLOCK_DISTANCE_2 = 2;
    public static final int CAR_ALARM_TYPE_LIGHT = 2;
    public static final int CAR_ALARM_TYPE_TRUMPET = 1;
    public static final int CAR_ALARM_TYPE_TRUMPET_AND_LIGHT = 3;
    public static final int DIM_THEME_BLUE = 3;
    public static final int DIM_THEME_GOLD = 2;
    public static final int DIM_THEME_RED = 1;
    public static final int EPS_MODE_COMFORT = 1;
    public static final int EPS_MODE_SUPPORT = 2;
    public static final int F_AMBIENT_LIGHT_COLOR_SET = 65;
    public static final int F_AMBIENT_LIGHT_FOLLOW_DRIVEMODE = 69;
    public static final int F_ANY_DOOR_LOCK_CAR_WARNING = 24;
    public static final int F_AUTONOMOUS_EMERGENCY_BRAKING = 1;
    public static final int F_AUTO_CLOSE_POSITION_LIGHT_WHEN_DOOR_LOCKED = 4;
    public static final int F_AUTO_CLOSE_WINDOW = 13;
    public static final int F_AUTO_HOLD = 64;
    public static final int F_AUTO_LOCK_WHEN_LEAVE_DOOR = 22;
    public static final int F_AUTO_UNLOCK_WHEN_ENGINE_OFF = 6;
    public static final int F_AUTO_UNLOCK_WHEN_NEAR_BACK_DOOR = 20;
    public static final int F_AUTO_UNLOCK_WHEN_NEAR_DOOR = 21;
    public static final int F_BACK_DOOR_AUTO_OPEN = 26;
    public static final int F_BACK_DOOR_AUTO_UNLOCK_DISTANCE = 27;
    public static final int F_BACK_DOOR_UNLOCK_WITHOUT_KEY = 28;
    public static final int F_BACK_LIGHT_ADJUST = 51;
    public static final int F_BLIND_SPOT_DECT = 31;
    public static final int F_BLIND_SPOT_DECT_VOICE_ALARM = 32;
    public static final int F_BLN_SWITCH = 70;

    @Deprecated
    public static final int F_BOOT_MUSIC = 42;
    public static final int F_CAR_ALARM = 39;
    public static final int F_CHANGE_DIM_THEME = 37;
    public static final int F_DAY_RUNNING_LAMP = 10;
    public static final int F_DIM_THEME_FOLLOW_DRIVEMODE = 36;
    public static final int F_DRIVE_PILOT = 59;
    public static final int F_ENGINE_STOP_START = 55;
    public static final int F_EPS_MODE = 41;
    public static final int F_ESP = 17;
    public static final int F_FLAMEOUT_UNLOCK_DOOR = 49;
    public static final int F_FLASH_WHEN_DOOR_AJAR = 15;
    public static final int F_FOG_LAMP_AUXILIARY = 46;
    public static final int F_FORWARD_COLLISION_WARNING = 2;
    public static final int F_HEAD_LAMP_WITH_DOOR = 30;
    public static final int F_HUD_SET = 33;
    public static final int F_HUD_SET_ADJUST = 34;
    public static final int F_IBOOSTER = 53;
    public static final int F_INTELLIGENT_CORNERING_LAMP = 16;
    public static final int F_INTELLIGENT_HIGH_BEAM_CONTROL = 8;
    public static final int F_KEY_IN_CAR_LOCK_ALARM = 45;
    public static final int F_LANE_CHANGE_AID = 61;
    public static final int F_LANE_DEPARTURE_WARNING = 9;
    public static final int F_LANE_KEEP_AID = 60;
    public static final int F_LDW_THRESHOLD = 68;
    public static final int F_MIRROR_AUTO_FOLDING = 5;
    public static final int F_MIRROR_TILTED = 38;
    public static final int F_MaintanceWarningInterval = 19;
    public static final int F_MaintanceWarningSts = 18;
    public static final int F_OPEN_DOOR_WARNING = 12;
    public static final int F_POWER_ON_LOCK_ALARM = 44;
    public static final int F_PRE_COLLISION_SYS = 67;
    public static final int F_RAER_COLLISION_TRAFFIC_ALERT = 62;
    public static final int F_REAR_COLLISION_WARNING = 63;
    public static final int F_REMOTE_KEY_UNLOCK_DOOR = 50;
    public static final int F_RKE_LOCK_FEEDBACK = 7;
    public static final int F_RLS = 3;
    public static final int F_RT_CAMERA_FOLLOW_TURNNING = 54;
    public static final int F_SAILING_MODE = 66;
    public static final int F_SEAT_COMFORT_GETOFF = 52;
    public static final int F_SEAT_REMEMBER = 29;
    public static final int F_SMART_ENERGY_MANAGE = 35;

    @Deprecated
    public static final int F_SOUND_CLIMATE_HARDKEY = 57;
    public static final int F_SOUND_TOUCH_BOARD = 43;
    public static final int F_SPEED_LIMIT_REMINDER = 58;
    public static final int F_SPEED_LOCK = 14;
    public static final int F_SUNROOF_RKE_MODE = 40;
    public static final int F_SYNC_CURRENT_STATUS = 0;
    public static final int F_TRUNK_LID_OPEN_HEIGHT = 48;
    public static final int F_WELCOME_LIGHT = 23;
    public static final int F_WINDOW_CLAMP_WARNING = 25;
    public static final int F_WINDOW_VENTILATE = 11;
    public static final int F_WIPER_FOLLOW_REVERSE_GEAR = 47;
    public static final int F_WIPER_SET_BY_AUTOSPEED = 56;
    public static final int LDW_THRESHOLD_LONG = 3;
    public static final int LDW_THRESHOLD_NORMAL = 2;
    public static final int LDW_THRESHOLD_SHORT = 1;
    public static final int MIRROR_TILTED_BOTH = 3;
    public static final int MIRROR_TILTED_DRIVER = 1;
    public static final int MIRROR_TILTED_OFF = 0;
    public static final int MIRROR_TILTED_PASSENGER = 2;
    public static final int RUDDER_POSITION_LEFT = 1;
    public static final int RUDDER_POSITION_RIGHT = 2;
    public static final int SUNROOF_RKE_MODE_CLOSE_SUNROOF_AND_SHADE = 1;
    public static final int SUNROOF_RKE_MODE_ONLY_CLOSE_SUNROOF = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BackdoorAutoUnlockDistance {
    }

    public interface BackdoorAutoUnlockDistanceCallBack {
        void onBackdoorAutoUnlockDistanceChanged(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CarAlarmType {
    }

    public interface CarAlarmTypeCallBack {
        void onCarAlarmTypeChange(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DimTheme {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EPSMode {
    }

    public interface EPSModeCallBack {
        void onEPSModeChange(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EnableSupportedFunctions {
    }

    public interface FunctionEnableCallBack {
        void onFunctionEnable(int i, Tribool tribool);
    }

    public interface IDimThemeCallBack {
        void onDimThemeChanged(int i);
    }

    public interface IFunctionSupportedCallBack {
        void onFunctionSupportedChanged(int i, FunctionStatus functionStatus);
    }

    public interface IResetSettingCallBack {
        void onSettingDataReset(boolean z);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LDWThreshold {
    }

    public interface LDWThresholdCallBack {
        void onLDWThresholdChange(int i);
    }

    public interface MaintainMilesChangeCallBack {
        void onMaintainMilesChange(int i, int i2);
    }

    public interface MirrorTiltedCallBack {
        void onMirrorTiltedTypeChanged(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MirrorTiltedType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RudderPosition {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SunroofRKEMode {
    }

    public interface SunroofRkeModeCallBack {
        void onSunroofRkeModeChange(int i);
    }

    IAmbientLightAdjust getAmbientLightSetting();

    IBackLightAdjust getBackLightAdjust();

    int getBackdoorAutoUnlockDistance();

    int getCarAlarmType();

    int getDimTheme();

    int getEpsMode();

    Tribool getFunctionEnable(int i);

    int getLDWThreshold();

    ILaneKeep getLaneKeepAidSetting();

    int getMirrorTiltedType();

    int getRudderPosition();

    int getSunroofRkeMode();

    int[] getSupportedFunctionSubs(int i);

    int[] getSupportedSetFunctions();

    ITrunkLid getTrunkLid();

    IUnlockDoorSettings getUnlockDoorSettings();

    FunctionStatus isFunctionSupported(int i);

    FunctionStatus isFunctionSupported(int i, int i2);

    void registerFunctionSupportedCallBack(IFunctionSupportedCallBack iFunctionSupportedCallBack);

    void registerResetSettingCallBack(IResetSettingCallBack iResetSettingCallBack);

    boolean resetSettingDataDefault();

    void setBackdoorAutoUnlockDistance(int i);

    void setBackdoorAutoUnlockDistanceCallBack(BackdoorAutoUnlockDistanceCallBack backdoorAutoUnlockDistanceCallBack);

    void setCarAlarmType(int i);

    void setCarAlarmTypeCallBack(CarAlarmTypeCallBack carAlarmTypeCallBack);

    boolean setDimTheme(int i);

    void setDimThemeCallBack(IDimThemeCallBack iDimThemeCallBack);

    void setEPSMode(int i);

    void setEPSModeCallBack(EPSModeCallBack ePSModeCallBack);

    void setFunctionEnable(int i, boolean z);

    void setFunctionEnableCallBack(FunctionEnableCallBack functionEnableCallBack);

    void setLDWThreshold(int i);

    void setLDWThresholdCallBack(LDWThresholdCallBack lDWThresholdCallBack);

    void setMaintainMilesChangeCallBack(MaintainMilesChangeCallBack maintainMilesChangeCallBack);

    void setMirrorTiltedType(int i);

    void setMirrorTiltedTypeCallBack(MirrorTiltedCallBack mirrorTiltedCallBack);

    void setSunroofRkeMode(int i);

    void setSunroofRkeModeCallBack(SunroofRkeModeCallBack sunroofRkeModeCallBack);

    void unregisterFunctionSupportedCallBack(IFunctionSupportedCallBack iFunctionSupportedCallBack);

    void unregisterResetSettingCallBack(IResetSettingCallBack iResetSettingCallBack);

    void unsetBackdoorAutoUnlockDistanceCallBack(BackdoorAutoUnlockDistanceCallBack backdoorAutoUnlockDistanceCallBack);

    void unsetCarAlarmTypeCallBack(CarAlarmTypeCallBack carAlarmTypeCallBack);

    void unsetDimThemeCallBack(IDimThemeCallBack iDimThemeCallBack);

    void unsetEPSModeCallBack(EPSModeCallBack ePSModeCallBack);

    void unsetFunctionEnableCallBack(FunctionEnableCallBack functionEnableCallBack);

    void unsetLDWThresholdCallBack(LDWThresholdCallBack lDWThresholdCallBack);

    void unsetMaintainMilesChangeCallBack(MaintainMilesChangeCallBack maintainMilesChangeCallBack);

    void unsetMirrorTiltedTypeCallBack(MirrorTiltedCallBack mirrorTiltedCallBack);

    void unsetSunroofRkeModeCallBack(SunroofRkeModeCallBack sunroofRkeModeCallBack);
}
