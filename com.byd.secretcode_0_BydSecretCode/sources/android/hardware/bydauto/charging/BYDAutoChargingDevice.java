package android.hardware.bydauto.charging;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.charging.tsimpl.ChargingManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoChargingDevice extends AbsBYDAutoDevice {
    public static final int APPOINTMENT_FAILED = 2;
    public static final int APPOINTMENT_INVALID = 0;
    public static final int APPOINTMENT_VALID = 1;
    public static final int APPO_DAY_INVALID = 0;
    public static final int APPO_DAY_MAX = 31;
    public static final int APPO_DAY_MIN = 1;
    public static final int APPO_HOUR_INVALID = 31;
    public static final int APPO_HOUR_MAX = 23;
    public static final int APPO_HOUR_MIN = 0;
    public static final int APPO_MINUTE_INVALID = 63;
    public static final int APPO_MINUTE_MAX = 59;
    public static final int APPO_MINUTE_MIN = 0;
    public static final int APPO_MONTH_INVALID1 = 0;
    public static final int APPO_MONTH_INVALID2 = 15;
    public static final int APPO_MONTH_MAX = 12;
    public static final int APPO_MONTH_MIN = 1;
    public static final int APPO_YEAR_INVALID = 2127;
    public static final int APPO_YEAR_MAX = 2099;
    public static final int APPO_YEAR_MIN = 2000;
    public static final int BATTERY_TYPE_INVALID = 65535;
    public static final int BATTERY_TYPE_IRON = 1;
    public static final int BATTERY_TYPE_LEAD_ACID = 0;
    public static final int CAP_CONFIRM = 1;
    public static final int CAP_INVALID = 0;
    public static final int CAP_LEFT = 1;
    public static final int CAP_NOPROMPT = 1;
    public static final int CAP_PROMPT = 2;
    public static final int CAP_RIGHT = 2;
    public static final int CAP_TIMEOUT = 2;
    public static final int CAR_DISCHARGE_CLOSE = 2;
    public static final int CAR_DISCHARGE_INVALID = 0;
    public static final int CAR_DISCHARGE_OPEN = 1;
    public static final int CHARGE_BATTERY_TYPE_LCO = 5;
    public static final int CHARGE_BATTERY_TYPE_LFP = 3;
    public static final int CHARGE_BATTERY_TYPE_LIP = 7;
    public static final int CHARGE_BATTERY_TYPE_LMO = 4;
    public static final int CHARGE_BATTERY_TYPE_LTO = 8;
    public static final int CHARGE_BATTERY_TYPE_NI_MH = 2;
    public static final int CHARGE_BATTERY_TYPE_PLUMBIC_ACID = 1;
    public static final int CHARGE_BATTERY_TYPE_TERNARY = 6;
    public static final int CHARGE_MODE_IMMEDIATELY = 1;
    public static final int CHARGE_MODE_INVALID = 0;
    public static final int CHARGE_MODE_RESERVE = 2;
    public static final int CHARGE_MODE_SMART = 3;
    public static final int CHARGE_PREHEAT_COMPLETED = 2;
    public static final int CHARGE_PREHEAT_OFF = 0;
    public static final int CHARGE_PREHEAT_ON = 1;
    public static final int CHARGE_STOP_CAPACITY_100 = 1;
    public static final int CHARGE_STOP_CAPACITY_50 = 6;
    public static final int CHARGE_STOP_CAPACITY_60 = 5;
    public static final int CHARGE_STOP_CAPACITY_70 = 4;
    public static final int CHARGE_STOP_CAPACITY_80 = 3;
    public static final int CHARGE_STOP_CAPACITY_90 = 2;
    public static final int CHARGE_STOP_CAPACITY_INVALID = 0;
    public static final int CHARGE_STOP_INVALID = 0;
    public static final int CHARGE_STOP_OFF = 1;
    public static final int CHARGE_STOP_ON = 2;
    public static final int CHARGE_STOP_SUPPORT_AVAILABLE = 2;
    public static final int CHARGE_STOP_SUPPORT_DEFAULT = 3;
    public static final int CHARGE_STOP_SUPPORT_DISAVAILABLE = 1;
    public static final int CHARGE_STOP_SUPPORT_RESERVED = 0;
    public static final int CHARGE_TEMP_CTL_STATE_INVALID = 0;
    public static final int CHARGE_TEMP_CTL_STATE_OFF = 2;
    public static final int CHARGE_TEMP_CTL_STATE_ON = 1;
    public static final int CHARGE_TOAGGLE_AUTO = 3;
    public static final int CHARGE_TOAGGLE_OFF = 2;
    public static final int CHARGE_TOAGGLE_ON = 1;
    public static final int CHARGE_WIRELESS_CHARGING_INVALID = 0;
    public static final int CHARGE_WIRELESS_CHARGING_OFF = 2;
    public static final int CHARGE_WIRELESS_CHARGING_ON = 1;
    public static final int CHARGE_WIRELESS_CHARGING_ONLINE_OFF = 0;
    public static final int CHARGE_WIRELESS_CHARGING_ONLINE_ON = 1;
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_AC = 8;
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_C10 = 5;
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_CHARGER = 7;
    public static final int CHARGING_BATTERY_STATE_BREAKDOWN_CHARGING_GUN = 6;
    public static final int CHARGING_BATTERY_STATE_CHARGING = 1;
    public static final int CHARGING_BATTERY_STATE_CHARGING_PAUSE = 13;
    public static final int CHARGING_BATTERY_STATE_CHARG_FINISH = 2;
    public static final int CHARGING_BATTERY_STATE_CHARG_TERMINATE = 4;
    public static final int CHARGING_BATTERY_STATE_DISCHARGING = 11;
    public static final int CHARGING_BATTERY_STATE_DISCHARGING_ABNORMAL = 14;
    public static final int CHARGING_BATTERY_STATE_DISCHARG_FINISH = 12;
    public static final int CHARGING_BATTERY_STATE_DISCHARG_READY = 10;
    public static final int CHARGING_BATTERY_STATE_GUN_CONNECTING = 3;
    public static final int CHARGING_BATTERY_STATE_READY = 0;
    public static final int CHARGING_BATTERY_STATE_SCHEDULE = 9;
    public static final double CHARGING_CAPACITY_MAX = 131.07d;
    public static final double CHARGING_CAPACITY_MIN = 0.0d;
    public static final int CHARGING_CAP_AC = 1;
    public static final int CHARGING_CAP_DC = 2;
    public static final int CHARGING_CAP_LEFT = 3;
    public static final int CHARGING_CAP_RIGHT = 4;
    public static final int CHARGING_CAP_STATE_OFF = 0;
    public static final int CHARGING_CAP_STATE_ON = 1;
    public static final int CHARGING_CHARGER_STATE_CONNECTED = 1;
    public static final int CHARGING_CHARGER_STATE_NOT_CONNECTED = 0;
    public static final int CHARGING_COMMAND_BUSY = -2147482647;
    public static final int CHARGING_COMMAND_FAILED = -2147482648;
    public static final int CHARGING_COMMAND_INVALID_VALUE = -2147482645;
    public static final int CHARGING_COMMAND_SUCCESS = 0;
    public static final int CHARGING_COMMAND_TIMEOUT = -2147482646;
    public static final int CHARGING_ENTER = 1;
    public static final int CHARGING_EXIT = 2;
    public static final int CHARGING_FAILURE = 1;
    public static final int CHARGING_FAULT_STATE_MAJOR = 3;
    public static final int CHARGING_FAULT_STATE_MINOR = 2;
    public static final int CHARGING_FAULT_STATE_NORMAL = 1;
    public static final int CHARGING_GUN_STATE_CONNECTED_AC = 1;
    public static final int CHARGING_GUN_STATE_CONNECTED_AC_DC = 3;
    public static final int CHARGING_GUN_STATE_CONNECTED_DC = 2;
    public static final int CHARGING_GUN_STATE_CONNECTED_NONE = 0;
    public static final int CHARGING_GUN_STATE_CONNECTED_OBC_CC = 5;
    public static final int CHARGING_GUN_STATE_CONNECTED_VTOL = 4;
    public static final int CHARGING_GUN_STATE_OFF = 2;
    public static final int CHARGING_GUN_STATE_ON = 1;
    public static final int CHARGING_PORT_STATE_LOCK_FINISH = 1;
    public static final int CHARGING_PORT_STATE_LOCK_INVALID = 2;
    public static final int CHARGING_PORT_STATE_UNLOCK_FINISH = 3;
    public static final int CHARGING_PORT_STATE_UNLOCK_INVALID = 4;
    public static final int CHARGING_POWER_MAX = 500;
    public static final int CHARGING_POWER_MIN = 0;
    public static final int CHARGING_SCHEDULE_STATE_CANCEL = 2;
    public static final int CHARGING_SCHEDULE_STATE_INVALID = 1;
    public static final int CHARGING_SCHEDULE_STATE_LOCAL = 4;
    public static final int CHARGING_SCHEDULE_STATE_NONE = 3;
    public static final int CHARGING_SCHEDULE_STATE_REMOTE = 5;
    public static final int CHARGING_STATE_DISABLE = 1;
    public static final int CHARGING_STATE_ENABLE = 0;
    public static final int CHARGING_SUCCESS = 2;
    public static final int CHARGING_TYPE_AC = 2;
    public static final int CHARGING_TYPE_DEFAULT = 1;
    public static final int CHARGING_TYPE_GB_DC = 4;
    public static final int CHARGING_TYPE_GB_NON_DC = 5;
    public static final int CHARGING_TYPE_VTOG = 3;
    public static final int CHARGING_WORK_STATE_FINISH = 3;
    public static final int CHARGING_WORK_STATE_READY = 1;
    public static final int CHARGING_WORK_STATE_START = 2;
    public static final int CHARGING_WORK_STATE_TERMINATE = 4;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int DISCHARGE_CONNECT_DISCHARGE_CAR = 1;
    public static final int DISCHARGE_CONNECT_DISCHARGE_CHARGE_CAR = 2;
    public static final int DISCHARGE_CONNECT_NONE = 0;
    public static final int DISCHARGE_LIMIT_INVALID = 255;
    public static final int DISCHARGE_LIMIT_VAL_MAX = 100;
    public static final int DISCHARGE_LIMIT_VAL_MIN = 0;
    public static final int DISCHARGE_LOWEST_VAL_MAX = 100;
    public static final int DISCHARGE_LOWEST_VAL_MIN = 0;
    public static final int DISCHARGE_OVER_REASON_CHARGE_BATTERY = 6;
    public static final int DISCHARGE_OVER_REASON_CHARGE_MADE = 5;
    public static final int DISCHARGE_OVER_REASON_DEFAULT = 0;
    public static final int DISCHARGE_OVER_REASON_DISCHARGE_MADE = 4;
    public static final int DISCHARGE_OVER_REASON_LIMIT = 2;
    public static final int DISCHARGE_OVER_REASON_MAN_MADE = 1;
    public static final int DISCHARGE_OVER_REASON_NORMAL = 3;
    public static final int DISCHARGE_OVER_REASON_OTHER = 8;
    public static final int DISCHARGE_OVER_REASON_TERNARY = 7;
    public static final int DISCHARGE_QUANTITY_MAX = 1000;
    public static final int DISCHARGE_QUANTITY_MIN = 0;
    public static final int DISCHARGE_STATE_FINISH = 2;
    public static final int DISCHARGE_STATE_HOUSEHOLD_APPLIANCE = 2;
    public static final int DISCHARGE_STATE_NON = 1;
    public static final int DISCHARGE_STATE_ONGOING = 1;
    public static final int DISCHARGE_STATE_POWER_SYSTEM = 5;
    public static final int DISCHARGE_STATE_READY = 0;
    public static final int DISCHARGE_STATE_SINGLE_PHASE_VEHICLE = 7;
    public static final int DISCHARGE_STATE_SOCKET = 6;
    public static final int DISCHARGE_STATE_THREE_PHASE_EQUIPMENT = 3;
    public static final int DISCHARGE_STATE_THREE_PHASE_VEHICLE = 4;
    public static final int DISCHARGE_TYPE_INNER_SOCKET = 2;
    public static final int DISCHARGE_TYPE_VTOL = 1;
    public static final int DISCHARGE_WARNING_STATE_INVALID = 1;
    public static final int DISCHARGE_WARNING_STATE_VALID = 2;
    public static final int ELECTRICITY_PRICE_LEVEL_LENGTH = 24;
    public static final String FEATURE_CHARGE_BY_APPOINTMENT = "ChargeByAppointment";
    public static final String FEATURE_CHARGE_DISCHARGE_TO_CAR = "ChargeDischargeToCar";
    public static final String FEATURE_CHARGE_STOP = "ChargeStop";
    public static final String FEATURE_CHARGE_TEMP_CTL = "ChargeTemperatureControl";
    public static final String FEATURE_CHARGE_WIRELESS_CHARGING = "ChargeWirlessCharging";
    public static final String FEATURE_CHARGING_SMART_TRAVEL = "SmartTravel";
    public static final String FEATURE_SOC_SAVE_SWITCH_CONFIG = "soc_sava_switch_config";
    public static final int HAS_WEATHER_AND_TIME_REQUIREMENT = 0;
    public static final int HOUR_MAX = 23;
    public static final int HOUR_MIN = 0;
    public static final int LOW_WARN_NONE = 0;
    public static final int LOW_WARN_TIPS = 1;
    public static final int MINUTE_MAX = 59;
    public static final int MINUTE_MIN = 0;
    public static final int NOT_HAS_WEATHER_AND_TIME_REQUIREMENT = 1;
    public static final int NOT_SMART_CHARGING_STATE = 3;
    public static final String PERMISSION_BYDAUTO_CHARGING_COMMON = "android.permission.BYDAUTO_CHARGING_COMMON";
    public static final String PERMISSION_BYDAUTO_CHARGING_GET = "android.permission.BYDAUTO_CHARGING_GET";
    public static final String PERMISSION_BYDAUTO_CHARGING_SET = "android.permission.BYDAUTO_CHARGING_SET";
    public static final int PREDICTED_TEMPERATURE_MAX = 160;
    public static final int PREDICTED_TEMPERATURE_MIN = -80;
    public static final int RESET_INVALID = 0;
    public static final int RESET_ITEM_CHARGE = 0;
    public static final int RESET_VALID = 1;
    public static final int REST_HOUR_MAX = 254;
    public static final int REST_HOUR_MIN = 0;
    public static final int SAFE_BATTERY_INVALID = 127;
    public static final int SAFE_BATTERY_MAX = 100;
    public static final int SAFE_BATTERY_MIN = 20;
    public static final int SMART_CHARGING_STATE_ENTER = 0;
    public static final int SMART_CHARGING_STATE_EXIT = 2;
    public static final int SMART_CHARGING_STATE_ONGOING = 1;
    public static final int SOC_SAVE_SWITCH_INVALID = 0;
    public static final int SOC_SAVE_SWITCH_OFF = 1;
    public static final int SOC_SAVE_SWITCH_ON = 2;
    public static final int STATE_AC_LIMIT_10A = 3;
    public static final int STATE_AC_LIMIT_16A = 4;
    public static final int STATE_AC_LIMIT_6A = 1;
    public static final int STATE_AC_LIMIT_8A = 2;
    public static final int STATE_AC_LIMIT_MAX = 5;
    public static final int STATE_ALLOW = 0;
    public static final int STATE_CARPLAN_ACC_ON = 1;
    public static final int STATE_CARPLAN_BATTERY_HEATING = 1;
    public static final int STATE_CARPLAN_BATTERY_NOT_HEAT = 0;
    public static final int STATE_CARPLAN_INVALID = 0;
    public static final int STATE_CARPLAN_NOT_IN = 0;
    public static final int STATE_CARPLAN_NOT_VALID = 2;
    public static final int STATE_CARPLAN_OFF = 0;
    public static final int STATE_CARPLAN_ON = 1;
    public static final int STATE_CARPLAN_VALID = 1;
    public static final int STATE_CHARGEPLAN_END = 3;
    public static final int STATE_CHARGEPLAN_INVALID = 0;
    public static final int STATE_CHARGEPLAN_START = 2;
    public static final int STATE_CHARGEPLAN_WAITING = 1;
    public static final int STATE_DAY_DEFAULT = 0;
    public static final int STATE_DAY_INVALID = 3;
    public static final int STATE_DAY_TODAY = 1;
    public static final int STATE_DAY_TOMORROW = 2;
    public static final int STATE_END = 3;
    public static final int STATE_HINT_HAS = 1;
    public static final int STATE_HINT_NOT = 0;
    public static final int STATE_INVALID = 0;
    public static final int STATE_MOVEPOWER_OFF = 1;
    public static final int STATE_MOVEPOWER_ON = 0;
    public static final int STATE_NOSENSECHARFE_OFF = 1;
    public static final int STATE_NOSENSECHARFE_ON = 0;
    public static final int STATE_NOT_ALLOW = 1;
    public static final int STATE_OFF = 2;
    public static final int STATE_ON = 1;
    public static final int STATE_READY = 1;
    public static final int STATE_START = 2;
    public static final int STATE_SUPPORT = 1;
    public static final int STATE_UNSUPPORT = 2;
    public static final String TAG = "BYDAutoChargingDevice";
    public static final int WIRELESS_CHARGING = 0;
    public static final int WIRELESS_CHARGING_FAULT = 2;
    public static final int WIRELESS_CHARGING_FULL = 1;
    public static final int WIRELESS_CHARGING_STANDBY = 3;
    public static final int WIRELESS_CHARGING_STATE_FAULT = 2;
    public static final int WIRELESS_CHARGING_STATE_FULL = 1;
    public static final int WIRELESS_CHARGING_STATE_ONGOING = 0;
    public static final int WIRELESS_CHARGING_STATE_STANDBY = 3;
    private static volatile BYDAutoChargingDevice mBYDAutoChargingDevice;
    private ChargingManagerImpl mChargingManagerImpl;
    private Context mContext;

    public BYDAutoChargingDevice(Context context) {
        super(context);
        this.mChargingManagerImpl = null;
        this.mChargingManagerImpl = ChargingManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoChargingDevice getInstance(Context context) {
        if (mBYDAutoChargingDevice == null) {
            mBYDAutoChargingDevice = new BYDAutoChargingDevice(context.getApplicationContext());
        }
        return mBYDAutoChargingDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mChargingManagerImpl.get(iArr, cls);
    }

    public int getACChargingLimit() {
        return this.mChargingManagerImpl.getAcChargingLimit();
    }

    public void getAllStatus() {
    }

    public byte[] getBMSWakeNoSenseCharge() {
        return this.mChargingManagerImpl.getBmsWakeNoSenseCharge();
    }

    public int getBatteryManagementDeviceState() {
        return this.mChargingManagerImpl.getBatteryManagementDeviceState();
    }

    public int getBatteryType() {
        return this.mChargingManagerImpl.getBatteryType();
    }

    public int getCapState() {
        return 0;
    }

    public int getCarDischargeLowWarn() {
        return 0;
    }

    public int getCarDischargeState() {
        return 0;
    }

    public int getCarPlanAccOnState() {
        return this.mChargingManagerImpl.getCarPlanAccOnState();
    }

    public int getCarPlanBatteryHeatState() {
        return this.mChargingManagerImpl.getCarPlanBatteryHeatState();
    }

    public int getCarPlanState() {
        return this.mChargingManagerImpl.getCarPlanState();
    }

    public int getChargeBatteryLevelBegin() {
        return this.mChargingManagerImpl.getChargeBatteryLevelBegin();
    }

    public int getChargeBatteryLimitTemp() {
        return this.mChargingManagerImpl.getChargeBatteryLimitTemp();
    }

    public int getChargeBatteryTemp() {
        return this.mChargingManagerImpl.getChargeBatteryTemp();
    }

    public float getChargeElectric() {
        return this.mChargingManagerImpl.getChargeElectric();
    }

    public float getChargeLimit() {
        return this.mChargingManagerImpl.getChargeLimit();
    }

    public int getChargePreheatAllow() {
        return this.mChargingManagerImpl.getChargePreheatAllow();
    }

    public int getChargePreheatState() {
        return this.mChargingManagerImpl.getChargePreheatState();
    }

    public float getChargeStopCapacityState() {
        return this.mChargingManagerImpl.getChargeStopCapacityState();
    }

    public int getChargeStopCapacityStateNotLi() {
        return this.mChargingManagerImpl.getChargeStopCapacityStateNotLi();
    }

    public int getChargeStopSupportConfig() {
        return 0;
    }

    public int getChargeStopSwitchState() {
        return 0;
    }

    public int getChargeTempCtlState() {
        return this.mChargingManagerImpl.getChargeTempCtlState();
    }

    public int getChargeToaggleGuard() {
        return this.mChargingManagerImpl.getChargeToggleGuard();
    }

    public int getChargeVoltage() {
        return this.mChargingManagerImpl.getChargeVoltage();
    }

    public float getChargeVoltageMax() {
        return this.mChargingManagerImpl.getChargeVoltageMax();
    }

    public int getChargerFaultState() {
        return this.mChargingManagerImpl.getChargerFaultState();
    }

    public int getChargerState() {
        return this.mChargingManagerImpl.getChargerState();
    }

    public int getChargerWorkState() {
        return this.mChargingManagerImpl.getChargerWorkState();
    }

    public int getChargingCapState(int i) {
        return this.mChargingManagerImpl.getChargingCapState(i);
    }

    public double getChargingCapacity() {
        return this.mChargingManagerImpl.getChargingCapacity();
    }

    public int getChargingGunNotInsertedState() {
        return this.mChargingManagerImpl.getChargingGunNotInsertedState();
    }

    public int getChargingGunState() {
        return this.mChargingManagerImpl.getChargingGunState();
    }

    public int getChargingMode() {
        return 0;
    }

    public int getChargingPortLockRebackState() {
        return this.mChargingManagerImpl.getChargingPortLockRebackState();
    }

    public double getChargingPower() {
        return this.mChargingManagerImpl.getChargingPower();
    }

    public int[] getChargingRestTime() {
        return this.mChargingManagerImpl.getChargingRestTime();
    }

    public int getChargingScheduleEnableState() {
        return this.mChargingManagerImpl.getChargingScheduleEnableState();
    }

    public int getChargingScheduleState() {
        return this.mChargingManagerImpl.getChargingScheduleState();
    }

    public int[] getChargingScheduleTime() {
        return this.mChargingManagerImpl.getChargingScheduleTime();
    }

    public int getChargingState() {
        return 0;
    }

    public ChargingTimerInfo getChargingTimerInfo(int i) {
        return null;
    }

    public int getChargingType() {
        return this.mChargingManagerImpl.getChargingType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public float getDischargeElectric() {
        return this.mChargingManagerImpl.getDischargeElectric();
    }

    public int getDischargeGunTimer() {
        return this.mChargingManagerImpl.getDischargeGunTimer();
    }

    public int getDischargeLimit() {
        return this.mChargingManagerImpl.getDischargeLimit();
    }

    public int getDischargeOverReason() {
        return this.mChargingManagerImpl.getDischargeOverReason();
    }

    public int getDischargeRemainTime() {
        return this.mChargingManagerImpl.getDischargeRemainTime();
    }

    public int getDischargeRequestState() {
        return this.mChargingManagerImpl.getDischargeRequestState();
    }

    public int getDischargeState(int i) {
        return 0;
    }

    public long getDischargeTime() {
        return this.mChargingManagerImpl.getDischargeTime();
    }

    public int getDischargeToggle() {
        return this.mChargingManagerImpl.getDischargeToggle();
    }

    public int getDischargeTotalTime() {
        return this.mChargingManagerImpl.getDischargeTotalTime();
    }

    public int getDischargeVoltage() {
        return this.mChargingManagerImpl.getDischargeVoltage();
    }

    public int getDischargeWarningState() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public int getFullChgRemanTimH() {
        return this.mChargingManagerImpl.getFullChgRemanTimH();
    }

    public int getFullChgRemanTimM() {
        return this.mChargingManagerImpl.getFullChgRemanTimM();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_CHARGING_GET;
    }

    public int getHEVChargePlanEndTimeTag() {
        return this.mChargingManagerImpl.getHevChargePlanEndTimeTag();
    }

    public int[] getHEVChargePlanStartTime() {
        return this.mChargingManagerImpl.getHevChargePlanStartTime();
    }

    public int getHEVChargePlanStartTimeTag() {
        return this.mChargingManagerImpl.getHevChargePlanStartTimeTag();
    }

    public int[] getHEVChargePlaneEndTime() {
        return this.mChargingManagerImpl.getHevChargePlanEndTime();
    }

    public int getHEVChargingType() {
        return this.mChargingManagerImpl.getHevChargingType();
    }

    public int getIntelligentKeepTemMode() {
        return this.mChargingManagerImpl.getIntelligentKeepTemMode();
    }

    public int getMovePowerStationToggle() {
        return this.mChargingManagerImpl.getMovePowerStationToggle();
    }

    public int getNoSenseChargeState() {
        return this.mChargingManagerImpl.getNoSenseChargeState();
    }

    public byte[] getOBCWakeNoSenseCharge() {
        return this.mChargingManagerImpl.getObcWakeNoSenseCharge();
    }

    public int getPorducePowerWhenLowPower() {
        return this.mChargingManagerImpl.getPorducePowerWhenLowPower();
    }

    public int getPortCapState(int i) {
        return this.mChargingManagerImpl.getPortCapState(i);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_CHARGING_SET;
    }

    public int getSituPowerGenerationState() {
        return this.mChargingManagerImpl.getSituPowerGenerationState();
    }

    public int getSituPowerGenerationSupport() {
        return this.mChargingManagerImpl.getSituPowerGenerationSupport();
    }

    public int getSmartChargingState() {
        return 0;
    }

    public int getSocSaveSwitch() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int getV2GChargeStatus() {
        return this.mChargingManagerImpl.getV2gChargeStatus();
    }

    public int getV2GOutputPower() {
        return this.mChargingManagerImpl.getV2gOutputPower();
    }

    public int getV2GStatus() {
        return this.mChargingManagerImpl.getV2gStatus();
    }

    public float getVoltageMax() {
        return this.mChargingManagerImpl.getVoltageMax();
    }

    public int getVtovChargeBatteryLevelNum() {
        return this.mChargingManagerImpl.getVtovChargeBatteryLevelNum();
    }

    public int getVtovChargeBatteryLevelNumLimit() {
        return this.mChargingManagerImpl.getVtovChargeBatteryLevelNumLimit();
    }

    public int getVtovDischargeConnectState() {
        return this.mChargingManagerImpl.getVtovDischargeConnectState();
    }

    public int getVtovDischargeLimitVal() {
        return this.mChargingManagerImpl.getVtovDischargeLimitVal();
    }

    public int getVtovDischargeLowPowerHint() {
        return this.mChargingManagerImpl.getVtovDischargeLowPowerHint();
    }

    public int getVtovDischargeLowestVal() {
        return this.mChargingManagerImpl.getVtovDischargeLowestVal();
    }

    public float getVtovDischargeQuantity() {
        return this.mChargingManagerImpl.getVtovDischargeQuantity();
    }

    public int getWeatherAndTimeRequest() {
        return 0;
    }

    public int getWirelessChargingFRState() {
        return this.mChargingManagerImpl.getWirelessChargingFrState();
    }

    public int getWirelessChargingFRSwitchState() {
        return this.mChargingManagerImpl.getWirelessChargingFrSwitchState();
    }

    public int getWirelessChargingOnline5sState() {
        return 0;
    }

    public int getWirelessChargingOnlineState() {
        return 0;
    }

    public int getWirelessChargingPowerFLState() {
        return this.mChargingManagerImpl.getWirelessChargingPowerFlState();
    }

    public int getWirelessChargingPowerFRState() {
        return this.mChargingManagerImpl.getWirelessChargingPowerFrState();
    }

    public int getWirelessChargingState() {
        return this.mChargingManagerImpl.getWirelessChargingState();
    }

    public int getWirelessChargingSwitchState() {
        return this.mChargingManagerImpl.getWirelessChargingSwitchState();
    }

    public int hasFeature(String str) {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i, int i2, float f, Object obj) {
        return true;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return true;
    }

    public void registerListener(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        this.mChargingManagerImpl.registerListener(absBYDAutoChargingListener);
    }

    public int reset(int i, int i2) {
        return 0;
    }

    public int sendCapState(int i) {
        return 0;
    }

    public int sendVtovDischargeLimitVal(int i) {
        return setDischargeLimit(i);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mChargingManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int setACChargingLimit(int i) {
        return this.mChargingManagerImpl.setAcChargingLimit(i);
    }

    public void setAllStatus() {
    }

    public int setCarDischargeState(int i) {
        return 0;
    }

    public int setCarPlan(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return this.mChargingManagerImpl.setCarPlan(i, i2, i3, i4, i5, i6, i7, i8);
    }

    @Deprecated
    public int setCarPlanState(int i) {
        return this.mChargingManagerImpl.setCarPlanState(i);
    }

    public int setChargeLimit(float f) {
        return this.mChargingManagerImpl.setChargeLimit(f);
    }

    public int setChargePlanState(int i) {
        return this.mChargingManagerImpl.setChargePlanState(i);
    }

    public int setChargePreheatState(int i) {
        return this.mChargingManagerImpl.setChargePreheatState(i);
    }

    public int setChargeStopCapacityState(int i) {
        return this.mChargingManagerImpl.setChargeStopCapacityState(i);
    }

    public int setChargeStopCapacityStateNotLi(int i) {
        return this.mChargingManagerImpl.setChargeStopCapacityStateNotLi(i);
    }

    public int setChargeStopSwitchState(int i) {
        return 0;
    }

    public int setChargeTempCtlState(int i) {
        return this.mChargingManagerImpl.setChargeTempCtlState(i);
    }

    public int setChargeToaggleGuard(int i) {
        return this.mChargingManagerImpl.setChargeToggleGuard(i);
    }

    public int setChargingMode(int i) {
        return 0;
    }

    public int setChargingTimerInfo(ChargingTimerInfo chargingTimerInfo) {
        return 0;
    }

    public int setDischargeLimit(int i) {
        return this.mChargingManagerImpl.setDischargeLimit(i);
    }

    public int setDischargeTime(long j) {
        return this.mChargingManagerImpl.setDischargeTime(j);
    }

    public int setDischargeToggle(int i) {
        return this.mChargingManagerImpl.setDischargeToggle(i);
    }

    public int setDriveDistanceShowStandard(int i) {
        return this.mChargingManagerImpl.setDriveDistanceShowStandard(i);
    }

    public int setEVChargePlanEndTime(int i, int i2, int i3, int i4, int i5) {
        return this.mChargingManagerImpl.setEvChargePlanEndTime(i, i2, i3, i4, i5);
    }

    public int setEVChargePlanStartTime(int i, int i2, int i3, int i4, int i5) {
        return this.mChargingManagerImpl.setEvChargePlanStartTime(i, i2, i3, i4, i5);
    }

    public int setElectricityPriceLevel(byte[] bArr) {
        return this.mChargingManagerImpl.setElectricityPriceLevel(bArr);
    }

    public int setHEVChargePlanEndTime(int i, int i2, int i3, int i4, int i5) {
        return this.mChargingManagerImpl.setHevChargePlanEndTime(i, i2, i3, i4, i5);
    }

    public int setHEVChargePlanStartTime(int i, int i2, int i3, int i4, int i5) {
        return this.mChargingManagerImpl.setHevChargePlanStartTime(i, i2, i3, i4, i5);
    }

    public int setHEVChargingType(int i) {
        return this.mChargingManagerImpl.setHevChargingType(i);
    }

    public int setIntelligentKeepTemMode(int i) {
        return this.mChargingManagerImpl.setIntelligentKeepTemMode(i);
    }

    public int setMovePowerStationToggle(int i) {
        return this.mChargingManagerImpl.setMovePowerStationToggle(i);
    }

    public int setNoSenseChargeState(int i) {
        return this.mChargingManagerImpl.setNoSenseChargeState(i);
    }

    public int setPorducePowerWhenLowPower(int i) {
        return this.mChargingManagerImpl.setPorducePowerWhenLowPower(i);
    }

    public int setPreSaleModelState(int i) {
        return this.mChargingManagerImpl.setPreSaleModelState(i);
    }

    public int setPredictTemperature(int i, int i2) {
        return 0;
    }

    public int setSituPowerGenerationState(int i) {
        return this.mChargingManagerImpl.setSituPowerGenerationState(i);
    }

    public int setSocSaveSwitch(int i) {
        return 0;
    }

    public int setSyncTime(int i, int i2, int i3, int i4, int i5) {
        return this.mChargingManagerImpl.setSyncTime(i, i2, i3, i4, i5);
    }

    public int setV2GCmd(int i) {
        return this.mChargingManagerImpl.setV2gCmd(i);
    }

    public int setV2GPower(float f) {
        return this.mChargingManagerImpl.setV2gPower(f);
    }

    public int setV2GStatus(int i) {
        return this.mChargingManagerImpl.setV2gStatus(i);
    }

    public int setWirelessChargingFRSwitchState(int i) {
        return this.mChargingManagerImpl.setWirelessChargingFrSwitchState(i);
    }

    public int setWirelessChargingSwitchState(int i) {
        return this.mChargingManagerImpl.setWirelessChargingSwitchState(i);
    }

    public void unregisterListener(AbsBYDAutoChargingListener absBYDAutoChargingListener) {
        this.mChargingManagerImpl.unregisterListener(absBYDAutoChargingListener);
    }

    public void registerListener(AbsBYDAutoChargingListener absBYDAutoChargingListener, int[] iArr) {
        this.mChargingManagerImpl.registerListener(absBYDAutoChargingListener, iArr);
    }
}
