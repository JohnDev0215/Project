package android.hardware.bydauto.vehiclehealth.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.util.SparseArray;
import com.ts.lib.caradapter.cabin.CabinPacket;
import com.ts.lib.caradapter.cabin.OnCabinChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class VehicleHealthManagerImpl extends AbsTsMyCar {
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_VehicleHealthImpl";
    protected static final String TAG = "VehicleHealthImpl";
    private static volatile VehicleHealthManagerImpl sInstance;
    private AbsBYDAutoVehicleHealthListener mAbsBydAutoHealthListener;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private TestVehicleHealthManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private int mForTest = 0;
    private CopyOnWriteArrayList<AbsBYDAutoVehicleHealthListener> mAbsHealthListeners = new CopyOnWriteArrayList<>();
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    VehicleHealthManagerImpl(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.vehiclehealth.tsimpl.VehicleHealthManagerImpl.1
            public void onCabinChanged(int i, int i2, CabinPacket cabinPacket) {
                Log.v(VehicleHealthManagerImpl.TAG, "vehicle health packet is " + cabinPacket.toString());
                VehicleHealthManagerImpl.this.healthCallback(i, i2, cabinPacket);
            }

            public void onCabinBinderChanged(boolean z) {
                Log.v(VehicleHealthManagerImpl.TAG, "cabin call back state :" + z);
            }
        });
    }

    public static VehicleHealthManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (VehicleHealthManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new VehicleHealthManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public String getGetPermission() {
        Log.v(TAG, "getGetPermission is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getGetPermission mForTestValue is" + this.mForTest + " execute return");
        }
        return "";
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getSetPermission mForTestValue is" + this.mForTest + " execute return");
        }
        return "";
    }

    public int getDevicetype() {
        Log.v(TAG, "getDevicetype is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getDevicetype mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getType() {
        Log.v(TAG, "getType is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getType mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int[] getFeatureList() {
        Log.v(TAG, "getFeatureList is run. ");
        if (1 != this.mForTest) {
            return new int[0];
        }
        Log.v(TAG, "getFeatureList mForTestValue is" + this.mForTest + " execute return");
        return new int[0];
    }

    public int getOilHint() {
        Log.v(TAG, "getOilHint is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getOilHint mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getOilHint();
    }

    public int getOilLifetime(int i) {
        Log.v(TAG, "getOilLifetime is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getOilLifetime mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeEngine();
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeTransmission();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeBrake();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeBattery();
        }
        if (i != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeElec();
    }

    public int resetOilLifetime(int i) {
        Log.v(TAG, "resetOilLifetime is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "resetOilLifetime mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestVehicleHealthManagerImplCallback.RESET_OIL_LIFE_TIME, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().resetOilLifetimeEngine();
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().resetOilLifetimeTransmission();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().resetOilLifetimeBrake();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().resetOilLifetimeBattery();
        }
        if (i != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().resetOilLifetimeELec();
    }

    public long[] getOilRecordList(int i) {
        Log.v(TAG, "getOilRecordList is run. ");
        if (1 != this.mForTest) {
            return new long[0];
        }
        Log.v(TAG, "getOilRecordList mForTestValue is" + this.mForTest + " execute return");
        return new long[0];
    }

    public int setOilRecordList(int i, long j, long j2, long j3) {
        Log.v(TAG, "setOilRecordList is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "setOilRecordList mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestVehicleHealthManagerImplCallback.SET_OIL_RECORD_LIST, i);
        }
        return 0;
    }

    public int getOilLevel() {
        Log.v(TAG, "getOilLevel is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getOilLevel mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getOilLevel();
    }

    public int getFrontWiperCheckState() {
        Log.v(TAG, "getFrontWiperCheckState is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getFrontWiperCheckState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getFrontWiperCheckState();
    }

    public int setFrontWiperCheckState(int i) {
        Log.v(TAG, "setFrontWiperCheckState is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "setFrontWiperCheckState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setFrontWiperCheckState(i);
    }

    public int getRearWiperCheckState() {
        Log.v(TAG, "getRearWiperCheckState is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearWiperCheckState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getRearWiperCheckState();
    }

    public int setRearWiperCheckState(int i) {
        Log.v(TAG, "setRearWiperCheckState is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "setRearWiperCheckState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestVehicleHealthManagerImplCallback.SET_REAR_WIPER_CHECK_STATE, i);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setRearWiperCheckState(i);
    }

    @Deprecated
    public int resetTirePressure() {
        Log.v(TAG, "resetTirePressure is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "resetTirePressure mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(TestVehicleHealthManagerImplCallback.RESET_TIRE_PRESSURE, 0);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().resetTirePressure();
    }

    public int getMileageValid() {
        Log.v(TAG, "getMileageValid is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getMileageValid mForTestValue is" + this.mForTest + " execute return");
        }
        return 0;
    }

    public int getOilUpdateState() {
        Log.v(TAG, "getOilUpdateState is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getOilUpdateState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getOilUpdateState();
    }

    public int getTyreFaultLocation() {
        Log.v(TAG, "getTyreFaultLocation is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTyreFaultLocation mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getTyreFaultLocation();
    }

    public int getEPBConfig() {
        Log.v(TAG, "getEPBConfig is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEPBConfig mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getEPBConfig();
    }

    public int getEPBType() {
        Log.v(TAG, "getEPBType is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEPBType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getEPBType();
    }

    public int setEPBState(int i) {
        Log.v(TAG, "setEPBState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setEPBState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setEPBState(i);
    }

    public int getEPBState() {
        Log.v(TAG, "getEPBState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEPBState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getEPBState();
    }

    public int setOilUpdateTag(int i) {
        Log.v(TAG, "setOilUpdateTag is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setOilUpdateTag mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setOilUpdateTag(i);
    }

    public int getOilMaintainanceHint(int i) {
        Log.v(TAG, "getOilMaintainanceHint is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getOilMaintainanceHint mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 0) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceHintEngine();
        }
        if (i == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceHintTransmission();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceHintBrake();
        }
        if (i == 3) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceHintCoolBladeBattery();
        }
        if (i != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceHintCoolMotorElec();
    }

    public int setTransportMode(int i) {
        Log.v(TAG, "setTransportMode is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setTransportMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setTransportMode(i);
    }

    public int getTransportMode() {
        Log.v(TAG, "getTransportMode is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTransportMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getTransportMode();
    }

    public int setPowerOffChargedState(int i) {
        Log.v(TAG, "setPowerOffChargedState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setPowerOffChargedState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setPowerOffChargedState(i);
    }

    public int getTransportModeStateHint() {
        Log.v(TAG, "getTransportModeStateHint is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTransportModeStateHint mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getTransportModeStateHint();
    }

    public int getEpbHint() {
        Log.v(TAG, "getEpbHint is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEpbHint mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getEpbHint();
    }

    public void registerListener(AbsBYDAutoVehicleHealthListener absBYDAutoVehicleHealthListener) {
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBYDAutoVehicleHealthListener(absBYDAutoVehicleHealthListener);
        } else {
            if (absBYDAutoVehicleHealthListener == null) {
                return;
            }
            registerListener(absBYDAutoVehicleHealthListener, new int[0]);
        }
    }

    public void registerListener(AbsBYDAutoVehicleHealthListener absBYDAutoVehicleHealthListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " subscribe health listener " + absBYDAutoVehicleHealthListener + " featureIDs = " + Arrays.toString(iArr));
        if (!this.mAbsHealthListeners.contains(absBYDAutoVehicleHealthListener)) {
            this.mAbsHealthListeners.add(absBYDAutoVehicleHealthListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoVehicleHealthListener);
        TsManagerImpl.getInstance(this.mContext).registerCabinCallback();
    }

    public void unregisterListener(AbsBYDAutoVehicleHealthListener absBYDAutoVehicleHealthListener) {
        Log.d(TAG, "unregisterListener is run. ");
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBYDAutoVehicleHealthListener(null);
            return;
        }
        Log.d(TAG, "unsubscribeHvacCallback: " + absBYDAutoVehicleHealthListener);
        if (absBYDAutoVehicleHealthListener == null) {
            return;
        }
        this.mAbsHealthListeners.remove(absBYDAutoVehicleHealthListener);
        unregisterConnectListener(absBYDAutoVehicleHealthListener);
    }

    public int getOilLifetimeNew(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getOilLifetimeNew mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeNew(i);
    }

    public int getOilMaintainanceDialogHint(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getOilMaintainanceDialogHint mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceDialogHint(i);
    }

    public int setOilMaintainanceNotHint(int i, int i2) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setOilMaintainanceNotHint mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setOilMaintainanceNotHint(i, i2);
    }

    public int getBatteryHealthStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getBatteryHealthStatus mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getBatteryHealthStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void healthCallback(int i, int i2, CabinPacket cabinPacket) {
        Log.i(TAG, "cabin propertyId:" + i);
        switch (i) {
            case HealthHalId.ID_OIL_HINT /* 557850624 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it = this.mAbsHealthListeners.iterator();
                while (it.hasNext()) {
                    it.next().onOilLifetimeChanged(cabinPacket.getOilHint());
                }
                break;
            case HealthHalId.ID_OIL_LIFE_TIME_ENGINE /* 557850625 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it2 = this.mAbsHealthListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onOilLifetimeChanged(0, cabinPacket.getOilLifetimeEngine());
                }
                caseFeatureIds(2126, Integer.valueOf(cabinPacket.getOilLifetimeEngine()));
                break;
            case HealthHalId.ID_OIL_LIFE_TIME_TRANSMISSION /* 557850626 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it3 = this.mAbsHealthListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onOilLifetimeChanged(1, cabinPacket.getOilLifetimeTransmission());
                }
                caseFeatureIds(2136, Integer.valueOf(cabinPacket.getOilLifetimeTransmission()));
                break;
            case HealthHalId.ID_OIL_LIFE_TIME_BRAKE /* 557850627 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it4 = this.mAbsHealthListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onOilLifetimeChanged(3, cabinPacket.getOilLifetimeBrake());
                }
                caseFeatureIds(2096, Integer.valueOf(cabinPacket.getOilLifetimeBrake()));
                break;
            case HealthHalId.ID_OIL_LIFE_TIME_BATTERY /* 557850628 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it5 = this.mAbsHealthListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onOilLifetimeChanged(4, cabinPacket.getOilLifetimeBattery());
                }
                caseFeatureIds(2094, Integer.valueOf(cabinPacket.getOilLifetimeBattery()));
                break;
            case HealthHalId.ID_OIL_LIFE_TIME_ELEC /* 557850629 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it6 = this.mAbsHealthListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onOilLifetimeChanged(2, cabinPacket.getOilLifetimeElec());
                }
                caseFeatureIds(2124, Integer.valueOf(cabinPacket.getOilLifetimeElec()));
                break;
            case HealthHalId.ID_RESET_OIL_LEFE_TIME_ENGINE /* 557850630 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it7 = this.mAbsHealthListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onResetOilLifetimeChanged(0, cabinPacket.getresetOilLifetimeEngine());
                }
                break;
            case HealthHalId.ID_RESET_OIL_LEFE_TIME_TRANSMISSION /* 557850631 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it8 = this.mAbsHealthListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().onResetOilLifetimeChanged(1, cabinPacket.getresetOilLifetimeTransmission());
                }
                break;
            case HealthHalId.ID_RESET_OIL_LEFE_TIME_BRAKE /* 557850632 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it9 = this.mAbsHealthListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().onResetOilLifetimeChanged(2, cabinPacket.getresetOilLifetimeBrake());
                }
                break;
            case HealthHalId.ID_RESET_OIL_LEFE_TIME_BATTERY /* 557850633 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it10 = this.mAbsHealthListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onResetOilLifetimeChanged(3, cabinPacket.getresetOilLifetimeBattery());
                }
                break;
            case HealthHalId.ID_RESET_OIL_LEFE_TIME_ELEC /* 557850634 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it11 = this.mAbsHealthListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onResetOilLifetimeChanged(4, cabinPacket.getresetOilLifetimeELec());
                }
                break;
            case HealthHalId.ID_OIL_LEVEL /* 557850635 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it12 = this.mAbsHealthListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onOilLevelChanged(cabinPacket.getOilLevel());
                }
                break;
            case HealthHalId.ID_FRONT_WIPER_CHECK_STATER /* 557850636 */:
                Log.v(TAG, "ID_FRONT_WIPER_CHECK_STATER value is " + cabinPacket.getFrontWiperCheckStateR());
                Iterator<AbsBYDAutoVehicleHealthListener> it13 = this.mAbsHealthListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onFrontWiperCheckStateChanged(cabinPacket.getFrontWiperCheckStateR());
                }
                break;
            case HealthHalId.ID_REAR_WIPER_CHECK_STATER /* 557850638 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it14 = this.mAbsHealthListeners.iterator();
                while (it14.hasNext()) {
                    it14.next().onRearWiperCheckStateChanged(cabinPacket.getRearWiperCheckStateR());
                }
                break;
            case HealthHalId.ID_RESET_TIRE_PRESSURE /* 557850640 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it15 = this.mAbsHealthListeners.iterator();
                while (it15.hasNext()) {
                    it15.next().onResetTirePressureStateChanged(cabinPacket.getresetTirePressure());
                }
                break;
            case HealthHalId.ID_OILUPDATESTATE /* 557850641 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it16 = this.mAbsHealthListeners.iterator();
                while (it16.hasNext()) {
                    it16.next().onOilUpdateStateChanged(cabinPacket.getOilUpdateState());
                }
                break;
            case HealthHalId.ID_EPB_STATE_R /* 557850647 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it17 = this.mAbsHealthListeners.iterator();
                while (it17.hasNext()) {
                    it17.next().onEPBStateChanged(cabinPacket.getEPBStateR());
                }
                break;
            case HealthHalId.ID_RESET_OIL_LIFETIME_CHANGED_ENGINER /* 557850648 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it18 = this.mAbsHealthListeners.iterator();
                while (it18.hasNext()) {
                    it18.next().onResetOilLifetimeChanged(0, cabinPacket.getResetOilLifetimeChangedEngineR());
                }
                caseFeatureIds(2118, Integer.valueOf(cabinPacket.getResetOilLifetimeChangedEngineR()));
                break;
            case HealthHalId.ID_RESET_OIL_LIFETIME_CHANGED_TRANS_MISSIONR /* 557850649 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it19 = this.mAbsHealthListeners.iterator();
                while (it19.hasNext()) {
                    it19.next().onResetOilLifetimeChanged(1, cabinPacket.getResetOilLifetimeChangedTransMissionR());
                }
                caseFeatureIds(2137, Integer.valueOf(cabinPacket.getResetOilLifetimeChangedTransMissionR()));
                break;
            case HealthHalId.ID_RESET_OIL_LIFETIME_CHANGED_BREAKR /* 557850650 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it20 = this.mAbsHealthListeners.iterator();
                while (it20.hasNext()) {
                    it20.next().onResetOilLifetimeChanged(3, cabinPacket.getResetOilLifetimeChangedBreakR());
                }
                caseFeatureIds(2097, Integer.valueOf(cabinPacket.getResetOilLifetimeChangedBreakR()));
                break;
            case HealthHalId.ID_RESET_OIL_LIFETIME_CHANGED_BATTERYR /* 557850651 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it21 = this.mAbsHealthListeners.iterator();
                while (it21.hasNext()) {
                    it21.next().onResetOilLifetimeChanged(4, cabinPacket.getResetOilLifetimeChangedBatteryR());
                }
                caseFeatureIds(2095, Integer.valueOf(cabinPacket.getResetOilLifetimeChangedBatteryR()));
                break;
            case HealthHalId.ID_RESET_OIL_LIFETIME_CHANGED_ELECR /* 557850652 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it22 = this.mAbsHealthListeners.iterator();
                while (it22.hasNext()) {
                    it22.next().onResetOilLifetimeChanged(2, cabinPacket.getResetOilLifetimeChangedElecR());
                }
                caseFeatureIds(2125, Integer.valueOf(cabinPacket.getResetOilLifetimeChangedElecR()));
                break;
            case HealthHalId.ID_OIL_MAINTAINANCE_HINT_OIL_LIFE /* 557850653 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it23 = this.mAbsHealthListeners.iterator();
                while (it23.hasNext()) {
                    it23.next().onOilMaintainanceHintChanged(0, cabinPacket.getOilMaintainanceHintOilLife());
                }
                caseFeatureIds(2102, Integer.valueOf(cabinPacket.getOilMaintainanceHintOilLife()));
                break;
            case HealthHalId.ID_OIL_MAINTAINANCE_HINT_TRANSMISSION_OILLIFE /* 557850654 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it24 = this.mAbsHealthListeners.iterator();
                while (it24.hasNext()) {
                    it24.next().onOilMaintainanceHintChanged(1, cabinPacket.getOilMaintainanceHintTransmissionOilLife());
                }
                caseFeatureIds(2103, Integer.valueOf(cabinPacket.getOilMaintainanceHintTransmissionOilLife()));
                break;
            case HealthHalId.ID_OIL_MAINTAINANCE_HINT_BRAKE_FLUID_LIFE /* 557850655 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it25 = this.mAbsHealthListeners.iterator();
                while (it25.hasNext()) {
                    it25.next().onOilMaintainanceHintChanged(2, cabinPacket.getOilMaintainanceHintBrakeFluidLife());
                }
                caseFeatureIds(2127, Integer.valueOf(cabinPacket.getOilMaintainanceHintBrakeFluidLife()));
                break;
            case HealthHalId.ID_OIL_MAINTAINANCE_HINT_LONG_LIFE_COOLANT /* 557850656 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it26 = this.mAbsHealthListeners.iterator();
                while (it26.hasNext()) {
                    it26.next().onOilMaintainanceHintChanged(4, cabinPacket.getOilMaintainanceHintLongLifeCoolant());
                }
                caseFeatureIds(2128, Integer.valueOf(cabinPacket.getOilMaintainanceHintLongLifeCoolant()));
                break;
            case HealthHalId.ID_OIL_MAINTAINANCE_HINT_BLADE_BATTERY_COOLANT /* 557850657 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it27 = this.mAbsHealthListeners.iterator();
                while (it27.hasNext()) {
                    it27.next().onOilMaintainanceHintChanged(3, cabinPacket.getOilMaintainanceHintBladeBatteryCoolant());
                }
                caseFeatureIds(2129, Integer.valueOf(cabinPacket.getOilMaintainanceHintBladeBatteryCoolant()));
                break;
            case HealthHalId.ID_TRANSPORT_MODE_R /* 557850659 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it28 = this.mAbsHealthListeners.iterator();
                while (it28.hasNext()) {
                    it28.next().onTransportModeChanged(cabinPacket.getTransportModeR());
                }
                break;
            case HealthHalId.ID_TRANSPORT_MODE_STATE_HINT_R /* 557850660 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it29 = this.mAbsHealthListeners.iterator();
                while (it29.hasNext()) {
                    it29.next().onTransportModeStateHintChanged(cabinPacket.getTransportModeStateHintR());
                }
                break;
            case HealthHalId.ID_EPB_HINT_R /* 557850662 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it30 = this.mAbsHealthListeners.iterator();
                while (it30.hasNext()) {
                    it30.next().onEPBHintChanged(cabinPacket.getEpbHintR());
                }
                break;
            case HealthHalId.ID_OIL_LIFETIME_NEW_OIL_LIFE_R /* 557850663 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it31 = this.mAbsHealthListeners.iterator();
                while (it31.hasNext()) {
                    it31.next().onOilLifetimeNewChanged(cabinPacket.getOilLifetimeNewOilLifeR());
                }
                break;
            case HealthHalId.ID_OIL_LIFETIME_NEW_TRANSMISSION_R /* 557850664 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it32 = this.mAbsHealthListeners.iterator();
                while (it32.hasNext()) {
                    it32.next().onOilLifetimeNewChanged(cabinPacket.getOilLifetimeNewTransmissionR());
                }
                break;
            case HealthHalId.ID_OIL_LIFETIME_NEW_BRAKE_FLUID_LIFE_BRAKE_FLUID_LIFE_R /* 557850665 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it33 = this.mAbsHealthListeners.iterator();
                while (it33.hasNext()) {
                    it33.next().onOilLifetimeNewChanged(cabinPacket.getOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR());
                }
                break;
            case HealthHalId.ID_OIL_LIFETIME_NEW_BLADE_BATTERY_COOLANT_R /* 557850666 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it34 = this.mAbsHealthListeners.iterator();
                while (it34.hasNext()) {
                    it34.next().onOilLifetimeNewChanged(cabinPacket.getOilLifetimeNewBladeBatteryCoolantR());
                }
                break;
            case HealthHalId.ID_OIL_LIFETIME_NEW_LONG_LIFE_COOLANT_LONG_LIFE_COOLANT_R /* 557850667 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it35 = this.mAbsHealthListeners.iterator();
                while (it35.hasNext()) {
                    it35.next().onOilLifetimeNewChanged(cabinPacket.getOilLifetimeNewLongLifeCoolantLongLifeCoolantR());
                }
                break;
            case HealthHalId.ID_OIL_MAINTENANCE_DIALOG_HINT_OIL_LIFE_R /* 557850668 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it36 = this.mAbsHealthListeners.iterator();
                while (it36.hasNext()) {
                    it36.next().onOilMaintainanceDialogHintChanged(cabinPacket.getOilMaintainanceDialogHintOilLifeR());
                }
                break;
            case HealthHalId.ID_OIL_MAINTENANCE_DIALOG_HINT_TRANSMISSION_R /* 557850669 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it37 = this.mAbsHealthListeners.iterator();
                while (it37.hasNext()) {
                    it37.next().onOilMaintainanceDialogHintChanged(cabinPacket.getOilMaintainanceDialogHintTransmissionR());
                }
                break;
            case HealthHalId.ID_OIL_MAINTENANCE_DIALOG_HINT_BLADE_BATTERY_COOLANT_R /* 557850671 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it38 = this.mAbsHealthListeners.iterator();
                while (it38.hasNext()) {
                    it38.next().onOilMaintainanceDialogHintChanged(cabinPacket.getOilMaintainanceDialogHintBladeBatteryCoolantR());
                }
                break;
            case HealthHalId.ID_OIL_MAINTENANCE_DIALOG_HINT_LONG_LIFE_COOLANT_R /* 557850672 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it39 = this.mAbsHealthListeners.iterator();
                while (it39.hasNext()) {
                    it39.next().onOilMaintainanceDialogHintChanged(cabinPacket.getOilMaintainanceDialogHintLongLifeCoolantR());
                }
                break;
            case HealthHalId.ID_OIL_MAINTENANCE_DIALOG_HINT_BRAKE_FLUID_LIFE_R /* 557850673 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it40 = this.mAbsHealthListeners.iterator();
                while (it40.hasNext()) {
                    it40.next().onOilMaintainanceDialogHintChanged(cabinPacket.getOilMaintainanceDialogHintBrakeFluidLifeR());
                }
                break;
            case HealthHalId.ID_BATTERY_HEALTH_STATUS_R /* 557850679 */:
                Iterator<AbsBYDAutoVehicleHealthListener> it41 = this.mAbsHealthListeners.iterator();
                while (it41.hasNext()) {
                    it41.next().onBatteryHealthStatusChanged(cabinPacket.getBatteryHealthStatusR());
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
            for (int i : iArr) {
                if (i > 0) {
                    switch (i) {
                        case 75:
                            bYDAutoEventValue.intValue = getTyreFaultLocation();
                            sparseArray.put(75, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 644:
                            bYDAutoEventValue.intValue = getEPBConfig();
                            sparseArray.put(644, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 646:
                            bYDAutoEventValue.intValue = getEPBState();
                            sparseArray.put(646, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 648:
                            bYDAutoEventValue.intValue = getEPBType();
                            sparseArray.put(648, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 649:
                            bYDAutoEventValue.intValue = getTransportMode();
                            sparseArray.put(649, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 650:
                            bYDAutoEventValue.intValue = getTransportModeStateHint();
                            sparseArray.put(650, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1057:
                            bYDAutoEventValue.intValue = getOilLevel();
                            sparseArray.put(1057, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1058:
                            bYDAutoEventValue.intValue = getOilLevel();
                            sparseArray.put(1058, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1059:
                            bYDAutoEventValue.intValue = getOilUpdateState();
                            sparseArray.put(1059, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1073:
                            bYDAutoEventValue.intValue = getFrontWiperCheckState();
                            sparseArray.put(1073, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1141:
                            bYDAutoEventValue.intValue = getRearWiperCheckState();
                            sparseArray.put(1141, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1263:
                            bYDAutoEventValue.intValue = getOilHint();
                            sparseArray.put(1263, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 1303:
                            bYDAutoEventValue.intValue = getRearWiperCheckState();
                            sparseArray.put(1303, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2094:
                            bYDAutoEventValue.intValue = getOilLifetime(3);
                            sparseArray.put(2094, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2096:
                            bYDAutoEventValue.intValue = getOilLifetime(2);
                            sparseArray.put(2096, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2102:
                            bYDAutoEventValue.intValue = getOilMaintainanceHint(0);
                            sparseArray.put(2102, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2103:
                            bYDAutoEventValue.intValue = getOilMaintainanceHint(1);
                            sparseArray.put(2103, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2124:
                            bYDAutoEventValue.intValue = getOilLifetime(4);
                            sparseArray.put(2124, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2126:
                            bYDAutoEventValue.intValue = getOilLifetime(0);
                            sparseArray.put(2126, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2127:
                            bYDAutoEventValue.intValue = getOilMaintainanceHint(2);
                            sparseArray.put(2127, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2128:
                            bYDAutoEventValue.intValue = getOilMaintainanceHint(4);
                            sparseArray.put(2128, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2129:
                            bYDAutoEventValue.intValue = getOilMaintainanceHint(3);
                            sparseArray.put(2129, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
                        case 2136:
                            bYDAutoEventValue.intValue = getOilLifetime(1);
                            sparseArray.put(2136, Integer.valueOf(bYDAutoEventValue.intValue));
                            break;
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
        Iterator<AbsBYDAutoVehicleHealthListener> it = this.mAbsHealthListeners.iterator();
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
        if (i == 624) {
            resetOilLifetime(iIntValue);
            return 1;
        }
        if (i == 625) {
            resetOilLifetime(iIntValue);
            return 1;
        }
        if (i == 647) {
            setEPBState(iIntValue);
            return 1;
        }
        if (i == 651) {
            setTransportMode(iIntValue);
            return 1;
        }
        if (i == 836) {
            resetOilLifetime(iIntValue);
            return 1;
        }
        if (i == 1056) {
            setOilUpdateTag(iIntValue);
            return 1;
        }
        if (i == 1074) {
            setFrontWiperCheckState(iIntValue);
            return 1;
        }
        if (i == 1264) {
            resetOilLifetime(iIntValue);
            return 1;
        }
        if (i == 1304) {
            setRearWiperCheckState(iIntValue);
            return 1;
        }
        if (i == 4566) {
            setPowerOffChargedState(iIntValue);
            return 1;
        }
        Log.v(TAG, "case fail ! please check out");
        return -1;
    }
}
