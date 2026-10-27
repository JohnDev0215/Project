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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TyreManagerImpl extends AbsTsMyCar {
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
        this.mTsManger = mTsManagerImpl.getCarBodyManager();
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
    public void tyreCallback(int i, int i2, CarBodyPacket carBodyPacket) {
        Log.i(TAG, "tyreCallback propertyId:" + i);
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
                it2.next().onTyreBatteryValueChanged(i2, carBodyPacket.getmTyreBatteryValue());
            }
            return;
        }
        switch (i) {
            case TyreHalId.ID_TYRE_AIR_LEAK_STATE /* 557861376 */:
                Iterator<AbsBYDAutoTyreListener> it3 = this.mAbsBYDAutoTyreListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onTyreAirLeakStateChanged(i2, carBodyPacket.getmTyreAirLeakState());
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
                    it5.next().onTyrePressureStateChanged(i2, carBodyPacket.getmTyrePressureState());
                }
                break;
            case TyreHalId.ID_TYRE_PRESSURE_VALUE /* 557861379 */:
                Iterator<AbsBYDAutoTyreListener> it6 = this.mAbsBYDAutoTyreListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onTyrePressureValueChanged(i2, carBodyPacket.getmTyrePressureValue());
                }
                break;
            case TyreHalId.ID_TYRE_SIGNAL_STATE /* 557861380 */:
                Iterator<AbsBYDAutoTyreListener> it7 = this.mAbsBYDAutoTyreListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onTyreSignalStateChanged(i2, carBodyPacket.getmTyreSignalState());
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
                }
                break;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r13, java.lang.Class<?> r14) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.tyre.tsimpl.TyreManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
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

    public int getTyrePressureWarn(int i) {
        enforcePermission(BYDAutoTyreDevice.PERMISSION_BYDAUTO_TYRE_GET);
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
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
