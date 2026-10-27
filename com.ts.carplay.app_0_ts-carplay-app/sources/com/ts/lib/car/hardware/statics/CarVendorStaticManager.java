package com.ts.lib.car.hardware.statics;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes.dex */
public interface CarVendorStaticManager {
    public static final int ID_AVERAGE_SPEEDS = 557859854;
    public static final int ID_AVERAGE_SPEED_VALUES = 557859855;
    public static final int ID_BATTERY_PACK_ENERGY_R = 559957011;
    public static final int ID_CURRENT_TRIP_ELEC_R = 559967007;
    public static final int ID_CURRENT_TRIP_ELEC_UNIT_R = 557869856;
    public static final int ID_CURRENT_TRIP_FUEL_R = 559967010;
    public static final int ID_CURRENT_TRIP_FUEL_UNIT_R = 557875217;
    public static final int ID_CURRENT_TRIP_MILEAGE_R = 559972404;
    public static final int ID_CURRENT_TRIP_MILEAGE_UNIT_R = 557875259;
    public static final int ID_CURRENT_TRIP_TIME_R = 557869857;
    public static final int ID_DRIVING_RANGE_ALL_R = 557859877;
    public static final int ID_DRIVING_RANGE_UNIT_R = 557859878;
    public static final int ID_DRIVING_TIME_VALUER = 559956992;
    public static final int ID_DRIVING_TIME_VALUES = 557859853;
    public static final int ID_DRIVING_TIME_VALUE_TWOR = 559957010;
    public static final int ID_ELEC_CONS_UNIT_R = 557875222;
    public static final int ID_ELEC_DRIVING_RANGE_BY_STANDARD_R = 557859841;
    public static final int ID_ELEC_DRIVING_RANGE_VALUER = 557859841;
    public static final int ID_ELEC_DRIVING_RANGE_VALUE_TWOR = 557875215;
    public static final int ID_ELEC_PERCENTAGE_VALUER = 559956994;
    public static final int ID_ELEC_UNIT_R = 557875258;
    public static final int ID_EV_REMAINING_BATTERY_POWER_R = 559957025;
    public static final int ID_EXT_CHARGE_CAPACITYS = 557859852;
    public static final int ID_E_V_MILEAGE_VALUES = 557859857;
    public static final int ID_FUEL_DRIVING_RANGE_VALUER = 557859843;
    public static final int ID_FUEL_PERCENTAGE_VALUER = 557859844;
    public static final int ID_H_E_V_MILEAGE_VALUES = 557859856;
    public static final int ID_KEY_BATTERY_LEVELR = 557859850;
    public static final int ID_MILEAGE_NUMBER_ONER = 559965186;
    public static final int ID_MILEAGE_NUMBER_TWOR = 559965187;
    public static final int ID_REMAINING_BATTERY_POWER_R = 559957024;
    public static final int ID_RESET_STATISTIC_DATE_S = 557925412;
    public static final int ID_RESET_TRAVEL_DATE_S = 557859880;
    public static final int ID_ROAD_ACCIDENT_S = 557859876;
    public static final int ID_ROAD_CONGESTED_S = 557859874;
    public static final int ID_ROAD_CONSTRUCTION_S = 557859875;
    public static final int ID_ROAD_IMPASSABLE_S = 557859877;
    public static final int ID_SEND_ISA_DATA_TO_ADAS_S = 557925415;
    public static final int ID_SPEED_UNIT_R = 557875219;
    public static final int ID_TOTAL_ELEC_CON_P_H_M_VALUER = 559956997;
    public static final int ID_TOTAL_ELEC_CON_VALUER = 559957000;
    public static final int ID_TOTAL_FUEL_CON_P_H_M_VALUER = 559956998;
    public static final int ID_TOTAL_FUEL_CON_VALUER = 559956999;
    public static final int ID_TOTAL_FUEL_CON_VALUES = 557859851;
    public static final int ID_TOTAL_MILEAGE_VALUER = 559957001;
    public static final int ID_TRAFFIC_SIGH_TYPE_S = 557859878;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.static";
    public static final String TAG = "CAR.VENDOR.STATIC";

