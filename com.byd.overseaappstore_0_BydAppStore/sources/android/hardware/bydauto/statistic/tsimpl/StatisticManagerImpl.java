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
import com.ts.lib.caradapter.general.GeneralPacket;
import com.ts.lib.caradapter.general.OnCarGeneralChangedListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.d;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class StatisticManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_StatisticManagerImpl";
    public static final String TAG = "StatisticManagerImpl";
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
                d.r("onCarClusterBrightnessChanged is ", i2, StatisticManagerImpl.TAG);
            }

            public void onCarGeneralBinderChanged(boolean z) {
                Log.v(StatisticManagerImpl.TAG, "onBinderStateChanged is " + z);
            }

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
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(d.f("requires permission ", str));
        }
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

    /* JADX WARN: Removed duplicated region for block: B:68:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r13, java.lang.Class<?> r14) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.statistic.tsimpl.StatisticManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
    }

    public float getAverageSpeed(int i) {
        String str;
        Log.v(TAG, "getAverageSpeed is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getAverageSpeed mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getAverageSpeed(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public float getAverageSpeedValue() {
        String str;
        Log.v(TAG, "getAverageSpeedValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getAverageSpeedValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getAverageSpeedValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public float getBatteryPackEnergy() {
        String str;
        Log.v(TAG, "getBatteryPackEnergy is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getBatteryPackEnergy mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getBatteryPackEnergy();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
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
        String str;
        Log.v(TAG, "getDrivingTimeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getDrivingTimeValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getDrivingTimeValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public int getEVMileageValue() {
        String str;
        Log.v(TAG, "getEVMileageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getEVMileageValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getEVMileageValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getElecDrivingRangeByStandard() {
        String str;
        Log.v(TAG, "getElecDrivingRangeByStandard is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getElecDrivingRangeByStandard mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getElecDrivingRangeByStandard();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getElecDrivingRangeValue() {
        String str;
        Log.v(TAG, "getElecDrivingRangeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getElecDrivingRangeValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getElecDrivingRangeValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public float getElecPercentageValue() {
        String str;
        Log.v(TAG, "getElecPercentageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getElecPercentageValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getElecPercentageValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public float getEvRemainingBatteryPower() {
        String str;
        Log.v(TAG, "getEvRemainingBatteryPower is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getEvRemainingBatteryPower mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getEvRemainingBatteryPower();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public int getFuelDrivingRangeValue() {
        String str;
        Log.v(TAG, "getFuelDrivingRangeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getFuelDrivingRangeValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getFuelDrivingRangeValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getFuelPercentageValue() {
        String str;
        Log.v(TAG, "getFuelPercentageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getFuelPercentageValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getFuelPercentageValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getHEVMileageValue() {
        String str;
        Log.v(TAG, "getHEVMileageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getHEVMileageValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getHEVMileageValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getKeyBatteryLevel() {
        String str;
        Log.v(TAG, "getKeyBatteryLevel is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getKeyBatteryLevel mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getKeyBatteryLevel();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public float getMileageNumber(int i) {
        String str;
        Log.v(TAG, "getMileageNumber is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getMileageNumber mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                if (i == 0) {
                    return this.mTsManagerImpl.getGeneralAdapterManager().getMileageNumberOne();
                }
                if (i != 1) {
                    return 0.0f;
                }
                return this.mTsManagerImpl.getGeneralAdapterManager().getMileageNumberTwo();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public float getRemainingBatteryPower() {
        String str;
        Log.v(TAG, "getRemainingBatteryPower is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getRemainingBatteryPower mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getRemainingBatteryPower();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public float getTotalElecConPHMValue() {
        String str;
        Log.v(TAG, "getTotalElecConPHMValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getTotalElecConPHMValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getTotalElecConPhmValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public float getTotalElecConValue() {
        String str;
        Log.v(TAG, "getTotalFuelConValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getTotalElecConValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getTotalElecConValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public float getTotalFuelConPhmValue() {
        String str;
        Log.v(TAG, "getTotalFuelConPhmValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getTotalFuelConPHMValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getTotalFuelConPHMValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public float getTotalFuelConValue() {
        String str;
        Log.v(TAG, "getTotalFuelConValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 == this.mForTest) {
            str = "getTotalFuelConValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().getTotalFuelConValue();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public float getTotalMileageValue() {
        Log.v(TAG, "getTotalMileageValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_GET);
        if (1 != this.mForTest) {
            return this.mTsManagerImpl.getGeneralAdapterManager().getTotalMileageValue();
        }
        Log.v(TAG, "getTotalMileageValue mForTestValue is 0 execute return");
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

    public void registerListener(AbsBYDAutoStatisticListener absBYDAutoStatisticListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe statistic listener ");
        sb.append(absBYDAutoStatisticListener);
        sb.append(" featureIDs = ");
        d.v(iArr, sb, TAG);
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

    public int resetAverageSpeed(int i) {
        String str;
        Log.v(TAG, "resetAverageSpeed is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            str = "resetAverageSpeed mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().resetAverageSpeed(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int resetData() {
        String str;
        Log.v(TAG, "resetData is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            str = "resetData mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().resetData();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int resetDrivingTimeValue(int i) {
        String str;
        Log.v(TAG, "resetDrivingTimeValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            str = "resetDrivingTimeValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().resetDrivingTimeValue(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int resetExtChargeCapacity(int i) {
        String str;
        Log.v(TAG, "resetExtChargeCapacity is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            str = "resetExtChargeCapacity mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().resetExtChargeCapacity(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int resetStatisticDate(int i, int i2, int i3, int i4) {
        String str;
        Log.v(TAG, "resetStatisticDate is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            str = "resetStatisticDate mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().resetStatisticDate(i, i2, i3, i4);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int resetTotalFuelConValue(int i) {
        String str;
        Log.v(TAG, "resetTotalFuelConValue is execute");
        enforcePermission(BYDAutoStatisticDevice.PERMISSION_BYDAUTO_STATISTIC_COMMON);
        if (1 == this.mForTest) {
            str = "resetTotalFuelConValue mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
                return this.mTsManagerImpl.getGeneralAdapterManager().resetTotalFuelConValue(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
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
    public int setForOneFeatureId(int i, Object obj) {
        String str;
        int iIntValue;
        String str2;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else {
                if (!(obj instanceof byte[])) {
                    str = "the type of value is not a suitable type";
                    Log.v(TAG, str);
                    return -1;
                }
            }
            iIntValue = 0;
        }
        switch (i) {
            case 3915:
                resetAverageSpeed(iIntValue);
                str2 = "Instrument.INSTRUMENT_CLEAR_INFO_AVERAGE_SPEED_SET:";
                break;
            case 3916:
                resetExtChargeCapacity(iIntValue);
                str2 = "INSTRUMENT_CLEAR_INFO_EXTERNAL_CHARGE_POWER_SET:";
                break;
            case 3917:
                resetTotalFuelConValue(iIntValue);
                str2 = "INSTRUMENT_CLEAR_INFO_FUEL_CONSUMPTION_SET:";
                break;
            case 3918:
                resetDrivingTimeValue(iIntValue);
                str2 = "Instrument.INSTRUMENT_CLEAR_INFO_TRAVEL_TIME_SET:";
                break;
            default:
                str = "case fail ! please check out";
                Log.v(TAG, str);
                return -1;
        }
        Log.v(TAG, str2);
        return 1;
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
        int i3;
        int fuelDrivingRangeValueR;
        Object objValueOf;
        float elecPercentageValueR;
        e.t("statisticCallback propertyId:", i, TAG);
        switch (i) {
            case StatisticHalid.ID_FUEL_DRIVING_RANGE_VALUER /* 557859843 */:
                Iterator<AbsBYDAutoStatisticListener> it = this.mAbsBydAutoStatisticListeners.iterator();
                while (it.hasNext()) {
                    it.next().onFuelDrivingRangeChanged(generalPacket.getFuelDrivingRangeValueR());
                }
                i3 = 276;
                fuelDrivingRangeValueR = generalPacket.getFuelDrivingRangeValueR();
                objValueOf = Integer.valueOf(fuelDrivingRangeValueR);
                caseFeatureIds(i3, objValueOf);
                break;
            case StatisticHalid.ID_FUEL_PERCENTAGE_VALUER /* 557859844 */:
                Iterator<AbsBYDAutoStatisticListener> it2 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onFuelPercentageChanged(generalPacket.getFuelPercentageValueR());
                }
                i3 = 278;
                fuelDrivingRangeValueR = generalPacket.getFuelPercentageValueR();
                objValueOf = Integer.valueOf(fuelDrivingRangeValueR);
                caseFeatureIds(i3, objValueOf);
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
                i3 = 3841;
                fuelDrivingRangeValueR = generalPacket.getAverageSpeedValueS();
                objValueOf = Integer.valueOf(fuelDrivingRangeValueR);
                caseFeatureIds(i3, objValueOf);
                break;
            case StatisticHalid.ID_E_V_MILEAGE_VALUES /* 557859857 */:
                Iterator<AbsBYDAutoStatisticListener> it5 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onEVMileageValueChanged(generalPacket.getEVMileageValueS());
                }
                i3 = 307;
                fuelDrivingRangeValueR = generalPacket.getEVMileageValueS();
                objValueOf = Integer.valueOf(fuelDrivingRangeValueR);
                caseFeatureIds(i3, objValueOf);
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
                i3 = 270;
                elecPercentageValueR = generalPacket.getElecPercentageValueR();
                objValueOf = Float.valueOf(elecPercentageValueR);
                caseFeatureIds(i3, objValueOf);
                break;
            case StatisticHalid.ID_TOTAL_ELEC_CON_P_H_M_VALUER /* 559956997 */:
                Iterator<AbsBYDAutoStatisticListener> it10 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onTotalElecConPHMChanged(generalPacket.getTotalElecConPHMValueR());
                }
                i3 = 319;
                elecPercentageValueR = generalPacket.getTotalElecConPHMValueR();
                objValueOf = Float.valueOf(elecPercentageValueR);
                caseFeatureIds(i3, objValueOf);
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
                i3 = 326;
                elecPercentageValueR = generalPacket.getTotalMileageValueR();
                objValueOf = Float.valueOf(elecPercentageValueR);
                caseFeatureIds(i3, objValueOf);
                break;
            case StatisticHalid.ID_DRIVING_TIME_VALUE_TWOR /* 559957010 */:
                Iterator<AbsBYDAutoStatisticListener> it15 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onDrivingTimeChanged(generalPacket.getDrivingTimeValueTwoR());
                }
                i3 = 267;
                elecPercentageValueR = generalPacket.getDrivingTimeValueTwoR();
                objValueOf = Float.valueOf(elecPercentageValueR);
                caseFeatureIds(i3, objValueOf);
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
            case StatisticHalid.ID_MILEAGE_NUMBER_ONER /* 559965186 */:
                Iterator<AbsBYDAutoStatisticListener> it18 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onMileageNumberChanged(0, generalPacket.getMileageNumberOneR());
                }
                break;
            case StatisticHalid.ID_MILEAGE_NUMBER_TWOR /* 559965187 */:
                Iterator<AbsBYDAutoStatisticListener> it19 = this.mAbsBydAutoStatisticListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onMileageNumberChanged(1, generalPacket.getMileageNumberTwoR());
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
}
