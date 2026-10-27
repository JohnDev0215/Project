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
import com.ts.lib.caradapter.body.CarBodyManager;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.d;
import spi.e;

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
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                return;
            }
        }
        mTsManagerImpl = TsManagerImpl.getInstance(context);
        mTsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.tyre.tsimpl.TyreManagerImpl.1
            public void onBinderStateChanged(boolean z) {
                Log.v(TyreManagerImpl.TAG, "onBinderStateChanged is " + z);
            }

            public void onBodyChanged(int i2, int i3, CarBodyPacket carBodyPacket) {
                if (TyreManagerImpl.this.mAbsBYDAutoTyreListeners.isEmpty()) {
                    Log.v(TyreManagerImpl.TAG, "mAbsBYDAutoTyreListener is null. ");
                    return;
                }
                try {
                    TyreManagerImpl.this.tyreCallback(i2, i3, carBodyPacket);
                } catch (Exception unused) {
                    Log.e(TyreManagerImpl.TAG, "tyreCallback is error");
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(d.f("requires permission ", str));
        }
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
    public void tyreCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        int i3;
        int indirectTyrePressureStateLfR;
        Object objValueOf;
        float tyrePressureValueByTypeLfR;
        e.t("tyreCallback propertyId:", i, TAG);
        if (i == 557861386) {
            Iterator<AbsBYDAutoTyreListener> it = this.mAbsBYDAutoTyreListeners.iterator();
            while (it.hasNext()) {
                it.next().onTyreTurnStateChanged(carBodyPacket.getmTyreTurnState());
            }
            return;
        }
        if (i == 559958537) {
            Iterator<AbsBYDAutoTyreListener> it2 = this.mAbsBYDAutoTyreListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onTyreBatteryValueChanged(getTyreArea(), carBodyPacket.getmTyreBatteryValue());
            }
            return;
        }
        switch (i) {
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
                switch (i) {
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
                        switch (i) {
                            case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_LF_R /* 557869844 */:
                                Iterator<AbsBYDAutoTyreListener> it15 = this.mAbsBYDAutoTyreListeners.iterator();
                                while (it15.hasNext()) {
                                    it15.next().onIndirectTyrePressureStateChanged(1, carBodyPacket.getIndirectTyrePressureStateLfR());
                                }
                                i3 = 4027;
                                indirectTyrePressureStateLfR = carBodyPacket.getIndirectTyrePressureStateLfR();
                                break;
                            case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_RF_R /* 557869845 */:
                                Iterator<AbsBYDAutoTyreListener> it16 = this.mAbsBYDAutoTyreListeners.iterator();
                                while (it16.hasNext()) {
                                    it16.next().onIndirectTyrePressureStateChanged(2, carBodyPacket.getIndirectTyrePressureStateRfR());
                                }
                                i3 = 4029;
                                indirectTyrePressureStateLfR = carBodyPacket.getIndirectTyrePressureStateRfR();
                                break;
                            case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_LB_R /* 557869846 */:
                                Iterator<AbsBYDAutoTyreListener> it17 = this.mAbsBYDAutoTyreListeners.iterator();
                                while (it17.hasNext()) {
                                    it17.next().onIndirectTyrePressureStateChanged(3, carBodyPacket.getIndirectTyrePressureStateLbR());
                                }
                                i3 = 4028;
                                indirectTyrePressureStateLfR = carBodyPacket.getIndirectTyrePressureStateLbR();
                                break;
                            case TyreHalId.ID_INDIRECT_TYRE_PRESSURE_STATE_RB_R /* 557869847 */:
                                Iterator<AbsBYDAutoTyreListener> it18 = this.mAbsBYDAutoTyreListeners.iterator();
                                while (it18.hasNext()) {
                                    it18.next().onIndirectTyrePressureStateChanged(4, carBodyPacket.getIndirectTyrePressureStateRbR());
                                }
                                i3 = 4030;
                                indirectTyrePressureStateLfR = carBodyPacket.getIndirectTyrePressureStateRbR();
                                break;
                            default:
                                switch (i) {
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
                                        switch (i) {
                                            case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LF_R /* 559972381 */:
                                                Iterator<AbsBYDAutoTyreListener> it25 = this.mAbsBYDAutoTyreListeners.iterator();
                                                while (it25.hasNext()) {
                                                    it25.next().onTyrePressureValueByTypeChanged(1, carBodyPacket.getTyrePressureValueByTypeLfR());
                                                }
                                                i3 = 3684;
                                                tyrePressureValueByTypeLfR = carBodyPacket.getTyrePressureValueByTypeLfR();
                                                break;
                                            case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RF_R /* 559972382 */:
                                                Iterator<AbsBYDAutoTyreListener> it26 = this.mAbsBYDAutoTyreListeners.iterator();
                                                while (it26.hasNext()) {
                                                    it26.next().onTyrePressureValueByTypeChanged(2, carBodyPacket.getTyrePressureValueByTypeRfR());
                                                }
                                                i3 = 3826;
                                                tyrePressureValueByTypeLfR = carBodyPacket.getTyrePressureValueByTypeRfR();
                                                break;
                                            case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_LB_R /* 559972383 */:
                                                Iterator<AbsBYDAutoTyreListener> it27 = this.mAbsBYDAutoTyreListeners.iterator();
                                                while (it27.hasNext()) {
                                                    it27.next().onTyrePressureValueByTypeChanged(3, carBodyPacket.getTyrePressureValueByTypeLbR());
                                                }
                                                i3 = 3810;
                                                tyrePressureValueByTypeLfR = carBodyPacket.getTyrePressureValueByTypeLbR();
                                                break;
                                            case TyreHalId.ID_TYRE_PRESSURE_VALUE_BY_TYPE_RB_R /* 559972384 */:
                                                Iterator<AbsBYDAutoTyreListener> it28 = this.mAbsBYDAutoTyreListeners.iterator();
                                                while (it28.hasNext()) {
                                                    it28.next().onTyrePressureValueByTypeChanged(4, carBodyPacket.getTyrePressureValueByTypeRbR());
                                                }
                                                i3 = 3822;
                                                tyrePressureValueByTypeLfR = carBodyPacket.getTyrePressureValueByTypeRbR();
                                                break;
                                        }
                                        objValueOf = Float.valueOf(tyrePressureValueByTypeLfR);
                                        break;
                                }
                                caseFeatureIds(i3, objValueOf);
                                break;
                        }
                        objValueOf = Integer.valueOf(indirectTyrePressureStateLfR);
                        caseFeatureIds(i3, objValueOf);
                        break;
                }
                break;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r13, java.lang.Class<?> r14) {
        /*
            Method dump skipped, instruction units count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.tyre.tsimpl.TyreManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
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

    public int getIndirectTyreSystemState() {
        String str;
        Log.v(TAG, "getIndirectTyreSystemState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            str = "getIndirectTyreSystemState mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getIndirectTyreSystemState();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
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

    public int getTyreAirLeakState(int i) {
        String str;
        Log.v(TAG, "getTyreAirLeakState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            str = "getTyreAirLeakState mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyreAirLeakState(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getTyreArea() {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            e.s(e.k(" getTyreArea mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyreArea();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getTyreBatteryState() {
        String str;
        Log.v(TAG, "getTyreBatteryState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            str = "getTyreBatteryState mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyreBatteryState();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public float getTyreBatteryValue(int i) {
        String str;
        Log.v(TAG, "getTyreBatteryValue is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            str = "getTyreBatteryValue mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyreBatteryValue(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0.0f;
    }

    public int getTyreBodyType() {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            e.s(e.k(" getTyreBodyType mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyreBodyType();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
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

    public int getTyrePressureState(int i) {
        String str;
        Log.v(TAG, "getTyrePressureState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            str = "getTyrePressureState mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyrePressureState(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getTyrePressureSysFailHint() {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            e.s(e.k(" getTyrePressureSysFailHint mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureSysFailHint();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getTyrePressureValue(int i) {
        String str;
        Log.v(TAG, "getTyrePressureValue is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            str = "getTyrePressureValue mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyrePressureValue(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
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

    public int getTyrePressureWarn(int i) {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            e.s(e.k(" getTyrePressureWarn mForTestValue is "), this.mForTest, " execute return", TAG);
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

    public int getTyrePressureWarnHint() {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            e.s(e.k(" getTyrePressureWarnHint mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() != null) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnHint();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getTyreSignalState(int i) {
        String str;
        Log.v(TAG, "getTyreSignalState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            str = "getTyreSignalState mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyreSignalState(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getTyreSystemState() {
        String str;
        Log.v(TAG, "getTyreSystemState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            str = "getTyreSystemState mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyreSystemState();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getTyreTemperatureState() {
        String str;
        Log.v(TAG, "getTyreTemperatureState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            str = "getTyreTemperatureState mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyreTemperatureState();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getTyreTemperatureValue(int i) {
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
        String str;
        Log.v(TAG, "getTyreTurnState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            str = "getTyreBatteryValue mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().getTyreTurnState();
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoTyreListener> it = this.mAbsBydAutoTyreListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoTyreListener absBYDAutoTyreListener) {
        registerListener(absBYDAutoTyreListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoTyreListener absBYDAutoTyreListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe tyre listener ");
        sb.append(absBYDAutoTyreListener);
        sb.append(" featureIDs = ");
        d.v(iArr, sb, TAG);
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        String str;
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
                    str = "the type of value is not a suitable type";
                    Log.v(TAG, str);
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i != 78) {
            str = "case fail ! please check out";
            Log.v(TAG, str);
            return -1;
        }
        setIndirectTyreConfirm(iIntValue);
        Log.v(TAG, "setIndirectTyreConfirm:" + iIntValue);
        return 1;
    }

    public int setIndirectTyreConfirm(int i) {
        String str;
        Log.v(TAG, "setIndirectTyreConfirm is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_SET);
        if (1 == this.mForTest) {
            str = "setIndirectTyreConfirm mForTestValue is 0 execute return";
        } else {
            if (mTsManagerImpl.getCarBodyManager() != null) {
                return mTsManagerImpl.getCarBodyManager().setIndirectTyreConfirm(i);
            }
            str = "caradatper still connecting, please wait";
        }
        Log.v(TAG, str);
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
}
