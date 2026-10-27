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
    public static final String TAG = "BYDAutoAcDevice";
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

    public BYDAutoAcDevice(Context context) {
        super(context.getApplicationContext());
        this.mAcManagerImpl = ACManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoAcDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoAcDevice(context);
        }
        return sDevice;
    }

    public int confirmCleanAirState(int i8) {
        return this.mAcManagerImpl.confirmCleanAirState(i8);
    }

    public int enablePurificationFunctionPrompt(int i8) {
        return this.mAcManagerImpl.enablePurificationFunctionPrompt(i8);
    }

    public int feelColdHot(int i8, int i9) {
        return this.mAcManagerImpl.feelColdHot(i8, i9);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mAcManagerImpl.get(iArr, cls);
    }

    public int get3daOnlineState() {
        return this.mAcManagerImpl.get3daOnlineState();
    }

    public int getACAssistModeTipsState() {
        return this.mAcManagerImpl.getAcAssistModeTipsState();
    }

    public int getACBTWindTipsState() {
        return this.mAcManagerImpl.getAcbtWindTipsState();
    }

    public int getACDefrostRearPanelNotAvailableTipsState() {
        return this.mAcManagerImpl.getAcDefrostRearPanelNotAvailableTipsState();
    }

    public int getACKeepEngineRunTipsState() {
        return this.mAcManagerImpl.getAcKeepEngineRunTipsState();
    }

    public int getACPreheatState() {
        return this.mAcManagerImpl.getACPreheatState();
    }

    public int getACSystemState() {
        return this.mAcManagerImpl.getACSystemState();
    }

    public int getACWorkCommand() {
        return this.mAcManagerImpl.getAcWorkCommand();
    }

    public int getACWorkMode(int i8) {
        return this.mAcManagerImpl.getAcWorkMode(i8);
    }

    public int getAcAQSConfig() {
        return this.mAcManagerImpl.getAcAqsConfig();
    }

    public int getAcCockpitPurificationStatus() {
        return this.mAcManagerImpl.getAcCockpitPurificationStatus();
    }

    public int getAcCompressorManualSign() {
        return this.mAcManagerImpl.getAcCompressorManualSign();
    }

    public int getAcCompressorMode() {
        return this.mAcManagerImpl.getAcCompressorMode();
    }

    public int getAcControlMode() {
        return this.mAcManagerImpl.getAcControlMode();
    }

    public int getAcCycleMode() {
        return this.mAcManagerImpl.getAcCycleMode();
    }

    public int getAcDefrostOnlineState() {
        return this.mAcManagerImpl.getAcDefrostOnlineState();
    }

    public int getAcDefrostState(int i8) {
        return this.mAcManagerImpl.getAcDefrostState(i8);
    }

    public int getAcFaultNumShownState() {
        return this.mAcManagerImpl.getAcFaultNumShownState();
    }

    public int getAcHeatingButtonConfig() {
        return this.mAcManagerImpl.getAcHeatingButtonConfig();
    }

    public int getAcHeatingState() {
        return this.mAcManagerImpl.getAcHeatingState();
    }

    public int getAcKeyActionState() {
        return this.mAcManagerImpl.getAcKeyActionState();
    }

    public int getAcMaxCoolingState() {
        return this.mAcManagerImpl.getAcMaxCoolingState();
    }

    public int getAcOnlineState() {
        return this.mAcManagerImpl.getAcOnlineState();
    }

    public int getAcPanelLockPromptState() {
        return this.mAcManagerImpl.getAcPanelLockPromptState();
    }

    public int getAcPromptBoxShownState() {
        return this.mAcManagerImpl.getAcPromptBoxShownState();
    }

    public int getAcPtcPreheatSignal() {
        return this.mAcManagerImpl.getAcPtcPreheatSignal();
    }

    public int getAcRearPaneNotAvailableMenuState() {
        return this.mAcManagerImpl.getAcRearPaneNotAvailableMenuState();
    }

    public int getAcRearPanelLockState() {
        return this.mAcManagerImpl.getAcRearPanelLockState();
    }

    public int getAcRemoteCtrlTime() {
        return this.mAcManagerImpl.getAcRemoteCtrlTime();
    }

    public int getAcStartState() {
        return this.mAcManagerImpl.getAcStartState();
    }

    public int getAcSubBatteryTemperature() {
        return this.mAcManagerImpl.getAcSubBatteryTemperature();
    }

    public int getAcTemperatureControlMode() {
        return this.mAcManagerImpl.getAcTemperatureControlMode();
    }

    public int getAcTuyereSwitchState(int i8) {
        return this.mAcManagerImpl.getAcTuyereSwitchState(i8);
    }

    public int getAcType() {
        return this.mAcManagerImpl.getAcType();
    }

    public int getAcVentilationState() {
        return this.mAcManagerImpl.getAcVentilationState();
    }

    public int getAcWarmState() {
        return this.mAcManagerImpl.getAcWarmState();
    }

    public int getAcWarmTypeOnlineState() {
        return this.mAcManagerImpl.getAcWarmTypeOnlineState();
    }

    public int getAcWindLevel() {
        return this.mAcManagerImpl.getAcWindLevel();
    }

    public int getAcWindLevelManualSign() {
        return this.mAcManagerImpl.getAcWindLevelManualSign();
    }

    public int getAcWindMode() {
        return this.mAcManagerImpl.getAcWindMode();
    }

    public int getAcWindModeManualSign() {
        return this.mAcManagerImpl.getAcWindModeManualSign();
    }

    public int getAcWindModeNum() {
        return this.mAcManagerImpl.getAcWindModeNum();
    }

    public int getAcWindModeShownState() {
        return this.mAcManagerImpl.getAcWindModeShownState();
    }

    public int getAirOutleOnlineState() {
        return this.mAcManagerImpl.getAirOutleOnlineState();
    }

    public int getAirOutletState(int i8) {
        return this.mAcManagerImpl.getAirOutletState(i8);
    }

    public float getAirOutletValue(int i8) {
        return this.mAcManagerImpl.getAirOutletValue(i8);
    }

    public int getAirQualityCtrlMenuState() {
        return this.mAcManagerImpl.getAirQualityCtrlMenuState();
    }

    public void getAllStatus() {
        this.mAcManagerImpl.getAllStatus();
    }

    public int getAutoCleanAirState() {
        return this.mAcManagerImpl.getAutoCleanAirState();
    }

    public int getAvoidBlowPeopleState(int i8) {
        return this.mAcManagerImpl.getAvoidBlowPeopleState(i8);
    }

    public int getBlowPeopleState(int i8) {
        return this.mAcManagerImpl.getBlowPeopleState(i8);
    }

    public int getDefrostRearConfig() {
        return this.mAcManagerImpl.getDefrostRearConfig();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mAcManagerImpl.getDevicetype();
    }

    public int getDriverSeatHeating2aaConfig() {
        return this.mAcManagerImpl.getDriverSeatHeating2aaConfig();
    }

    public int getDriverSeatHeating2e4Config() {
        return this.mAcManagerImpl.getDriverSeatHeating2e4Config();
    }

    public int getDriverSeatVentilating2aaConfig() {
        return this.mAcManagerImpl.getDriverSeatVentilating2aaConfig();
    }

    public int getDriverSeatVentilating2e4Config() {
        return this.mAcManagerImpl.getDriverSeatVentilating2e4Config();
    }

    @Deprecated
    public int getElectricDefrostState() {
        return this.mAcManagerImpl.getElectricDefrostState();
    }

    public int[] getFeatrueList() {
        return this.mAcManagerImpl.getFeatrueList();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public int getFreeWindBTNState(int i8) {
        return this.mAcManagerImpl.getFreeWindBtnState(i8);
    }

    public int getFrontRowAirOutletState() {
        return this.mAcManagerImpl.getFrontRowAirOutletState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "android.permission.BYDAUTO_AC_GET";
    }

    public int getHeatPumpCompressorWorkMode() {
        return this.mAcManagerImpl.getHeatPumpCompressorWorkMode();
    }

    public int getHighTempAntivirusCountDown() {
        return this.mAcManagerImpl.getHighTempAntivirusCountDown();
    }

    public int getHighTempAntivirusState() {
        return this.mAcManagerImpl.getHighTempAntivirusState();
    }

    public int getIntelligentControlState() {
        return this.mAcManagerImpl.getIntelligentControlState();
    }

    public int getLrSeatHeating2aaConfig() {
        return this.mAcManagerImpl.getLrSeatHeating2aaConfig();
    }

    public int getLrSeatHeating349Config() {
        return this.mAcManagerImpl.getLrSeatHeating349Config();
    }

    public int getLrSeatHeating35dConfig() {
        return this.mAcManagerImpl.getLrSeatHeating35dConfig();
    }

    public int getLrSeatVentilating2aaConfig() {
        return this.mAcManagerImpl.getLrSeatVentilating2aaConfig();
    }

    public int getLrSeatVentilating349Config() {
        return this.mAcManagerImpl.getLrSeatVentilating349Config();
    }

    public int getLrSeatVentilating35dConfig() {
        return this.mAcManagerImpl.getLrSeatVentilating35dConfig();
    }

    public int getMaxAcState() {
        return this.mAcManagerImpl.getMaxAcState();
    }

    public int getPassengerSeatHeating2aaConfig() {
        return this.mAcManagerImpl.getPassengerSeatHeating2aaConfig();
    }

    public int getPassengerSeatHeating2ffConfig() {
        return this.mAcManagerImpl.getPassengerSeatHeating2ffConfig();
    }

    public int getPassengerSeatHeating41cConfig() {
        return this.mAcManagerImpl.getPassengerSeatHeating41cConfig();
    }

    public int getPassengerSeatVentilating2aaConfig() {
        return this.mAcManagerImpl.getPassengerSeatVentilating2aaConfig();
    }

    public int getPassengerSeatVentilating2ffConfig() {
        return this.mAcManagerImpl.getPassengerSeatVentilating2ffConfig();
    }

    public int getPassengerSeatVentilating41cConfig() {
        return this.mAcManagerImpl.getPassengerSeatVentilating41cConfig();
    }

    public int getQuickCleanAirState() {
        return this.mAcManagerImpl.getQuickCleanAirState();
    }

    public int getQuickCleanTip() {
        return this.mAcManagerImpl.getQuickCleanTip();
    }

    public int getRearACkeyLockState() {
        return this.mAcManagerImpl.getRearACkeyLockState();
    }

    public int getRearAcControlMode() {
        return this.mAcManagerImpl.getRearAcControlMode();
    }

    public int getRearAcLockState() {
        return this.mAcManagerImpl.getRearAcLockState();
    }

    public int getRearAcMaxWindLevel() {
        return this.mAcManagerImpl.getRearAcMaxWindLevel();
    }

    public int getRearAcStartState() {
        return this.mAcManagerImpl.getRearAcStartState();
    }

    public int getRearAcWindLevel() {
        return this.mAcManagerImpl.getRearAcWindLevel();
    }

    public int getRearAcWindMode() {
        return this.mAcManagerImpl.getRearAcWindMode();
    }

    public int getRrSeatHeating21dConfig() {
        return this.mAcManagerImpl.getRrSeatHeating21dConfig();
    }

    public int getRrSeatHeating2aaConfig() {
        return this.mAcManagerImpl.getRrSeatHeating2aaConfig();
    }

    public int getRrSeatHeating349Config() {
        return this.mAcManagerImpl.getRrSeatHeating349Config();
    }

    public int getRrSeatHeating363Config() {
        return this.mAcManagerImpl.getRrSeatHeating363Config();
    }

    public int getRrSeatVentilating21dConfig() {
        return this.mAcManagerImpl.getRrSeatVentilating21dConfig();
    }

    public int getRrSeatVentilating2aaConfig() {
        return this.mAcManagerImpl.getRrSeatVentilating2aaConfig();
    }

    public int getRrSeatVentilating349Config() {
        return this.mAcManagerImpl.getRrSeatVentilating349Config();
    }

    public int getRrSeatVentilating363Config() {
        return this.mAcManagerImpl.getRrSeatVentilating363Config();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "android.permission.BYDAUTO_AC_SET";
    }

    public int getSweepBlowState(int i8) {
        return this.mAcManagerImpl.getSweepBlowState(i8);
    }

    public int getTempRegulatingAccuracy() {
        return this.mAcManagerImpl.getTempRegulatingAccuracy();
    }

    public int getTemperatureUnit() {
        return this.mAcManagerImpl.getTemperatureUnit();
    }

    public int getTemprature(int i8) {
        return this.mAcManagerImpl.getTemprature(i8);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return this.mAcManagerImpl.getType();
    }

    public int getVoiceCmdResult() {
        return this.mAcManagerImpl.getVoiceCmdResult();
    }

    public int hasFeature(String str) {
        return this.mAcManagerImpl.hasFeature(str);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i8, int i9, int i10, Object obj) {
        return false;
    }

    public int qurAcAllStatus() {
        return this.mAcManagerImpl.qurAcAllStatus();
    }

    public void registerListener(AbsBYDAutoAcListener absBYDAutoAcListener) {
        this.mAcManagerImpl.registerListener(absBYDAutoAcListener);
    }

    public int reset(int i8, int i9) {
        return this.mAcManagerImpl.reset(i8, i9);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mAcManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int setACAntibacterialControlState(int i8) {
        return this.mAcManagerImpl.setAcAntibacterialControlState(i8);
    }

    public int setAcCompressorMode(int i8, int i9) {
        return this.mAcManagerImpl.setAcCompressorMode(i8, i9);
    }

    public int setAcControlMode(int i8, int i9) {
        return this.mAcManagerImpl.setAcControlMode(i8, i9);
    }

    public int setAcCycleMode(int i8, int i9) {
        return this.mAcManagerImpl.setAcCycleMode(i8, i9);
    }

    public int setAcDefrostState(int i8, int i9, int i10) {
        return this.mAcManagerImpl.setAcDefrostState(i8, i9, i10);
    }

    public int setAcHeatingState(int i8) {
        return this.mAcManagerImpl.setAcHeatingState(i8);
    }

    public int setAcMaxCoolingState(int i8) {
        return this.mAcManagerImpl.setAcMaxCoolingState(i8);
    }

    public int setAcRearPanelLockState(int i8) {
        return this.mAcManagerImpl.setAcRearPanelLockState(i8);
    }

    public int setAcRemoteCtrlTime(int i8) {
        return this.mAcManagerImpl.setAcRemoteCtrlTime(i8);
    }

    public int setAcTemperature(int i8, int i9, int i10, int i11) {
        return this.mAcManagerImpl.setAcTemperature(i8, i9, i10, i11);
    }

    public int setAcTemperatureControlMode(int i8, int i9) {
        return this.mAcManagerImpl.setAcTemperatureControlMode(i8, i9);
    }

    public int setAcTuyereSwitchState(int i8, int i9) {
        return this.mAcManagerImpl.setAcTuyereSwitchState(i8, i9);
    }

    public int setAcVentilationState(int i8, int i9) {
        return this.mAcManagerImpl.setAcVentilationState(i8, i9);
    }

    public int setAcWarmState(int i8) {
        return this.mAcManagerImpl.setAcWarmState(i8);
    }

    public int setAcWindLevel(int i8, int i9) {
        return this.mAcManagerImpl.setAcWindLevel(i8, i9);
    }

    public int setAcWindMode(int i8, int i9) {
        return this.mAcManagerImpl.setAcWindMode(i8, i9);
    }

    public int setAirOutletState(int i8, int i9) {
        return this.mAcManagerImpl.setAirOutletState(i8, i9);
    }

    public int setAirOutletValue(int i8, int i9) {
        return this.mAcManagerImpl.setAirOutletValue(i8, i9);
    }

    public void setAllStatus() {
        this.mAcManagerImpl.setAllStatus();
    }

    public int setAutoCleanAirState(int i8) {
        return this.mAcManagerImpl.setAutoCleanAirState(i8);
    }

    public int setAvoidBlowPeopleState(int i8, int i9) {
        return this.mAcManagerImpl.setAvoidBlowPeopleState(i8, i9);
    }

    public int setBlowPeopleState(int i8, int i9) {
        return this.mAcManagerImpl.setBlowPeopleState(i8, i9);
    }

    public int setConstantTemperature(int i8) {
        return this.mAcManagerImpl.setConstantTemperature(i8);
    }

    @Deprecated
    public int setElectricDefrostState(int i8) {
        return this.mAcManagerImpl.setElectricDefrostState(i8);
    }

    public int setFreeWindBTNState(int i8, int i9) {
        return this.mAcManagerImpl.setFreeWindBtnState(i8, i9);
    }

    public int setFrontRowAirOutletState(int i8) {
        return this.mAcManagerImpl.setFrontRowAirOutletState(i8);
    }

    public int setIntelligentControlState(int i8) {
        return this.mAcManagerImpl.setIntelligentControlState(i8);
    }

    public int setMaxAcState(int i8) {
        return this.mAcManagerImpl.setMaxAcState(i8);
    }

    public int setQuickCleanAirState(int i8) {
        return this.mAcManagerImpl.setQuickCleanAirState(i8);
    }

    public int setRearAcControlMode(int i8, int i9) {
        return this.mAcManagerImpl.setRearAcControlMode(i8, i9);
    }

    public int setRearAcKeyLockState(int i8) {
        return this.mAcManagerImpl.setRearAcKeyLockState(i8);
    }

    public int setRearAcLockState(int i8) {
        return this.mAcManagerImpl.setRearAcLockState(i8);
    }

    public int setRearAcWindLevel(int i8, int i9) {
        return this.mAcManagerImpl.setRearAcWindLevel(i8, i9);
    }

    public int setRearAcWindMode(int i8, int i9) {
        return this.mAcManagerImpl.setRearAcWindMode(i8, i9);
    }

    public int setSweepBlowState(int i8, int i9) {
        return this.mAcManagerImpl.setSweepBlowState(i8, i9);
    }

    public int start(int i8) {
        return this.mAcManagerImpl.start(i8);
    }

    public int startRearAc(int i8) {
        return this.mAcManagerImpl.startRearAc(i8);
    }

    public int stop(int i8) {
        return this.mAcManagerImpl.stop(i8);
    }

    public int stopRearAc(int i8) {
        return this.mAcManagerImpl.stopRearAc(i8);
    }

    public void unregisterListener(AbsBYDAutoAcListener absBYDAutoAcListener) {
        this.mAcManagerImpl.unregisterListener(absBYDAutoAcListener);
    }

    public void registerListener(AbsBYDAutoAcListener absBYDAutoAcListener, int[] iArr) {
        this.mAcManagerImpl.registerListener(absBYDAutoAcListener, iArr);
    }
}
