package android.hardware.bydauto.tyre.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.tyre.AbsBYDAutoTyreListener;
import android.hardware.bydauto.tyre.BYDAutoTyreDevice;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.body.CarBodyManager;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TyreManagerImpl extends AbsTsMyCar {
    private static final int LB_TYRE = 3;
    private static final int LF_TYRE = 1;
    private static final int RB_TYRE = 4;
    private static final int RF_TYRE = 2;
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_TyreManagerImpl";
    public static final String TAG = "TyreManagerImpl";
    private static final int TYRE_COMMAND_INVALID_VALUE = 0;
    private static volatile TsManagerImpl mTsManagerImpl;
    private static volatile TyreManagerImpl mTyreManagerImpl;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private CarBodyManager mTsManger;
    private final CopyOnWriteArrayList<AbsBYDAutoTyreListener> mAbsBYDAutoTyreListeners = new CopyOnWriteArrayList<>();
    private List<AbsBYDAutoTyreListener> mAbsBydAutoTyreListeners = new ArrayList();
    private boolean mIgnoreBydPermission = false;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    private TyreManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_COMMON);
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i8 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i8;
            if (1 == i8) {
                return;
            }
        }
        mTsManagerImpl = TsManagerImpl.getInstance(context);
        mTsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.tyre.tsimpl.TyreManagerImpl.1
            public void onBinderStateChanged(boolean z7) {
                Log.v(TyreManagerImpl.TAG, "onBinderStateChanged is " + z7);
            }

            public void onBodyChanged(int i9, int i10, CarBodyPacket carBodyPacket) {
                if (TyreManagerImpl.this.mAbsBYDAutoTyreListeners.isEmpty()) {
                    Log.v(TyreManagerImpl.TAG, "mAbsBYDAutoTyreListener is null. ");
                    return;
                }
                try {
                    TyreManagerImpl.this.tyreCallback(i9, i10, carBodyPacket);
                } catch (Exception unused) {
                    Log.e(TyreManagerImpl.TAG, "tyreCallback is error");
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static TyreManagerImpl getInstance(Context context) {
        if (mTyreManagerImpl == null) {
            synchronized (TyreManagerImpl.class) {
                if (mTyreManagerImpl == null) {
                    mTyreManagerImpl = new TyreManagerImpl(context);
                }
            }
        }
        return mTyreManagerImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tyreCallback(int i8, int i9, CarBodyPacket carBodyPacket) {
        Log.i(TAG, "tyreCallback propertyId:" + i8);
        if (i8 == 557861386) {
            Iterator<AbsBYDAutoTyreListener> it = this.mAbsBYDAutoTyreListeners.iterator();
            while (it.hasNext()) {
                it.next().onTyreTurnStateChanged(carBodyPacket.getmTyreTurnState());
            }
            return;
        }
        if (i8 == 559958537) {
            Iterator<AbsBYDAutoTyreListener> it2 = this.mAbsBYDAutoTyreListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onTyreBatteryValueChanged(getTyreArea(), carBodyPacket.getmTyreBatteryValue());
            }
            return;
        }
        switch (i8) {
            case TyreHalId.ID_TYRE_AIR_LEAK_STATE /* 557861376 */:
                Iterator<AbsBYDAutoTyreListener> it3 = this.mAbsBYDAutoTyreListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onTyreAirLeakStateChanged(getTyreArea(), carBodyPacket.getmTyreAirLeakState());
                }
                break;
            case TyreHalId.ID_TYRE_BATTERY_STATE /* 557861377 */:
                Iterator<AbsBYDAutoTyreListener> it4 = this.mAbsBYDAutoTyreListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onTyreBatteryStateChanged(carBodyPacket.getmTyreBatteryState());
                }
                break;
            case TyreHalId.ID_TYRE_PRESSURE_STATE /* 557861378 */:
                Iterator<AbsBYDAutoTyreListener> it5 = this.mAbsBYDAutoTyreListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onTyrePressureStateChanged(getTyreArea(), carBodyPacket.getmTyrePressureState());
                }
                break;
            case TyreHalId.ID_TYRE_PRESSURE_VALUE /* 557861379 */:
                Iterator<AbsBYDAutoTyreListener> it6 = this.mAbsBYDAutoTyreListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onTyrePressureValueChanged(getTyreArea(), carBodyPacket.getmTyrePressureValue());
                }
                break;
            case TyreHalId.ID_TYRE_SIGNAL_STATE /* 557861380 */:
                Iterator<AbsBYDAutoTyreListener> it7 = this.mAbsBYDAutoTyreListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onTyreSignalStateChanged(getTyreArea(), carBodyPacket.getmTyreSignalState());
                }
                break;
            case TyreHalId.ID_TYRE_SYSTEM_STATE /* 557861381 */:
                Iterator<AbsBYDAutoTyreListener> it8 = this.mAbsBYDAutoTyreListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onTyreSystemStateChanged(carBodyPacket.getmTyreSystemState());
                }
                break;
            case TyreHalId.ID_TYRE_TEMPERATURE_STATE /* 557861382 */:
                Iterator<AbsBYDAutoTyreListener> it9 = this.mAbsBYDAutoTyreListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onTyreTemperatureStateChanged(carBodyPacket.getmTyreTemperatureState());
                }
                break;
            case TyreHalId.ID_INDIRECT_TYRE_SYSTEM_STATE /* 557861383 */:
                Iterator<AbsBYDAutoTyreListener> it10 = this.mAbsBYDAutoTyreListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onIndirectTyreSystemStateChanged(carBodyPacket.getmIndirectTyreSystemState());
                }
                break;
            default:
                switch (i8) {
                    case TyreHalId.ID_TYRE_PRESSURE_WARN_LEFT_FRONT_R /* 557861388 */:
                        Iterator<AbsBYDAutoTyreListener> it11 = this.mAbsBYDAutoTyreListeners.iterator();
                        while (it11.hasNext()) {
                            it11.next().onTyrePressureWarnChanged(1, carBodyPacket.getTyrePressureWarnLeftFrontR());
                        }
                        break;
                    case TyreHalId.ID_TYRE_PRESSURE_WARN_RIGHT_FRONT_R /* 557861389 */:
                        Iterator<AbsBYDAutoTyreListener> it12 = this.mAbsBYDAutoTyreListeners.iterator();
                        while (it12.hasNext()) {
                            it12.next().onTyrePressureWarnChanged(2, carBodyPacket.getTyrePressureWarnRightFrontR());
                        }
                        break;
                    case TyreHalId.ID_TYRE_PRESSURE_WARN_LEFT_REAR_R /* 557861390 */:
                        Iterator<AbsBYDAutoTyreListener> it13 = this.mAbsBYDAutoTyreListeners.iterator();
                        while (it13.hasNext()) {
                            it13.next().onTyrePressureWarnChanged(3, carBodyPacket.getTyrePressureWarnLeftRearR());
                        }
                        break;
                    case TyreHalId.ID_TYRE_PRESSURE_WARN_RIGHT_REAR_R /* 557861391 */:
                        Iterator<AbsBYDAutoTyreListener> it14 = this.mAbsBYDAutoTyreListeners.iterator();
                        while (it14.hasNext()) {
                            it14.next().onTyrePressureWarnChanged(4, carBodyPacket.getTyrePressureWarnRightRearR());
                        }
                        break;
                    default:
                        switch (i8) {
                            case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_LF_R /* 557869844 */:
                                Iterator<AbsBYDAutoTyreListener> it15 = this.mAbsBYDAutoTyreListeners.iterator();
                                while (it15.hasNext()) {
                                    it15.next().onIndirectTyrePressureStateChanged(1, carBodyPacket.getIndirectTyrePressureStateLfR());
                                }
                                caseFeatureIds(4027, Integer.valueOf(carBodyPacket.getIndirectTyrePressureStateLfR()));
                                break;
                            case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_RF_R /* 557869845 */:
                                Iterator<AbsBYDAutoTyreListener> it16 = this.mAbsBYDAutoTyreListeners.iterator();
                                while (it16.hasNext()) {
                                    it16.next().onIndirectTyrePressureStateChanged(2, carBodyPacket.getIndirectTyrePressureStateRfR());
                                }
                                caseFeatureIds(4029, Integer.valueOf(carBodyPacket.getIndirectTyrePressureStateRfR()));
                                break;
                            case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_LB_R /* 557869846 */:
                                Iterator<AbsBYDAutoTyreListener> it17 = this.mAbsBYDAutoTyreListeners.iterator();
                                while (it17.hasNext()) {
                                    it17.next().onIndirectTyrePressureStateChanged(3, carBodyPacket.getIndirectTyrePressureStateLbR());
                                }
                                caseFeatureIds(4028, Integer.valueOf(carBodyPacket.getIndirectTyrePressureStateLbR()));
                                break;
                            case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_RB_R /* 557869847 */:
                                Iterator<AbsBYDAutoTyreListener> it18 = this.mAbsBYDAutoTyreListeners.iterator();
                                while (it18.hasNext()) {
                                    it18.next().onIndirectTyrePressureStateChanged(4, carBodyPacket.getIndirectTyrePressureStateRbR());
                                }
                                caseFeatureIds(4030, Integer.valueOf(carBodyPacket.getIndirectTyrePressureStateRbR()));
                                break;
                            default:
                                switch (i8) {
                                    case TyreHalId.ID_TYRE_COLOR_BY_TYPE_LF_R /* 557875234 */:
                                        Iterator<AbsBYDAutoTyreListener> it19 = this.mAbsBYDAutoTyreListeners.iterator();
                                        while (it19.hasNext()) {
                                            it19.next().onTyreColorByTypeChanged(1, carBodyPacket.getTyreColorByTypeLfR());
                                        }
                                        break;
                                    case TyreHalId.ID_TYRE_COLOR_BY_TYPE_RF_R /* 557875235 */:
                                        Iterator<AbsBYDAutoTyreListener> it20 = this.mAbsBYDAutoTyreListeners.iterator();
                                        while (it20.hasNext()) {
                                            it20.next().onTyreColorByTypeChanged(2, carBodyPacket.getTyreColorByTypeRfR());
                                        }
                                        break;
                                    case TyreHalId.ID_TYRE_COLOR_BY_TYPE_LB_R /* 557875236 */:
                                        Iterator<AbsBYDAutoTyreListener> it21 = this.mAbsBYDAutoTyreListeners.iterator();
                                        while (it21.hasNext()) {
                                            it21.next().onTyreColorByTypeChanged(3, carBodyPacket.getTyreColorByTypeLbR());
                                        }
                                        break;
                                    case TyreHalId.ID_TYRE_COLOR_BY_TYPE_RB_R /* 557875237 */:
                                        Iterator<AbsBYDAutoTyreListener> it22 = this.mAbsBYDAutoTyreListeners.iterator();
                                        while (it22.hasNext()) {
                                            it22.next().onTyreColorByTypeChanged(4, carBodyPacket.getTyreColorByTypeRbR());
                                        }
                                        break;
                                    case TyreHalId.ID_TYRE_TEMPERATURE_VALUE_R /* 557875238 */:
                                        Iterator<AbsBYDAutoTyreListener> it23 = this.mAbsBYDAutoTyreListeners.iterator();
                                        while (it23.hasNext()) {
                                            it23.next().onTyreTemperatureValueChanged(getTyreArea(), carBodyPacket.getTyreTemperatureValueR());
                                        }
                                        break;
                                    case TyreHalId.ID_SYSTEM_UNDERVOLTAGE_STATUS_R /* 557875239 */:
                                        Iterator<AbsBYDAutoTyreListener> it24 = this.mAbsBYDAutoTyreListeners.iterator();
                                        while (it24.hasNext()) {
                                            it24.next().onSystemUndervoltageStatusChanged(carBodyPacket.getSystemUndervoltageStatusR());
                                        }
                                        break;
                                    default:
                                        switch (i8) {
                                            case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LF_R /* 559972381 */:
                                                Iterator<AbsBYDAutoTyreListener> it25 = this.mAbsBYDAutoTyreListeners.iterator();
                                                while (it25.hasNext()) {
                                                    it25.next().onTyrePressureValueByTypeChanged(1, carBodyPacket.getTyrePressureValueByTypeLfR());
                                                }
                                                caseFeatureIds(3684, Float.valueOf(carBodyPacket.getTyrePressureValueByTypeLfR()));
                                                break;
                                            case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RF_R /* 559972382 */:
                                                Iterator<AbsBYDAutoTyreListener> it26 = this.mAbsBYDAutoTyreListeners.iterator();
                                                while (it26.hasNext()) {
                                                    it26.next().onTyrePressureValueByTypeChanged(2, carBodyPacket.getTyrePressureValueByTypeRfR());
                                                }
                                                caseFeatureIds(3826, Float.valueOf(carBodyPacket.getTyrePressureValueByTypeRfR()));
                                                break;
                                            case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LB_R /* 559972383 */:
                                                Iterator<AbsBYDAutoTyreListener> it27 = this.mAbsBYDAutoTyreListeners.iterator();
                                                while (it27.hasNext()) {
                                                    it27.next().onTyrePressureValueByTypeChanged(3, carBodyPacket.getTyrePressureValueByTypeLbR());
                                                }
                                                caseFeatureIds(3810, Float.valueOf(carBodyPacket.getTyrePressureValueByTypeLbR()));
                                                break;
                                            case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RB_R /* 559972384 */:
                                                Iterator<AbsBYDAutoTyreListener> it28 = this.mAbsBYDAutoTyreListeners.iterator();
                                                while (it28.hasNext()) {
                                                    it28.next().onTyrePressureValueByTypeChanged(4, carBodyPacket.getTyrePressureValueByTypeRbR());
                                                }
                                                caseFeatureIds(3822, Float.valueOf(carBodyPacket.getTyrePressureValueByTypeRbR()));
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i8 : iArr) {
                if (i8 > 0) {
                    if (i8 == 84) {
                        int tyreAirLeakState = getTyreAirLeakState(1);
                        bYDAutoEventValue.intValue = tyreAirLeakState;
                        sparseArray.put(84, Integer.valueOf(tyreAirLeakState));
                    } else if (i8 == 119) {
                        int tyreSystemState = getTyreSystemState();
                        bYDAutoEventValue.intValue = tyreSystemState;
                        sparseArray.put(119, Integer.valueOf(tyreSystemState));
                    } else if (i8 == 376) {
                        int tyreTurnState = getTyreTurnState();
                        bYDAutoEventValue.intValue = tyreTurnState;
                        sparseArray.put(376, Integer.valueOf(tyreTurnState));
                    } else if (i8 == 3684) {
                        bYDAutoEventValue.floatValue = getTyrePressureValueByType(1);
                        sparseArray.put(3684, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i8 == 3810) {
                        bYDAutoEventValue.floatValue = getTyrePressureValueByType(3);
                        sparseArray.put(3810, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i8 == 3822) {
                        bYDAutoEventValue.floatValue = getTyrePressureValueByType(4);
                        sparseArray.put(3822, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i8 == 3826) {
                        bYDAutoEventValue.floatValue = getTyrePressureValueByType(2);
                        sparseArray.put(3826, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i8 == 116) {
                        int tyreSignalState = getTyreSignalState(1);
                        bYDAutoEventValue.intValue = tyreSignalState;
                        sparseArray.put(116, Integer.valueOf(tyreSignalState));
                    } else if (i8 != 117) {
                        switch (i8) {
                            case 105:
                                int tyreAirLeakState2 = getTyreAirLeakState(1);
                                bYDAutoEventValue.intValue = tyreAirLeakState2;
                                sparseArray.put(105, Integer.valueOf(tyreAirLeakState2));
                                break;
                            case 106:
                                int tyrePressureState = getTyrePressureState(1);
                                bYDAutoEventValue.intValue = tyrePressureState;
                                sparseArray.put(106, Integer.valueOf(tyrePressureState));
                                break;
                            case 107:
                                int tyrePressureValue = getTyrePressureValue(1);
                                bYDAutoEventValue.intValue = tyrePressureValue;
                                sparseArray.put(107, Integer.valueOf(tyrePressureValue));
                                break;
                            case 108:
                                int tyrePressureWarn = getTyrePressureWarn(1);
                                bYDAutoEventValue.intValue = tyrePressureWarn;
                                sparseArray.put(108, Integer.valueOf(tyrePressureWarn));
                                break;
                            case 109:
                                int tyrePressureWarn2 = getTyrePressureWarn(3);
                                bYDAutoEventValue.intValue = tyrePressureWarn2;
                                sparseArray.put(109, Integer.valueOf(tyrePressureWarn2));
                                break;
                            case 110:
                                int tyrePressureWarn3 = getTyrePressureWarn(2);
                                bYDAutoEventValue.intValue = tyrePressureWarn3;
                                sparseArray.put(110, Integer.valueOf(tyrePressureWarn3));
                                break;
                            case 111:
                                int tyrePressureWarn4 = getTyrePressureWarn(4);
                                bYDAutoEventValue.intValue = tyrePressureWarn4;
                                sparseArray.put(111, Integer.valueOf(tyrePressureWarn4));
                                break;
                            default:
                                switch (i8) {
                                    case 4027:
                                        int indirectTyrePressureState = getIndirectTyrePressureState(1);
                                        bYDAutoEventValue.intValue = indirectTyrePressureState;
                                        sparseArray.put(4027, Integer.valueOf(indirectTyrePressureState));
                                        break;
                                    case 4028:
                                        int indirectTyrePressureState2 = getIndirectTyrePressureState(3);
                                        bYDAutoEventValue.intValue = indirectTyrePressureState2;
                                        sparseArray.put(4028, Integer.valueOf(indirectTyrePressureState2));
                                        break;
                                    case 4029:
                                        int indirectTyrePressureState3 = getIndirectTyrePressureState(2);
                                        bYDAutoEventValue.intValue = indirectTyrePressureState3;
                                        sparseArray.put(4029, Integer.valueOf(indirectTyrePressureState3));
                                        break;
                                    case 4030:
                                        int indirectTyrePressureState4 = getIndirectTyrePressureState(4);
                                        bYDAutoEventValue.intValue = indirectTyrePressureState4;
                                        sparseArray.put(4030, Integer.valueOf(indirectTyrePressureState4));
                                        break;
                                    default:
                                        Log.v(TAG, "case fail ! please check out");
                                        break;
                                }
                                break;
                        }
                    } else {
                        int tyreTemperatureState = getTyreTemperatureState();
                        bYDAutoEventValue.intValue = tyreTemperatureState;
                        sparseArray.put(117, Integer.valueOf(tyreTemperatureState));
                    }
                    int i9 = 0;
                    for (int i10 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i9] = ((Integer) sparseArray.get(i10)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i9++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getIndirectTyrePressureState(int i8) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getIndirectTyrePressureState mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i8 == 1) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateLf();
        }
        if (i8 == 2) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateRf();
        }
        if (i8 == 3) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateLb();
        }
        if (i8 == 4) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateRb();
        }
        return 0;
    }

    public int getIndirectTyreSystemState() {
        Log.v(TAG, "getIndirectTyreSystemState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getIndirectTyreSystemState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyreSystemState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getSystemUndervoltageStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSystemUndervoltageStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getSystemUndervoltageStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getTyreAirLeakState(int i8) {
        Log.v(TAG, "getTyreAirLeakState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreAirLeakState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyreAirLeakState(i8);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTyreArea() {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyreArea();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getTyreArea mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getTyreBatteryState() {
        Log.v(TAG, "getTyreBatteryState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreBatteryState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyreBatteryState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public float getTyreBatteryValue(int i8) {
        Log.v(TAG, "getTyreBatteryValue is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreBatteryValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyreBatteryValue(i8);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public int getTyreBodyType() {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyreBodyType();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getTyreBodyType mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getTyreColorByType(int i8) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyreColorByType mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i8 == 1) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeLf();
        }
        if (i8 == 2) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeRf();
        }
        if (i8 == 3) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeLb();
        }
        if (i8 == 4) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeRb();
        }
        return 0;
    }

    public int getTyrePressureState(int i8) {
        Log.v(TAG, "getTyrePressureState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyrePressureState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureState(i8);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTyrePressureSysFailHint() {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyrePressureSysFailHint();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getTyrePressureSysFailHint mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getTyrePressureValue(int i8) {
        Log.v(TAG, "getTyrePressureValue is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyrePressureValue mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValue(i8);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public float getTyrePressureValueByType(int i8) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyrePressureValueByType mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0.0f;
        }
        if (i8 == 1) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeLf();
        }
        if (i8 == 2) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeRf();
        }
        if (i8 == 3) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeLb();
        }
        if (i8 == 4) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeRb();
        }
        return 0.0f;
    }

    public int getTyrePressureWarn(int i8) {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyrePressureWarn mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i8 == 1) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnLeftFront();
        }
        if (i8 == 2) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnRightFront();
        }
        if (i8 == 3) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnLeftRear();
        }
        if (i8 != 4) {
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnRightRear();
    }

    public int getTyrePressureWarnHint() {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 != this.mForTest) {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnHint();
            }
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, " getTyrePressureWarnHint mForTestValue is " + this.mForTest + " execute return");
        return -1;
    }

    public int getTyreSignalState(int i8) {
        Log.v(TAG, "getTyreSignalState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreSignalState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyreSignalState(i8);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTyreSystemState() {
        Log.v(TAG, "getTyreSystemState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreSystemState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyreSystemState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTyreTemperatureState() {
        Log.v(TAG, "getTyreTemperatureState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreTemperatureState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyreTemperatureState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getTyreTemperatureValue(int i8) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyreTemperatureValue mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyreTemperatureValue();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getTyreTurnState() {
        Log.v(TAG, "getTyreTurnState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreBatteryValue mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyreTurnState();
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoTyreListener> it = this.mAbsBydAutoTyreListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i8, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoTyreListener absBYDAutoTyreListener) {
        registerListener(absBYDAutoTyreListener, new int[0]);
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i8, Object obj) {
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
        if (i8 != 78) {
            Log.v(TAG, "case fail ! please check out");
            return -1;
        }
        setIndirectTyreConfirm(iIntValue);
        Log.v(TAG, "setIndirectTyreConfirm:" + iIntValue);
        return 1;
    }

    public int setIndirectTyreConfirm(int i8) {
        Log.v(TAG, "setIndirectTyreConfirm is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setIndirectTyreConfirm mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().setIndirectTyreConfirm(i8);
        }
        Log.v(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public void unregisterListener(AbsBYDAutoTyreListener absBYDAutoTyreListener) {
        Log.d(TAG, "unregisterListener is execute" + absBYDAutoTyreListener);
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_COMMON);
        if (absBYDAutoTyreListener == null) {
            return;
        }
        this.mAbsBYDAutoTyreListeners.remove(absBYDAutoTyreListener);
        unregisterConnectListener(absBYDAutoTyreListener);
    }

    public void registerListener(AbsBYDAutoTyreListener absBYDAutoTyreListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe tyre listener " + absBYDAutoTyreListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoTyreListener == null) {
            return;
        }
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_COMMON);
        if (!this.mAbsBYDAutoTyreListeners.contains(absBYDAutoTyreListener)) {
            this.mAbsBYDAutoTyreListeners.add(absBYDAutoTyreListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoTyreListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }
}
