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

    private TyreManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_COMMON);
        ContentResolver contentResolver = con.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                return;
            }
        }
        mTsManagerImpl = TsManagerImpl.getInstance(con);
        mTsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.tyre.tsimpl.TyreManagerImpl.1
            public void onBodyChanged(int id, int areaId, CarBodyPacket packet) {
                if (!TyreManagerImpl.this.mAbsBYDAutoTyreListeners.isEmpty()) {
                    try {
                        TyreManagerImpl.this.tyreCallback(id, areaId, packet);
                        return;
                    } catch (Exception e) {
                        Log.e(TyreManagerImpl.TAG, "tyreCallback is error");
                        return;
                    }
                }
                Log.v(TyreManagerImpl.TAG, "mAbsBYDAutoTyreListener is null. ");
            }

            public void onBinderStateChanged(boolean binderState) {
                Log.v(TyreManagerImpl.TAG, "onBinderStateChanged is " + binderState);
            }
        });
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
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

    public int getTyreAirLeakState(int area) {
        Log.v(TAG, "getTyreAirLeakState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreAirLeakState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreAirLeakState(area);
    }

    public int getTyreBatteryState() {
        Log.v(TAG, "getTyreBatteryState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
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

    public int getTyrePressureState(int area) {
        Log.v(TAG, "getTyrePressureState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyrePressureState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyrePressureState(area);
    }

    public int getTyrePressureValue(int area) {
        Log.v(TAG, "getTyrePressureValue is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyrePressureValue mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyrePressureValue(area);
    }

    public int getTyreSignalState(int area) {
        Log.v(TAG, "getTyreSignalState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreSignalState mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreSignalState(area);
    }

    public int getTyreSystemState() {
        Log.v(TAG, "getTyreSystemState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
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
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
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
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
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

    public int setIndirectTyreConfirm(int value) {
        Log.v(TAG, "setIndirectTyreConfirm is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setIndirectTyreConfirm mForTestValue is 0 execute return");
            return 0;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().setIndirectTyreConfirm(value);
    }

    public float getTyreBatteryValue(int area) {
        Log.v(TAG, "getTyreBatteryValue is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreBatteryValue mForTestValue is 0 execute return");
            return 0.0f;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        return mTsManagerImpl.getCarBodyManager().getTyreBatteryValue(area);
    }

    public int getTyreTurnState() {
        Log.v(TAG, "getTyreTurnState is run");
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
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
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
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

    public int getTyrePressureWarn(int area) {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyrePressureWarn mForTestValue is " + this.mForTest + " execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 1) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnLeftFront();
        }
        if (area == 2) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnRightFront();
        }
        if (area == 3) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnLeftRear();
        }
        if (area != 4) {
            return 0;
        }
        return mTsManagerImpl.getCarBodyManager().getTyrePressureWarnRightRear();
    }

    public int getTyrePressureSysFailHint() {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
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
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
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

    public int getIndirectTyrePressureState(int area) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getIndirectTyrePressureState mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 1) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateLf();
        }
        if (area == 2) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateRf();
        }
        if (area == 3) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateLb();
        }
        if (area == 4) {
            return mTsManagerImpl.getCarBodyManager().getIndirectTyrePressureStateRb();
        }
        return 0;
    }

    public float getTyrePressureValueByType(int area) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyrePressureValueByType mForTestValue is 0 execute return");
            return -1.0f;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0.0f;
        }
        if (area == 1) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeLf();
        }
        if (area == 2) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeRf();
        }
        if (area == 3) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeLb();
        }
        if (area == 4) {
            return mTsManagerImpl.getCarBodyManager().getTyrePressureValueByTypeRb();
        }
        return 0.0f;
    }

    public int getTyreColorByType(int area) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getTyreColorByType mForTestValue is 0 execute return");
            return -1;
        }
        if (mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (area == 1) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeLf();
        }
        if (area == 2) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeRf();
        }
        if (area == 3) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeLb();
        }
        if (area == 4) {
            return mTsManagerImpl.getCarBodyManager().getTyreColorByTypeRb();
        }
        return 0;
    }

    public int getTyreTemperatureValue(int area) {
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
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
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

    public void registerListener(AbsBYDAutoTyreListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoTyreListener listener, int[] featureIDs) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe tyre listener " + listener + " featureIDs = " + Arrays.toString(featureIDs));
        if (listener == null) {
            return;
        }
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_COMMON);
        if (!this.mAbsBYDAutoTyreListeners.contains(listener)) {
            this.mAbsBYDAutoTyreListeners.add(listener);
        }
        setFeatureIds(featureIDs);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    public void unregisterListener(AbsBYDAutoTyreListener listener) {
        Log.d(TAG, "unregisterListener is execute" + listener);
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_COMMON);
        if (listener == null) {
            return;
        }
        this.mAbsBYDAutoTyreListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tyreCallback(int id, int areaId, CarBodyPacket packet) {
        Log.i(TAG, "tyreCallback propertyId:" + id);
        if (id == 557861386) {
            for (AbsBYDAutoTyreListener listener : this.mAbsBYDAutoTyreListeners) {
                listener.onTyreTurnStateChanged(packet.getmTyreTurnState());
            }
            return;
        }
        if (id != 559958537) {
            switch (id) {
                case 557861376:
                    for (AbsBYDAutoTyreListener listener2 : this.mAbsBYDAutoTyreListeners) {
                        listener2.onTyreAirLeakStateChanged(getTyreArea(), packet.getmTyreAirLeakState());
                    }
                    break;
                case 557861377:
                    for (AbsBYDAutoTyreListener listener3 : this.mAbsBYDAutoTyreListeners) {
                        listener3.onTyreBatteryStateChanged(packet.getmTyreBatteryState());
                    }
                    break;
                case 557861378:
                    for (AbsBYDAutoTyreListener listener4 : this.mAbsBYDAutoTyreListeners) {
                        listener4.onTyrePressureStateChanged(getTyreArea(), packet.getmTyrePressureState());
                    }
                    break;
                case 557861379:
                    for (AbsBYDAutoTyreListener listener5 : this.mAbsBYDAutoTyreListeners) {
                        listener5.onTyrePressureValueChanged(getTyreArea(), packet.getmTyrePressureValue());
                    }
                    break;
                case 557861380:
                    for (AbsBYDAutoTyreListener listener6 : this.mAbsBYDAutoTyreListeners) {
                        listener6.onTyreSignalStateChanged(getTyreArea(), packet.getmTyreSignalState());
                    }
                    break;
                case 557861381:
                    for (AbsBYDAutoTyreListener listener7 : this.mAbsBYDAutoTyreListeners) {
                        listener7.onTyreSystemStateChanged(packet.getmTyreSystemState());
                    }
                    break;
                case 557861382:
                    for (AbsBYDAutoTyreListener listener8 : this.mAbsBYDAutoTyreListeners) {
                        listener8.onTyreTemperatureStateChanged(packet.getmTyreTemperatureState());
                    }
                    break;
                case 557861383:
                    for (AbsBYDAutoTyreListener listener9 : this.mAbsBYDAutoTyreListeners) {
                        listener9.onIndirectTyreSystemStateChanged(packet.getmIndirectTyreSystemState());
                    }
                    break;
                default:
                    switch (id) {
                        case 557861388:
                            for (AbsBYDAutoTyreListener listener10 : this.mAbsBYDAutoTyreListeners) {
                                listener10.onTyrePressureWarnChanged(1, packet.getTyrePressureWarnLeftFrontR());
                            }
                            break;
                        case 557861389:
                            for (AbsBYDAutoTyreListener listener11 : this.mAbsBYDAutoTyreListeners) {
                                listener11.onTyrePressureWarnChanged(2, packet.getTyrePressureWarnRightFrontR());
                            }
                            break;
                        case 557861390:
                            for (AbsBYDAutoTyreListener listener12 : this.mAbsBYDAutoTyreListeners) {
                                listener12.onTyrePressureWarnChanged(3, packet.getTyrePressureWarnLeftRearR());
                            }
                            break;
                        case 557861391:
                            for (AbsBYDAutoTyreListener listener13 : this.mAbsBYDAutoTyreListeners) {
                                listener13.onTyrePressureWarnChanged(4, packet.getTyrePressureWarnRightRearR());
                            }
                            break;
                        default:
                            switch (id) {
                                case 557869844:
                                    for (AbsBYDAutoTyreListener listener14 : this.mAbsBYDAutoTyreListeners) {
                                        listener14.onIndirectTyrePressureStateChanged(1, packet.getIndirectTyrePressureStateLfR());
                                    }
                                    caseFeatureIds(4027, Integer.valueOf(packet.getIndirectTyrePressureStateLfR()));
                                    break;
                                case 557869845:
                                    for (AbsBYDAutoTyreListener listener15 : this.mAbsBYDAutoTyreListeners) {
                                        listener15.onIndirectTyrePressureStateChanged(2, packet.getIndirectTyrePressureStateRfR());
                                    }
                                    caseFeatureIds(4029, Integer.valueOf(packet.getIndirectTyrePressureStateRfR()));
                                    break;
                                case 557869846:
                                    for (AbsBYDAutoTyreListener listener16 : this.mAbsBYDAutoTyreListeners) {
                                        listener16.onIndirectTyrePressureStateChanged(3, packet.getIndirectTyrePressureStateLbR());
                                    }
                                    caseFeatureIds(4028, Integer.valueOf(packet.getIndirectTyrePressureStateLbR()));
                                    break;
                                case 557869847:
                                    for (AbsBYDAutoTyreListener listener17 : this.mAbsBYDAutoTyreListeners) {
                                        listener17.onIndirectTyrePressureStateChanged(4, packet.getIndirectTyrePressureStateRbR());
                                    }
                                    caseFeatureIds(4030, Integer.valueOf(packet.getIndirectTyrePressureStateRbR()));
                                    break;
                                default:
                                    switch (id) {
                                        case 557875234:
                                            for (AbsBYDAutoTyreListener listener18 : this.mAbsBYDAutoTyreListeners) {
                                                listener18.onTyreColorByTypeChanged(1, packet.getTyreColorByTypeLfR());
                                            }
                                            break;
                                        case 557875235:
                                            for (AbsBYDAutoTyreListener listener19 : this.mAbsBYDAutoTyreListeners) {
                                                listener19.onTyreColorByTypeChanged(2, packet.getTyreColorByTypeRfR());
                                            }
                                            break;
                                        case 557875236:
                                            for (AbsBYDAutoTyreListener listener20 : this.mAbsBYDAutoTyreListeners) {
                                                listener20.onTyreColorByTypeChanged(3, packet.getTyreColorByTypeLbR());
                                            }
                                            break;
                                        case 557875237:
                                            for (AbsBYDAutoTyreListener listener21 : this.mAbsBYDAutoTyreListeners) {
                                                listener21.onTyreColorByTypeChanged(4, packet.getTyreColorByTypeRbR());
                                            }
                                            break;
                                        case 557875238:
                                            for (AbsBYDAutoTyreListener listener22 : this.mAbsBYDAutoTyreListeners) {
                                                listener22.onTyreTemperatureValueChanged(getTyreArea(), packet.getTyreTemperatureValueR());
                                            }
                                            break;
                                        case 557875239:
                                            for (AbsBYDAutoTyreListener listener23 : this.mAbsBYDAutoTyreListeners) {
                                                listener23.onSystemUndervoltageStatusChanged(packet.getSystemUndervoltageStatusR());
                                            }
                                            break;
                                        default:
                                            switch (id) {
                                                case 559972381:
                                                    for (AbsBYDAutoTyreListener listener24 : this.mAbsBYDAutoTyreListeners) {
                                                        listener24.onTyrePressureValueByTypeChanged(1, packet.getTyrePressureValueByTypeLfR());
                                                    }
                                                    caseFeatureIds(3684, Float.valueOf(packet.getTyrePressureValueByTypeLfR()));
                                                    break;
                                                case 559972382:
                                                    for (AbsBYDAutoTyreListener listener25 : this.mAbsBYDAutoTyreListeners) {
                                                        listener25.onTyrePressureValueByTypeChanged(2, packet.getTyrePressureValueByTypeRfR());
                                                    }
                                                    caseFeatureIds(3826, Float.valueOf(packet.getTyrePressureValueByTypeRfR()));
                                                    break;
                                                case 559972383:
                                                    for (AbsBYDAutoTyreListener listener26 : this.mAbsBYDAutoTyreListeners) {
                                                        listener26.onTyrePressureValueByTypeChanged(3, packet.getTyrePressureValueByTypeLbR());
                                                    }
                                                    caseFeatureIds(3810, Float.valueOf(packet.getTyrePressureValueByTypeLbR()));
                                                    break;
                                                case 559972384:
                                                    for (AbsBYDAutoTyreListener listener27 : this.mAbsBYDAutoTyreListeners) {
                                                        listener27.onTyrePressureValueByTypeChanged(4, packet.getTyrePressureValueByTypeRbR());
                                                    }
                                                    caseFeatureIds(3822, Float.valueOf(packet.getTyrePressureValueByTypeRbR()));
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
        for (AbsBYDAutoTyreListener listener28 : this.mAbsBYDAutoTyreListeners) {
            listener28.onTyreBatteryValueChanged(getTyreArea(), packet.getmTyreBatteryValue());
        }
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
                    if (eventType == 84) {
                        bydAutoEventValue.intValue = getTyreAirLeakState(i);
                        sparseArray.put(84, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 119) {
                        bydAutoEventValue.intValue = getTyreSystemState();
                        sparseArray.put(119, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 376) {
                        bydAutoEventValue.intValue = getTyreTurnState();
                        sparseArray.put(376, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3684) {
                        bydAutoEventValue.floatValue = getTyrePressureValueByType(i);
                        sparseArray.put(3684, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3810) {
                        bydAutoEventValue.floatValue = getTyrePressureValueByType(3);
                        sparseArray.put(3810, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3822) {
                        bydAutoEventValue.floatValue = getTyrePressureValueByType(4);
                        sparseArray.put(3822, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 3826) {
                        bydAutoEventValue.floatValue = getTyrePressureValueByType(2);
                        sparseArray.put(3826, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType == 116) {
                        bydAutoEventValue.intValue = getTyreSignalState(i);
                        sparseArray.put(116, Integer.valueOf(bydAutoEventValue.intValue));
                    } else if (eventType != 117) {
                        switch (eventType) {
                            case 105:
                                bydAutoEventValue.intValue = getTyreAirLeakState(i);
                                sparseArray.put(105, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 106:
                                bydAutoEventValue.intValue = getTyrePressureState(i);
                                sparseArray.put(106, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 107:
                                bydAutoEventValue.intValue = getTyrePressureValue(i);
                                sparseArray.put(107, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 108:
                                bydAutoEventValue.intValue = getTyrePressureWarn(i);
                                sparseArray.put(108, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 109:
                                bydAutoEventValue.intValue = getTyrePressureWarn(3);
                                sparseArray.put(109, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 110:
                                bydAutoEventValue.intValue = getTyrePressureWarn(2);
                                sparseArray.put(110, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            case 111:
                                bydAutoEventValue.intValue = getTyrePressureWarn(4);
                                sparseArray.put(111, Integer.valueOf(bydAutoEventValue.intValue));
                                break;
                            default:
                                switch (eventType) {
                                    case 4027:
                                        bydAutoEventValue.intValue = getIndirectTyrePressureState(i);
                                        sparseArray.put(4027, Integer.valueOf(bydAutoEventValue.intValue));
                                        break;
                                    case 4028:
                                        bydAutoEventValue.intValue = getIndirectTyrePressureState(3);
                                        sparseArray.put(4028, Integer.valueOf(bydAutoEventValue.intValue));
                                        break;
                                    case 4029:
                                        bydAutoEventValue.intValue = getIndirectTyrePressureState(2);
                                        sparseArray.put(4029, Integer.valueOf(bydAutoEventValue.intValue));
                                        break;
                                    case 4030:
                                        bydAutoEventValue.intValue = getIndirectTyrePressureState(4);
                                        sparseArray.put(4030, Integer.valueOf(bydAutoEventValue.intValue));
                                        break;
                                    default:
                                        Log.v(TAG, "case fail ! please check out");
                                        break;
                                }
                                break;
                        }
                    } else {
                        bydAutoEventValue.intValue = getTyreTemperatureState();
                        sparseArray.put(117, Integer.valueOf(bydAutoEventValue.intValue));
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
        for (AbsBYDAutoTyreListener listener : this.mAbsBydAutoTyreListeners) {
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
        if (eventType == 78) {
            setIndirectTyreConfirm(intValue);
            Log.v(TAG, "setIndirectTyreConfirm:" + intValue);
            return 1;
        }
        Log.v(TAG, "case fail ! please check out");
        return -1;
    }
}
