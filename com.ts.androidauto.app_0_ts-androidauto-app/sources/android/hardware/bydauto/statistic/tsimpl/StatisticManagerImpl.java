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

    private StatisticManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        this.mContentResolver = con.getContentResolver();
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnGeneralChangedListener(new OnCarGeneralChangedListener() { // from class: android.hardware.bydauto.statistic.tsimpl.StatisticManagerImpl.1
            public void onCarGeneralChanged(int id, int areaId, GeneralPacket packet) {
                if (!StatisticManagerImpl.this.mAbsBydAutoStatisticListeners.isEmpty()) {
                    try {
                        StatisticManagerImpl.this.statisticCallback(id, areaId, packet);
                    } catch (Exception e) {
                        Log.e(StatisticManagerImpl.TAG, "statisticCallback is error");
                    }
                }
            }

            public void onCarGeneralBinderChanged(boolean binderState) {
                Log.v(StatisticManagerImpl.TAG, "onBinderStateChanged is " + binderState);
            }

            public void onCarClusterBrightnessChanged(int brightness) {
                Log.v(StatisticManagerImpl.TAG, "onCarClusterBrightnessChanged is " + brightness);
            }
        });
    }

    public static StatisticManagerImpl getInstance(Context con) {
        if (mStatisticManagerImpl == null) {
            synchronized (StatisticManagerImpl.class) {
                if (mStatisticManagerImpl == null) {
                    mStatisticManagerImpl = new StatisticManagerImpl(con);
                }
            }
        }
        return mStatisticManagerImpl;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    public void statisticCallback(int id, int areaId, GeneralPacket packet) {
        Log.i(TAG, "statisticCallback propertyId:" + id);
        switch (id) {
            case 557859843:
                for (AbsBYDAutoStatisticListener listener : this.mAbsBydAutoStatisticListeners) {
                    listener.onFuelDrivingRangeChanged(packet.getFuelDrivingRangeValueR());
                }
                caseFeatureIds(276, Integer.valueOf(packet.getFuelDrivingRangeValueR()));
                break;
            case 557859844:
                for (AbsBYDAutoStatisticListener listener2 : this.mAbsBydAutoStatisticListeners) {
                    listener2.onFuelPercentageChanged(packet.getFuelPercentageValueR());
                }
                caseFeatureIds(278, Integer.valueOf(packet.getFuelPercentageValueR()));
                break;
            case 557859850:
                for (AbsBYDAutoStatisticListener listener3 : this.mAbsBydAutoStatisticListeners) {
                    listener3.onKeyBatteryLevelChanged(packet.getKeyBatteryLevelR());
                }
                break;
            case 557859855:
                for (AbsBYDAutoStatisticListener listener4 : this.mAbsBydAutoStatisticListeners) {
                    listener4.onAverageSpeedChanged(packet.getAverageSpeedValueS());
                }
                caseFeatureIds(3841, Integer.valueOf(packet.getAverageSpeedValueS()));
                break;
            case 557859857:
                for (AbsBYDAutoStatisticListener listener5 : this.mAbsBydAutoStatisticListeners) {
                    listener5.onEVMileageValueChanged(packet.getEVMileageValueS());
                }
                caseFeatureIds(307, Integer.valueOf(packet.getEVMileageValueS()));
                break;
            case 557859877:
                for (AbsBYDAutoStatisticListener listener6 : this.mAbsBydAutoStatisticListeners) {
                    listener6.onDrivingRangeAllChanged(packet.getDrivingRangeAllR());
                }
                break;
            case 557859878:
                for (AbsBYDAutoStatisticListener listener7 : this.mAbsBydAutoStatisticListeners) {
                    listener7.onDrivingRangeUnitChanged(packet.getDrivingRangeUnitR());
                }
                break;
            case 557869856:
                for (AbsBYDAutoStatisticListener listener8 : this.mAbsBydAutoStatisticListeners) {
                    listener8.onCurrentTripElecUnitChanged(packet.getCurrentTripElecUnitR());
                }
                break;
            case 557869857:
                for (AbsBYDAutoStatisticListener listener9 : this.mAbsBydAutoStatisticListeners) {
                    listener9.onCurrentTripTimeChanged(packet.getCurrentTripTimeR());
                }
                break;
            case 557875215:
                for (AbsBYDAutoStatisticListener listener10 : this.mAbsBydAutoStatisticListeners) {
                    listener10.onElecDrivingRangeChanged(packet.getElecDrivingRangeValueTwoR());
                }
                break;
            case 557875217:
                for (AbsBYDAutoStatisticListener listener11 : this.mAbsBydAutoStatisticListeners) {
                    listener11.onCurrentTripFuelUnitChanged(packet.getCurrentTripFuelUnitR());
                }
                break;
            case 557875219:
                for (AbsBYDAutoStatisticListener listener12 : this.mAbsBydAutoStatisticListeners) {
                    listener12.onSpeedUnitChanged(packet.getSpeedUnitR());
                }
                break;
            case 557875222:
                for (AbsBYDAutoStatisticListener listener13 : this.mAbsBydAutoStatisticListeners) {
                    listener13.onElecConsUnitChanged(packet.getElecConsUnitR());
                }
                break;
            case 557875258:
                for (AbsBYDAutoStatisticListener listener14 : this.mAbsBydAutoStatisticListeners) {
                    listener14.onElecUnitChanged(packet.getElecUnitR());
                }
                break;
            case 557875259:
                for (AbsBYDAutoStatisticListener listener15 : this.mAbsBydAutoStatisticListeners) {
                    listener15.onCurrentTripMileageUnitChanged(packet.getCurrentTripMileageUnitR());
                }
                break;
            case 559956994:
                for (AbsBYDAutoStatisticListener listener16 : this.mAbsBydAutoStatisticListeners) {
                    listener16.onElecPercentageChanged(packet.getElecPercentageValueR());
                }
                caseFeatureIds(270, Float.valueOf(packet.getElecPercentageValueR()));
                break;
            case 559956997:
                for (AbsBYDAutoStatisticListener listener17 : this.mAbsBydAutoStatisticListeners) {
                    listener17.onTotalElecConPHMChanged(packet.getTotalElecConPHMValueR());
                }
                caseFeatureIds(319, Float.valueOf(packet.getTotalElecConPHMValueR()));
                break;
            case 559956998:
                for (AbsBYDAutoStatisticListener listener18 : this.mAbsBydAutoStatisticListeners) {
                    listener18.onTotalFuelConPHMChanged(packet.getTotalFuelConPHMValueR());
                }
                break;
            case 559956999:
                for (AbsBYDAutoStatisticListener listener19 : this.mAbsBydAutoStatisticListeners) {
                    listener19.onTotalFuelConChanged(packet.getTotalFuelConValueR());
                }
                break;
            case 559957000:
                for (AbsBYDAutoStatisticListener listener20 : this.mAbsBydAutoStatisticListeners) {
                    listener20.onTotalElecConChanged(packet.getTotalElecConValueR());
                }
                break;
            case 559957001:
                for (AbsBYDAutoStatisticListener listener21 : this.mAbsBydAutoStatisticListeners) {
                    listener21.onTotalMileageValueChanged(packet.getTotalMileageValueR());
                }
                caseFeatureIds(326, Float.valueOf(packet.getTotalMileageValueR()));
                break;
            case 559957010:
                for (AbsBYDAutoStatisticListener listener22 : this.mAbsBydAutoStatisticListeners) {
                    listener22.onDrivingTimeChanged(packet.getDrivingTimeValueTwoR());
                }
                caseFeatureIds(267, Float.valueOf(packet.getDrivingTimeValueTwoR()));
                break;
            case 559957024:
                for (AbsBYDAutoStatisticListener listener23 : this.mAbsBydAutoStatisticListeners) {
                    listener23.onRemainingBatteryPowerChanged(packet.getRemainingBatteryPowerR());
                }
                break;
            case 559957025:
                for (AbsBYDAutoStatisticListener listener24 : this.mAbsBydAutoStatisticListeners) {
                    listener24.onEVRemainingBatteryPowerChanged(packet.getEvRemainingBatteryPowerR());
                }
                break;
            case 559965186:
                for (AbsBYDAutoStatisticListener listener25 : this.mAbsBydAutoStatisticListeners) {
                    listener25.onMileageNumberChanged(0, packet.getMileageNumberOneR());
                }
                break;
            case 559965187:
                for (AbsBYDAutoStatisticListener listener26 : this.mAbsBydAutoStatisticListeners) {
                    listener26.onMileageNumberChanged(1, packet.getMileageNumberTwoR());
                }
                break;
            case 559967007:
                for (AbsBYDAutoStatisticListener listener27 : this.mAbsBydAutoStatisticListeners) {
                    listener27.onCurrentTripElecChanged(packet.getCurrentTripElecR());
                }
                break;
            case 559967010:
                for (AbsBYDAutoStatisticListener listener28 : this.mAbsBydAutoStatisticListeners) {
                    listener28.onCurrentTripFuelChanged(packet.getCurrentTripFuelR());
                }
                break;
            case 559972404:
                for (AbsBYDAutoStatisticListener listener29 : this.mAbsBydAutoStatisticListeners) {
                    listener29.onCurrentTripMileageChanged(packet.getCurrentTripMileageR());
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

    public int resetTotalFuelConValue(int value) {
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
        return this.mTsManagerImpl.getGeneralAdapterManager().resetTotalFuelConValue(value);
    }

    public int resetExtChargeCapacity(int value) {
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
        return this.mTsManagerImpl.getGeneralAdapterManager().resetExtChargeCapacity(value);
    }

    public int resetDrivingTimeValue(int value) {
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
        return this.mTsManagerImpl.getGeneralAdapterManager().resetDrivingTimeValue(value);
    }

    public int resetAverageSpeed(int value) {
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
        return this.mTsManagerImpl.getGeneralAdapterManager().resetAverageSpeed(value);
    }

    public int resetStatisticDate(int fuel, int elec, int speed, int time) {
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
        return this.mTsManagerImpl.getGeneralAdapterManager().resetStatisticDate(fuel, elec, speed, time);
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

    public float getMileageNumber(int status) {
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
        if (status == 0) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getMileageNumberOne();
        }
        if (status != 1) {
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

    public void registerListener(AbsBYDAutoStatisticListener l) {
        registerListener(l, new int[0]);
    }

    public void registerListener(AbsBYDAutoStatisticListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe statistic listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener mForTestValue is 0 execute return");
            return;
        }
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (!this.mAbsBydAutoStatisticListeners.contains(listener)) {
            this.mAbsBydAutoStatisticListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerGeneralCallback();
    }

    public void unregisterListener(AbsBYDAutoStatisticListener l) {
        Log.d(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.d(TAG, "unregisterListener mForTestValue is 0 execute return");
            return;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.d(TAG, "caradatper still connecting, please wait");
            return;
        }
        if (l != null) {
            Log.d(TAG, "unregisterStatisticListener: " + l);
            this.mAbsBydAutoStatisticListeners.remove(l);
        }
    }

    public float getAverageSpeed(int target) {
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
        return this.mTsManagerImpl.getGeneralAdapterManager().getAverageSpeed(target);
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

    public int setRoadCongestedS(int arg0) {
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
        return this.mTsManagerImpl.getGeneralAdapterManager().setRoadCongestedS(arg0);
    }

    public int setRoadConstructionS(int arg0) {
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
        return this.mTsManagerImpl.getGeneralAdapterManager().setRoadConstructionS(arg0);
    }

    public int setRoadAccidentS(int arg0) {
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
        return this.mTsManagerImpl.getGeneralAdapterManager().setRoadAccidentS(arg0);
    }

    public int setRoadImpassableS(int arg0) {
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
        return this.mTsManagerImpl.getGeneralAdapterManager().setRoadImpassableS(arg0);
    }

    public int setTrafficSignTypeS(int arg0) {
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
        return this.mTsManagerImpl.getGeneralAdapterManager().setTrafficSignTypeS(arg0);
    }

    public int sendIsaDataToAdas(int[] data) {
        Log.v(TAG, "sendIsaDataToAdas is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().sendIsaDataToAdas(data);
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

    public int resetTravelDate(int state) {
        if (1 == this.mForTest) {
            Log.v(TAG, " resetTravelDate mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().resetTravelDate(state);
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        int i = 1;
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            int length = eventTypes.length;
            int i2 = 0;
            while (i2 < length) {
                int eventType = eventTypes[i2];
                if (eventType > 0) {
                    if (eventType == 270) {
                        bydAutoEventValue.floatValue = getElecPercentageValue();
                        sparseArray.put(270, Float.valueOf(bydAutoEventValue.floatValue));
                    } else if (eventType == 276) {
                        bydAutoEventValue.intValue = getFuelDrivingRangeValue();
                        sparseArray.put(276, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 278) {
                        bydAutoEventValue.intValue = getFuelPercentageValue();
                        sparseArray.put(278, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 322) {
                        bydAutoEventValue.floatValue = getTotalFuelConValue();
                        sparseArray.put(322, Float.valueOf(bydAutoEventValue.floatValue));
                    } else if (eventType == 324) {
                        bydAutoEventValue.floatValue = getTotalFuelConPhmValue();
                        sparseArray.put(324, Float.valueOf(bydAutoEventValue.floatValue));
                    } else if (eventType == 326) {
                        bydAutoEventValue.floatValue = getTotalMileageValue();
                        sparseArray.put(326, Float.valueOf(bydAutoEventValue.floatValue));
                    } else if (eventType == 1049) {
                        bydAutoEventValue.intValue = getElecDrivingRangeValue();
                        sparseArray.put(1049, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3841) {
                        bydAutoEventValue.floatValue = getAverageSpeedValue();
                        sparseArray.put(3841, Float.valueOf(bydAutoEventValue.floatValue));
                    } else if (eventType == 283) {
                        bydAutoEventValue.floatValue = getDrivingTimeValue();
                        sparseArray.put(283, Float.valueOf(bydAutoEventValue.floatValue));
                    } else if (eventType == 284) {
                        bydAutoEventValue.intValue = getKeyBatteryLevel();
                        sparseArray.put(284, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 307) {
                        bydAutoEventValue.intValue = getEVMileageValue();
                        sparseArray.put(307, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 308) {
                        bydAutoEventValue.intValue = getHEVMileageValue();
                        sparseArray.put(326, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 318) {
                        bydAutoEventValue.floatValue = getTotalElecConValue();
                        sparseArray.put(318, Float.valueOf(bydAutoEventValue.floatValue));
                    } else if (eventType != 319) {
                        switch (eventType) {
                            case 265:
                                bydAutoEventValue.floatValue = getMileageNumber(0);
                                sparseArray.put(265, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 266:
                                bydAutoEventValue.floatValue = getMileageNumber(i);
                                sparseArray.put(266, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 267:
                                bydAutoEventValue.floatValue = getDrivingTimeValue();
                                sparseArray.put(267, Float.valueOf(bydAutoEventValue.floatValue));
                                break;
                            case 268:
                                bydAutoEventValue.intValue = getElecDrivingRangeValue();
                                sparseArray.put(268, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            default:
                                Log.v(TAG, "case fail ! please check out");
                                break;
                        }
                    } else {
                        bydAutoEventValue.floatValue = getTotalElecConPHMValue();
                        sparseArray.put(319, Float.valueOf(bydAutoEventValue.floatValue));
                    }
                    int index = 0;
                    for (int i3 : eventTypes) {
                        try {
                            int getNum = ((Integer) sparseArray.get(i3)).intValue();
                            bydAutoEventValue.intArrayValue[index] = getNum;
                        } catch (Exception e) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        index++;
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
                i2++;
                i = 1;
            }
        }
        return bydAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoStatisticListener listener : this.mAbsBydAutoStatisticListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        int intValue = 0;
        if (value instanceof Integer) {
            intValue = ((Integer) value).intValue();
        } else if (value instanceof Float) {
            ((Float) value).floatValue();
        } else if (value instanceof Double) {
            ((Double) value).doubleValue();
        } else if (value instanceof byte[]) {
        } else {
            Log.v(TAG, "the type of value is not a suitable type");
            return -1;
        }
        switch (eventType) {
            case 3915:
                resetAverageSpeed(intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_CLEAR_INFO_AVERAGE_SPEED_SET:");
                return 1;
            case 3916:
                resetExtChargeCapacity(intValue);
                Log.v(TAG, "INSTRUMENT_CLEAR_INFO_EXTERNAL_CHARGE_POWER_SET:");
                return 1;
            case 3917:
                resetTotalFuelConValue(intValue);
                Log.v(TAG, "INSTRUMENT_CLEAR_INFO_FUEL_CONSUMPTION_SET:");
                return 1;
            case 3918:
                resetDrivingTimeValue(intValue);
                Log.v(TAG, "Instrument.INSTRUMENT_CLEAR_INFO_TRAVEL_TIME_SET:");
                return 1;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }
}
