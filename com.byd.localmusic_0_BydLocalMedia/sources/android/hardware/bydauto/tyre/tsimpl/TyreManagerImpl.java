package android.hardware.bydauto.tyre.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.tyre.AbsBYDAutoTyreListener;
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

/* JADX INFO: loaded from: classes2.dex */
public class TyreManagerImpl extends AbsTsMyCar {
    private static final int LB_TYRE = 3;
    private static final int LF_TYRE = 1;
    private static final int RB_TYRE = 4;
    private static final int RF_TYRE = 2;
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_TyreManagerImpl";
    protected static final String TAG = "TyreManagerImpl";
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
        enforcePermission("android.permission.BYDAUTO_TYRE_COMMON");
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                return;
            }
        }
        mTsManagerImpl = TsManagerImpl.getInstance(context);
        mTsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.tyre.tsimpl.TyreManagerImpl.1
            public void onBodyChanged(int i2, int i3, CarBodyPacket carBodyPacket) {
                if (!TyreManagerImpl.this.mAbsBYDAutoTyreListeners.isEmpty()) {
                    try {
                        TyreManagerImpl.this.tyreCallback(i2, i3, carBodyPacket);
                        return;
                    } catch (Exception unused) {
                        Log.e(TyreManagerImpl.TAG, "tyreCallback is error");
                        return;
                    }
                }
                Log.v(TyreManagerImpl.TAG, "mAbsBYDAutoTyreListener is null. ");
            }

            public void onBinderStateChanged(boolean z) {
                Log.v(TyreManagerImpl.TAG, "onBinderStateChanged is " + z);
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

    public int getTyreAirLeakState(int i) {
        Log.v(TAG, "getTyreAirLeakState is run");
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreAirLeakState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreAirLeakState(i);
    }

    public int getTyreBatteryState() {
        Log.v(TAG, "getTyreBatteryState is run");
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreBatteryState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreBatteryState();
    }

    public int getTyrePressureState(int i) {
        Log.v(TAG, "getTyrePressureState is run");
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyrePressureState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyrePressureState(i);
    }

    public int getTyrePressureValue(int i) {
        Log.v(TAG, "getTyrePressureValue is run");
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyrePressureValue mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyrePressureValue(i);
    }

    public int getTyreSignalState(int i) {
        Log.v(TAG, "getTyreSignalState is run");
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreSignalState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreSignalState(i);
    }

    public int getTyreSystemState() {
        Log.v(TAG, "getTyreSystemState is run");
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreSystemState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreSystemState();
    }

    public int getTyreTemperatureState() {
        Log.v(TAG, "getTyreTemperatureState is run");
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreTemperatureState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreTemperatureState();
    }

    public int getIndirectTyreSystemState() {
        Log.v(TAG, "getIndirectTyreSystemState is run");
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getIndirectTyreSystemState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getIndirectTyreSystemState();
    }

    public int setIndirectTyreConfirm(int i) {
        Log.v(TAG, "setIndirectTyreConfirm is run");
        enforcePermission("android.permission.BYDAUTO_TYRE_SET");
        if (1 == this.mForTest) {
            Log.v(TAG, "setIndirectTyreConfirm mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().setIndirectTyreConfirm(i);
    }

    public float getTyreBatteryValue(int i) {
        Log.v(TAG, "getTyreBatteryValue is run");
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreBatteryValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreBatteryValue(i);
    }

    public int getTyreTurnState() {
        Log.v(TAG, "getTyreTurnState is run");
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreBatteryValue mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreTurnState();
    }

    public int getTyreBodyType() {
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyreBodyType mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreBodyType();
    }

    public int getTyrePressureWarn(int i) {
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyrePressureWarn mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnLeftFront();
        }
        if (i == 2) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnRightFront();
        }
        if (i == 3) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnLeftRear();
        }
        if (i != 4) {
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnRightRear();
    }

    public int getTyrePressureSysFailHint() {
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyrePressureSysFailHint mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyrePressureSysFailHint();
    }

    public int getTyrePressureWarnHint() {
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyrePressureWarnHint mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnHint();
    }

    public int getIndirectTyrePressureState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getIndirectTyrePressureState mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateLf();
        }
        if (i == 2) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateRf();
        }
        if (i == 3) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateLb();
        }
        if (i == 4) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateRb();
        }
        return 0;
    }

    public float getTyrePressureValueByType(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyrePressureValueByType mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0.0f;
        }
        if (i == 1) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeLf();
        }
        if (i == 2) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeRf();
        }
        if (i == 3) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeLb();
        }
        if (i == 4) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeRb();
        }
        return 0.0f;
    }

    public int getTyreColorByType(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyreColorByType mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeLf();
        }
        if (i == 2) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeRf();
        }
        if (i == 3) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeLb();
        }
        if (i == 4) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeRb();
        }
        return 0;
    }

    public int getTyreTemperatureValue(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyreTemperatureValue mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreTemperatureValue();
    }

    public int getSystemUndervoltageStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getSystemUndervoltageStatus mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getSystemUndervoltageStatus();
    }

    public int getTyreArea() {
        enforcePermission("android.permission.BYDAUTO_TYRE_GET");
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyreArea mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreArea();
    }

    public void registerListener(AbsBYDAutoTyreListener absBYDAutoTyreListener) {
        registerListener(absBYDAutoTyreListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoTyreListener absBYDAutoTyreListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe tyre listener " + absBYDAutoTyreListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoTyreListener == null) {
            return;
        }
        enforcePermission("android.permission.BYDAUTO_TYRE_COMMON");
        if (!this.mAbsBYDAutoTyreListeners.contains(absBYDAutoTyreListener)) {
            this.mAbsBYDAutoTyreListeners.add(absBYDAutoTyreListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoTyreListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    public void unregisterListener(AbsBYDAutoTyreListener absBYDAutoTyreListener) {
        Log.d(TAG, "unregisterListener is execute" + absBYDAutoTyreListener);
        enforcePermission("android.permission.BYDAUTO_TYRE_COMMON");
        if (absBYDAutoTyreListener == null) {
            return;
        }
        this.mAbsBYDAutoTyreListeners.remove(absBYDAutoTyreListener);
        unregisterConnectListener(absBYDAutoTyreListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tyreCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        Log.i(TAG, "tyreCallback propertyId:" + i);
        if (i == 557861386) {
            Iterator<AbsBYDAutoTyreListener> it = this.mAbsBYDAutoTyreListeners.iterator();
            while (it.hasNext()) {
                it.next().onTyreTurnStateChanged(carBodyPacket.getmTyreTurnState());
            }
            return;
        }
        if (i != 559958537) {
            switch (i) {
                case TyreHalId.ID_TYRE_AIR_LEAK_STATE /* 557861376 */:
                    Iterator<AbsBYDAutoTyreListener> it2 = this.mAbsBYDAutoTyreListeners.iterator();
                    while (it2.hasNext()) {
                        it2.next().onTyreAirLeakStateChanged(getTyreArea(), carBodyPacket.getmTyreAirLeakState());
                    }
                    break;
                case TyreHalId.ID_TYRE_BATTERY_STATE /* 557861377 */:
                    Iterator<AbsBYDAutoTyreListener> it3 = this.mAbsBYDAutoTyreListeners.iterator();
                    while (it3.hasNext()) {
                        it3.next().onTyreBatteryStateChanged(carBodyPacket.getmTyreBatteryState());
                    }
                    break;
                case TyreHalId.ID_TYRE_PRESSURE_STATE /* 557861378 */:
                    Iterator<AbsBYDAutoTyreListener> it4 = this.mAbsBYDAutoTyreListeners.iterator();
                    while (it4.hasNext()) {
                        it4.next().onTyrePressureStateChanged(getTyreArea(), carBodyPacket.getmTyrePressureState());
                    }
                    break;
                case TyreHalId.ID_TYRE_PRESSURE_VALUE /* 557861379 */:
                    Iterator<AbsBYDAutoTyreListener> it5 = this.mAbsBYDAutoTyreListeners.iterator();
                    while (it5.hasNext()) {
                        it5.next().onTyrePressureValueChanged(getTyreArea(), carBodyPacket.getmTyrePressureValue());
                    }
                    break;
                case TyreHalId.ID_TYRE_SIGNAL_STATE /* 557861380 */:
                    Iterator<AbsBYDAutoTyreListener> it6 = this.mAbsBYDAutoTyreListeners.iterator();
                    while (it6.hasNext()) {
                        it6.next().onTyreSignalStateChanged(getTyreArea(), carBodyPacket.getmTyreSignalState());
                    }
                    break;
                case TyreHalId.ID_TYRE_SYSTEM_STATE /* 557861381 */:
                    Iterator<AbsBYDAutoTyreListener> it7 = this.mAbsBYDAutoTyreListeners.iterator();
                    while (it7.hasNext()) {
                        it7.next().onTyreSystemStateChanged(carBodyPacket.getmTyreSystemState());
                    }
                    break;
                case TyreHalId.ID_TYRE_TEMPERATURE_STATE /* 557861382 */:
                    Iterator<AbsBYDAutoTyreListener> it8 = this.mAbsBYDAutoTyreListeners.iterator();
                    while (it8.hasNext()) {
                        it8.next().onTyreTemperatureStateChanged(carBodyPacket.getmTyreTemperatureState());
                    }
                    break;
                case TyreHalId.ID_INDIRECT_TYRE_SYSTEM_STATE /* 557861383 */:
                    Iterator<AbsBYDAutoTyreListener> it9 = this.mAbsBYDAutoTyreListeners.iterator();
                    while (it9.hasNext()) {
                        it9.next().onIndirectTyreSystemStateChanged(carBodyPacket.getmIndirectTyreSystemState());
                    }
                    break;
                default:
                    switch (i) {
                        case TyreHalId.ID_TYRE_PRESSURE_WARN_LEFT_FRONT_R /* 557861388 */:
                            Iterator<AbsBYDAutoTyreListener> it10 = this.mAbsBYDAutoTyreListeners.iterator();
                            while (it10.hasNext()) {
                                it10.next().onTyrePressureWarnChanged(1, carBodyPacket.getTyrePressureWarnLeftFrontR());
                            }
                            break;
                        case TyreHalId.ID_TYRE_PRESSURE_WARN_RIGHT_FRONT_R /* 557861389 */:
                            Iterator<AbsBYDAutoTyreListener> it11 = this.mAbsBYDAutoTyreListeners.iterator();
                            while (it11.hasNext()) {
                                it11.next().onTyrePressureWarnChanged(2, carBodyPacket.getTyrePressureWarnRightFrontR());
                            }
                            break;
                        case TyreHalId.ID_TYRE_PRESSURE_WARN_LEFT_REAR_R /* 557861390 */:
                            Iterator<AbsBYDAutoTyreListener> it12 = this.mAbsBYDAutoTyreListeners.iterator();
                            while (it12.hasNext()) {
                                it12.next().onTyrePressureWarnChanged(3, carBodyPacket.getTyrePressureWarnLeftRearR());
                            }
                            break;
                        case TyreHalId.ID_TYRE_PRESSURE_WARN_RIGHT_REAR_R /* 557861391 */:
                            Iterator<AbsBYDAutoTyreListener> it13 = this.mAbsBYDAutoTyreListeners.iterator();
                            while (it13.hasNext()) {
                                it13.next().onTyrePressureWarnChanged(4, carBodyPacket.getTyrePressureWarnRightRearR());
                            }
                            break;
                        default:
                            switch (i) {
                                case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_LF_R /* 557869844 */:
                                    Iterator<AbsBYDAutoTyreListener> it14 = this.mAbsBYDAutoTyreListeners.iterator();
                                    while (it14.hasNext()) {
                                        it14.next().onIndirectTyrePressureStateChanged(1, carBodyPacket.getIndirectTyrePressureStateLfR());
                                    }
                                    caseFeatureIds(4027, Integer.valueOf(carBodyPacket.getIndirectTyrePressureStateLfR()));
                                    break;
                                case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_RF_R /* 557869845 */:
                                    Iterator<AbsBYDAutoTyreListener> it15 = this.mAbsBYDAutoTyreListeners.iterator();
                                    while (it15.hasNext()) {
                                        it15.next().onIndirectTyrePressureStateChanged(2, carBodyPacket.getIndirectTyrePressureStateRfR());
                                    }
                                    caseFeatureIds(4029, Integer.valueOf(carBodyPacket.getIndirectTyrePressureStateRfR()));
                                    break;
                                case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_LB_R /* 557869846 */:
                                    Iterator<AbsBYDAutoTyreListener> it16 = this.mAbsBYDAutoTyreListeners.iterator();
                                    while (it16.hasNext()) {
                                        it16.next().onIndirectTyrePressureStateChanged(3, carBodyPacket.getIndirectTyrePressureStateLbR());
                                    }
                                    caseFeatureIds(4028, Integer.valueOf(carBodyPacket.getIndirectTyrePressureStateLbR()));
                                    break;
                                case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_RB_R /* 557869847 */:
                                    Iterator<AbsBYDAutoTyreListener> it17 = this.mAbsBYDAutoTyreListeners.iterator();
                                    while (it17.hasNext()) {
                                        it17.next().onIndirectTyrePressureStateChanged(4, carBodyPacket.getIndirectTyrePressureStateRbR());
                                    }
                                    caseFeatureIds(4030, Integer.valueOf(carBodyPacket.getIndirectTyrePressureStateRbR()));
                                    break;
                                default:
                                    switch (i) {
                                        case TyreHalId.ID_TYRE_COLOR_BY_TYPE_LF_R /* 557875234 */:
                                            Iterator<AbsBYDAutoTyreListener> it18 = this.mAbsBYDAutoTyreListeners.iterator();
                                            while (it18.hasNext()) {
                                                it18.next().onTyreColorByTypeChanged(1, carBodyPacket.getTyreColorByTypeLfR());
                                            }
                                            break;
                                        case TyreHalId.ID_TYRE_COLOR_BY_TYPE_RF_R /* 557875235 */:
                                            Iterator<AbsBYDAutoTyreListener> it19 = this.mAbsBYDAutoTyreListeners.iterator();
                                            while (it19.hasNext()) {
                                                it19.next().onTyreColorByTypeChanged(2, carBodyPacket.getTyreColorByTypeRfR());
                                            }
                                            break;
                                        case TyreHalId.ID_TYRE_COLOR_BY_TYPE_LB_R /* 557875236 */:
                                            Iterator<AbsBYDAutoTyreListener> it20 = this.mAbsBYDAutoTyreListeners.iterator();
                                            while (it20.hasNext()) {
                                                it20.next().onTyreColorByTypeChanged(3, carBodyPacket.getTyreColorByTypeLbR());
                                            }
                                            break;
                                        case TyreHalId.ID_TYRE_COLOR_BY_TYPE_RB_R /* 557875237 */:
                                            Iterator<AbsBYDAutoTyreListener> it21 = this.mAbsBYDAutoTyreListeners.iterator();
                                            while (it21.hasNext()) {
                                                it21.next().onTyreColorByTypeChanged(4, carBodyPacket.getTyreColorByTypeRbR());
                                            }
                                            break;
                                        case TyreHalId.ID_TYRE_TEMPERATURE_VALUE_R /* 557875238 */:
                                            Iterator<AbsBYDAutoTyreListener> it22 = this.mAbsBYDAutoTyreListeners.iterator();
                                            while (it22.hasNext()) {
                                                it22.next().onTyreTemperatureValueChanged(getTyreArea(), carBodyPacket.getTyreTemperatureValueR());
                                            }
                                            break;
                                        case TyreHalId.ID_SYSTEM_UNDERVOLTAGE_STATUS_R /* 557875239 */:
                                            Iterator<AbsBYDAutoTyreListener> it23 = this.mAbsBYDAutoTyreListeners.iterator();
                                            while (it23.hasNext()) {
                                                it23.next().onSystemUndervoltageStatusChanged(carBodyPacket.getSystemUndervoltageStatusR());
                                            }
                                            break;
                                        default:
                                            switch (i) {
                                                case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LF_R /* 559972381 */:
                                                    Iterator<AbsBYDAutoTyreListener> it24 = this.mAbsBYDAutoTyreListeners.iterator();
                                                    while (it24.hasNext()) {
                                                        it24.next().onTyrePressureValueByTypeChanged(1, carBodyPacket.getTyrePressureValueByTypeLfR());
                                                    }
                                                    caseFeatureIds(3684, Float.valueOf(carBodyPacket.getTyrePressureValueByTypeLfR()));
                                                    break;
                                                case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RF_R /* 559972382 */:
                                                    Iterator<AbsBYDAutoTyreListener> it25 = this.mAbsBYDAutoTyreListeners.iterator();
                                                    while (it25.hasNext()) {
                                                        it25.next().onTyrePressureValueByTypeChanged(2, carBodyPacket.getTyrePressureValueByTypeRfR());
                                                    }
                                                    caseFeatureIds(3826, Float.valueOf(carBodyPacket.getTyrePressureValueByTypeRfR()));
                                                    break;
                                                case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LB_R /* 559972383 */:
                                                    Iterator<AbsBYDAutoTyreListener> it26 = this.mAbsBYDAutoTyreListeners.iterator();
                                                    while (it26.hasNext()) {
                                                        it26.next().onTyrePressureValueByTypeChanged(3, carBodyPacket.getTyrePressureValueByTypeLbR());
                                                    }
                                                    caseFeatureIds(3810, Float.valueOf(carBodyPacket.getTyrePressureValueByTypeLbR()));
                                                    break;
                                                case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RB_R /* 559972384 */:
                                                    Iterator<AbsBYDAutoTyreListener> it27 = this.mAbsBYDAutoTyreListeners.iterator();
                                                    while (it27.hasNext()) {
                                                        it27.next().onTyrePressureValueByTypeChanged(4, carBodyPacket.getTyrePressureValueByTypeRbR());
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
            return;
        }
        Iterator<AbsBYDAutoTyreListener> it28 = this.mAbsBYDAutoTyreListeners.iterator();
        while (it28.hasNext()) {
            it28.next().onTyreBatteryValueChanged(getTyreArea(), carBodyPacket.getmTyreBatteryValue());
        }
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
                    if (i == 84) {
                        bYDAutoEventValue.intValue = getTyreAirLeakState(1);
                        sparseArray.put(84, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 119) {
                        bYDAutoEventValue.intValue = getTyreSystemState();
                        sparseArray.put(119, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 376) {
                        bYDAutoEventValue.intValue = getTyreTurnState();
                        sparseArray.put(376, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3684) {
                        bYDAutoEventValue.floatValue = getTyrePressureValueByType(1);
                        sparseArray.put(3684, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3810) {
                        bYDAutoEventValue.floatValue = getTyrePressureValueByType(3);
                        sparseArray.put(3810, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3822) {
                        bYDAutoEventValue.floatValue = getTyrePressureValueByType(4);
                        sparseArray.put(3822, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 3826) {
                        bYDAutoEventValue.floatValue = getTyrePressureValueByType(2);
                        sparseArray.put(3826, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i == 116) {
                        bYDAutoEventValue.intValue = getTyreSignalState(1);
                        sparseArray.put(116, Integer.valueOf(bYDAutoEventValue.intValue));
                    } else if (i != 117) {
                        switch (i) {
                            case 105:
                                bYDAutoEventValue.intValue = getTyreAirLeakState(1);
                                sparseArray.put(105, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 106:
                                bYDAutoEventValue.intValue = getTyrePressureState(1);
                                sparseArray.put(106, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 107:
                                bYDAutoEventValue.intValue = getTyrePressureValue(1);
                                sparseArray.put(107, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 108:
                                bYDAutoEventValue.intValue = getTyrePressureWarn(1);
                                sparseArray.put(108, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 109:
                                bYDAutoEventValue.intValue = getTyrePressureWarn(3);
                                sparseArray.put(109, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 110:
                                bYDAutoEventValue.intValue = getTyrePressureWarn(2);
                                sparseArray.put(110, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            case 111:
                                bYDAutoEventValue.intValue = getTyrePressureWarn(4);
                                sparseArray.put(111, Integer.valueOf(bYDAutoEventValue.intValue));
                                break;
                            default:
                                switch (i) {
                                    case 4027:
                                        bYDAutoEventValue.intValue = getIndirectTyrePressureState(1);
                                        sparseArray.put(4027, Integer.valueOf(bYDAutoEventValue.intValue));
                                        break;
                                    case 4028:
                                        bYDAutoEventValue.intValue = getIndirectTyrePressureState(3);
                                        sparseArray.put(4028, Integer.valueOf(bYDAutoEventValue.intValue));
                                        break;
                                    case 4029:
                                        bYDAutoEventValue.intValue = getIndirectTyrePressureState(2);
                                        sparseArray.put(4029, Integer.valueOf(bYDAutoEventValue.intValue));
                                        break;
                                    case 4030:
                                        bYDAutoEventValue.intValue = getIndirectTyrePressureState(4);
                                        sparseArray.put(4030, Integer.valueOf(bYDAutoEventValue.intValue));
                                        break;
                                    default:
                                        Log.v(TAG, "case fail ! please check out");
                                        break;
                                }
                                break;
                        }
                    } else {
                        bYDAutoEventValue.intValue = getTyreTemperatureState();
                        sparseArray.put(117, Integer.valueOf(bYDAutoEventValue.intValue));
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
        Iterator<AbsBYDAutoTyreListener> it = this.mAbsBydAutoTyreListeners.iterator();
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
        if (i == 78) {
            setIndirectTyreConfirm(iIntValue);
            Log.v(TAG, "setIndirectTyreConfirm:" + iIntValue);
            return 1;
        }
        Log.v(TAG, "case fail ! please check out");
        return -1;
    }
}