    public interface OnCarStaticEventListener {
        void onStaticErrorEvent(int i, int i2);

        void onStaticEventChange(StaticEventChangeValue staticEventChangeValue);
    }

    void addCarStaticEventListener(OnCarStaticEventListener onCarStaticEventListener) throws CarNotConnectedException;

    int getAverageSpeedValueS() throws CarNotConnectedException;

    float getBatteryPackEnergyR() throws CarNotConnectedException;

    float getCurrentTripElecR() throws CarNotConnectedException;

    int getCurrentTripElecUnitR() throws CarNotConnectedException;

    float getCurrentTripFuelR() throws CarNotConnectedException;

    int getCurrentTripFuelUnitR() throws CarNotConnectedException;

    float getCurrentTripMileageR() throws CarNotConnectedException;

    int getCurrentTripMileageUnitR() throws CarNotConnectedException;

    int getCurrentTripTimeR() throws CarNotConnectedException;

    int getDrivingRangeAllR() throws CarNotConnectedException;

    int getDrivingRangeUnitR() throws CarNotConnectedException;

    float getDrivingTimeValueR() throws CarNotConnectedException;

    float getDrivingTimeValueTwoR() throws CarNotConnectedException;

    int getEVMileageValueS() throws CarNotConnectedException;

    int getElecConsUnitR() throws CarNotConnectedException;

    int getElecDrivingRangeByStandardR() throws CarNotConnectedException;

    int getElecDrivingRangeValueR() throws CarNotConnectedException;

    int getElecDrivingRangeValueTwoR() throws CarNotConnectedException;

    float getElecPercentageValueR() throws CarNotConnectedException;

    int getElecUnitR() throws CarNotConnectedException;

    float getEvRemainingBatteryPowerR() throws CarNotConnectedException;

    int getFuelDrivingRangeValueR() throws CarNotConnectedException;

    int getFuelPercentageValueR() throws CarNotConnectedException;

    int getHEVMileageValueS() throws CarNotConnectedException;

    int getKeyBatteryLevelR() throws CarNotConnectedException;

    float getMileageNumberOneR() throws CarNotConnectedException;

    float getMileageNumberTwoR() throws CarNotConnectedException;

    float getRemainingBatteryPowerR() throws CarNotConnectedException;

    int getSpeedUnitR() throws CarNotConnectedException;

    float getTotalElecConPHMValueR() throws CarNotConnectedException;

    float getTotalElecConValueR() throws CarNotConnectedException;

    float getTotalFuelConPHMValueR() throws CarNotConnectedException;

    float getTotalFuelConValueR() throws CarNotConnectedException;

    float getTotalMileageValueR() throws CarNotConnectedException;

    void removeCarStaticEventListener(OnCarStaticEventListener onCarStaticEventListener) throws CarNotConnectedException;

    void resetStatisticDateS(int[] iArr) throws CarNotConnectedException;

    void setAverageSpeedS(int i) throws CarNotConnectedException;

    void setDrivingTimeValueS(int i) throws CarNotConnectedException;

    void setExtChargeCapacityS(int i) throws CarNotConnectedException;

    void setResetTravelDateS(int i) throws CarNotConnectedException;

    void setRoadAccidentS(int i) throws CarNotConnectedException;

    void setRoadCongestedS(int i) throws CarNotConnectedException;

    void setRoadConstructionS(int i) throws CarNotConnectedException;

    void setRoadImpassableS(int i) throws CarNotConnectedException;

    void setSendIsaData2AdasS(int[] iArr) throws CarNotConnectedException;

    void setTotalFuelConValueS(int i) throws CarNotConnectedException;

    void setTrafficSignTypeS(int i) throws CarNotConnectedException;
}
