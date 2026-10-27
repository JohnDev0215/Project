package com.ts.lib.car.hardware.charging;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorChargingManager {
    public static final int ID_A_C_CHARGING_LIMIT_R = 557856280;
    public static final int ID_A_C_CHARGING_LIMIT_S = 557856281;
    public static final int ID_BATTERY_MANAGEMENT_DEVICE_STATER = 557855753;
    public static final int ID_BATTERY_TYPER = 557855795;
    public static final int ID_BMS_WAKE_SENSE_CHARGE10_R = 557856297;
    public static final int ID_BMS_WAKE_SENSE_CHARGE11_R = 557856298;
    public static final int ID_BMS_WAKE_SENSE_CHARGE12_R = 557856299;
    public static final int ID_BMS_WAKE_SENSE_CHARGE13_R = 557856300;
    public static final int ID_BMS_WAKE_SENSE_CHARGE14_R = 557856301;
    public static final int ID_BMS_WAKE_SENSE_CHARGE15_R = 557856302;
    public static final int ID_BMS_WAKE_SENSE_CHARGE16_R = 557856303;
    public static final int ID_BMS_WAKE_SENSE_CHARGE17_R = 557856304;
    public static final int ID_BMS_WAKE_SENSE_CHARGE18_R = 557856305;
    public static final int ID_BMS_WAKE_SENSE_CHARGE19_R = 557856306;
    public static final int ID_BMS_WAKE_SENSE_CHARGE1_R = 557856288;
    public static final int ID_BMS_WAKE_SENSE_CHARGE20_R = 557856307;
    public static final int ID_BMS_WAKE_SENSE_CHARGE21_R = 557856308;
    public static final int ID_BMS_WAKE_SENSE_CHARGE22_R = 557856309;
    public static final int ID_BMS_WAKE_SENSE_CHARGE23_R = 557856310;
    public static final int ID_BMS_WAKE_SENSE_CHARGE24_R = 557856311;
    public static final int ID_BMS_WAKE_SENSE_CHARGE25_R = 557856312;
    public static final int ID_BMS_WAKE_SENSE_CHARGE26_R = 557856313;
    public static final int ID_BMS_WAKE_SENSE_CHARGE27_R = 557856314;
    public static final int ID_BMS_WAKE_SENSE_CHARGE28_R = 557856315;
    public static final int ID_BMS_WAKE_SENSE_CHARGE29_R = 557856316;
    public static final int ID_BMS_WAKE_SENSE_CHARGE2_R = 557856289;
    public static final int ID_BMS_WAKE_SENSE_CHARGE30_R = 557856317;
    public static final int ID_BMS_WAKE_SENSE_CHARGE31_R = 557856318;
    public static final int ID_BMS_WAKE_SENSE_CHARGE32_R = 557856319;
    public static final int ID_BMS_WAKE_SENSE_CHARGE33_R = 557856320;
    public static final int ID_BMS_WAKE_SENSE_CHARGE34_R = 557856321;
    public static final int ID_BMS_WAKE_SENSE_CHARGE35_R = 557856322;
    public static final int ID_BMS_WAKE_SENSE_CHARGE3_R = 557856290;
    public static final int ID_BMS_WAKE_SENSE_CHARGE4_R = 557856291;
    public static final int ID_BMS_WAKE_SENSE_CHARGE5_R = 557856292;
    public static final int ID_BMS_WAKE_SENSE_CHARGE6_R = 557856293;
    public static final int ID_BMS_WAKE_SENSE_CHARGE7_R = 557856294;
    public static final int ID_BMS_WAKE_SENSE_CHARGE8_R = 557856295;
    public static final int ID_BMS_WAKE_SENSE_CHARGE9_R = 557856296;
    public static final int ID_CAR_PLAN_ACC_ON_STATE_R = 557856273;
    public static final int ID_CAR_PLAN_BATTERY_HEAT_STATE_R = 557856274;
    public static final int ID_CAR_PLAN_DAY_S = 557856279;
    public static final int ID_CAR_PLAN_HOUR_S = 557856280;
    public static final int ID_CAR_PLAN_MINUTE_S = 557856281;
    public static final int ID_CAR_PLAN_MONTH_S = 557856278;
    public static final int ID_CAR_PLAN_STATE_R = 557856272;
    public static final int ID_CAR_PLAN_STATE_S = 557856271;
    public static final int ID_CAR_PLAN_YEAR_S = 557856277;
    public static final int ID_CHARGER_FAULT_STATER = 557855744;
    public static final int ID_CHARGER_STATER = 557855750;
    public static final int ID_CHARGER_WORK_FIX_STATER = 557855798;
    public static final int ID_CHARGER_WORK_STATER = 557855745;
    public static final int ID_CHARGE_BATTERY_LEVEL_BEGINR = 557855790;
    public static final int ID_CHARGE_BATTERY_LIMIT_TEMPR = 557855791;
    public static final int ID_CHARGE_BATTERY_TEMPR = 557855792;
    public static final int ID_CHARGE_ELECTRICR = 559952941;
    public static final int ID_CHARGE_PLAN_STATE_S = 557856287;
    public static final int ID_CHARGE_PREHEAT_ALLOW_R = 557856324;
    public static final int ID_CHARGE_PREHEAT_STATE_R = 557856269;
    public static final int ID_CHARGE_PREHEAT_STATE_S = 557856270;
    public static final int ID_CHARGE_STOP_CAPACITY_STATER = 557855804;
    public static final int ID_CHARGE_STOP_CAPACITY_STATES = 557855805;
    public static final int ID_CHARGE_STOP_CAPACITY_STATE_NOT_LI_R = 557856339;
    public static final int ID_CHARGE_STOP_CAPACITY_STATE_NOT_LI_S = 557856340;
    public static final int ID_CHARGE_TEMP_CTL_STATER = 557855802;
    public static final int ID_CHARGE_TEMP_CTL_STATES = 557855803;
    public static final int ID_CHARGE_TOAGGLE_GUARD_MIXR = 557855806;
    public static final int ID_CHARGE_TOGGLE_GUARDR = 557855762;
    public static final int ID_CHARGE_TOGGLE_GUARDS = 557855761;
    public static final int ID_CHARGE_VOLTAGER = 557855788;
    public static final int ID_CHARGE_VOLTAGE_MAXR = 559952933;
    public static final int ID_CHARGING_CAP_STATE_LEFTR = 557855800;
    public static final int ID_CHARGING_CAP_STATE_RIGHTR = 557855801;
    public static final int ID_CHARGING_GUN_STATER = 557855751;
    public static final int ID_CHARGING_POWERR = 559952904;
    public static final int ID_CHARGING_RESET_TIME_HOURR = 557855746;
    public static final int ID_CHARGING_RESET_TIME_MINUTER = 557855747;
    public static final int ID_CHARGING_RESTTIME_HOURR = 557855808;
    public static final int ID_CHARGING_RESTTIME_MINUTER = 557855809;
    public static final int ID_CHARGING_SCHEDULE_ENABLE_STATER = 557855754;
    public static final int ID_CHARGING_SCHEDULE_STATER = 557855755;
    public static final int ID_CHARGING_SCHEDULE_TIME_DAYR = 557855758;
    public static final int ID_CHARGING_SCHEDULE_TIME_HOURR = 557855759;
    public static final int ID_CHARGING_SCHEDULE_TIME_MINUTER = 557855760;
    public static final int ID_CHARGING_SCHEDULE_TIME_MONTHR = 557855757;
    public static final int ID_CHARGING_SCHEDULE_TIME_YEARR = 557855756;
    public static final int ID_CHARING_PORT_LOCK_REBACK_STATER = 557855748;
    public static final int ID_DISCHARGE_ELECTRICR = 559952936;
    public static final int ID_DISCHARGE_GUN_TIMERR = 557855785;
    public static final int ID_DISCHARGE_LIMITR = 557855766;
    public static final int ID_DISCHARGE_LIMITS = 557855765;
    public static final int ID_DISCHARGE_OVER_REASONR = 557855787;
    public static final int ID_DISCHARGE_REMAIN_TIMER = 557855780;
    public static final int ID_DISCHARGE_REQUEST_STATER = 557855749;
    public static final int ID_DISCHARGE_TIME_HOURR = 557855771;
    public static final int ID_DISCHARGE_TIME_HOURS = 557855769;
    public static final int ID_DISCHARGE_TIME_MINUTER = 557855772;
    public static final int ID_DISCHARGE_TIME_MINUTES = 557855770;
    public static final int ID_DISCHARGE_TOGGLER = 557855768;
    public static final int ID_DISCHARGE_TOGGLES = 557855767;
    public static final int ID_DISCHARGE_TOTAL_TIMER = 557855786;
    public static final int ID_DISCHARGE_VOLTAGER = 557855783;
    public static final int ID_ELECTRICITY_PRICE_LEVEL_EIGHT_S = 557921410;
    public static final int ID_ELECTRICITY_PRICE_LEVEL_NIGHT_S = 557921424;
    public static final int ID_ELECTRICITY_PRICE_LEVEL_SEVEN_S = 557921396;
    public static final int ID_ELECTRICITY_PRICE_LEVEL_SIX_S = 557921382;
    public static final int ID_ELECTRIC_PORT_CAP_STATE_LEFT_R = 557856347;
    public static final int ID_ELECTRIC_PORT_CAP_STATE_RIGHT_R = 557856348;
    public static final int ID_E_V_CHARGE_PLAN_END_TIME_DAY_S = 557856263;
    public static final int ID_E_V_CHARGE_PLAN_END_TIME_HOUR_S = 557856264;
    public static final int ID_E_V_CHARGE_PLAN_END_TIME_MINUTE_S = 557856265;
    public static final int ID_E_V_CHARGE_PLAN_END_TIME_MONTH_S = 557856262;
    public static final int ID_E_V_CHARGE_PLAN_END_TIME_YEAR_S = 557856261;
    public static final int ID_E_V_CHARGE_PLAN_START_TIME_DAY_S = 557856258;
    public static final int ID_E_V_CHARGE_PLAN_START_TIME_HOUR_S = 557856259;
    public static final int ID_E_V_CHARGE_PLAN_START_TIME_MINUTE_S = 557856260;
    public static final int ID_E_V_CHARGE_PLAN_START_TIME_MONTH_S = 557856257;
    public static final int ID_E_V_CHARGE_PLAN_START_TIME_YEAR_S = 557856256;
    public static final int ID_FULL_CHG_REMAN_TIM_H_R = 557855796;
    public static final int ID_FULL_CHG_REMAN_TIM_M_R = 557855797;
    public static final int ID_HEV_CHARGE_PLAN_END_TIME_DAY_R = 567856339;
    public static final int ID_HEV_CHARGE_PLAN_END_TIME_HOUR_R = 567856340;
    public static final int ID_HEV_CHARGE_PLAN_END_TIME_MINUTE_R = 567856341;
    public static final int ID_HEV_CHARGE_PLAN_END_TIME_MONTH_R = 557856438;
    public static final int ID_HEV_CHARGE_PLAN_END_TIME_YEAR_R = 557856437;
    public static final int ID_HEV_CHARGE_PLAN_START_TIME_DAY_R = 557856334;
    public static final int ID_HEV_CHARGE_PLAN_START_TIME_HOUR_R = 557856335;
    public static final int ID_HEV_CHARGE_PLAN_START_TIME_MINUTE_R = 557856336;
    public static final int ID_HEV_CHARGE_PLAN_START_TIME_MONTH_R = 557856333;
    public static final int ID_HEV_CHARGE_PLAN_START_TIME_YEAR_R = 557856332;
    public static final int ID_HEV_CHARGING_TYPE = 557855799;
    public static final int ID_H_E_V_CHARGE_PLAN_END_TIME_DAY_S = 557855901;
    public static final int ID_H_E_V_CHARGE_PLAN_END_TIME_HOUR_S = 557855902;
    public static final int ID_H_E_V_CHARGE_PLAN_END_TIME_MINUTE_S = 557855903;
    public static final int ID_H_E_V_CHARGE_PLAN_END_TIME_MONTH_S = 557855900;
    public static final int ID_H_E_V_CHARGE_PLAN_END_TIME_R = 557921815;
    public static final int ID_H_E_V_CHARGE_PLAN_END_TIME_TAG_R = 557856338;
    public static final int ID_H_E_V_CHARGE_PLAN_END_TIME_YEAR_S = 557855899;
    public static final int ID_H_E_V_CHARGE_PLAN_START_TIME_DAY_S = 557855896;
    public static final int ID_H_E_V_CHARGE_PLAN_START_TIME_HOUR_S = 557855897;
    public static final int ID_H_E_V_CHARGE_PLAN_START_TIME_MINUTE_S = 557855898;
    public static final int ID_H_E_V_CHARGE_PLAN_START_TIME_MONTH_S = 557855895;
    public static final int ID_H_E_V_CHARGE_PLAN_START_TIME_TAG_R = 557856337;
    public static final int ID_H_E_V_CHARGE_PLAN_START_TIME_YEAR_S = 557855894;
    public static final int ID_MOVE_POWER_STATION_TOGGLER = 557855764;
    public static final int ID_MOVE_POWER_STATION_TOGGLES = 557855763;
    public static final int ID_NEW_CAR_PLAN_MONTH_S = 557921814;
    public static final int ID_NEW_E_V_CHARGE_PLAN_END_TIME_MINUTE_S = 557921801;
    public static final int ID_NEW_E_V_CHARGE_PLAN_START_TIME_MINUTE_S = 557921796;
    public static final int ID_NEW_H_E_V_CHARGE_PLAN_END_TIME_MINUTE_S = 557921439;
    public static final int ID_NEW_H_E_V_CHARGE_PLAN_START_TIME_MINUTE_S = 557921434;
    public static final int ID_NO_SENSE_CHARGE_STATE_R = 557856276;
    public static final int ID_NO_SENSE_CHARGE_STATE_S = 557856275;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE10_R = 557855820;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE11_R = 557855821;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE12_R = 557855822;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE13_R = 557855823;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE14_R = 557855824;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE15_R = 557855825;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE16_R = 557855826;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE17_R = 557855827;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE18_R = 557855828;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE19_R = 557855829;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE1_R = 557855811;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE20_R = 557855830;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE21_R = 557855831;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE22_R = 557855832;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE23_R = 557855833;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE24_R = 557855834;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE25_R = 557855835;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE26_R = 557855836;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE27_R = 557855837;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE28_R = 557855838;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE29_R = 557855839;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE2_R = 557855812;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE30_R = 557855840;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE31_R = 557855841;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE32_R = 557855842;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE33_R = 557855843;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE34_R = 557855844;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE35_R = 557855845;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE3_R = 557855813;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE4_R = 557855814;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE5_R = 557855815;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE6_R = 557855816;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE7_R = 557855817;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE8_R = 557855818;
    public static final int ID_OBC_WAKE_NO_SENSE_CHARGE9_R = 557855819;
    public static final int ID_PORT_CAP_STATE_LEFT_R = 557856331;
    public static final int ID_PORT_CAP_STATE_RIGHT_R = 557856330;
    public static final int ID_PRODUCE_POWER_WHEN_LOW_POWERR = 557855774;
    public static final int ID_PRODUCE_POWER_WHEN_LOW_POWERS = 557855773;
    public static final int ID_SITU_POWER_GENERATION_STATE_R = 557856266;
    public static final int ID_SITU_POWER_GENERATION_STATE_S = 557856267;
    public static final int ID_SITU_POWER_GENERATION_SUPPORT_R = 557856268;
    public static final int ID_SYNC_TIME_DAY_S = 557856284;
    public static final int ID_SYNC_TIME_HOUR_S = 557856285;
    public static final int ID_SYNC_TIME_MINUTE_S = 557856286;
    public static final int ID_SYNC_TIME_MONTH_S = 557856283;
    public static final int ID_SYNC_TIME_YEAR_S = 557856282;
    public static final int ID_V2_G_CHARGE_STATUS_R = 557856345;
    public static final int ID_V2_G_CMD_S = 557856343;
    public static final int ID_V2_G_OUTPUT_POWER_R = 557856346;
    public static final int ID_V2_G_POWER_S = 559953494;
    public static final int ID_V2_G_STATUS_R = 557856344;
    public static final int ID_V2_G_STATUS_S = 557856341;
    public static final int ID_VOLTAGE_MAXR = 559952934;
    public static final int ID_VTOV_CHARGE_BATTERY_LEVEL_NUMR = 557855778;
    public static final int ID_VTOV_CHARGE_BATTERY_LEVEL_NUM_LIMITR = 557855779;
    public static final int ID_VTOV_DISCHARGE_CONNECT_STATE = 557855810;
    public static final int ID_VTOV_DISCHARGE_LIMIT_VALR = 557855807;
    public static final int ID_VTOV_DISCHARGE_LOWEST_VALR = 557855793;
    public static final int ID_VTOV_DISCHARGE_LOW_POWER_HINT_R = 557856323;
    public static final int ID_VTOV_DISCHARGE_QUANTITYR = 559952946;
    public static final int ID_WIRELESS_CHARGING_F_R_STATE_R = 557856327;
    public static final int ID_WIRELESS_CHARGING_F_R_SWITCH_STATE_R = 557856326;
    public static final int ID_WIRELESS_CHARGING_F_R_SWITCH_STATE_S = 557856325;
    public static final int ID_WIRELESS_CHARGING_POWER_F_L_STATE_R = 557856328;
    public static final int ID_WIRELESS_CHARGING_POWER_F_R_STATE_R = 557856329;
    public static final int ID_WIRELESS_CHARGING_STATER = 557855777;
    public static final int ID_WIRELESS_CHARGING_SWITCH_STATER = 557855776;
    public static final int ID_WIRELESS_CHARGING_SWITCH_STATES = 557855775;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.charging";
    public static final String TAG = "CAR.VENDOR.CHARGING";

    public interface OnCarChargingEventListener {
        void onChargingErrorEvent(int i, int i2);

        void onChargingEventChange(ChargingEventChangeValue chargingEventChangeValue);
    }

    void addCarChargingEventListener(OnCarChargingEventListener onCarChargingEventListener) throws CarNotConnectedException;

    int getAcChargingLimitR() throws CarNotConnectedException;

    int getBatteryManagementDeviceStateR() throws CarNotConnectedException;

    int getBatteryTypeR() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge10R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge11R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge12R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge13R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge14R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge15R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge16R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge17R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge18R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge19R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge1R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge20R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge21R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge22R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge23R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge24R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge25R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge26R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge27R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge28R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge29R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge2R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge30R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge31R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge32R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge33R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge34R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge35R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge3R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge4R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge5R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge6R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge7R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge8R() throws CarNotConnectedException;

    int getBmsWakeNoSenseCharge9R() throws CarNotConnectedException;

    int getCarPlanAccOnStateR() throws CarNotConnectedException;

    int getCarPlanBatteryHeatStateR() throws CarNotConnectedException;

    int getCarPlanStateR() throws CarNotConnectedException;

    int getChargeBatteryLevelBeginR() throws CarNotConnectedException;

    int getChargeBatteryLimitTempR() throws CarNotConnectedException;

    int getChargeBatteryTempR() throws CarNotConnectedException;

    float getChargeElectricR() throws CarNotConnectedException;

    int getChargePreheatAllowR() throws CarNotConnectedException;

    int getChargePreheatStateR() throws CarNotConnectedException;

    int getChargeStopCapacityStateNotLiR() throws CarNotConnectedException;

    int getChargeStopCapacityStateR() throws CarNotConnectedException;

    int getChargeTempCtlStateR() throws CarNotConnectedException;

    int getChargeToaggleGuardMixR() throws CarNotConnectedException;

    int getChargeToggleGuardR() throws CarNotConnectedException;

    float getChargeVoltageMaxR() throws CarNotConnectedException;

    int getChargeVoltageR() throws CarNotConnectedException;

    int getChargerFaultStateR() throws CarNotConnectedException;

    int getChargerStateR() throws CarNotConnectedException;

    int getChargerWorkStateFixR() throws CarNotConnectedException;

    int getChargerWorkStateR() throws CarNotConnectedException;

    int getChargingCapStateLeftR() throws CarNotConnectedException;

    int getChargingCapStateRightR() throws CarNotConnectedException;

    int getChargingGunStateR() throws CarNotConnectedException;

    float getChargingPowerR() throws CarNotConnectedException;

    int getChargingResetTimeHourR() throws CarNotConnectedException;

    int getChargingResetTimeMinuteR() throws CarNotConnectedException;

    int getChargingRestTimeHourR() throws CarNotConnectedException;

    int getChargingRestTimeMinuteR() throws CarNotConnectedException;

    int getChargingScheduleEnableStateR() throws CarNotConnectedException;

    int getChargingScheduleStateR() throws CarNotConnectedException;

    int getChargingScheduleTimeDayR() throws CarNotConnectedException;

    int getChargingScheduleTimeHourR() throws CarNotConnectedException;

    int getChargingScheduleTimeMinuteR() throws CarNotConnectedException;

    int getChargingScheduleTimeMonthR() throws CarNotConnectedException;

    int getChargingScheduleTimeYearR() throws CarNotConnectedException;

    int getCharingPortLockRebackStateR() throws CarNotConnectedException;

    float getDischargeElectricR() throws CarNotConnectedException;

    int getDischargeGunTimerR() throws CarNotConnectedException;

    int getDischargeLimitR() throws CarNotConnectedException;

    int getDischargeOverReasonR() throws CarNotConnectedException;

    int getDischargeRemainTimeR() throws CarNotConnectedException;

    int getDischargeRequestStateR() throws CarNotConnectedException;

    int getDischargeTimeHourR() throws CarNotConnectedException;

    int getDischargeTimeMinuteR() throws CarNotConnectedException;

    int getDischargeToggleR() throws CarNotConnectedException;

    int getDischargeTotalTimeR() throws CarNotConnectedException;

    int getDischargeVoltageR() throws CarNotConnectedException;

    int getElectricPortCapStateLeftR() throws CarNotConnectedException;

    int getElectricPortCapStateRightR() throws CarNotConnectedException;

    int getFullChgRemanTimHR() throws CarNotConnectedException;

    int getFullChgRemanTimMR() throws CarNotConnectedException;

    int getHevChargePlanEndTimeMonthR() throws CarNotConnectedException;

    int[] getHevChargePlanEndTimeR() throws CarNotConnectedException;

    int getHevChargePlanEndTimeTagR() throws CarNotConnectedException;

    int getHevChargePlanEndTimeYearDayR() throws CarNotConnectedException;

    int getHevChargePlanEndTimeYearHourR() throws CarNotConnectedException;

    int getHevChargePlanEndTimeYearMinuteR() throws CarNotConnectedException;

    int getHevChargePlanStartTimeMonthR() throws CarNotConnectedException;

    int getHevChargePlanStartTimeTagR() throws CarNotConnectedException;

    int getHevChargePlanStartTimeYearDayR() throws CarNotConnectedException;

    int getHevChargePlanStartTimeYearHourR() throws CarNotConnectedException;

    int getHevChargePlanStartTimeYearMinuteR() throws CarNotConnectedException;

    int getHevChargePlanStartTimeYearR() throws CarNotConnectedException;

    int getHevChargePlaneEndTimeYearR() throws CarNotConnectedException;

    int getMovePowerStationToggleR() throws CarNotConnectedException;

    int getNoSenseChargeStateR() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge10R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge11R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge12R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge13R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge14R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge15R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge16R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge17R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge18R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge19R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge1R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge20R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge21R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge22R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge23R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge24R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge25R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge26R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge27R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge28R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge29R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge2R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge30R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge31R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge32R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge33R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge34R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge35R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge3R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge4R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge5R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge6R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge7R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge8R() throws CarNotConnectedException;

    int getObcWakeNoSenseCharge9R() throws CarNotConnectedException;

    int getProducePowerWhenLowPowerR() throws CarNotConnectedException;

    int getSituPowerGenerationStateR() throws CarNotConnectedException;

    int getSituPowerGenerationSupportR() throws CarNotConnectedException;

    float getVoltageMaxR() throws CarNotConnectedException;

    int getVtovChargeBatteryLevelNumLimitR() throws CarNotConnectedException;

    int getVtovChargeBatteryLevelNumR() throws CarNotConnectedException;

    int getVtovDischargeConnectState() throws CarNotConnectedException;

    int getVtovDischargeLimitValR() throws CarNotConnectedException;

    int getVtovDischargeLowPowerHintR() throws CarNotConnectedException;

    int getVtovDischargeLowestValR() throws CarNotConnectedException;

    float getVtovDischargeQuantityR() throws CarNotConnectedException;

    int getWirelessChargingFrStateR() throws CarNotConnectedException;

    int getWirelessChargingFrSwitchStateR() throws CarNotConnectedException;

    int getWirelessChargingPowerFlStateR() throws CarNotConnectedException;

    int getWirelessChargingPowerFrStateR() throws CarNotConnectedException;

    int getWirelessChargingStateR() throws CarNotConnectedException;

    int getWirelessChargingSwitchStateR() throws CarNotConnectedException;

    int getgetPortCapStateLeftR() throws CarNotConnectedException;

    int getgetPortCapStateRightR() throws CarNotConnectedException;

    int getv2gChargeStatusR() throws CarNotConnectedException;

    int getv2gOutputPowerR() throws CarNotConnectedException;

    int getv2gStatusR() throws CarNotConnectedException;

    void removeCarChargingEventListener(OnCarChargingEventListener onCarChargingEventListener) throws CarNotConnectedException;

    void setAcChargingLimitS(int i) throws CarNotConnectedException;

    void setCarPlanDayS(int i) throws CarNotConnectedException;

    void setCarPlanHourS(int i) throws CarNotConnectedException;

    void setCarPlanMinuteS(int i) throws CarNotConnectedException;

    void setCarPlanMonthS(int i) throws CarNotConnectedException;

    void setCarPlanStateS(int i) throws CarNotConnectedException;

    void setCarPlanYearS(int i) throws CarNotConnectedException;

    void setChargePlanStateS(int i) throws CarNotConnectedException;

    void setChargePreheatStateS(int i) throws CarNotConnectedException;

    void setChargeStopCapacityStateNotLiS(int i) throws CarNotConnectedException;

    void setChargeStopCapacityStateS(int i) throws CarNotConnectedException;

    void setChargeTempCtlStateS(int i) throws CarNotConnectedException;

    void setChargeToggleGuardS(int i) throws CarNotConnectedException;

    void setDischargeLimitS(int i) throws CarNotConnectedException;

    void setDischargeTimeHourS(int i) throws CarNotConnectedException;

    void setDischargeTimeMinuteS(int i) throws CarNotConnectedException;

    void setDischargeToggleS(int i) throws CarNotConnectedException;

    void setElectricityPriceLeveSixS(int[] iArr) throws CarNotConnectedException;

    void setElectricityPriceLevelEightS(int[] iArr) throws CarNotConnectedException;

    void setElectricityPriceLevelNineS(int[] iArr) throws CarNotConnectedException;

    void setElectricityPriceLevelSevenS(int[] iArr) throws CarNotConnectedException;

    void setEvChargePlanEndTimeDayS(int i) throws CarNotConnectedException;

    void setEvChargePlanEndTimeHourS(int i) throws CarNotConnectedException;

    void setEvChargePlanEndTimeMinuteS(int i) throws CarNotConnectedException;

    void setEvChargePlanEndTimeMonthS(int i) throws CarNotConnectedException;

    void setEvChargePlanEndTimeYearS(int i) throws CarNotConnectedException;

    void setEvChargePlanStartTimeDayS(int i) throws CarNotConnectedException;

    void setEvChargePlanStartTimeHourS(int i) throws CarNotConnectedException;

    void setEvChargePlanStartTimeMinuteS(int i) throws CarNotConnectedException;

    void setEvChargePlanStartTimeMonthS(int i) throws CarNotConnectedException;

    void setEvChargePlanStartTimeYearS(int i) throws CarNotConnectedException;

    void setHevChargePlanEndTimeDayS(int i) throws CarNotConnectedException;

    void setHevChargePlanEndTimeHourS(int i) throws CarNotConnectedException;

    void setHevChargePlanEndTimeMinuteS(int i) throws CarNotConnectedException;

    void setHevChargePlanEndTimeMonthS(int i) throws CarNotConnectedException;

    void setHevChargePlanEndTimeYearS(int i) throws CarNotConnectedException;

    void setHevChargePlanStartTimeDayS(int i) throws CarNotConnectedException;

    void setHevChargePlanStartTimeHourS(int i) throws CarNotConnectedException;

    void setHevChargePlanStartTimeMinuteS(int i) throws CarNotConnectedException;

    void setHevChargePlanStartTimeMonthS(int i) throws CarNotConnectedException;

    void setHevChargePlanStartTimeYearS(int i) throws CarNotConnectedException;

    void setHevChargingType(int i) throws CarNotConnectedException;

    void setMovePowerStationToggleS(int i) throws CarNotConnectedException;

    void setNewCarPlanR(int[] iArr) throws CarNotConnectedException;

    void setNewEvChargePlanEndTimeMinuteS(int[] iArr) throws CarNotConnectedException;

    void setNewEvChargePlanStartTimeMinuteS(int[] iArr) throws CarNotConnectedException;

    void setNewHevChargePlanEndTimeMinuteS(int[] iArr) throws CarNotConnectedException;

    void setNewHevChargePlanStartTimeMinuteS(int[] iArr) throws CarNotConnectedException;

    void setNoSenseChargeStateS(int i) throws CarNotConnectedException;

    void setProducePowerWhenLowPowerS(int i) throws CarNotConnectedException;

    void setSituPowerGenerationStateS(int i) throws CarNotConnectedException;

    void setSyncTimeDayS(int i) throws CarNotConnectedException;

    void setSyncTimeHourS(int i) throws CarNotConnectedException;

    void setSyncTimeMinuteS(int i) throws CarNotConnectedException;

    void setSyncTimeMonthS(int i) throws CarNotConnectedException;

    void setSyncTimeYearS(int i) throws CarNotConnectedException;

    void setWirelessChargingFrSwitchStateS(int i) throws CarNotConnectedException;

    void setWirelessChargingSwitchStateS(int i) throws CarNotConnectedException;

    void setv2gCmdS(int i) throws CarNotConnectedException;

    void setv2gPowerS(float f) throws CarNotConnectedException;

    void setv2gStatusS(int i) throws CarNotConnectedException;
}
