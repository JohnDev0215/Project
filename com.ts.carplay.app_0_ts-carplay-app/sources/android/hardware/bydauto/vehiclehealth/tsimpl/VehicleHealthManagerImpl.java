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

    VehicleHealthManagerImpl(Context con) {
        this.mContext = con;
        this.mContentResolver = con.getContentResolver();
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnCabinChangedListener(new OnCabinChangedListener() { // from class: android.hardware.bydauto.vehiclehealth.tsimpl.VehicleHealthManagerImpl.1
            public void onCabinChanged(int id, int areaId, CabinPacket packet) {
                Log.v(VehicleHealthManagerImpl.TAG, "vehicle health packet is " + packet.toString());
                try {
                    VehicleHealthManagerImpl.this.healthCallback(id, areaId, packet);
                } catch (Exception e) {
                    Log.e(VehicleHealthManagerImpl.TAG, "healthCallback is error");
                }
            }

            public void onCabinBinderChanged(boolean binderState) {
                Log.v(VehicleHealthManagerImpl.TAG, "cabin call back state :" + binderState);
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
        if (1 != this.mForTest) {
            return "";
        }
        Log.v(TAG, "getGetPermission mForTestValue is" + this.mForTest + " execute return");
        return "";
    }

    public String getSetPermission() {
        Log.v(TAG, "getSetPermission is run. ");
        if (1 != this.mForTest) {
            return "";
        }
        Log.v(TAG, "getSetPermission mForTestValue is" + this.mForTest + " execute return");
        return "";
    }

    public int getDevicetype() {
        Log.v(TAG, "getDevicetype is run. ");
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getDevicetype mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getType() {
        Log.v(TAG, "getType is run. ");
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int[] getFeatureList() {
        Log.v(TAG, "getFeatureList is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getFeatureList mForTestValue is" + this.mForTest + " execute return");
            return new int[0];
        }
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

    public int getOilLifetime(int type) {
        Log.v(TAG, "getOilLifetime is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getOilLifetime mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (type == 0) {
            int resultOil = this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeEngine();
            return resultOil;
        }
        if (type == 1) {
            int resultOil2 = this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeTransmission();
            return resultOil2;
        }
        if (type == 2) {
            int resultOil3 = this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeBrake();
            return resultOil3;
        }
        if (type == 3) {
            int resultOil4 = this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeBattery();
            return resultOil4;
        }
        if (type != 4) {
            return 0;
        }
        int resultOil5 = this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeElec();
        return resultOil5;
    }

    public int resetOilLifetime(int type) {
        Log.v(TAG, "resetOilLifetime is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "resetOilLifetime mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(10003, type);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (type == 0) {
            int resetResult = this.mTsManagerImpl.getCarCabinAdapterManager().resetOilLifetimeEngine();
            return resetResult;
        }
        if (type == 1) {
            int resetResult2 = this.mTsManagerImpl.getCarCabinAdapterManager().resetOilLifetimeTransmission();
            return resetResult2;
        }
        if (type == 2) {
            int resetResult3 = this.mTsManagerImpl.getCarCabinAdapterManager().resetOilLifetimeBrake();
            return resetResult3;
        }
        if (type == 3) {
            int resetResult4 = this.mTsManagerImpl.getCarCabinAdapterManager().resetOilLifetimeBattery();
            return resetResult4;
        }
        if (type != 4) {
            return 0;
        }
        int resetResult5 = this.mTsManagerImpl.getCarCabinAdapterManager().resetOilLifetimeELec();
        return resetResult5;
    }

    public long[] getOilRecordList(int type) {
        Log.v(TAG, "getOilRecordList is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "getOilRecordList mForTestValue is" + this.mForTest + " execute return");
            return new long[0];
        }
        return new long[0];
    }

    public int setOilRecordList(int type, long time, long total, long value) {
        Log.v(TAG, "setOilRecordList is run. ");
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "setOilRecordList mForTestValue is" + this.mForTest + " execute return");
        this.mTestCallback.enqueueMessage(10004, type);
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

    public int setFrontWiperCheckState(int state) {
        Log.v(TAG, "setFrontWiperCheckState is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "setFrontWiperCheckState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(10000, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setFrontWiperCheckState(state);
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

    public int setRearWiperCheckState(int state) {
        Log.v(TAG, "setRearWiperCheckState is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "setRearWiperCheckState mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(10001, state);
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setRearWiperCheckState(state);
    }

    @Deprecated
    public int resetTirePressure() {
        Log.v(TAG, "resetTirePressure is run. ");
        if (1 == this.mForTest) {
            Log.v(TAG, "resetTirePressure mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.enqueueMessage(10002, 0);
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
        if (1 != this.mForTest) {
            return 0;
        }
        Log.v(TAG, "getMileageValid mForTestValue is" + this.mForTest + " execute return");
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
        return this.mTsManagerImpl.getCarCabinAdapterManager().getEPBConfig();
    }

    public int getEPBType() {
        Log.v(TAG, "getEPBType is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getEPBType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getEPBType();
    }

    public int setEPBState(int state) {
        Log.v(TAG, "setEPBState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setEPBState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setEPBState(state);
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

    public int setOilUpdateTag(int value) {
        Log.v(TAG, "setOilUpdateTag is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setOilUpdateTag mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setOilUpdateTag(value);
    }

    public int getOilMaintainanceHint(int type) {
        Log.v(TAG, "getOilMaintainanceHint is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getOilMaintainanceHint mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 0) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceHintEngine();
        }
        if (type == 1) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceHintTransmission();
        }
        if (type == 2) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceHintBrake();
        }
        if (type == 3) {
            return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceHintCoolBladeBattery();
        }
        if (type != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceHintCoolMotorElec();
    }

    public int setTransportMode(int state) {
        Log.v(TAG, "setTransportMode is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setTransportMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setTransportMode(state);
    }

    public int getTransportMode() {
        Log.v(TAG, "getTransportMode is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getTransportMode mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getTransportMode();
    }

    public int setPowerOffChargedState(int state) {
        Log.v(TAG, "setPowerOffChargedState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "setPowerOffChargedState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setPowerOffChargedState(state);
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

    public void registerListener(AbsBYDAutoVehicleHealthListener listener) {
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBYDAutoVehicleHealthListener(listener);
            return;
        }
        if (listener == null) {
            return;
        }
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoVehicleHealthListener listener, int[] featureIds) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " subscribe health listener " + listener + " featureIDs = " + Arrays.toString(featureIds));
        if (!this.mAbsHealthListeners.contains(listener)) {
            this.mAbsHealthListeners.add(listener);
        }
        setFeatureIds(featureIds);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerCabinCallback();
    }

    public void unregisterListener(AbsBYDAutoVehicleHealthListener listener) {
        Log.d(TAG, "unregisterListener is run. ");
        if (1 == this.mForTest) {
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            this.mTestCallback.setAbsBYDAutoVehicleHealthListener(null);
            return;
        }
        Log.d(TAG, "unsubscribeHvacCallback: " + listener);
        if (listener == null) {
            return;
        }
        this.mAbsHealthListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public int getOilLifetimeNew(int type) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getOilLifetimeNew mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getOilLifetimeNew(type);
    }

    public int getOilMaintainanceDialogHint(int type) {
        if (1 == this.mForTest) {
            Log.v(TAG, " getOilMaintainanceDialogHint mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getOilMaintainanceDialogHint(type);
    }

    public int setOilMaintainanceNotHint(int type, int value) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setOilMaintainanceNotHint mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarCabinAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().setOilMaintainanceNotHint(type, value);
    }

    public int getBatteryHealthStatus() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getBatteryHealthStatus mForTestValue is 0 execute return");
            return -1;
        }
        return this.mTsManagerImpl.getCarCabinAdapterManager().getBatteryHealthStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void healthCallback(int id, int areaId, CabinPacket cabinPacket) {
        Log.i(TAG, "cabin propertyId:" + id);
        switch (id) {
            case 557850624:
                for (AbsBYDAutoVehicleHealthListener healthListener : this.mAbsHealthListeners) {
                    healthListener.onOilLifetimeChanged(cabinPacket.getOilHint());
                }
                break;
            case 557850625:
                for (AbsBYDAutoVehicleHealthListener healthListener2 : this.mAbsHealthListeners) {
                    healthListener2.onOilLifetimeChanged(0, cabinPacket.getOilLifetimeEngine());
                }
                caseFeatureIds(2126, Integer.valueOf(cabinPacket.getOilLifetimeEngine()));
                break;
            case 557850626:
                for (AbsBYDAutoVehicleHealthListener healthListener3 : this.mAbsHealthListeners) {
                    healthListener3.onOilLifetimeChanged(1, cabinPacket.getOilLifetimeTransmission());
                }
                caseFeatureIds(2136, Integer.valueOf(cabinPacket.getOilLifetimeTransmission()));
                break;
            case 557850627:
                for (AbsBYDAutoVehicleHealthListener healthListener4 : this.mAbsHealthListeners) {
                    healthListener4.onOilLifetimeChanged(3, cabinPacket.getOilLifetimeBrake());
                }
                caseFeatureIds(2096, Integer.valueOf(cabinPacket.getOilLifetimeBrake()));
                break;
            case 557850628:
                for (AbsBYDAutoVehicleHealthListener healthListener5 : this.mAbsHealthListeners) {
                    healthListener5.onOilLifetimeChanged(4, cabinPacket.getOilLifetimeBattery());
                }
                caseFeatureIds(2094, Integer.valueOf(cabinPacket.getOilLifetimeBattery()));
                break;
            case 557850629:
                for (AbsBYDAutoVehicleHealthListener healthListener6 : this.mAbsHealthListeners) {
                    healthListener6.onOilLifetimeChanged(2, cabinPacket.getOilLifetimeElec());
                }
                caseFeatureIds(2124, Integer.valueOf(cabinPacket.getOilLifetimeElec()));
                break;
            case 557850630:
                for (AbsBYDAutoVehicleHealthListener healthListener7 : this.mAbsHealthListeners) {
                    healthListener7.onResetOilLifetimeChanged(0, cabinPacket.getresetOilLifetimeEngine());
                }
                break;
            case 557850631:
                for (AbsBYDAutoVehicleHealthListener healthListener8 : this.mAbsHealthListeners) {
                    healthListener8.onResetOilLifetimeChanged(1, cabinPacket.getresetOilLifetimeTransmission());
                }
                break;
            case 557850632:
                for (AbsBYDAutoVehicleHealthListener healthListener9 : this.mAbsHealthListeners) {
                    healthListener9.onResetOilLifetimeChanged(2, cabinPacket.getresetOilLifetimeBrake());
                }
                break;
            case 557850633:
                for (AbsBYDAutoVehicleHealthListener healthListener10 : this.mAbsHealthListeners) {
                    healthListener10.onResetOilLifetimeChanged(3, cabinPacket.getresetOilLifetimeBattery());
                }
                break;
            case 557850634:
                for (AbsBYDAutoVehicleHealthListener healthListener11 : this.mAbsHealthListeners) {
                    healthListener11.onResetOilLifetimeChanged(4, cabinPacket.getresetOilLifetimeELec());
                }
                break;
            case 557850635:
                for (AbsBYDAutoVehicleHealthListener healthListener12 : this.mAbsHealthListeners) {
                    healthListener12.onOilLevelChanged(cabinPacket.getOilLevel());
                }
                break;
            case 557850636:
                Log.v(TAG, "ID_FRONT_WIPER_CHECK_STATER value is " + cabinPacket.getFrontWiperCheckStateR());
                for (AbsBYDAutoVehicleHealthListener healthListener13 : this.mAbsHealthListeners) {
                    healthListener13.onFrontWiperCheckStateChanged(cabinPacket.getFrontWiperCheckStateR());
                }
                break;
            case 557850638:
                for (AbsBYDAutoVehicleHealthListener healthListener14 : this.mAbsHealthListeners) {
                    healthListener14.onRearWiperCheckStateChanged(cabinPacket.getRearWiperCheckStateR());
                }
                break;
            case 557850640:
                for (AbsBYDAutoVehicleHealthListener healthListener15 : this.mAbsHealthListeners) {
                    healthListener15.onResetTirePressureStateChanged(cabinPacket.getresetTirePressure());
                }
                break;
            case 557850641:
                for (AbsBYDAutoVehicleHealthListener healthListener16 : this.mAbsHealthListeners) {
                    healthListener16.onOilUpdateStateChanged(cabinPacket.getOilUpdateState());
                }
                break;
            case 557850647:
                for (AbsBYDAutoVehicleHealthListener healthListener17 : this.mAbsHealthListeners) {
                    healthListener17.onEPBStateChanged(cabinPacket.getEPBStateR());
                }
                break;
            case 557850648:
                for (AbsBYDAutoVehicleHealthListener healthListener18 : this.mAbsHealthListeners) {
                    healthListener18.onResetOilLifetimeChanged(0, cabinPacket.getResetOilLifetimeChangedEngineR());
                }
                caseFeatureIds(2118, Integer.valueOf(cabinPacket.getResetOilLifetimeChangedEngineR()));
                break;
            case 557850649:
                for (AbsBYDAutoVehicleHealthListener healthListener19 : this.mAbsHealthListeners) {
                    healthListener19.onResetOilLifetimeChanged(1, cabinPacket.getResetOilLifetimeChangedTransMissionR());
                }
                caseFeatureIds(2137, Integer.valueOf(cabinPacket.getResetOilLifetimeChangedTransMissionR()));
                break;
            case 557850650:
                for (AbsBYDAutoVehicleHealthListener healthListener20 : this.mAbsHealthListeners) {
                    healthListener20.onResetOilLifetimeChanged(3, cabinPacket.getResetOilLifetimeChangedBreakR());
                }
                caseFeatureIds(2097, Integer.valueOf(cabinPacket.getResetOilLifetimeChangedBreakR()));
                break;
            case 557850651:
                for (AbsBYDAutoVehicleHealthListener healthListener21 : this.mAbsHealthListeners) {
                    healthListener21.onResetOilLifetimeChanged(4, cabinPacket.getResetOilLifetimeChangedBatteryR());
                }
                caseFeatureIds(2095, Integer.valueOf(cabinPacket.getResetOilLifetimeChangedBatteryR()));
                break;
            case 557850652:
                for (AbsBYDAutoVehicleHealthListener healthListener22 : this.mAbsHealthListeners) {
                    healthListener22.onResetOilLifetimeChanged(2, cabinPacket.getResetOilLifetimeChangedElecR());
                }
                caseFeatureIds(2125, Integer.valueOf(cabinPacket.getResetOilLifetimeChangedElecR()));
                break;
            case 557850653:
                for (AbsBYDAutoVehicleHealthListener healthListener23 : this.mAbsHealthListeners) {
                    healthListener23.onOilMaintainanceHintChanged(0, cabinPacket.getOilMaintainanceHintOilLife());
                }
                caseFeatureIds(2102, Integer.valueOf(cabinPacket.getOilMaintainanceHintOilLife()));
                break;
            case 557850654:
                for (AbsBYDAutoVehicleHealthListener healthListener24 : this.mAbsHealthListeners) {
                    healthListener24.onOilMaintainanceHintChanged(1, cabinPacket.getOilMaintainanceHintTransmissionOilLife());
                }
                caseFeatureIds(2103, Integer.valueOf(cabinPacket.getOilMaintainanceHintTransmissionOilLife()));
                break;
            case 557850655:
                for (AbsBYDAutoVehicleHealthListener healthListener25 : this.mAbsHealthListeners) {
                    healthListener25.onOilMaintainanceHintChanged(2, cabinPacket.getOilMaintainanceHintBrakeFluidLife());
                }
                caseFeatureIds(2127, Integer.valueOf(cabinPacket.getOilMaintainanceHintBrakeFluidLife()));
                break;
            case 557850656:
                for (AbsBYDAutoVehicleHealthListener healthListener26 : this.mAbsHealthListeners) {
                    healthListener26.onOilMaintainanceHintChanged(4, cabinPacket.getOilMaintainanceHintLongLifeCoolant());
                }
                caseFeatureIds(2128, Integer.valueOf(cabinPacket.getOilMaintainanceHintLongLifeCoolant()));
                break;
            case 557850657:
                for (AbsBYDAutoVehicleHealthListener healthListener27 : this.mAbsHealthListeners) {
                    healthListener27.onOilMaintainanceHintChanged(3, cabinPacket.getOilMaintainanceHintBladeBatteryCoolant());
                }
                caseFeatureIds(2129, Integer.valueOf(cabinPacket.getOilMaintainanceHintBladeBatteryCoolant()));
                break;
            case 557850659:
                for (AbsBYDAutoVehicleHealthListener healthListener28 : this.mAbsHealthListeners) {
                    healthListener28.onTransportModeChanged(cabinPacket.getTransportModeR());
                }
                break;
            case 557850660:
                for (AbsBYDAutoVehicleHealthListener healthListener29 : this.mAbsHealthListeners) {
                    healthListener29.onTransportModeStateHintChanged(cabinPacket.getTransportModeStateHintR());
                }
                break;
            case 557850662:
                for (AbsBYDAutoVehicleHealthListener healthListener30 : this.mAbsHealthListeners) {
                    healthListener30.onEPBHintChanged(cabinPacket.getEpbHintR());
                }
                break;
            case 557850663:
                for (AbsBYDAutoVehicleHealthListener healthListener31 : this.mAbsHealthListeners) {
                    healthListener31.onOilLifetimeNewChanged(cabinPacket.getOilLifetimeNewOilLifeR());
                }
                break;
            case 557850664:
                for (AbsBYDAutoVehicleHealthListener healthListener32 : this.mAbsHealthListeners) {
                    healthListener32.onOilLifetimeNewChanged(cabinPacket.getOilLifetimeNewTransmissionR());
                }
                break;
            case 557850665:
                for (AbsBYDAutoVehicleHealthListener healthListener33 : this.mAbsHealthListeners) {
                    healthListener33.onOilLifetimeNewChanged(cabinPacket.getOilLifetimeNewBrakeFluidLifeBrakeFluidLifeR());
                }
                break;
            case 557850666:
                for (AbsBYDAutoVehicleHealthListener healthListener34 : this.mAbsHealthListeners) {
                    healthListener34.onOilLifetimeNewChanged(cabinPacket.getOilLifetimeNewBladeBatteryCoolantR());
                }
                break;
            case 557850667:
                for (AbsBYDAutoVehicleHealthListener healthListener35 : this.mAbsHealthListeners) {
                    healthListener35.onOilLifetimeNewChanged(cabinPacket.getOilLifetimeNewLongLifeCoolantLongLifeCoolantR());
                }
                break;
            case 557850668:
                for (AbsBYDAutoVehicleHealthListener healthListener36 : this.mAbsHealthListeners) {
                    healthListener36.onOilMaintainanceDialogHintChanged(cabinPacket.getOilMaintainanceDialogHintOilLifeR());
                }
                break;
            case 557850669:
                for (AbsBYDAutoVehicleHealthListener healthListener37 : this.mAbsHealthListeners) {
                    healthListener37.onOilMaintainanceDialogHintChanged(cabinPacket.getOilMaintainanceDialogHintTransmissionR());
                }
                break;
            case 557850671:
                for (AbsBYDAutoVehicleHealthListener healthListener38 : this.mAbsHealthListeners) {
                    healthListener38.onOilMaintainanceDialogHintChanged(cabinPacket.getOilMaintainanceDialogHintBladeBatteryCoolantR());
                }
                break;
            case 557850672:
                for (AbsBYDAutoVehicleHealthListener healthListener39 : this.mAbsHealthListeners) {
                    healthListener39.onOilMaintainanceDialogHintChanged(cabinPacket.getOilMaintainanceDialogHintLongLifeCoolantR());
                }
                break;
            case 557850673:
                for (AbsBYDAutoVehicleHealthListener healthListener40 : this.mAbsHealthListeners) {
                    healthListener40.onOilMaintainanceDialogHintChanged(cabinPacket.getOilMaintainanceDialogHintBrakeFluidLifeR());
                }
                break;
            case 557850679:
                for (AbsBYDAutoVehicleHealthListener healthListener41 : this.mAbsHealthListeners) {
                    healthListener41.onBatteryHealthStatusChanged(cabinPacket.getBatteryHealthStatusR());
                }
                break;
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
                    switch (eventType) {
                        case 75:
                            bydAutoEventValue.intValue = getTyreFaultLocation();
                            sparseArray.put(75, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 644:
                            bydAutoEventValue.intValue = getEPBConfig();
                            sparseArray.put(644, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 646:
                            bydAutoEventValue.intValue = getEPBState();
                            sparseArray.put(646, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 648:
                            bydAutoEventValue.intValue = getEPBType();
                            sparseArray.put(648, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 649:
                            bydAutoEventValue.intValue = getTransportMode();
                            sparseArray.put(649, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 650:
                            bydAutoEventValue.intValue = getTransportModeStateHint();
                            sparseArray.put(650, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1057:
                            bydAutoEventValue.intValue = getOilLevel();
                            sparseArray.put(1057, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1058:
                            bydAutoEventValue.intValue = getOilLevel();
                            sparseArray.put(1058, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1059:
                            bydAutoEventValue.intValue = getOilUpdateState();
                            sparseArray.put(1059, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1073:
                            bydAutoEventValue.intValue = getFrontWiperCheckState();
                            sparseArray.put(1073, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1141:
                            bydAutoEventValue.intValue = getRearWiperCheckState();
                            sparseArray.put(1141, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1263:
                            bydAutoEventValue.intValue = getOilHint();
                            sparseArray.put(1263, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 1303:
                            bydAutoEventValue.intValue = getRearWiperCheckState();
                            sparseArray.put(1303, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2094:
                            bydAutoEventValue.intValue = getOilLifetime(3);
                            sparseArray.put(2094, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2096:
                            bydAutoEventValue.intValue = getOilLifetime(2);
                            sparseArray.put(2096, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2102:
                            bydAutoEventValue.intValue = getOilMaintainanceHint(0);
                            sparseArray.put(2102, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2103:
                            bydAutoEventValue.intValue = getOilMaintainanceHint(i);
                            sparseArray.put(2103, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2124:
                            bydAutoEventValue.intValue = getOilLifetime(4);
                            sparseArray.put(2124, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2126:
                            bydAutoEventValue.intValue = getOilLifetime(0);
                            sparseArray.put(2126, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2127:
                            bydAutoEventValue.intValue = getOilMaintainanceHint(2);
                            sparseArray.put(2127, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2128:
                            bydAutoEventValue.intValue = getOilMaintainanceHint(4);
                            sparseArray.put(2128, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2129:
                            bydAutoEventValue.intValue = getOilMaintainanceHint(3);
                            sparseArray.put(2129, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
                        case 2136:
                            bydAutoEventValue.intValue = getOilLifetime(i);
                            sparseArray.put(2136, Integer.valueOf(bydAutoEventValue.intValue));
                            break;
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
        for (AbsBYDAutoVehicleHealthListener listener : this.mAbsHealthListeners) {
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
        if (eventType == 624) {
            resetOilLifetime(intValue);
            return 1;
        }
        if (eventType == 625) {
            resetOilLifetime(intValue);
            return 1;
        }
        if (eventType == 647) {
            setEPBState(intValue);
            return 1;
        }
        if (eventType == 651) {
            setTransportMode(intValue);
            return 1;
        }
        if (eventType == 836) {
            resetOilLifetime(intValue);
            return 1;
        }
        if (eventType == 1056) {
            setOilUpdateTag(intValue);
            return 1;
        }
        if (eventType == 1074) {
            setFrontWiperCheckState(intValue);
            return 1;
        }
        if (eventType == 1264) {
            resetOilLifetime(intValue);
            return 1;
        }
        if (eventType == 1304) {
            setRearWiperCheckState(intValue);
            return 1;
        }
        if (eventType == 4566) {
            setPowerOffChargedState(intValue);
            return 1;
        }
        Log.v(TAG, "case fail ! please check out");
        return -1;
    }
}
