package com.ts.lib.car.hardware.energy;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorEnergyManager {
    public static final int ID_CHANGE_P_HINT_R = 557847597;
    public static final int ID_CHARGE_PORT_COVER_HINT_S = 557847602;
    public static final int ID_CHARGE_PORT_COVER_TIP_R = 557847601;
    public static final int ID_CONFIRM_CHARGE_PORT_COVER_S = 557847603;
    public static final int ID_DRIVE_DISTANCE_SHOW_MODER = 557847558;
    public static final int ID_DRIVE_DISTANCE_SHOW_MODES = 557847557;
    public static final int ID_DRIVE_DISTANCE_SHOW_STANDARDR = 557847569;
    public static final int ID_DRIVE_DISTANCE_SHOW_STANDARDS = 557847570;
    public static final int ID_ELECTRIC_POINT_FLAG = 557847581;
    public static final int ID_ELECTRIC_SHOW_MODER = 557847565;
    public static final int ID_ELECTRIC_SHOW_MODES = 557847566;
    public static final int ID_ENERGY_MODER = 557847556;
    public static final int ID_ENERGY_STATER = 557847586;
    public static final int ID_EPEDAL_STATER = 557847563;
    public static final int ID_EPEDAL_STATES = 557847564;
    public static final int ID_HEV_ELECTRIC_POINT_FLAG_R = 557847587;
    public static final int ID_HEV_LAST_200_M_ELECTRIC_CON_R = 559944740;
    public static final int ID_I_T_A_C_EXIT_TAG_R = 557847598;
    public static final int ID_I_T_A_C_MODE_R = 557847599;
    public static final int ID_I_T_A_C_MODE_S = 557847600;
    public static final int ID_I_T_A_C_P_ALLOW_TAG_R = 557847596;
    public static final int ID_I_T_A_C_P_STATE_R = 557847594;
    public static final int ID_I_T_A_C_P_STATE_S = 557847595;
    public static final int ID_LAST200_M_ELECTRIC_CON = 559944735;
    public static final int ID_LAST200_M_OIL_CON = 559944734;
    public static final int ID_LAST50_K_M_ELECTRIC_CON_AVG = 559944737;
    public static final int ID_LAST50_K_M_OIL_CON_AVG = 559944736;
    public static final int ID_MANDATORY_ELECTRIC_STATER = 557847567;
    public static final int ID_MANDATORY_ELECTRIC_STATES = 557847568;
    public static final int ID_MEMORY_ELECTRIC_STATER = 557847576;
    public static final int ID_MEMORY_ELECTRIC_STATES = 557847577;
    public static final int ID_MEMORY_MAX_EV_STATER = 557847574;
    public static final int ID_MEMORY_MAX_EV_STATES = 557847575;
    public static final int ID_MEMORY_SPORT_STATER = 557847572;
    public static final int ID_MEMORY_SPORT_STATES = 557847573;
    public static final int ID_OIL_POINT_FLAG = 557847580;
    public static final int ID_OPERATION_MODER = 557847552;
    public static final int ID_OPERATION_MODE_NORMAL_R = 557847590;
    public static final int ID_OPERATION_MODE_S = 557847591;
    public static final int ID_POWER_GENERATION_STATE = 557847554;
    public static final int ID_POWER_GENERATION_VALUE = 557847555;
    public static final int ID_PRE_SALE_MODEL_STATER = 557847578;
    public static final int ID_PRE_SALE_MODEL_STATES = 557847579;
    public static final int ID_RESET_CURVE_DATA = 557847571;
    public static final int ID_RESET_CURVE_DATA_S = 557847589;
    public static final int ID_ROAD_SURFACE_MODE = 557847553;
    public static final int ID_ROAD_SURFACE_MODE_NORMAL_R = 557847592;
    public static final int ID_ROLL_BENCH_MODE_STATUS_R = 557860387;
    public static final int ID_TRIP_A_C_PERCENT50_K_M_R = 557847610;
    public static final int ID_TRIP_A_C_PERCENT_R = 557847605;
    public static final int ID_TRIP_DRIVE_PERCENT50_K_M_R = 557847609;
    public static final int ID_TRIP_DRIVE_PERCENT_R = 557847604;
    public static final int ID_TRIP_ELEC_APPLIANCE_PERCENT50_K_M_R = 557847611;
    public static final int ID_TRIP_ELEC_APPLIANCE_PERCENT_R = 557847606;
    public static final int ID_TRIP_ELEC_CON_AVG_R = 559944768;
    public static final int ID_TRIP_ENERGY_CON_AVG_R = 559944766;
    public static final int ID_TRIP_LAST50_K_M_ELECTRIC_CON_AVG_R = 559944770;
    public static final int ID_TRIP_LAST50_K_M_FUEL_CON_AVG_R = 559944772;
    public static final int ID_TRIP_LAST50_K_M_OIL_CON_AVG_R = 559944771;
    public static final int ID_TRIP_OIL_CON_AVG_R = 559944765;
    public static final int ID_TRIP_OTHER_PERCENT50_K_M_R = 557847612;
    public static final int ID_TRIP_OTHER_PERCENT_R = 557847607;
    public static final int ID_TRIP_POINT_FLAG_R = 557847608;
    public static final int ID_Y_ENERGY_CON_LIMIT_R = 557847617;
    public static final int ID_Y_ENERGY_CON_R = 559944767;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.energy";
    public static final String TAG = "CAR.VENDOR.ENERGY";

    public interface OnCarEnergyEventListener {
        void onEnergyErrorEvent(int i, int i2);

        void onEnergyEventChange(EnergyEventChangeValue energyEventChangeValue);
    }

    void addCarEnergyEventListener(OnCarEnergyEventListener onCarEnergyEventListener) throws CarNotConnectedException;

    int getChangePHintR() throws CarNotConnectedException;

    int getChargePortCoverTipR() throws CarNotConnectedException;

    int getDriveDistanceShowModeR() throws CarNotConnectedException;

    int getDriveDistanceShowStandardR() throws CarNotConnectedException;

    int getEPedalStateR() throws CarNotConnectedException;

    int getElectricPointFlag() throws CarNotConnectedException;

    int getElectricShowModeR() throws CarNotConnectedException;

    int getEnergyMode() throws CarNotConnectedException;

    int getEnergyStateR() throws CarNotConnectedException;

    int getHevElectricPointFlagR() throws CarNotConnectedException;

    float getHevLast200MElectricConR() throws CarNotConnectedException;

    float getLast200MElectricCon() throws CarNotConnectedException;

    float getLast200MOilCon() throws CarNotConnectedException;

    float getLast50KMElectricConAvg() throws CarNotConnectedException;

    float getLast50KMOilConAvg() throws CarNotConnectedException;

    int getMandatoryElectricStateR() throws CarNotConnectedException;

    int getMemoryElectricStateR() throws CarNotConnectedException;

    int getMemoryMaxEvStateR() throws CarNotConnectedException;

    int getMemorySportStateR() throws CarNotConnectedException;

    int getOilPointFlag() throws CarNotConnectedException;

    int getOperationMode() throws CarNotConnectedException;

    int getOperationModeNormalR() throws CarNotConnectedException;

    int getPowerGenerationState() throws CarNotConnectedException;

    int getPowerGenerationValue() throws CarNotConnectedException;

    int getPreSaleModelStateR() throws CarNotConnectedException;

    int getRoadSurfaceMode() throws CarNotConnectedException;

    int getRoadSurfaceModeNormalR() throws CarNotConnectedException;

    int getRollBenchModeStatusR() throws CarNotConnectedException;

    int getTripAcPercent50kmR() throws CarNotConnectedException;

    int getTripAcPercentR() throws CarNotConnectedException;

    int getTripDrivePercent50kmR() throws CarNotConnectedException;

    int getTripDrivePercentR() throws CarNotConnectedException;

    int getTripElecAppliancePercent50kmR() throws CarNotConnectedException;

    int getTripElecAppliancePercentR() throws CarNotConnectedException;

    float getTripElecConAvgR() throws CarNotConnectedException;

    float getTripEnergyConAvgR() throws CarNotConnectedException;

    float getTripLast50kmElectricConAvgR() throws CarNotConnectedException;

    float getTripLast50kmFuelConAvgR() throws CarNotConnectedException;

    float getTripLast50kmOilConAvgR() throws CarNotConnectedException;

    float getTripOilConAvgR() throws CarNotConnectedException;

    int getTripOtherPercent50kmR() throws CarNotConnectedException;

    int getTripOtherPercentR() throws CarNotConnectedException;

    int getTripPointFlagR() throws CarNotConnectedException;

    int getYEnergyConLimitR() throws CarNotConnectedException;

    float getYEnergyConR() throws CarNotConnectedException;

    int getiTacExitTagR() throws CarNotConnectedException;

    int getiTacModeR() throws CarNotConnectedException;

    int getiTacpAllowTagR() throws CarNotConnectedException;

    int getiTacpStateR() throws CarNotConnectedException;

    void removeCarEnergyEventListener(OnCarEnergyEventListener onCarEnergyEventListener) throws CarNotConnectedException;

    void setChargePortCoverHintS(int i) throws CarNotConnectedException;

    void setConfirmChargePortCoverS(int i) throws CarNotConnectedException;

    void setDriveDistanceShowModeS(int i) throws CarNotConnectedException;

    void setDriveDistanceShowStandardS(int i) throws CarNotConnectedException;

    void setEPedalStateS(int i) throws CarNotConnectedException;

    void setElectricShowModeS(int i) throws CarNotConnectedException;

    void setMandatoryElectricStateS(int i) throws CarNotConnectedException;

    void setMemoryElectricStateS(int i) throws CarNotConnectedException;

    void setMemoryMaxEvStateS(int i) throws CarNotConnectedException;

    void setMemorySportStateS(int i) throws CarNotConnectedException;

    void setOperationModeS(int i) throws CarNotConnectedException;

    void setPreSaleModelStateS(int i) throws CarNotConnectedException;

    void setResetCurveData(int i) throws CarNotConnectedException;

    void setResetCurveDataS(int i) throws CarNotConnectedException;

    void setiTacModeS(int i) throws CarNotConnectedException;

    void setiTacpStateS(int i) throws CarNotConnectedException;
}
