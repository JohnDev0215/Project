package android.hardware.bydauto.ac;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ac.tsimpl.ACManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoAcDevice extends AbsBYDAutoDevice {
    public static final int AC = 1;
    public static final int AC_AIR_QUAL_CTRL_MENU_OFF = 0;
    public static final int AC_AIR_QUAL_CTRL_MENU_ON = 1;
    public static final int AC_ASSISTMODE_NONE_REVE = 3;
    public static final int AC_ASSISTMODE_NONE_TIPS = 1;
    public static final int AC_ASSISTMODE_TIPS = 2;
    public static final int AC_BT_WIND_NONE_TIPS = 1;
    public static final int AC_BT_WIND_REVERSE = 3;
    public static final int AC_BT_WIND_TIPS = 2;
    public static final int AC_COMMAND_BUSY = -2147482647;
    public static final int AC_COMMAND_FAILED = -2147482648;
    public static final int AC_COMMAND_INVALID_VALUE = -2147482645;
    public static final int AC_COMMAND_SUCCESS = 0;
    public static final int AC_COMMAND_TIMEOUT = -2147482646;
    public static final int AC_COMPRESSOR_MANUAL_SIGN_OFF = 0;
    public static final int AC_COMPRESSOR_MANUAL_SIGN_ON = 1;
    public static final int AC_COMPRESSOR_OFF = 0;
    public static final int AC_COMPRESSOR_ON = 1;
    public static final int AC_CTRLMODE_AUTO = 0;
    public static final int AC_CTRLMODE_MANUAL = 1;
    public static final int AC_CTRL_SOURCE_UI_KEY = 0;
    public static final int AC_CTRL_SOURCE_VOICE = 1;
    public static final int AC_CYCLEMODE_INLOOP = 1;
    public static final int AC_CYCLEMODE_OUTLOOP = 0;
    public static final int AC_DEFROST_5S_OFFLINE = 0;
    public static final int AC_DEFROST_5S_ONLINE = 1;
    public static final int AC_DEFROST_AREA_FRONT = 1;
    public static final int AC_DEFROST_AREA_REAR = 2;
    public static final int AC_DEFROST_REAR_PANEL_NONE_TIPS = 1;
    public static final int AC_DEFROST_REAR_PANEL_REVERSE = 3;
    public static final int AC_DEFROST_REAR_PANEL_TIPS = 2;
    public static final int AC_DEFROST_STATE_OFF = 0;
    public static final int AC_DEFROST_STATE_ON = 1;
    public static final int AC_FAULT_NUM_SHOWN_STATE_INVALID = 0;
    public static final int AC_FAULT_NUM_SHOWN_STATE_OFF = 1;
    public static final int AC_FAULT_NUM_SHOWN_STATE_ON = 2;
    public static final int AC_FAULT_NUM_SHOWN_STATE_RESERVED = 3;
    public static final String AC_GET_PERM = "android.permission.BYDAUTO_AC_GET";
    public static final int AC_KEY_ACTION_FALSE = 1;
    public static final int AC_KEY_ACTION_INVALID = 0;
    public static final int AC_KEY_ACTION_TRUE = 2;
    public static final int AC_MAX_COOLING_OFF = 0;
    public static final int AC_MAX_COOLING_ON = 1;
    public static final int AC_ONLINE_STATE_OFF = 0;
    public static final int AC_ONLINE_STATE_ON = 1;
    public static final int AC_POWER_OFF = 0;
    public static final int AC_POWER_ON = 1;
    public static final int AC_PROMPT_BOX_SHOWN_STATE_INVALID = 0;
    public static final int AC_PROMPT_BOX_SHOWN_STATE_OFF = 2;
    public static final int AC_PROMPT_BOX_SHOWN_STATE_ON = 1;
    public static final int AC_PROMPT_BOX_SHOWN_STATE_RESERVE = 3;
    public static final int AC_PTC_PREHEAT_ILLUME = 1;
    public static final int AC_PTC_PREHEAT_INVALID = 0;
    public static final int AC_PTC_PREHEAT_NOTILLUME = 2;
    public static final int AC_PTC_PREHEAT_RESERVE = 3;
    public static final int AC_REAR_LOCK_STATE_OFF = 0;
    public static final int AC_REAR_LOCK_STATE_ON = 1;
    public static final int AC_REAR_NOT_AVAILABLE_MENU_INVALID = 0;
    public static final int AC_REAR_NOT_AVAILABLE_MENU_NO_POP = 1;
    public static final int AC_REAR_NOT_AVAILABLE_MENU_POP = 2;
    public static final int AC_REAR_PANEL_LOCK_INVALID = 0;
    public static final int AC_REAR_PANEL_LOCK_LOCKED = 1;
    public static final int AC_REAR_PANEL_LOCK_PROMPT_CLOSED = 0;
    public static final int AC_REAR_PANEL_LOCK_PROMPT_OPEN = 1;
    public static final int AC_REAR_PANEL_LOCK_UNLOCKED = 2;
    public static final int AC_REMOTE_CTRL_TIME_10 = 1;
    public static final int AC_REMOTE_CTRL_TIME_15 = 2;
    public static final int AC_REMOTE_CTRL_TIME_20 = 3;
    public static final int AC_REMOTE_CTRL_TIME_25 = 4;
    public static final int AC_REMOTE_CTRL_TIME_30 = 5;
    public static final String AC_SET_PERM = "android.permission.BYDAUTO_AC_SET";
    public static final int AC_TEMPCTRL_RANGE_SINGLE = 3;
    public static final int AC_TEMPCTRL_SEPARATE_OFF = 0;
    public static final int AC_TEMPCTRL_SEPARATE_ON = 1;
    public static final int AC_TEMPERATURE_CENTER_LEFT = 5;
    public static final int AC_TEMPERATURE_CENTER_RIGHT = 6;
    public static final int AC_TEMPERATURE_DEPUTY = 2;
    public static final int AC_TEMPERATURE_MAIN = 1;
    public static final int AC_TEMPERATURE_MAIN_DEPUTY = 0;
    public static final int AC_TEMPERATURE_OUT = 4;
    public static final int AC_TEMPERATURE_REAR = 3;
    public static final int AC_TEMPERATURE_UNIT_OC = 1;
    public static final int AC_TEMPERATURE_UNIT_OF = 0;
    public static final int AC_TEMP_INVALID = 0;
    public static final int AC_TEMP_IN_CELSIUS_MAX = 33;
    public static final int AC_TEMP_IN_CELSIUS_MIN = 17;
    public static final int AC_TEMP_IN_FAHRENHEIT_MAX = 91;
    public static final int AC_TEMP_IN_FAHRENHEIT_MIN = 64;
    public static final int AC_TEMP_OUT_CELSIUS_MAX = 50;
    public static final int AC_TEMP_OUT_CELSIUS_MIN = -40;
    public static final int AC_TEMP_OUT_FAHRENHEIT_MAX = 122;
    public static final int AC_TEMP_OUT_FAHRENHEIT_MIN = -40;
    public static final int AC_TUYERE_DEPUTY_LEFT = 2;
    public static final int AC_TUYERE_DEPUTY_RIGHT = 3;
    public static final int AC_TUYERE_MAIN_LEFT = 0;
    public static final int AC_TUYERE_MAIN_RIGHT = 1;
    public static final int AC_TYPE_AUTO = 1;
    public static final int AC_TYPE_ELECTRIC = 2;
    public static final int AC_TYPE_INVALID = 0;
    public static final int AC_VENTILATION_STATE_OFF = 0;
    public static final int AC_VENTILATION_STATE_ON = 1;
    public static final int AC_WARM_FUEL = 2;
    public static final int AC_WARM_HEAT_PUMP = 6;
    public static final int AC_WARM_INVALID = 0;
    public static final int AC_WARM_LOW_TENSION_PTC = 5;
    public static final int AC_WARM_PTC = 1;
    public static final int AC_WARM_PTCFUEL = 3;
    public static final int AC_WARM_TYPE_FLAG_FUEL = 2;
    public static final int AC_WARM_TYPE_FLAG_INVALID = 0;
    public static final int AC_WARM_TYPE_FLAG_PTC = 1;
    public static final int AC_WARM_TYPE_FLAG_PTCFUEL = 3;
    public static final int AC_WARM_WIND = 4;
    public static final int AC_WINDLEVEL_0 = 0;
    public static final int AC_WINDLEVEL_1 = 1;
    public static final int AC_WINDLEVEL_2 = 2;
    public static final int AC_WINDLEVEL_3 = 3;
    public static final int AC_WINDLEVEL_4 = 4;
    public static final int AC_WINDLEVEL_5 = 5;
    public static final int AC_WINDLEVEL_6 = 6;
    public static final int AC_WINDLEVEL_7 = 7;
    public static final int AC_WINDLEVEL_MANUAL_SIGN_OFF = 0;
    public static final int AC_WINDLEVEL_MANUAL_SIGN_ON = 1;
    public static final int AC_WINDMODE_DEFROST = 5;
    public static final int AC_WINDMODE_FACE = 1;
    public static final int AC_WINDMODE_FACEDEFROST = 7;
    public static final int AC_WINDMODE_FACEFOOT = 2;
    public static final int AC_WINDMODE_FACEFOOTDEFROST = 6;
    public static final int AC_WINDMODE_FOOT = 3;
    public static final int AC_WINDMODE_FOOTDEFROST = 4;
    public static final int AC_WINDMODE_MANUAL_SIGN_OFF = 0;
    public static final int AC_WINDMODE_MANUAL_SIGN_ON = 1;
    public static final int AC_WINDMODE_SHOWN_STATE_OFF = 0;
    public static final int AC_WINDMODE_SHOWN_STATE_ON = 1;
    public static final int AC_WIND_TOTAL_MODES_NUM_5 = 1;
    public static final int AC_WIND_TOTAL_MODES_NUM_7 = 2;
    public static final int AC_WIND_TOTAL_MODES_NUM_INVALID = 0;
    public static final int AC_WORK_MODE_DEPUTY = 1;
    public static final int AC_WORK_MODE_MAIN = 0;
    public static final int AC_WORK_MODE_MIDDLE_LEFT = 2;
    public static final int AC_WORK_MODE_MIDDLE_RIGHT = 3;
    public static final int AC_WORK_MODE_REAR = 4;
    public static final int AIR_OUTLET_DEPUTY = 1;
    public static final int AIR_OUTLET_DEPUTY_LEFT_LF = 5;
    public static final int AIR_OUTLET_DEPUTY_LEFT_UD = 4;
    public static final int AIR_OUTLET_DEPUTY_RIGHT_LF = 7;
    public static final int AIR_OUTLET_DEPUTY_RIGHT_UD = 6;
    public static final int AIR_OUTLET_MAIN = 0;
    public static final int AIR_OUTLET_MAIN_LEFT_LF = 1;
    public static final int AIR_OUTLET_MAIN_LEFT_UD = 0;
    public static final int AIR_OUTLET_MAIN_RIGHT_LF = 3;
    public static final int AIR_OUTLET_MAIN_RIGHT_UD = 2;
    public static final int AIR_OUTLET_ONLINE_STATE_OFF = 0;
    public static final int AIR_OUTLET_ONLINE_STATE_ON = 1;
    public static final int ANTIVIRUS_COUNT_DOWN_MAX = 70;
    public static final int ANTIVIRUS_COUNT_DOWN_MIN = 0;
    public static final int ANTIVIRUS_DO_NOT_NEED = 4;
    public static final int ANTIVIRUS_END = 2;
    public static final int ANTIVIRUS_EXIT_LOW_POWER = 6;
    public static final int ANTIVIRUS_INTERRUPT = 3;
    public static final int ANTIVIRUS_START = 1;
    public static final int ANTIVIRUS_START_FAIL = 5;
    public static final int AUTO_CLEAN_AIR_INVALID = 0;
    public static final int AUTO_CLEAN_AIR_OFF = 2;
    public static final int AUTO_CLEAN_AIR_ON = 1;
    public static final int AVOID_BLOW_PEOPLE_DEPUTY = 1;
    public static final int AVOID_BLOW_PEOPLE_MAIN = 0;
    public static final int BLOW_PEOPLE_DEPUTY = 1;
    public static final int BLOW_PEOPLE_MAIN = 0;
    public static final int COLD = 1;
    public static final int DEFROST_REAR_DEFAULT = 3;
    public static final int DEFROST_REAR_HAS_FUNC = 1;
    public static final int DEFROST_REAR_MIRROR_HEATING = 3;
    public static final int DEFROST_REAR_MIRROR_HEATING_HAS_FUNC = 2;
    public static final int DEFROST_REAR_NO_FUNC = 0;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int DEVICE_TYPE = 0;
    public static final int ELECTRIC_DEFROST_OFF = 0;
    public static final int ELECTRIC_DEFROST_ON = 1;
    public static final String FEATURE_AC_449_ONLINE = "449Online";
    public static final String FEATURE_AC_AUTO_MODE = "ACAutoMode";
    public static final String FEATURE_AC_DEFROST = "ACDefrost";
    public static final String FEATURE_AC_REAR_PANEL_3F1 = "ACRearPanel3f1";
    public static final String FEATURE_AC_REAR_PANEL_408 = "ACRearPanel408";
    public static final String FEATURE_AC_REMOTE_CTL = "ACRemoteControl";
    public static final String FEATURE_AC_WARM_SETTING = "WarmSetting";
    public static final int FREE_WIND_BTN_DEPUTY = 1;
    public static final int FREE_WIND_BTN_MAIN = 0;
    public static final int GET_QUICK_CLEAN_BEGIN = 1;
    public static final int GET_QUICK_CLEAN_END = 2;
    public static final int GET_QUICK_CLEAN_TERMINATE = 3;
    public static final int HEAT_PUMP_MODE_COOLING = 2;
    public static final int HEAT_PUMP_MODE_HEATING = 3;
    public static final int HEAT_PUMP_MODE_INVALID = 0;
    public static final int HEAT_PUMP_MODE_NO_COMPRESSOR = 1;
    public static final int HOT = 0;
    public static final int OTA_SUB_BATTERY_TEMPRATURE_INVALID = 65535;
    public static final int OTA_SUB_BATTERY_TEMPRATURE_MAX = 160;
    public static final int OTA_SUB_BATTERY_TEMPRATURE_MIN = -60;
    public static final String PERMISSION_BYDAUTO_AC_COMMON = "android.permission.BYDAUTO_AC_COMMON";
    public static final String PERMISSION_BYDAUTO_AC_GET = "android.permission.BYDAUTO_AC_GET";
    public static final String PERMISSION_BYDAUTO_AC_SET = "android.permission.BYDAUTO_AC_SET";
    public static final int PURIFICATION_FUNCTION_PROMPT_ENABLE = 1;
    public static final int QUICK_CLEAN_3DA_OFFLINE = 0;
    public static final int QUICK_CLEAN_3DA_ONLINE = 1;
    public static final int QUICK_CLEAN_INVALID = 0;
    public static final int REAR_AC = 2;
    public static final int REAR_AC_KEY_LOCK_OFF = 2;
    public static final int REAR_AC_KEY_LOCK_ON = 1;
    public static final int REAR_AC_MAX_WINDLEVEL_4 = 1;
    public static final int REAR_AC_MAX_WINDLEVEL_7 = 2;
    public static final int RESET_INVALID = 0;
    public static final int RESET_ITEM_AC = 0;
    public static final int RESET_VALID = 1;
    public static final int SET_CLEAN_INVALID = 0;
    public static final int SET_CLEAN_OPEN = 1;
    public static final int SET_QUICK_CLEAN_CLOSE2 = 2;

    @Deprecated
    public static final int SET_QUICK_CLEAN_CLOSE3 = 3;
    public static final int SET_QUICK_CLEAN_OPEN = 1;
    public static final int SWEEP_BLOW_DEPUTY = 1;
    public static final int SWEEP_BLOW_MAIN = 0;
    protected static final String TAG = "BYDAutoAcDevice";
    public static final int TIP_AUTO_CLEAN = 2;
    public static final int TIP_FLAG_INVALID = 0;
    public static final int TIP_QUICK_CLEAN = 1;
    public static final int VOICE_AC_ALREADY_IN_MAX_TEMP = 5;
    public static final int VOICE_AC_ALREADY_IN_MIN_TEMP = 4;
    public static final int VOICE_CMD_INVALID = 0;
    public static final int VOICE_SET_INVALID_TEMP = 2;
    public static final int VOICE_SET_INVALID_WIND_LEVEL = 3;
    public static final int VOICE_START_AC_SUCCESS = 1;
    private static BYDAutoAcDevice sDevice;
    private final ACManagerImpl mAcManagerImpl;

    BYDAutoAcDevice(Context con) {
        super(con.getApplicationContext());
        this.mAcManagerImpl = ACManagerImpl.getsInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoAcDevice getInstance(Context con) {
        if (sDevice == null) {
            sDevice = new BYDAutoAcDevice(con);
        }
        return sDevice;
    }

    public int start(int setSource) {
        return this.mAcManagerImpl.start(setSource);
    }

    public int stop(int setSource) {
        return this.mAcManagerImpl.stop(setSource);
    }

    public int getAcStartState() {
        return this.mAcManagerImpl.getAcStartState();
    }

    public int startRearAc(int setSource) {
        return this.mAcManagerImpl.startRearAc(setSource);
    }

    public int stopRearAc(int setSource) {
        return this.mAcManagerImpl.stopRearAc(setSource);
    }

    public int getRearAcStartState() {
        return this.mAcManagerImpl.getRearAcStartState();
    }

    public int setAcControlMode(int setSource, int mode) {
        return this.mAcManagerImpl.setAcControlMode(setSource, mode);
    }

    public int getAcControlMode() {
        return this.mAcManagerImpl.getAcControlMode();
    }

    public int setRearAcControlMode(int setSource, int mode) {
        return this.mAcManagerImpl.setRearAcControlMode(setSource, mode);
    }

    public int getRearAcControlMode() {
        return this.mAcManagerImpl.getRearAcControlMode();
    }

    public int setAcCycleMode(int setSource, int mode) {
        return this.mAcManagerImpl.setAcCycleMode(setSource, mode);
    }

    public int getAcCycleMode() {
        return this.mAcManagerImpl.getAcCycleMode();
    }

    public int setAcVentilationState(int setSource, int state) {
        return this.mAcManagerImpl.setAcVentilationState(setSource, state);
    }

    public int getAcVentilationState() {
        return this.mAcManagerImpl.getAcVentilationState();
    }

    public int setAcTemperatureControlMode(int setSource, int mode) {
        return this.mAcManagerImpl.setAcTemperatureControlMode(setSource, mode);
    }

    public int getAcTemperatureControlMode() {
        return this.mAcManagerImpl.getAcTemperatureControlMode();
    }

    public int setAcDefrostState(int setSource, int area, int state) {
        return this.mAcManagerImpl.setAcDefrostState(setSource, area, state);
    }

    public int getAcDefrostState(int area) {
        return this.mAcManagerImpl.getAcDefrostState(area);
    }

    public int setAcMaxCoolingState(int state) {
        return this.mAcManagerImpl.setAcMaxCoolingState(state);
    }

    public int getAcMaxCoolingState() {
        return this.mAcManagerImpl.getAcMaxCoolingState();
    }

    public int getAcCompressorManualSign() {
        return this.mAcManagerImpl.getAcCompressorManualSign();
    }

    public int setAcCompressorMode(int setSource, int state) {
        return this.mAcManagerImpl.setAcCompressorMode(setSource, state);
    }

    public int getAcCompressorMode() {
        return this.mAcManagerImpl.getAcCompressorMode();
    }

    public int getAcWindModeManualSign() {
        return this.mAcManagerImpl.getAcWindModeManualSign();
    }

    public int setAcWindMode(int setSource, int mode) {
        return this.mAcManagerImpl.setAcWindMode(setSource, mode);
    }

    public int getAcWindMode() {
        return this.mAcManagerImpl.getAcWindMode();
    }

    public int setRearAcWindMode(int setSource, int mode) {
        return this.mAcManagerImpl.setRearAcWindMode(setSource, mode);
    }

    public int getRearAcWindMode() {
        return this.mAcManagerImpl.getRearAcWindMode();
    }

    public int getAcWindLevelManualSign() {
        return this.mAcManagerImpl.getAcWindLevelManualSign();
    }

    public int setAcWindLevel(int setSource, int level) {
        return this.mAcManagerImpl.setAcWindLevel(setSource, level);
    }

    public int getAcWindLevel() {
        return this.mAcManagerImpl.getAcWindLevel();
    }

    public int setRearAcWindLevel(int setSource, int level) {
        return this.mAcManagerImpl.setRearAcWindLevel(setSource, level);
    }

    public int getRearAcWindLevel() {
        return this.mAcManagerImpl.getRearAcWindLevel();
    }

    public int getTemperatureUnit() {
        return this.mAcManagerImpl.getTemperatureUnit();
    }

    public int setAcTemperature(int type, int value, int tempSource, int unit) {
        return this.mAcManagerImpl.setAcTemperature(type, value, tempSource, unit);
    }

    public int getTemprature(int area) {
        return this.mAcManagerImpl.getTemprature(area);
    }

    public int getAcRearPanelLockState() {
        return this.mAcManagerImpl.getAcRearPanelLockState();
    }

    public int setAcRearPanelLockState(int state) {
        return this.mAcManagerImpl.setAcRearPanelLockState(state);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mAcManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mAcManagerImpl.set(eventTypes, eventValue);
    }

    public void registerListener(AbsBYDAutoAcListener listener) {
        this.mAcManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoAcListener listener, int[] featureIds) {
        this.mAcManagerImpl.registerListener(listener, featureIds);
    }

    public void unregisterListener(AbsBYDAutoAcListener listener) {
        this.mAcManagerImpl.unregisterListener(listener);
    }

    public int setRearAcKeyLockState(int state) {
        return this.mAcManagerImpl.setRearAcKeyLockState(state);
    }

    public int getMaxAcState() {
        return this.mAcManagerImpl.getMaxAcState();
    }

    public int setMaxAcState(int state) {
        return this.mAcManagerImpl.setMaxAcState(state);
    }

    @Deprecated
    public int getElectricDefrostState() {
        return this.mAcManagerImpl.getElectricDefrostState();
    }

    @Deprecated
    public int setElectricDefrostState(int state) {
        return this.mAcManagerImpl.setElectricDefrostState(state);
    }

    public int getRearACkeyLockState() {
        return this.mAcManagerImpl.getRearACkeyLockState();
    }

    public int getACSystemState() {
        return this.mAcManagerImpl.getACSystemState();
    }

    public int getACPreheatState() {
        return this.mAcManagerImpl.getACPreheatState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mAcManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "android.permission.BYDAUTO_AC_SET";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "android.permission.BYDAUTO_AC_GET";
    }

    public int getAcOnlineState() {
        return this.mAcManagerImpl.getAcOnlineState();
    }

    public int setAcWarmState(int state) {
        return this.mAcManagerImpl.setAcWarmState(state);
    }

    public int getAcWarmState() {
        return this.mAcManagerImpl.getAcWarmState();
    }

    public int getAcWarmTypeOnlineState() {
        return this.mAcManagerImpl.getAcWarmTypeOnlineState();
    }

    public int getAcPromptBoxShownState() {
        return this.mAcManagerImpl.getAcPromptBoxShownState();
    }

    public int getAcFaultNumShownState() {
        return this.mAcManagerImpl.getAcFaultNumShownState();
    }

    public int getAcWindModeShownState() {
        return this.mAcManagerImpl.getAcWindModeShownState();
    }

    public int getAirQualityCtrlMenuState() {
        return this.mAcManagerImpl.getAirQualityCtrlMenuState();
    }

    public int getAcKeyActionState() {
        return this.mAcManagerImpl.getAcKeyActionState();
    }

    public int setRearAcLockState(int state) {
        return this.mAcManagerImpl.setRearAcLockState(state);
    }

    public int getRearAcLockState() {
        return this.mAcManagerImpl.getRearAcLockState();
    }

    public int feelColdHot(int whichAC, int signal) {
        return this.mAcManagerImpl.feelColdHot(whichAC, signal);
    }

    public int getVoiceCmdResult() {
        return this.mAcManagerImpl.getVoiceCmdResult();
    }

    public int hasFeature(String feature) {
        return this.mAcManagerImpl.hasFeature(feature);
    }

    public int enablePurificationFunctionPrompt(int enable) {
        return this.mAcManagerImpl.enablePurificationFunctionPrompt(enable);
    }

    public int getRearAcMaxWindLevel() {
        return this.mAcManagerImpl.getRearAcMaxWindLevel();
    }

    public int getAcRemoteCtrlTime() {
        return this.mAcManagerImpl.getAcRemoteCtrlTime();
    }

    public int setAcRemoteCtrlTime(int value) {
        return this.mAcManagerImpl.setAcRemoteCtrlTime(value);
    }

    public int getAcType() {
        return this.mAcManagerImpl.getAcType();
    }

    public int getAcPtcPreheatSignal() {
        return this.mAcManagerImpl.getAcPtcPreheatSignal();
    }

    public int reset(int item, int value) {
        return this.mAcManagerImpl.reset(item, value);
    }

    public int getAcDefrostOnlineState() {
        return this.mAcManagerImpl.getAcDefrostOnlineState();
    }

    public int getAcSubBatteryTemperature() {
        return this.mAcManagerImpl.getAcSubBatteryTemperature();
    }

    public int getAcRearPaneNotAvailableMenuState() {
        return this.mAcManagerImpl.getAcRearPaneNotAvailableMenuState();
    }

    public int setQuickCleanAirState(int state) {
        return this.mAcManagerImpl.setQuickCleanAirState(state);
    }

    public int getQuickCleanAirState() {
        return this.mAcManagerImpl.getQuickCleanAirState();
    }

    public int getQuickCleanTip() {
        return this.mAcManagerImpl.getQuickCleanTip();
    }

    public int confirmCleanAirState(int state) {
        return this.mAcManagerImpl.confirmCleanAirState(state);
    }

    public int getAcWindModeNum() {
        return this.mAcManagerImpl.getAcWindModeNum();
    }

    public int get3daOnlineState() {
        return this.mAcManagerImpl.get3daOnlineState();
    }

    public int setAutoCleanAirState(int state) {
        return this.mAcManagerImpl.setAutoCleanAirState(state);
    }

    public int getAutoCleanAirState() {
        return this.mAcManagerImpl.getAutoCleanAirState();
    }

    public int getHighTempAntivirusState() {
        return this.mAcManagerImpl.getHighTempAntivirusState();
    }

    public int getHighTempAntivirusCountDown() {
        return this.mAcManagerImpl.getHighTempAntivirusCountDown();
    }

    public int getAcPanelLockPromptState() {
        return this.mAcManagerImpl.getAcPanelLockPromptState();
    }

    public int qurAcAllStatus() {
        return this.mAcManagerImpl.qurAcAllStatus();
    }

    public int getDefrostRearConfig() {
        return this.mAcManagerImpl.getDefrostRearConfig();
    }

    public void setAllStatus() {
        this.mAcManagerImpl.setAllStatus();
    }

    public void getAllStatus() {
        this.mAcManagerImpl.getAllStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int deviceType, int eventType, int value, Object data) {
        return false;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mAcManagerImpl.getType();
    }

    public int[] getFeatrueList() {
        return this.mAcManagerImpl.getFeatrueList();
    }

    public float getAirOutletValue(int area) {
        return this.mAcManagerImpl.getAirOutletValue(area);
    }

    public int getFreeWindBTNState(int area) {
        return this.mAcManagerImpl.getFreeWindBtnState(area);
    }

    public int getBlowPeopleState(int area) {
        return this.mAcManagerImpl.getBlowPeopleState(area);
    }

    public int getAvoidBlowPeopleState(int area) {
        return this.mAcManagerImpl.getAvoidBlowPeopleState(area);
    }

    public int getSweepBlowState(int area) {
        return this.mAcManagerImpl.getSweepBlowState(area);
    }

    public int getAirOutletState(int area) {
        return this.mAcManagerImpl.getAirOutletState(area);
    }

    public int getIntelligentControlState() {
        return this.mAcManagerImpl.getIntelligentControlState();
    }

    public int getFrontRowAirOutletState() {
        return this.mAcManagerImpl.getFrontRowAirOutletState();
    }

    public int getAirOutleOnlineState() {
        return this.mAcManagerImpl.getAirOutleOnlineState();
    }

    public int getACWorkMode(int area) {
        return this.mAcManagerImpl.getAcWorkMode(area);
    }

    public int setAirOutletValue(int area, int value) {
        return this.mAcManagerImpl.setAirOutletValue(area, value);
    }

    public int setBlowPeopleState(int area, int value) {
        return this.mAcManagerImpl.setBlowPeopleState(area, value);
    }

    public int setAvoidBlowPeopleState(int area, int value) {
        return this.mAcManagerImpl.setAvoidBlowPeopleState(area, value);
    }

    public int setSweepBlowState(int area, int value) {
        return this.mAcManagerImpl.setSweepBlowState(area, value);
    }

    public int setFreeWindBTNState(int area, int value) {
        return this.mAcManagerImpl.setFreeWindBtnState(area, value);
    }

    public int setAirOutletState(int area, int value) {
        return this.mAcManagerImpl.setAirOutletState(area, value);
    }

    public int setIntelligentControlState(int state) {
        return this.mAcManagerImpl.setIntelligentControlState(state);
    }

    public int setFrontRowAirOutletState(int state) {
        return this.mAcManagerImpl.setFrontRowAirOutletState(state);
    }

    public int setConstantTemperature(int type) {
        return this.mAcManagerImpl.setConstantTemperature(type);
    }

    public int getHeatPumpCompressorWorkMode() {
        return this.mAcManagerImpl.getHeatPumpCompressorWorkMode();
    }

    public int setACAntibacterialControlState(int state) {
        return this.mAcManagerImpl.setAcAntibacterialControlState(state);
    }

    public int getACAssistModeTipsState() {
        return this.mAcManagerImpl.getAcAssistModeTipsState();
    }

    public int getACKeepEngineRunTipsState() {
        return this.mAcManagerImpl.getAcKeepEngineRunTipsState();
    }

    public int getACBTWindTipsState() {
        return this.mAcManagerImpl.getAcbtWindTipsState();
    }

    public int getACDefrostRearPanelNotAvailableTipsState() {
        return this.mAcManagerImpl.getAcDefrostRearPanelNotAvailableTipsState();
    }

    public int getAcHeatingButtonConfig() {
        return this.mAcManagerImpl.getAcHeatingButtonConfig();
    }

    public int getAcHeatingState() {
        return this.mAcManagerImpl.getAcHeatingState();
    }

    public int setAcHeatingState(int state) {
        return this.mAcManagerImpl.setAcHeatingState(state);
    }

    public int getTempRegulatingAccuracy() {
        return this.mAcManagerImpl.getTempRegulatingAccuracy();
    }

    public int getDriverSeatVentilating2e4Config() {
        return this.mAcManagerImpl.getDriverSeatVentilating2e4Config();
    }

    public int getDriverSeatVentilating2aaConfig() {
        return this.mAcManagerImpl.getDriverSeatVentilating2aaConfig();
    }

    public int getDriverSeatHeating2e4Config() {
        return this.mAcManagerImpl.getDriverSeatHeating2e4Config();
    }

    public int getDriverSeatHeating2aaConfig() {
        return this.mAcManagerImpl.getDriverSeatHeating2aaConfig();
    }

    public int getPassengerSeatVentilating41cConfig() {
        return this.mAcManagerImpl.getPassengerSeatVentilating41cConfig();
    }

    public int getPassengerSeatVentilating2aaConfig() {
        return this.mAcManagerImpl.getPassengerSeatVentilating2aaConfig();
    }

    public int getPassengerSeatVentilating2ffConfig() {
        return this.mAcManagerImpl.getPassengerSeatVentilating2ffConfig();
    }

    public int getPassengerSeatHeating41cConfig() {
        return this.mAcManagerImpl.getPassengerSeatHeating41cConfig();
    }

    public int getPassengerSeatHeating2aaConfig() {
        return this.mAcManagerImpl.getPassengerSeatHeating2aaConfig();
    }

    public int getPassengerSeatHeating2ffConfig() {
        return this.mAcManagerImpl.getPassengerSeatHeating2ffConfig();
    }

    public int getLrSeatVentilating2aaConfig() {
        return this.mAcManagerImpl.getLrSeatVentilating2aaConfig();
    }

    public int getLrSeatVentilating35dConfig() {
        return this.mAcManagerImpl.getLrSeatVentilating35dConfig();
    }

    public int getLrSeatVentilating349Config() {
        return this.mAcManagerImpl.getLrSeatVentilating349Config();
    }

    public int getLrSeatHeating2aaConfig() {
        return this.mAcManagerImpl.getLrSeatHeating2aaConfig();
    }

    public int getLrSeatHeating35dConfig() {
        return this.mAcManagerImpl.getLrSeatHeating35dConfig();
    }

    public int getLrSeatHeating349Config() {
        return this.mAcManagerImpl.getLrSeatHeating349Config();
    }

    public int getRrSeatVentilating363Config() {
        return this.mAcManagerImpl.getRrSeatVentilating363Config();
    }

    public int getRrSeatVentilating2aaConfig() {
        return this.mAcManagerImpl.getRrSeatVentilating2aaConfig();
    }

    public int getRrSeatVentilating349Config() {
        return this.mAcManagerImpl.getRrSeatVentilating349Config();
    }

    public int getRrSeatVentilating21dConfig() {
        return this.mAcManagerImpl.getRrSeatVentilating21dConfig();
    }

    public int getRrSeatHeating363Config() {
        return this.mAcManagerImpl.getRrSeatHeating363Config();
    }

    public int getRrSeatHeating2aaConfig() {
        return this.mAcManagerImpl.getRrSeatHeating2aaConfig();
    }

    public int getRrSeatHeating349Config() {
        return this.mAcManagerImpl.getRrSeatHeating349Config();
    }

    public int getRrSeatHeating21dConfig() {
        return this.mAcManagerImpl.getRrSeatHeating21dConfig();
    }

    public int getACWorkCommand() {
        return this.mAcManagerImpl.getAcWorkCommand();
    }

    public int getAcTuyereSwitchState(int area) {
        return this.mAcManagerImpl.getAcTuyereSwitchState(area);
    }

    public int setAcTuyereSwitchState(int area, int state) {
        return this.mAcManagerImpl.setAcTuyereSwitchState(area, state);
    }

    public int getAcAQSConfig() {
        return this.mAcManagerImpl.getAcAqsConfig();
    }

    public int getAcCockpitPurificationStatus() {
        return this.mAcManagerImpl.getAcCockpitPurificationStatus();
    }
}
