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
            public void onCarClusterBrightnessChanged(int i2) {
                Log.v(StatisticManagerImpl.TAG, "onCarClusterBrightnessChanged is " + i2);
            }

            public void onCarGeneralBinderChanged(boolean z) {
                Log.v(StatisticManagerImpl.TAG, "onBinderStateChanged is " + z);
            }

            public void onCarGeneralChanged(int i2, int i3, GeneralPacket generalPacket) {
                if (StatisticManagerImpl.this.mAbsBydAutoStatisticListeners.isEmpty()) {
                    return;
                }
                StatisticManagerImpl.this.statisticCallback(i2, i3, generalPacket);
            }
        });
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
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
                        float elecPercentageValue = getElecPercentageValue();
                        bYDAutoEventValue.floatValue = elecPercentageValue;
                        sparseArray.put(270, Float.valueOf(elecPercentageValue));
                    } else if (i == 276) {
                        int fuelDrivingRangeValue = getFuelDrivingRangeValue();
                        bYDAutoEventValue.intValue = fuelDrivingRangeValue;
                        sparseArray.put(276, Integer.valueOf(fuelDrivingRangeValue));
                    } else if (i == 278) {
                        int fuelPercentageValue = getFuelPercentageValue();
                        bYDAutoEventValue.intValue = fuelPercentageValue;
                        sparseArray.put(278, Integer.valueOf(fuelPercentageValue));
                    } else if (i == 322) {
                        float totalFuelConValue = getTotalFuelConValue();
                        bYDAutoEventValue.floatValue = totalFuelConValue;
                        sparseArray.put(322, Float.valueOf(totalFuelConValue));
                    } else if (i == 324) {
                        float totalFuelConPhmValue = getTotalFuelConPhmValue();
                        bYDAutoEventValue.floatValue = totalFuelConPhmValue;
                        sparseArray.put(324, Float.valueOf(totalFuelConPhmValue));
                    } else if (i == 326) {
                        float totalMileageValue = getTotalMileageValue();
                        bYDAutoEventValue.floatValue = totalMileageValue;
                        sparseArray.put(326, Float.valueOf(totalMileageValue));
                    } else if (i == 1049) {
                        int elecDrivingRangeValue = getElecDrivingRangeValue();
                        bYDAutoEventValue.intValue = elecDrivingRangeValue;
                        sparseArray.put(1049, Integer.valueOf(elecDrivingRangeValue));
                    } else if (i == 3841) {
                        float averageSpeedValue = getAverageSpeedValue();
                        bYDAutoEventValue.floatValue = averageSpeedValue;
                        sparseArray.put(3841, Float.valueOf(averageSpeedValue));
                    } else if (i == 283) {
                        float drivingTimeValue = getDrivingTimeValue();
                        bYDAutoEventValue.floatValue = drivingTimeValue;
                        sparseArray.put(283, Float.valueOf(drivingTimeValue));
                    } else if (i == 284) {
                        int keyBatteryLevel = getKeyBatteryLevel();
                        bYDAutoEventValue.intValue = keyBatteryLevel;
                        sparseArray.put(284, Integer.valueOf(keyBatteryLevel));
                    } else if (i == 307) {
                        int eVMileageValue = getEVMileageValue();
                        bYDAutoEventValue.intValue = eVMileageValue;
                        sparseArray.put(307, Integer.valueOf(eVMileageValue));
                    } else if (i == 308) {
                        int hEVMileageValue = getHEVMileageValue();
                        bYDAutoEventValue.intValue = hEVMileageValue;
                        sparseArray.put(326, Integer.valueOf(hEVMileageValue));
                    } else if (i == 318) {
                        float totalElecConValue = getTotalElecConValue();
                        bYDAutoEventValue.floatValue = totalElecConValue;
                        sparseArray.put(318, Float.valueOf(totalElecConValue));
                    } else if (i != 319) {
                        switch (i) {
                            case 265:
                                int mileageNumber = getMileageNumber(0);
                                bYDAutoEventValue.intValue = mileageNumber;
                                sparseArray.put(265, Integer.valueOf(mileageNumber));
                                break;
                            case 266:
                                int mileageNumber2 = getMileageNumber(1);
                                bYDAutoEventValue.intValue = mileageNumber2;
                                sparseArray.put(266, Integer.valueOf(mileageNumber2));
                                break;
                            case 267:
                                float drivingTimeValue2 = getDrivingTimeValue();
                                bYDAutoEventValue.floatValue = drivingTimeValue2;
                                sparseArray.put(267, Float.valueOf(drivingTimeValue2));
                                break;
                            case 268:
                                int elecDrivingRangeValue2 = getElecDrivingRangeValue();
                                bYDAutoEventValue.intValue = elecDrivingRangeValue2;
                                sparseArray.put(268, Integer.valueOf(elecDrivingRangeValue2));
                                break;
                            default:
                                Log.v(TAG, "case fail ! please check out");
                                break;
                        }
                    } else {
                        float totalElecConPHMValue = getTotalElecConPHMValue();
                        bYDAutoEventValue.floatValue = totalElecConPHMValue;
                        sparseArray.put(319, Float.valueOf(totalElecConPHMValue));
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

    public float getAverageSpeed(int i) {
        Log.v(TAG, "getAverageSpeed is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAverageSpeed mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getAverageSpeed(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public float getAverageSpeedValue() {
        Log.v(TAG, "getAverageSpeedValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getAverageSpeedValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getAverageSpeedValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public float getBatteryPackEnergy() {
        Log.v(TAG, "getBatteryPackEnergy is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getBatteryPackEnergy mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getBatteryPackEnergy();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public int getDrivingRangeAll() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDrivingRangeAll mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getDrivingRangeAll();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDrivingRangeUnit() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getDrivingRangeUnit mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getDrivingRangeUnit();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public float getDrivingTimeValue() {
        Log.v(TAG, "getDrivingTimeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getDrivingTimeValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getDrivingTimeValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public int getEVMileageValue() {
        Log.v(TAG, "getEVMileageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEVMileageValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getEVMileageValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getElecDrivingRangeByStandard() {
        Log.v(TAG, "getElecDrivingRangeByStandard is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getElecDrivingRangeByStandard mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getElecDrivingRangeByStandard();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getElecDrivingRangeValue() {
        Log.v(TAG, "getElecDrivingRangeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getElecDrivingRangeValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getElecDrivingRangeValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public float getElecPercentageValue() {
        Log.v(TAG, "getElecPercentageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getElecPercentageValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getElecPercentageValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public float getEvRemainingBatteryPower() {
        Log.v(TAG, "getEvRemainingBatteryPower is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getEvRemainingBatteryPower mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getEvRemainingBatteryPower();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public int getFuelDrivingRangeValue() {
        Log.v(TAG, "getFuelDrivingRangeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFuelDrivingRangeValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getFuelDrivingRangeValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getFuelPercentageValue() {
        Log.v(TAG, "getFuelPercentageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getFuelPercentageValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getFuelPercentageValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getHEVMileageValue() {
        Log.v(TAG, "getHEVMileageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getHEVMileageValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getHEVMileageValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getKeyBatteryLevel() {
        Log.v(TAG, "getKeyBatteryLevel is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getKeyBatteryLevel mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getKeyBatteryLevel();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMileageNumber(int i) {
        Log.v(TAG, "getMileageNumber is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getMileageNumber mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getMileageNumberOne();
        }
        if (i != 1) {
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().getMileageNumberTwo();
    }

    public float getRemainingBatteryPower() {
        Log.v(TAG, "getRemainingBatteryPower is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRemainingBatteryPower mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getRemainingBatteryPower();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public float getTotalElecConPHMValue() {
        Log.v(TAG, "getTotalElecConPHMValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTotalElecConPHMValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getTotalElecConPhmValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public float getTotalElecConValue() {
        Log.v(TAG, "getTotalFuelConValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTotalElecConValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getTotalElecConValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public float getTotalFuelConPhmValue() {
        Log.v(TAG, "getTotalFuelConPhmValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTotalFuelConPHMValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getTotalFuelConPHMValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public float getTotalFuelConValue() {
        Log.v(TAG, "getTotalFuelConValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTotalFuelConValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getTotalFuelConValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public float getTotalMileageValue() {
        Log.v(TAG, "getTotalMileageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTotalMileageValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getTotalMileageValue();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoStatisticListener> it = this.mAbsBydAutoStatisticListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoStatisticListener absBYDAutoStatisticListener) {
        registerListener(absBYDAutoStatisticListener, new int[0]);
    }

    public int resetAverageSpeed(int i) {
        Log.v(TAG, "resetAverageSpeed is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetAverageSpeed mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().resetAverageSpeed(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int resetData() {
        Log.v(TAG, "resetData is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetData mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().resetData();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int resetDrivingTimeValue(int i) {
        Log.v(TAG, "resetDrivingTimeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetDrivingTimeValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().resetDrivingTimeValue(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int resetExtChargeCapacity(int i) {
        Log.v(TAG, "resetExtChargeCapacity is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetExtChargeCapacity mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().resetExtChargeCapacity(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int resetStatisticDate(int i, int i2, int i3, int i4) {
        Log.v(TAG, "resetStatisticDate is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetStatisticDate mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().resetStatisticDate(i, i2, i3, i4);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int resetTotalFuelConValue(int i) {
        Log.v(TAG, "resetTotalFuelConValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            Log.v(TAG, "resetTotalFuelConValue mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().resetTotalFuelConValue(i);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int sendIsaDataToAdas(int[] iArr) {
        Log.v(TAG, "sendIsaDataToAdas is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().sendIsaDataToAdas(iArr);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
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
            } else {
                if (!(obj instanceof byte[])) {
                    Log.v(TAG, "the type of value is not a suitable type");
                    return -1;
                }
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

    public int setRoadAccidentS(int i) {
        Log.i(TAG, "setRoadAccidentS is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRoadAccidentS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setRoadAccidentS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRoadCongestedS(int i) {
        Log.i(TAG, "setRoadCongestedS is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRoadCongestedS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setRoadCongestedS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRoadConstructionS(int i) {
        Log.i(TAG, "setRoadConstructionS is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRoadConstructionS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setRoadConstructionS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRoadImpassableS(int i) {
        Log.i(TAG, "setRoadImpassableS is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setRoadImpassableS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setRoadImpassableS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setTrafficSignTypeS(int i) {
        Log.i(TAG, "setTrafficSignTypeS is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setTrafficSignTypeS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setTrafficSignTypeS(i);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
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
            case StatisticHalid.ID_ELEC_DRIVING_RANGE_VALUE_TWOR /* 557875215 */:
                Iterator<AbsBYDAutoStatisticListener> it8 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onElecDrivingRangeChanged(generalPacket.getElecDrivingRangeValueTwoR());
                }
                break;
            case StatisticHalid.ID_ELEC_PERCENTAGE_VALUER /* 559956994 */:
                Iterator<AbsBYDAutoStatisticListener> it9 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onElecPercentageChanged(generalPacket.getElecPercentageValueR());
                }
                caseFeatureIds(270, Float.valueOf(generalPacket.getElecPercentageValueR()));
                break;
            case StatisticHalid.ID_TOTAL_ELEC_CON_P_H_M_VALUER /* 559956997 */:
                Iterator<AbsBYDAutoStatisticListener> it10 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onTotalElecConPHMChanged(generalPacket.getTotalElecConPHMValueR());
                }
                caseFeatureIds(319, Float.valueOf(generalPacket.getTotalElecConPHMValueR()));
                break;
            case StatisticHalid.ID_TOTAL_FUEL_CON_P_H_M_VALUER /* 559956998 */:
                Iterator<AbsBYDAutoStatisticListener> it11 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onTotalFuelConPHMChanged(generalPacket.getTotalFuelConPHMValueR());
                }
                break;
            case StatisticHalid.ID_TOTAL_FUEL_CON_VALUER /* 559956999 */:
                Iterator<AbsBYDAutoStatisticListener> it12 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onTotalFuelConChanged(generalPacket.getTotalFuelConValueR());
                }
                break;
            case StatisticHalid.ID_TOTAL_ELEC_CON_VALUER /* 559957000 */:
                Iterator<AbsBYDAutoStatisticListener> it13 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onTotalElecConChanged(generalPacket.getTotalElecConValueR());
                }
                break;
            case StatisticHalid.ID_TOTAL_MILEAGE_VALUER /* 559957001 */:
                Iterator<AbsBYDAutoStatisticListener> it14 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onTotalMileageValueChanged(generalPacket.getTotalMileageValueR());
                }
                caseFeatureIds(326, Float.valueOf(generalPacket.getTotalMileageValueR()));
                break;
            case StatisticHalid.ID_DRIVING_TIME_VALUE_TWOR /* 559957010 */:
                Iterator<AbsBYDAutoStatisticListener> it15 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onDrivingTimeChanged(generalPacket.getDrivingTimeValueTwoR());
                }
                caseFeatureIds(267, Float.valueOf(generalPacket.getDrivingTimeValueTwoR()));
                break;
            case StatisticHalid.ID_REMAINING_BATTERY_POWER_R /* 559957024 */:
                Iterator<AbsBYDAutoStatisticListener> it16 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onRemainingBatteryPowerChanged(generalPacket.getRemainingBatteryPowerR());
                }
                break;
            case StatisticHalid.ID_EV_REMAINING_BATTERY_POWER_R /* 559957025 */:
                Iterator<AbsBYDAutoStatisticListener> it17 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onEVRemainingBatteryPowerChanged(generalPacket.getEvRemainingBatteryPowerR());
                }
                break;
        }
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
            return;
        }
        if (absBYDAutoStatisticListener != null) {
            Log.d(TAG, "unregisterStatisticListener: " + absBYDAutoStatisticListener);
            this.mAbsBydAutoStatisticListeners.remove(absBYDAutoStatisticListener);
        }
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
}
