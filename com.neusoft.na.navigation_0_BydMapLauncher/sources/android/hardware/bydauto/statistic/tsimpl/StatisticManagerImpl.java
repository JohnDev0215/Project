package android.hardware.bydauto.statistic.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.statistic.AbsBYDAutoStatisticListener;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.general.GeneralPacket;
import com.ts.lib.caradapter.general.OnCarGeneralChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class StatisticManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_StatisticManagerImpl";
    protected static final String TAG = "StatisticManagerImpl";
    private static volatile StatisticManagerImpl mStatisticManagerImpl;
    private AbsBYDAutoStatisticListener mAbsBydAutoStatisticListener;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private TsManagerImpl mTsManagerImpl;
    private CopyOnWriteArrayList<AbsBYDAutoStatisticListener> mAbsBydAutoStatisticListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    private StatisticManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnGeneralChangedListener(new OnCarGeneralChangedListener() { // from class: android.hardware.bydauto.statistic.tsimpl.StatisticManagerImpl.1
            public void onCarGeneralChanged(int i2, int i3, GeneralPacket generalPacket) {
                if (StatisticManagerImpl.this.mAbsBydAutoStatisticListeners.isEmpty()) {
                    return;
                }
                try {
                    StatisticManagerImpl.this.statisticCallback(i2, i3, generalPacket);
                } catch (Exception unused) {
                    Log.e(StatisticManagerImpl.TAG, "statisticCallback is error");
                }
            }

            public void onCarGeneralBinderChanged(boolean z) {
                Log.v(StatisticManagerImpl.TAG, "onBinderStateChanged is " + z);
            }

            public void onCarClusterBrightnessChanged(int i2) {
                Log.v(StatisticManagerImpl.TAG, "onCarClusterBrightnessChanged is " + i2);
            }
        });
    }

    public static StatisticManagerImpl getInstance(Context context) {
        if (mStatisticManagerImpl == null) {
            synchronized (StatisticManagerImpl.class) {
                if (mStatisticManagerImpl == null) {
                    mStatisticManagerImpl = new StatisticManagerImpl(context);
                }
            }
        }
        return mStatisticManagerImpl;
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException("requires permission " + str);
        }
    }

    public void statisticCallback(int i, int i2, GeneralPacket generalPacket) {
        Log.i(TAG, "statisticCallback propertyId:" + i);
        switch (i) {
            case StatisticHalid.ID_FUEL_DRIVING_RANGE_VALUER /* 557859843 */:
                Iterator<AbsBYDAutoStatisticListener> it = this.mAbsBydAutoStatisticListeners.iterator();
                while (it.hasNext()) {
                    it.next().onFuelDrivingRangeChanged(generalPacket.getFuelDrivingRangeValueR());
                }
                caseFeatureIds(276, Integer.valueOf(generalPacket.getFuelDrivingRangeValueR()));
                break;
            case StatisticHalid.ID_FUEL_PERCENTAGE_VALUER /* 557859844 */:
                Iterator<AbsBYDAutoStatisticListener> it2 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onFuelPercentageChanged(generalPacket.getFuelPercentageValueR());
                }
                caseFeatureIds(278, Integer.valueOf(generalPacket.getFuelPercentageValueR()));
                break;
            case 557859850:
                Iterator<AbsBYDAutoStatisticListener> it3 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onKeyBatteryLevelChanged(generalPacket.getKeyBatteryLevelR());
                }
                break;
            case StatisticHalid.ID_AVERAGE_SPEED_VALUES /* 557859855 */:
                Iterator<AbsBYDAutoStatisticListener> it4 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onAverageSpeedChanged(generalPacket.getAverageSpeedValueS());
                }
                caseFeatureIds(3841, Integer.valueOf(generalPacket.getAverageSpeedValueS()));
                break;
            case StatisticHalid.ID_E_V_MILEAGE_VALUES /* 557859857 */:
                Iterator<AbsBYDAutoStatisticListener> it5 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onEVMileageValueChanged(generalPacket.getEVMileageValueS());
                }
                caseFeatureIds(307, Integer.valueOf(generalPacket.getEVMileageValueS()));
                break;
            case StatisticHalid.ID_DRIVING_RANGE_ALL_R /* 557859877 */:
                Iterator<AbsBYDAutoStatisticListener> it6 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onDrivingRangeAllChanged(generalPacket.getDrivingRangeAllR());
                }
                break;
            case StatisticHalid.ID_DRIVING_RANGE_UNIT_R /* 557859878 */:
                Iterator<AbsBYDAutoStatisticListener> it7 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onDrivingRangeUnitChanged(generalPacket.getDrivingRangeUnitR());
                }
                break;
            case StatisticHalid.ID_CURRENT_TRIP_ELEC_UNIT_R /* 557869856 */:
                Iterator<AbsBYDAutoStatisticListener> it8 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onCurrentTripElecUnitChanged(generalPacket.getCurrentTripElecUnitR());
                }
                break;
            case StatisticHalid.ID_CURRENT_TRIP_TIME_R /* 557869857 */:
                Iterator<AbsBYDAutoStatisticListener> it9 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onCurrentTripTimeChanged(generalPacket.getCurrentTripTimeR());
                }
                break;
            case StatisticHalid.ID_ELEC_DRIVING_RANGE_VALUE_TWOR /* 557875215 */:
                Iterator<AbsBYDAutoStatisticListener> it10 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onElecDrivingRangeChanged(generalPacket.getElecDrivingRangeValueTwoR());
                }
                break;
            case StatisticHalid.ID_CURRENT_TRIP_FUEL_UNIT_R /* 557875217 */:
                Iterator<AbsBYDAutoStatisticListener> it11 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onCurrentTripFuelUnitChanged(generalPacket.getCurrentTripFuelUnitR());
                }
                break;
            case StatisticHalid.ID_SPEED_UNIT_R /* 557875219 */:
                Iterator<AbsBYDAutoStatisticListener> it12 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onSpeedUnitChanged(generalPacket.getSpeedUnitR());
                }
                break;
            case StatisticHalid.ID_ELEC_CONS_UNIT_R /* 557875222 */:
                Iterator<AbsBYDAutoStatisticListener> it13 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onElecConsUnitChanged(generalPacket.getElecConsUnitR());
                }
                break;
            case StatisticHalid.ID_ELEC_UNIT_R /* 557875258 */:
                Iterator<AbsBYDAutoStatisticListener> it14 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onElecUnitChanged(generalPacket.getElecUnitR());
                }
                break;
            case StatisticHalid.ID_CURRENT_TRIP_MILEAGE_UNIT_R /* 557875259 */:
                Iterator<AbsBYDAutoStatisticListener> it15 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onCurrentTripMileageUnitChanged(generalPacket.getCurrentTripMileageUnitR());
                }
                break;
            case StatisticHalid.ID_ELEC_PERCENTAGE_VALUER /* 559956994 */:
                Iterator<AbsBYDAutoStatisticListener> it16 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onElecPercentageChanged(generalPacket.getElecPercentageValueR());
                }
                caseFeatureIds(270, Float.valueOf(generalPacket.getElecPercentageValueR()));
                break;
            case StatisticHalid.ID_TOTAL_ELEC_CON_P_H_M_VALUER /* 559956997 */:
                Iterator<AbsBYDAutoStatisticListener> it17 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onTotalElecConPHMChanged(generalPacket.getTotalElecConPHMValueR());
                }
                caseFeatureIds(319, Float.valueOf(generalPacket.getTotalElecConPHMValueR()));
                break;
            case StatisticHalid.ID_TOTAL_FUEL_CON_P_H_M_VALUER /* 559956998 */:
                Iterator<AbsBYDAutoStatisticListener> it18 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onTotalFuelConPHMChanged(generalPacket.getTotalFuelConPHMValueR());
                }
                break;
            case StatisticHalid.ID_TOTAL_FUEL_CON_VALUER /* 559956999 */:
                Iterator<AbsBYDAutoStatisticListener> it19 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onTotalFuelConChanged(generalPacket.getTotalFuelConValueR());
                }
                break;
            case StatisticHalid.ID_TOTAL_ELEC_CON_VALUER /* 559957000 */:
                Iterator<AbsBYDAutoStatisticListener> it20 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onTotalElecConChanged(generalPacket.getTotalElecConValueR());
                }
                break;
            case StatisticHalid.ID_TOTAL_MILEAGE_VALUER /* 559957001 */:
                Iterator<AbsBYDAutoStatisticListener> it21 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onTotalMileageValueChanged(generalPacket.getTotalMileageValueR());
                }
                caseFeatureIds(326, Float.valueOf(generalPacket.getTotalMileageValueR()));
                break;
            case StatisticHalid.ID_DRIVING_TIME_VALUE_TWOR /* 559957010 */:
                Iterator<AbsBYDAutoStatisticListener> it22 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onDrivingTimeChanged(generalPacket.getDrivingTimeValueTwoR());
                }
                caseFeatureIds(267, Float.valueOf(generalPacket.getDrivingTimeValueTwoR()));
                break;
            case StatisticHalid.ID_REMAINING_BATTERY_POWER_R /* 559957024 */:
                Iterator<AbsBYDAutoStatisticListener> it23 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onRemainingBatteryPowerChanged(generalPacket.getRemainingBatteryPowerR());
                }
                break;
            case StatisticHalid.ID_EV_REMAINING_BATTERY_POWER_R /* 559957025 */:
                Iterator<AbsBYDAutoStatisticListener> it24 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onEVRemainingBatteryPowerChanged(generalPacket.getEvRemainingBatteryPowerR());
                }
                break;
            case StatisticHalid.ID_MILEAGE_NUMBER_ONER /* 559965186 */:
                Iterator<AbsBYDAutoStatisticListener> it25 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onMileageNumberChanged(0, generalPacket.getMileageNumberOneR());
                }
                break;
            case StatisticHalid.ID_MILEAGE_NUMBER_TWOR /* 559965187 */:
                Iterator<AbsBYDAutoStatisticListener> it26 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().onMileageNumberChanged(1, generalPacket.getMileageNumberTwoR());
                }
                break;
            case StatisticHalid.ID_CURRENT_TRIP_ELEC_R /* 559967007 */:
                Iterator<AbsBYDAutoStatisticListener> it27 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onCurrentTripElecChanged(generalPacket.getCurrentTripElecR());
                }
                break;
            case StatisticHalid.ID_CURRENT_TRIP_FUEL_R /* 559967010 */:
                Iterator<AbsBYDAutoStatisticListener> it28 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onCurrentTripFuelChanged(generalPacket.getCurrentTripFuelR());
                }
                break;
            case StatisticHalid.ID_CURRENT_TRIP_MILEAGE_R /* 559972404 */:
                Iterator<AbsBYDAutoStatisticListener> it29 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onCurrentTripMileageChanged(generalPacket.getCurrentTripMileageR());
                }
                break;
        }
    }

    public int getElecDrivingRangeByStandard() {
        Log.v(TAG, "getElecDrivingRangeByStandard is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getElecDrivingRangeByStandard mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getElecDrivingRangeByStandard();
    }

    public float getDrivingTimeValue() {
        Log.v(TAG, "getDrivingTimeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDrivingTimeValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getDrivingTimeValue();
    }

    public float getBatteryPackEnergy() {
        Log.v(TAG, "getBatteryPackEnergy is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getBatteryPackEnergy mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getBatteryPackEnergy();
    }

    public int getElecDrivingRangeValue() {
        Log.v(TAG, "getElecDrivingRangeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getElecDrivingRangeValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getElecDrivingRangeValue();
    }

    public float getElecPercentageValue() {
        Log.v(TAG, "getElecPercentageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getElecPercentageValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getElecPercentageValue();
    }

    public int getFuelDrivingRangeValue() {
        Log.v(TAG, "getFuelDrivingRangeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFuelDrivingRangeValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getFuelDrivingRangeValue();
    }

    public int getFuelPercentageValue() {
        Log.v(TAG, "getFuelPercentageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFuelPercentageValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getFuelPercentageValue();
    }

    public float getTotalElecConPHMValue() {
        Log.v(TAG, "getTotalElecConPHMValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTotalElecConPHMValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getTotalElecConPhmValue();
    }

    public float getTotalFuelConPhmValue() {
        Log.v(TAG, "getTotalFuelConPhmValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTotalFuelConPHMValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getTotalFuelConPHMValue();
    }

    public float getTotalFuelConValue() {
        Log.v(TAG, "getTotalFuelConValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTotalFuelConValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getTotalFuelConValue();
    }

    public float getTotalElecConValue() {
        Log.v(TAG, "getTotalFuelConValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTotalElecConValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getTotalElecConValue();
    }

    public float getTotalMileageValue() {
        Log.v(TAG, "getTotalMileageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTotalMileageValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getTotalMileageValue();
    }

    public int getKeyBatteryLevel() {
        Log.v(TAG, "getKeyBatteryLevel is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getKeyBatteryLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getKeyBatteryLevel();
    }

    public int getEVMileageValue() {
        Log.v(TAG, "getEVMileageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEVMileageValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getEVMileageValue();
    }

    public int resetData() {
        Log.v(TAG, "resetData is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetData mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().resetData();
    }

    public int resetTotalFuelConValue(int i) {
        Log.v(TAG, "resetTotalFuelConValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetTotalFuelConValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().resetTotalFuelConValue(i);
    }

    public int resetExtChargeCapacity(int i) {
        Log.v(TAG, "resetExtChargeCapacity is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetExtChargeCapacity mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().resetExtChargeCapacity(i);
    }

    public int resetDrivingTimeValue(int i) {
        Log.v(TAG, "resetDrivingTimeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetDrivingTimeValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().resetDrivingTimeValue(i);
    }

    public int resetAverageSpeed(int i) {
        Log.v(TAG, "resetAverageSpeed is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetAverageSpeed mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().resetAverageSpeed(i);
    }

    public int resetStatisticDate(int i, int i2, int i3, int i4) {
        Log.v(TAG, "resetStatisticDate is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetStatisticDate mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().resetStatisticDate(i, i2, i3, i4);
    }

    public float getAverageSpeedValue() {
        Log.v(TAG, "getAverageSpeedValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAverageSpeedValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getAverageSpeedValue();
    }

    public float getMileageNumber(int i) {
        Log.v(TAG, "getMileageNumber is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMileageNumber mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getMileageNumberOne();
        }
        if (i != 1) {
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getMileageNumberTwo();
    }

    public int getHEVMileageValue() {
        Log.v(TAG, "getHEVMileageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getHEVMileageValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getHEVMileageValue();
    }

    public void registerListener(AbsBYDAutoStatisticListener absBYDAutoStatisticListener) {
        registerListener(absBYDAutoStatisticListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoStatisticListener absBYDAutoStatisticListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe statistic listener " + absBYDAutoStatisticListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoStatisticListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener mForTestValue is 0 execute return");
            return;
        }
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (!this.mAbsBydAutoStatisticListeners.contains(absBYDAutoStatisticListener)) {
            this.mAbsBydAutoStatisticListeners.add(absBYDAutoStatisticListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoStatisticListener);
        TsManagerImpl.getInstance(this.mContext).registerGeneralCallback();
    }

    public void unregisterListener(AbsBYDAutoStatisticListener absBYDAutoStatisticListener) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.d(TAG, "unregisterListener mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.d(TAG, "caradatper still connecting, please wait");
        } else if (absBYDAutoStatisticListener != null) {
            Log.d(TAG, "unregisterStatisticListener: " + absBYDAutoStatisticListener);
            this.mAbsBydAutoStatisticListeners.remove(absBYDAutoStatisticListener);
        }
    }

    public float getAverageSpeed(int i) {
        Log.v(TAG, "getAverageSpeed is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAverageSpeed mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getAverageSpeed(i);
    }

    public float getRemainingBatteryPower() {
        Log.v(TAG, "getRemainingBatteryPower is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRemainingBatteryPower mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getRemainingBatteryPower();
    }

    public float getEvRemainingBatteryPower() {
        Log.v(TAG, "getEvRemainingBatteryPower is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEvRemainingBatteryPower mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getEvRemainingBatteryPower();
    }

    public int setRoadCongestedS(int i) {
        Log.i(TAG, "setRoadCongestedS is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRoadCongestedS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().setRoadCongestedS(i);
    }

    public int setRoadConstructionS(int i) {
        Log.i(TAG, "setRoadConstructionS is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRoadConstructionS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().setRoadConstructionS(i);
    }

    public int setRoadAccidentS(int i) {
        Log.i(TAG, "setRoadAccidentS is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRoadAccidentS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().setRoadAccidentS(i);
    }

    public int setRoadImpassableS(int i) {
        Log.i(TAG, "setRoadImpassableS is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRoadImpassableS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().setRoadImpassableS(i);
    }

    public int setTrafficSignTypeS(int i) {
        Log.i(TAG, "setTrafficSignTypeS is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setTrafficSignTypeS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().setTrafficSignTypeS(i);
    }

    public int sendIsaDataToAdas(int[] iArr) {
        Log.v(TAG, "sendIsaDataToAdas is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().sendIsaDataToAdas(iArr);
    }

    public int getDrivingRangeAll() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDrivingRangeAll mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getDrivingRangeAll();
    }

    public int getDrivingRangeUnit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDrivingRangeUnit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getDrivingRangeUnit();
    }

    public float getCurrentTripMileage() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCurrentTripMileage mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getCurrentTripMileage();
    }

    public int getCurrentTripMileageUnit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCurrentTripMileageUnit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getCurrentTripMileageUnit();
    }

    public int getCurrentTripTime() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCurrentTripTime mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getCurrentTripTime();
    }

    public float getCurrentTripElec() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCurrentTripElec mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getCurrentTripElec();
    }

    public int getCurrentTripElecUnit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCurrentTripElecUnit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getCurrentTripElecUnit();
    }

    public float getCurrentTripFuel() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCurrentTripFuel mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getCurrentTripFuel();
    }

    public int getCurrentTripFuelUnit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getCurrentTripFuelUnit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getCurrentTripFuelUnit();
    }

    public int getElecConsUnit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getElecConsUnit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getElecConsUnit();
    }

    public int getSpeedUnit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSpeedUnit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getSpeedUnit();
    }

    public int getElecUnit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getElecUnit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getElecUnit();
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    if (i == 270) {
                        bYDAutoEventValue.floatValue = getElecPercentageValue();
                        sparseArray.put(270, Float.valueOf(bYDAutoEventValue.floatValue));
                    } else if (i == 276) {
                        bYDAutoEventValue.intValue = getFuelDrivingRangeValue();
                        sparseArray.put(276, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 278) {
                        bYDAutoEventValue.intValue = getFuelPercentageValue();
                        sparseArray.put(278, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 322) {
                        bYDAutoEventValue.floatValue = getTotalFuelConValue();
                        sparseArray.put(322, Float.valueOf(bYDAutoEventValue.floatValue));
                    } else if (i == 324) {
                        bYDAutoEventValue.floatValue = getTotalFuelConPhmValue();
                        sparseArray.put(324, Float.valueOf(bYDAutoEventValue.floatValue));
                    } else if (i == 326) {
                        bYDAutoEventValue.floatValue = getTotalMileageValue();
                        sparseArray.put(326, Float.valueOf(bYDAutoEventValue.floatValue));
                    } else if (i == 1049) {
                        bYDAutoEventValue.intValue = getElecDrivingRangeValue();
                        sparseArray.put(1049, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3841) {
                        bYDAutoEventValue.floatValue = getAverageSpeedValue();
                        sparseArray.put(3841, Float.valueOf(bYDAutoEventValue.floatValue));
                    } else if (i == 283) {
                        bYDAutoEventValue.floatValue = getDrivingTimeValue();
                        sparseArray.put(283, Float.valueOf(bYDAutoEventValue.floatValue));
                    } else if (i == 284) {
                        bYDAutoEventValue.intValue = getKeyBatteryLevel();
                        sparseArray.put(284, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 307) {
                        bYDAutoEventValue.intValue = getEVMileageValue();
                        sparseArray.put(307, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 308) {
                        bYDAutoEventValue.intValue = getHEVMileageValue();
                        sparseArray.put(326, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 318) {
                        bYDAutoEventValue.floatValue = getTotalElecConValue();
                        sparseArray.put(318, Float.valueOf(bYDAutoEventValue.floatValue));
                    } else if (i != 319) {
                        switch (i) {
                            case 265:
                                bYDAutoEventValue.floatValue = getMileageNumber(0);
                                sparseArray.put(265, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 266:
                                bYDAutoEventValue.floatValue = getMileageNumber(1);
                                sparseArray.put(266, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 267:
                                bYDAutoEventValue.floatValue = getDrivingTimeValue();
                                sparseArray.put(267, Float.valueOf(bYDAutoEventValue.floatValue));
                                break;
                            case 268:
                                bYDAutoEventValue.intValue = getElecDrivingRangeValue();
                                sparseArray.put(268, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            default:
                                Log.v(TAG, "case fail ! please check out");
                                break;
                        }
                    } else {
                        bYDAutoEventValue.floatValue = getTotalElecConPHMValue();
                        sparseArray.put(319, Float.valueOf(bYDAutoEventValue.floatValue));
                    }
                    int i2 = 0;
                    for (int i3 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i2] = ((Integer) sparseArray.get(i3)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i2++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoStatisticListener> it = this.mAbsBydAutoStatisticListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else if (obj instanceof byte[]) {
            } else {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        switch (i) {
            case 3915:
                resetAverageSpeed(iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_CLEAR_INFO_AVERAGE_SPEED_SET:");
                return 1;
            case 3916:
                resetExtChargeCapacity(iIntValue);
                Log.v(TAG, "INSTRUMENT_CLEAR_INFO_EXTERNAL_CHARGE_POWER_SET:");
                return 1;
            case 3917:
                resetTotalFuelConValue(iIntValue);
                Log.v(TAG, "INSTRUMENT_CLEAR_INFO_FUEL_CONSUMPTION_SET:");
                return 1;
            case 3918:
                resetDrivingTimeValue(iIntValue);
                Log.v(TAG, "Instrument.INSTRUMENT_CLEAR_INFO_TRAVEL_TIME_SET:");
                return 1;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }
}
