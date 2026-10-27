package android.hardware.bydauto.light;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.light.tsimpl.LightManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoLightDevice extends AbsBYDAutoDevice {
    public static final int AMBIENT_COLD_WHITE = 2;
    public static final int AMBIENT_ICE_BLUE = 1;
    public static final int AMBIENT_INVALID = 0;
    public static final int AMBIENT_MAUVE = 6;
    public static final int AMBIENT_REDDISH_ORANGE = 4;
    public static final int AMBIENT_RESERVED = 8;
    public static final int AMBIENT_TRUE_BLUE = 7;
    public static final int AMBIENT_TRUE_RED = 5;
    public static final int AMBIENT_WARM_WHITE = 3;
    public static final int AMB_BRIGHTNESS_LEVEL_FIFTH = 6;
    public static final int AMB_BRIGHTNESS_LEVEL_FIRST = 2;
    public static final int AMB_BRIGHTNESS_LEVEL_FOURTH = 5;
    public static final int AMB_BRIGHTNESS_LEVEL_INVALID = 0;
    public static final int AMB_BRIGHTNESS_LEVEL_RESERVED = 7;
    public static final int AMB_BRIGHTNESS_LEVEL_SECOND = 3;
    public static final int AMB_BRIGHTNESS_LEVEL_THIRD = 4;
    public static final int AMB_BRIGHTNESS_LEVEL_ZERO = 1;
    public static final int AMB_COLOR_MODE_FIRST = 1;
    public static final int AMB_COLOR_MODE_INVALID = 0;
    public static final int AMB_COLOR_MODE_SECOND = 2;
    public static final int AMB_COLOR_MODE_THIRD = 3;
    public static final int AMB_MUSIC_MODE_FIFTH = 5;
    public static final int AMB_MUSIC_MODE_FIRST = 1;
    public static final int AMB_MUSIC_MODE_FOURTH = 4;
    public static final int AMB_MUSIC_MODE_INVALID = 1;
    public static final int AMB_MUSIC_MODE_SECOND = 2;
    public static final int AMB_MUSIC_MODE_THIRD = 3;
    public static final int AREA_BACK_ROW = 2;
    public static final int AREA_FRONT_ROW = 1;
    public static final int AREA_INVALID = 0;
    public static final int AREA_RESERVED = 4;
    public static final int AREA_VEHICLE = 3;
    public static final int BACK_HOME_DELAY_LEVEL_CLOSE = 7;
    public static final int BACK_HOME_DELAY_LEVEL_FIFTH = 5;
    public static final int BACK_HOME_DELAY_LEVEL_FIRST = 1;
    public static final int BACK_HOME_DELAY_LEVEL_FOURTH = 4;
    public static final int BACK_HOME_DELAY_LEVEL_INVALID = 0;
    public static final int BACK_HOME_DELAY_LEVEL_SECOND = 2;
    public static final int BACK_HOME_DELAY_LEVEL_SIXTH = 6;
    public static final int BACK_HOME_DELAY_LEVEL_THIRD = 3;
    public static final int DAYTIME_LIGHT_CLOSE = 2;
    public static final int DAYTIME_LIGHT_INVALID = 0;
    public static final int DAYTIME_LIGHT_OPEN = 1;
    public static final int DAYTIME_LIGHT_UNDEFINED = 3;
    public static final int DAY_RUNNING_LIGHT_INVALID = 0;
    public static final int DAY_RUNNING_LIGHT_OFF = 2;
    public static final int DAY_RUNNING_LIGHT_ON = 1;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int DOUBLE_FLASH_INVALID = 0;
    public static final int DOUBLE_FLASH_OFF = 2;
    public static final int DOUBLE_FLASH_ON = 1;
    public static final String FEATURE_DAY_RUNNING_LIGHT_3AC = "DayRunningLight3ac";
    public static final String FEATURE_DAY_RUNNING_LIGHT_475 = "DayRunningLight475";
    public static final String FEATURE_DAY_RUNNING_LIGHT_CONFIG = "DayRunningLightConfig";
    public static final String FEATURE_LIGHT_LEFT_ADB = "LeftADB";
    public static final String FEATURE_LIGHT_RIGHT_ADB = "RightADB";
    public static final int HEAD_LAMP_HEIGHT_LEVEL_EIGHTH = 8;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_ELEVENTH = 11;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_FIFTH = 5;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_FIRST = 1;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_FOURTH = 4;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_INVALID = 0;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_NINETH = 9;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_RESERVED = 12;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_SECONDE = 2;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_SEVENTH = 7;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_SIXTH = 6;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_TENTH = 10;
    public static final int HEAD_LAMP_HEIGHT_LEVEL_THIRD = 3;
    public static final int IAL_AREA_ALL_ROW = 3;
    public static final int IAL_AREA_BACK_ROW = 2;
    public static final int IAL_AREA_FRONT_ROW = 1;
    public static final int IAL_AREA_STARRY_SKY = 4;
    public static final int IAL_AREA_STAR_RING = 5;
    public static final int IAL_CTRL_SOURCE_UI = 0;
    public static final int IAL_CTRL_SOURCE_VOICE = 1;
    public static final int LAMP_SIGNAL_MODE_INVALID = 0;
    public static final int LAMP_SIGNAL_MODE_LOCK = 3;
    public static final int LAMP_SIGNAL_MODE_NULL = 1;
    public static final int LAMP_SIGNAL_MODE_RESERVED = 4;
    public static final int LAMP_SIGNAL_MODE_UNLOCK = 2;
    public static final int LEFT_TURN_LIGHT = 1;
    public static final int LIGHT_ADB_ACTIVATION = 3;
    public static final int LIGHT_ADB_FAULT = 5;
    public static final int LIGHT_ADB_INVALID = 0;
    public static final int LIGHT_ADB_OFF = 1;
    public static final int LIGHT_ADB_STANDBY = 2;
    public static final int LIGHT_ADB_STATE_INVALID = 0;
    public static final int LIGHT_ADB_STATE_OFF = 2;
    public static final int LIGHT_ADB_STATE_ON = 1;
    public static final int LIGHT_ADB_WORKING = 4;
    public static final int LIGHT_COMMAND_BUSY = -2147482647;
    public static final int LIGHT_COMMAND_FAILED = -2147482648;
    public static final int LIGHT_COMMAND_INVALID = -2147482645;
    public static final int LIGHT_COMMAND_SUCCESS = 0;
    public static final int LIGHT_COMMAND_TIMEOUT = -2147482646;
    public static final int LIGHT_DYNAMIC_COLORFUL_FLAG = 0;
    public static final int LIGHT_FL_ADB = 0;
    public static final int LIGHT_FOOT = 8;
    public static final int LIGHT_FRONT_FOG = 6;
    public static final int LIGHT_FR_ADB = 1;
    public static final int LIGHT_GROUP_LEFT = 0;
    public static final int LIGHT_GROUP_RIGHT = 1;
    public static final int LIGHT_HAS_DEVICE = 1;
    public static final int LIGHT_HIGH_BEAM = 3;
    public static final int LIGHT_ILLUM_LEVEL1 = 1;
    public static final int LIGHT_ILLUM_LEVEL2 = 2;
    public static final int LIGHT_ILLUM_LEVEL3 = 3;
    public static final int LIGHT_ILLUM_LEVEL4 = 4;
    public static final int LIGHT_ILLUM_LEVEL5 = 5;
    public static final int LIGHT_INVALID = 0;
    public static final int LIGHT_LEFT_TURN_SIGNAL = 4;
    public static final int LIGHT_LOW_BEAM = 2;
    public static final int LIGHT_NOT_HAS_DEVICE = 2;
    public static final int LIGHT_OFF = 0;
    public static final int LIGHT_ON = 1;
    public static final int LIGHT_REAR_FOG = 7;
    public static final int LIGHT_REAR_POSITION = 9;
    public static final int LIGHT_RESERVED = 3;
    public static final int LIGHT_RIGHT_TURN_SIGNAL = 5;
    public static final int LIGHT_SIDE = 1;
    public static final int LIGHT_STATE_FAULT = 1;
    public static final int LIGHT_STATE_INVALID = 2;
    public static final int LIGHT_STATE_NORMAL = 0;
    public static final String PERMISSION_BYDAUTO_LIGHT_COMMON = "android.permission.BYDAUTO_LIGHT_COMMON";
    public static final String PERMISSION_BYDAUTO_LIGHT_GET = "android.permission.BYDAUTO_LIGHT_GET";
    public static final String PERMISSION_BYDAUTO_LIGHT_SET = "android.permission.BYDAUTO_LIGHT_SET";
    public static final int REVERSING_LIGHT_INVALID = 0;
    public static final int REVERSING_LIGHT_OFF = 2;
    public static final int REVERSING_LIGHT_ON = 1;
    public static final int RIGHT_TURN_LIGHT = 2;
    public static final int SEQUENTIAL_LIGHT_INVALID = 3;
    public static final int SEQUENTIAL_LIGHT_OFF = 1;
    public static final int SEQUENTIAL_LIGHT_ON = 0;
    public static final int SET_INVALID = 0;
    public static final int SET_LOCK = 3;
    public static final int SET_OFF = 2;
    public static final int SET_ON = 1;
    public static final int SET_RESERVED = 3;
    public static final int SET_UNLOCK = 2;
    public static final int SMART_WELCOME_LIGHT_CLOSE = 2;
    public static final int SMART_WELCOME_LIGHT_INVALID = 0;
    public static final int SMART_WELCOME_LIGHT_OPEN = 1;
    public static final int SMART_WELCOME_LIGHT_RESERVED = 3;
    public static final int STOP_LIGHT_FLASH = 2;
    public static final int STOP_LIGHT_INVALID = 0;
    public static final int STOP_LIGHT_OFF = 3;
    public static final int STOP_LIGHT_ON = 1;
    private static final String TAG = "BYDAutoLightDevice";
    public static final int TURN_LIGHT_FLASH_DANGER = 6;
    public static final int TURN_LIGHT_FLASH_EMERG = 7;
    public static final int TURN_LIGHT_FLASH_FAST = 9;
    public static final int TURN_LIGHT_FLASH_REAR_END = 8;
    public static final int TURN_LIGHT_INVALID = 0;
    public static final int TURN_LIGHT_LEFT_FLASH_FAST = 3;
    public static final int TURN_LIGHT_LEFT_FLASH_NORMAL = 2;
    public static final int TURN_LIGHT_LEFT_LONG = 3;
    public static final int TURN_LIGHT_LEFT_SHORT = 2;
    public static final int TURN_LIGHT_LONG = 2;
    public static final int TURN_LIGHT_NOT_FLASH = 1;
    public static final int TURN_LIGHT_OFF = 1;
    public static final int TURN_LIGHT_RIGHT_FLASH_FAULT = 5;
    public static final int TURN_LIGHT_RIGHT_FLASH_NORMAL = 4;
    public static final int TURN_LIGHT_RIGHT_LONG = 5;
    public static final int TURN_LIGHT_RIGHT_SHORT = 4;
    public static final int TURN_LIGHT_SHORT = 1;
    private static volatile BYDAutoLightDevice mBYDAutoLightDevice;
    private LightManagerImpl mLightManagerImpl;

    private BYDAutoLightDevice(Context con) {
        super(con);
        this.mLightManagerImpl = LightManagerImpl.getInstance(con);
    }

    public static BYDAutoLightDevice getInstance(Context con) {
        if (mBYDAutoLightDevice == null) {
            synchronized (BYDAutoLightDevice.class) {
                if (mBYDAutoLightDevice == null) {
                    mBYDAutoLightDevice = new BYDAutoLightDevice(con.getApplicationContext());
                }
            }
        }
        return mBYDAutoLightDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_LIGHT_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_LIGHT_GET;
    }

    public int getLightStatus(int type) {
        return this.mLightManagerImpl.getLightStatus(type);
    }

    public int getLightAutoStatus() {
        return this.mLightManagerImpl.getLightAutoStatus();
    }

    public int getIlluminationIntensityLevel() {
        return 0;
    }

    public int getAFSSwitch() {
        return this.mLightManagerImpl.getAFSSwitch();
    }

    public int getGroupHeadlightState(int area) {
        return 0;
    }

    public int getDayRunningLightState() {
        return 0;
    }

    public int getSequentialLightState() {
        return 0;
    }

    public int getDoubleFlashLightState() {
        return 0;
    }

    public int setAcLightAdbState(int value) {
        return 0;
    }

    public int getLightAdbState(int id) {
        return 0;
    }

    public int hasFeature(String feature) {
        return 0;
    }

    public int getTurnLightState(int id) {
        return 0;
    }

    public int getTurnLightFlashState() {
        return this.mLightManagerImpl.getTurnLightFlashState();
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return false;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Deprecated
    public int getAmbientMusicModeState(int area) {
        return this.mLightManagerImpl.getAmbientMusicModeState(area);
    }

    @Deprecated
    public int setAmbientMusicModeState(int area, int state) {
        return this.mLightManagerImpl.setAmbientMusicModeState(area, state);
    }

    public int getAmbientMusicMode(int area) {
        return this.mLightManagerImpl.getAmbientMusicMode(area);
    }

    public int setAmbientMusicMode(int area, int value) {
        return this.mLightManagerImpl.setAmbientMusicMode(area, value);
    }

    public int getAmbientMulticolorState(int area) {
        return this.mLightManagerImpl.getAmbientMulticolorState(area);
    }

    public int setAmbientMulticolorState(int area, int value) {
        return this.mLightManagerImpl.setAmbientMulticolorState(area, value);
    }

    public int getAmbientMulticolorMode(int area) {
        return this.mLightManagerImpl.getAmbientMulticolorMode(area);
    }

    public int setAmbientMulticolorMode(int area, int value) {
        return this.mLightManagerImpl.setAmbientMulticolorMode(area, value);
    }

    public int setAmbientMusicRhym(int area, int value) {
        return this.mLightManagerImpl.setAmbientMusicRhym(area, value);
    }

    public int getAmbientMusicRhym(int area) {
        return this.mLightManagerImpl.getAmbientMusicRhym(area);
    }

    public int getLightDelayTime(int mode) {
        return this.mLightManagerImpl.getLightDelayTime(mode);
    }

    public int setLightDelayTime(int mode, int value) {
        return this.mLightManagerImpl.setLightDelayTime(mode, value);
    }

    public int getDayTimeLightState() {
        return this.mLightManagerImpl.getDayTimeLightState();
    }

    public int setDayTimeLightState(int state) {
        return this.mLightManagerImpl.setDayTimeLightState(state);
    }

    public int getDoorLightState() {
        return this.mLightManagerImpl.getDoorLightState();
    }

    public int setDoorLightState(int state) {
        return this.mLightManagerImpl.setDoorLightState(state);
    }

    public void reSetAmbientParams(int area) {
        this.mLightManagerImpl.reSetAmbientParams(area);
    }

    public void resetDelayLightParams() {
        this.mLightManagerImpl.resetDelayLightParams();
    }

    public int setAFSSwitchSate(int value) {
        return this.mLightManagerImpl.setAFSSwitchSate(value);
    }

    public int setMusicModeState(int area, int value) {
        return this.mLightManagerImpl.setMusicModeState(area, value);
    }

    public int[] getAmbientColors(int type) {
        return this.mLightManagerImpl.getAmbientColors(type);
    }

    public int setBrightness(int area, int value) {
        return this.mLightManagerImpl.setBrightness(area, value);
    }

    public int getLastSetedArea() {
        return this.mLightManagerImpl.getLastSetedArea();
    }

    public int setTurnLightsFlashState(int state) {
        return this.mLightManagerImpl.setTurnLightsFlashState(state);
    }

    public void registerListener(AbsBYDAutoLightListener listener) {
        this.mLightManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoLightListener listener, int[] featureIDs) {
        this.mLightManagerImpl.registerListener(listener, featureIDs);
    }

    public void unregisterListener(AbsBYDAutoLightListener listener) {
        this.mLightManagerImpl.unregisterListener(listener);
    }

    public int getStopLightState() {
        return this.mLightManagerImpl.getStopLightState();
    }

    public int getReversingLightState() {
        return this.mLightManagerImpl.getReversingLightState();
    }

    public int getTurnLightState() {
        return this.mLightManagerImpl.getTurnLightState();
    }

    @Deprecated
    public int getAmbientFeedBack() {
        return this.mLightManagerImpl.getAmbientFeedBack();
    }

    public int getAmbientColorsSupport() {
        return this.mLightManagerImpl.getAmbientColorsSupport();
    }

    @Deprecated
    public int getIALColor(int area) {
        return this.mLightManagerImpl.getIalColor(area);
    }

    @Deprecated
    public int setIALColor(int area, int color, int source) {
        return this.mLightManagerImpl.setIalColor(area, color, source);
    }

    @Deprecated
    public int getIALBrightness(int area) {
        return this.mLightManagerImpl.getIalBrightness(area);
    }

    public int getAmbRingBrightness() {
        return this.mLightManagerImpl.getAmbRingBrightness();
    }

    public int getAmbRingColor() {
        return this.mLightManagerImpl.getAmbRingColor();
    }

    public int setIALBrightness(int area, int brightness, int source) {
        return this.mLightManagerImpl.setIalBrightness(area, brightness, source);
    }

    public int setOnSceneMode(int mode) {
        return this.mLightManagerImpl.setOnSceneMode(mode);
    }

    public int getDayTimeRunningLightState() {
        return this.mLightManagerImpl.getDayTimeRunningLightState();
    }

    public int getDayLightSupport() {
        return this.mLightManagerImpl.getDayLightSupport();
    }

    public int setKtvMode(int color, int brightness) {
        return this.mLightManagerImpl.setKtvMode(color, brightness);
    }

    public int getBacklightLevels() {
        return this.mLightManagerImpl.getBacklightLevels();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mLightManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mLightManagerImpl.set(eventTypes, eventValue);
    }

    public int setLeftFrontReadingLampSwitchState(int state) {
        return this.mLightManagerImpl.setLeftFrontReadingLampSwitchState(state);
    }

    public int setRightFrontReadingLampSwitchState(int state) {
        return this.mLightManagerImpl.setRightFrontReadingLampSwitchState(state);
    }

    public int setLeftMiddleRowSideRLightSwitchState(int state) {
        return this.mLightManagerImpl.setLeftMiddleRowSideRLightSwitchState(state);
    }

    public int setRightMiddleRowRLightSwitchState(int state) {
        return this.mLightManagerImpl.setRightMiddleRowRLightSwitchState(state);
    }

    public int setLeftMiddleRowRLightRotaryState(int state) {
        return this.mLightManagerImpl.setLeftMiddleRowRLightRotaryState(state);
    }

    public int setRightMiddleRowRLightRotaryState(int state) {
        return this.mLightManagerImpl.setRightMiddleRowRLightRotaryState(state);
    }

    public int setLeftRearRLightSwitchState(int state) {
        return this.mLightManagerImpl.setLeftRearRLightSwitchState(state);
    }

    public int setRightRearRLightSwitchState(int state) {
        return this.mLightManagerImpl.setRightRearRLightSwitchState(state);
    }

    public int getLeftFrontReadingLampStatus() {
        return this.mLightManagerImpl.getLeftFrontReadingLampStatus();
    }

    public int getRightFrontReadingLampStatus() {
        return this.mLightManagerImpl.getRightFrontReadingLampStatus();
    }

    public int getLeftMiddleRowSideRLightSwitch() {
        return this.mLightManagerImpl.getLeftMiddleRowSideRLightSwitch();
    }

    public int getRightMiddleRowRLightSwitch() {
        return this.mLightManagerImpl.getRightMiddleRowRLightSwitch();
    }

    public int getLeftMiddleRowRLightRotaryState() {
        return this.mLightManagerImpl.getLeftMiddleRowRLightRotaryState();
    }

    public int getRightMiddleRowRLightRotary() {
        return this.mLightManagerImpl.getRightMiddleRowRLightRotary();
    }

    public int getLeftRearRLightSwitch() {
        return this.mLightManagerImpl.getLeftRearRLightSwitch();
    }

    public int getRightRearRLightSwitch() {
        return this.mLightManagerImpl.getRightRearRLightSwitch();
    }

    public int getCargoLightSwitchState() {
        return this.mLightManagerImpl.getCargoLightSwitchState();
    }

    public int getCargoLightSwitchConfig() {
        return this.mLightManagerImpl.getCargoLightSwitchConfig();
    }

    public int setCargoLightSwitchState(int state) {
        return this.mLightManagerImpl.setCargoLightSwitchState(state);
    }

    public int getLeftCornerLightStatus() {
        return this.mLightManagerImpl.getLeftCornerLightStatus();
    }

    public int getRightCornerLightStatus() {
        return this.mLightManagerImpl.getRightCornerLightStatus();
    }

    public int getFrontFogLampStatus() {
        return this.mLightManagerImpl.getFrontFogLampStatus();
    }

    public int getFrontCenterLightStatus() {
        return this.mLightManagerImpl.getFrontCenterLightStatus();
    }

    public int getPositionLightDisplayFeedCallback() {
        return this.mLightManagerImpl.getPositionLightDisplayFeedCallback();
    }

    public int setPositionLightDisplay(int state) {
        return this.mLightManagerImpl.setPositionLightDisplay(state);
    }

    public int getRightDaytimeLightState() {
        return this.mLightManagerImpl.getRightDaytimeLightState();
    }

    public int getLeftDaytimeLightState() {
        return this.mLightManagerImpl.getLeftDaytimeLightState();
    }

    public int setHighlightPosition(int state) {
        return this.mLightManagerImpl.setHighlightPosition(state);
    }

    public int getHightlightPosition() {
        return this.mLightManagerImpl.getHightlightPosition();
    }

    public int getHightlightPositionConfig() {
        return this.mLightManagerImpl.getHightlightPositionConfig();
    }

    public int getIntelligentPostionFeedback() {
        return this.mLightManagerImpl.getIntelligentPostionFeedback();
    }

    public int getLeftFrontPositionSwlStatus() {
        return this.mLightManagerImpl.getLeftFrontPositionSwlStatus();
    }

    public int getRightFrontPositionSwlStatus() {
        return this.mLightManagerImpl.getRightFrontPositionSwlStatus();
    }

    public int getLeftRemoteLightState() {
        return this.mLightManagerImpl.getLeftRemoteLightState();
    }

    public int getRightRemoteLightState() {
        return this.mLightManagerImpl.getRightRemoteLightState();
    }

    public int getLeftDaytimeRunLampState() {
        return this.mLightManagerImpl.getLeftDaytimeRunLampState();
    }

    public int getRightDaytimeRunLampState() {
        return this.mLightManagerImpl.getRightDaytimeRunLampState();
    }

    public int setHeadlightControlMode(int mode) {
        return this.mLightManagerImpl.setHeadlightControlMode(mode);
    }

    public int getHeadlightControlMode() {
        return this.mLightManagerImpl.getHeadlightControlMode();
    }

    public int getRearFogLightSwitchState() {
        return this.mLightManagerImpl.getRearFogLightSwitchState();
    }

    public int getAllWeatherLightSwitchState() {
        return this.mLightManagerImpl.getAllWeatherLightSwitchState();
    }

    public int getFrontFogLightSwitchState() {
        return this.mLightManagerImpl.getFrontFogLightSwitchState();
    }

    public int getFrontFogLightConfig() {
        return this.mLightManagerImpl.getFrontFogLightConfig();
    }

    public int setRearFogLightSwitchState(int state) {
        return this.mLightManagerImpl.setRearFogLightSwitchState(state);
    }

    public int setAllWeatherLightSwitchState(int state) {
        return this.mLightManagerImpl.setAllWeatherLightSwitchState(state);
    }

    public int setFrontFogLightSwitchState(int state) {
        return this.mLightManagerImpl.setFrontFogLightSwitchState(state);
    }

    public int getMiddleSternLampState() {
        return this.mLightManagerImpl.getMiddleSternLampState();
    }

    public int setAmbientState(int state) {
        return this.mLightManagerImpl.setAmbientState(state);
    }

    public int getAmbientState() {
        return this.mLightManagerImpl.getAmbientState();
    }

    public int getAmbientSwitchConfig() {
        return this.mLightManagerImpl.getAmbientSwitchConfig();
    }

    public int getLightRearGlowLogoWorkState() {
        return this.mLightManagerImpl.getLightRearGlowLogoWorkState();
    }

    public int getLightAdasIndicatorStatus() {
        return this.mLightManagerImpl.getLightAdasIndicatorStatus();
    }

    public int getLightFrontGlowLogoWorkState() {
        return this.mLightManagerImpl.getLightFrontGlowLogoWorkState();
    }

    public int getAtmosphereLampBodyDisplay() {
        return this.mLightManagerImpl.getAtmosphereLampBodyDisplay();
    }

    public int getAtmospereLightNightWeakenModeConfig() {
        return this.mLightManagerImpl.getAtmospereLightNightWeakenModeConfig();
    }

    public int getAtmospereLightNightWeakenMode() {
        return this.mLightManagerImpl.getAtmospereLightNightWeakenMode();
    }

    public int setAtmospereLightNightWeakenMode(int mode) {
        return this.mLightManagerImpl.setAtmospereLightNightWeakenMode(mode);
    }

    public int getAtmospereLampPopupPrompt() {
        return this.mLightManagerImpl.getAtmospereLampPopupPrompt();
    }

    public int getThemeColorSwitchState() {
        return this.mLightManagerImpl.getThemeColorSwitchState();
    }

    public int setThemeLinkedAmbienceLightColor(int color) {
        return this.mLightManagerImpl.setThemeLinkedAmbienceLightColor(color);
    }

    public int getAtmospereLampUiDisplay() {
        return this.mLightManagerImpl.getAtmospereLampUiDisplay();
    }

    public int getThemeColorFeedBack() {
        return this.mLightManagerImpl.getThemeColorFeedBack();
    }

    public int setThemeLinkedAtmosphereLampSwitch(int state) {
        return this.mLightManagerImpl.setThemeLinkedAtmosphereLampSwitch(state);
    }

    public int getThemeColorFeatureFlag() {
        return this.mLightManagerImpl.getThemeColorFeatureFlag();
    }
}
