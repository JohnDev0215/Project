package android.hardware.bydauto.statistic;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.statistic.tsimpl.StatisticManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoStatisticDevice extends AbsBYDAutoDevice {
    public static final double AVERAGE_ELEC_CON_PHM_MAX = 99.9d;
    public static final double AVERAGE_ELEC_CON_PHM_MIN = -99.9d;
    public static final double AVERAGE_FUEL_CON_PHM_MAX = 51.1d;
    public static final double AVERAGE_FUEL_CON_PHM_MIN = 0.0d;
    public static final double AVERAGE_SPEED_MAX = 281.5d;
    public static final double AVERAGE_SPEED_MIN = 0.0d;
    public static final int CURVE_FLAG_INVALID = 0;
    public static final int CURVE_FLAG_VALID = 1;
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int DRIVING_MILEAGE_CONFIG_DISABLE = 1;
    public static final int DRIVING_MILEAGE_CONFIG_SELECTABLE = 2;
    public static final int DRIVING_MILEAGE_MODE_DYNAMIC = 2;
    public static final int DRIVING_MILEAGE_MODE_INVALID = 0;
    public static final int DRIVING_MILEAGE_MODE_STANDARD = 1;
    public static final double ELEC_CONSUMPTION_MAX = 1676721.4d;
    public static final double ELEC_CONSUMPTION_MIN = -1000.0d;
    public static final int EV_PH_MAX = 1346;
    public static final int EV_PH_MIN = -700;
    public static final String FEATURE_FUEL_MILEAGE = "FuelMileage";
    public static final double FUEL_CONSUMPTION_MAX = 104857.4d;
    public static final double FUEL_CONSUMPTION_MIN = 0.0d;
    public static final int FUEL_PH_MAX = 510;
    public static final int FUEL_PH_MIN = 0;
    public static final int MESSAGE_3D9_SUBID_55 = 1;
    public static final int MESSAGE_3D9_SUBID_57 = 2;
    public static final int MESSAGE_OFFLINE = 0;
    public static final int MESSAGE_ONLINE = 1;
    public static final int MILEAGE_MAX = 99999;
    public static final int MILEAGE_MIN = 0;
    public static final int MILEAGE_ONE = 0;
    public static final int MILEAGE_TOTAL = 2;
    public static final int MILEAGE_TWO = 1;
    public static final double MILE_TOTAL_MAX = 9999999.9d;
    public static final double MILE_TOTAL_MIN = 0.0d;
    public static final String PERMISSION_BYDAUTO_STATISTIC_COMMON = "android.permission.BYDAUTO_STATISTIC_COMMON";
    public static final String PERMISSION_BYDAUTO_STATISTIC_GET = "android.permission.BYDAUTO_STATISTIC_GET";
    public static final String PERMISSION_BYDAUTO_STATISTIC_SET = "android.permission.BYDAUTO_STATISTIC_SET";
    public static final int SOC_BATTERY_PERCENTAGE_MAX = 100;
    public static final int SOC_BATTERY_PERCENTAGE_MIN = 0;
    public static final int SPEED_AVERAGE_MAX = 240;
    public static final int SPEED_AVERAGE_MIN = 0;
    public static final int STATE_BEACONSIGN = 5;
    public static final int STATE_DATA_CLEAR = 1;
    public static final int STATE_DATA_NORMAL = 0;
    public static final int STATE_FALSE = 2;
    public static final int STATE_INTERSECTIONSIGN = 2;
    public static final int STATE_INVALID = 0;
    public static final int STATE_REDLIGHTSIGN = 3;
    public static final int STATE_ROUNDABOUTSIGN = 1;
    public static final int STATE_STOPSIGN = 4;
    public static final int STATE_TRUE = 1;
    public static final int STATE_UNKNOWN = 6;
    public static final int STATISTIC_COMMAND_BUSY = -2147482647;
    public static final int STATISTIC_COMMAND_FAILED = -2147482648;
    public static final int STATISTIC_COMMAND_INVALID_VALUE = -2147482645;
    public static final int STATISTIC_COMMAND_SUCCESS = 0;
    public static final int STATISTIC_COMMAND_TIMEOUT = -2147482646;
    public static final double STATISTIC_DRIVING_TIME_MAX = 99.9d;
    public static final double STATISTIC_DRIVING_TIME_MIN = 0.0d;
    public static final int STATISTIC_ELEC_DRIVING_RANGE_DEFAULT = 1023;
    public static final int STATISTIC_ELEC_DRIVING_RANGE_INVALID = 1000;
    public static final int STATISTIC_ELEC_DRIVING_RANGE_MAX = 999;
    public static final int STATISTIC_ELEC_DRIVING_RANGE_MIN = 0;
    public static final double STATISTIC_ELEC_PERCENTAGE_MAX = 100.0d;
    public static final double STATISTIC_ELEC_PERCENTAGE_MIN = 0.0d;
    public static final int STATISTIC_FUEL_AD_MAX = 4095;
    public static final int STATISTIC_FUEL_AD_MIN = 0;
    public static final int STATISTIC_FUEL_DRIVING_RANGE_MAX = 1500;
    public static final int STATISTIC_FUEL_DRIVING_RANGE_MIN = 31;
    public static final int STATISTIC_FUEL_PERCENTAGE_MAX = 100;
    public static final int STATISTIC_FUEL_PERCENTAGE_MIN = 0;
    public static final double STATISTIC_INSTANT_ELEC_CON_MAX = 2000.0d;
    public static final double STATISTIC_INSTANT_ELEC_CON_MIN = -2000.0d;
    public static final double STATISTIC_INSTANT_FUEL_CON_MAX = 0.0d;
    public static final double STATISTIC_INSTANT_FUEL_CON_MIN = 0.0d;
    public static final int STATISTIC_KEY_BATTERY_LEVEL_LOW = 1;
    public static final int STATISTIC_KEY_BATTERY_LEVEL_NORMAL = 2;
    public static final double STATISTIC_LAST_ELEC_CON_PHM_MAX = 99.9d;
    public static final double STATISTIC_LAST_ELEC_CON_PHM_MIN = -99.9d;
    public static final double STATISTIC_LAST_FUEL_CON_PHM_MAX = 51.1d;
    public static final double STATISTIC_LAST_FUEL_CON_PHM_MIN = 0.0d;
    public static final int STATISTIC_MILEAGE_MAX = 999999;
    public static final int STATISTIC_MILEAGE_MIN = 0;
    public static final int STATISTIC_RANGE_UNIT_KM = 1;
    public static final int STATISTIC_RANGE_UNIT_MILE = 2;
    public static final double STATISTIC_TOTAL_ELEC_CONSUMPTION_MAX = 1676721.4d;
    public static final double STATISTIC_TOTAL_ELEC_CONSUMPTION_MIN = -1000.0d;
    public static final double STATISTIC_TOTAL_ELEC_CON_PHM_MAX = 99.9d;
    public static final double STATISTIC_TOTAL_ELEC_CON_PHM_MIN = -99.9d;
    public static final double STATISTIC_TOTAL_FUEL_CONSUMPTION_MAX = 9999.9d;
    public static final double STATISTIC_TOTAL_FUEL_CONSUMPTION_MIN = 0.0d;
    public static final double STATISTIC_TOTAL_FUEL_CON_PHM_MAX = 99.9d;
    public static final double STATISTIC_TOTAL_FUEL_CON_PHM_MIN = 0.0d;
    public static final int STATISTIC_TOTAL_MILEAGE_MAX = 0;
    public static final int STATISTIC_TOTAL_MILEAGE_MIN = 0;
    public static final int STATISTIC_WATER_TEMPERATURE_MAX = 255;
    public static final int STATISTIC_WATER_TEMPERATURE_MIN = 0;
    protected static final String TAG = "BYDAutoStatisticDevice";
    public static final int TARGET_MILEAGE1 = 2;
    public static final int TARGET_MILEAGE2 = 3;
    public static final int TARGET_TOTAL = 1;
    public static final double TRAVEL_TIME_MAX = 99999.0d;
    public static final double TRAVEL_TIME_MIN = 0.0d;
    public static final int UNIT_HP_100KM = 3;
    public static final int UNIT_HP_100MILES = 4;
    public static final int UNIT_INVALID = 0;
    public static final int UNIT_KW_100KM = 1;
    public static final int UNIT_KW_100MILES = 2;
    public static final int UNIT_TOTAL_FUEL_CONSUMPTION_GAL = 2;
    public static final int UNIT_TOTAL_FUEL_CONSUMPTION_INVALID = 0;
    public static final int UNIT_TOTAL_FUEL_CONSUMPTION_L = 1;
    private static volatile BYDAutoStatisticDevice mBYDAutoStatisticDevice;
    private StatisticManagerImpl mStatisticManagerImpl;

    public void getAllStatus() {
    }

    public float getAverageElectricConsumption(int i) {
        return 0.0f;
    }

    public int getAverageEvCurveFlag() {
        return 0;
    }

    public int getAverageEvLast200M() {
        return 0;
    }

    public float getAverageFuelConsumption(int i) {
        return 0.0f;
    }

    public int getAverageFuelCurveFlag() {
        return 0;
    }

    public int getAverageFuelLast200M() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public int getEVDrivingMileageConfig() {
        return 0;
    }

    public int getEVDrivingMileageMode() {
        return 0;
    }

    public float getElectricConsumption(int i) {
        return 0.0f;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public int getFuelADValue() {
        return 0;
    }

    public float getFuelConsumption(int i) {
        return 0.0f;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "android.permission.BYDAUTO_STATISTIC_GET";
    }

    public float getInstantElecConValue() {
        return 0.0f;
    }

    public float getInstantFuelConValue() {
        return 0.0f;
    }

    public float getLast50KmEqualFuelConValue() {
        return 0.0f;
    }

    public float getLastElecConPHMValue() {
        return 0.0f;
    }

    public float getLastFuelConPHMValue() {
        return 0.0f;
    }

    public int getMessage5sOnlineState(int i) {
        return 0;
    }

    public int getSOCBatteryPercentage() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "android.permission.BYDAUTO_STATISTIC_SET";
    }

    public int getSpeedSignalVDisValue() {
        return 0;
    }

    public int getTotalElecConPhmUnit() {
        return 0;
    }

    public int getTotalFuelConsumptionUnit() {
        return 0;
    }

    public float getTravelTime(int i) {
        return 0.0f;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int getWaterTemperature() {
        return 0;
    }

    public int hasFeature(String str) {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, float f, Object obj) {
        return true;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return true;
    }

    public void registerListener(AbsBYDAutoStatisticListener absBYDAutoStatisticListener, int[] iArr) {
    }

    public int setEVDrivingMileageMode(int i) {
        return 0;
    }

    private BYDAutoStatisticDevice(Context context) {
        super(context);
        this.mStatisticManagerImpl = StatisticManagerImpl.getInstance(context);
    }

    public static BYDAutoStatisticDevice getInstance(Context context) {
        if (mBYDAutoStatisticDevice == null) {
            synchronized (BYDAutoStatisticDevice.class) {
                if (mBYDAutoStatisticDevice == null) {
                    mBYDAutoStatisticDevice = new BYDAutoStatisticDevice(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoStatisticDevice;
    }

    public float getTotalMileageValue() {
        return this.mStatisticManagerImpl.getTotalMileageValue();
    }

    public double getTotalFuelConValue() {
        return this.mStatisticManagerImpl.getTotalFuelConValue();
    }

    public double getTotalElecConValue() {
        return this.mStatisticManagerImpl.getTotalElecConValue();
    }

    public double getDrivingTimeValue() {
        return this.mStatisticManagerImpl.getDrivingTimeValue();
    }

    public float getBatteryPackEnergy() {
        return this.mStatisticManagerImpl.getBatteryPackEnergy();
    }

    public double getTotalFuelConPHMValue() {
        return this.mStatisticManagerImpl.getTotalFuelConPhmValue();
    }

    public double getTotalElecConPHMValue() {
        return this.mStatisticManagerImpl.getTotalElecConPHMValue();
    }

    public int getElecDrivingRangeValue() {
        return this.mStatisticManagerImpl.getElecDrivingRangeValue();
    }

    public int getFuelDrivingRangeValue() {
        return this.mStatisticManagerImpl.getFuelDrivingRangeValue();
    }

    public int getFuelPercentageValue() {
        return this.mStatisticManagerImpl.getFuelPercentageValue();
    }

    public double getElecPercentageValue() {
        return this.mStatisticManagerImpl.getElecPercentageValue();
    }

    public float getAverageSpeedValue() {
        return this.mStatisticManagerImpl.getAverageSpeedValue();
    }

    public int getKeyBatteryLevel() {
        return this.mStatisticManagerImpl.getKeyBatteryLevel();
    }

    public int getEVMileageValue() {
        return this.mStatisticManagerImpl.getEVMileageValue();
    }

    public int getHEVMileageValue() {
        return this.mStatisticManagerImpl.getHEVMileageValue();
    }

    public float getMileageNumber(int i) {
        return this.mStatisticManagerImpl.getMileageNumber(i);
    }

    public int getElecDrivingRangeByStandard() {
        return this.mStatisticManagerImpl.getElecDrivingRangeByStandard();
    }

    public int resetData() {
        return this.mStatisticManagerImpl.resetData();
    }

    @Deprecated
    public int resetTotalFuelConValue(int i) {
        return this.mStatisticManagerImpl.resetTotalFuelConValue(i);
    }

    @Deprecated
    public int resetExtChargeCapacity(int i) {
        return this.mStatisticManagerImpl.resetExtChargeCapacity(i);
    }

    @Deprecated
    public int resetDrivingTimeValue(int i) {
        return this.mStatisticManagerImpl.resetDrivingTimeValue(i);
    }

    @Deprecated
    public int resetAverageSpeed(int i) {
        return this.mStatisticManagerImpl.resetAverageSpeed(i);
    }

    public int resetStatisticDate(int i, int i2, int i3, int i4) {
        return this.mStatisticManagerImpl.resetStatisticDate(i, i2, i3, i4);
    }

    public void registerListener(AbsBYDAutoStatisticListener absBYDAutoStatisticListener) {
        this.mStatisticManagerImpl.registerListener(absBYDAutoStatisticListener);
    }

    public void unregisterListener(AbsBYDAutoStatisticListener absBYDAutoStatisticListener) {
        this.mStatisticManagerImpl.unregisterListener(absBYDAutoStatisticListener);
    }

    public float getAverageSpeed(int i) {
        return this.mStatisticManagerImpl.getAverageSpeed(i);
    }

    public float getRemainingBatteryPower() {
        return this.mStatisticManagerImpl.getRemainingBatteryPower();
    }

    public float getEVRemainingBatteryPower() {
        return this.mStatisticManagerImpl.getEvRemainingBatteryPower();
    }

    @Deprecated
    public int setRoadCongested(int i) {
        return this.mStatisticManagerImpl.setRoadCongestedS(i);
    }

    @Deprecated
    public int setRoadConstruction(int i) {
        return this.mStatisticManagerImpl.setRoadConstructionS(i);
    }

    @Deprecated
    public int setRoadAccident(int i) {
        return this.mStatisticManagerImpl.setRoadAccidentS(i);
    }

    @Deprecated
    public int setRoadImpassable(int i) {
        return this.mStatisticManagerImpl.setRoadImpassableS(i);
    }

    @Deprecated
    public int setTrafficSignType(int i) {
        return this.mStatisticManagerImpl.setTrafficSignTypeS(i);
    }

    public int sendIsaDataToAdas(int[] iArr) {
        return this.mStatisticManagerImpl.sendIsaDataToAdas(iArr);
    }

    public int getDrivingRangeAll() {
        return this.mStatisticManagerImpl.getDrivingRangeAll();
    }

    public int getDrivingRangeUnit() {
        return this.mStatisticManagerImpl.getDrivingRangeUnit();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mStatisticManagerImpl.get(iArr, cls);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mStatisticManagerImpl.set(iArr, bYDAutoEventValue);
    }
}
