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

    public void getAllStatus() {
    }

    public int getDayRunningLightState() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public int getDoubleFlashLightState() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_LIGHT_GET;
    }

    public int getGroupHeadlightState(int i) {
        return 0;
    }

    public int getIlluminationIntensityLevel() {
        return 0;
    }

    public int getLightAdbState(int i) {
        return 0;
    }

    public int getSequentialLightState() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_LIGHT_SET;
    }

    public int getTurnLightState(int i) {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int hasFeature(String str) {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return false;
    }

    public int setAcLightAdbState(int i) {
        return 0;
    }

    private BYDAutoLightDevice(Context context) {
        super(context);
        this.mLightManagerImpl = LightManagerImpl.getInstance(context);
    }

    public static BYDAutoLightDevice getInstance(Context context) {
        if (mBYDAutoLightDevice == null) {
            synchronized (BYDAutoLightDevice.class) {
                if (mBYDAutoLightDevice == null) {
                    mBYDAutoLightDevice = new BYDAutoLightDevice(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoLightDevice;
    }

    public int getLightStatus(int i) {
        return this.mLightManagerImpl.getLightStatus(i);
    }

    public int getLightAutoStatus() {
        return this.mLightManagerImpl.getLightAutoStatus();
    }

    public int getAFSSwitch() {
        return this.mLightManagerImpl.getAFSSwitch();
    }

    public int getTurnLightFlashState() {
        return this.mLightManagerImpl.getTurnLightFlashState();
    }

    @Deprecated
    public int getAmbientMusicModeState(int i) {
        return this.mLightManagerImpl.getAmbientMusicModeState(i);
    }

    @Deprecated
    public int setAmbientMusicModeState(int i, int i2) {
        return this.mLightManagerImpl.setAmbientMusicModeState(i, i2);
    }

    public int getAmbientMusicMode(int i) {
        return this.mLightManagerImpl.getAmbientMusicMode(i);
    }

    public int setAmbientMusicMode(int i, int i2) {
        return this.mLightManagerImpl.setAmbientMusicMode(i, i2);
    }

    public int getAmbientMulticolorState(int i) {
        return this.mLightManagerImpl.getAmbientMulticolorState(i);
    }

    public int setAmbientMulticolorState(int i, int i2) {
        return this.mLightManagerImpl.setAmbientMulticolorState(i, i2);
    }

    public int getAmbientMulticolorMode(int i) {
        return this.mLightManagerImpl.getAmbientMulticolorMode(i);
    }

    public int setAmbientMulticolorMode(int i, int i2) {
        return this.mLightManagerImpl.setAmbientMulticolorMode(i, i2);
    }

    public int setAmbientMusicRhym(int i, int i2) {
        return this.mLightManagerImpl.setAmbientMusicRhym(i, i2);
    }

    public int getAmbientMusicRhym(int i) {
        return this.mLightManagerImpl.getAmbientMusicRhym(i);
    }

    public int getLightDelayTime(int i) {
        return this.mLightManagerImpl.getLightDelayTime(i);
    }

    public int setLightDelayTime(int i, int i2) {
        return this.mLightManagerImpl.setLightDelayTime(i, i2);
    }

    public int getDayTimeLightState() {
        return this.mLightManagerImpl.getDayTimeLightState();
    }

    public int setDayTimeLightState(int i) {
        return this.mLightManagerImpl.setDayTimeLightState(i);
    }

    public int getDoorLightState() {
        return this.mLightManagerImpl.getDoorLightState();
    }

    public int setDoorLightState(int i) {
        return this.mLightManagerImpl.setDoorLightState(i);
    }

    public void reSetAmbientParams(int i) {
        this.mLightManagerImpl.reSetAmbientParams(i);
    }

    public void resetDelayLightParams() {
        this.mLightManagerImpl.resetDelayLightParams();
    }

    public int setAFSSwitchSate(int i) {
        return this.mLightManagerImpl.setAFSSwitchSate(i);
    }

    public int setMusicModeState(int i, int i2) {
        return this.mLightManagerImpl.setMusicModeState(i, i2);
    }

    public int[] getAmbientColors(int i) {
        return this.mLightManagerImpl.getAmbientColors(i);
    }

    public int setBrightness(int i, int i2) {
        return this.mLightManagerImpl.setBrightness(i, i2);
    }

    public int getLastSetedArea() {
        return this.mLightManagerImpl.getLastSetedArea();
    }

    public int setTurnLightsFlashState(int i) {
        return this.mLightManagerImpl.setTurnLightsFlashState(i);
    }

    public void registerListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        this.mLightManagerImpl.registerListener(absBYDAutoLightListener);
    }

    public void registerListener(AbsBYDAutoLightListener absBYDAutoLightListener, int[] iArr) {
        this.mLightManagerImpl.registerListener(absBYDAutoLightListener, iArr);
    }

    public void unregisterListener(AbsBYDAutoLightListener absBYDAutoLightListener) {
        this.mLightManagerImpl.unregisterListener(absBYDAutoLightListener);
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
    public int getIALColor(int i) {
        return this.mLightManagerImpl.getIalColor(i);
    }

    @Deprecated
    public int setIALColor(int i, int i2, int i3) {
        return this.mLightManagerImpl.setIalColor(i, i2, i3);
    }

    @Deprecated
    public int getIALBrightness(int i) {
        return this.mLightManagerImpl.getIalBrightness(i);
    }

    public int getAmbRingBrightness() {
        return this.mLightManagerImpl.getAmbRingBrightness();
    }

    public int getAmbRingColor() {
        return this.mLightManagerImpl.getAmbRingColor();
    }

    public int setIALBrightness(int i, int i2, int i3) {
        return this.mLightManagerImpl.setIalBrightness(i, i2, i3);
    }

    public int setOnSceneMode(int i) {
        return this.mLightManagerImpl.setOnSceneMode(i);
    }

    public int getDayTimeRunningLightState() {
        return this.mLightManagerImpl.getDayTimeRunningLightState();
    }

    public int getDayLightSupport() {
        return this.mLightManagerImpl.getDayLightSupport();
    }

    public int setKtvMode(int i, int i2) {
        return this.mLightManagerImpl.setKtvMode(i, i2);
    }

    public int getBacklightLevels() {
        return this.mLightManagerImpl.getBacklightLevels();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mLightManagerImpl.get(iArr, cls);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mLightManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int setLeftFrontReadingLampSwitchState(int i) {
        return this.mLightManagerImpl.setLeftFrontReadingLampSwitchState(i);
    }

    public int setRightFrontReadingLampSwitchState(int i) {
        return this.mLightManagerImpl.setRightFrontReadingLampSwitchState(i);
    }

    public int setLeftMiddleRowSideRLightSwitchState(int i) {
        return this.mLightManagerImpl.setLeftMiddleRowSideRLightSwitchState(i);
    }

    public int setRightMiddleRowRLightSwitchState(int i) {
        return this.mLightManagerImpl.setRightMiddleRowRLightSwitchState(i);
    }

    public int setLeftMiddleRowRLightRotaryState(int i) {
        return this.mLightManagerImpl.setLeftMiddleRowRLightRotaryState(i);
    }

    public int setRightMiddleRowRLightRotaryState(int i) {
        return this.mLightManagerImpl.setRightMiddleRowRLightRotaryState(i);
    }

    public int setLeftRearRLightSwitchState(int i) {
        return this.mLightManagerImpl.setLeftRearRLightSwitchState(i);
    }

    public int setRightRearRLightSwitchState(int i) {
        return this.mLightManagerImpl.setRightRearRLightSwitchState(i);
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

    public int setCargoLightSwitchState(int i) {
        return this.mLightManagerImpl.setCargoLightSwitchState(i);
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

    public int setPositionLightDisplay(int i) {
        return this.mLightManagerImpl.setPositionLightDisplay(i);
    }

    public int getRightDaytimeLightState() {
        return this.mLightManagerImpl.getRightDaytimeLightState();
    }

    public int getLeftDaytimeLightState() {
        return this.mLightManagerImpl.getLeftDaytimeLightState();
    }

    public int setHighlightPosition(int i) {
        return this.mLightManagerImpl.setHighlightPosition(i);
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
}
